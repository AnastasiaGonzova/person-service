package core.controller;

import core.api.service.AddressService;
import core.model.Address;
import dto.external.AddressDto;
import dto.internal.AddressInternalDto;
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
                .map(addressService::getAndInitialize)
                .map(current->modelMapper.map(current, AddressDto.class))
                .orElseThrow();
    }

    @PostMapping("/admin")
    public AddressDto create(@RequestBody AddressInternalDto addressJson){
        return Optional.ofNullable(addressJson)
                .map(current->modelMapper.map(current, Address.class))
                .map(addressService::create)
                .map(current->modelMapper.map(current, AddressDto.class))
                .orElseThrow();
    }

    @PutMapping("/{address_id}/update")
    public AddressDto update(@PathVariable(name="address_id") Long addressId, @RequestBody AddressInternalDto addressJson){
        return Optional.ofNullable(addressJson)
                .map(current->modelMapper.map(current, Address.class))
                .map(toUpdate->addressService.update(addressId, toUpdate))
                .map(current->modelMapper.map(current, AddressDto.class))
                .orElseThrow();
    }

    @DeleteMapping("/{address_id}/admin/delete")
    public void delete(@PathVariable(name="address_id") Long addressId){
        addressService.delete(addressId);
    }

    @PostMapping("/{address_id}/admin/contacts/{contact_id}")
    public AddressDto assignContact(@PathVariable(name="address_id") Long addressId, @PathVariable(name="contact_id") Long contactId){
        return Optional.of(addressId)
                .map(current->addressService.assignContact(current, contactId))
                .map(current->modelMapper.map(current, AddressDto.class))
                .orElseThrow();
    }

    @DeleteMapping("/{address_id}/admin/contacts/{contact_id}/delete")
    public AddressDto removeContact(@PathVariable(name="address_id") Long addressId, @PathVariable(name="contact_id") Long contactId){
        return Optional.of(addressId)
                .map(current->addressService.removeContact(current, contactId))
                .map(current->modelMapper.map(current, AddressDto.class))
                .orElseThrow();
    }
}
