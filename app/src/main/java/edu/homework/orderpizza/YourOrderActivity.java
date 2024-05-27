package edu.homework.orderpizza;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import edu.homework.orderpizza.entity.Pizza;

public class YourOrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_order);
        Bundle arguments = getIntent().getExtras();

        if(arguments!=null) {
            Pizza pizza = (Pizza) arguments.getSerializable(Pizza.class.getSimpleName());
            TextView pizzaTitle = findViewById(R.id.textViewPizza);
            pizzaTitle.setText(pizza.getTitle());
            String size = "";
            switch (pizza.getSize()) {
                case small:
                    size = MainActivity.getAppContext().getString(R.string.small);
                    break;
                case normal:
                    size = MainActivity.getAppContext().getString(R.string.big);
                    break;
                case big:
                    size = MainActivity.getAppContext().getString(R.string.big);
                    break;
            }
            TextView pizzaSize = findViewById(R.id.textViewSize);
            pizzaSize.setText(size);
            String supplements = android.text.TextUtils.join(", ", pizza.getSupplements());
            TextView pizzaSupplements = findViewById(R.id.textViewSupplements);
            pizzaSupplements.setText(supplements);
            TextView pizzaTotalPrice = findViewById(R.id.textViewTotalPrice);
            pizzaTotalPrice.setText(Double.toString(pizza.calcTotalPrice()));
        }
    }
}