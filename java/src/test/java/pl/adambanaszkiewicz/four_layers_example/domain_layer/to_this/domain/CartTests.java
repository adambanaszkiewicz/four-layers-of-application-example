package pl.adambanaszkiewicz.four_layers_example;

import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import pl.adambanaszkiewicz.four_layers_example.domain_layer.to_this.domain.DomainException;
import pl.adambanaszkiewicz.four_layers_example.domain_layer.to_this.domain.ProductDTO;
import pl.adambanaszkiewicz.four_layers_example.domain_layer.to_this.domain.Cart;

@SpringBootTest
class CartTests {

	private Cart cart;
    private ProductDTO availableProduct;
    private ProductDTO unavailableProduct;

    @BeforeEach
    public void setUp() {
        cart = new Cart();
        availableProduct = new ProductDTO("1", true, 10, 5);
        unavailableProduct = new ProductDTO("2", false, 0, 0);
    }

    @Test
    public void testAddProduct_availableProduct() {
        assertDoesNotThrow(() -> cart.addProduct(availableProduct, 3));
    }

    @Test
    public void testAddProduct_unavailableProduct() {
        DomainException exception = assertThrows(DomainException.class, () -> cart.addProduct(unavailableProduct, 1));
        assertEquals("This product is unavailable.", exception.getMessage());
    }

    @Test
    public void testAddProduct_outOfStock() {
        DomainException exception = assertThrows(DomainException.class, () -> cart.addProduct(availableProduct, 11));
        assertEquals("This product is out of stock.", exception.getMessage());
    }

    @Test
    public void testAddProduct_exceedMaxProductsToBuy() {
        DomainException exception = assertThrows(DomainException.class, () -> cart.addProduct(availableProduct, 6));
        assertEquals("You cannot buy more than 5 pieces.", exception.getMessage());
    }
}
