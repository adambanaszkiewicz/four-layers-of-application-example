<?php

declare(strict_types=1);

namespace App\Tests\Units\DomainLayer\ToThis\Domain;

use App\DomainLayer\ToThis\Domain\Cart;
use App\DomainLayer\ToThis\Domain\ProductDTO;
use DomainException;
use PHPUnit\Framework\TestCase;

final class CartTest extends TestCase
{
    public function testAddProductThrowsExceptionWhenExceedingMaxProductsToBuy(): void
    {
        $this->expectException(DomainException::class);
        $this->expectExceptionMessage('You cannot buy more than 5 pieces.');

        $cart = new Cart();
        $product = new ProductDTO('product1', true, 10, 5);

        $cart->addProduct($product, 6);
    }

    public function testAddProductThrowsExceptionWhenOutOfStock(): void
    {
        $this->expectException(DomainException::class);
        $this->expectExceptionMessage('This product is out of stock.');

        $cart = new Cart();
        $product = new ProductDTO('product1', true, 4, 5);

        $cart->addProduct($product, 5);
        $cart->addProduct($product, 1);
    }

    public function testAddProductThrowsExceptionWhenProductIsUnavailable(): void
    {
        $this->expectException(DomainException::class);
        $this->expectExceptionMessage('This product is unavailable.');

        $cart = new Cart();
        $product = new ProductDTO('product1', false, 10, 5);

        $cart->addProduct($product, 1);
    }

    // Rest of tests goes here...
}
