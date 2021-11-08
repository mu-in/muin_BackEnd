package dev.muin.backend.domain.Stock;

import dev.muin.backend.web.response.StockForManagerResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Short> {

    @Query("SELECT new dev.muin.backend.web.response.StockForManagerResponse(s)  FROM Stock s WHERE s.store.id = :storeId")
    List<StockForManagerResponse> findStocksGroupAsCategory(@Param("storeId") Short storeId);

    @Modifying
    @Query("UPDATE Stock s SET s.quantity= :quantity WHERE s.id = :stockId AND s.store.id = :storeId")
    void updateStock(@Param("storeId") Short storeId, @Param("stockId") int stockId, @Param("quantity") Integer quantity);
}
