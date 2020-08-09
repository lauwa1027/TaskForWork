package com.example.demo.api;

import com.example.demo.model.Inventory;
import com.example.demo.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;

@Controller
public class InventoryController {

    private final InventoryService service;

    @Autowired
    public InventoryController(InventoryService service) {
        this.service = service;
    }

    //Upload page
    @GetMapping("upload")
    public String upload(){
        return "upload";
    }

    //Store CSV items to DB
    @PostMapping("uploadFile")
    @ResponseBody
    public String addProduct(MultipartFile file){
        String message;
        String line;

        // validate file
        if (file.isEmpty()) {
           message = "Please select a CSV file to upload.";
        } else {
            // parse CSV file
            message = "success";
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

                while((line = reader.readLine()) != null) {
                    String[] values = line.split(",");
                    Inventory product = new Inventory(values[1],values[0],Integer.parseInt(values[2]),values[3]);
                    service.addProduct(product);
                    System.out.println(product);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return message;
    }

    //get the products by product code
    @GetMapping("search")
    @ResponseBody
    public List<Inventory> getProductByCode(@RequestParam("code") String code){
        return service.getProductByCode(code);
    }

    @GetMapping("find")
    @ResponseBody
    public Optional<Inventory> getProduct(@RequestParam("code") String code, @RequestParam("location") String location){
        return service.getProduct(code, location);
    }

    @PutMapping("update")
    @ResponseBody
    public List<Inventory> updateInventory(@RequestParam MultiValueMap<String, String> req) {
        return service.updateInventory(req.get("startLocation").get(0),req.get("code").get(0),Integer.parseInt(req.get("number").get(0)),req.get("destination").get(0));
    }

}
