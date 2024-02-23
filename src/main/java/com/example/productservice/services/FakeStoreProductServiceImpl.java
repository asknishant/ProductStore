package com.example.productservice.services;

import com.example.productservice.dtos.FakeStoreProductDto;
import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import com.example.productservice.thirdpartyclients.FakeStoreClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("FakeProductService")
public class FakeStoreProductServiceImpl implements ProductService {
     private FakeStoreClient fakeStoreClient;
    @Autowired
    public FakeStoreProductServiceImpl(FakeStoreClient fakeStoreClient) {
        this.fakeStoreClient = fakeStoreClient;
    }

    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        FakeStoreProductDto fakeStoreProductDto = fakeStoreClient.getProductById(id);
        return getProductFromFakeStoreProductDto(fakeStoreProductDto);
    }

    @Override
    public List<Product> getAllProducts() {
        List<FakeStoreProductDto> fakeStoreProductDtos = fakeStoreClient.getAllProducts();
        return fakeStoreProductDtos.stream().map(this::getProductFromFakeStoreProductDto).toList();
    }

    @Override
    public Product deleteProductById(Long id) {
        FakeStoreProductDto deletedFakeStoreProduct = fakeStoreClient.deleteProductById(id);
        return getProductFromFakeStoreProductDto(deletedFakeStoreProduct);
    }

    @Override
    public Product updateProductById(Long id, Product updatedProduct) throws ProductNotFoundException {
        FakeStoreProductDto getFakeStoreProductDto = fakeStoreClient.updateProductById(id, getFakeStoreProductDtoFromProduct(updatedProduct));
        return getProductFromFakeStoreProductDto(getFakeStoreProductDto);
    }

    @Override
    public Product addProduct(Product product) {
        FakeStoreProductDto addedProduct = fakeStoreClient.addProduct(getFakeStoreProductDtoFromProduct(product));
        return getProductFromFakeStoreProductDto(addedProduct);
    }

    private Product getProductFromFakeStoreProductDto(FakeStoreProductDto fakeStoreProductDto) {
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setPrice(fakeStoreProductDto.getPrice());
        Category category = new Category();
        category.setName(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        return product;
    }

    private FakeStoreProductDto getFakeStoreProductDtoFromProduct(Product product) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setCategory(product.getCategory().getName());
        return fakeStoreProductDto;
    }
}

