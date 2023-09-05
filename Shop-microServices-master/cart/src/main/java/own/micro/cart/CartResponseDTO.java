package own.micro.cart;

import java.util.Objects;

public class CartResponseDTO {

    private Integer qualityInBasket;
    private Product product;

    public CartResponseDTO() {
    }

    public CartResponseDTO(CartResponseDTOBuilder builder) {
        this.qualityInBasket = builder.qualityInBasket;
        this.product = builder.product;
    }

    public Integer getQualityInBasket() {
        return qualityInBasket;
    }

    public void setQualityInBasket(Integer qualityInBasket) {
        this.qualityInBasket = qualityInBasket;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public static class CartResponseDTOBuilder {
        private Integer qualityInBasket;
        private Product product;

        public CartResponseDTOBuilder() {
        }

        public CartResponseDTOBuilder(Integer qualityInBasket, Product product) {
            this.qualityInBasket = qualityInBasket;
            this.product = product;
        }

        public CartResponseDTOBuilder qualityInBasket(Integer qualityInBasket) {
            this.qualityInBasket = qualityInBasket;
            return this;
        }

        public CartResponseDTOBuilder product(Product product) {
            this.product = product;
            return this;
        }

        public CartResponseDTO build() {
            return new CartResponseDTO(this);
        }
    }

    @Override
    public String toString() {
        return "CartResponseDTO{" +
                "qualityInBasket=" + qualityInBasket +
                ", product=" + product +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartResponseDTO that = (CartResponseDTO) o;
        return Objects.equals(qualityInBasket, that.qualityInBasket) && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(qualityInBasket, product);
    }
}
