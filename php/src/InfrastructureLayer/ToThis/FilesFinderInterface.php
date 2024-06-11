<?php

declare(strict_types=1);

namespace App\InfrastructureLayer\ToThis;

interface FilesFinderInterface
{
    public function find(int $fileId): ?array;
}























