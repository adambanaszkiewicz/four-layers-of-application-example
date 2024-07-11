package pl.adambanaszkiewicz.four_layers_example.application_layer.to_this.domain;

public class DomainException extends Exception {
    public DomainException(String errorMessage) {
        super(errorMessage);
    }
}
