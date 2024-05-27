package edu.homework.orderpizza;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import edu.homework.orderpizza.entity.Pizza;


public class MainActivity extends AppCompatActivity {
    private static Context context;
    private Button buttonNext;
    private RadioGroup radioGroup;
    private String selectedPizza = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivity.context = getApplicationContext();
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.menu), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        radioGroup = findViewById(R.id.radioGroup);

        radioGroup.setOnCheckedChangeListener((radiogroup, id) -> {
            RadioButton radio = findViewById(id);
            selectedPizza = radio.getText().toString();
        });

        buttonNext = findViewById(R.id.next);

        buttonNext.setOnClickListener(v -> {
            if(selectedPizza.isEmpty()) {
                Toast toast = Toast.makeText(this, R.string.error_1,Toast.LENGTH_LONG);
                toast.show();
                return;
            }

            Pizza pizza = new Pizza();
            pizza.setTitle(selectedPizza);

            Intent intent = new Intent(MainActivity.this, OrderDetailsActivity.class);
            intent.putExtra(Pizza.class.getSimpleName(), pizza);
            startActivity(intent);
        });
    }

    public static Context getAppContext() {
        return MainActivity.context;
    }
}