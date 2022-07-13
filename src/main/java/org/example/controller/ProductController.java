package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.models.BucketDetails;
import org.example.models.Info;
import org.example.models.Product;
import org.example.service.BucketDetailsService;
import org.example.service.InfoService;
import org.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
public class ProductController {

    private final InfoService infoService;

    private final ProductService productService;

    private final BucketDetailsService bucketDetailsService;


    @Autowired
    public ProductController(InfoService infoService,
                             ProductService productService,
                             BucketDetailsService bucketDetailsService) {
        this.infoService = infoService;
        this.productService = productService;
        this.bucketDetailsService = bucketDetailsService;
    }

    private String getHeader() {
        return infoService.getAllInfo().get(0).getName();
    }

    private String getFooter() {

        List<Info> infos = infoService.getAllInfo();
        Info info = infos.get(0);

        return info.getCopyright() + " " + info.getPeriod() + " " + info.getName();
    }


    @GetMapping()
    public String showAllProducts(Model model) {

        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);

        String header = getHeader();
        String footer = getFooter();
        model.addAttribute("mytitle", header);
        model.addAttribute("myfooter", footer);

        return "products-All-for-Customers";
    }

    @GetMapping("/edit")
    public String showAllProductsForAdmins(Model model) {

        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);

        String header = getHeader();
        String footer = getFooter();
        model.addAttribute("mytitle", header);
        model.addAttribute("myfooter", footer);

        return "products-All-for-Admins";
    }



    @GetMapping("/addNewProduct")
    public String addNewProduct(Model model) {

        Product product = new Product();
        model.addAttribute("product", product);

        String header = getHeader();
        String footer = getFooter();
        model.addAttribute("mytitle", header);
        model.addAttribute("myfooter", footer);

        return "product-Details";

    }

    @PostMapping("/saveProduct")
    public String saveProduct(@ModelAttribute("product") Product product) {

        productService.saveProduct(product);

        return "redirect:/edit";
    }

    @GetMapping("/updateProduct")
    public String updateProduct(@RequestParam("productId") int productId,
                                Model model) {

        Product product = productService.getProductById(productId);
        model.addAttribute("product", product);

        String header = getHeader();
        String footer = getFooter();
        model.addAttribute("mytitle", header);
        model.addAttribute("myfooter", footer);

        return "product-Details";
    }

    @GetMapping("/deleteProduct")
    public String deleteProduct(@RequestParam("productId") int productId) {
        productService.deleteProductById(productId);
        return "redirect:/edit";
    }

    @GetMapping("/addProductToBucket")
    public String addProductToBucket(@RequestParam("productId") int productId) {

        BucketDetails bucketDetails = bucketDetailsService.getBucketDetailsByProductId(productId);

        if (bucketDetails != null) {

            int amount = bucketDetails.getAmount();
            bucketDetails.setAmount(++amount);

            bucketDetailsService.saveBucketDetails(bucketDetails);
        } else {

            Product product = productService.getProductById(productId);

            BucketDetails newBucketDetails = BucketDetails.builder()
                    .product(product)
                    .amount(1)
                    .build();

            bucketDetailsService.saveBucketDetails(newBucketDetails);
        }

        return "redirect:/";
    }

    @GetMapping("/deleteProductFromBucket")
    public String deleteProductFromBucket(@RequestParam("productId") int productId) {

        BucketDetails bucketDetails = bucketDetailsService.getBucketDetailsByProductId(productId);

        if (bucketDetails != null) {

            int amount = bucketDetails.getAmount();
            int newAmount = --amount;

            if (newAmount < 1) {
                bucketDetailsService.deleteBucketDetailsById(bucketDetails.getId());
            } else {
                bucketDetails.setAmount(newAmount);

                bucketDetailsService.saveBucketDetails(bucketDetails);
            }
        }

        return "redirect:/";
    }

}
