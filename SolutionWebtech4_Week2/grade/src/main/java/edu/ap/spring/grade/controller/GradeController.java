package edu.ap.spring.grade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import edu.ap.spring.grade.jpa.Grade;
import edu.ap.spring.grade.jpa.GradeRepository;

@Controller
public class GradeController {

   @Autowired
   private GradeRepository gradeRepository;

   @GetMapping("/")
   public String index() {
      return "redirect:/list";
   }

   @GetMapping("/{walletA}/{walletB}")
   public String getDetail(@PathVariable("walletA") String walletA,
                           @PathVariable("walletB") String walletB,
                           Model model) {
      
      Grade grade = gradeRepository.findByWalletAAndWalletB(walletA, walletB);
      model.addAttribute("amount", amount);

      return "detail";
   }

   @GetMapping("/grade")
   public String grade() {
      return "grade";
   }
   
   @GetMapping("/list")
   public String list(Model model) {
      
      model.addAttribute("grades", gradeRepository.findAll());

	   return "list";
   }

   @PostMapping("/grade")
   public String setGrade(@RequestParam("walletA") String walletA, 
                          @RequestParam("walletB") String walletB,
                          @RequestParam("amount") int amount) {
 
      gradeRepository.save(new Grade(walletA, walletB, amount));
      
      return "redirect:/list";
   }
}