<!DOCTYPE html>
<html >
<head>
    <title>Выбор значения из вложенного списка</title>
    <link rel="icon" href="%PUBLIC_URL%/static/favicon.ico" />
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
<select id="select" onchange="showData()">
    <option value="">Выберите валюту</option>
    <#list currencies as cur>
        <option value="${cur.id}">${cur}</option>
    </#list>
</select>
<br><br><div id="dataDiv"></div>
</body>
</html>
