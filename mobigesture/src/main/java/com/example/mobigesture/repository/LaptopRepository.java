package com.example.mobigesture.repository;

import com.example.mobigesture.model.Laptop;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LaptopRepository extends JpaRepository<Laptop,Integer> {
    Slice<Laptop> findAllByBrand (String brand, Pageable pageable);
    Page<Laptop> findAllByPriceGreaterThan(double price, Pageable pageable);
}
