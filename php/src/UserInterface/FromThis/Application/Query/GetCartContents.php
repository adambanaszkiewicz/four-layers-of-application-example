<?php

declare(strict_types=1);

namespace App\UserInterfaceLayer\FromThis\Application\Query;

use Doctrine\DBAL\Connection;

final class GetCartContents
{
    public function __construct(
        private readonly Connection $connection,
    ) {
    }

    public function __invoke(
        string $userId,
    ): array {
        return $this->connection->fetchAllAssociative(
            'SELECT p.name, li.productId, li.qty FROM [...]',
            ['user_id' => $userId],
        );
    }
}
