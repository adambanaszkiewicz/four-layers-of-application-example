<?php

declare(strict_types=1);

namespace App\UserInterface\ToThis\UserInterface\CLI\Command;

use App\UserInterfaceLayer\ToThis\Application\UseCase\AddProductToCart;
use Symfony\Component\Console\Attribute\AsCommand;
use Symfony\Component\Console\Command\Command;
use Symfony\Component\Console\Input\InputArgument;
use Symfony\Component\Console\Input\InputInterface;
use Symfony\Component\Console\Output\OutputInterface;

#[AsCommand(name: 'add-product')]
final class AddProductToCartCommand extends Command
{
    public function __construct(
        private readonly AddProductToCart $addProductToCart,
    ) {
        parent::__construct();
    }

    protected function configure()
    {
        $this->addArgument('productId', InputArgument::REQUIRED);
        $this->addArgument('qty', InputArgument::REQUIRED);
        $this->addArgument('userId', InputArgument::REQUIRED);
    }

    protected function execute(InputInterface $input, OutputInterface $output): int
    {
        try {
            ($this->addProductToCart)(
                $input->getArgument('productId'),
                $input->getArgument('qty'),
                $input->getArgument('userId')
            );

            $output->writeln('Product added to cart successfully!');

            return Command::SUCCESS;
        } catch (\Exception $e) {
            $output->writeln('<error>' . $e->getMessage() . '</error>');

            return Command::FAILURE;
        }
    }
}
