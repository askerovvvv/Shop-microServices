package own.micro.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    @Query(value = "SELECT * FROM cart WHERE user_id=:userId", nativeQuery = true)
    List<Cart> findCartByUserId(Long userId);
}
