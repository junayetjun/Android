package com.istiaq.food;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.istiaq.food.model.CartItem;

import java.util.List;

public class CartActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextView totalText;
    private Button checkoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerView = findViewById(R.id.cartRecyclerView);
        totalText = findViewById(R.id.totalText);
        checkoutButton = findViewById(R.id.checkoutButton);

        // Get cart items
        List<CartItem> items = CartManager.getInstance().getItems();

        // Setup adapter with listener to update total
        CartAdapter adapter = new CartAdapter(items, () -> {
            totalText.setText("Total: $" + CartManager.getInstance().getTotalPrice());
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Set initial total
        totalText.setText("Total: $" + CartManager.getInstance().getTotalPrice());

        // Checkout button click
        checkoutButton.setOnClickListener(v -> {
            if (items.isEmpty()) {
                Toast.makeText(CartActivity.this, "Cart is empty!", Toast.LENGTH_SHORT).show();
            } else {
                // Save last order
                CartManager.getInstance().saveLastOrder();

                // Open Order Summary Activity
                Intent intent = new Intent(CartActivity.this, OrderSummaryActivity.class);
                startActivity(intent);

                // Clear cart
                CartManager.getInstance().clearCart();
                finish();
            }
        });

    }
}
