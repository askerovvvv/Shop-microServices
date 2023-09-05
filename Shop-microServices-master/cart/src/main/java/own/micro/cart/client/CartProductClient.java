package own.micro.cart.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import own.micro.cart.MyFeignClientConfiguration;
import own.micro.cart.Product;
import own.micro.cart.PurchaseProductsFromCartDTO;

import java.util.List;


@FeignClient(name = "cartProduct-service", url = "${application.config.products-url}", configuration = MyFeignClientConfiguration.class)
public interface CartProductClient {

    @PutMapping("/purchase-product")
    void purchaseProduct(@RequestBody List<PurchaseProductsFromCartDTO> list);

    @GetMapping("/products{user-id}")
    Product findProductsByUserId(@RequestParam("user-id") Integer userId);

    @GetMapping("/{product-id}")
    Product findProductById(@PathVariable("product-id") Integer productId);
}