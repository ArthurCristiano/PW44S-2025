package br.edu.utfpr.pb.pw44s.server.service.impl;

import br.edu.utfpr.pb.pw44s.server.dto.OrderItemDTO;
import br.edu.utfpr.pb.pw44s.server.dto.OrdersDTO;
import br.edu.utfpr.pb.pw44s.server.model.OrderItens;
import br.edu.utfpr.pb.pw44s.server.model.Orders;
import br.edu.utfpr.pb.pw44s.server.model.Product;
import br.edu.utfpr.pb.pw44s.server.model.User;
import br.edu.utfpr.pb.pw44s.server.repository.OrderItensRepository;
import br.edu.utfpr.pb.pw44s.server.repository.OrdersRepository;
import br.edu.utfpr.pb.pw44s.server.repository.ProductRepository;
import br.edu.utfpr.pb.pw44s.server.repository.UserRepository;
import br.edu.utfpr.pb.pw44s.server.service.IOrdersService;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrdersServiceImpl extends CrudServiceImpl<Orders, Long> implements IOrdersService {

    private final OrdersRepository ordersRepository;
    private final OrderItensRepository orderItensRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public OrdersServiceImpl(OrdersRepository ordersRepository,
                             OrderItensRepository orderItensRepository,
                             UserRepository userRepository,
                             ProductRepository productRepository,
                             ModelMapper modelMapper) {
        this.ordersRepository = ordersRepository;
        this.orderItensRepository = orderItensRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    protected JpaRepository<Orders, Long> getRepository() {
        return this.ordersRepository;
    }

    @Override
    @Transactional
    public Orders save(Orders entity) {
        // Implementação padrão do CRUD
        return getRepository().save(entity);
    }

    @Transactional
    public OrdersDTO createOrder(OrdersDTO orderDTO) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username);

        Orders order = new Orders();
        order.setDate(LocalDateTime.now());
        order.setUser(user);
        order.setStatus("Pendente");
        Orders savedOrder = ordersRepository.save(order);

        List<OrderItens> items = orderDTO.getItems().stream().map(itemDTO -> {
            Product product = productRepository.findById(itemDTO.getProductId())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado: " + itemDTO.getProductId()));

            OrderItens item = new OrderItens();
            item.setOrders(savedOrder);
            item.setProduct(product);
            item.setPrice(itemDTO.getPrice());
            item.setQuantity(itemDTO.getQuantity());

            return item;
        }).collect(Collectors.toList());

        orderItensRepository.saveAll(items);

        orderDTO.setId(savedOrder.getId());
        orderDTO.setDate(savedOrder.getDate());
        orderDTO.setUserId(user.getId());

        return orderDTO;
    }

    @Override
    public List<OrdersDTO> findByUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username);

        return ordersRepository.findByUser(user).stream()
                .map(order -> {
                    OrdersDTO dto = modelMapper.map(order, OrdersDTO.class);
                    dto.setUserId(user.getId());

                    List<OrderItemDTO> itemDTOs = order.getItems().stream().map(item -> {
                        return OrderItemDTO.builder()
                                .productId(item.getProduct().getId())
                                .price(item.getPrice())
                                .quantity(item.getQuantity())
                                .build();
                    }).collect(Collectors.toList());

                    dto.setItems(itemDTOs);

                    return dto;
                })
                .collect(Collectors.toList());
    }
}
