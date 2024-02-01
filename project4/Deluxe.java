package projectpackage;
/**
 * Class for deluxe pizza preset. Has methods and constructors to create an instance of deluxe pizza.
 * As well as get the price of the pizza and create a user-friendly string.
 * @author Thomas Shea, James Artuso
 */

public class Deluxe extends Pizza{

    /**
     * Constructor to create a deluxe pizza with appropriate crust and toppings.
     * @param crust, crust which is based upon the pizza style.
     */
    public Deluxe(Crust crust){
        super(new Topping[]{Topping.SAUSAGE, Topping.PEPPERONI, Topping.GREENPEPPER, Topping.ONION, Topping.MUSHROOM}, crust, Size.SMALL);
    }

    /**
     * Method to return price of the deluxe pizza based upon pizza size
     * @return double, returns the price of the pizza.
     */
    @Override
    public double price() {
        switch(super.getSize()){
            case SMALL  -> {return 14.99;}
            case MEDIUM -> {return 16.99;}
            case LARGE  -> {return 18.99;}
        }
        return -1;
    }

    /**
     * Method to return a more user-friendly string containing data about the pizza.
     * @return string, returns the string that has data about all the pizza fields.
     */
    @Override
    public String toString(){
        return "Deluxe: Size: " + super.getSize() +", Crust: " + super.getCrust().toString() + ", Toppings: " + super.getToppingNames()
                + ", Price: $" + price();
    }
}
