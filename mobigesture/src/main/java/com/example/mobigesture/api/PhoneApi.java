package com.example.mobigesture.api;

import com.example.mobigesture.model.Laptop;
import com.example.mobigesture.model.Phone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/product")
public interface PhoneApi {

    @PostMapping("/add/phone")
    public Phone addPhone( Phone Phone);

    @GetMapping("/get/phone")
    public ResponseEntity<List<Phone>> getAllPhones();

    @GetMapping("/phone/pageable")
    public Page<Phone> retrievePhonessbyPaging(int page, int size);

    @GetMapping("/phone/pageablebybrand")
    public Slice<Phone> retrievePhoneByBrandWithPaging(String brand,
                                                         int page,
                                                         int size);
    @GetMapping("/phone/pageable/bypricegreaterthan")
    public Page<Phone> retrievePhonesByPriceGreaterThan( double price,
                                                           int page,
                                                           int size);

    @GetMapping("phone/{id}")
    public ResponseEntity<Phone> findPhoneById(Integer phoneId);

    @PutMapping("phone/{id}")
    public ResponseEntity<Phone> updatePhone(Integer phoneId,
                                              Phone phoneDetails);

    @DeleteMapping("phone/{id}")
    public ResponseEntity<Void> deletePhone(Integer phoneID);
}
