package br.edu.utfpr.pb.pw44s.server.controller;

import br.edu.utfpr.pb.pw44s.server.dto.AddressDTO;
import br.edu.utfpr.pb.pw44s.server.model.Address;
import br.edu.utfpr.pb.pw44s.server.service.IAddressService;
import br.edu.utfpr.pb.pw44s.server.service.ICrudService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("address")
public class AddressController extends CrudController<Address, AddressDTO, Long>{

    private final IAddressService addressService;
    private final ModelMapper modelMapper;

    public AddressController(IAddressService addressService, ModelMapper modelMapper) {
        super(Address.class, AddressDTO.class);
        this.addressService = addressService;
        this.modelMapper = modelMapper;
    }

    @Override
    protected ICrudService<Address, Long> getService(){
        return this.addressService;
    }

    @Override
    protected ModelMapper getModelMapper() {
        return modelMapper;
    }

    @PostMapping
    public ResponseEntity<AddressDTO> create(@RequestBody AddressDTO addressDTO) {
        try {
            AddressDTO savedAddress = addressService.save(addressDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedAddress);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/my-addresses")
    public ResponseEntity<List<AddressDTO>> findByUser() {
        return ResponseEntity.ok(addressService.findByUser());
    }

}