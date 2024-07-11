package pl.adambanaszkiewicz.four_layers_example.application_layer.to_this.domain;

public final class ProductDTO {
    private final String id;
    private final boolean available;
    private final int stock;
    private final int maxProductsToBuy;

    public ProductDTO(String id, boolean available, int stock, int maxProductsToBuy) {
        this.id = id;
        this.available = available;
        this.stock = stock;
        this.maxProductsToBuy = maxProductsToBuy;
    }

    public String getId() {
        return id;
    }

    public boolean isAvailable() {
        return available;
    }

    public int getStock() {
        return stock;
    }

    public int getMaxProductsToBuy() {
        return maxProductsToBuy;
    }
}
