package pl.adambanaszkiewicz.four_layers_example.domain_layer.from_this;

import jakarta.persistence.*;

@Entity
public class Product {
    @Id
    private String id;
    private int qty;
    private double price;

    public Product(String id, int qty, double price) {
        this.id = id;
        this.qty = qty;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
