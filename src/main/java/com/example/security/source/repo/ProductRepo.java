package com.example.security.source.repo;

import com.example.security.source.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProductRepo extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    Optional<Product> findByProductId(Long id);

    boolean existsByProductId(Long productId);

//    @Modifying
//    @Transactional
//    @Query(value = "DELETE FROM product WHERE product_id NOT IN :activeId", nativeQuery = true)
//    void deleteByProductIdIn(@Param("productId") List<Long> productId);

}
