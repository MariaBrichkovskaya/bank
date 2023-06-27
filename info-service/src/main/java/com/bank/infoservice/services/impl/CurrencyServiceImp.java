package com.bank.infoservice.services.impl;

import com.bank.infoservice.dto.CurrencyDTO;
import com.bank.infoservice.services.CurrencyService;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
@Service
public class CurrencyServiceImp implements CurrencyService {
    static CurrencyDTO model = new CurrencyDTO();
    public CurrencyDTO getCurrencyRate(String message) throws IOException, ParseException {
        try {
            URL url = new URL(" https://api.nbrb.by/exrates/rates/" + message);

            Scanner scanner = new Scanner((InputStream) url.getContent());
            String result = "";
            while (scanner.hasNext()){
                result +=scanner.nextLine();
            }
            JSONObject object = new JSONObject(result);

            model.setCur_ID(object.getInt("Cur_ID"));
            model.setDate(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(object.getString("Date")));
            model.setCur_Abbreviation(object.getString("Cur_Abbreviation"));
            model.setCur_Scale(object.getInt("Cur_Scale"));
            model.setCur_Name(object.getString("Cur_Name"));
            model.setCur_OfficialRate(object.getDouble("Cur_OfficialRate"));
        } catch (FileNotFoundException e){ // что-то замутить

        }
        return model;

    }


}
