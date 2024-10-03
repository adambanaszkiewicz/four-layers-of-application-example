<?php

declare(strict_types=1);

namespace App\UserInterfaceLayer\ToThis\Infrastructure\Mysql;

use App\UserInterfaceLayer\ToThis\Domain\Cart;
use App\UserInterfaceLayer\ToThis\Domain\CartRepositoryInterface;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

final class MysqlCartRepository extends ServiceEntityRepository implements CartRepositoryInterface
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Cart::class);
    }

    public function get(string $id): Cart
    {
        $cart = $this->find($id);

        if (!$cart instanceof Cart) {
            return new Cart();
        }

        return $cart;
    }

    public function save(Cart $cart): void
    {
        $this->_em->persist($cart);
        $this->_em->flush();
    }
}
