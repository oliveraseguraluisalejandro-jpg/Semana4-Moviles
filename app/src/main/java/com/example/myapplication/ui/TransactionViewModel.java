package com.example.myapplication.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.myapplication.data.Transaction;
import com.example.myapplication.data.TransactionRepository;

import java.util.List;

public class TransactionViewModel extends AndroidViewModel {

    private TransactionRepository repository;
    private LiveData<List<Transaction>> allTransactions;

    public TransactionViewModel(@NonNull Application application) {
        super(application);
        repository = new TransactionRepository(application);
        allTransactions = repository.getAllTransactions();
    }

    public LiveData<List<Transaction>> getAllTransactions() {
        return allTransactions;
    }

    public void insert(Transaction transaction) {
        repository.insert(transaction);
    }

    public void delete(Transaction transaction) {
        repository.delete(transaction);
    }
}
