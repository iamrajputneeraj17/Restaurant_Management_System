package com.example.Restaurant.services;

import com.example.Restaurant.DTOs.CategoryDto;
import com.example.Restaurant.DTOs.ProductDto;
import com.example.Restaurant.Exception.ResourceNotFoundException;
import com.example.Restaurant.Repositories.CategoryRepository;
import com.example.Restaurant.Repositories.ProductRepository;
import com.example.Restaurant.entites.Category;
import com.example.Restaurant.entites.Product;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;



    @Override
    public ProductDto createPro(ProductDto productDto, Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if(optionalCategory.isPresent())
        {
            Product product = new Product();
            BeanUtils.copyProperties(productDto, product);  // It will copy all the properties of productdto and will set in product. It is used in place of mapping.
            product.setImg(productDto.getImg());
            product.setCategory(optionalCategory.get());
            Product createdProduct = productRepository.save(product);
            ProductDto createdProductDTO =  new ProductDto();
            createdProductDTO.setId(createdProduct.getId());
            return createdProductDTO;
        }
        return null;
    }

    @Override
    public List<ProductDto> getProductsByCategroyId(Long id) {

        return productRepository.findAllByCategoryId(id).stream().map(Product::getProductDto).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getProductByTitle(Long categoryId, String title) {

        return productRepository.findAllByCategoryIdAndNameContaining(categoryId, title).stream().map(Product::getProductDto).collect(Collectors.toList());
    }

    @Override
    public ProductDto findByProductId(Long productId) {
        Optional<Product> product = productRepository.findById(productId);
        if(product.isPresent()) {
            ProductDto getProduct = product.get().getProductDto();
            return getProduct;
        }
        return null;
    }

    @Override
    public ProductDto updateProduct(Long productId, ProductDto productDto) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if(optionalProduct.isPresent())
        {
            Product product = optionalProduct.get();
            product.setName(productDto.getName());
            product.setDescription(productDto.getDescription());
            product.setPrice(productDto.getPrice());
            product.setImg(productDto.getImg());
            Product updatedProduct = productRepository.save(product);
            ProductDto updateProductDto = new ProductDto();
            updateProductDto.setId(updatedProduct.getId());
            return updateProductDto;
        }
        return null;
    }


}
