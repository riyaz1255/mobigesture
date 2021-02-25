package com.example.mobigesture.controller;


import com.example.mobigesture.ResouceNotFoundException;
import com.example.mobigesture.api.LaptopApi;
import com.example.mobigesture.model.Laptop;
import com.example.mobigesture.repository.LaptopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LaptopController implements  LaptopApi {
    @Autowired
    private LaptopRepository laptopRepository;

    @Override
    public Laptop addLaptops(@RequestBody Laptop laptop)
    {
        return laptopRepository.save(laptop);
    }

    public Slice<Laptop> retrieveLaptopByBrandWithPaging(@Param(value = "brand") String brand,
                                                              @Param(value = "page") int page,
                                                              @Param(value = "size") int size){
        Pageable requestedPage = PageRequest.of(page, size);
        Slice<Laptop> laptops  = laptopRepository.findAllByBrand(brand, requestedPage);
        return laptops;
    }

    public Page<Laptop> retrieveLaptopsByPriceGreaterThan(@Param(value = "price") double price,
                                                            @Param(value = "page") int page,
                                                            @Param(value = "size") int size){
        Pageable requestedPage = PageRequest.of(page, size);
        Page<Laptop> laptops  = laptopRepository.findAllByPriceGreaterThan(price, requestedPage);
        return laptops;
    }

    public Page<Laptop> retrieveLaptopsbyPaging(@Param(value = "page") int page,
                                                     @Param(value = "size") int size){
        Pageable requestedPage = PageRequest.of(page, size);
        Page<Laptop> laptops  = laptopRepository.findAll(requestedPage);
        return laptops;
    }


    @Override
    public ResponseEntity<List<Laptop>> getAllLaptops() {
        return ResponseEntity.ok(laptopRepository.findAll());
    }

    public ResponseEntity<Laptop> findLaptopById(@PathVariable(value = "id") Integer laptopId) {
        Laptop laptop = laptopRepository.findById(laptopId).orElseThrow(
                () -> new ResouceNotFoundException("laptop not found" + laptopId));
        return ResponseEntity.ok().body(laptop);
    }


    @Override
    public ResponseEntity<Laptop> updateLaptop(@PathVariable(value = "id") Integer laptopId,
                                                   @RequestBody Laptop laptop) {
        Laptop lap = laptopRepository.findById(laptopId)
                .orElseThrow(() -> new ResouceNotFoundException("laptop not found for this id :: " + laptopId));
        lap.setName(laptop.getName());
        final Laptop updatedLaptop = laptopRepository.save(lap);
        return ResponseEntity.ok(updatedLaptop);

    }

    public ResponseEntity<Void> deleteLaptop(@PathVariable(value = "id") Integer laptopId) {
        Laptop laptop = laptopRepository.findById(laptopId).orElseThrow(
                () -> new ResouceNotFoundException("laptop not found" + laptopId));
        laptopRepository.delete(laptop);
        return ResponseEntity.ok().build();
    }
}
