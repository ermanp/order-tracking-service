package com.zooplus.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CustomerBalanceNotFoundException extends Exception{

    public CustomerBalanceNotFoundException() {
        super("Customer Balance couldn't be found.");
    }
}
