public class OrderItem {
    private final String productName;
    private final int quantity;
    private final double price;
    private final double weight;

    public OrderItem(String productName, int quantity, double price, double weight) {

        if (productName == null || productName.isBlank()) {
            throw new IllegalArgumentException("Название товара не может быть пустым");
        }

        if (quantity <= 0) {
            throw new IllegalArgumentException("Количество должно быть больше 0");
        }
        if (price <= 0) {
            throw new IllegalArgumentException("Цена должна быть больше 0");
        }
        if (weight <= 0) {
            throw new IllegalArgumentException("Вес должен быть больше 0");
        }

        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.weight = weight;
    }

    public double getPrice() {
        return price;
    }

    public double getTotalPrice() {
        return quantity * price;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return productName + " x " + quantity + " = " + getTotalPrice();
    }
}