package io.github.jaoxavier.myOwnEcormmece.rest.controller;

import io.github.jaoxavier.myOwnEcormmece.domain.entity.client.info.Client;
import io.github.jaoxavier.myOwnEcormmece.domain.entity.product.info.Product;
import io.github.jaoxavier.myOwnEcormmece.exception.client.ClientIsNotCompany;
import io.github.jaoxavier.myOwnEcormmece.repository.ProductRepository;
import io.github.jaoxavier.myOwnEcormmece.rest.dto.CreateProductDTO;
import io.github.jaoxavier.myOwnEcormmece.service.client.ClientService;
import io.github.jaoxavier.myOwnEcormmece.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ClientService clientService;
    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Integer id){
        return productService.getProductById(id);
    }

    @PostMapping
    public Product createProduct(@RequestBody CreateProductDTO dto){
        Client client = clientService.getClient(dto.getClient_id());

        if (!client.getIsCompany()){
            throw new ClientIsNotCompany("Only companys can create product");
        }

        Product product = Product
                .builder()
                .client(client)
                .name(dto.getName())
                .brand(dto.getBrand())
                .price(dto.getPrice())
                .stock(dto.getStock())
                .build();

        return productService.saveProduct(product);
    }
}
