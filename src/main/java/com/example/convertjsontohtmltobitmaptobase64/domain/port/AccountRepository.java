package com.example.convertjsontohtmltobitmaptobase64.domain.port;

import com.example.convertjsontohtmltobitmaptobase64.domain.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

}