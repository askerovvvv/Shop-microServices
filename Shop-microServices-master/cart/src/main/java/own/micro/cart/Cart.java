package own.micro.cart;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;

import java.util.List;

@Entity
@Audited
public class Cart {

    // TODo: validation must be unique

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantity;
    private Integer productId;
    private Long userId;

    public Cart() {
    }

    public Cart(Integer quantity, Integer productId, Long userId) {
        this.quantity = quantity;
        this.productId = productId;
        this.userId = userId;
    }

    public Long getId() {
        return id;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", productId=" + productId +
                ", userId=" + userId +
                '}';
    }
}
