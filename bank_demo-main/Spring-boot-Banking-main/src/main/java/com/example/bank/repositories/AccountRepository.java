package com.example.bank.repositories;

import com.example.bank.models.PartnerAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<PartnerAccount, Long> {

//    Optional<Account> findBySortCodeAndAccountNumber(Long partnerReferenceNumber, String escrowAccountNumber);
//    Optional<Account> findByAccountNumber(String escrowAccountNumber);
}
