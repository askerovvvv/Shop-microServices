package own.micro.products;

public class ProductDTO {
    private String name;
    private Integer price;
    private String description;
    private Integer quantity;

    public ProductDTO() {
    }

    public ProductDTO(ProductDTOBuilder builder) {
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

    public static class ProductDTOBuilder {
        private String name;
        private Integer price;
        private String description;
        private Integer quantity;

        public ProductDTOBuilder() {
        }

        public ProductDTOBuilder(String name, Integer price, String description, Integer quantity) {
            this.name = name;
            this.price = price;
            this.description = description;
            this.quantity = quantity;
        }

        public ProductDTOBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ProductDTOBuilder price(Integer price) {
            this.price = price;
            return this;
        }

        public ProductDTOBuilder description(String description) {
            this.description = description;
            return this;
        }

        public ProductDTOBuilder quantity(Integer quantity) {
            this.quantity = quantity;
            return this;
        }

        public ProductDTO build() {
            return new ProductDTO(this);
        }
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", amount=" + quantity +
                '}';
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}

