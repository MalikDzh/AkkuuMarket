import java.util.Map;

public class ProductUtils {
    public static void updateProductPrice(Map<String, Product> products, String productName, double newPrice) {
        Product product = products.get(productName);
        if (product != null) {
            product.setPrice(newPrice);
            System.out.println("Цена на " + productName + " обновлена до " + newPrice + " сом");
        } else {
            System.out.println("Товар '" + productName + "' не найден.");
        }
    }
}
