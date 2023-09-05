package own.micro.products;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class Mapper {

    @Autowired
    ModelMapper modelMapper;

    public ProductDTO convertToDTO(Product product) {
        return modelMapper.map(product, ProductDTO.class);
    }

    public Product convertToEntity(ProductDTO product) {
        return modelMapper.map(product, Product.class);
    }
}
