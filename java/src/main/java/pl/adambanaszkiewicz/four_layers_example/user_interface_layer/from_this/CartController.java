package pl.adambanaszkiewicz.four_layers_example.user_interface_layer.from_this;

import pl.adambanaszkiewicz.four_layers_example.user_interface_layer.from_this.application.usecase.AddProductToCart;
import pl.adambanaszkiewicz.four_layers_example.user_interface_layer.from_this.application.query.GetCartContents;
import pl.adambanaszkiewicz.four_layers_example.user_interface_layer.from_this.domain.DomainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

@RestController("UserInterfaceLayerFromThisCartController")
@RequestMapping("/user-interface-layer/from-this/cart")
public class CartController {
    private final AddProductToCart addProductToCart;
    private final GetCartContents getCartContents;

    @Autowired
    public CartController(
        AddProductToCart addProductToCart,
        GetCartContents getCartContents
    ) {
        this.addProductToCart = addProductToCart;
        this.getCartContents = getCartContents;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addToCart(@RequestParam String productId, @RequestParam int qty) throws DomainException {
        String userId = "1"; // Get user ID, from session for example

        try {
            addProductToCart.execute(
                productId,
                qty,
                userId
            );

            return ResponseEntity.ok().build();
        } catch (DomainException e) {
            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Internal Server Error");
        }
    }

    @GetMapping("/cart/products")
    public ResponseEntity<List<Map<String, Object>>> getCartProducts() {
        String userId = "1"; // Get user ID, from session for example

        return ResponseEntity.ok(
            this.getCartContents.get(userId)
        );
    }
}
