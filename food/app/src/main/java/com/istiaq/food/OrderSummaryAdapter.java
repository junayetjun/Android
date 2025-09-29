package com.istiaq.food;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.istiaq.food.model.CartItem;

import java.util.List;

public class OrderSummaryAdapter extends RecyclerView.Adapter<OrderSummaryAdapter.SummaryViewHolder> {

    private final List<CartItem> orderedItems;

    public OrderSummaryAdapter(List<CartItem> orderedItems) {
        this.orderedItems = orderedItems;
    }

    @NonNull
    @Override
    public SummaryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new SummaryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SummaryViewHolder holder, int position) {
        CartItem item = orderedItems.get(position);
        holder.name.setText(item.getName());
        holder.quantity.setText("Qty: " + item.getQuantity());
        holder.price.setText("$" + (item.getPrice() * item.getQuantity()));
        holder.increaseBtn.setVisibility(View.GONE); // Hide buttons in summary
        holder.decreaseBtn.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return orderedItems.size();
    }

    static class SummaryViewHolder extends RecyclerView.ViewHolder {
        TextView name, quantity, price;
        View increaseBtn, decreaseBtn;

        public SummaryViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.itemName);
            quantity = itemView.findViewById(R.id.itemQty);
            price = itemView.findViewById(R.id.itemPrice);
            increaseBtn = itemView.findViewById(R.id.increaseBtn);
            decreaseBtn = itemView.findViewById(R.id.decreaseBtn);
        }
    }
}
