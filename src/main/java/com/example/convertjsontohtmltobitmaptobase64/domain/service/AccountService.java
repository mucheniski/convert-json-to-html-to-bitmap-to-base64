package com.example.convertjsontohtmltobitmaptobase64.domain.service;

import com.example.convertjsontohtmltobitmaptobase64.domain.domain.Account;
import com.example.convertjsontohtmltobitmaptobase64.domain.exception.EntityNotFoundException;
import com.example.convertjsontohtmltobitmaptobase64.domain.port.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account findById(Long id) {
        return accountRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Id not found: " + id));
    }

}
