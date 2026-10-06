public class Courier extends Person{
    private String courierId;


    public Courier(String courierId, String name, int age, String address) {
        super(name, age, address);
        if (getAge() <=18){
            System.out.println("Курьер должен достичь совершенолетнего возраста,текущий возраст: "  + getAge() + " не подходит");
        }
       this.courierId=courierId;
    }

    public String getCourierId() { return courierId; }

    @Override
    public String toString(){
        return "Курьер №: " + getCourierId() + " (" +getName() + ") ";
    }


}
