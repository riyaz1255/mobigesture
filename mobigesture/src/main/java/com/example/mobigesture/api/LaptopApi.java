package com.example.mobigesture.api;

import com.example.mobigesture.model.Laptop;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/product")
public interface LaptopApi {

    @PostMapping("/add/laptop")
    public Laptop addLaptops(Laptop laptop);

    @GetMapping("/get/laptop")
    public ResponseEntity<List<Laptop>> getAllLaptops();

    @GetMapping("/laptop/pageable")
    public Page<Laptop> retrieveLaptopsbyPaging(int page, int size);

    @GetMapping("/laptop/pageablebybrand")
    public Slice<Laptop> retrieveLaptopByBrandWithPaging(String brand,
                                                           int page,
                                                            int size);
    @GetMapping("/laptop/pageable/bypricegreaterthan")
    public Page<Laptop> retrieveLaptopsByPriceGreaterThan( double price,
                                                           int page,
                                                           int size);

    @GetMapping("laptop/{id}")
    public ResponseEntity<Laptop> findLaptopById(Integer laptopId);

    @PutMapping("laptop/{id}")
    public ResponseEntity<Laptop> updateLaptop(Integer laptopId,
                                                  Laptop laptopDetails);

    @DeleteMapping("laptop/{id}")
    public ResponseEntity<Void> deleteLaptop(Integer laptopId);
}
