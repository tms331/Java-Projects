package com.computerscience.project5;
/**
 * Class for meatzza pizza preset. Has methods and constructors to create an instance of a meat pizza.
 * As well as get the price of the pizza and create a user-friendly string.
 * @author Thomas Shea, James Artuso
 */

public class Meatzza extends Pizza{

    /**
     * Constructor to make the meat pizza, has preset toppings.
     * Crust is based upon pizza style, size will be set accordingly.
     * @param crust, the crust differs from pizza style.
     */
    public Meatzza(Crust crust){
        super(new Topping[]{Topping.SAUSAGE, Topping.PEPPERONI, Topping.BEEF, Topping.HAM}, crust, Size.SMALL);
    }

    /**
     * Method to return price of pizza based upon size.
     * @return double, returns pizza price.
     */
    @Override
    public double price() {
        switch(super.getSize()){
            case SMALL: {return 15.99;}
            case MEDIUM: {return 17.99;}
            case LARGE: {return 19.99;}
        }
        return -1;
    }

    /**
     * Method to return the title of the pizza
     * @return String, Title of pizza
     */
    @Override
    public String getTitle(){
        return "Meatzza";
    }

    /**
     * method to return a more user-friendly string.
     * @return string, the improved string.
     */
    @Override
    public String toString(){
        return "Meatzza: Size: " + super.getSize() +", Crust: " + super.getCrust().toString() + ", Toppings: " + super.getToppingNames()
                + ",Price: $" + price();
    }

}
