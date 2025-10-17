import java.util.*;

public class ReportUtils {

//    public static void printOrderReport(Map<Integer, Order> orders) {
//        System.out.println("--- Отчет по заказам ---");
//        for (Order order : orders.values()) {
//            User user = order.getCustomer();
//            System.out.println("Пользователь: " + user);
//            System.out.println(" Заказ:: " + order.getId() + " (" + order.getStatus() + ")");
//            for (Map.Entry<Product, Integer> item : order.getItems().entrySet()) {
//                System.out.println("   " + item.getKey().getName() + ": " + item.getValue() + " шт");
//            }
//            System.out.println("  Сумма: " + (int) order.getTotalAmount() + " сом");
//
//        }
//    }

    public static void printOrdersByStatus(Map<Integer, Order> orders) {
        Map<OrderStatus, List<Order>> grouped = new HashMap<>();
        for (Order order : orders.values()) {
            grouped.computeIfAbsent(order.getStatus(), k -> new ArrayList<>()).add(order);
        }

        System.out.println("--- Заказы по статусам ---");
        for (OrderStatus status : OrderStatus.values()) {
            System.out.println(status + ":");
            List<Order> list = grouped.getOrDefault(status, new ArrayList<>());
            for (Order o : list) {
                System.out.println("   Заказ:: " + o.getId() + " от " + o.getCustomer().getUsername());
            }
        }

        System.out.println();
    }

    public static void printMostExpensiveOrder(Map<Integer, Order> orders) {
        Order expensive = null;
        for (Order o : orders.values()) {
            if (expensive == null || o.getTotalAmount() > expensive.getTotalAmount()) {
                expensive = o;
            }
        }
        if (expensive != null) {
            System.out.println("--- Самый дорогой заказ ---");
            System.out.println("Заказ:: " + expensive.getId() + " на сумму " + (int) expensive.getTotalAmount() + " сом");
        }
    }

    public static void printOrderCountByUser(Map<Integer, Order> orders) {
        Map<String, Integer> userOrderCount = new HashMap<>();
        for (Order o : orders.values()) {
            String username = o.getCustomer().getUsername();
            userOrderCount.put(username, userOrderCount.getOrDefault(username, 0) + 1);
        }

        System.out.println("--- Количество заказов по пользователям ---");
        for (Map.Entry<String, Integer> entry : userOrderCount.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " заказ(ов)");
        }
    }

    public static void findOrdersByStatus(Map<Integer, Order> orders, OrderStatus status) {
        System.out.println("--- Поиск заказов по статусу: " + status + " ---");
        boolean found = false;
        for (Order order : orders.values()) {
            if (order.getStatus() == status) {
                System.out.println("Заказ #" + order.getId() + " от " + order.getCustomer().getUsername());
                found = true;
            }
        }
        if (!found) {
            System.out.println("Нет заказов со статусом " + status);
        }
    }

    public static double getTotalRevenue(Map<Integer, Order> orders) {
        double total = 0;
        for (Order order : orders.values()) {
            total += order.getTotalAmount();
        }
        return total;
    }
    public static void printOrderReport(Map<Integer, Order> orders) {
        System.out.println("--- Отчет по заказам ---");
        for (Order order : orders.values()) {
            User user = order.getCustomer();
            System.out.println("Пользователь: " + user);
            System.out.println(" Заказ:: " + order.getId() + " (" + order.getStatus() + ")");
            for (Map.Entry<Product, Integer> item : order.getItems().entrySet()) {
                System.out.println("   " + item.getKey().getName() + ": " + item.getValue() + " шт");
            }
            System.out.println("  Сумма: " + (int) order.getTotalAmount() + " сом");
            PaymentType payment = order.getPaymentType();
            System.out.println("  Оплата: " + (payment != null ? payment : "НЕ УКАЗАНО"));
        }
    }
}
