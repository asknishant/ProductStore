package com.example.productservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel {
    private String name;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "category") // by default JPA uses lazy loading for collections inside an entity.
    List<Product> products;
}
/*eager loading is beneficial as it does send only one request to server to join the tables product and category on the server
* itself and returns one response. On the other hand if we want list of products with some category ids then we will fetch products and
* category both over the network and network calls are always expensive.
* https://www.baeldung.com/hibernate-lazy-eager-loading
* https://www.baeldung.com/hibernate-fetchmode // jpa ignores fetchmode.read on N+1 problem.
* */
