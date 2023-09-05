package own.micro.order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import own.micro.order.client.MicroserviceClient;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService{

    @Autowired
    private MicroserviceClient client;
//
//    @Override
//    public ProductInCart itemsToBuy(Integer userId) {
//        var order = client.findCartByUserId(userId);
//        return order;
//    }

    @Override
    public CartForPurchaseResponseDTO getCartForPurchase(Integer userId) {
        List<Cart> cartsFromCart = client.findCartsByUserId(userId);
        List<Cart> listOfDataForProduct = new ArrayList<>();

        long totalPrice = 0;

        for (Cart cart: cartsFromCart) {
            Product product = client.findProductById(cart.getProductId());
            if (product.getQuantity() < cart.getQuantity()) {
                log.error("Такое количество недоступна {} --> {}", cart.getQuantity(), product.getQuantity());
                throw new QualityNotAvailableException("В магазине нет такого количества вещей " + cart.getQuantity());
            }
            Cart dataForProduct = new Cart(
                    cart.getQuantity(),
                    cart.getProductId()
            );

            listOfDataForProduct.add(dataForProduct);
            totalPrice += countSumOfItems(cart.getQuantity(), product.getPrice());
        }

        client.purchaseProduct(listOfDataForProduct);
        client.deleteCartsByUserId(userId);

        return null;
    }

    public Long countSumOfItems(Integer quantity, Integer price) {
        long totalPrice = (long) price * quantity;
        return totalPrice;
    }
}
