package projectpackage;
/**
 * Class used to create the preset pizza BBQChicken which is a subclass of pizza.
 * Constructor to initialize and create a BBQChicken pizza, an instance of Pizza.
 * has methods to return a user-friendly string and the appropriate price of the pizza.
 * @author Thomas Shea, James Artuso
 */

public class BBQChicken extends Pizza{

    /**
     * Constructor to initialize and create a preset pizza, BBQChicken.
     * @param crust, crust type of pizza, different if chicago or NY style.
     */
    public BBQChicken(Crust crust){
        super(new Topping[]{Topping.BBQCHICKEN, Topping.GREENPEPPER, Topping.PROVOLONE, Topping.CHEDDAR}, crust, Size.SMALL);
    }

    /**
     * Method to return the price of the pizza, different based on size.
     * @return double, returns the price of the pizza based on the chosen size.
     */
    @Override
    public double price() {
        switch(super.getSize()){
            case SMALL  -> {return 13.99;}
            case MEDIUM -> {return 15.99;}
            case LARGE  -> {return 17.99;}
        }
        return -1;
    }

    /**
     * Method used to detail the type of pizza, its toppings, size and crust.
     * String used in order and store order menus to show full detail of the pizza.
     * @return string, returns the created string giving full info about the pizza.
     */
    @Override
    public String toString(){
        return "BBQ Chicken: Size:" + super.getSize() + ", Crust: " + super.getCrust().toString() + ", Toppings: " + super.getToppingNames()
                +", Price: $" + price();
    }
}
