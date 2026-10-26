import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class OrderService {
    private final List<Product> menu = new ArrayList<>();



    private final List<Order> orders = new ArrayList<>();

    private final List<Courier> couriers = new ArrayList<>();

    public OrderService() {
        initMenu();
        initCouriers();
    }

    private void initMenu() {
        menu.add(new Product("Яблоко", 100, 1000));
        menu.add(new Product("Груша", 200, 1000));
        menu.add(new Product("Бананы", 120, 1000));
        menu.add(new Product("Арбуз", 19, 1000));


    }
    private void initCouriers() {
        couriers.add(new Courier("K-01", "Петр", 25, "ул. Мира, 1"));
        couriers.add(new Courier("K-02", "Иван", 30, "ул. Мира, 2"));
        couriers.add(new Courier("K-03", "Сергей", 22, "ул. Мира, 3"));
    }

    private void showMenu() {
        for (int i = 0; i < menu.size(); i++) {
            System.out.println(1 + i + "." + menu.get(i));
        }
        System.out.println("Введите 0 для того чтобы закончить выбор");

    }

    private int readChoice() {
        System.out.print("Выберите позию: ");
        int choise = scanner.nextInt();
        return choise;

    }

    private int readQuantity() {
        System.out.print("Выберите количество: ");
        int quantity = scanner.nextInt();
        return quantity;

    }

    private OrderItem makeItem(Product p, int qty) {

        return new OrderItem(p.getName(), qty, p.getPrice(), p.getWeight() * qty);


    }

    public List<OrderItem> chooseItems() {
        List<OrderItem> chosen = new ArrayList<>();


        while (true) {
            showMenu();
            int choice = readChoice();
            if (choice == 0) break;
            if (choice > menu.size() || choice < 1) {
                System.out.println("Нет такой позиции");
                continue;
            }
            Product p = menu.get(choice - 1);
            int qty = readQuantity();
            if (qty <= 0) {
                System.out.println("Количество должно быть > 0");
                continue;
            }
            OrderItem item = makeItem(p, qty);
            chosen.add(item);
            System.out.println("Добавлено: " + item);


        }
        return chosen;


    }

    public Courier chooseCourier(){
        for (Courier c : couriers){
            System.out.println(c.getCourierId() + " " + c.getName());
        }
        System.out.println("Выберите курьера: ");
        int n = scanner.nextInt();
        scanner.nextLine();

        if (n < 1 || n > couriers.size()) {
            System.out.println("Нет такого курьера");
            return null;
        }
        return couriers.get(n-1);

    }

    public void showResult(List<OrderItem> chosen) {
        double sum = 0;
        for (OrderItem item : chosen) {
            System.out.println(item);
            sum += item.getTotalPrice();

        }
        System.out.println("Итоговая сумма: " + sum + " руб");

    }

    private Person readClient() {
        System.out.print("Введите имя: ");
        String name = scanner.nextLine();
        System.out.print("Введите возраст: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Введите адресс: ");
        String address = scanner.nextLine();

        return new Person(name, age, address);
    }

    public String readDeliveryAddress(Person client) {
        System.out.print("Для того, чтобы оставить текущий адрес доставки ( " + client.getAddress() + " ) нажмите 1, хотите поменять нажмите 2, хотите выйти нажмите 3: ");
        int tmp = scanner.nextInt();
        scanner.nextLine();
        while (true) {
            if (tmp == 1) {
                return client.getAddress();
            }
            if (tmp == 2) {
                System.out.print("Введите адрес доставки: ");
                String address = scanner.nextLine();
                return address;
            }
            if (tmp == 3) break;
            else {
                System.out.println("Ошибка ввода");
                System.out.print("Введите снова: ");
                tmp = scanner.nextInt();
                scanner.nextLine();
                continue;
            }
        }
        return "";
    }
    private Order selectOrder(){
        if (orders.isEmpty()){
            System.out.println("Заказов нет!");
            return null;
        }
        for (int i =0; i<orders.size(); i++){
            System.out.println("Заказ № " + orders.get(i).getNumber() + " для " + orders.get(i).getClient().getName());

        }
        System.out.println("Выберите номер заказа: ");
        int n = scanner.nextInt();
        scanner.nextLine();
        if(n<1 || n>orders.size()){
            System.out.println("Некоректный номенр заказа");
            return null;
        }
        return orders.get(n-1);



    }
    private DeliveryType chooseDelivery(List<OrderItem> items) {
        double gram = 0;
        for (OrderItem item : items) {
            gram += item.getWeight();

        }
        double weightKG = gram / 1000;
        List<DeliveryType> canH = new ArrayList<>();
        for (DeliveryType t : DeliveryType.values()) {
            if (t.canHandle(weightKG)) {
                canH.add(t);
            }
        }
        while (true) {
            System.out.println("Выбери спосок доставки: ");
            for (int i = 0; i < canH.size(); i++) {
                System.out.println((i + 1) + "." + canH.get(i).getName());
            }
            System.out.println("Введите 0, чтобы выйти");
            int choice = scanner.nextInt();
            if (choice == 0) return null;
            if (choice < 0 || choice > canH.size()) {
                System.out.println("Неизвестный выбор курьера, попробуйте снова");
                continue;
            } else return canH.get(choice - 1);

        }
    }

    private void showMainMenu() {
        System.out.println("\n=== Меню ===");
        System.out.println("1. Создать заказ");
        System.out.println("2. Показать список заказов");
        System.out.println("3. Выбрать способ доставки для заказа");
        System.out.println("4. Назначить курьера");
        System.out.println("5. Изменить статус заказа");
        System.out.println("0. Выход");
        System.out.print("> ");
    }

    private void createOrder() {
        Person client = readClient();
        String address = readDeliveryAddress(client);
        List<OrderItem> chosen = chooseItems();
        DeliveryType type = chooseDelivery(chosen);

        Order order = new Order(client, address, type);
        for (OrderItem item : chosen) {
            order.addItem(item);
        }

        orders.add(order);
        System.out.println(order);
    }

    private void showOrders() {
        if (orders.isEmpty()) {
            System.out.println("Заказов пока нет");
            return;
        }
        for (Order order : orders) {
            System.out.println(order);
            System.out.println("-------------------");
        }
    }

    private void changeDelivery() {
        Order order = selectOrder();
        if (order==null) return;
        System.out.println("Текущий способ доставки: " + order.getDeliveryType().getName());
        DeliveryType newType = chooseDelivery(order.getItems());
        order.setDeliveryType(newType);
        double price = newType.calculateCost(order.getItems());
        System.out.println("Установлена доставка:" + newType.getName());
        System.out.println("Цена: " + price);
    }

    private void assignCourier() {
        Order order = selectOrder();
        if (order == null) return;

        if (order.getDeliveryType() == DeliveryType.PICKUP) {
            System.out.println("Для самовывоза курьер не нужен.");
            return;
        }

        Courier courier = chooseCourier();
        order.assignCourier(courier);
        System.out.println("Курьер успешно назначен!");
    }

    private void changeStatus() {
        Order order = selectOrder();
        if (order == null) return;
        System.out.println("Текущий статус заказа: " + order.getStatus());
        OrderStatus[] allStatuses = OrderStatus.values();
        System.out.println("Выберите новый статус:");
        for (int i = 0; i < allStatuses.length; i++) {
            System.out.println((i + 1) + ". " + allStatuses[i]);
        }
        System.out.print("> ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        if (choice < 1 || choice > allStatuses.length) {
            System.out.println("Неверный выбор");
            return;
        }
        OrderStatus newStatus = allStatuses[choice - 1];
        order.setStatus(newStatus);
        System.out.println("\nСтатус успешно изменен!");
        System.out.println(order);
    }

    public void start() {
        while (true) {
            showMainMenu();

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) break;

            switch (choice) {
                case 1:
                    createOrder();
                    break;
                case 2:
                    showOrders();
                    break;
                case 3:
                    changeDelivery();
                    break;
                case 4:
                    assignCourier();
                    break;
                case 5:
                    changeStatus();
                    break;
                default:
                    System.out.println("Нет такого пункта");
            }
        }
    }
}














