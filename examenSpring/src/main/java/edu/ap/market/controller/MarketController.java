package edu.ap.market.controller;

import edu.ap.market.model.Product;
import edu.ap.market.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

@Controller
@Scope("session")
public class MarketController {

    private RedisService service; // pattern : "products":name:weight:amount

    @Autowired
    public void setRedisService(RedisService service) {
        this.service = service;
    }

    public MarketController() { }


    @RequestMapping("/")
    public String root() {
        return "redirect:/addProductForm";
    }

    @GetMapping("/addProductForm")
    public String addProductForm() {
        return "addProduct";
    }

    @GetMapping("/searchByNameForm")
    public String searchByNameForm() {
        return "searchByName";
    }

    @GetMapping("/searchByWeightForm")
    public String searchByWeightForm() {
        return "searchByWeight";
    }



    @RequestMapping("/listByName")
    public String listProductsByName(@RequestParam("name") String name,
                                       Model model) {
        Product product = null;
        Set<String> keys = service.keys("products:*");

        for (String key : keys) {
            String[] parts = key.split(":");

            if (parts[1].equalsIgnoreCase(name)) {
                product = new Product(parts[1], Integer.valueOf(parts[2]), Integer.valueOf(parts[3]));
            }
        }
        model.addAttribute("product", product);

        return "product";
    }


    @PostMapping("/listByWeight")
    public String listProductsByWeight(@RequestParam("minWeight") int minWeight,
                                       @RequestParam("maxWeight") int maxWeight,
                                       Model model) {

        List<Product> products = new ArrayList<>();
        Set<String> keys = service.keys("products:*");

        for (String key : keys) {
            String[] parts = key.split(":");
            int weight = Integer.valueOf(parts[2]);
            int amount = Integer.valueOf(parts[3]);

            if (minWeight <= weight && weight <= maxWeight) {
                products.add(new Product(parts[1], weight, amount));
            }
        }
        model.addAttribute("products", products);

        return "products";
    }


    @RequestMapping("/listAll")
    public String listAllProducts(Model model) {
        List<Product> products = new ArrayList<>();
        Set<String> keys = service.keys("products:*");

        for (String key : keys) {
            String[] parts = key.split(":");
            products.add(new Product(parts[1], Integer.valueOf(parts[2]), Integer.valueOf(parts[3])));
        }
        products.sort(Comparator.comparing(Product::getName));
        model.addAttribute("products", products);

        return "products";
    }


    @PostMapping("/newProduct")
    @ResponseBody
    public String addProduct(@RequestParam("name") String name,
                             @RequestParam("weight") int weight,
                             @RequestParam("amount") int amount) {

        String pattern = "products:" + name + ":" + weight + ":" + amount;
        Set<String> keys = service.keys(pattern);

        if (keys.size() == 0) {
            service.setKey(String.format("products:%s:%s:%s", name, weight, amount), "");
            return "Product added";
        } else {
            return "Product already exists!";
        }
    }

}
