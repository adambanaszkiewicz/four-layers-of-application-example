<?php

declare(strict_types=1);

namespace App\InfrastructureLayer\FromThis;

use Doctrine\DBAL\Connection;
use Symfony\Component\Filesystem\Filesystem;

final class DownloadManager
{
    public function __construct(
        private readonly Connection $connection,
        private readonly Filesystem $filesystem,
        private readonly string $root,
    ) {
    }

    public function fileExistsOnServer(int $fileId): bool
    {
        /** @var array $file */
        $file = $this->connection->createQueryBuilder()
            ->select('filepath, filename, expiration_date')
            ->from('files')
            ->where('id = :id')
            ->setMaxResults(1)
            ->setParameter('id', $fileId)
            ->fetchAssociative();

        /** Business logic... */

        return $this->filesystem->exists($this->root.'/'.$file['filename']);
    }
}

