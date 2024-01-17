package com.example.bank.controllers;

import com.example.bank.dto.PartnerAdditionRequestDto;
import com.example.bank.dto.PartnerAdditionResponseDto;
import com.example.bank.dto.PaymentRequestDto;
import com.example.bank.dto.PaymentResponseDto;
import com.example.bank.dto.SellerAdditionRequestDto;
import com.example.bank.dto.SellerAdditionResponseDto;
import com.example.bank.services.AccountService;
import com.example.bank.services.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

import static com.example.bank.constants.constants.*;

@RestController
@RequestMapping("/bank")
public class TransactionRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionRestController.class);

    @Autowired
    private AccountService accountService;
    
    @Autowired
    private TransactionService transactionService;


//    @PostMapping(value = "/transactions",
//            consumes = MediaType.APPLICATION_JSON_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> makeTransfer(
//            @Valid @RequestBody TransactionInput transactionInput) {
//        if (InputValidator.isSearchTransactionValid(transactionInput)) {
////            new Thread(() -> transactionService.makeTransfer(transactionInput));
//            boolean isComplete = transactionService.makeTransfer(transactionInput);
//            return new ResponseEntity<>(isComplete, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(INVALID_TRANSACTION, HttpStatus.BAD_REQUEST);
//        }
//    }

    
    @PostMapping(value = "/partneraddition",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PartnerAdditionResponseDto> createPartnerAccount(
            @Valid @RequestBody PartnerAdditionRequestDto partnerAdditionRequestDto) {

        PartnerAdditionResponseDto partner = accountService.createPartnerAccount(partnerAdditionRequestDto);

        return new ResponseEntity<PartnerAdditionResponseDto>(partner, HttpStatus.OK);

    }
    
    @PostMapping(value = "/selleraddition",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SellerAdditionResponseDto> createSellerAccount(
            @Valid @RequestBody SellerAdditionRequestDto sellerAdditionRequestDto) {


        SellerAdditionResponseDto seller = accountService.createSellerAccount(sellerAdditionRequestDto);

        return new ResponseEntity<SellerAdditionResponseDto>( seller , HttpStatus.OK);

    }

    
    @PostMapping(value = "/getpayment",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaymentResponseDto> deposit(
            @Valid @RequestBody PaymentRequestDto paymentRequestDto) {
        LOGGER.debug("Triggered AccountRestController.withdrawInput");
        
        PaymentResponseDto paymentResponseDto = new PaymentResponseDto();
        
        paymentResponseDto.setTransactionId(123456L);
        paymentResponseDto.setMerchantOktaId("merchant123");
        paymentResponseDto.setEmailId("merchant@example.com");
        paymentResponseDto.setMerchantName("Example Merchant");

        paymentResponseDto.setCardNumber(4111111111111111L);
        paymentResponseDto.setExpiryDate("12/23");
        paymentResponseDto.setCardHolderName("John Doe");
        paymentResponseDto.setCVV(123);
        paymentResponseDto.setAmount(100.50);

        paymentResponseDto.setPartnerReferenceNumber(789012L);
        paymentResponseDto.setEscrowAccountNumber("Escrow123");

        paymentResponseDto.setStatus("Success");
        paymentResponseDto.setResponseCode("200");
        
            return new ResponseEntity<>(paymentResponseDto, HttpStatus.OK);

    }

    
//    @PostMapping(value = "/deposit",
//            consumes = MediaType.APPLICATION_JSON_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> deposit(
//            @Valid @RequestBody DepositInput depositInput) {
//        LOGGER.debug("Triggered AccountRestController.depositInput");
//
//        // Validate input
//        if (InputValidator.isAccountNoValid(depositInput.getTargetAccountNo())) {
//            // Attempt to retrieve the account information
//            Account account = accountService.getAccount(depositInput.getTargetAccountNo());
//
//            // Return the account details, or warn that no account was found for given input
//            if (account == null) {
//                return new ResponseEntity<>(NO_ACCOUNT_FOUND, HttpStatus.OK);
//            } else {
//                transactionService.updateAccountBalance(account, depositInput.getAmount(), ACTION.DEPOSIT);
//                return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
//            }
//        } else {
//            return new ResponseEntity<>(INVALID_SEARCH_CRITERIA, HttpStatus.BAD_REQUEST);
//        }
//    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return errors;
    }
}
