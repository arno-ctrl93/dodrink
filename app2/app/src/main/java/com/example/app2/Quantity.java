package com.example.app2;

import java.io.Serializable;

public class Quantity implements Serializable {
    public int quantity;
    public whichunity whichunity;

    public Quantity(int quantity, com.example.app2.whichunity whichunity) {
        this.quantity = quantity;
        this.whichunity = whichunity;
    }
}
