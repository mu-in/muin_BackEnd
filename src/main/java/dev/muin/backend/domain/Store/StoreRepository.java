package dev.muin.backend.domain.Store;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Short> {

    String HAVERSINE = "(6371 * acos(cos(radians(:userLat)) * cos(radians(s.location.latitude)) * cos(radians(s.location.longitude) - radians(:userLon)) + sin(radians(:userLat)) * sin(radians(s.location.latitude))))";

    // TODO SQL에선 ORDER BY를 제외하곤 alias 사용 못 함. => 서브 쿼리 사용해볼 수 있음
    // JPQL SELECT 구문 빨간줄 오류 아님
    @Query("SELECT s, " + HAVERSINE +
            "FROM Store s " +
            "WHERE " + HAVERSINE + "<= :distance " +
            "ORDER BY" + HAVERSINE)
    List<Object[]> findAllByDistanceASC(@Param("userLat") double userLat, @Param("userLon") double userLon,
                                        @Param("distance") double distance);

    Optional<Store> findByUuid(String uuid);
}
