package com.microservice.security2;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "security-service", url = "${application.config.carts-url}")
public interface SecurityClient {
    @DeleteMapping("/{user-id}/delete-carts")
    void deleteUserCarts(@PathVariable("user-id") Long id);
}
