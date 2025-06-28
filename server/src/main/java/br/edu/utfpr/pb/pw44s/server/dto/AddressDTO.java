package br.edu.utfpr.pb.pw44s.server.dto;

import br.edu.utfpr.pb.pw44s.server.model.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressDTO {
    private Long id;
    private Long userId;
    private String street;
    private String complement;
    private String zipCode;
    private String city;
    private String state;
    private String country;
    private String number;
    private String neighborhood;
}
