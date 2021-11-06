package dev.muin.backend.domain.Stock;

import dev.muin.backend.web.response.StocksResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Short> {

    @Query("SELECT new dev.muin.backend.web.response.StocksResponse(s)  FROM Stock s WHERE s.store.id = :storeId")
    List<StocksResponse> findStocksGroupAsCategory(@Param("storeId") Short storeId);
}
