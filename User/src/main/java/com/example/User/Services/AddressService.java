package com.example.User.Services;

import com.example.User.Enums.AuditType;
import com.example.User.Models.Entities.AddressEntity;
import com.example.User.Models.Entities.UserEntity;
import com.example.User.Repository.AddressRepository;
import com.example.User.Repository.UserRepository;
import com.example.User.Utils.AuditUtil;
import com.example.User.Utils.ObjectComparatorUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class AddressService {

    private AddressRepository addressRepository;
    private UserRepository userRepository;
    private AuditUtil auditUtil;

    public List<AddressEntity> getAddresses(long userId) throws Exception {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new Exception("User not found"));

        return user.getAddresses();
    }

    public String EditAddress(AddressEntity address, Long userId) throws Exception {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new Exception("User not found"));

        address.setUser(user);
        if ( address.getId() != null && !(address.getId() > 0)) {
            addressRepository.save(address);
            auditUtil.captureAudit(AuditType.ADDRESS, user.getID(), address.getAddress()+ " address added", "");
            return "Address added successfully";
        }

        AddressEntity oldAddress = addressRepository.findById(address.getId()).orElseThrow(() -> new Exception("Address not found"));
        String changes = ObjectComparatorUtil.compareObject(oldAddress, address);

        if (changes != null) {
            auditUtil.captureAudit(AuditType.ADDRESS, user.getID(), changes, "");
        }

        addressRepository.save(address);
        return "Address updated successfully";
    }

    public String deleteAddress(Long id) {
        AddressEntity address = addressRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Address not present"));
        addressRepository.delete(address);
        auditUtil.captureAudit(AuditType.ADDRESS,address.getUser().getID(), address.getAddress()+ " address deleted", "");
        return "Address removed successfully";
    }

}
