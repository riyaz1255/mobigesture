package com.example.mobigesture.controller;


import com.example.mobigesture.ResouceNotFoundException;
import com.example.mobigesture.api.PhoneApi;
import com.example.mobigesture.model.Phone;
import com.example.mobigesture.repository.PhoneRepository;
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
public class PhoneController implements PhoneApi
 {
    @Autowired
    private PhoneRepository phoneRepository;

     @Override
     public Phone addPhone(@RequestBody Phone phone)
     {
         return phoneRepository.save(phone);
     }

     public Slice<Phone> retrievePhoneByBrandWithPaging(@Param(value = "brand") String brand,
                                                          @Param(value = "page") int page,
                                                          @Param(value = "size") int size){
         Pageable requestedPage = PageRequest.of(page, size);
         Slice<Phone> laptops  = phoneRepository.findAllByBrand(brand, requestedPage);
         return laptops;
     }

     public Page<Phone> retrievePhonesByPriceGreaterThan(@Param(value = "price") double price,
                                                           @Param(value = "page") int page,
                                                           @Param(value = "size") int size){
         Pageable requestedPage = PageRequest.of(page, size);
         Page<Phone> laptops  = phoneRepository.findAllByPriceGreaterThan(price, requestedPage);
         return laptops;
     }

     public Page<Phone> retrievePhonessbyPaging(@Param(value = "page") int page,
                                                 @Param(value = "size") int size){
         Pageable requestedPage = PageRequest.of(page, size);
         Page<Phone> phones  = phoneRepository.findAll(requestedPage);
         return phones;
     }

     @Override
     public ResponseEntity<List<Phone>> getAllPhones() {
         return ResponseEntity.ok(phoneRepository.findAll());
     }

     public ResponseEntity<Phone> findPhoneById(@PathVariable(value = "id") Integer phoneId) {
         Phone phone = phoneRepository.findById(phoneId).orElseThrow(
                 () -> new ResouceNotFoundException("Phone not found" + phoneId));
         return ResponseEntity.ok().body(phone);
     }

     @Override
     public ResponseEntity<Phone> updatePhone(@PathVariable(value = "id") Integer phoneId,
                                                @RequestBody Phone PhoneDetails) {
         Phone phone = phoneRepository.findById(phoneId)
                 .orElseThrow(() -> new ResouceNotFoundException("phone not found for this id :: " + phoneId));
         phone.setName(PhoneDetails.getName());
         final Phone updatedPhone = phoneRepository.save(phone);
         return ResponseEntity.ok(updatedPhone);

     }

     public ResponseEntity<Void> deletePhone(@PathVariable(value = "id") Integer phoneId) {
         Phone phone = phoneRepository.findById(phoneId).orElseThrow(
                 () -> new ResouceNotFoundException("phone not found" + phoneId));
         phoneRepository.delete(phone);
         return ResponseEntity.ok().build();
     }

}
