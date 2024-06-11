<?php

declare(strict_types=1);

namespace App\InfrastructureLayer\ToThis\Infrastructure;

use App\InfrastructureLayer\ToThis\FilesFinderInterface;
use Doctrine\DBAL\Connection;

final class MySQLFilesFinder implements FilesFinderInterface
{
    public function __construct(
        private readonly Connection $connection,
    ) {
    }

    public function find(int $fileId): array
    {
        return $this->connection->createQueryBuilder()
            ->select('filepath, filename, expiration_date')
            ->from('files')
            ->where('id = :id')
            ->setMaxResults(1)
            ->setParameter('id', $fileId)
            ->fetchAssociative();
    }
}

