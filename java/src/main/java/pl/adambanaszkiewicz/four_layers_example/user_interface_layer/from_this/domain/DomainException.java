package pl.adambanaszkiewicz.four_layers_example.user_interface_layer.from_this.domain;

public class DomainException extends Exception {
    public DomainException(String errorMessage) {
        super(errorMessage);
    }
}
