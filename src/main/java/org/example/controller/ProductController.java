package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.dto.InfoDTO;
import org.example.dto.ManufacturerDTO;
import org.example.dto.ProductDTO;
import org.example.mappers.ProductMapper;
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

import java.security.Principal;
import java.util.List;

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

    @GetMapping()
    public String showAllProducts(Model model) {

        List<ProductDTO> productDTOS = productService.getAllProductsDTOS();
        model.addAttribute("products", productDTOS);

        InfoDTO infoDTO = infoService.getInfoDTOBuId(0);
        model.addAttribute("infoDTO", infoDTO);

        return "products-All-for-Customers";
    }

    @GetMapping("/edit")
    public String showAllProductsForAdmins(Model model) {

        List<ProductDTO> productDTOS = productService.getAllProductsDTOS();
        model.addAttribute("products", productDTOS);

        InfoDTO infoDTO = infoService.getInfoDTOBuId(0);
        model.addAttribute("infoDTO", infoDTO);

        return "products-All-for-Admins";
    }

    @GetMapping("/addNewProduct")
    public String addNewProduct(Model model) {

        ProductDTO productDTO = new ProductDTO();
        model.addAttribute("product", productDTO);

        List<ManufacturerDTO> manufacturerDTOS = manufacturerService.getAllManufacturerDTOS();
        model.addAttribute("manufacturers", manufacturerDTOS);

        InfoDTO infoDTO = infoService.getInfoDTOBuId(0);
        model.addAttribute("infoDTO", infoDTO);

        return "product-Details";

    }

    @PostMapping("/saveProduct")
    public String saveProduct(@ModelAttribute("product") ProductDTO productDTO) {

        int productDTOId = productDTO.getId();

        if (productDTOId != 0) {
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
        } else {

            int manufacturerId = productDTO.getManufacturerId();
            Manufacturer manufacturer = manufacturerService.getManufacturerById(manufacturerId);

            Product product = Product.builder()
                    .title(productDTO.getTitle())
                    .price(productDTO.getPrice())
                    .manufacturer(manufacturer)
                    .build();

            productService.saveProduct(product);

        }

        return "redirect:/edit";
    }

    @GetMapping("/updateProduct")
    public String updateProduct(@RequestParam("productId") int productId,
                                Model model) {

        ProductDTO productDTO = productService.getProductDTOById(productId);
        model.addAttribute("product", productDTO);

        List<ManufacturerDTO> manufacturerDTOS = manufacturerService.getAllManufacturerDTOS();
        model.addAttribute("manufacturers", manufacturerDTOS);

        InfoDTO infoDTO = infoService.getInfoDTOBuId(0);
        model.addAttribute("infoDTO", infoDTO);

        return "product-Details";
    }

    @GetMapping("/deleteProduct")
    public String deleteProduct(@RequestParam("productId") int productId) {

        Product product = productService.getProductById(productId);
        productService.deleteProduct(product);

        return "redirect:/edit";
    }

    @GetMapping("/addProductToBucket")
    public String addProductToBucket(@RequestParam("productId") int productId,
                                     Principal principal) {

        String username = principal.getName();
        productService.addProductToBucket(productId, username);

        return "redirect:/";
    }

    @GetMapping("/deleteProductFromBucket")
    public String deleteProductFromBucket(@RequestParam("productId") int productId,
                                          Principal principal) {

        String username = principal.getName();
        productService.deleteProductFromBucket(productId, username);

        return "redirect:/";
    }

}
