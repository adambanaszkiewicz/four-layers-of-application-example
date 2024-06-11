package pl.adambanaszkiewicz.four_layers_example.domain_layer.from_this;

import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.*;

@Entity
public class Cart {
    @Id
    private Long id;
    private String userId;
    @ElementCollection
    private Set<Product> products = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public Product getProduct(String productId) {
        return this.products.stream()
            .filter(product -> product.getId().equals(productId))
            .findFirst()
            .orElse(null);
    }
}
