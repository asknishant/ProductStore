package com.example.productservice.thirdpartyclients;

import com.example.productservice.dtos.FakeStoreProductDto;
import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedList;
import java.util.List;

@Component // this annotation is used to register the class as a bean in the spring container.
public class FakeStoreClient {
    private String getProductUrl = "https://fakestoreapi.com/products/1";
    private String genericProductUrl = "https://fakestoreapi.com/products";
    private RestTemplateBuilder restTemplateBuilder;
    @Autowired
    public FakeStoreClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public FakeStoreProductDto getProductById(Long id) throws ProductNotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.getForEntity(getProductUrl, FakeStoreProductDto.class);
        if(responseEntity.getBody().getId() != id) {
            throw new ProductNotFoundException(responseEntity.getBody().getId());
        }
        return responseEntity.getBody();
    }

    public List<FakeStoreProductDto> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> responseEntity = restTemplate.getForEntity(genericProductUrl, FakeStoreProductDto[].class);
        List<FakeStoreProductDto> productList = new LinkedList<>();
        for(FakeStoreProductDto fakeStoreProductDto: responseEntity.getBody()) {
            productList.add(fakeStoreProductDto);
        }
        return productList;
    }

    public FakeStoreProductDto deleteProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        //restTemplate.delete(genericProductUrl + "/" + id);
        // current current delete does not return a response entity.
        // we can request callback to get the response entity first.
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor =
                restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.execute(genericProductUrl + "/" + id, org.springframework.http.HttpMethod.DELETE, requestCallback, responseExtractor, id);
        return responseEntity.getBody();
    }


    public void updateProductById(Long id) {

    }

    public FakeStoreProductDto addProduct(FakeStoreProductDto fakeStoreProductDto) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> responseEntity =
                restTemplate.postForEntity(genericProductUrl, fakeStoreProductDto, FakeStoreProductDto.class);
        return responseEntity.getBody();
    }
}
