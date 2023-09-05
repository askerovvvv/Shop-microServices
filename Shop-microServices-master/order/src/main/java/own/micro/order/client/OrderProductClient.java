package own.micro.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import own.micro.order.Cart;
import own.micro.order.Product;

import java.util.List;

@FeignClient(name = "orderProduct-service", url = "${application.config.products-url}")
//        , configuration = MyFeignClientConfiguration.class)

public interface OrderProductClient {

    @PutMapping("/purchase-product")
    void purchaseProduct(@RequestBody List<Cart> list);

    @GetMapping("/products{user-id}")
    Product findProductsByUserId(@RequestParam("user-id") Integer userId);

    @GetMapping("/{product-id}")
    Product findProductById(@PathVariable("product-id") Integer productId);
}