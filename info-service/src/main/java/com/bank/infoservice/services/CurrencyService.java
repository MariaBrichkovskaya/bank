package com.bank.infoservice.services;

import com.bank.infoservice.dto.CurrencyDTO;
import org.json.JSONArray;

import java.io.IOException;
import java.text.ParseException;

public interface CurrencyService {
    CurrencyDTO getCurrencyRate(String message)  throws IOException, ParseException;
    JSONArray getCurrencyDynamic(String message) throws IOException;
}
