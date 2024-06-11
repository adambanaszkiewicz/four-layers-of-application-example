package pl.adambanaszkiewicz.four_layers_example.domain_layer.from_this;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.Optional;
import java.util.Map;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/from-this/cart")
public class BadCartController {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping("/add")
    public ResponseEntity<Void> addToCart(
        @RequestParam String productId,
        @RequestParam int qty
    ) {
        ProductToAdd productToAdd = getProduct(productId);

        Cart cart = cartRepository.findOneByUserId(getCurrentUserId()).orElse(new Cart());

        Product product = cart.getProduct(productId);

        if (product != null) {
            qty += product.getQty();
            product.setQty(qty);
        } else {
            product = new Product(productId, qty, productToAdd.getPrice());
        }

        if (productToAdd.getStock() < qty) {
            throw new ResponseStatusException(NOT_FOUND, "This product is out of stock.");
        }

        int maxToBuy = getMaxProductAmountToBuy(productId);
        if (qty > maxToBuy) {
            throw new ResponseStatusException(NOT_FOUND, String.format("Cannot buy more than %d pieces of this product.", maxToBuy));
        }

        cart.addProduct(product);
        cartRepository.save(cart);

        return ResponseEntity.ok().build();
    }

    private ProductToAdd getProduct(String productId) {
        ProductToAdd product = (ProductToAdd) jdbcTemplate.queryForObject(
            "SELECT * FROM products WHERE id = ? AND available = true LIMIT 1",
            new Object[]{productId},
            new BeanPropertyRowMapper(ProductToAdd.class)
        );

        if (product == null || !product.getAvailable()) {
            throw new ResponseStatusException(NOT_FOUND, "This product does not exist.");
        }

        return product;
    }

    private int getMaxProductAmountToBuy(String productId) {
        try {
            Map<String, Object> row = jdbcTemplate.queryForMap(
                "SELECT max_buy_amount FROM products_settings WHERE id = ? LIMIT 1",
                productId
            );

            return (int) row.getOrDefault("max_buy_amount", 100);
        } catch (Exception e) {
            return 100;
        }
    }

    private String getCurrentUserId() {
        // Mock implementation, replace with actual logic to get the current user's ID
        return "user-123";
    }
}
