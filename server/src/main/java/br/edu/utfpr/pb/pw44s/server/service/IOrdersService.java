package br.edu.utfpr.pb.pw44s.server.service;

import br.edu.utfpr.pb.pw44s.server.dto.OrdersDTO;
import br.edu.utfpr.pb.pw44s.server.model.Orders;

import java.util.List;

public interface IOrdersService extends ICrudService<Orders, Long> {
    OrdersDTO createOrder(OrdersDTO orderDTO);
    List<OrdersDTO> findByUser();
    OrdersDTO updateStatus(Long id, String status);
    OrdersDTO updateOrderAddress(Long orderId, Long addressId);
}
