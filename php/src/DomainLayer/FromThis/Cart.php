<?php

declare(strict_types=1);

namespace App\DomainLayer\FromThis;

use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;

final class Cart
{
    private string $id;
    private string $userId;
    private Collection $products;

    public function __construct()
    {
        $this->products = new ArrayCollection();
    }

    public function getProduct(string $productId): Product
    {
        return $this->products->get($productId);
    }

    public function addProduct(Product $product)
    {
        $this->products->add($product);
    }
}
