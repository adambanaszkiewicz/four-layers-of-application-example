<?php

declare(strict_types=1);

namespace App\DomainLayer\FromThis;

final class Product
{
    private string $id;

    public function __construct(
        private string $productId,
        private int $qty,
        private float $price,
    ) {
    }

    public function setQty(int $qty): void
    {
        $this->qty = $qty;
    }

    public function getQty(): int
    {
        return $this->qty;
    }

    public function getProductId(): string
    {
        return $this->productId;
    }

    public function setProductId(string $productId): void
    {
        $this->productId = $productId;
    }

    public function getPrice(): float
    {
        return $this->price;
    }

    public function setPrice(float $price): void
    {
        $this->price = $price;
    }
}
