package core.controller;

import core.api.service.AddressService;
import core.model.Address;
import dto.external.AddressDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/addresses")
public class AddressController {

    private AddressService addressService;
    private ModelMapper modelMapper;

    @GetMapping("/{address_id}")
    public AddressDto get(@PathVariable(name="address_id") Long addressId){
        return Optional.of(addressId)
                .map(addressService::get)
                .map(current->modelMapper.map(current, AddressDto.class))
                .orElseThrow();
    }

    @PostMapping()
    public AddressDto create(@RequestBody Address addressJson){
        return Optional.ofNullable(addressJson)
                .map(addressService::create)
                .map(current->modelMapper.map(current, AddressDto.class))
                .orElseThrow();
    }

    @PutMapping("/{address_id}/update")
    public AddressDto update(@PathVariable(name="address_id") Long addressId, @RequestBody Address addressJson){
        return Optional.ofNullable(addressJson)
                .map(toUpdate->addressService.update(addressId, addressJson))
                .map(current->modelMapper.map(current, AddressDto.class))
                .orElseThrow();
    }

    @DeleteMapping("/{address_id}/delete")
    public void delete(@PathVariable(name="address_id") Long addressId){
        addressService.delete(addressId);
    }

    @PostMapping("/{address_id}/contacts/{contact_id}")
    public AddressDto assignContact(@PathVariable(name="address_id") Long addressId, @PathVariable(name="contact_id") Long contactId){
        return Optional.of(addressId)
                .map(current->addressService.assignContact(addressId, contactId))
                .map(current->modelMapper.map(current, AddressDto.class))
                .orElseThrow();
    }
}
