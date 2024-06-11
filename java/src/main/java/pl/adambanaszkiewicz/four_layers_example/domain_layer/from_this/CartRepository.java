package pl.adambanaszkiewicz.four_layers_example.domain_layer.from_this;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findOneByUserId(String userId);
}
