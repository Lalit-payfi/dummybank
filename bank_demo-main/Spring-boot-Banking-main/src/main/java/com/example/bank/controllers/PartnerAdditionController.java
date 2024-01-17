package com.example.bank.controllers;

import com.example.bank.dto.PartnerAdditionRequestDto;
import com.example.bank.dto.PartnerAdditionResponseDto;
import com.example.bank.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/partner")
public class PartnerAdditionController {

	@Autowired
    private AccountService accountService;

	/*
    @Autowired
    public PartnerAdditionController(AccountService accountService) {
        this.accountService = accountService;
    }

*/





}
