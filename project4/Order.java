package projectpackage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 * Class to create and manage pizza orders. Methods to add and remove pizzas from the order array,
 * get pizza price, get a pizza in the array, order number and make a more user-friendly string.
 * @author Thomas Shea, James Artuso
 */

public class Order implements Customizable{

    private ObservableList<Pizza> pizzas = FXCollections.observableArrayList();
    private int orderNumber;

    /**
     * Constructor to make order datatype which has a pizza array and order number.
     * @param orderNumber, the ordernumber of the order.
     * @param pizzas, the array of the pizzas in the order.
     */
    public Order(int orderNumber, ObservableList<Pizza> pizzas){
        this.orderNumber = orderNumber;
        this.pizzas = pizzas;
    }

    /**
     * Constructor used for object initialization in other classes.
     */
    // used for object initialization
    public Order(){}

    /**
     * Copy constructor
     * @param order order to be copied
     */
    public Order(Order order){
        this.orderNumber = order.getOrderNumber();
        for(int i = 0; i < order.getPizzas().size(); i++){
            pizzas.add(order.getPizzas().get(i));
        }
    }

    /**
     * Method to get order number
     * @return int order number
     */
    public int getOrderNumber(){
        return orderNumber;
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
    public ObservableList<Pizza> getPizzas(){
        return pizzas;
    }

    /**
     * Method to make a more user-friendly string.
     * @return string, order number in form of string.
     */
    @Override
    public String toString(){
        return "Order #" + orderNumber;
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

    /**
     * Method to return a string that has the order number, the pizzas, subtotal, tax and total.
     * @return string, the string containing all relevant order information.
     */
    public String getFileString(){
        String returnString = "";
        returnString = returnString + "Order #" + orderNumber;
        for(Pizza p: pizzas){
            returnString = returnString + "\n\t" + p.toString();
        }
        returnString = returnString + String.format("\n\tsubtotal: $%.2f", price());
        returnString = returnString + String.format("\n\ttax: $%.2f", price()*0.07);
        returnString = returnString + String.format("\n\ttotal: $%.2f", price()*1.07);
        return returnString;
    }
}
