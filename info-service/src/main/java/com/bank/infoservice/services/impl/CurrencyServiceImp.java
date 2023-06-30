package com.bank.infoservice.services.impl;

import com.bank.infoservice.dto.CurrencyDTO;
import com.bank.infoservice.services.CurrencyService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class CurrencyServiceImp implements CurrencyService {
    static CurrencyDTO model = new CurrencyDTO();
    SimpleDateFormat httpDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public String getStartDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -5);
        Date fiveDaysAgo = calendar.getTime();
        return httpDateFormat.format(fiveDaysAgo);
    }
    public JSONArray getCurrencyDynamic(String message) throws IOException{
        try {
            String dynamicUrl = "https://api.nbrb.by/exrates/rates/dynamics/";
            URL url = new URL( dynamicUrl + message+"?startDate="+getStartDate()+"&endDate="+httpDateFormat.format(new Date()));
            Scanner scanner = new Scanner((InputStream) url.getContent());
            StringBuilder result = new StringBuilder();
            while (scanner.hasNext()){
                result.append(scanner.nextLine());
            }
            return new JSONArray(result.toString());


        } catch (FileNotFoundException e){ // что-то замутить

            return new JSONArray();
        }

    }
    public CurrencyDTO getCurrencyRate(String message) throws IOException, ParseException {
        try {
            String currencyUrl = " https://api.nbrb.by/exrates/rates/";
            URL url = new URL(currencyUrl + message);

            Scanner scanner = new Scanner((InputStream) url.getContent());
            StringBuilder result = new StringBuilder();
            while (scanner.hasNext()){
                result.append(scanner.nextLine());
            }
            JSONObject object = new JSONObject(result.toString());
            model.setCur_ID(object.getInt("Cur_ID"));
            model.setDate(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(object.getString("Date")));
            model.setCur_Abbreviation(object.getString("Cur_Abbreviation"));
            model.setCur_Scale(object.getInt("Cur_Scale"));
            model.setCur_Name(object.getString("Cur_Name"));
            model.setCur_OfficialRate(object.getDouble("Cur_OfficialRate"));
            //if(model.getCur_ID()==null) return null;
        } catch (FileNotFoundException e){ // что-то замутить

        }
        return model;

    }


}
