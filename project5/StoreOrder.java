package com.computerscience.project5;

import java.util.ArrayList;
/**
 * Class for store order view. Contains orders and method to add, remove and return placed orders.
 * @author Thomas Shea, James Artuso
 */

public class StoreOrder implements Customizable {

    private ArrayList<Order> orders = new ArrayList<Order>();

    /**
     * Default constructor. Used to create an empty StoreOrder
     */
    public StoreOrder(){
    }


    /**
     * Method to add orders to the order array.
     * @param obj, the order to be added.
     * @return boolean, true if add was successful.
     */
    @Override
    public boolean add(Object obj) {
        if(obj instanceof Order){
            return orders.add((Order) obj);
        }
        return false;
    }

    /**
     * Method to remove orders to the order array.
     * @param obj, the order to be removed.
     * @return boolean, true if remove was successful.
     */
    @Override
    public boolean remove(Object obj) {
        if(obj instanceof Order){
            return orders.remove((Order) obj);
        }
        return false;
    }

    /**
     * method to return the contents of the order array.
     * @return observable list, the array of orders.
     */
    public ArrayList<Order> getOrders(){
        return orders;
    }
}
