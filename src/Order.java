import java.util.ArrayList;
import java.util.List;


public class Order {
    private static int counter = 0;
    private final int number;
    private final Person client;
    private final String deliveryAddress;
    private final List<OrderItem> items = new ArrayList<>();
    private DeliveryType deliveryType;
    private Courier courier;
    private OrderStatus status;


    public void setDeliveryType(DeliveryType method) {
        if (method == null)
            throw new IllegalArgumentException("deliveryMethod is null");
        this.deliveryType = method;
    }


    public Order(Person client, String deliveryAddress, DeliveryType deliveryType) {
        if (client == null) {
            throw new IllegalArgumentException("клиент null");
        }
        if (deliveryAddress == null) {
            throw new IllegalArgumentException("адресс доставки null");
        }
        if (deliveryType == null)
            throw new IllegalArgumentException("DeiveryType is null");
        this.number = ++counter;
        this.client = client;
        this.deliveryAddress = deliveryAddress;
        this.deliveryType = deliveryType;
        this.status = OrderStatus.CREATED;



    }
    public void assignCourier(Courier c){
        if (deliveryType == DeliveryType.PICKUP) {
            throw new IllegalStateException("Для самовывоза курьер не нужен");
        }
        if (c == null) {
            throw new IllegalArgumentException("Курьер null");
        }
        this.courier = c;
        this.status = OrderStatus.COURIER_ASSIGNED;

    }

    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) {
        if (status == null){
            throw new IllegalArgumentException("Статус не может быть null");
        }
        this.status = status;
    }


    public int getNumber() {
        return number;
    }

    public Person getClient() {
        return client;
    }
    public Courier getCourier() { return courier; }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public DeliveryType getDeliveryType() {
        return deliveryType;
    }
    public List<OrderItem> getItems() {
        return items;
    }


    public void addItem(OrderItem item) {
        if (item == null) {
            throw new IllegalArgumentException("item is null");
        }
        items.add(item);
    }

    public void addProduct(Product p, int qty) {
        if (p == null)
            throw new IllegalArgumentException("product is null");
        if (qty <= 0)
            throw new IllegalArgumentException("quantity must be > 0");
        OrderItem item = new OrderItem(p.getName(), qty, p.getPrice(), p.getWeight() * qty);
        addItem(item);
    }

    public double getItemsTotal() {
        double sum = 0;
        for (OrderItem item : items) {
            sum += item.getTotalPrice();
        }
        return sum;
    }

    public double getTotalWeight() {
        double sum = 0;
        for (OrderItem item : items) {
            sum += item.getWeight();
        }
        return sum;
    }

    public double getDeliveryCost() {
        return deliveryType.calculateCost(items);
    }

    public double getTotalCost() {
        return getItemsTotal() + getDeliveryCost();
    }



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Заказ №").append(number).append("\n");
        sb.append("Клиент: ").append(client.getName()).append("\n");
        sb.append("Адрес: ").append(deliveryAddress).append("\n");
        sb.append("Способ доставки: ").append(deliveryType.getName()).append("\n");
        sb.append("Статус: ").append(getStatus()).append("\n");
        sb.append("Курьер: ").append(getCourier()).append("\n");
        sb.append("Позиции:\n");
        for (OrderItem item : items) {
            sb.append("  ").append(item).append("\n");
        }
        sb.append(String.format("Сумма товаров: %.2f%n", getItemsTotal()));
        sb.append(String.format("Вес: %.0f г%n", getTotalWeight()));
        sb.append(String.format("Доставка: %.2f%n", getDeliveryCost()));
        sb.append(String.format("Итого: %.2f", getTotalCost()));
        return sb.toString();
    }
}

