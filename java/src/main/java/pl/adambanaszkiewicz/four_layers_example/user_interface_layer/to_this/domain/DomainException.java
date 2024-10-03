package pl.adambanaszkiewicz.four_layers_example.user_interface_layer.to_this.domain;

public class DomainException extends Exception {
    public DomainException(String errorMessage) {
        super(errorMessage);
    }
}
