<?php

declare(strict_types=1);

namespace App\InfrastructureLayer\ToThis;

final class DownloadManager
{
    public function __construct(
        private readonly FilesFinderInterface $filesFinder,
        private readonly FilesystemInterface $filesystem,
    ) {
    }

    public function fileExistsOnServer(int $fileId): bool
    {
        $file = $this->filesFinder->find($fileId);

        if (!$file) {
            return false;
        }

        /** Business logic... */

        return $this->filesystem->exists($file['filepath']);
    }
}

