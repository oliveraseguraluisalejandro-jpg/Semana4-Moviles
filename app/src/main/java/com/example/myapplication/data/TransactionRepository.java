package com.example.myapplication.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TransactionRepository {

    private TransactionDao transactionDao;
    private LiveData<List<Transaction>> allTransactions;

    public TransactionRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        transactionDao = db.transactionDao();
        allTransactions = transactionDao.getAllTransactions();
    }

    public LiveData<List<Transaction>> getAllTransactions() {
        return allTransactions;
    }

    public void insert(Transaction transaction) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            transactionDao.insert(transaction);
        });
    }

    public void delete(Transaction transaction) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            transactionDao.delete(transaction);
        });
    }
}
