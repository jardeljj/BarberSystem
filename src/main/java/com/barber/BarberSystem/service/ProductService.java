package com.barber.BarberSystem.service;

import com.barber.BarberSystem.dto.ProductRequestDTO;
import com.barber.BarberSystem.dto.ProductResponseDTO;
import com.barber.BarberSystem.exception.ResourceNotFoundException;
import com.barber.BarberSystem.model.Product;
import com.barber.BarberSystem.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductResponseDTO createProduct(ProductRequestDTO dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setQuantity(dto.getQuantity());
        product.setPrice(dto.getPrice());

        Product saved = productRepository.save(product);
        return toResponseDTO(saved);
    }

    public List<ProductResponseDTO> getAllProducts(){
        return productRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public Optional<ProductResponseDTO> getProductById(Long id){
        return productRepository.findById(id)
                .map(this::toResponseDTO);
    }

    public Optional<ProductResponseDTO> updateProduct(Long id, ProductRequestDTO dto){
        return productRepository.findById(id).map(product -> {
            product.setName(dto.getName());
            product.setDescription(dto.getDescription());
            product.setQuantity(dto.getQuantity());
            product.setPrice(dto.getPrice());
            Product updated = productRepository.save(product);
            return toResponseDTO(updated);
        });
    }

    public void deleteProduct(Long id){
        if (!productRepository.existsById(id)){
            throw new ResourceNotFoundException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }

    public ProductResponseDTO toResponseDTO(Product product){
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setQuantity(product.getQuantity());
        dto.setPrice(product.getPrice());
        return dto;
    }
}
