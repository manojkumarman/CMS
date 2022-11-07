package com.customermanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.customermanagementsystem.model.*;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity,Long>{

}
