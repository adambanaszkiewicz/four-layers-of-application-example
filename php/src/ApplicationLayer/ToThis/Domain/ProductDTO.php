<?php

declare(strict_types=1);

namespace App\ApplicationLayer\ToThis\Domain;

final class ProductDTO
{
    public function __construct(
        public readonly string $id,
        public readonly bool $available,
        public readonly int $stock,
        public readonly int $maxProductsToBuy,
    ) {
    }
}
