<!DOCTYPE html>
<html>
<head>
    <title>Выбор значения из вложенного списка</title>
    <link rel="icon" href="favicon.ico" type="image/x-icon"><link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
    <script type="text/javascript" >

        function showData() {
            var select = document.getElementById("select");
            var selectedValue = select.options[select.selectedIndex].value;
            var dataDiv = document.getElementById("dataDiv");
            const url = "/?message=" + selectedValue;
            fetch(url)
                .then(
                    response => response.text()
                ).then(
                text => dataDiv.innerHTML = text
            );
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
<br><br><div id="dataDiv"></div>
</body>
</html>
