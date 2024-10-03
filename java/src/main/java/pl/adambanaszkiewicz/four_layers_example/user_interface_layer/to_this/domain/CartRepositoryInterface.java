package pl.adambanaszkiewicz.four_layers_example.user_interface_layer.to_this.domain;

public interface CartRepositoryInterface {
    Cart get(String userId);
    void save(Cart cart);
}
