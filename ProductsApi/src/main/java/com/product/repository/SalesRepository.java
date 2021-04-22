package com.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.product.entity.Sale;

@Repository
public interface SalesRepository extends JpaRepository<Sale, Long>{
}
