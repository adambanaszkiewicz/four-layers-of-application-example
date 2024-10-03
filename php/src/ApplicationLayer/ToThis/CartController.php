<?php

declare(strict_types=1);

namespace App\ApplicationLayer\ToThis;

use App\ApplicationLayer\ToThis\Application\Query\GetCartContents;
use App\ApplicationLayer\ToThis\Application\UseCase\AddProductToCart;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;

final class CartController extends AbstractController
{
    public function __construct(
        private readonly AddProductToCart $addProductToCart,
        private readonly GetCartContents $getCartContents,
    ) {
    }

    public function addToCart(Request $request): Response
    {
        $userId = $this->getUser()->getId();

        try {
            ($this->addProductToCart)(
                $request->request->get('productId'),
                (int) $request->request->get('qty'),
                $userId,
            );

            return new Response('', Response::HTTP_OK);
        } catch (\DomainException $e) {
            return new Response('Not found', Response::HTTP_NOT_FOUND);
        } catch (\Exception $e) {
            return new Response('Internal Server Error', Response::HTTP_INTERNAL_SERVER_ERROR);
        }
    }

    public function getCartProducts(): Response
    {
        $userId = $this->getUser()->getId();

        return new JsonResponse(
            ($this->getCartContents)($userId),
        );
    }
}
