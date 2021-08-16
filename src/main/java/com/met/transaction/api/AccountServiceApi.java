package com.met.transaction.api;

import com.met.transaction.dto.request.ExecuteTransactionRequest;
import com.met.transaction.dto.response.ExecuteTransactionResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient("account-service")
public interface AccountServiceApi {
    @PutMapping("/account/transaction/execute")
    ExecuteTransactionResponse execute(@RequestHeader("Authorization") String token, @RequestBody ExecuteTransactionRequest request);
}
