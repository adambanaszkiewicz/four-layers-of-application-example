package pl.adambanaszkiewicz.four_layers_example.user_interface_layer.from_this.domain;

public final class LineItem {
    private final String productId;
    private int qty;

    public LineItem(String productId, int qty) {
        this.productId = productId;
        this.qty = qty;
    }

    public String getProductId() {
        return productId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
