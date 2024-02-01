package com.computerscience.project5;

/**
 * Class for the build your own pizza preset. Has methods and constructors
 * to create an instance of a build your own pizza as well as get the price of the pizza
 * and create a user-friendly string.
 * @author Thomas Shea, James Artuso
 */
public class BuildYourOwn extends Pizza{

    public BuildYourOwn(Crust crust){
        super(new Topping[0], crust, Size.SMALL);
    }

    /**
     * Method to get the price of a Build Your Own pizza
     * @return double, price of pizza
     */
    @Override
    public double price() {
        double extraCharge = 1.59 * super.getNumberOfToppings();
        switch(super.getSize()){
            case SMALL: {return (8.99  + extraCharge);}
            case MEDIUM: {return (10.99 + extraCharge);}
            case LARGE: {return (12.99 + extraCharge);}
        }
        return -1;
    }

    /**
     * Method to return the title of the pizza
     * @return String, Title of pizza
     */
    @Override
    public String getTitle(){
        return "Build Your Own";
    }

    /**
     *  Method to get the String of  a pizza
     *  This inlcudes the size, crust, toppings, and price
     * @return String
     */
    @Override
    public String toString(){
        return "Build Your Own: Size: " + super.getSize() +", Crust: " + super.getCrust().toString() + ", Toppings: " + super.getToppingNames()
                +", Price: $" + price();
    }
}
