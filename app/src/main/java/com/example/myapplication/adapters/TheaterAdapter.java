package com.example.myapplication.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.databinding.ItemTheaterBinding;
import com.example.myapplication.models.Theater;
import java.util.List;

public class TheaterAdapter extends RecyclerView.Adapter<TheaterAdapter.TheaterViewHolder> {

    private List<Theater> theaterList;
    private OnTheaterClickListener listener;

    public interface OnTheaterClickListener {
        void onTheaterClick(Theater theater);
    }

    public TheaterAdapter(List<Theater> theaterList, OnTheaterClickListener listener) {
        this.theaterList = theaterList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TheaterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTheaterBinding binding = ItemTheaterBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new TheaterViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TheaterViewHolder holder, int position) {
        Theater theater = theaterList.get(position);
        holder.binding.tvName.setText(theater.getName());
        holder.binding.tvAddress.setText(theater.getAddress());
        holder.binding.tvCity.setText(theater.getCity());

        holder.itemView.setOnClickListener(v -> listener.onTheaterClick(theater));
    }

    @Override
    public int getItemCount() {
        return theaterList.size();
    }

    public static class TheaterViewHolder extends RecyclerView.ViewHolder {
        ItemTheaterBinding binding;
        public TheaterViewHolder(ItemTheaterBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}