package own.micro.cart;

import java.util.Objects;

public class PurchaseProductsFromCartDTO {
    private Integer quantity;
    private Integer productId;

    public PurchaseProductsFromCartDTO() {
    }

    public PurchaseProductsFromCartDTO(Integer quantity, Integer productId) {
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
        return "PurchaseProductsFromCartDTO{" +
                "quantity=" + quantity +
                ", productId=" + productId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseProductsFromCartDTO that = (PurchaseProductsFromCartDTO) o;
        return Objects.equals(quantity, that.quantity) && Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quantity, productId);
    }
}
