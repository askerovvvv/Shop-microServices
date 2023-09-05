package own.micro.cart;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import own.micro.cart.client.MicroserviceClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class CartServiceImplTest {

    @Mock
    private MicroserviceClient microserviceClient;
    @Mock
    private CartRepository cartRepository;
    @Mock
    private Mapper mapper;
    @InjectMocks
    private CartServiceImpl cartService;
    private Cart mockCart1;
    private Cart mockCart2;
    private Product mockProduct1;
    private Product mockProduct2;



    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        mockCart1 = new Cart();
        mockCart1.setUserId(1L);
        mockCart1.setProductId(1);
        mockCart1.setQuantity(4);

        mockCart2 = new Cart();
        mockCart2.setUserId(1L);
        mockCart2.setProductId(2);
        mockCart2.setQuantity(2);

        mockProduct1 = new Product();
        mockProduct1.setName("test 1");
        mockProduct1.setQuantity(5);
        mockProduct1.setPrice(100);

        mockProduct2 = new Product();
        mockProduct2.setName("test 2");
        mockProduct2.setQuantity(3);
        mockProduct2.setPrice(200);
    }

    @Test
    void testGetCartList() {
        CartResponseDTO responseData1 = new CartResponseDTO();
        responseData1.setQualityInBasket(4);
        responseData1.setProduct(mockProduct1);

        CartResponseDTO responseData2 = new CartResponseDTO();
        responseData2.setQualityInBasket(2);
        responseData2.setProduct(mockProduct2);

        List<CartResponseDTO> expectedResponse = new ArrayList<>();
        expectedResponse.add(responseData1);
        expectedResponse.add(responseData2);

        List<Cart> carts = new ArrayList<>();
        carts.add(mockCart1);
        carts.add(mockCart2);

        when(cartRepository.findCartByUserId(1L)).thenReturn(carts);
        when(microserviceClient.findProductById(1)).thenReturn(mockProduct1);
        when(microserviceClient.findProductById(2)).thenReturn(mockProduct2);

        List<CartResponseDTO> actualResponse = cartService.getCartList(1L);

        assertEquals(expectedResponse.size(), actualResponse.size());
        assertThat(expectedResponse).containsExactlyElementsOf(actualResponse);
    }

    @Test
    void testAddProductToCart_Success() {
        CartRequestDTO mockRequestData = new CartRequestDTO();
        mockRequestData.setQuality(2);
        mockRequestData.setProductId(1);
        mockRequestData.setUserId(1L);

        when(microserviceClient.findProductById(mockRequestData.getProductId())).thenReturn(mockProduct1);
        when(mapper.convertToEntity(mockRequestData)).thenReturn(mockCart1);

        CartRequestDTO actualResponse = cartService.addProductToCart(mockRequestData);

        System.out.println(actualResponse);

        verify(cartRepository, times(1)).save(mockCart1);
        assertEquals(mockRequestData, actualResponse);
    }

    @Test
    void testAddProductToCart_Failed() {
        CartRequestDTO mockRequestData = new CartRequestDTO();
        mockRequestData.setQuality(8);
        mockRequestData.setProductId(1);
        mockRequestData.setUserId(1L);

        when(microserviceClient.findProductById(mockRequestData.getProductId())).thenReturn(mockProduct1);
        when(mapper.convertToEntity(mockRequestData)).thenReturn(mockCart1);

        verify(cartRepository, never()).save(mockCart1);
        assertThrows(QualityNotAvailableException.class, () -> cartService.addProductToCart(mockRequestData));
    }

    @Test
    void testDeleteCart_Succes() {
        when(cartRepository.findById(1L)).thenReturn(Optional.of(mockCart1));

        cartService.deleteCart(1L);
        verify(cartRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteCart_Failed() {
        when(cartRepository.findById(1L)).thenReturn(Optional.empty());

        verify(cartRepository, never()).deleteById(1L);
        assertThrows(CartNotFoundException.class, () -> cartService.deleteCart(1L));
    }

    @Test
    void testDeleteUserCarts() {
        List<Cart> carts = new ArrayList<>();
        carts.add(mockCart1);
        carts.add(mockCart2);

        when(cartRepository.findCartByUserId(1L)).thenReturn(carts);

        cartService.deleteUserCarts(1L);

        verify(cartRepository, times(1)).deleteAll(carts);
    }

    @Test
    void testGetCartsByUserId() {
        List<Cart> carts = new ArrayList<>();
        carts.add(mockCart1);
        carts.add(mockCart2);

        List<PurchaseProductsFromCartDTO> expectedData = new ArrayList<>();
        PurchaseProductsFromCartDTO mockPurchaseProductsFromCartDTO1 = new PurchaseProductsFromCartDTO(4, 1);
        PurchaseProductsFromCartDTO mockPurchaseProductsFromCartDTO2 = new PurchaseProductsFromCartDTO(2, 2);
        expectedData.add(mockPurchaseProductsFromCartDTO1);
        expectedData.add(mockPurchaseProductsFromCartDTO2);

        when(cartRepository.findCartByUserId(1L)).thenReturn(carts);

        cartService.getCartsByUserId(1L);

        assertEquals(expectedData, cartService.getCartsByUserId(1L));
    }
}
