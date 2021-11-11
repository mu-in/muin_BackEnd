package dev.muin.backend.domain.Stock;

import dev.muin.backend.service.dto.StockAndProductDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Integer> {

    @Query("SELECT new dev.muin.backend.service.dto.StockAndProductDto(s)  FROM Stock s WHERE s.store.id = :storeId")
    List<StockAndProductDto> findByStoreId(@Param("storeId") Short storeId);

    // 빨간줄 에러 아님
    @Query("SELECT COUNT(CASE WHEN 0 < s.quantity AND s.quantity<=10 THEN 1 END), COUNT(CASE WHEN s.quantity=0 THEN 1 END) " +
            "FROM Stock s WHERE s.store.id = :storeId")
    List<Long[]> countStatusByStoreId(@Param("storeId") Short storeId);

    @Modifying
    @Query("UPDATE Stock s SET s.quantity= :quantity WHERE s.id = :stockId AND s.store.id = :storeId")
    void updateStockByManager(@Param("storeId") Short storeId, @Param("stockId") int stockId, @Param("quantity") int quantity);

    /**
     * 기존 수량보다 같거나 적게 사갔는지 체크
     */
    @Modifying
    @Query("UPDATE Stock s SET s.quantity =CASE WHEN s.quantity >= :quantity THEN (s.quantity - :quantity) ELSE s.quantity END WHERE s.id = :stockId AND s.store.id = :storeId")
    void updateStockByPayment(@Param("storeId") Short storeId, @Param("stockId") int stockId, @Param("quantity") int quantity);


    @Query("SELECT s.store FROM Stock s WHERE s.store.id=:storeId AND s.product.id=:productId")
    Optional<Stock> findByStoreIdAndProductId(@Param("storeId") Short storeId, @Param("productId") int productId);
}
