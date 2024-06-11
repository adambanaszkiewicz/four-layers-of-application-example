<?php

declare(strict_types=1);

namespace App\InfrastructureLayer\ToThis;

interface FilesystemInterface
{
    public function exists(string $filepath): bool;
}






















