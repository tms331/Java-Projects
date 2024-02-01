package com.computerscience.project5;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Controller for store order, executes commands based upon user selection.
 * Contains methods to add orders to listview, set pizzeria, store orders, cancel orders,
 * export orders, view order, show price and set total.
 * @author Thomas Shea, James Artuso
 */

public class StoreOrderController extends Activity implements Taxable {

    private StoreOrder storeOrderObj = new StoreOrder();
    private PizzaManager myPizzeria;
    private Button back_button;
    private RecyclerView orderRecycler;
    private TextView order_info_view;
    private Button clear_button;
    private TextView store_total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setPizzeria();
        setContentView(R.layout.store_order_layout);
        back_button = findViewById(R.id.store_order_back_button);
        setUpBackButton();
        order_info_view = findViewById(R.id.order_info_view);
        order_info_view.setMovementMethod(new ScrollingMovementMethod());
        storeOrderObj = myPizzeria.getCurrentStoreOrder();
        orderRecycler = findViewById(R.id.order_recycler);
        ItemAdapter<Order> adapter = new ItemAdapter(this, storeOrderObj.getOrders(), storeOrderObj,  order_info_view);
        orderRecycler.setAdapter(adapter);
        orderRecycler.setLayoutManager(new LinearLayoutManager(this));

        clear_button = findViewById(R.id.clear_store_order_button);
        setUpClearButton();
        store_total = findViewById(R.id.store_total);
        setPrices();
    }

    /**
     * Helper method to help initialize the clear button
     */
    private void setUpClearButton(){
        clear_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(storeOrderObj.getOrders().size() > 0){
                    Context context = getApplicationContext();
                    Toast.makeText(context,
                            R.string.cleared, Toast.LENGTH_SHORT).show();
                }
                storeOrderObj.getOrders().clear();
                orderRecycler.getAdapter().notifyDataSetChanged();
                order_info_view.setText("");
                setPrices();
            }
        });
    }

    /**
     * helper method to help initialize the back button
     */
    private void setUpBackButton(){
        back_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(StoreOrderController.this, PizzaManagerActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivityIfNeeded(intent, 0);
                finish();
            }
        });
    }

    /**
     * Method to add orders to the list view.
     * @param obj, order to be added.
     * @return boolean, true if successfully added.
     */
    public boolean add(Object obj) {
        if(obj instanceof Order){
            //setTotalText();
            return true;
        }
        return false;
    }

    /**
     * Method to set corresponding pizzeria.

     */
    public void setPizzeria(){
        myPizzeria = PizzaManagerActivity.myManager;
    }


    /**
     * Method to set the total text.
     * Sets the total based upon the order.
     */
    public void setPrices(){
       store_total.setText(String.format("Store Total: $%.2f", price()));
    }


    /**
     * Method to show the price of corresponding store order.
     * @return double, the price of the order.
     */
    private double price(){
        double price = 0;
        for(Order order : storeOrderObj.getOrders()){
            price += order.price();
        }
        return price;
    }


}
