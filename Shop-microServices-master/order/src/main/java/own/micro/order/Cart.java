package own.micro.order;

import java.util.Objects;

public class Cart {
    private Integer quantity;
    private Integer productId;

    public Cart() {
    }

    public Cart(Integer quantity, Integer productId) {
        this.quantity = quantity;
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "quantity=" + quantity +
                ", productId=" + productId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return Objects.equals(quantity, cart.quantity) && Objects.equals(productId, cart.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quantity, productId);
    }
}
