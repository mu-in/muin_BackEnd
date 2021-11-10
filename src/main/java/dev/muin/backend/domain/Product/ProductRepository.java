package dev.muin.backend.domain.Product;

import dev.muin.backend.service.dto.StockAndProductDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT new dev.muin.backend.service.dto.StockAndProductDto(p) FROM Product p")
    List<StockAndProductDto> findAllProducts();
}
