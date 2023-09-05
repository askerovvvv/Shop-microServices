package own.micro.order;

import java.util.List;
import java.util.Objects;

public class CartForPurchaseResponseDTO {

    private Long totalPrice;
    private List<String> items;

    public CartForPurchaseResponseDTO() {
    }

    public CartForPurchaseResponseDTO(Long totalPrice, List<String> items) {
        this.totalPrice = totalPrice;
        this.items = items;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "CartForPurchaseResponseDTO{" +
                "totalPrice=" + totalPrice +
                ", items=" + items +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartForPurchaseResponseDTO that = (CartForPurchaseResponseDTO) o;
        return Objects.equals(totalPrice, that.totalPrice) && Objects.equals(items, that.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalPrice, items);
    }
}
