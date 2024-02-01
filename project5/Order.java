package com.computerscience.project5;
import java.util.ArrayList;

/**
 * Class to create and manage pizza orders. Methods to add and remove pizzas from the order array,
 * get pizza price, get a pizza in the array, order number and make a more user-friendly string.
 * @author Thomas Shea, James Artuso
 */

public class Order implements Customizable, Titleable {

    private ArrayList<Pizza> pizzas = new ArrayList<Pizza>();
    private int orderNumber;

    /**
     * Constructor used for object initialization in other classes.
     */
    public Order() {
    }

    /**
     * Method to get order number
     * @return int order number
     */
    public int getOrderNumber() {
        return orderNumber;
    }

    @Override
    public String getTitle(){
        return ("Order #" + getOrderNumber());
    }

    /**
     * Method to add pizzas to the order array.
     * @param obj, the pizza being added.
     * @return boolean, true if added successfully.
     */
    @Override
    public boolean add(Object obj) {
        if(obj instanceof Pizza){
            return pizzas.add((Pizza) obj);
        }
        return false;
    }

    /**
     * Method to remove pizzas to the order array.
     * @param obj, the pizza being removed.
     * @return boolean, true if removed successfully.
     */
    @Override
    public boolean remove(Object obj) {
        if(obj instanceof Pizza){
            return pizzas.remove((Pizza) obj);
        }
        return false;
    }


    /**
     * Method to get the price of the order. This price
     * Is equal to the sum of all pizza prices in the order.
     * @return double, price of the order
     */
    public double price(){
        double runningTotal = 0.0;
        int index = 0;

        while(index < pizzas.size()){
            runningTotal += pizzas.get(index).price();
            index += 1;
        }
        return runningTotal;
    }

    /**
     * Method to set the order number of an order.
     * @param orderNumber, the order number to be set.
     */
    public void setOrderNumber(int orderNumber){
        this.orderNumber = orderNumber;
    }

    /**
     * Method to return the pizzas array, the pizzas in the current order.
     * @return observablelist, pizza observable array list.
     */
    public ArrayList<Pizza> getPizzas(){
        return pizzas;
    }

    /**
     * Method to make a more user-friendly string.
     * @return string, order number in form of string.
     */
    @Override
    public String toString(){
        String returnString = getTitle() + "\n";
        returnString = returnString + listPizzas() + "\n";
        returnString = returnString + String.format("Subtotal: $%.2f\n", price());
        returnString = returnString + String.format("Tax: $%.2f\n", price() * 0.07);
        returnString = returnString + String.format("Total: $%.2f\n", price()*1.07);
        return returnString;
    }

    /**
     * Method to return a string containing the list of all pizzas.
     * @return string, the string containing all pizzas.
     */
    public String listPizzas(){
        String returnString = "";
        for(Pizza p: pizzas) {
            returnString = returnString + p.toString() + "\n";
        }
        return returnString;
    }

}
