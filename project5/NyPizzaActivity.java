package com.computerscience.project5;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class is a controller for the ny pizza view contains associated methods.
 * From this view the user can order different types of ny pizzas
 * or even create their own. Will update user interface to reflect changes.
 * @author Thomas Shea, James Artuso
 */

public class NyPizzaActivity extends Activity implements Customizable {

    private ArrayList<Topping> toppingsList = new ArrayList<>();
    private ArrayList<Topping> selectedList = new ArrayList<>();
    private RecyclerView toppingsRecycler;
    private final Size[]  sizes = new Size[]{Size.SMALL, Size.MEDIUM, Size.LARGE};
    private final String[] types = new String[]{"Deluxe", "BBQ Chicken",
            "Meatzza", "Build Your Own"};
    private Spinner sizeSpinner;
    private Button orderButton;
    private TextView pizzaView;
    private Button backButton;
    private ImageView pizzaImage;
    private ListView pizzaTypes;
    private String selectedType = "Deluxe";

    private PizzaManager myPizzeria;

    /**
     * Method to be called when activity is created
     * @param savedInstanceState bundle to create
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setPizzeria();
        setContentView(R.layout.pizza_layout);

        toppingsRecycler = findViewById(R.id.toppingsRecycler);
        setToppingsRecycler();

        sizeSpinner = findViewById(R.id.size_spinner);
        setSizeSpinner();

        orderButton = findViewById(R.id.order_button);
        setOrderButton();

        backButton = findViewById(R.id.back_button);
        setBackButton();
        pizzaTypes = findViewById(R.id.pizza_types);
        setPizzaTypes();

        pizzaImage = findViewById(R.id.pizza_image);
        setPizzaImage();

        pizzaView = findViewById(R.id.pizza_view);
        displayPizza();
    }

    /**
     * Helper method to initilize the pizza types ListView
     */
    private void setPizzaTypes(){
        ArrayAdapter<String> buttonAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, types);
        pizzaTypes.setAdapter(buttonAdapter);
        pizzaTypes.setSelection(0);
        pizzaTypes.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        pizzaTypes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedType = (String) pizzaTypes.getItemAtPosition(i);
                if(selectedType.equals("Build Your Own")){
                    toppingsRecycler.setVisibility(View.VISIBLE);
                }
                else{
                    toppingsRecycler.setVisibility(View.INVISIBLE);
                }
                displayPizza();
                setPizzaImage();
            }
        });
    }

    /**
     * Method to add topping to the selected toppings list
     * @param obj topping to be added
     * @return boolean true if the item was added
     */
    public boolean add(Object obj){
        if(obj instanceof Topping) {
            Topping topping = (Topping) obj;
            toppingsList.remove(topping);
            return selectedList.add(topping);
        }
        return false;
    }

    /**
     * Method to remove topping from the selected toppings list
     * @param obj topping to be remove
     * @return boolean, true if the item was removed
     */
    public boolean remove(Object obj){
        if(obj instanceof Topping){
            Topping topping = (Topping) obj;
            toppingsList.add(topping);
            return selectedList.remove(topping);
        }
        return false;
    }


    /**
     * Helper method to initialize set order button
     */
    private void setOrderButton(){
        orderButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Context context = getApplicationContext();
                if(selectedType.equals("Build Your Own") && selectedList.size()>7){
                    Toast.makeText(context, R.string.too_many, Toast.LENGTH_SHORT).show();
                }
                else {
                    addOrder();
                    Toast.makeText(context,
                            R.string.pizza_ordered, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * Helper method to initialize the back button
     */
    private void setBackButton(){
        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(NyPizzaActivity.this, PizzaManagerActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivityIfNeeded(intent, 0);
                finish();
            }
        });
    }

    /**
     * Method to change the pizza image display based on
     * selected pizza
     */
    private void setPizzaImage(){
        String selected = selectedType;

        switch (selected){
            case "Deluxe":
                pizzaImage.setImageResource(R.drawable.deluxe_ny);
                break;
            case "BBQ Chicken":
                pizzaImage.setImageResource(R.drawable.bbq_ny);
                break;
            case "Meatzza":
                pizzaImage.setImageResource(R.drawable.meatzza_ny);
                break;
            case "Build Your Own":
                pizzaImage.setImageResource(R.drawable.build_your_own_ny);
                break;
        }
    }

    /**
     * Helper method to initialize the toppings recycler
     */
    private void setToppingsRecycler(){
        toppingsList.addAll(Arrays.asList(new Topping[]{Topping.SAUSAGE, Topping.CHEDDAR,
                Topping.PEPPERONI, Topping.PROVOLONE, Topping.HAM,
                Topping.BBQCHICKEN, Topping.ONION, Topping.MUSHROOM, Topping.GREENPEPPER,
                Topping.BACON, Topping.OLIVES, Topping.SPINACH, Topping.BEEF}));
        ArrayList<Integer> images = new ArrayList<Integer>();
        images.addAll(Arrays.asList(new Integer[]{R.drawable.sausage, R.drawable.cheddar,
                R.drawable.pepperoni, R.drawable.provolone, R.drawable.ham, R.drawable.bbqchicken,
                R.drawable.onion, R.drawable.mushroom, R.drawable.green_pepper, R.drawable.bacon,
                R.drawable.olives, R.drawable.spinach, R.drawable.beef}));
        ToppingsAdapter adapter = new ToppingsAdapter(this, toppingsList, images, this);
        toppingsRecycler.setAdapter(adapter);
        toppingsRecycler.setLayoutManager(new LinearLayoutManager(this));
        toppingsRecycler.setVisibility(View.INVISIBLE);
    }

    /**
     * Helper method to initialize the size spinner
     */
    private void setSizeSpinner(){
        ArrayAdapter<Size> sizeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sizes);
        sizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sizeSpinner.setAdapter(sizeAdapter);

        sizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                displayPizza();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent){}
        });
    }



    /**
     * Method to set appropriate pizzeria manager
     */
    public void setPizzeria(){
        myPizzeria = PizzaManagerActivity.myManager;
    }



    /**
     * Method to update the display. Display is for user convenience.
     * With display the size, crust, toppings and price of pizza.
     * */
    public void displayPizza(){
        pizzaView.setText("");

        Pizza currentPizza = getCustomizedPizza();

        if(currentPizza == null){
            pizzaView.setText("Invalid Pizza");
        }
        pizzaView.append(selectedType + ":  \n");
        pizzaView.append("Size: " + currentPizza.getSize() + "\n");
        pizzaView.append("Crust: " + currentPizza.getCrust().toString() + "\n");
        pizzaView.append("Toppings: \n");
        ArrayList<Topping> toppings = currentPizza.getToppings();

        for(int i = 0; i < toppings.size(); i++){
            pizzaView.append("\t" + toppings.get(i).toString() + "\n");
        }

        if(toppings.size() == 0){
             pizzaView.append("\tNone\n");
        }
        pizzaView.append("Price: ");
        pizzaView.append(String.format("%.2f", currentPizza.price()));
    }

    /**
     * Method to create and set returnpizza to the user selected pizza.
     * Sets the size and topping fields according to user selection.
     * If user selects preset pizza will call upon appropriate creation method.
     * @return returnpizza, returns the created pizza.
     */
    private Pizza getCustomizedPizza(){
        NYPizza NYFactory = new NYPizza();

        Pizza returnPizza;

        switch(selectedType){
            case "Deluxe": {returnPizza = NYFactory.createDeluxe(); break;}
            case "Meatzza": {returnPizza = NYFactory.createMeatzza(); break;}
            case "BBQ Chicken": {returnPizza = NYFactory.createBBQChicken(); break;}
            case "Build Your Own": {returnPizza = NYFactory.createBuildYourOwn(); break;}
            default: {returnPizza = null;}
        }

        returnPizza.setSize((Size)sizeSpinner.getSelectedItem());

        if(returnPizza instanceof BuildYourOwn) {
            returnPizza.setToppings(new ArrayList<>(selectedList));
        }

        return returnPizza;
    }


    /**
     * Method to add pizza to order
     */
    protected void addOrder(){
        Pizza currentPizza = getCustomizedPizza();
        myPizzeria.addToCurrentOrder(currentPizza);
    }

}

