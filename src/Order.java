import java.util.Map;
import java.util.LinkedHashMap;

public class Order {

    private int id;
    private User customer;
    private OrderStatus status;
    private Map<Product, Integer> items = new LinkedHashMap<>();
    private PaymentType paymentType;

    public Order(int id, User customer, PaymentType paymentType) {
        this.id = id;
        this.customer = customer;
        this.paymentType = paymentType;
        this.status = OrderStatus.NEW;
    }

    public int getId() {
        return id;
    }

    public User getCustomer() {
        return customer;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Map<Product, Integer> getItems() {
        return items;
    }

    public void addItem(Product product, int quantity) {
        items.put(product, items.getOrDefault(product, 0) + quantity);
    }

    public double getTotalAmount() {
        double sum = 0;
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            sum += entry.getKey().getPrice() * entry.getValue();
        }
        return sum;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }
}
