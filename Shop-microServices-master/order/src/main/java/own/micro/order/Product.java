package own.micro.order;

import java.util.Objects;

public class Product {
    private Integer quantity;
    private Integer price;

    public Product() {
    }

    public Product(Integer quantity, Integer price) {
        this.quantity = quantity;
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "quantity=" + quantity +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(quantity, product.quantity) && Objects.equals(price, product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quantity, price);
    }
}
