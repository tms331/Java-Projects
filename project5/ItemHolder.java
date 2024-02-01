package com.computerscience.project5;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Class to get view for ItemAdapter
 * @author Thomas Shea, James Artuso
 */
public class ItemHolder<T> extends RecyclerView.ViewHolder {
    private TextView nameTextview;
    private Button viewButton;
    private Button removeButton;
    ItemAdapter myAdapter = null;
    private T myItem;


    /**
     * Item holder constructor
     * @param itemView view of an item
     * @param currAdapter Adapter that created the ItemHolder
     */
    public ItemHolder(@NonNull View itemView, ItemAdapter currAdapter) {
        super(itemView);
        nameTextview = itemView.findViewById(R.id.name_textview);
        viewButton = itemView.findViewById(R.id.view_button);
        removeButton = itemView.findViewById(R.id.remove_button);
        setButtonOnClick(itemView);
        myAdapter = currAdapter;
    }

    /**
     * Getter method to get the name textview
     * @return text view reference
     */
    public TextView getNameTextView(){
        return nameTextview;
    }

    /**
     * Setter method to set myItem
     * @param newItem item to be set
     */
    public void setMyItem(T newItem){
        myItem = newItem;
    }

    /**
     * Set the onClickListener for the button on each row.
     * Clicking on the button will create an AlertDialog with the options of YES/NO.
     * @param itemView itemView view
     */
    private void setButtonOnClick(@NonNull View itemView) {

        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myAdapter.getTextBox().setText(myItem.toString());
            }
        });
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myAdapter.remove(myItem);
                myAdapter.notifyItemRemoved(getAdapterPosition());
                myAdapter.getTextBox().setText("");
                Toast.makeText(itemView.getContext(),
                        R.string.removed, Toast.LENGTH_LONG).show();
            }
        });

    }
}