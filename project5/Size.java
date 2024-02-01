package com.computerscience.project5;
/**
 * Enum class for pizza size, contains constructor to make size typ and a to-string method.
 * @author Thomas Shea, James Artuso
 */

public enum Size {
    SMALL ("Small"),
    MEDIUM ("Medium"),
    LARGE ("Large");

    private final String NAME;

    /**
     * Constructor used to create the size datatype, contains string which denotes size.
     * @param name, the size name that is to be created into size type.
     */
    Size(String name){
        this.NAME = name;
    }

    /**
     * to string method to make more user-friendly string.
     * @return string, returns the corresponding size.
     */
    @Override
    public String toString(){
        return NAME;
    }
}
