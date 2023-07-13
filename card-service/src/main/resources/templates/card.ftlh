<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org" >
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bank Card</title>
    <link rel="stylesheet" href="card.css" type="text/css">
    <script src="card.js"></script>
</head>

<body>
<div class="slideshow-container">
<#list cards as card>
    <div class="card">
        <div class="bank-name">Money Bank</div>
        <div class="logo">
            <#if card.type.name()=="MASTER">
                <img src="mastercard.png" alt="Mastercard Logo">
            <#elseif card.type.name()=="VISA">
                <img src="visa.png" alt="Visa Logo">
            </#if>
        </div>
        <div class="card-details">
            <div class="card-number">${card.number?substring(0,4)} ${card.number?substring(4,8)} ${card.number?substring(8,12)} ${card.number?substring(12,16)}</div>

            <#assign formattedMonth = card.expiryDate.month.value?string("00")>
            <#assign formattedYear = card.expiryDate.year?string("0000")>
            <div class="expiry-date">${formattedMonth}/${formattedYear} </div>
            <div class="cardholder-name">${card.clientId}</div>
        </div>
        <img class="chip" src="chip.png" alt="Chip">
    </div>
</#list>
    <a class="prev" onclick="plusSlides(-1)">&#10094;</a>
    <a class="next" onclick="plusSlides(1)">&#10095;</a>
</div>
<div >
    <#list cards as card>
        <span class="dot" onclick="currentSlide(${card.id+1})"></span>
    </#list>
</div>
</body>
</html>
