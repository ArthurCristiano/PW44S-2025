package br.edu.utfpr.pb.pw44s.server.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdersDTO {
    private Long id;
    private LocalDateTime date;
    private String status;
    private Long userId;
    private List<OrderItemDTO> items;
    private Long addressId;
}
