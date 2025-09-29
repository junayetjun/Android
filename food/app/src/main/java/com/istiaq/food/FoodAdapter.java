package com.istiaq.food;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.istiaq.food.model.Food;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Food food);
    }

    private final List<Food> foodList;
    private final OnItemClickListener listener;

    // Constructor with click listener
    public FoodAdapter(List<Food> foodList, OnItemClickListener listener) {
        this.foodList = foodList;
        this.listener = listener;
    }

    public static class FoodViewHolder extends RecyclerView.ViewHolder {
        TextView foodName, foodPrice, foodDesc;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            foodName = itemView.findViewById(R.id.foodName);
            foodPrice = itemView.findViewById(R.id.foodPrice);
            foodDesc = itemView.findViewById(R.id.foodDesc);
        }

        public void bind(final Food food, final OnItemClickListener listener) {
            foodName.setText(food.getName());
            foodPrice.setText("$" + food.getPrice());
            foodDesc.setText(food.getDescription());

            itemView.setOnClickListener(v -> listener.onItemClick(food));
        }
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_item_food, parent, false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        holder.bind(foodList.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }
}
