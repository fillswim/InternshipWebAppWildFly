package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.dto.ProductDTO;
import org.example.mappers.ProductMapper;
import org.example.models.BucketDetails;
import org.example.models.Info;
import org.example.models.Manufacturer;
import org.example.models.Product;
import org.example.service.BucketDetailsService;
import org.example.service.InfoService;
import org.example.service.ManufacturerService;
import org.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
public class ProductController {

    private final InfoService infoService;

    private final ProductService productService;

    private final BucketDetailsService bucketDetailsService;

    private final ProductMapper productMapper;

    private final ManufacturerService manufacturerService;


    @Autowired
    public ProductController(InfoService infoService,
                             ProductService productService,
                             BucketDetailsService bucketDetailsService,
                             ProductMapper productMapper,
                             ManufacturerService manufacturerService) {
        this.infoService = infoService;
        this.productService = productService;
        this.bucketDetailsService = bucketDetailsService;
        this.productMapper = productMapper;
        this.manufacturerService = manufacturerService;
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

        List<ProductDTO> productDTOS = products.stream()
                .map(productMapper::mapToProductDto)
                .collect(Collectors.toList());

        model.addAttribute("products", productDTOS);

        String header = getHeader();
        String footer = getFooter();
        model.addAttribute("mytitle", header);
        model.addAttribute("myfooter", footer);

        return "products-All-for-Customers";
    }

    @GetMapping("/edit")
    public String showAllProductsForAdmins(Model model) {

        List<Product> products = productService.getAllProducts();

        List<ProductDTO> productDTOS = products.stream()
                .map(productMapper::mapToProductDto)
                .collect(Collectors.toList());

        model.addAttribute("products", productDTOS);

        String header = getHeader();
        String footer = getFooter();
        model.addAttribute("mytitle", header);
        model.addAttribute("myfooter", footer);

        return "products-All-for-Admins";
    }



    @GetMapping("/addNewProduct")
    public String addNewProduct(Model model) {

        ProductDTO productDTO = new ProductDTO();
        model.addAttribute("product", productDTO);

        String header = getHeader();
        String footer = getFooter();
        model.addAttribute("mytitle", header);
        model.addAttribute("myfooter", footer);

        return "product-Details";

    }

    @PostMapping("/saveProduct")
    public String saveProduct(@ModelAttribute("product") ProductDTO productDTO) {

        Product product = productService.getProductById(productDTO.getId());

        if ((productDTO.getTitle() != null) && (!productDTO.getTitle().equals(product.getTitle()))) {
            product.setTitle(productDTO.getTitle());
        }

        if ((productDTO.getPrice() != null) && (productDTO.getPrice() != product.getPrice())) {
            product.setPrice(productDTO.getPrice());
        }

        if ((productDTO.getManufacturerId() != 0)
                && (productDTO.getManufacturerId() != product.getManufacturer().getId())) {

            Manufacturer manufacturer = manufacturerService.getManufacturerById(productDTO.getManufacturerId());
            product.setManufacturer(manufacturer);
        }

        productService.saveProduct(product);

        return "redirect:/edit";
    }

    @GetMapping("/updateProduct")
    public String updateProduct(@RequestParam("productId") int productId,
                                Model model) {

        Product product = productService.getProductById(productId);
        ProductDTO productDTO = productMapper.mapToProductDto(product);
        model.addAttribute("product", productDTO);

        List<Manufacturer> manufacturers = manufacturerService.getAllManufacturer();
        model.addAttribute("manufacturers", manufacturers);

        String header = getHeader();
        String footer = getFooter();
        model.addAttribute("mytitle", header);
        model.addAttribute("myfooter", footer);

        return "product-Details";
    }

    @GetMapping("/deleteProduct")
    public String deleteProduct(@RequestParam("productId") int productId) {

        Product product = productService.getProductById(productId);
        productService.deleteProduct(product);

        return "redirect:/edit";
    }

    @GetMapping("/addProductToBucket")
    public String addProductToBucket(@RequestParam("productId") int productId) {

        Product product = productService.getProductById(productId);
        BucketDetails bucketDetails = bucketDetailsService.getBucketDetailsByProduct(product);

        if (bucketDetails != null) {

            int amount = bucketDetails.getAmount();
            bucketDetails.setAmount(++amount);

            bucketDetailsService.saveBucketDetails(bucketDetails);
        } else {

            Product newProduct = productService.getProductById(productId);

            BucketDetails newBucketDetails = BucketDetails.builder()
                    .product(newProduct)
                    .amount(1)
                    .build();

            bucketDetailsService.saveBucketDetails(newBucketDetails);
        }

        return "redirect:/";
    }

    @GetMapping("/deleteProductFromBucket")
    public String deleteProductFromBucket(@RequestParam("productId") int productId) {

        Product product = productService.getProductById(productId);
        BucketDetails bucketDetails = bucketDetailsService.getBucketDetailsByProduct(product);

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
