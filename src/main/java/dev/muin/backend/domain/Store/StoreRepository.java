package dev.muin.backend.domain.Store;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Short> {
    Optional<Store> findByUuid(String uuid);
}
