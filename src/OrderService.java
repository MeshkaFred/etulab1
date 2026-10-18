import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class OrderService {
    private final List<Product> menu = new ArrayList<>();

    private final Scanner scanner = new Scanner(System.in);

    public OrderService(){
        initMenu();
    }

    private void initMenu(){
        menu.add(new Product("Яблоко", 100, 1000));
        menu.add(new Product("Груша", 200, 1000));
        menu.add(new Product("Бананы", 120, 1000));
        menu.add(new Product("Арбуз", 19, 1000));


    }
    private void showMenu(){
        for (int i=0; i<menu.size(); i++){
            System.out.println(1+i + "." + menu.get(i));
        }
        System.out.println("Введите 0 для того чтобы закончить выбор");

    }
    private int readChoice(){
        System.out.print("Выберите позию: ");
        int choise = scanner.nextInt();
        return choise;

    }
    private int readQuantity(){
        System.out.print("Выберите количество: ");
        int quantity = scanner.nextInt();
        return quantity;

    }

    private OrderItem makeItem(Product p, int qty){

        return new OrderItem(p.getName(),qty,p.getPrice(),p.getWeight()*qty);


    }
    public List<OrderItem> chooseItems(){
        List<OrderItem> chosen = new ArrayList<>();


        while(true){
            showMenu();
            int choice = readChoice();
            if (choice == 0) break;
            if (choice > menu.size() || choice <1){
                System.out.println("Нет такой позиции");
                continue;
            }
            Product p = menu.get(choice - 1);
            int qty = readQuantity();
            if (qty<=0){
                System.out.println("Количество должно быть > 0");
                continue;
            }
            OrderItem item = makeItem(p, qty);
            chosen.add(item);
            System.out.println("Добавлено: " + item);


        }
        return chosen;


    }
    public void showResult(List<OrderItem> chosen){
        double sum=0;
        for (OrderItem item : chosen){
            System.out.println(item);
            sum+=item.getTotalPrice();

        }
        System.out.println("Итоговая сумма: " + sum + " руб");

    }
    private Person readClient(){
        System.out.print("Введите имя: ");
        String name = scanner.nextLine();
        System.out.print("Введите возраст: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Введите адресс: ");
        String address = scanner.nextLine();

        return new Person(name,age,address);
    }

    public String readDeliveryAddress(Person client){
        System.out.print("Для того, чтобы оставить текущий адрес доставки ( " + client.getAddress() + " ) нажмите 1, хотите поменять нажмите 2, хотите выйти нажмите 3: ");
        int tmp = scanner.nextInt();
        scanner.nextLine();
        while (true){
            if (tmp==1){
                return client.getAddress();
            }
            if (tmp==2){
                System.out.print("Введите адрес доставки: ");
                String address = scanner.nextLine();
                return address;
            }
            if (tmp==3) break;
            else{
                System.out.println("Ошибка ввода");
                System.out.print("Введите снова: ");
                tmp = scanner.nextInt();
                scanner.nextLine();
                continue;
            }
        }
        return "";
    }
    private DeliveryType chooseDelivery(List<OrderItem> items){
        double gram=0;
        for (OrderItem item : items){
            gram+=item.getWeight();

        }
        double weightKG=gram / 1000;
        List<DeliveryType> canH = new ArrayList<>();
        for (DeliveryType t : DeliveryType.values()) {
            if (t.canHandle(weightKG)) {
                canH.add(t);
            }
        }
        while (true){
            System.out.println("Выбери спосок доставки: ");
            for (int i = 0; i <canH.size(); i++){
                System.out.println((i+1) + "." + canH.get(i).getName());
            }
            System.out.println("Введите 0, чтобы выйти");
            int choice = scanner.nextInt();
            if (choice==0) return null;
            if (choice<0 || choice > canH.size()){
                System.out.println("Неизвестный выбор курьера, попробуйте снова");
                continue;
            }
            else return canH.get(choice-1);

        }
    }
    public void start() {
        // 1. клиент
        Person client = readClient();
        // 2. адрес доставки (передаём клиента — если сделан выбор)
        String address = readDeliveryAddress(client);
        // 3. товары
        List<OrderItem> chosen = chooseItems();
        // 4. способ доставки — передаём список, чтобы знать вес
        DeliveryType type = chooseDelivery(chosen);
        // 5. собрать Order
        Order order = new Order(client, address, type);
        for (OrderItem item : chosen) {
            order.addItem(item);
        }

        System.out.println(order);
    }









}
