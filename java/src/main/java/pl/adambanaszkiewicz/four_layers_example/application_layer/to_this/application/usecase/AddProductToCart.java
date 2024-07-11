package pl.adambanaszkiewicz.four_layers_example.application_layer.to_this.application.usecase;

import pl.adambanaszkiewicz.four_layers_example.application_layer.to_this.domain.CartRepositoryInterface;
import pl.adambanaszkiewicz.four_layers_example.application_layer.to_this.domain.Cart;
import pl.adambanaszkiewicz.four_layers_example.application_layer.to_this.domain.ProductDTO;
import pl.adambanaszkiewicz.four_layers_example.application_layer.to_this.domain.DomainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Service;

@Service
public class AddProductToCart {
    private final CartRepositoryInterface repository;

    @Autowired
    public AddProductToCart(
        @Qualifier("ApplicationLayerToThisCartRepository") CartRepositoryInterface repository
    ) {
        this.repository = repository;
    }

    public void execute(
        String productId,
        int qty,
        String userId
    ) throws Exception {
        // Begin transaction
        try {
            ProductDTO product = getProduct(productId);

            Cart cart = repository.get(userId);
            cart.addProduct(product, qty);

            repository.save(cart);

            // Commit transaction
        } catch (DomainException e) {
            // Do some domain logic after domain exception
            throw e;
        } catch (Exception e) {
            // Rollback transaction
            throw e;
        }
    }

    private ProductDTO getProduct(String productId) {
        int maxProductsToBuy = 2;
        int stock = 10;

        /**
         * To simplify code, create DTO directly here. In real world we would
         * need to fetch product from database, and create ProductDTO instance.
         * ProductDTO is here as a bridge between data from source
         * (database or external module) and our Domain in this module.
         * We could use separate Service to fetch this DTO.
         */
        return new ProductDTO(productId, true, stock, maxProductsToBuy);
    }
}
