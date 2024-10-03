<?php

declare(strict_types=1);

namespace App\ApplicationLayer\ToThis\Domain;

interface CartRepositoryInterface
{
    public function get(string $userId): Cart;

    public function save(Cart $cart): void;
}
