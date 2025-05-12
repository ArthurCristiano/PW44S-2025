package br.edu.utfpr.pb.pw44s.server.controller;

import br.edu.utfpr.pb.pw44s.server.dto.ProductDTO;
import br.edu.utfpr.pb.pw44s.server.model.Product;
import br.edu.utfpr.pb.pw44s.server.repository.ProductRepository;
import br.edu.utfpr.pb.pw44s.server.service.ICrudService;
import br.edu.utfpr.pb.pw44s.server.service.IProductService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController extends CrudController<Product, ProductDTO, Long> {

    private final IProductService productService;
    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;

    public ProductController(IProductService productService, ModelMapper modelMapper,
                             ProductRepository productRepository) {
        super(Product.class, ProductDTO.class);
        this.productService = productService;
        this.modelMapper = modelMapper;
        this.productRepository = productRepository;
    }

    @Override
    protected ICrudService<Product, Long> getService(){
        return this.productService;
    }

    @Override
    protected ModelMapper getModelMapper() {
        return modelMapper;
    }

    @GetMapping("/categories/{id}")
    public List<Product> listarPorCategoria(@PathVariable Long id) {
        return productRepository.findByCategoryId(id);
    }
}
