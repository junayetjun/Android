package com.istiaq.food;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.istiaq.food.model.CartItem;
import com.istiaq.food.model.Food;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FoodAdapter adapter;
    private Button cartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        cartButton = findViewById(R.id.cartButton);

        List<Food> foodList = Arrays.asList(
                new Food("Pizza", 8.99, "Cheesy pizza with tomato sauce", R.drawable.pizza),
                new Food("Burger", 5.99, "Beef burger with fries", R.drawable.burger),
                new Food("Pasta", 7.49, "Italian pasta with white sauce", R.drawable.pasta),
                new Food("Sandwich", 4.99, "Veggie sandwich with mayo", R.drawable.sandwich),
                new Food("Curry Pizza", 8.99, "Cheesy pizza with tomato sauce", R.drawable.pizza),
                new Food("Chesse Burger", 5.99, "Beef burger with fries", R.drawable.burger),
                new Food("DeliciousPasta", 7.49, "Italian pasta with white sauce", R.drawable.pasta),
                new Food("Pasta", 4.99, "Veggie sandwich with mayo", R.drawable.pastat)
        );

        adapter = new FoodAdapter(foodList, food -> {
            CartManager.getInstance().addItem(new CartItem(food.getName(), food.getPrice()));
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        cartButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CartActivity.class);
            startActivity(intent);
        });
    }
}
