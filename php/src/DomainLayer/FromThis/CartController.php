<?php

declare(strict_types=1);

namespace App\DomainLayer\FromThis;

use Doctrine\DBAL\Connection;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpKernel\Exception\NotFoundHttpException;

final class CartController extends AbstractController
{
    public function __construct(
        private readonly CartRepository $repository,
        private readonly Connection $connection,
    ) {
    }

    public function addToCart(Request $request): Response
    {
        $productId = $request->request->get('productId');
        $qty = $request->request->get('qty');

        $productToAdd = $this->getProduct($productId);
        /** @var Cart $cart */
        $cart = $this->repository->findOneBy(['user_id' => $this->getUser()->getId()]);
        $product = $cart->getProduct($productId);

        if ($product) {
            $qty = $product->getQty() + $qty;
            $product->setQty($qty);
        } else {
            $product = new Product($productId, $qty, $productToAdd['price']);
        }

        if ($productToAdd['stock'] < $qty) {
            throw new NotFoundHttpException('This product is out of stock.');
        }

        $maxToBuy = $this->getMaxProductAmountToBuy($productId);
        if ($maxToBuy > $qty) {
            throw new NotFoundHttpException(sprintf('Cannot buy more than %s pieces of this product.', $maxToBuy));
        }

        $cart->addProduct($product);
        $this->repository->save($cart);

        return new Response();
    }

    private function getProduct(string $productId): array
    {
        $product = $this->connection->createQueryBuilder()
            ->select('*')
            ->from('products')
            ->where('id = :id')
            ->setParameter('id', $productId)
            ->setMaxResults(1)
            ->fetchAssociative();

        if (!$product || !$product['available']) {
            throw new NotFoundHttpException('This product does not exist.');
        }

        return $product;
    }

    private function getMaxProductAmountToBuy(string $productId): int
    {
        /** @var array|null $row */
        $row = $this->connection->createQueryBuilder()
            ->select('max_buy_ammount')
            ->from('products_settings')
            ->where('id = :id')
            ->setParameter('id', $productId)
            ->setMaxResults(1)
            ->fetchAssociative();

        return (int) ($row['max_buy_ammount'] ?? 100);
    }
}
