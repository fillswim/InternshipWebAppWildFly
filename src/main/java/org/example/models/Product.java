package org.example.models;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private Double price;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    @ToString.Exclude
    private BucketDetails bucketDetails;

}
