public class Person {
    private String name;
    private int age;
    private String address;

    public Person(String name, int age, String address){
        setName(name);
        setAge(age);
    }

    public void setName(String name){
        if (name==null  || name.isBlank()){
            System.out.print("Имя не может быть пустым. Попробуйте снова");
        }
        this.name=name;

    }

    public void setAge(int age){
        if (age>=0 && age<=14){
            System.out.print("Не достигнут возраст заказа: " + age );
        }
        if (age<0 | age>=110){
            System.out.print("Неккоректный возраст:" +age);
        }
        this.age=age;
    }

    public void setAddress(String address){
        if (address==null || address.isBlank()){
            System.out.print("Адрес не может быть пустым. Попробуйте снова");
        }
        this.address=address;
    }

    /// @return
    public int getAge(){
        return age;
    }
    public String getName(){
        return name;
    }
    public String getAddress(){
        return address;
    }
}
