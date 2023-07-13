<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org" >
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bank Card</title>
    <link rel="stylesheet" href="card.css" type="text/css">

</head>

<body>
<div class="carousel">
<#list cards as card>
    <div class="card">
        <div class="bank-name">Money Bank</div>
        <div class="logo">
            <img src="mastercard.png" alt="Mastercard Logo">
        </div>
        <div class="card-details">
            <div class="card-number">${card.number?substring(0,4)} ${card.number?substring(4,8)} ${card.number?substring(8,12)} ${card.number?substring(12,16)}</div>
            <div class="expiry-date">${card.expiryDate.month.value}/${card.expiryDate.year}</div>
            <div class="cardholder-name">${card.clientId}</div>
        </div>
        <img class="chip" src="chip.png" alt="Chip">
    </div>
</#list>
</div>


</body>
</html>
