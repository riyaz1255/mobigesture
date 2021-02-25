package com.example.mobigesture.repository;

import com.example.mobigesture.model.Phone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepository extends JpaRepository<Phone,Integer> {
    Slice<Phone> findAllByBrand(String brand, Pageable pageable);
    Page<Phone> findAllByPriceGreaterThan(double price, Pageable pageable);
}
