package com.ridango.payment;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, Long>{    
}
