package com.computerscience.project5;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Class to get view for ToppingsAdapter
 * @author Thomas Shea, James Artuso
 */
public class ToppingsHolder extends RecyclerView.ViewHolder {
    private TextView toppingTextview;
    private Button addButton;
    ToppingsAdapter myAdapter = null;
    private Topping myTopping;
    private ImageView toppingImageView;

    /**
     * Constructor for ToppingsHolder
     * @param itemView view of item
     * @param currAdapter adapter that instantiated the ToppingsHolder
     */
    public ToppingsHolder(@NonNull View itemView, ToppingsAdapter currAdapter) {
        super(itemView);
        toppingTextview = itemView.findViewById(R.id.name_textview);
        toppingImageView = itemView.findViewById(R.id.topping_imageview);
        addButton = itemView.findViewById(R.id.view_button);
        setAddButtonOnClick(itemView);
        myAdapter = currAdapter;
    }

    /**
     * Getter method for text view
     * @return text view of view
     */
    public TextView getTextView(){
        return toppingTextview;
    }

    /**
     * Getter method for add button
     * @return add button of view
     */
    public Button getAddButton(){
        return addButton;
    }

    /**
     * Getter method for image view
     * @return image view of view
     */
    public ImageView getImageView(){
        return toppingImageView;
    }

    /**
     * Setter method for topping
     * @param newTopping new topping to be stored
     */
    public void setTopping(Topping newTopping){
        myTopping = newTopping;
    }

    /**
     * Helper method to set text of an alerts button
     * @param alert alert to be modified
     */
    private void setButtonText(AlertDialog.Builder alert){
        if(addButton.getText().equals("add")){
            alert.setTitle("Add to order");
        }
        else{
            alert.setTitle("Remove from order");
        }
    }

    /**
     * Helper method to set on negative click
     * @return string of text for on negative click
     */
    private String getNegativeOnClickText(){
        if(addButton.getText().equals("add")){
            return " not added.";
        }
        return " not removed.";
    }

    /**
     * Set the onClickListener for the button on each row.
     * Clicking on the button will create an AlertDialog with the correct options
     * depending on whether the item  is added already or not
     * @param itemView
     */
    private void setAddButtonOnClick(@NonNull View itemView) {
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(itemView.getContext());
                setButtonText(alert);
                alert.setMessage(toppingTextview.getText().toString());
                alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if(addButton.getText().equals("add")) {
                            Toast.makeText(itemView.getContext(),
                                    toppingTextview.getText().toString() + " added.", Toast.LENGTH_LONG).show();
                            myAdapter.add(myTopping, getAdapterPosition());
                            addButton.setText(R.string.remove);
                            myAdapter.notifyDataSetChanged();
                        }
                        else{
                            Toast.makeText(itemView.getContext(),
                                    toppingTextview.getText().toString() + " removed.", Toast.LENGTH_LONG).show();
                            myAdapter.remove(myTopping, getAdapterPosition());
                            addButton.setText(R.string.add);
                            myAdapter.notifyDataSetChanged();
                        }
                    }
                }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(itemView.getContext(),
                                toppingTextview.getText().toString() + getNegativeOnClickText(), Toast.LENGTH_LONG).show();
                    }
                });
                AlertDialog dialog = alert.create();
                dialog.show();
            }
        });
    }
}