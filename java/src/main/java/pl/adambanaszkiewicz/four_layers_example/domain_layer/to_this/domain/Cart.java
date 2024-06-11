package pl.adambanaszkiewicz.four_layers_example.domain_layer.to_this.domain;

import java.util.ArrayList;
import java.util.List;
import pl.adambanaszkiewicz.four_layers_example.domain_layer.to_this.domain.DomainException;

public final class Cart {
    private String id;
    private List<LineItem> lineItems;

    public Cart() {
        this.lineItems = new ArrayList<>();
    }

    public void addProduct(ProductDTO productToAdd, int qty) throws DomainException {
        if (!productToAdd.isAvailable()) {
            throw new DomainException("This product is unavailable.");
        }

        LineItem productInCart = findLineItem(productToAdd.getId());

        int destinationQty;
        if (productInCart != null) {
            destinationQty = productInCart.getQty() + qty;
        } else {
            destinationQty = qty;
        }

        if (productToAdd.getStock() < destinationQty) {
            throw new DomainException("This product is out of stock.");
        }

        if (destinationQty > productToAdd.getMaxProductsToBuy()) {
            throw new DomainException(String.format("You cannot buy more than %d pieces.", productToAdd.getMaxProductsToBuy()));
        }

        if (productInCart != null) {
            productInCart.setQty(destinationQty);
        } else {
            lineItems.add(new LineItem(productToAdd.getId(), qty));
        }
    }

    private LineItem findLineItem(String productId) {
        for (LineItem lineItem : lineItems) {
            if (lineItem.getProductId().equals(productId)) {
                return lineItem;
            }
        }
        return null;
    }
}
