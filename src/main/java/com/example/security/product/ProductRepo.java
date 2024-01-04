package com.example.security.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProductRepo extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    Optional<Product> findByProductId(Long id);

    @Query(value = "SELECT * FROM product WHERE product_id IN :ids", nativeQuery = true)
    List<Product> findByProductIds(@Param("ids") List<Long> ids);

    boolean existsByProductId(Long productId);
}
