package com.istiaq.food;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.istiaq.food.model.CartItem;

import java.util.List;

public class OrderSummaryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextView totalText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_summary);

        recyclerView = findViewById(R.id.summaryRecyclerView);
        totalText = findViewById(R.id.summaryTotalText);

        // Get the last ordered items from CartManager
        List<CartItem> orderedItems = CartManager.getInstance().getLastOrder();

        // Set RecyclerView adapter
        OrderSummaryAdapter adapter = new OrderSummaryAdapter(orderedItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Calculate and show total price
        double total = 0;
        for (CartItem item : orderedItems) {
            total += item.getPrice() * item.getQuantity();
        }
        totalText.setText("Total: $" + total);
    }
}
