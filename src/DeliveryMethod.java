import java.util.List;
public enum DeliveryMethod {
    PICKUP,
    BIKE,
    CAR;

    public String getName(){
        switch(this){
            case PICKUP: return "Самовызов";
            case BIKE: return "Вело курьер";
            case CAR: return "Машина";


            default:
                throw new IllegalStateException("Unexpected value: " + this);
        }

    }

    public double calculateCost(List<OrderItem> items){
        switch(this) {
            case PICKUP:
                return 0;
            case BIKE:

                double weightKG = 0;
                for (OrderItem item : items) {
                    weightKG += item.getWeight();
                }
                weightKG = weightKG / 1000;
                if (weightKG > 15) throw new IllegalArgumentException("Вело не возит больше 15 кг");

                return 150 + 15 * weightKG;
            case CAR:
                double weight1KG = 0;
                for (OrderItem item : items) {
                    weight1KG += item.getWeight();
                }
                weight1KG = weight1KG / 1000;
                return 300 + 25 * weight1KG;
            default:
                throw new IllegalStateException("Unexpected value: " + this);



        }


    }


}

