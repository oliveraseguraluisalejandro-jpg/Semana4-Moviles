package com.example.myapplication.ui;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.data.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder> {

    private List<Transaction> transactions = new ArrayList<>();

    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_transaction, parent, false);
        return new TransactionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {
        Transaction current = transactions.get(position);
        holder.tvCategory.setText(current.getCategory());
        holder.tvPaymentMethod.setText(current.getPaymentMethod());

        String prefix = current.getType().equals("Ingreso") ? "+ S/. " : "- S/. ";
        holder.tvAmount.setText(prefix + String.format("%.2f", current.getAmount()));

        if (current.getType().equals("Ingreso")) {
            holder.tvAmount.setTextColor(Color.parseColor("#4CAF50")); // Green
        } else {
            holder.tvAmount.setTextColor(Color.parseColor("#F44336")); // Red
        }
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
        notifyDataSetChanged();
    }

    static class TransactionViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvCategory;
        private final TextView tvPaymentMethod;
        private final TextView tvAmount;

        public TransactionViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCategory = itemView.findViewById(R.id.tv_category);
            tvPaymentMethod = itemView.findViewById(R.id.tv_payment_method);
            tvAmount = itemView.findViewById(R.id.tv_amount);
        }
    }
}
