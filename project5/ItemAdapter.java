package com.computerscience.project5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Custom adapter for the current order and store order recycler view
 * @author Thomas Shea, James Artuso
 */
public class ItemAdapter<T> extends RecyclerView.Adapter<ItemHolder<T>>{
    private Context context;
    private ArrayList<T> items;
    private Customizable holderItem;
    private TextView myTextBox;

    /**
     * Item adapter constructor
     * @param context item adaptor context
     * @param items ArrayList of items of type T
     * @param customItem Origin of items
     * @param textBox Parent activity textbox
     */
    public ItemAdapter(Context context, ArrayList<T> items, Customizable customItem, TextView textBox) {
        this.context = context;
        this.items = items;
        holderItem = customItem;
        myTextBox = textBox;
    }

    /**
     * Method to update the data of the adaptor
     * @param holderItem new holder item
     * @param items new ArrayList of items
     */
    public void updateData(Customizable holderItem, ArrayList<T> items){
        this.holderItem = holderItem;
        this.items = items;
    }

    /**
     * Method to add item to the holder item
     * @param item item to be added
     * @return
     */
    public boolean add(T item){
        boolean returnBool = holderItem.add(item);
        if(context instanceof Taxable){
            ((Taxable)context).setPrices();
        }
        return returnBool;
    }

    /**
     * Method to remove item from the holder item
     * @param item
     * @return
     */
    public boolean remove(T item){
        boolean returnBool = holderItem.remove(item);
        if(context instanceof Taxable){
            ((Taxable)context).setPrices();
        }
        return returnBool;
    }

    /**
     * This method will inflate the row layout for the items in the RecyclerView
     * @param parent View Group
     * @param viewType view type
     * @return ItemHolder of the view
     */
    @NonNull
    @Override
    public ItemHolder<T> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate the row layout for the items
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.order_layout, parent, false);

        return new ItemHolder(view, this);
    }

    /**
     * Assign data values for each row according to their "position" (index) when the item becomes
     * visible on the screen.
     * @param holder the instance of ItemsHolder
     * @param position the index of the item in the list of items
     */
    @Override
    public void onBindViewHolder(@NonNull ItemHolder<T> holder, int position) {
        //assign values for each row
        if(items.get(position) instanceof Titleable){
            holder.getNameTextView().setText(((Titleable) items.get(position)).getTitle());
        }
        else {
            holder.getNameTextView().setText(items.get(position).toString());
        }
        holder.setMyItem(items.get(position));
    }


    /**
     * Getter method to get text box
     * @return text box refernece
     */
    public TextView getTextBox(){
        return myTextBox;
    }

    /**
     * Get the number of items in the ArrayList.
     * @return the number of items in the list.
     */
    @Override
    public int getItemCount() {
        return items.size(); //number of MenuItem in the array list.
    }


}
