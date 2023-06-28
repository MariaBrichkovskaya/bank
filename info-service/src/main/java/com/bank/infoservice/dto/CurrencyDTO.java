package com.bank.infoservice.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyDTO {
    Integer cur_ID;
    Date date;
    String cur_Abbreviation = "null";
    Integer cur_Scale = 0;
    String cur_Name = "null";
    Double cur_OfficialRate = 0.0;
}
