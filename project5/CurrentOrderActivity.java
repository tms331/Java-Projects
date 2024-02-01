package com.computerscience.project5;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Class is a controller for the current order Activity. An Activity where users can see the pizzas they've ordered.
 * Class has methods that will update the user view accordingly. Will add items to the order if added to current order,
 * will add order to store order if the order is placed, also has methods to clear the current order when placed or canceled.
 * @author Thomas Shea, James Artuso
 */

public class CurrentOrderActivity extends Activity implements Taxable {

    private Order orderObj = new Order();
    private PizzaManager myPizzeria;
    private Button backButton;
    private RecyclerView pizzaRecycler;
    private TextView pizzaInfoView;
    private Button clearButton;
    private Button addOrderButton;
    private TextView orderTotal;


    /**
     * Method to be called when activity is created
     * @param savedInstanceState bundle to create
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setPizzeria();
        orderObj = myPizzeria.getCurrentOrder();
        setContentView(R.layout.current_order_layout);
        backButton = findViewById(R.id.current_back_button);
        setBackButton();

        pizzaInfoView = findViewById(R.id.pizza_info_view);

        pizzaRecycler = findViewById(R.id.pizza_recycler);
        setPizzaRecycler();

        clearButton = findViewById(R.id.clear_order_button);
        setClearButton();

        addOrderButton = findViewById(R.id.add_order_button);
        setAddOrderButton();
        orderTotal = findViewById(R.id.order_total);
        setPrices();
    }

    /**
     * Method to initialize the clear button
     */
    private void setClearButton(){
        clearButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(orderObj.getPizzas().size()>0){
                    Context context = getApplicationContext();
                    Toast.makeText(context,
                            R.string.cleared, Toast.LENGTH_SHORT).show();
                }
                orderObj.getPizzas().clear();
                pizzaRecycler.getAdapter().notifyDataSetChanged();
                setPrices();
            }
        });
    }

    /**
     * Helper method to initialize the add order button
     */
    private void setAddOrderButton(){
        addOrderButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(orderObj.getPizzas().size()>0){
                    Context context = getApplicationContext();
                    Toast.makeText(context,
                            R.string.ordered, Toast.LENGTH_SHORT).show();
                }
                place_order();
                orderObj.getPizzas().clear();
                ((ItemAdapter)pizzaRecycler.getAdapter()).updateData(orderObj, orderObj.getPizzas());
                pizzaRecycler.getAdapter().notifyDataSetChanged();
                pizzaInfoView.setText("");
                setPrices();
            }
        });
    }

    /**
     * Method to set the current pizzeria being used.
     */
    public void setPizzeria(){
        myPizzeria = PizzaManagerActivity.myManager;
    }

    /**
     * Helper method to initialize the back button
     */
    private void setBackButton(){
        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(CurrentOrderActivity.this, PizzaManagerActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivityIfNeeded(intent, 0);
                finish();
            }
        });
    }

    /**
     * Helper method to initialize the pizza recycler
     */
    private void setPizzaRecycler(){
        ItemAdapter<Pizza> adapter = new ItemAdapter(this, orderObj.getPizzas(), orderObj,  pizzaInfoView);
        pizzaRecycler.setAdapter(adapter);
        pizzaRecycler.setLayoutManager(new LinearLayoutManager(this));

        pizzaRecycler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setPrices();
            }
        });
    }

    /**
     * Method to add items to the orderlist listview and update price accordingly.
     * @param obj, object to be added to the orderlist view.
     * @return boolean, true if added successfully, false otherwise.
     */
    public boolean add(Object obj) {
        if(obj instanceof Pizza){
            orderObj.add((Pizza) obj);
            return true;
        }
        return false;

    }


    /**
     * Method to place order, will move order to store order,
     * and remove pizzas from current order. Also sets order
     * number for the store order.
     */
    protected void place_order(){
        if (orderObj.getPizzas().size() > 0) {
            orderObj.setOrderNumber(myPizzeria.getOrderNumber());
            myPizzeria.addtoStoreOrder(orderObj);
            orderObj = new Order();
            myPizzeria.setCurrentOrder(orderObj);

        }
    }

    /**
     * Method to clear price fields and set then set current price, tax and total.
     */
    public void setPrices(){
        String orderString = "";
        double subtotal = orderObj.price();
        orderString = orderString + String.format("Subtotal: $%.2f\n", subtotal);
        double tax = subtotal * 0.06625;
        orderString = orderString + String.format("Tax: $%.2f\n", tax);
        double total = subtotal + tax;
        orderString = orderString + String.format("Total: $%.2f", total);
        orderTotal.setText(orderString);
    }
}
