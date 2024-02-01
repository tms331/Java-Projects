package projectpackage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.IOException;
/**
 * Pizza Manager Controller class, the class that creates the different views, tracks orders,
 * sets images and loads fxml files to appropriate stages.
 * @author Thomas Shea, James Artuso
 */

public class PizzaManagerController {
    @FXML
    private ImageView NYPizzaImage;
    @FXML
    private ImageView chicagoImage;
    @FXML
    private ImageView storeImage;
    @FXML
    private ImageView currentImage;
    private Order currentOrder = new Order();
    private StoreOrder storeOrders = new StoreOrder();
    private CurrentOrderController myCurrentOrder;
    private StoreOrderController myStoreOrder;
    private int orderNumber = 0;

    /**
     * Initialize method to set different view images.
     */
    @FXML
    public void initialize(){
        try {
            Image image = new Image(PizzaManagerMain.class.getResource("NY.jpg").openStream());
            NYPizzaImage.setImage(image);
            image = new Image(PizzaManagerMain.class.getResource("chicago.jpg").openStream());
            chicagoImage.setImage(image);
            image = new Image(PizzaManagerMain.class.getResource("store.jpg").openStream());
            storeImage.setImage(image);
            image = new Image(PizzaManagerMain.class.getResource("shoppingCart.jpg").openStream());
            currentImage.setImage(image);
        }

        catch(Exception e){
        }
    }

    /**
     * Method to create the chicago style stage and load the associated fxml file.
     * @throws IOException, throws I/O exceptions
     */
    @FXML
    protected void chicagoStyleButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PizzaManagerMain.class.getResource("ChicagoStyleView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 750, 550);
        Stage stage = new Stage();
        stage.setTitle("Chicago Style Pizza");
        stage.setScene(scene);
        stage.show();
        ChicagoPizzaController controller = (ChicagoPizzaController) fxmlLoader.getController();
        controller.setPizzeria(this);
    }

    /**
     * Method to create the NY style stage and load the associated fxml file.
     * @throws IOException, throws I/O exceptions
     */
    @FXML
    protected void nyStyleButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PizzaManagerMain.class.getResource("NyStyleView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 750,550);
        Stage stage = new Stage();
        stage.setTitle("NY Style Pizza");
        stage.setScene(scene);
        stage.show();
        NyPizzaController controller = (NyPizzaController) fxmlLoader.getController();
        controller.setPizzeria(this);
    }

    /**
     * Method to create the current order stage and load the associated fxml file.
     * @throws IOException, throws I/O exceptions
     */
    @FXML
    protected void currentOrderButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PizzaManagerMain.class.getResource("CurrentOrderView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 550, 650);
        Stage stage = new Stage();
        stage.setTitle("Current Order");
        stage.setScene(scene);
        stage.show();
        myCurrentOrder = (CurrentOrderController) fxmlLoader.getController();
        myCurrentOrder.setPizzeria(this);
        myCurrentOrder.setOrder(new Order(currentOrder));
    }

    /**
     * Method to create the store order stage and load the associated fxml file.
     * @throws IOException, throws I/O exceptions
     */
    @FXML
    protected void storeOrderButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PizzaManagerMain.class.getResource("StoreOrderView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 550, 650);
        Stage stage = new Stage();
        stage.setTitle("Store Order");
        stage.setScene(scene);
        stage.show();
        myStoreOrder =  (StoreOrderController) fxmlLoader.getController();
        myStoreOrder.setPizzeria(this);
        myStoreOrder.setStoreOrder(storeOrders);
    }

    /**
     * Method to set store order.
     * @param storeOrder, the store order being set to.
     */
    public void setStoreOrder(StoreOrder storeOrder){
        storeOrders = storeOrder;
    }

    /**
     * Method to set the current order.
     * @param order, current order that is being changed.
     */
    public void setCurrentOrder(Order order){
        currentOrder = order;
    }

    /**
     * Method to add to the current order.
     * @param pizza, the pizza being added.
     */
    public void addToCurrentOrder(Pizza pizza){
        currentOrder.add(pizza);
        if(myCurrentOrder != null) {
            myCurrentOrder.add(pizza);
        }
    }

    /**
     * Method to add an order to the store order.
     * @param order, the order being added.
     */
    public void addtoStoreOrder(Order order){
        storeOrders.add(order);
        if(myStoreOrder != null){
            myStoreOrder.add(order);
        }
    }


    /**
     * method to get current order number.
     * @return orderNumber, returns order number.
     */
    public int getOrderNumber(){
        orderNumber += 1;
        return (orderNumber - 1);
    }
}