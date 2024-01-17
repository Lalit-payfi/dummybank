package com.example.bank.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "seller_account", schema = "online_bank")
public class SellerAccount {

    @Id
    private String merchantOktaId;
    
    private Long partnerReferenceNumber;
    private String partnerName;
    private Long merchantId;
    private String emailId;
    private String merchantName;
    private String gstNumber;
    private String companyPAN;
    private String bankAccountNumber;
    private String profileStatus;
    private Long cardNumber;
    private double balance;

    

}
