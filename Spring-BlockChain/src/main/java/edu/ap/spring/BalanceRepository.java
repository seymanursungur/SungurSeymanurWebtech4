package edu.ap.spring;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BalanceRepository extends CrudRepository<WebApp, Long> { 

    public WebApp findBywalletAAndwalletB(String walletA, int balance);
}
