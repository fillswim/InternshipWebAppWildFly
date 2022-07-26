package org.example.models;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
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

    private double sum;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "bucket",
            cascade= CascadeType.ALL,
            orphanRemoval = true)
    @ToString.Exclude
    private List<BucketDetails> bucketDetailsList;

}
