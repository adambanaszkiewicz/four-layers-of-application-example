<?php

declare(strict_types=1);

namespace App\UserInterfaceLayer\ToThis\Domain;

final class LineItem
{
    public function __construct(
        public readonly string $productId,
        public int $qty,
    ) {
    }
}
