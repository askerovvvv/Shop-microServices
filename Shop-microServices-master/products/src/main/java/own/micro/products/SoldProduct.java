package own.micro.products;

public class SoldProduct {
    private Integer quantity;
    private Integer productId;

    public SoldProduct() {
    }

    public SoldProduct(Integer quantity, Integer productId) {
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
        return "SoldProducts{" +
                "quantity=" + quantity +
                ", productId=" + productId +
                '}';
    }
}
