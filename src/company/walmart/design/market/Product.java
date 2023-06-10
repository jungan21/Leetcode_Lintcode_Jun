package company.walmart.design.market;

public class Product {

    private ProductType type;

    private String name;

    private Integer count;

    private Integer price;

    public Product(ProductType type, String name, Integer count, Integer price) {
        this.type = type;
        this.name = name;
        this.count = count;
        this.price = price;
    }

    public ProductType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Integer getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "type=" + type +
                ", name='" + name + '\'' +
                ", count=" + count +
                ", price=" + price +
                '}';
    }
}