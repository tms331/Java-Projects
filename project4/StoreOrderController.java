package projectpackage;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
/**
 * Controller for store order, executes commands based upon user selection.
 * Contains methods to add orders to listview, set pizzeria, store orders, cancel orders,
 * export orders, view order, show price and set total.
 * @author Thomas Shea, James Artuso
 */

public class StoreOrderController {

    private StoreOrder storeOrderObj = new StoreOrder();
    @FXML
    private ListView<Order> storeOrderList;
    @FXML
    private TextField totalText;
    @FXML
    private TextArea orderView;
    private PizzaManagerController myPizzeria;

    /**
     * Method to add orders to the list view.
     * @param obj, order to be added.
     * @return boolean, true if successfully added.
     */
    public boolean add(Object obj) {
        if(obj instanceof Order){
            setTotalText();
            return true;
        }
        return false;
    }

    /**
     * Method to set corresponding pizzeria.
     * @param pizzeria, name of pizzeria that is being set.
     */
    public void setPizzeria(PizzaManagerController pizzeria){
        myPizzeria = pizzeria;
    }

    /**
     * Method to set store order. Will clear and then add all order to the listview.
     * @param storeOrder, store order that it is being set to.
     */
    public void setStoreOrder(StoreOrder storeOrder){
        storeOrderObj = storeOrder;
        storeOrderList.getItems().removeAll(storeOrderList.getItems());
        storeOrderList.getItems().addAll(storeOrderObj.getOrders());
        setTotalText();
    }

    /**
     * Method to cancel the current order. Will remove the current order
     * from order array and corresponding list views.
     */
    @FXML
    protected void cancelOrderButton(){
        Order order = storeOrderList.getSelectionModel().getSelectedItem();
        storeOrderList.getItems().remove(order);
        storeOrderObj.remove(order);
        myPizzeria.setStoreOrder(new StoreOrder(storeOrderObj));
        setTotalText();
    }

    /**
     * Method to set the total text.
     * Sets the total based upon the order.
     */
    private void setTotalText(){
        totalText.setText(String.format("Store Total: $%.2f", price()));
    }

    /**
     * Method to export the store orders to a file.
     * Will create file if non-existent, or delete if it exists.
     * @throws FileNotFoundException, throws file not found exceptions
     */
    @FXML
    protected void exportOrderButton() throws FileNotFoundException {
        File file = new File("storeOrder.txt");
        if(file.exists()){
            file.delete();
        }
        file = new File("storeOrder.txt");
        PrintWriter pw = new PrintWriter(file);
        for(Order order : storeOrderList.getItems()){
            pw.println(order.getFileString());
        }
        pw.println(String.format("Store Total: $%.2f", price()));
        pw.close();
    }

    /**
     * Method to show the price of corresponding store order.
     * @return double, the price of the order.
     */
    private double price(){
        double price = 0;
        for(Order order : storeOrderList.getItems()){
            price += order.price();
        }
        return price;
    }

    /**
     * Method to show subtotal, tax and total price of the order.
     * As well as the items of the selected order.
     */
    @FXML
    protected void viewOrderButton(){
        orderView.clear();
        Order order = storeOrderList.getSelectionModel().getSelectedItem();
        if(order != null) {
            orderView.appendText(order.toString() + "\n");
            orderView.appendText(order.listPizzas() + "\n");
            orderView.appendText(String.format("Subtotal: $%.2f\n", order.price()));
            orderView.appendText(String.format("Tax: $%.2f\n", order.price() * .07));
            orderView.appendText(String.format("Total: $%.2f", order.price() * 1.07));
        }
    }

}
