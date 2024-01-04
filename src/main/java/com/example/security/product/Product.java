package com.example.security.product;

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
    @GeneratedValue
    private Long productId;
    private String productCode;
    private String productNameEn;
    private String productNameKh;
    private String productDescription;

}
