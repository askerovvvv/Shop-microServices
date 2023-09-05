package own.micro.cart;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import own.micro.cart.client.MicroserviceClient;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private Mapper mapper;

    @Autowired
    private MicroserviceClient microserviceClient;

    @Override
    public List<CartResponseDTO> getCartList(Long userId) {

        microserviceClient.checkIfUserExists(userId);
        List<Cart> carts = cartRepository.findCartByUserId(userId);
        List<CartResponseDTO> cartResponseDTOList = new ArrayList<>();

        for (Cart cart : carts) {
            var product = microserviceClient.findProductById(cart.getProductId());
            CartResponseDTO cartResponseDTO = new CartResponseDTO();
            cartResponseDTO.setQualityInBasket(cart.getQuantity());
            cartResponseDTO.setProduct(new Product(
                    product.getName(),
                    product.getPrice(),
                    product.getDescription(),
                    product.getQuantity()
            ));

            cartResponseDTOList.add(cartResponseDTO);
        }

        return cartResponseDTOList;
    }

    @Override
    public List<PurchaseProductsFromCartDTO> getCartsByUserId(Long userId) {
        List<Cart> carts = cartRepository.findCartByUserId(userId);
        List<PurchaseProductsFromCartDTO> list = new ArrayList<>();

        for (Cart cart: carts) {
            PurchaseProductsFromCartDTO cfpr = new PurchaseProductsFromCartDTO(cart.getQuantity(), cart.getProductId());
            list.add(cfpr);
        }
        return list;
    }

//    @Override
//    public CartForPurchaseResponseDTO getCartForPurchase(Long userId) {
//        microserviceClient.checkIfUserExists(userId); // TODO: если удален user удалить все его корзины
//        List<Cart> carts = cartRepository.findCartByUserId(userId);
//        List<String> items = new ArrayList<>();
//        List<PurchaseProductsFromCartDTO> list = new ArrayList<>();
//        long totalPrice = 0;
//
//        for (Cart cart : carts) {
//            var product = microserviceClient.findProductById(cart.getProductId());
//
//            if (product.getQuantity() < cart.getQuantity()) {
//                log.error("Количество продуктов недоступна");
//                throw new QualityNotAvailableException("В магазине нет такого количества вещей " + cart.getQuantity());
//            }
//
//            PurchaseProductsFromCartDTO pp = new PurchaseProductsFromCartDTO(
//                    product.getQuantity(),
//                    cart.getProductId()
//            );
//            list.add(pp);
//
//            items.add(product.getName());
//            totalPrice += countSumOfItems(cart.getQuantity(), product.getPrice());
//        }
//
//        microserviceClient.purchaseProduct(list);
//        cartRepository.deleteAll(carts);
//
//        return new CartForPurchaseResponseDTO(totalPrice, items);
//    }

    @Override
    public CartRequestDTO addProductToCart(CartRequestDTO requestData) {
        microserviceClient.checkIfUserExists(requestData.getUserId());
        var product = microserviceClient.findProductById(requestData.getProductId());
        int availableQuantity = product.getQuantity();

        if (requestData.getQuality() > availableQuantity) {
            log.error("При добавлении продукта в корзину --> количество продуктов недоступна");
            throw new QualityNotAvailableException("Доступное количество ->  " + availableQuantity);
        }

        Cart cart = mapper.convertToEntity(requestData);
        cartRepository.save(cart);

        return requestData;
    }

    @Override
    public void deleteCart(Long id) {
        cartRepository.findById(id)
                .orElseThrow(() -> new CartNotFoundException("Такой корзины нету"));
        cartRepository.deleteById(id);
    }

    @Override
    public void deleteUserCarts(Long id) {
        List<Cart> cartsToDelete = cartRepository.findCartByUserId(id);
        if (!cartsToDelete.isEmpty()) {
            cartRepository.deleteAll(cartsToDelete);
        }
    }
}
