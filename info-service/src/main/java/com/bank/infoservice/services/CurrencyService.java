package com.bank.infoservice.services;

import com.bank.infoservice.dto.CurrencyDTO;

import java.io.IOException;
import java.text.ParseException;

public interface CurrencyService {
    CurrencyDTO getCurrencyRate(String message)  throws IOException, ParseException;
}
