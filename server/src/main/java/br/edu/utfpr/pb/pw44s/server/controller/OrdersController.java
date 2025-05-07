package br.edu.utfpr.pb.pw44s.server.controller;

import br.edu.utfpr.pb.pw44s.server.dto.OrdersDTO;
import br.edu.utfpr.pb.pw44s.server.model.Orders;
import br.edu.utfpr.pb.pw44s.server.service.ICrudService;
import br.edu.utfpr.pb.pw44s.server.service.IOrdersService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("orders")
public class OrdersController extends CrudController<Orders, OrdersDTO, Long>{

    private final IOrdersService ordersService;
    private final ModelMapper modelMapper;

    public OrdersController(IOrdersService ordersService, ModelMapper modelMapper) {
        super(Orders.class, OrdersDTO.class);
        this.ordersService = ordersService;
        this.modelMapper = modelMapper;
    }

    @Override
    protected ICrudService<Orders, Long> getService(){
        return this.ordersService;
    }

    @Override
    protected ModelMapper getModelMapper() {
        return modelMapper;
    }
}
