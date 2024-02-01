package projectpackage;
/**
 * Enum class for toppings. A toppings array is a field of the pizza data type.
 * @author Thomas Shea, James Artuso
 */

public enum Topping{
    SAUSAGE ("Sausage"),
    PEPPERONI ("Pepperoni"),
    GREENPEPPER ("Green Pepper"),
    ONION ("Onion"),
    MUSHROOM ("Mushroom"),
    BBQCHICKEN ("BBQ Chicken"),
    PROVOLONE ("Provolone"),
    CHEDDAR ("Cheddar"),
    BEEF ("Beef"),
    HAM ("Ham"),
    BACON ("Bacon"),
    OLIVES( "Olives"),
    SPINACH("Spinach");

    private final String NAME;

    /**
     * Constructor used to make topping data type.
     * Has field name, name of the topping.
     * @param name, name that the field will be set to.
     */
    Topping(String name){
        this.NAME = name;
    }

    /**
     * Method to return a string containing the name of the topping.
     * @return string, returns name of topping as string.
     */
    @Override
    public String toString(){
        return NAME;
    }
}
