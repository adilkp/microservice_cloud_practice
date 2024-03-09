package com.users.services.serviceimpl;

import com.users.entities.Transactions;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

//@FeignClient(url = "http://localhost:8083", value = "Payment-Client")
@FeignClient(name = "PAYMENT-MICROSERVICE")
public interface PaymentClient {

    @GetMapping("/payment/user/{user_id}")
    List<Transactions> getTransactions(@PathVariable Long user_id);
}
