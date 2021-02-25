package com.example.mobigesture.controller;


import com.example.mobigesture.ResouceNotFoundException;
import com.example.mobigesture.api.TabletApi;
import com.example.mobigesture.model.Phone;
import com.example.mobigesture.model.Tablet;
import com.example.mobigesture.repository.TabletRepository;
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
public class TabletController implements TabletApi
{
    @Autowired
    private TabletRepository tabletRepository;

    @Override
    public Tablet addTablets(@RequestBody Tablet tablet)
    {
        return tabletRepository.save(tablet);
    }

    public Slice<Tablet> retrieveTabletByBrandWithPaging(@Param(value = "brand") String brand,
                                                       @Param(value = "page") int page,
                                                       @Param(value = "size") int size){
        Pageable requestedPage = PageRequest.of(page, size);
        Slice<Tablet> tablets  = tabletRepository.findAllByBrand(brand, requestedPage);
        return tablets;
    }

    public Page<Tablet> retrieveTabletsByPriceGreaterThan(@Param(value = "price") double price,
                                                        @Param(value = "page") int page,
                                                        @Param(value = "size") int size){
        Pageable requestedPage = PageRequest.of(page, size);
        Page<Tablet> tablets  = tabletRepository.findAllByPriceGreaterThan(price, requestedPage);
        return tablets;
    }

    public Page<Tablet> retrieveTabletsbyPaging(@Param(value = "page") int page,
                                               @Param(value = "size") int size){
        Pageable requestedPage = PageRequest.of(page, size);
        Page<Tablet> tablets  = tabletRepository.findAll(requestedPage);
        return tablets;
    }
    @Override
    public ResponseEntity<List<Tablet>> getAllTablets() {
        return ResponseEntity.ok(tabletRepository.findAll());
    }

    public ResponseEntity<Tablet> findTabletById(@PathVariable(value = "id") Integer tabletId) {
        Tablet tablet = tabletRepository.findById(tabletId).orElseThrow(
                () -> new ResouceNotFoundException("tabletId not found" + tabletId));
        return ResponseEntity.ok().body(tablet);
    }

    @Override
    public ResponseEntity<Tablet> updateTablet(@PathVariable(value = "id") Integer tabletId,
                                             @RequestBody Tablet tabletDetails) {
        Tablet tablet = tabletRepository.findById(tabletId)
                .orElseThrow(() -> new ResouceNotFoundException("tablet not found for this id :: " + tabletId));
        tablet.setName(tabletDetails.getName());
        final Tablet updatedTablet = tabletRepository.save(tablet);
        return ResponseEntity.ok(updatedTablet);

    }

    @Override
    public ResponseEntity<Void> deleteTablet(@PathVariable(value = "id") Integer tabletId) {
        Tablet tablet = tabletRepository.findById(tabletId).orElseThrow(
                () -> new ResouceNotFoundException("tablet not found" + tabletId));
        tabletRepository.delete(tablet);
        return ResponseEntity.ok().build();
    }
}
