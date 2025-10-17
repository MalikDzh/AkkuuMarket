import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        Map<String, User> users = new HashMap<>();
        Map<String, Product> products = new HashMap<>();
        Map<Integer, Order> orders = new HashMap<>();

        User aizada = new User("Aizada", Role.CUSTOMER);
        User temirlan = new User("Temirlan", Role.CUSTOMER);
        User admin = new User("Admin", Role.CUSTOMER);
        users.put(aizada.getUsername(), aizada);
        users.put(temirlan.getUsername(), temirlan);
        users.put(admin.getUsername(), admin);

        Product apple = new Product("Apple", 50);
        Product orange = new Product("Orange", 100);
        Product laptop = new Product("Laptop", 45000);
        Product mouse = new Product("Mouse", 800);
        products.put(apple.getName(), apple);
        products.put(orange.getName(), orange);
        products.put(laptop.getName(), laptop);
        products.put(mouse.getName(), mouse);

        Order order1 = new Order(1, aizada, PaymentType.CASH);
        order1.addItem(apple, 14);
        order1.addItem(orange, 5);
        order1.setStatus(OrderStatus.PROCESSING);
        orders.put(order1.getId(), order1);

        Order order2 = new Order(2, temirlan, PaymentType.CARD);
        order2.addItem(laptop, 2);
        order2.addItem(mouse, 1);
        order2.setStatus(OrderStatus.DELIVERED);
        orders.put(order2.getId(), order2);

        ReportUtils.printOrderReport(orders);
        ReportUtils.printOrdersByStatus(orders);
        ReportUtils.printMostExpensiveOrder(orders);
        ReportUtils.printOrderCountByUser(orders);
//        printOrderReport(orders);

        ReportUtils.findOrdersByStatus(orders, OrderStatus.PROCESSING);
        ProductUtils.updateProductPrice(products, "Laptop", 42000);

        double revenue = ReportUtils.getTotalRevenue(orders);
        System.out.println("--- Общая выручка магазина: " + revenue + " сом");

        System.out.println("--- Клиенты со статусом DELIVERED:");
        List<String> deliveredCustomers = StreamUtils.getCustomersWithDeliveredOrders(orders);
        deliveredCustomers.forEach(System.out::println);

        System.out.println("--- Кол-во заказов со статусом PROCESSING:");
        System.out.println(StreamUtils.countProcessingOrders(orders));

        System.out.println("--- Товары дороже 5000:");
        StreamUtils.getExpensiveProducts(products).forEach(System.out::println);

        System.out.println("--- Самый дорогой товар:");
        StreamUtils.getMostExpensiveProduct(products)
                .ifPresent(System.out::println);

        System.out.println("--- Сортировка клиентов по сумме заказов:");
        List<String> sortedCustomerNames = StreamUtils.getSortedCustomerNamesByOrderTotal(orders);
        sortedCustomerNames.forEach(System.out::println);

    }
}
