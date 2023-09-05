package own.micro.products;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;
    @Mock
    private Mapper mapper;
    @InjectMocks
    private ProductServiceImpl productService;
    private Product mockProduct1;
    private Product mockProduct2;
    private ProductDTO mockProductDTO1;
    private ProductDTO mockProductDTO2;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        mockProduct1 = new Product.ProductBuilder()
                .name("Test product 1")
                .quantity(55)
                .price(5000)
                .build();

        mockProduct2 = new Product.ProductBuilder()
                .name("Test product 2")
                .quantity(30)
                .price(2000)
                .build();

        mockProductDTO1 = new ProductDTO.ProductDTOBuilder()
                .name("Test product 1")
                .quantity(55)
                .price(5000)
                .build();

        mockProductDTO2 = new ProductDTO.ProductDTOBuilder()
                .name("Test product 2")
                .quantity(30)
                .price(2000)
                .build();
    }

    @Test
    void testGetAllProducts() {


        List<Product> productList = List.of(
                mockProduct1,
                mockProduct2
        );
        List<ProductDTO> productDTOS = List.of(
                mockProductDTO1,
                mockProductDTO2
        );

        when(productRepository.findAll()).thenReturn(productList);
        when(mapper.convertToDTO(any(Product.class))).thenAnswer(
                invocationOnMock -> {
                    Product product = invocationOnMock.getArgument(0);
                    String name = product.getName();
                    return productDTOS.stream()
                            .filter(dto -> dto.getName().equals(name))
                            .findFirst()
                            .orElse(null);
                }
        );

        List<ProductDTO> result = productService.getAllProducts();

        assertEquals(productDTOS, result);
    }

    @Test
    void testFindById_Success() {
        when(productRepository.findById(1)).thenReturn(Optional.of(mockProduct1));

        productService.findProductById(1);

        assertEquals(mockProduct1.getName(), mockProductDTO1.getName());
    }

    @Test
    void testFindByIdNonExistingProduct() {
        when(productRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productService.findProductById(1));
    }

    @Test
    void testCreateProduct() {
        when(mapper.convertToEntity(mockProductDTO2)).thenReturn(mockProduct2);

        ProductDTO result = productService.createProduct(mockProductDTO2);

        verify(productRepository, times(1)).save(mockProduct2);
        assertEquals(mockProductDTO2, result);
        assertEquals(mockProduct2.getName(), result.getName());
    }

    @Test
    void testDeleteProduct() {
        when(productRepository.findById(1)).thenReturn(Optional.of(mockProduct1));

        productService.deleteProduct(1);

        verify(productRepository, times(1)).deleteById(1);
    }

    @Test
    void testDeleteNonExistingProduct() {
        when(productRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productService.deleteProduct(1));
    }

    @Test
    void testDecreaseProductQuantity() {
        SoldProduct mockSoldProduct1 = new SoldProduct(23, 1);
        SoldProduct mockSoldProduct2 = new SoldProduct(15, 2);

        List<SoldProduct> mockSoldProducts = new ArrayList<>();
        mockSoldProducts.add(mockSoldProduct1);
        mockSoldProducts.add(mockSoldProduct2);

        when(productRepository.findById(mockSoldProduct1.getProductId())).thenReturn(Optional.of(mockProduct1));
        when(productRepository.findById(mockSoldProduct2.getProductId())).thenReturn(Optional.of(mockProduct2));

        productService.decreaseProductQuantity(mockSoldProducts);

        verify(productRepository, times(2)).save(any(Product.class));
        assertEquals(mockProduct1.getQuantity(), 32);
        assertEquals(mockProduct2.getQuantity(), 15);
    }

    @Test
    void testUpdateQuantity() {
        ProductDTO testRequestData = new ProductDTO();
        testRequestData.setQuantity(33);

        mockProductDTO1.setQuantity(33);

        when(productRepository.findById(1)).thenReturn(Optional.of(mockProduct1));
        when(mapper.convertToDTO(mockProduct1)).thenReturn(mockProductDTO1);

        ProductDTO actualResponse = productService.updateProduct(testRequestData, 1);

//        assertEquals(mockProduct1.getQuantity(), actualResponse.getQuantity());
        assertEquals(33, actualResponse.getQuantity());
        assertEquals(mockProduct1.getPrice(), actualResponse.getPrice());
    }
}
