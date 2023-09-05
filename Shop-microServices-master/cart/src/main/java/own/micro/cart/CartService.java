package own.micro.cart;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartService {
    List<CartResponseDTO> getCartList(Long userId);
    List<PurchaseProductsFromCartDTO> getCartsByUserId(Long userId);
//    CartForPurchaseResponseDTO getCartForPurchase(Long userId);
    CartRequestDTO addProductToCart(CartRequestDTO basketRequestDTO);
    void deleteCart(Long id); // TODO: add json response
    void deleteUserCarts(Long id); // TODO: add json response
}
