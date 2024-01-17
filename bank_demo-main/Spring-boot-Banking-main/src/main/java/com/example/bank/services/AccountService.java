package com.example.bank.services;

import com.example.bank.dto.PartnerAdditionRequestDto;
import com.example.bank.dto.PartnerAdditionResponseDto;
import com.example.bank.dto.SellerAdditionRequestDto;
import com.example.bank.dto.SellerAdditionResponseDto;
import com.example.bank.models.PartnerAccount;
import com.example.bank.models.SellerAccount;
import com.example.bank.repositories.AccountRepository;
import com.example.bank.repositories.SellerAccountRepository;
import com.example.bank.utils.CodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private  AccountRepository accountRepository;

    @Autowired
    private  SellerAccountRepository sellerAccountRepository;

//    @Autowired
//    private final TransactionRepository transactionRepository;

//    public Account getAccount(String sortCode, String accountNumber) {
//        Optional<Account> account = accountRepository
//                .findBySortCodeAndAccountNumber(sortCode, accountNumber);
//
//        account.ifPresent(value ->
//                value.setTransactions(transactionRepository
//                        .findBySourceAccountIdOrderByInitiationDate(value.getPartnerReferenceNumber())));
//
//        return account.orElse(null);
//    }

//    public Account getAccount(String accountNumber) {
//        Optional<Account> account = accountRepository
//                .findByAccountNumber(accountNumber);
//
//        return account.orElse(null);
//    }

//    public Account createAccount(String bankName, String ownerName) {
//        CodeGenerator codeGenerator = new CodeGenerator();
//        Account newAccount = new Account(bankName, ownerName, codeGenerator.generateSortCode(), codeGenerator.generateAccountNumber(), 0.00);
//        return accountRepository.save(newAccount);
//    }

    public PartnerAdditionResponseDto createPartnerAccount(@Valid PartnerAdditionRequestDto partnerAdditionRequestDto){
        CodeGenerator codeGenerator = new CodeGenerator();
        PartnerAdditionResponseDto partnerAdditionResponseDto = new PartnerAdditionResponseDto( Long.parseLong(codeGenerator.genratePartnerReferenceNumber()),
                partnerAdditionRequestDto.getPartnerName(),
                partnerAdditionRequestDto.getLegalName(),
                partnerAdditionRequestDto.getEmailId(),
                partnerAdditionRequestDto.getAddress(),
                partnerAdditionRequestDto.getGstin(),
                partnerAdditionRequestDto.getPan(),
                "Created",
                codeGenerator.generateAccountNumber());
        PartnerAccount newAccount = new PartnerAccount(partnerAdditionResponseDto.getPartnerReferenceNumber(),
                partnerAdditionRequestDto.getPartnerName(),
                partnerAdditionRequestDto.getLegalName(),
                partnerAdditionRequestDto.getMobileNumber(),
                partnerAdditionRequestDto.getEmailId(),
                partnerAdditionRequestDto.getAddress(),
                partnerAdditionRequestDto.getCity(),
                partnerAdditionRequestDto.getState(),
                partnerAdditionRequestDto.getPincode(),
                partnerAdditionRequestDto.getGstin(),
                partnerAdditionRequestDto.getPan(),
                partnerAdditionResponseDto.getStatus(),
                partnerAdditionResponseDto.getEscrowAccountNumber(),
                0);

         accountRepository.save(newAccount);

        return partnerAdditionResponseDto;
    }


    public SellerAdditionResponseDto createSellerAccount(@Valid SellerAdditionRequestDto sellerAdditionRequestDto) {
        CodeGenerator codeGenerator = new CodeGenerator();
        System.out.println(sellerAdditionRequestDto);
        SellerAdditionResponseDto sellerAdditionResponseDto = new SellerAdditionResponseDto(  sellerAdditionRequestDto.getMerchantOktaId(),
        		sellerAdditionRequestDto.getPartnerReferenceNumber(),
                sellerAdditionRequestDto.getPartnerName(),
                sellerAdditionRequestDto.getMerchantId(),
                sellerAdditionRequestDto.getEmailId(),
                sellerAdditionRequestDto.getMerchantName(),
                sellerAdditionRequestDto.getGstNumber(),
                sellerAdditionRequestDto.getCompanyPAN(),
                codeGenerator.generateAccountNumber() ,
                "Account Created");
        
        System.out.println(sellerAdditionResponseDto);
        SellerAccount sellerAccount = new SellerAccount(sellerAdditionRequestDto.getMerchantOktaId(),
                sellerAdditionRequestDto.getPartnerReferenceNumber(),
                sellerAdditionRequestDto.getPartnerName(),
                sellerAdditionRequestDto.getMerchantId() ,
                sellerAdditionRequestDto.getEmailId(),
                sellerAdditionRequestDto.getMerchantName(),
                sellerAdditionRequestDto.getGstNumber(),
                sellerAdditionRequestDto.getCompanyPAN(),
                sellerAdditionRequestDto.getBankAccountNumber(),
                "Active",
                Long.parseLong(codeGenerator.generateCardNumber()),
                0);
        System.out.println(sellerAccount);

        sellerAccountRepository.save(sellerAccount);

        return sellerAdditionResponseDto;
    }
}
