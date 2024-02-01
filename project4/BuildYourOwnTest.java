package projectpackage;

import org.junit.Test;

import static org.junit.Assert.*;

public class BuildYourOwnTest {
    //For these tests, a delta of 0.0001 should be fine
    //because price is being compared and price is only
    //to two decimal places

    /**
     * Test Small Pizza with no toppings
     */
    @Test
    public void price_small_no_toppings() {
        NYPizza NYFactory = new NYPizza();
        Pizza pizza = NYFactory.createBuildYourOwn();
        pizza.setSize(Size.SMALL);
        assertEquals(8.99, pizza.price(), 0.0001);
    }

    /**
     * Test Small Pizza with 1 topping
     */
    @Test
    public void price_small_1_toppings() {
        NYPizza NYFactory = new NYPizza();
        Pizza pizza = NYFactory.createBuildYourOwn();
        pizza.setSize(Size.SMALL);
        pizza.add(Topping.GREENPEPPER);
        assertEquals(10.58, pizza.price(), 0.0001);
    }

    /**
     * Test Small Pizza with 7 topping
     */
    @Test
    public void price_small_7_toppings() {
        NYPizza NYFactory = new NYPizza();
        Pizza pizza = NYFactory.createBuildYourOwn();
        pizza.setSize(Size.SMALL);
        pizza.add(Topping.GREENPEPPER);
        pizza.add(Topping.ONION);
        pizza.add(Topping.PEPPERONI);
        pizza.add(Topping.SAUSAGE);
        pizza.add(Topping.MUSHROOM);
        pizza.add(Topping.CHEDDAR);
        pizza.add(Topping.BBQCHICKEN);
        assertEquals(20.12, pizza.price(), 0.0001);
    }


    /**
     * Test Medium Pizza with no toppings
     */
    @Test
    public void price_medium_no_toppings() {
        NYPizza NYFactory = new NYPizza();
        Pizza pizza = NYFactory.createBuildYourOwn();
        pizza.setSize(Size.MEDIUM);
        assertEquals(10.99, pizza.price(), 0.0001);
    }

    /**
     * Test Medium Pizza with 1 topping
     */
    @Test
    public void price_medium_1_toppings() {
        NYPizza NYFactory = new NYPizza();
        Pizza pizza = NYFactory.createBuildYourOwn();
        pizza.setSize(Size.MEDIUM);
        pizza.add(Topping.GREENPEPPER);
        assertEquals(12.58, pizza.price(), 0.0001);
    }

    /**
     * Test Medium Pizza with 7 topping
     */
    @Test
    public void price_medium_7_toppings() {
        NYPizza NYFactory = new NYPizza();
        Pizza pizza = NYFactory.createBuildYourOwn();
        pizza.setSize(Size.MEDIUM);
        pizza.add(Topping.GREENPEPPER);
        pizza.add(Topping.ONION);
        pizza.add(Topping.PEPPERONI);
        pizza.add(Topping.SAUSAGE);
        pizza.add(Topping.MUSHROOM);
        pizza.add(Topping.CHEDDAR);
        pizza.add(Topping.BBQCHICKEN);
        assertEquals(22.12, pizza.price(), 0.0001);
    }

    /**
     * Test Large Pizza with no toppings
     */
    @Test
    public void price_large_no_toppings() {
        NYPizza NYFactory = new NYPizza();
        Pizza pizza = NYFactory.createBuildYourOwn();
        pizza.setSize(Size.LARGE);
        assertEquals(12.99, pizza.price(), 0.0001);
    }

    /**
     * Test Large Pizza with 1 topping
     */
    @Test
    public void price_large_1_toppings() {
        NYPizza NYFactory = new NYPizza();
        Pizza pizza = NYFactory.createBuildYourOwn();
        pizza.setSize(Size.LARGE);
        pizza.add(Topping.GREENPEPPER);
        assertEquals(14.58, pizza.price(), 0.0001);
    }

    /**
     * Test Large Pizza with 7 topping
     */
    @Test
    public void price_large_7_toppings() {
        NYPizza NYFactory = new NYPizza();
        Pizza pizza = NYFactory.createBuildYourOwn();
        pizza.setSize(Size.LARGE);
        pizza.add(Topping.GREENPEPPER);
        pizza.add(Topping.ONION);
        pizza.add(Topping.PEPPERONI);
        pizza.add(Topping.SAUSAGE);
        pizza.add(Topping.MUSHROOM);
        pizza.add(Topping.CHEDDAR);
        pizza.add(Topping.BBQCHICKEN);
        assertEquals(24.12, pizza.price(), 0.0001);
    }

}