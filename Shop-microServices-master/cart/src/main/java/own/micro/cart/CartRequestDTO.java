package own.micro.cart;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
public class CartRequestDTO {
    private Integer quality;
    private Integer productId;
    private Long userId;
}

