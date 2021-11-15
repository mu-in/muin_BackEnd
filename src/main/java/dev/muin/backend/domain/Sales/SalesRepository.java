package dev.muin.backend.domain.Sales;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SalesRepository extends JpaRepository<Sales, Short> {

    @Query(nativeQuery = true,
            value = "SELECT * FROM sales WHERE store_id = :storeId AND month >= DATE_ADD(CURDATE(), INTERVAL -7 MONTH) " +
                    "ORDER BY month ASC ")
    List<Sales> findLast6MonthPayment(@Param("storeId") Short storeId);
}
