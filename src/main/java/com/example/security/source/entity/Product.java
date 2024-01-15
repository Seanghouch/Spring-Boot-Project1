package com.example.security.source.entity;

import com.example.security.core.BaseEntity;
import jakarta.persistence.*;
import lombok.*;



@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="product")
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String productCode;
    private String productNameEn;
    private String productNameKh;
    private String productDescription;

}
