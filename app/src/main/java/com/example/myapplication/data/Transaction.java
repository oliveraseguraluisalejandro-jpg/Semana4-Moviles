package com.example.myapplication.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "transactions")
public class Transaction {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private String type; // "Ingreso" o "Egreso"
    private double amount;
    private String category; // Ej: Comida, Transporte, Salario, etc.
    private String paymentMethod; // "Efectivo", "Tarjeta", "Yape"
    private long date; // timestamp

    public Transaction(String type, double amount, String category, String paymentMethod, long date) {
        this.type = type;
        this.amount = amount;
        this.category = category;
        this.paymentMethod = paymentMethod;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
