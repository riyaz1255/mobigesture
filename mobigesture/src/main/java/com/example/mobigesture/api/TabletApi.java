package com.example.mobigesture.api;

import com.example.mobigesture.model.Phone;
import com.example.mobigesture.model.Tablet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/product")
public interface TabletApi {

    @PostMapping("/add/tablet")
    public Tablet addTablets( Tablet tablet);

    @GetMapping("/tablet/pageable")
    public Page<Tablet> retrieveTabletsbyPaging(int page, int size);

    @GetMapping("/tablet/pageablebybrand")
    public Slice<Tablet> retrieveTabletByBrandWithPaging(String brand,
                                                       int page,
                                                       int size);
    @GetMapping("/tablet/pageable/bypricegreaterthan")
    public Page<Tablet> retrieveTabletsByPriceGreaterThan( double price,
                                                         int page,
                                                         int size);

    @GetMapping("/get/tablet")
    public ResponseEntity<List<Tablet>> getAllTablets();

    @GetMapping("tablet/{id}")
    public ResponseEntity<Tablet> findTabletById(Integer tabletId);

    @PutMapping("tablet/{id}")
    public ResponseEntity<Tablet> updateTablet(Integer tabletId,
                                               Tablet tabletDetails);

    @DeleteMapping("tablet/{id}")
    public ResponseEntity<Void> deleteTablet(Integer tabletId);
}
