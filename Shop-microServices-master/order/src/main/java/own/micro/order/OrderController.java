package own.micro.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

//    @GetMapping("/{user-id}")
//    public ResponseEntity<ProductInCart> getItemsToPurchase(@PathVariable("user-id") Integer userId) {
//
//        return ResponseEntity.status(HttpStatus.OK).body(orderService.itemsToBuy(userId));
//    }

    @GetMapping("/{user-id}/purchase")
    public void purchaseItems(@PathVariable("user-id") Integer userId) {
        orderService.getCartForPurchase(userId);
    }

}
