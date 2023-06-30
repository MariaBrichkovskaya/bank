<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>MONEY-BANK</title>
    <link rel="stylesheet" href="css/consultant.css" type="text/css">
    <script src="js/consultant.js"></script>
    <script src="js/currency.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.min.js"></script>
    <script src="js/chart.js"></script>
    <link rel="icon" href="favicon.png">
</head>

<body onload="init()">
<h1 style="text-align: center">Информация о курсах валют</h1>
    <select id="select" onchange="updateInfo()">
        <option value="">Выберите валюту</option>
        <#list currencies as cur>
            <option value="${cur.id}">${cur}</option>
        </#list>
    </select><br><br>

<div id="dataDiv"></div><br><br>
<div id="currencyChart" style="display: none">
    <canvas id="myChart" width="200px" height="50px"></canvas>
</div>

<div style="display: none" id="button">
<a href="#dialog"><button>Онлайн консультант</button></a>
</div>

<div id="dialog" class="dialog" >
        <div id="window" class="window">
            <div id="chatbox">
                <textarea id="messages" rows="20" cols="50" readonly="readonly"></textarea>
            </div>
            <form onsubmit="return sendMessage()">
                <input type="text" id="message" size="40"/>
                <input type="button" value="Отправить" onclick="sendMessage()"/>
            </form>
            <a href="#" class="close">Закрыть окно</a>
        </div>
</div>
</body>
</html>
