<?php

declare(strict_types=1);

namespace App\ApplicationLayer\FromThis;

use App\ApplicationLayer\FromThis\Domain\CartRepositoryInterface;
use App\ApplicationLayer\FromThis\Domain\ProductDTO;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;

final class CartController extends AbstractController
{
    public function __construct(
        private readonly CartRepositoryInterface $repository,
    ) {
    }

    public function addToCart(Request $request): Response
    {
        $productId = $request->request->get('productId');
        $qty = (int) $request->request->get('qty');

        $product = $this->getProduct($productId);

        $cart = $this->repository->get($this->getUser()->getId());
        $cart->addProduct($product, $qty);

        $this->repository->save($cart);

        return new Response();
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
