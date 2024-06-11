package pl.adambanaszkiewicz.four_layers_example.domain_layer.to_this.domain;

public class DomainException extends Exception {
    public DomainException(String errorMessage) {
        super(errorMessage);
    }
}
