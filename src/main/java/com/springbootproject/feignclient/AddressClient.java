package com.springbootproject.feignclient;

import com.springbootproject.dto.address.AddressDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="AddressClient", url="http://localhost:8081/address-app", path="api/address")
public interface AddressClient {

    @GetMapping("/{id}")
    ResponseEntity<AddressDto> getAddressByEmployeeId(@PathVariable("id") int id);


}
