package com.istiaq.food;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
        cartButton = findViewById(R.id.cartButton); // add this

        // Sample food list
        List<Food> foodList = Arrays.asList(
                new Food("Pizza", 8.99, "Cheesy pizza with tomato sauce"),
                new Food("Burger", 5.99, "Beef burger with fries"),
                new Food("Pasta", 7.49, "Italian pasta with white sauce"),
                new Food("Sandwich", 4.99, "Veggie sandwich with mayo")
        );

        // Set adapter with click listener
        adapter = new FoodAdapter(foodList, food -> {
            CartManager.getInstance().addItem(new CartItem(food.getName(), food.getPrice()));
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Handle button click to open CartActivity
        cartButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CartActivity.class);
            startActivity(intent);
        });
    }
}
