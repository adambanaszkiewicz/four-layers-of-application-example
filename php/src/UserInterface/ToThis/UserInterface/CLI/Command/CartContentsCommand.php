<?php

declare(strict_types=1);

namespace App\UserInterface\ToThis\UserInterface\CLI\Command;

use App\UserInterfaceLayer\ToThis\Application\Query\GetCartContents;
use Symfony\Component\Console\Attribute\AsCommand;
use Symfony\Component\Console\Command\Command;
use Symfony\Component\Console\Input\InputArgument;
use Symfony\Component\Console\Input\InputInterface;
use Symfony\Component\Console\Output\OutputInterface;

#[AsCommand(name: 'cart-contents')]
final class CartContentsCommand extends Command
{
    public function __construct(
        private readonly GetCartContents $getCartContents,
    ) {
        parent::__construct();
    }

    protected function configure()
    {
        $this->addArgument('userId', InputArgument::REQUIRED);
    }

    protected function execute(InputInterface $input, OutputInterface $output): int
    {
        $products = ($this->getCartContents)($input->getArgument('userId'));

        foreach ($products as $product) {
            $output->writeln($product['name']);
        }

        return Command::SUCCESS;
    }
}
