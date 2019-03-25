package edu.ap.spring;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class WebApp {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String walletA;
    @Column
    private String walletB;
    @Column
    private int balance;

    public WebApp() {}

    public WebApp(String walletA, String walletB, int balance) {
        this.walletA = walletA;
        this.walletB = walletB;
        this.balance = balance;
    }

    public String getWalletA() {
        return walletA;
    }

    public void setWalletA(String walletA) {
        this.walletA = walletA;
    }

    public String getWalletB() {
        return walletB;
    }

    public void setWalletB(String walletB) {
        this.walletB = walletB;
    }

    public int getbalance() {
        return balance;
    }

    public void setbalance(int balance) {
        this.balance = balance;
    }

}
