package com.zooplus.repository;

import com.zooplus.model.OrderBalance;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderBalanceRepository extends CrudRepository<OrderBalance, Long> {

    OrderBalance findByOrderId(Long orderId);

}
