package com.computerscience.project5;
/**
 * Class used to create a chicago style pizza of which there are four types.
 * Class implements pizza factory interface which has methods to create the pizzas.
 * Crusts are set differently based on pizza style.
 * @author Thomas Shea, James Artuso
 */

public class ChicagoPizza implements PizzaFactory{

    /**
     * Method to create a deluxe pizza with deep-dish crust.
     * Crust is set according to chicago style.
     * @return pizza, returns the created deluxe pizza.
     */
    public Pizza createDeluxe(){
        return new Deluxe(Crust.DEEPDISH);
    }

    /**
     * Method to create a BBQChicken pizza with pan crust.
     * Crust is set according to chicago style.
     * @return pizza, returns the created BBQChicken pizza.
     */
    public Pizza createBBQChicken(){
        return new BBQChicken(Crust.PAN);
    }

    /**
     * Method to create a meatzza pizza with stuffed crust.
     * Crust is set according to chicago style.
     * @return pizza, returns the created meatzza pizza.
     */
    public Pizza createMeatzza(){
        return new Meatzza(Crust.STUFFED);
    }

    /**
     * Method to create a pizza created by the user, crust is pan.
     * Crust is set according to chicago style.
     * @return pizza, returns the user created pizza.
     */
    public Pizza createBuildYourOwn(){
        return new BuildYourOwn(Crust.PAN);
    }

}
