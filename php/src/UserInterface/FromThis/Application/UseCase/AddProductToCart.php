<?php

declare(strict_types=1);

namespace App\UserInterfaceLayer\FromThis\Application\UseCase;

use App\UserInterfaceLayer\FromThis\Domain\CartRepositoryInterface;
use App\UserInterfaceLayer\FromThis\Domain\ProductDTO;

final class AddProductToCart
{
    public function __construct(
        private readonly CartRepositoryInterface $repository,
    ) {
    }

    public function __invoke(
        string $productId,
        int $qty,
        string $userId,
    ): void {
        // Begin transaction
        try {
            $product = $this->getProduct($productId);

            $cart = $this->repository->get($userId);
            $cart->addProduct($product, $qty);

            $this->repository->save($cart);

            // Commit transaction
        } catch (\DomainException $e) {
            // Do some domain logic after domain exception
            throw $e;
        } catch (\Exception $e) {
            // Rollback transaction
            throw $e;
        }
    }

    private function getProduct(string $productId): ProductDTO
    {
        $maxProductsToBuy = 2;
        $stock = 10;

        /**
         * To simplify code, create DTO directly. In real world we would
         * need to fetch product from database, and create ProductDTO instance.
         * ProductDTO is here as a bridge between data from source
         * (database or external module) and our Domain in this module.
         */
        return new ProductDTO($productId, true, $stock, $maxProductsToBuy);
    }
}
