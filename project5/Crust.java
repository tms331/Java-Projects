package com.computerscience.project5;
/**
 * Enum class containing different type of crust and
 * methods create crust data type and return crust name.
 * @author Thomas Shea, James Artuso
 */

public enum Crust {
    DEEPDISH ("Deep Dish"),
    PAN ("Pan"),
    STUFFED ("Stuffed"),
    BROOKLYN ("Brooklyn"),
    THIN ("Thin"),
    HANDTOSSED ("Hand Tossed");

    private final String NAME;

    /**
     * Constructor to create crust datatype.
     * @param name, name of crust.
     */
    Crust(String name){
        this.NAME = name;
    }

    /**
     * Method to return name of the crust.
     * @return string, name of the crust.
     */
    @Override
    public String toString(){
        return NAME;
    }
}
