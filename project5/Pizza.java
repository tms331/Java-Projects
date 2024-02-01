package com.computerscience.project5;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Pizza class containing related methods for managing pizzas and its data fields.
 * Methods to create pizza, set data fields, return data fields, add and remove.
 * @author Thomas Shea, James Artuso
 */

public abstract class Pizza implements Customizable, Titleable {
    private ArrayList<Topping> toppings;
    private Crust crust;
    private Size size;
    public abstract double price();

    /**
     * Constructor to make the pizza type.
     * @param toppings, array of toppings associated with the pizza.
     * @param crust, the crust of the pizza.
     * @param size, the size of the pizza.
     */
    protected Pizza(Topping[] toppings, Crust crust, Size size){
        this.toppings = new ArrayList<Topping>(Arrays.asList(toppings));
        setCrust(crust);
        this.size = size;
    }

    /**
     * Method to set the toppings of a pizza.
     * @param toppings, the array the pizzas toppings will be set to.
     */
    protected void setToppings(ArrayList<Topping> toppings){
        this.toppings = toppings;
    }

    /**
     * Method to set crust of the pizza.
     * @param crust, the crust the pizza will now have.
     */
    protected void setCrust(Crust crust){
        this.crust = crust;
    }

    /**
     * Method to set size of the pizza.
     * @param size, the size the pizza will now have.
     */
    protected void setSize(Size size){
        this.size = size;
    }

    /**
     * Method to get the current size of a pizza.
     * @return size, the size of the pizza.
     */
    public Size getSize(){
        return size;
    }

    /**
     * Method to get the crust of the current pizza.
     * @return crust, the current pizza's crust.
     */
    public Crust getCrust(){
        return crust;
    }

    /**
     * Method to return the names of the toppings on the pizza.
     * @return string, the string containing the names of all toppings.
     */
    public String getToppingNames(){
        if(toppings.size() == 0){
            return "none";
        }
        String returnString = "";
        for(int i = 0; i < toppings.size(); i++){
            returnString = returnString + toppings.get(i).toString();
            if(i + 1 != toppings.size()){
                returnString = returnString + "; ";
            }
        }
        return returnString;
    }

    /**
     * Method to get the array list of toppings
     * @return arraylist, the array containing the toppings.
     */
    public ArrayList<Topping> getToppings(){
        return toppings;
    }

    /**
     * Method to return number of toppings.
     * @return int, the size of the toppings array.
     */
    public int getNumberOfToppings(){
        return toppings.size();
    }

    /**
     * Method to add toppings to the pizza.
     * @param obj, topping to be added.
     * @return boolean, true if the topping is added.
     */
    @Override
    public boolean add(Object obj){
        if(obj instanceof Topping){
            return toppings.add((Topping) obj);
        }
        return false;
    }

    /**
     * Method to remove topping from pizza.
     * @param obj, the topping to be removed.
     * @return boolean, true if the topping is removed.
     */
    @Override
    public boolean remove(Object obj){
        if(obj instanceof Topping){
            return toppings.remove((Topping) obj);
        }
        return false;
    }

}
