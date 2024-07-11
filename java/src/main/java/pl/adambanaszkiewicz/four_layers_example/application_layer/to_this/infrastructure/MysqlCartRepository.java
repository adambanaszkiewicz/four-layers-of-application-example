package pl.adambanaszkiewicz.four_layers_example.application_layer.to_this;

import org.springframework.stereotype.Repository;
import pl.adambanaszkiewicz.four_layers_example.application_layer.to_this.domain.Cart;
import pl.adambanaszkiewicz.four_layers_example.application_layer.to_this.domain.CartRepositoryInterface;

@Repository("ApplicationLayerToThisCartRepository")
public class MysqlCartRepository implements CartRepositoryInterface {

    @Override
    public Cart get(String userId) {
        // Todo
        return null;
    }

    @Override
    public void save(Cart cart) {
        // Todo
    }
}
