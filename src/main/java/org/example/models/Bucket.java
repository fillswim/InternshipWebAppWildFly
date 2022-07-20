package org.example.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "buckets")
public class Bucket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private BucketStatus bucketStatus;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(
            fetch = FetchType.EAGER,
            mappedBy = "bucket",
            cascade= CascadeType.ALL,
            orphanRemoval = true)
    @ToString.Exclude
    private List<BucketDetails> bucketDetailsList;


}
