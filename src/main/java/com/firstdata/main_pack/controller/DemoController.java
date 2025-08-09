package com.firstdata.main_pack.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import com.fasterxml.jackson.databind.JsonNode;
import com.firstdata.main_pack.model.Account;
import com.firstdata.main_pack.model.Product;
import com.firstdata.main_pack.service.AccountService;
import com.firstdata.main_pack.service.ExternalApiService;
import com.firstdata.main_pack.service.MailService;

@RestController
@Validated
// @RequestMapping("/api/demo/")
public class DemoController {
    @Autowired
    MailService mailService;
    @Autowired
    ExternalApiService externalApiService;

    @Autowired
    private SpringTemplateEngine templateEngine; // Inject Thymeleaf's template engine

    @GetMapping("/demo/hello")
    public ResponseEntity<String> displayHelloMe(@RequestParam String name){
        String aString = "Hello " + name;
        return new ResponseEntity<>(aString, HttpStatus.OK);
    }

    @GetMapping("/demo/hello2")
    public ResponseEntity<String> displayHelloMeInPage(@RequestParam String name){
        String aString = "Hello " + name;
        return new ResponseEntity<>(aString, HttpStatus.OK);
    }

    @PostMapping("/api/form/fill")
    public ResponseEntity<List> fillTheForm(
        @Valid @RequestBody Account accountParams,
        BindingResult bindingResult
    ){
        if (bindingResult.hasErrors()) {
            List<String> errors = new ArrayList<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.add(error.getDefaultMessage());
            }
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/api/account/sendEmail")
    public ResponseEntity<String> sendEmail(
        @RequestParam String receiverEmail,
        @RequestParam String subject,
        @RequestParam String text
    ){
        String result = mailService.sendEmail(receiverEmail, subject, text);
        return new ResponseEntity<String>(result, HttpStatus.OK);
    }

    @Autowired
    AccountService accountService;

    @GetMapping("/api/account/orders")
    public ResponseEntity<List<String>> getOrders(
        @RequestParam String userId
    ){
        List<String> orders = accountService.getOrders(userId);
        System.out.println(orders);
        return new ResponseEntity<List<String>>(orders, HttpStatus.OK);
    }

    @PostMapping("/api/account/signUp")
    public ResponseEntity<Boolean> signUpAccount(
        @RequestParam String emailAddress
    ){
        Boolean isValidEmail = accountService.validEmailFormat(emailAddress);
        return new ResponseEntity<Boolean>(isValidEmail, HttpStatus.OK);
    }

    @GetMapping("/products")
    public ResponseEntity getAllProducts(@RequestHeader Map<String, String> headers){
        System.out.println(headers);

        return new ResponseEntity<>(headers, HttpStatus.OK);
    }

    @GetMapping("/product/detail")
    public ResponseEntity getProductById(
        @RequestParam String id
    ){
        System.out.println("Id value: " + id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PostMapping("/product/detail")
    public ResponseEntity createNewProduct(
        @RequestParam String name,
        @RequestParam(required = false) String price,
        @RequestParam(defaultValue = "yellow") String color
    ){
        System.out.println("Name value: " + name);
        System.out.println("Price value: " + price);
        System.out.println("Color value: " + color);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/product/detail/body")
    public ResponseEntity createNewProductFromBodyParam(
        @RequestBody Object productDetail
    ){
        System.out.println("Body data: " + productDetail.toString());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/category/detail")
    public ResponseEntity updateCategory(
        @RequestParam String name,
        @RequestParam(required = false) String price,
        @RequestParam(defaultValue = "red") String color
    ){
        System.out.println("Name value: " + name);
        System.out.println("Price value: " + price);
        System.out.println("Color value: " + color);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/category/detail/body")
    public ResponseEntity updateCategoryFromBody(
        @RequestBody Object productDetail
    ){
        System.out.println("Body data: " + productDetail.toString());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/order/detail")
    public ResponseEntity deleteOrder(
        @RequestParam String id
    ){
        System.out.println("Id value: " + id);

        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @DeleteMapping("/orders/detail/body")
    public ResponseEntity deleteOrdersFromBody(
        @RequestBody Object ids
    ){
        System.out.println("Body data: " + ids.toString());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // @PostMapping("/api/account/signUp")
    // public ResponseEntity signUpAccount(
    //     @RequestParam String emailAddress
    // ){
    //     Boolean isValidEmail = accountService.validEmailFormat(emailAddress);
    //     return new ResponseEntity<>(isValidEmail, HttpStatus.OK);
    // }

    @GetMapping("/product/{id}/detail")
    public ResponseEntity<String> getProductByIdFromPath(
        @PathVariable String id
    ){
        System.out.println("Id value: " + id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PostMapping("/product/upload_image")
    public ResponseEntity<String> uploadProductImage(
        @RequestParam("file") MultipartFile file
    ){
        final String UPLOAD_DIR = "src/main/resources/static/uploads/"; // Or /public/, /resources/, etc.
        if (file.isEmpty()) {
            return new ResponseEntity<>("Missing file", HttpStatus.BAD_REQUEST);
        }

        try {
            // Create the directory if it doesn't exist
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Save the file
            Path filePath = uploadPath.resolve(file.getOriginalFilename());
            Files.copy(file.getInputStream(), filePath);

            return new ResponseEntity<>(filePath.toUri().toString(), HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/product/list")
    public ResponseEntity<JsonNode> getProductList() {
        try {
            JsonNode data = externalApiService.fetchDataFromExternalApi("https://dummyjson.com/products");
            return new ResponseEntity<>(data, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/product/detail_page", produces = MediaType.TEXT_HTML_VALUE)
    public String showProductDetails() {
        Context context = new Context();    //thymeleaf context package
        context.setVariable("productName", "Marshall speaker 111");
        return templateEngine.process("product_management/product_detail", context);
    }

    @GetMapping(value = "/product/list_view", produces = MediaType.TEXT_HTML_VALUE)
    public String showListView() {
        Context context = new Context();    //thymeleaf context package
        //create a list
        List<String> aList = new ArrayList<>();
        aList.add("Superior Sound Quality");
        aList.add("Long Battery Life");
        aList.add("Bluetooth 5.0 Connectivity");
        aList.add("Portable Design");
        context.setVariable("myList", aList);
        //
        return templateEngine.process("product_management/list", context);
    }

    @GetMapping(value = "/product/list_view_2", produces = MediaType.TEXT_HTML_VALUE)
    public String showListView2() {
        Context context = new Context();    //thymeleaf context package
        //create a list
        List<Product> aList = new ArrayList<>();
        aList.add(new Product(1, "Paper HP", 67.9));
        aList.add(new Product(2, "Monitor Dell", 73.8));
        aList.add(new Product(3, "Keyboard Mac", 122.0));
        context.setVariable("productList", aList);
        //
        return templateEngine.process("product_management/list_2", context);
    }
    @GetMapping(value = "/product/add_product_view", produces = MediaType.TEXT_HTML_VALUE)
    public String showProductAddPage() {
        Context context = new Context();    //thymeleaf context package
        return templateEngine.process("product_management/add_product", context);
    }

    @PostMapping(value = "/product/add_product")
    public ResponseEntity<String> processAddData(@RequestBody Object dataParams) {
        System.out.println(dataParams);
        return new ResponseEntity<>("Data is received", HttpStatus.OK);
    }

    @GetMapping(value = "/home", produces = MediaType.TEXT_HTML_VALUE)
    public String showHomepageOrgani() {
        Context context = new Context();    //thymeleaf context package
        return templateEngine.process("ogani-master/index", context);
    }

    @GetMapping(value = "/shopping-cart", produces = MediaType.TEXT_HTML_VALUE)
    public String showShoppingCart() {
        Context context = new Context();    //thymeleaf context package
        return templateEngine.process("ogani-master/shoping-cart", context);
    }

    public List<HashMap<String, Object>> getData(){
        List<HashMap<String, Object>> EMPLOYERS = new ArrayList<>();
        //Employee 1
        HashMap<String, Object> employee1 = new HashMap<>();
        employee1.put("name", "Alice");
        employee1.put("email", "alice.smith@example.com");
        employee1.put("department", "HR");
        employee1.put("salary", 4300);
        EMPLOYERS.add(employee1);
        //Employee 2
        HashMap<String, Object> employee2 = new HashMap<>();
        employee2.put("name", "Johnson");
        employee2.put("email", "js.me@gmail.com");
        employee2.put("department", "HR");
        employee2.put("salary", 5000);
        EMPLOYERS.add(employee2);
        //Employee 3
        HashMap<String, Object> employee3 = new HashMap<>();
        employee3.put("name", "Bob");
        employee3.put("email", "bod.dylan@dylan.com");
        employee3.put("department", "IT");
        employee3.put("salary", 3000);
        EMPLOYERS.add(employee3);
        return EMPLOYERS;
    }
    @GetMapping("/api/search_employees")
    public ResponseEntity<List<HashMap<String, Object>>> searchEmployees(@RequestParam String keyword){
        List<HashMap<String, Object>> employees = getData();
        List<HashMap<String, Object>> results = new ArrayList<>();
        //todo: các bạn hiện thực để tìm dữ liệu trong data và trả về kết quả
        for (HashMap<String, Object> employee : employees){
            if (String.valueOf(employee.get("name")).toLowerCase().contains(keyword.toLowerCase())){
                results.add(employee);
            }
        }

        //
        return new ResponseEntity<List<HashMap<String, Object>>>(results, HttpStatus.OK);
    }

    @PutMapping("/api/employee/update")
    public ResponseEntity<HashMap<String, Object>> changeEmployeeInfo(@RequestBody Object newData){
        List<HashMap<String, Object>> employees = getData();
        //todo
        //
        return new ResponseEntity<HashMap<String, Object>>(employees.get(0), HttpStatus.OK);
    }

}
