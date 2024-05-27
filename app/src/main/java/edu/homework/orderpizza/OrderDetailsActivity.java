package edu.homework.orderpizza;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import edu.homework.orderpizza.entity.Pizza;
import edu.homework.orderpizza.entity.PizzaSize;

public class OrderDetailsActivity extends AppCompatActivity {

    private Button buttonNext;
    private Pizza pizza;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        Bundle arguments = getIntent().getExtras();

        if(arguments!=null) {
            pizza = (Pizza) arguments.getSerializable(Pizza.class.getSimpleName());
        }

        findViewById(R.id.radioButton1).setOnClickListener(v -> {
            setPizzaSize(PizzaSize.small);
        });
        findViewById(R.id.radioButton2).setOnClickListener(v -> {
            setPizzaSize(PizzaSize.normal);
        });
        findViewById(R.id.radioButton3).setOnClickListener(v -> {
            setPizzaSize(PizzaSize.big);
        });

        CheckBox checkBox1 = findViewById(R.id.checkBox1);
        checkBox1.setOnClickListener(v -> {
            changePizzaSupplements(checkBox1);
        });
        CheckBox checkBox2 = findViewById(R.id.checkBox2);
        checkBox2.setOnClickListener(v -> {
            changePizzaSupplements(checkBox2);
        });
        CheckBox checkBox3 = findViewById(R.id.checkBox3);
        checkBox3.setOnClickListener(v -> {
            changePizzaSupplements(checkBox3);
        });
        CheckBox checkBox4 = findViewById(R.id.checkBox4);
        checkBox4.setOnClickListener(v -> {
            changePizzaSupplements(checkBox4);
        });

        buttonNext = findViewById(R.id.next);

        buttonNext.setOnClickListener(v -> {
            if(pizza == null)
                return;
            if(pizza.getSize() == null) {
                Toast toast = Toast.makeText(this, R.string.error_2,Toast.LENGTH_LONG);
                toast.show();
                return;
            }
            Intent intent = new Intent(OrderDetailsActivity.this, YourOrderActivity.class);
            intent.putExtra(Pizza.class.getSimpleName(), pizza);
            startActivity(intent);
        });
    }

    public void setPizzaSize(PizzaSize pizzaSize) {
        pizza.setSize(pizzaSize);
    }

    public void changePizzaSupplements(CheckBox checkBox) {
        if(checkBox.isChecked()) {
            pizza.addSupplement(checkBox.getText().toString());
        }
        else {
            pizza.removeSupplement(checkBox.getText().toString());
        }
    }
}
