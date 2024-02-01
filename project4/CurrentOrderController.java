package projectpackage;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
/**
 * Class is a controller for the current order view. A view where users can see the pizzas they've ordered.
 * Class has methods that will update the user view accordingly. Will add items to listview if added to current order,
 * will add order to store order if the order is placed, also has methods to clear the current order when placed or canceled.
 * @author Thomas Shea, James Artuso
 */

public class CurrentOrderController {

    private Order orderObj = new Order();
    private PizzaManagerController myPizzeria;
    @FXML
    private TextField subtotalText;
    @FXML
    private TextField taxText;
    @FXML
    private TextField totalText;
    @FXML
    private ListView<Pizza> orderList;

    /**
     * Method to set the current pizzeria being used.
     * @param pizzeria, the current pizzeria being set.
     */
    public void setPizzeria(PizzaManagerController pizzeria){
        myPizzeria = pizzeria;
    }

    /**
     * Method to add items to the orderlist listview and update price accordingly.
     * @param obj, object to be added to the orderlist view.
     * @return boolean, true if added successfully, false otherwise.
     */
    public boolean add(Object obj) {
        if(obj instanceof Pizza){
            orderList.getItems().add((Pizza) obj);
            orderObj.add((Pizza) obj);
            setPrices();
            return true;

        }
        return false;

    }

    /**
     * Method to set order, removes current items from orderlist and adds new ones.
     * @param order, order to be added to the orderlist listview.
     */
    public void setOrder(Order order){
        orderObj = order;
        orderList.getItems().removeAll(orderList.getItems());
        orderList.getItems().addAll(orderObj.getPizzas());
        setPrices();
    }

    /**
     * Method to remove pizza from the order and the associated listview.
     */
    @FXML
    protected void removePizzaButton(){
        Pizza pizza = orderList.getSelectionModel().getSelectedItem();
        orderList.getItems().remove(pizza);
        orderObj.remove(pizza);
        myPizzeria.setCurrentOrder(new Order(orderObj));
        setPrices();
    }

    /**
     * Method to place order, will move order to store order,
     * and remove pizzas from current order. Also sets order
     * number for the store order.
     */
    @FXML
    protected void onPlaceOrderButtonClick(){
        if (orderList.getItems().size() > 0) {
            orderObj.setOrderNumber(myPizzeria.getOrderNumber());
            myPizzeria.addtoStoreOrder(orderObj);
            onClearOrderButtonClick();
        }
    }

    /**
     * Method to clear the current order array and listview
     */
    @FXML
    protected void onClearOrderButtonClick(){
        orderList.getItems().clear();
        orderObj = new Order();
        myPizzeria.setCurrentOrder(new Order());
        setPrices();
    }

    /**
     * Method to clear price fields and set then set current price, tax and total.
     */
    private void setPrices(){
        subtotalText.clear();
        taxText.clear();
        totalText.clear();
        double subtotal = orderObj.price();
        subtotalText.appendText(String.format("$%.2f", subtotal));
        double tax = subtotal * 0.07;
        taxText.appendText(String.format("$%.2f", tax));
        double total = subtotal + tax;
        totalText.appendText(String.format("$%.2f", total));
    }

}
