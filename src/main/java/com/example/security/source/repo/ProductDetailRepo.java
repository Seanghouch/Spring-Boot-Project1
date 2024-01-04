package com.example.security.source.repo;

import com.example.security.source.entity.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductDetailRepo extends JpaRepository<ProductDetail, Long>, PagingAndSortingRepository<ProductDetail, Long>{

    List<ProductDetail> findByProductId(Long productId);

    Optional<ProductDetail> findByProductDetailId(Long productDetailId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM product_detail WHERE product_detail_id NOT IN :activeId AND product_id = :productId", nativeQuery = true)
    void deleteByProductDetailIdNotIn(@Param("activeId") List<Long> activeId,@Param("productId") Long productId);
}
