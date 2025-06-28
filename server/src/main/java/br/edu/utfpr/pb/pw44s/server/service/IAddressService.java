package br.edu.utfpr.pb.pw44s.server.service;

import br.edu.utfpr.pb.pw44s.server.dto.AddressDTO;
import br.edu.utfpr.pb.pw44s.server.model.Address;

import java.util.List;

public interface IAddressService extends ICrudService<Address, Long>{
    AddressDTO save(AddressDTO addressDTO);
    List<AddressDTO> findByUser();
}
