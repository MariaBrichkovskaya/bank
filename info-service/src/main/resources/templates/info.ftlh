<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <link rel='stylesheet' href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
    <link rel='stylesheet' href= "https://cdnjs.cloudflare.com/ajax/libs/bootstrap-material-design/4.0.2/bootstrap-material-design.css">
    <link rel='stylesheet' href= 'https://fonts.googleapis.com/icon?family=Material+Icons'>
    <link rel="stylesheet" href="css/index.css">
    <title>MONEY-BANK</title>
    <link rel="stylesheet" href="css/consultant.css" type="text/css">
    <script src="js/consultant.js"></script>
    <script src="js/currency.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.min.js"></script>
    <script src="js/chart.js"></script>
    <link rel="icon" href="favicon.png">
</head>

<body onload="newChart(); init();">
<h1 class="header">Информация о курсах валют</h1>
    <select class="selector" id="select" onchange="updateInfo()">
        <option value="">Выберите валюту</option>
        <#list currencies as cur>
            <option value="${cur.id}">${cur}</option>
        </#list>
    </select><br><br>

<div class="data" id="dataDiv"></div><br><br>
<div id="currencyChart" style="display: none">
    <canvas id="myChart" width="90%" height="30%"></canvas>
</div>

<div id="body" >
    <div id="chat-circle" style="display: none" class="btn btn-raised">
        <div id="chat-overlay"></div>
        <i class="material-icons">speaker_phone</i>
    </div>
    <div class="chat-box">
        <div class="chat-box-header">
            Консультант
            <span class="chat-box-toggle"><i
                class="material-icons">close</i></span>
        </div>
        <div class="chat-box-body">
            <div class="chat-box-overlay"></div>
            <div class="chat-logs" id="textarea">
            </div>
        </div>
        <div class="chat-input">
            <form onsubmit="return sendMessage()">
                <input type="text" id="chat-input" placeholder="Send a message...">
                <button class="chat-submit" id="chat-submit">
                    <i class="material-icons">send</i>
                </button>
            </form>
        </div>
    </div>
</div>

<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js'></script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0-alpha.6/js/bootstrap.min.js'></script>
<script src="js/index.js"></script>
</body>
</html>
