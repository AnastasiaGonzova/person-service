package core.controller;

import core.api.service.AddressService;
import core.model.Address;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/addresses")
public class AddressController {

    private AddressService addressService;

    @GetMapping("/{address_id}")
    public Address get(@PathVariable(name="address_id") Long addressId){
        return Optional.of(addressId)
                .map(addressService::get)
                .orElseThrow();
    }

    @PostMapping()
    public Address create(@RequestBody Address addressJson){
        return Optional.ofNullable(addressJson)
                .map(addressService::create)
                .orElseThrow();
    }

    @PutMapping("/{address_id}/update")
    public Address update(@PathVariable(name="address_id") Long addressId, @RequestBody Address addressJson){
        return Optional.ofNullable(addressJson)
                .map(toUpdate->addressService.update(addressId, addressJson))
                .orElseThrow();
    }

    @DeleteMapping("/{address_id}/delete")
    public void delete(@PathVariable(name="address_id") Long addressId){
        addressService.delete(addressId);
    }

    @PostMapping("/{address_id}/contacts/{contact_id}")
    public Address assignContact(@PathVariable(name="address_id") Long addressId, @PathVariable(name="contact_id") Long contactId){
        return Optional.of(addressId)
                .map(current->addressService.assignContact(addressId, contactId))
                .orElseThrow();
    }
}
