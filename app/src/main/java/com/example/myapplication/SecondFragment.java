package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.myapplication.data.Transaction;
import com.example.myapplication.databinding.FragmentSecondBinding;
import com.example.myapplication.ui.TransactionViewModel;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;
    private TransactionViewModel viewModel;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(TransactionViewModel.class);

        binding.btnSave.setOnClickListener(v -> saveTransaction());
    }

    private void saveTransaction() {
        String amountStr = binding.etAmount.getText() != null ? binding.etAmount.getText().toString().trim() : "";
        String categoryStr = binding.etCategory.getText() != null ? binding.etCategory.getText().toString().trim() : "";
        String typeStr = binding.spinnerType.getSelectedItem().toString();
        String paymentMethodStr = binding.spinnerPaymentMethod.getSelectedItem().toString();

        if (amountStr.isEmpty()) {
            binding.etAmount.setError("Ingresa una cantidad");
            return;
        }

        double amount;
        try {
            amount = Double.parseDouble(amountStr);
        } catch (NumberFormatException e) {
            binding.etAmount.setError("Cantidad inválida");
            return;
        }

        if (categoryStr.isEmpty()) {
            categoryStr = "General";
        }

        Transaction transaction = new Transaction(typeStr, amount, categoryStr, paymentMethodStr, System.currentTimeMillis());
        viewModel.insert(transaction);

        Toast.makeText(getContext(), "Movimiento guardado con éxito", Toast.LENGTH_SHORT).show();

        NavHostFragment.findNavController(SecondFragment.this)
                .navigate(R.id.action_SecondFragment_to_FirstFragment);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
