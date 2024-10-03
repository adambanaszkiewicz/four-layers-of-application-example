<?php

declare(strict_types=1);

namespace App\UserInterfaceLayer\FromThis\Domain;

use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use DomainException;

final class Cart
{
    private string $id;
    private Collection $lineItems;

    public function __construct()
    {
        $this->lineItems = new ArrayCollection();
    }

    public function addProduct(
        ProductDTO $productToAdd,
        int $qty,
    ): void {
        if (!$productToAdd->available) {
            throw new DomainException('This product is unavailable.');
        }

        $productInCart = $this->findLineItem($productToAdd->id);

        if ($productInCart) {
            $destinationQty = $productInCart->qty + $qty;
        } else {
            $destinationQty = $qty;
        }

        if ($productToAdd->stock < $destinationQty) {
            throw new DomainException('This product is out of stock.');
        }

        if ($destinationQty > $productToAdd->maxProductsToBuy) {
            throw new DomainException(sprintf('You cannot buy more than %d pieces.', $productToAdd->maxProductsToBuy));
        }

        if ($productInCart) {
            $productInCart->qty = $destinationQty;
        } else {
            $this->lineItems->add(new LineItem($productToAdd->id, $qty));
        }
    }

    private function findLineItem(string $productId): ?LineItem
    {
        return $this->lineItems->findFirst(function (LineItem $lineItem) use ($productId) {
            return $lineItem->productId === $productId;
        });
    }
}
