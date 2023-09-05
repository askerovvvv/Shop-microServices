package own.micro.products;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProducts());
    }

    @GetMapping("/{product-id}")
    public ResponseEntity<ProductDTO> getById(@PathVariable("product-id") Integer productId) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findProductById(productId));
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(productDTO));
    }

    @DeleteMapping("/{product-id}")
    public ResponseEntity<?> deleteProduct(@RequestParam Integer productId) {
        productService.deleteProduct(productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/purchase-product")
    public ResponseEntity<?> purchaseProduct(@RequestBody List<SoldProduct> soldProducts) {
        productService.decreaseProductQuantity(soldProducts);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/update-quantity-price/{product-id}")
    public ResponseEntity<ProductDTO> updateProduct(
            @RequestBody ProductDTO productDTO,
            @PathVariable("product-id") Integer productId
    ){
        return ResponseEntity.status(HttpStatus.OK).body(productService.updateProduct(productDTO, productId));
    }

}
