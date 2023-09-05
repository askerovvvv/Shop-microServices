package own.micro.cart;

import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    @Autowired
    ModelMapper modelMapper;

    @PostConstruct
    public void configureMapper() {
        modelMapper.createTypeMap(CartRequestDTO.class, Cart.class)
                .addMapping(CartRequestDTO::getProductId, Cart::setProductId)
                .addMapping(CartRequestDTO::getUserId, Cart::setUserId)
                .addMapping(CartRequestDTO::getQuality, Cart::setQuantity);
    }

    public Cart convertToEntity(CartRequestDTO product) {
        return modelMapper.map(product, Cart.class);
    }
}
