package edu.homework.orderpizza.entity;

import android.content.Context;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.homework.orderpizza.MainActivity;
import edu.homework.orderpizza.R;

public class Pizza implements Serializable {
    private String title;
    private PizzaSize pizzaSize;
    private List<String> supplements;

    private static final Map<String, Double> prices;
    static {
        Context appContext = MainActivity.getAppContext();
        prices = new HashMap<>();
        prices.put(appContext.getString(R.string.pizza_1), Double.valueOf(50));
        prices.put(appContext.getString(R.string.pizza_2),  Double.valueOf(20));
        prices.put(appContext.getString(R.string.pizza_3),  Double.valueOf(30));

        prices.put(appContext.getString(R.string.supplement_1),  Double.valueOf(10));
        prices.put(appContext.getString(R.string.supplement_2),  Double.valueOf(5));
        prices.put(appContext.getString(R.string.supplement_3),  Double.valueOf(15));
        prices.put(appContext.getString(R.string.supplement_4),  Double.valueOf(10));
    }

    public Pizza() {
        supplements = new ArrayList<>();
    }

    public Pizza(String title, PizzaSize pizzaSize, List<String> supplements) {
        this.title = title;
        this.pizzaSize = pizzaSize;
        this.supplements = supplements;
    }

    public String getTitle() {
        return title;
    }

    public PizzaSize getSize() {
        return pizzaSize;
    }

    public List<String> getSupplements() {
        return supplements;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSize(PizzaSize pizzaSize) {
        this.pizzaSize = pizzaSize;
    }

    public void setSupplements(List<String> supplements) {
        this.supplements = supplements;
    }

    public void addSupplement(String supplement) {
        if(!supplements.contains(supplement))
            supplements.add(supplement);
    }

    public void removeSupplement(String supplement) {
        supplements.remove(supplement);
    }

    public double calcTotalPrice() {
        if(this.title == null)
            return 0.0;
        double totalPrice = prices.get(this.title);

        for (String supplement : this.supplements) {
            totalPrice += prices.get(supplement);
        }

        return totalPrice * this.pizzaSize.getPriceMultiplier();
    }
}