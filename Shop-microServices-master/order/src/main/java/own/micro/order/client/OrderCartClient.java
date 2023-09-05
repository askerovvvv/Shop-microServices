package own.micro.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import own.micro.order.Cart;

import java.util.List;


@FeignClient(name = "orderCart-service", url = "${application.config.carts-url}")
public interface OrderCartClient {

//    @GetMapping("/{user-id}/purchase")
//    ProductInCart findCartByUserId(@PathVariable("user-id") Integer userId);

    @GetMapping("/{user-id}/carts")
    List<Cart> findCartsByUserId(@PathVariable("user-id") Integer userId);

    @DeleteMapping("/{user-id}/delete-carts")
    void deleteCartsByUserId(@PathVariable("user-id") Integer userId);

}
