package com.computerscience.project5;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Custom adapter for the toppings recycler
 * @author Thomas Shea, James Artuso
 */
public class ToppingsAdapter extends RecyclerView.Adapter<ToppingsHolder>{
    private Context context;
    private ArrayList<Topping> toppings;
    private ArrayList<Boolean> added;
    private ArrayList<Integer> images;
    private Activity pizzaController;

    public ToppingsAdapter(Context context, ArrayList<Topping> toppings, ArrayList<Integer> images,Activity currActivity) {
        this.context = context;
        this.toppings = new ArrayList<>(toppings);
        this.images = images;
        added = new ArrayList<Boolean>(Arrays.asList(new Boolean[toppings.size()]));
        Collections.fill(added, Boolean.FALSE);
        pizzaController = currActivity;
    }

    /**
     * Method to add topping to pizza
     * @param topping topping to add
     * @param pos position of topping in array list
     * @return boolean, true if item is added
     */
    public boolean add(Topping topping, int pos){
        NyPizzaActivity nyPizza = null;
        added.set(pos, Boolean.TRUE);
        if(pizzaController instanceof NyPizzaActivity){
            nyPizza = (NyPizzaActivity) pizzaController;
            boolean returnBool = nyPizza.add(topping);
            nyPizza.displayPizza();
            return returnBool;
        }
        if(pizzaController instanceof ChicagoPizzaActivity){
            ChicagoPizzaActivity chicagoPizza = (ChicagoPizzaActivity) pizzaController;
            boolean returnBool = chicagoPizza.add(topping);
            chicagoPizza.displayPizza();
            return returnBool;
        }
        return false;
    }

    /**
     * Method to remove a topping from
     * @param topping topping to be remove
     * @param pos position of topping in array list
     * @return boolean, true if item is removed
     */
    public boolean remove(Topping topping, int pos){
        added.set(pos, Boolean.FALSE);
        if(pizzaController instanceof NyPizzaActivity){
            NyPizzaActivity nyPizza = (NyPizzaActivity) pizzaController;
            boolean returnBool = nyPizza.remove(topping);
            nyPizza.displayPizza();
            return returnBool;
        }
        if(pizzaController instanceof ChicagoPizzaActivity){
            ChicagoPizzaActivity chicagoPizza = (ChicagoPizzaActivity) pizzaController;
            boolean returnBool = chicagoPizza.remove(topping);
            chicagoPizza.displayPizza();
            return returnBool;
        }
        return false;
    }


    /**
     * This method will inflate the row layout for the items in the RecyclerView
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public ToppingsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate the row layout for the items
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.topping_layout, parent, false);
        return new ToppingsHolder(view, this);
    }

    /**
     * Assign data values for each row according to their "position" (index) when the item becomes
     * visible on the screen.
     * @param holder the instance of ItemsHolder
     * @param position the index of the item in the list of items
     */
    @Override
    public void onBindViewHolder(@NonNull ToppingsHolder holder, int position) {
        //assign values for each row
        holder.setIsRecyclable(false);
        holder.getTextView().setText(toppings.get(position).toString());
        holder.setTopping(toppings.get(position));
        holder.getImageView().setImageResource(images.get(position));

        if(added.get(position)){
            holder.getAddButton().setText("remove");
        }
        else{
            holder.getAddButton().setText("add");
        }

    }


    /**
     * Get the number of items in the ArrayList.
     * @return the number of items in the list.
     */
    @Override
    public int getItemCount() {
        return toppings.size();
    }

}
