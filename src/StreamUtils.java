import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class StreamUtils {

    public static List<String> getCustomersWithDeliveredOrders(Map<Integer, Order> orders) {
        return orders.values().stream()
                .filter(order -> order.getStatus() == OrderStatus.DELIVERED)
                .map(order -> order.getCustomer().getUsername())
                .distinct()
                .collect(Collectors.toList());
    }

    public static long countProcessingOrders(Map<Integer, Order> orders) {
        return orders.values().stream()
                .filter(order -> order.getStatus() == OrderStatus.PROCESSING)
                .count();
    }

    public static List<Product> getExpensiveProducts(Map<String, Product> products) {
        return products.values().stream()
                .filter(product -> product.getPrice() > 5000)
                .collect(Collectors.toList());
    }

    public static Optional<Product> getMostExpensiveProduct(Map<String, Product> products) {
        return products.values().stream()
                .max(Comparator.comparing(Product::getPrice));
    }

    public static List<String> getSortedCustomerNamesByOrderTotal(Map<Integer, Order> orders) {
        return orders.values().stream()
                .sorted(Comparator.comparing(Order::getTotalAmount))
                .map(order -> order.getCustomer().getUsername())
                .collect(Collectors.toList());
    }
}
