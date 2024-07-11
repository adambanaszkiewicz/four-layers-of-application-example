package pl.adambanaszkiewicz.four_layers_example.application_layer.from_this.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import pl.adambanaszkiewicz.four_layers_example.application_layer.from_this.domain.DomainException;

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

        LineItem lineItemInCart = findLineItem(productToAdd.getId());

        int destinationQty;
        if (lineItemInCart != null) {
            destinationQty = lineItemInCart.getQty() + qty;
        } else {
            destinationQty = qty;
        }

        if (productToAdd.getStock() < destinationQty) {
            throw new DomainException("This product is out of stock.");
        }

        if (destinationQty > productToAdd.getMaxProductsToBuy()) {
            throw new DomainException(String.format("You cannot buy more than %d pieces.", productToAdd.getMaxProductsToBuy()));
        }

        if (lineItemInCart != null) {
            lineItemInCart.setQty(destinationQty);
        } else {
            lineItems.add(new LineItem(productToAdd.getId(), qty));
        }
    }

    public List<Map<String, Object>> getItemsWithQuantity() {
        List<Map<String, Object>> itemsWithQuantity = new ArrayList<>();

        for (LineItem lineItem : lineItems) {
            Map<String, Object> itemMap = new HashMap<>();
            itemMap.put("productId", lineItem.getProductId());
            itemMap.put("quantity", lineItem.getQty());
            // Problem: Where we have product name?
            itemsWithQuantity.add(itemMap);
        }

        return itemsWithQuantity;
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
