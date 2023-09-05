package own.micro.cart.client;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import own.micro.cart.Product;
import own.micro.cart.PurchaseProductsFromCartDTO;
import own.micro.cart.client.CartProductClient;
import own.micro.cart.client.CartSecurityClient;

import java.util.List;

@Component
@Slf4j
public class MicroserviceClient {

    @Autowired
    private CartProductClient cartProductClient;

    @Autowired
    private CartSecurityClient cartSecurityClient;

    public void purchaseProduct(List<PurchaseProductsFromCartDTO> list) {
        log.info("Обращение к микросервису продуктов от микросервиса корзин");
        cartProductClient.purchaseProduct(list);
    }

    public Product findProductById(Integer productId) {
        log.info("Обращение к микросервису продуктов от микросервиса корзин");
        return cartProductClient.findProductById(productId);
    }

    public void checkIfUserExists(Long id) {
        log.info("Обращение к микросервису пользователей от микросервиса корзин");
        cartSecurityClient.checkIfUserExists(id);
    }

}
