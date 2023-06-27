<!DOCTYPE html>
<html>
<head>
    <title>Выбор значения из вложенного списка</title>
    <script type="text/javascript" >

        function showData() {
            const currId = [];
            currId.push(431, 451, 456,452,462);
            var select = document.getElementById("select");
            var selectedValue = select.options[select.selectedIndex].value;
            var dataDiv = document.getElementById("dataDiv");

            var currencies = select.getAttribute("data-currencies").split(",").map(function(curr) {
                return JSON.parse(curr);
            });
            var xhr = new XMLHttpRequest();
            xhr.open("GET", "?message=" + selectedValue, true);
            xhr.send();
            var currency=select.getAttribute("data-currency");
            for (value of currId){
                if(value==selectedValue){
                    dataDiv.innerHTML = "Вы выбрали "+currencies.at(currId.indexOf(value));
                    //dataDiv.innerHTML =currency;
                    //dataDiv.innerHTML =selectedValue;
                }
            }

        }
    </script>
</head>
<body>
<h1>Выбор значения из вложенного списка</h1>
<select id="select" onchange="showData()"
        data-currency='${currency}'
        data-currencies='${currencies?map(c -> c.toJsonString())?join(",")}'>
    <option value="">Выберите валюту</option>
    <#list currencies as cur>
        <option value="${cur.id}">${cur}</option>
    </#list>
</select>

<br><div id="dataDiv"></div>
<#if message?has_content>
    <p>${message}</p>
</#if>

</body>
</html>