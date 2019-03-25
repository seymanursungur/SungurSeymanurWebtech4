package edu.ap.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import edu.ap.spring.WebApp;
import edu.ap.spring.BalanceRepository;

@Controller
public class WebAppController {

   @Autowired
   private BalanceRepository balanceRepository;

   @GetMapping("/")
   public String index() {
      return "redirect:/list";
   }

   @GetMapping("/{balance}/{walletA}")
   public String getDetail(@PathVariable("balance") int balance,
                           @PathVariable("walletA") String walletA,
                           Model model) {
      
      WebApp webApp = balanceRepository.findBywalletAAndwalletB(walletA, balance);
      model.addAttribute("balance", balance);

      return "detail";
   }

   @GetMapping("/balance")
   public String balance() {
      return "balance";
   }
   
   @GetMapping("/list")
   public String list(Model model) {
      
      model.addAttribute("balances", balanceRepository.findAll());

	   return "list";
   }

   @PostMapping("/balance")
   public String setbalance(@RequestParam("walletA") String walletA, 
                          @RequestParam("walletB") String walletB,
                          @RequestParam("balance") int balance) {
 
      balanceRepository.save(new WebApp(walletA, walletB, balance));
      
      return "redirect:/list";
   }
}
