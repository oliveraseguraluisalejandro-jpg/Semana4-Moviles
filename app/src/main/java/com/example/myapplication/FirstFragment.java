package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myapplication.data.Transaction;
import com.example.myapplication.databinding.FragmentFirstBinding;
import com.example.myapplication.ui.TransactionAdapter;
import com.example.myapplication.ui.TransactionViewModel;

import java.util.List;
import java.util.Locale;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private TransactionViewModel viewModel;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(TransactionViewModel.class);

        TransactionAdapter adapter = new TransactionAdapter();
        binding.recyclerTransactions.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerTransactions.setAdapter(adapter);

        viewModel.getAllTransactions().observe(getViewLifecycleOwner(), transactions -> {
            adapter.setTransactions(transactions);

            double balance = 0.0;
            for (Transaction t : transactions) {
                if ("Ingreso".equals(t.getType())) {
                    balance += t.getAmount();
                } else {
                    balance -= t.getAmount();
                }
            }
            binding.tvBalanceAmount.setText(String.format(Locale.getDefault(), "S/. %.2f", balance));
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
