package com.example.User.Controllers;

import com.example.User.Models.Entities.AddressEntity;
import com.example.User.Services.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
@AllArgsConstructor
@CrossOrigin(originPatterns = "*")
public class AddressController {

    private AddressService addressService;

    @GetMapping("")
    private List<AddressEntity> getAddresses(@RequestParam long userId) throws Exception {
        return addressService.getAddresses(userId);
    }

    @PostMapping("/edit")
    private String EditAddress(@RequestBody AddressEntity address, @RequestParam Long userId) throws Exception {
        return addressService.EditAddress(address,userId);
    }

    @DeleteMapping("/delete")
    private String deleteAddress(@RequestParam Long addressId) throws Exception {
        return addressService.deleteAddress(addressId);
    }

}
