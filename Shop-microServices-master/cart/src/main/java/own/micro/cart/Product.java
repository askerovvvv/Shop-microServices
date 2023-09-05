package own.micro.cart;

import java.util.Objects;

public class Product {

    private String name;
    private Integer price;
    private String description;
    private Integer quantity;
    // TODO: category

    public Product() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Product(String name, Integer price, String description, Integer quantity) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
    }

    public Product(ProductBuilder builder) {
        this.name = builder.name;
        this.price = builder.price;
        this.description = builder.description;
        this.quantity = builder.quantity;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public static class ProductBuilder {
        private String name;
        private Integer price;
        private String description;
        private Integer quantity;

        public ProductBuilder() {
        }

        public ProductBuilder(String name, Integer price, String description, Integer quantity) {
            this.name = name;
            this.price = price;
            this.description = description;
            this.quantity = quantity;
        }

        public ProductBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ProductBuilder price(Integer price) {
            this.price = price;
            return this;
        }

        public ProductBuilder description(String description) {
            this.description = description;
            return this;
        }

        public ProductBuilder quantity(Integer quantity) {
            this.quantity = quantity;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }

    @Override
    public String toString() {
        return "Product{" +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", amount=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name) && Objects.equals(price, product.price) && Objects.equals(description, product.description) && Objects.equals(quantity, product.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, description, quantity);
    }
}
