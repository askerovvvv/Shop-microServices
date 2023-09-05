package own.micro.products;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    List<ProductDTO> getAllProducts();

    ProductDTO createProduct(ProductDTO productDTO);

    void deleteProduct(Integer id); // TODO: add json response

    ProductDTO findProductById(Integer id);

    void decreaseProductQuantity(List<SoldProduct> soldProducts);

    ProductDTO updateProduct(ProductDTO productDTO, Integer productId);
}
