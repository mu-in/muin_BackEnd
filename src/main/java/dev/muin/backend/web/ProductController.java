package dev.muin.backend.web;

import dev.muin.backend.service.ManagerService;
import dev.muin.backend.web.response.ProductsGroupedByCategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProductController {

    private final ManagerService managerService;

    /**
     * Use before adding new products to store by MANGER
     */
    @GetMapping("/products")
    public ResponseEntity<List<ProductsGroupedByCategoryDto>> getProducts() throws Exception{
        List<ProductsGroupedByCategoryDto> res = managerService.getProducts();
        return ResponseEntity.ok(res);
    }
}
