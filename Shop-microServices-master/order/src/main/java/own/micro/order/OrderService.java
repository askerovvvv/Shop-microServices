package own.micro.order;

public interface OrderService {
//    ProductInCart itemsToBuy(Integer userId);

    CartForPurchaseResponseDTO getCartForPurchase(Integer userId);
}
