package pl.adambanaszkiewicz.four_layers_example.domain_layer.to_this;

import pl.adambanaszkiewicz.four_layers_example.domain_layer.to_this.domain.CartRepositoryInterface;
import pl.adambanaszkiewicz.four_layers_example.domain_layer.to_this.domain.Cart;
import pl.adambanaszkiewicz.four_layers_example.domain_layer.to_this.domain.ProductDTO;
import pl.adambanaszkiewicz.four_layers_example.domain_layer.to_this.domain.DomainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/to-this/cart")
public class CartController {

    private final CartRepositoryInterface repository;

    @Autowired
    public CartController(CartRepositoryInterface repository) {
        this.repository = repository;
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addToCart(
        @RequestParam String productId,
        @RequestParam int qty
    ) throws DomainException {
        ProductDTO product = getProduct(productId);

        String userId = "1"; // Get user ID, from session for example
        Cart cart = repository.get(userId);
        cart.addProduct(product, qty);

        repository.save(cart);

        return ResponseEntity.ok().build();
    }

    private ProductDTO getProduct(String productId) {
        int maxProductsToBuy = 2;
        int stock = 10;

        /**
         * To simplify code, create DTO directly. In real world we would
         * need to fetch product from database, and create ProductDTO instance.
         * ProductDTO is here as a bridge between data from source
         * (database or external module) and our Domain in this module.
         */
        return new ProductDTO(productId, true, stock, maxProductsToBuy);
    }
}
