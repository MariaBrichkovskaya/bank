<!DOCTYPE html>
<html>
<head>
    <title>MoneyBank</title>
</head>
<body>
<h1>Банк денег</h1><hr>
<h4>Подробная информация о пользователе</h4>
<p>${client.name} ${client.surname}</p>
<b>Email </b>${client.email}<br><br>
<#if client.cards?has_content>
    <#list client.cards as card>
        <b>Карта </b>${card.number}
        <b>Баланс </b>${card.balance} р.<br>
    </#list>
<#else>
    <h3>Нет информации о картах</h3>
</#if>
</body>
</html>