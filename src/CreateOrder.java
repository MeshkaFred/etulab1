import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class CreateOrder {
    private final List<Product> menu = new ArrayList<>();

    private final Scanner scanner = new Scanner(System.in);

    public CreateOrder(){
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





}
