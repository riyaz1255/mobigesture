package com.example.mobigesture.repository;

import com.example.mobigesture.model.Phone;
import com.example.mobigesture.model.Tablet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TabletRepository extends JpaRepository<Tablet,Integer> {

    Slice<Tablet> findAllByBrand(String brand, Pageable pageable);
    Page<Tablet> findAllByPriceGreaterThan(double price, Pageable pageable);
}
