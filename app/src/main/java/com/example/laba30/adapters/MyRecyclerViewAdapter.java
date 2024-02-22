package com.example.laba30.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laba30.databinding.RecyclerViewItemBinding;

import java.util.ArrayList;
import java.util.List;


public class MyRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    RecyclerViewItemBinding binding;

    class ItemViewHolder extends RecyclerView.ViewHolder {
        private RecyclerViewItemBinding itemBinding;
        public ItemViewHolder(RecyclerViewItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }

        @SuppressLint("SetTextI18n")
        public void setItem(RecyclerViewItem item) {
            itemBinding.labWork.setText("Лабораторная работа №" + item.labNumber);

            String month = "" + item.month;
            if (month.length() < 2) {
                month = "0" + month;
            }
            String day = "" + item.day;
            if (day.length() < 2) {
                day = "0" + day;
            }
            String date = day + "." + month + "." + item.year;

            String text;
            if (item.accepted) {
                text = "Принята " + date;
            } else {
                text = "Будет принята " + date;
            }
            itemBinding.completeDate.setText(text);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = RecyclerViewItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ItemViewHolder) holder).setItem(((List<RecyclerViewItem>) differ.getCurrentList()).get(position));
        holder.setIsRecyclable(false);
    }

    @Override
    public int getItemCount() {
        return differ.getCurrentList().size();
    }

    private DiffUtil.ItemCallback<RecyclerViewItem> differCallback = new DiffUtil.ItemCallback<RecyclerViewItem>() {
        @Override
        public boolean areItemsTheSame(@NonNull RecyclerViewItem oldItem, @NonNull RecyclerViewItem newItem) {
            return oldItem.labNumber == newItem.labNumber;
        }

        @Override
        public boolean areContentsTheSame(@NonNull RecyclerViewItem oldItem, @NonNull RecyclerViewItem newItem) {
            return oldItem.accepted == newItem.accepted &&
                    oldItem.year == newItem.year &&
                    oldItem.month == newItem.month &&
                    oldItem.day == newItem.day;
        }
    };
    public AsyncListDiffer<RecyclerViewItem> differ = new AsyncListDiffer<>(this, differCallback);
}