import java.util.ArrayList;
import java.util.List;

public class OrderOfPeople {
    private static int counter = 0;
    private final int number;
    private final Person client;
    private final String deliveryAddress;
    private final List<OrderItem> items = new ArrayList<>();
    private final DeliveryMethod deliveryMethod;


    public OrderOfPeople(Person client, String deliveryAddress, DeliveryMethod deliveryMethod){
        if (client==null){
            throw new IllegalArgumentException("клиент null");
        }
        if (deliveryAddress==null){
            throw new IllegalArgumentException("адресс доставки null");
        }
        this.number = ++counter;
        this.client=client;
        this.deliveryAddress=deliveryAddress;
        this.deliveryMethod=deliveryMethod;

    }




    public int getNumber()                    { return number; }
    public Person getClient()                 { return client; }
    public String getDeliveryAddress()        { return deliveryAddress; }
    public DeliveryMethod getDeliveryMethod() { return deliveryMethod; }

    public void addItem(OrderItem item){
        if (item==null){
            throw new IllegalArgumentException("item is null");
        }
        items.add(item);
    }

    public void addProduct(Product p, int qty){
        OrderItem item = new OrderItem(p.getName(), qty, p.getPrice(), p.getWeight() * qty);
        addItem(item);
    }

    public double getItemsTotal(){
        double sum=0;
        for (OrderItem item : items){
            sum+=item.getTotalPrice();
        }
        return sum;
    }

    public double getTotalWeight(){
        double weightKG=0;
        for (OrderItem item : items){
            weightKG+=item.getWeight();
        }
        return weightKG;
    }

    public double getDeliveryCost(){
        return deliveryMethod.calculateCost(items);
    }
    public double getTotalCost(){
        return getItemsTotal() + getDeliveryCost();
    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Заказ №").append(number).append("\n");
        sb.append("Клиент: ").append(client.getName()).append("\n");
        sb.append("Адрес: ").append(deliveryAddress).append("\n");
        sb.append("Способ доставки: ").append(deliveryMethod.getName()).append("\n");
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
