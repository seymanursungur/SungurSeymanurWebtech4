package edu.ap.spring.grade.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class Product {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String walletA;
    @Column
    private String walletB;
    @Column
    private int amount;

    public Product() {}

    public Product(String walletA, String walletB, int amount) {
        this.walletA = walletA;
        this.walletB = walletB;
        this.amount = amount;
    }

    public String getWalletA() {
        return walletA;
    }

    public void setWalletA(String walletA) {
        this.walletA = walletA;
    }

    public int getWalletB() {
        return walletB;
    }

    public void setWalletB(int walletB) {
        this.walletB = walletB;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

}
