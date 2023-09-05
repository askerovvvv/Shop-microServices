package own.micro.cart;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CartRepositoryTest {

    @Autowired
    CartRepository cartRepository;

    @Test
    void testFindCartByUserId() {

        Cart cart1 = new Cart();
        cart1.setUserId(1L);

        Cart cart2 = new Cart();
        cart2.setUserId(1L);

        Cart cart3 = new Cart();
        cart3.setUserId(1L);

        cartRepository.saveAll(List.of(
                cart1,
                cart2,
                cart3
        ));

        boolean exists = cartRepository.findCartByUserId(1L).isEmpty();

        assertFalse(exists);
    }
}