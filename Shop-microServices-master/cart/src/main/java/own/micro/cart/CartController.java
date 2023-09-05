package own.micro.cart;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/{user-id}")
    public ResponseEntity<List<CartResponseDTO>> getCartList(@PathVariable("user-id") Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(cartService.getCartList(userId));
    }

    @GetMapping("/{user-id}/carts")
    public ResponseEntity<List<PurchaseProductsFromCartDTO>> getCartsByUserId(@PathVariable("user-id") Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(cartService.getCartsByUserId(userId));
    }

//    @GetMapping("/{user-id}/purchase")
//    public ResponseEntity<CartForPurchaseResponseDTO> getCartForPurchase(@PathVariable("user-id") Long userId) {
//        return ResponseEntity.status(HttpStatus.OK).body(cartService.getCartForPurchase(userId));
//    }

    @PostMapping
    public ResponseEntity<CartRequestDTO> addCart(@RequestBody CartRequestDTO basketRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cartService.addProductToCart(basketRequestDTO));
    }

    @DeleteMapping("/{basket-id}")
    public ResponseEntity<?> deleteCart(@RequestParam Long basketId) {
        cartService.deleteCart(basketId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{user-id}/delete-carts")
    public ResponseEntity<?> deleteUserCarts(@PathVariable("user-id") Long userId) {
        cartService.deleteUserCarts(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
