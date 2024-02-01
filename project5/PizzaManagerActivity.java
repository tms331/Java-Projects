package com.computerscience.project5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.Serializable;


/**
 * Pizza Manager Controller class, this class acts as the main screen for the app.
 * @author Thomas Shea, James Artuso
 */

public class PizzaManagerActivity extends Activity implements Serializable {

    private ImageView nyView;
    private ImageView chicagoView;
    private ImageView currentOrderView;
    private ImageView storeOrderView;
    public static PizzaManager myManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        myManager = new PizzaManager();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pizza_manager_main);
        nyView = findViewById(R.id.nyImageView);
        setUpNY();

        chicagoView = findViewById(R.id.chicagoImageView);
        setUpChicago();

        currentOrderView = findViewById(R.id.currentOrderView);
        setUpCurrentView();

        storeOrderView = findViewById(R.id.storeOrderView);
        setUpStoreOrderView();
    }

    /**
     * Helper method to initialize NY Pizza button
     */
    private void setUpNY(){
        nyView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(PizzaManagerActivity.this, NyPizzaActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Helper method to initialize Chicago Pizza button
     */
    private void setUpChicago(){
        chicagoView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(PizzaManagerActivity.this, ChicagoPizzaActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Helper method to initialize current view button
     */
    private void setUpCurrentView(){
        currentOrderView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(PizzaManagerActivity.this, CurrentOrderActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Helper method to initialize store order view button
     */
    private void setUpStoreOrderView(){
        storeOrderView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(PizzaManagerActivity.this, StoreOrderController.class);
                startActivity(intent);
            }
        });
    }

}