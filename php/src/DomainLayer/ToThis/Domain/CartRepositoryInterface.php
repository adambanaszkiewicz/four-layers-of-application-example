<?php

declare(strict_types=1);

namespace App\DomainLayer\ToThis\Domain;

interface CartRepositoryInterface
{
    public function get(string $userId): Cart;

    public function save(Cart $cart): void;
}
