package own.micro.products;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private Mapper mapper;

    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(product -> mapper.convertToDTO(product))
                .toList();
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = mapper.convertToEntity(productDTO);
        productRepository.save(product);

        log.info("Продукт {} создан", product.getId());
        return productDTO;
    }

    @Override
    public void deleteProduct(Integer id) {
        productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Такого продукта не существует"));
        productRepository.deleteById(id);

        log.info("Продукт {} удален", id);
    }

    @Override
    public ProductDTO findProductById(Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Такого продукта не существует"));

        return mapper.convertToDTO(product);
    }

    @Override
    public void decreaseProductQuantity(List<SoldProduct> soldProducts) {
        soldProducts.stream()
                .map(soldProduct -> {
                    Product currentProduct = productRepository.findById(soldProduct.getProductId())
                            .orElseThrow(() -> new ProductNotFoundException("Нету"));
                    currentProduct.setQuantity(currentProduct.getQuantity() - soldProduct.getQuantity());
                    return productRepository.save(currentProduct);
                })
                .toList();

        log.info("Количество продуктов снижена");
    }

    @Override
    public ProductDTO updateProduct(ProductDTO productDTO, Integer productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Нету"));

        if (productDTO.getPrice() != null && productDTO.getQuantity() != null) {
            product.setQuantity(productDTO.getQuantity());
            product.setPrice(productDTO.getPrice());

            log.info(
                    "У продукта {} изменены цена {} --> {} и количество {} --> {}",
                    product.getName(),
                    product.getPrice(),
                    productDTO.getPrice(),
                    product.getQuantity(),
                    productDTO.getQuantity()
            );
        } else if (product.getPrice() != null && productDTO.getQuantity() == null) {
            product.setPrice(productDTO.getPrice());

            log.info(
                    "У продукта {} изменена цена {} --> {}",
                    product.getName(),
                    product.getPrice(),
                    productDTO.getPrice()
            );
        } else {
            product.setQuantity(productDTO.getQuantity());

            log.info(
                    "У продукта {} изменена количество {} --> {}",
                    product.getName(),
                    product.getQuantity(),
                    productDTO.getQuantity()
            );
        }

        ProductDTO responseData = mapper.convertToDTO(product);
        productRepository.save(product);

        return responseData;
    }
}
