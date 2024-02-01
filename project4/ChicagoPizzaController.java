package projectpackage;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
/**
 * Class is a controller for the chicago pizza view contains associated methods.
 * From this view the user can order different types of chicago pizzas
 * or even create their own. Will update user interface to reflect changes.
 * @author Thomas Shea, James Artuso
 */

public class ChicagoPizzaController implements Initializable {

    @FXML
    private ListView<Topping> toppingsList;
    @FXML
    private ListView<Topping> selectedList;
    @FXML
    private Button addButton;
    @FXML
    private Button removeButton;
    @FXML
    private TextArea outputText;
    @FXML
    private ToggleGroup pizzaTypes;
    @FXML
    private ComboBox<String> pizzaSizes;
    @FXML
    private ImageView pizzaView;
    private PizzaManagerController myPizzeria;

    /**
     * Initialize method to add toppings to list view,
     * add sizes to combo box, set selection models and display
     * current pizza order for user view.
     * @param location, universal resource locator location.
     * @param resource, resource bundle being used, if applicable.
     */
    public void initialize(URL location, ResourceBundle resource){
        toppingsList.getItems().addAll(Topping.SAUSAGE, Topping.CHEDDAR,
                Topping.PEPPERONI, Topping.PROVOLONE, Topping.HAM,
                Topping.BBQCHICKEN, Topping.ONION, Topping.MUSHROOM, Topping.GREENPEPPER,
                Topping.BACON, Topping.OLIVES, Topping.SPINACH);
        toppingsList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        selectedList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        pizzaSizes.getItems().add("Small");
        pizzaSizes.getItems().add("Medium");
        pizzaSizes.getItems().add("Large");
        pizzaSizes.getSelectionModel().select(1);
        pizzaSizes.setEditable(false);
        displayPizza();
    }

    /**
     * Method to set appropriate pizzeria, NY or chicago pizzeria.
     * @param pizzeria, the current pizzeria which is being set.
     */
    public void setPizzeria(PizzaManagerController pizzeria){
        myPizzeria = pizzeria;
    }

    /**
     * Method to add toppings to the selected toppings listview.
     * Takes toppings from topping list and puts them into selected listview.
     * Will not allow user to add more than 7 toppings.
     * Finally, will update the user-view by calling upon display pizza method.
     */
    @FXML
    protected void addToppings(){
        ObservableList<Topping> listOfItems = toppingsList.getSelectionModel().getSelectedItems();
        int numSelected = listOfItems.size();
        if(listOfItems.size() + selectedList.getItems().size() > 7){
            return;
        }

        for(int i = 0; i < numSelected; i++){
            Topping topping = listOfItems.get(i);
            selectedList.getItems().add(topping);
            toppingsList.getItems().remove(topping);
        }
        displayPizza();
    }

    /**
     * Method to remove toppings from the selected listview.
     * Moves the removed topping back into the regular topping listview.
     * Finally, will update the user-view by calling upon display pizza method.
     */
    @FXML
    protected void removeToppings(){
        ObservableList<Topping> listOfItems = selectedList.getSelectionModel().getSelectedItems();
        int numSelected = listOfItems.size();

        for(int i = 0; i < numSelected; i++){
            Topping topping = listOfItems.get(i);
            toppingsList.getItems().add(topping);
            selectedList.getItems().remove(topping);
        }
        displayPizza();
    }

    /**
     * Method to re-enable the toppings, disabled topping listviews.
     * Will only re-enable when the user selects the create-your-own
     * pizza, which allows the user to add and remove toppings.
     */
    @FXML
    protected void enableToppings(){
        toppingsList.setDisable(false);
        selectedList.setDisable(false);
        addButton.setDisable(false);
        removeButton.setDisable(false);
        displayPizza();
    }

    /**
     * Method to disable the add, remove buttons as well as the listviews.
     * Will be called upon when the user selected preset pizzas. Preset
     * pizzas are not editable and therefore the user should not be able
     * to add or remove toppings from the listviews.
     */
    @FXML
    protected void disableToppings(){
        toppingsList.setDisable(true);
        selectedList.setDisable(true);
        addButton.setDisable(true);
        removeButton.setDisable(true);
        displayPizza();
    }

    /**
     * Method to get the current toppings and return them.
     * Adds all toppings from selected listview to an array.
     * @return arraylist, an arraylist containing the toppings.
     */
    public ArrayList<Topping> getToppings(){
        ArrayList<Topping> returnToppings = new ArrayList<>();
        for(Topping t : selectedList.getItems()){
            returnToppings.add(t);
        }
        return returnToppings;
    }

    /**
     * Method to update the display. Display is for user convenience.
     * With display the size, crust, toppings and price of pizza.
     */
    @FXML
    protected void displayPizza(){
        outputText.clear();

        RadioButton selectedType = (RadioButton) pizzaTypes.getSelectedToggle();
        Pizza currentPizza = getCustomizedPizza(selectedType.getText());
        setImage(selectedType.getText());
        if(currentPizza == null){
            outputText.setText("Invalid Pizza");
        }
        outputText.appendText(selectedType.getText() + ":  \n");
        outputText.appendText("Size: " + currentPizza.getSize() + "\n");
        outputText.appendText("Crust: " + currentPizza.getCrust().toString() + "\n");
        outputText.appendText("Toppings: \n");
        ArrayList<Topping> toppings = currentPizza.getToppings();

        for(int i = 0; i < toppings.size(); i++){
            outputText.appendText("\t" + toppings.get(i).toString() + "\n");
        }

        if(toppings.size() == 0){
            outputText.appendText("\tNone\n");
        }
        outputText.appendText("Price: ");
        outputText.appendText(String.format("%.2f", currentPizza.price()));
    }

    /**
     * Will adjust image shown based upon the user selected pizza.
     * @param pizzaName, takes in the name of the selected pizza.
     */
    private void setImage(String pizzaName){
        try{
            Image image;
            switch(pizzaName) {
                case "Deluxe" -> {
                    image = new Image(PizzaManagerMain.class.getResource("deluxe_Chicago.jpg").openStream());
                }
                case "Meatzza" -> {
                    image = new Image(PizzaManagerMain.class.getResource("meatzza_Chicago.jpg").openStream());
                }
                case "BBQ Chicken" -> {
                    image = new Image(PizzaManagerMain.class.getResource("BBQ_Chicago.jpg").openStream());
                }
                default -> {
                    image = new Image(PizzaManagerMain.class.getResource("buildYourOwn_Chicago.jpg").openStream());
                }
            }
            pizzaView.setImage(image);
        }

        catch(Exception e){

        }
    }

    /**
     * Method to create and set returnpizza to the user selected pizza.
     * Sets the size and topping fields according to user selection.
     * If user selects preset pizza will call upon appropriate creation method.
     * @param pizzaName, the name of the pizza being created.
     * @return returnpizza, returns the created pizza.
     */
    private Pizza getCustomizedPizza(String pizzaName){
        ChicagoPizza chicagoFactory = new ChicagoPizza();

        Pizza returnPizza;
        switch(pizzaName){
            case "Deluxe" -> {returnPizza = chicagoFactory.createDeluxe();}
            case "Meatzza" -> {returnPizza = chicagoFactory.createMeatzza();}
            case "BBQ Chicken" -> {returnPizza = chicagoFactory.createBBQChicken();}
            case "Build Your Own" -> {returnPizza = chicagoFactory.createBuildYourOwn();}
            default -> {returnPizza = null;}
        }

        String pizzaSize = pizzaSizes.getSelectionModel().getSelectedItem();
        switch(pizzaSize){
            case "Small" -> returnPizza.setSize(Size.SMALL);
            case "Medium" -> returnPizza.setSize(Size.MEDIUM);
            case "Large" -> returnPizza.setSize(Size.LARGE);
        }

        if(returnPizza instanceof BuildYourOwn) {
            returnPizza.setToppings(getToppings());
        }

        return returnPizza;
    }

    /**
     * Method to create the ordered pizza and add it to the order listview.
     */
    @FXML
    protected void addOrder(){
        RadioButton selectedType = (RadioButton) pizzaTypes.getSelectedToggle();
        Pizza currentPizza = getCustomizedPizza(selectedType.getText());
        myPizzeria.addToCurrentOrder(currentPizza);
    }
}

