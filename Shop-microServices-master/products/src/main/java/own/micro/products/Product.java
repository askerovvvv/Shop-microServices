package own.micro.products;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.envers.Audited;

@Entity
@DynamicUpdate
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer price;
    private String description;

    @Audited
    private Integer quantity;


    // TODO: category

    public Product() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Product(ProductBuilder builder) {
        this.name = builder.name;
        this.price = builder.price;
        this.description = builder.description;
        this.quantity = builder.quantity;
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
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", amount=" + quantity +
                '}';
    }
}
