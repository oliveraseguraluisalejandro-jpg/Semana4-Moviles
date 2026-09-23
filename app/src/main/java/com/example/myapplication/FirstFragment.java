package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myapplication.data.Transaction;
import com.example.myapplication.databinding.FragmentFirstBinding;
import com.example.myapplication.ui.TransactionAdapter;
import com.example.myapplication.ui.TransactionViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private TransactionViewModel viewModel;
    private TransactionAdapter adapter;

    private final Calendar selectedCalendar = Calendar.getInstance();
    private List<Transaction> allTransactionsList = new ArrayList<>();
    private final SimpleDateFormat monthYearFormat = new SimpleDateFormat("MMMM yyyy", Locale.forLanguageTag("es-ES"));

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

        adapter = new TransactionAdapter();
        binding.recyclerTransactions.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerTransactions.setAdapter(adapter);

        binding.btnPrevMonth.setOnClickListener(v -> {
            selectedCalendar.add(Calendar.MONTH, -1);
            updateUI();
        });

        binding.btnNextMonth.setOnClickListener(v -> {
            selectedCalendar.add(Calendar.MONTH, 1);
            updateUI();
        });

        viewModel.getAllTransactions().observe(getViewLifecycleOwner(), transactions -> {
            if (transactions != null) {
                allTransactionsList = transactions;
                adapter.setTransactions(transactions);
                updateUI();
            }
        });
    }

    private void updateUI() {
        if (binding == null) return;

        double overallBalance = 0.0;
        for (Transaction t : allTransactionsList) {
            if ("Ingreso".equals(t.getType())) {
                overallBalance += t.getAmount();
            } else {
                overallBalance -= t.getAmount();
            }
        }
        binding.tvBalanceAmount.setText(String.format(Locale.getDefault(), "S/. %.2f", overallBalance));

        String monthFormatted = monthYearFormat.format(selectedCalendar.getTime());
        if (!monthFormatted.isEmpty()) {
            monthFormatted = monthFormatted.substring(0, 1).toUpperCase(Locale.getDefault()) + monthFormatted.substring(1);
        }
        binding.tvCurrentMonth.setText(monthFormatted);

        int selectedYear = selectedCalendar.get(Calendar.YEAR);
        int selectedMonth = selectedCalendar.get(Calendar.MONTH);

        Calendar transCal = Calendar.getInstance();
        double monthlyIncome = 0.0;
        double monthlyExpense = 0.0;

        for (Transaction t : allTransactionsList) {
            transCal.setTimeInMillis(t.getDate());
            if (transCal.get(Calendar.YEAR) == selectedYear && transCal.get(Calendar.MONTH) == selectedMonth) {
                if ("Ingreso".equals(t.getType())) {
                    monthlyIncome += t.getAmount();
                } else if ("Egreso".equals(t.getType())) {
                    monthlyExpense += t.getAmount();
                }
            }
        }

        binding.monthlyChartView.setData(monthlyIncome, monthlyExpense);
        binding.tvMonthlyIncome.setText(String.format(Locale.getDefault(), "+ S/. %.2f", monthlyIncome));
        binding.tvMonthlyExpense.setText(String.format(Locale.getDefault(), "- S/. %.2f", monthlyExpense));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
