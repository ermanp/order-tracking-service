package com.zooplus.repository;

import com.zooplus.model.CustomerBalance;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerBalanceRepository extends CrudRepository<CustomerBalance,Long> {

    Optional<CustomerBalance> findByCustomerId(Long customerId);
}
