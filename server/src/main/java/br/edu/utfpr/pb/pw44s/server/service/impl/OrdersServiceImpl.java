package br.edu.utfpr.pb.pw44s.server.service.impl;

import br.edu.utfpr.pb.pw44s.server.model.Orders;
import br.edu.utfpr.pb.pw44s.server.repository.OrdersRepository;
import br.edu.utfpr.pb.pw44s.server.service.IOrdersService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class OrdersServiceImpl extends CrudServiceImpl<Orders, Long> implements IOrdersService {

    private final OrdersRepository ordersRepository;

    public OrdersServiceImpl(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    @Override
    protected JpaRepository<Orders, Long> getRepository() {
        return this.ordersRepository;
    }
}
