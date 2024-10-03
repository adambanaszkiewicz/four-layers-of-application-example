<?php

declare(strict_types=1);

namespace App\UserInterfaceLayer\FromThis\Domain;

interface CartRepositoryInterface
{
    public function get(string $userId): Cart;

    public function save(Cart $cart): void;
}
