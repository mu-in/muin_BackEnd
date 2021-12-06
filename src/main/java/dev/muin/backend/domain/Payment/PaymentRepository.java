package dev.muin.backend.domain.Payment;

import dev.muin.backend.service.dto.RecentPaymentDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Short> {

    @Query("SELECT new dev.muin.backend.service.dto.RecentPaymentDto(p) FROM Payment p WHERE p.store.id = :storeId " +
            "ORDER BY p.payTime DESC ")
    List<RecentPaymentDto> find5PaymentByOrderByPayTime(@Param("storeId") Short storeId, Pageable pageable);

    @Query("SELECT new dev.muin.backend.service.dto.RecentPaymentDto(p) FROM Payment p WHERE p.store.id = :storeId " +
            "ORDER BY p.payTime DESC")
    List<RecentPaymentDto> findAllOrderByPayTime(@Param("storeId") Short storeId);

    // 빨간줄 에러 아님
    @Query("SELECT SUM(p.totalPrice) FROM Payment p WHERE p.store.id = :storeId AND YEAR(p.payTime) = YEAR(:now) " +
            "AND MONTH(p.payTime) = MONTH(:now)")
    Integer findThisMonthByStore(@Param("storeId") Short storeId, @Param("now") Date now);

    // 빨간줄 에러 아님
    @Query("SELECT SUM(p.totalPrice) FROM Payment p WHERE p.store.id = :storeId AND YEAR(p.payTime) = YEAR(:now) " +
            "AND MONTH(p.payTime) = MONTH(:now)-1")
    Integer findPrevMonthByStore(@Param("storeId") Short storeId, @Param("now") Date now);

    // 빨간줄 에러 아님
    @Query("SELECT SUM(p.totalPrice) FROM Payment p WHERE p.store.id = :storeId AND DATE(p.payTime)=:now")
    Integer findTodayByStore(@Param("storeId") Short storeId, @Param("now") Date now);
}
