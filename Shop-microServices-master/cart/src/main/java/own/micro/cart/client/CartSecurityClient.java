package own.micro.cart.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cartSecurity-service", url = "${application.config.security-url}")
public interface CartSecurityClient {

    @GetMapping("/get-user/{user-id}")
    void checkIfUserExists(@PathVariable("user-id") Long userId);

}
