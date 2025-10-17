import java.util.Objects;

public class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name + " = " + price + " сом";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product p = (Product) o;
        return name.equals(p.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
