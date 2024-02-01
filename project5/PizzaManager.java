package com.computerscience.project5;

import java.io.Serializable;


/**
 * This class holds all the data in one class. Other classes get their data from this class.
 * @author Thomas Shea, James Artuso
 */
public class PizzaManager implements Serializable {
    private Order currentOrder = new Order();
    private StoreOrder storeOrders = new StoreOrder();
    private int orderNumber = 0;

    /**
     * Method to add to the current order.
     * @param pizza, the pizza being added.
     */
    public void addToCurrentOrder(Pizza pizza){
        currentOrder.add(pizza);
    }

    /**
     * Method to add an order to the store order.
     * @param order, the order being added.
     */
    public void addtoStoreOrder(Order order){
        storeOrders.add(order);
    }

    /**
     * Getter method to get the current order
     * @return current order object
     */
    public Order getCurrentOrder(){
        return currentOrder;
    }

    /**
     * Getter method to get the store order
     * @return current store order object
     */
    public StoreOrder getCurrentStoreOrder(){return storeOrders;}
    /**
     * method to get current order number.
     * @return orderNumber, returns order number.
     */
    public int getOrderNumber(){
        orderNumber += 1;
        return (orderNumber);
    }

    /**
     * Setter method to set the current order value
     * @param newOrder Order to change current order to
     */
    public void setCurrentOrder(Order newOrder){
        currentOrder = newOrder;
    }
}
