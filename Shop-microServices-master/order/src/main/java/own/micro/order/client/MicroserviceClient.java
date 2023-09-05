package own.micro.order.client;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import own.micro.order.Cart;
import own.micro.order.Product;

import java.util.List;

@Component
@Slf4j
public class MicroserviceClient {

    @Autowired
    private OrderProductClient orderProductClient;

    @Autowired
    private OrderCartClient orderCartClient;

    public void purchaseProduct(List<Cart> list) {
        log.info("Обращение к микросервису продуктов от микросервиса покупок");
        orderProductClient.purchaseProduct(list);
    }

    public Product findProductById(Integer productId) {
        log.info("Обращение к микросервису продуктов от микросервиса покупок");
        return orderProductClient.findProductById(productId);
    }

    public void deleteCartsByUserId(Integer userId) {
        log.info("Обращение к микросервису корзин от микросервиса покупок");
        orderCartClient.deleteCartsByUserId(userId);
    }

//    public ProductInCart findCartByUserId(Integer userId) {
//        return orderCartClient.findCartByUserId(userId);
//    }

    public List<Cart> findCartsByUserId(Integer userId) {
        return orderCartClient.findCartsByUserId(userId);
    }

}
