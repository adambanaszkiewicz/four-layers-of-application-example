package pl.adambanaszkiewicz.four_layers_example.user_interface_layer.from_this;

import org.springframework.stereotype.Repository;
import pl.adambanaszkiewicz.four_layers_example.user_interface_layer.from_this.domain.Cart;
import pl.adambanaszkiewicz.four_layers_example.user_interface_layer.from_this.domain.CartRepositoryInterface;

@Repository("UserInterfaceLayerFromThisCartRepository")
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
