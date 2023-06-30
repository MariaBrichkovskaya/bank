<!DOCTYPE html>
<html >
<head>
    <title>MONEY-BANK</title>
    <script type="text/javascript" >

        function updateInfo() {
            var select = document.getElementById("select");
            var selectedValue = select.options[select.selectedIndex].value;
            var dataDiv = document.getElementById("dataDiv");
            if (selectedValue === "") {
                dataDiv.innerHTML = "";
                document.getElementById("currencyChart").style.display="none";
                //document.getElementById("currencyChart").innerHTML = "<canvas id=`myChart` width=`200px` height=`50px`></canvas>";
                return;
            }
            const currencyUrl = "/currency?message=" + selectedValue;
            fetch(currencyUrl)
                .then(
                    response => response.text()
                ).then(
                text => dataDiv.innerHTML = text
            );
            const dynamicUrl = "/dynamic?message=" + selectedValue;
            fetch(dynamicUrl)
                .then(
                    response => response.text()
                ).then(
                text => showChart(text)
            );
            document.getElementById("currencyChart").style.display="block";
        }
        function showInfoButton() {
            document.getElementById("button").style.display="block";
        }
        setTimeout(showInfoButton,5000);
    </script>
</head>
<body>
<h1>Информация о курсах валют</h1>
<select id="select" onchange="updateInfo()">
    <option value="">Выберите валюту</option>
    <#list currencies as cur>
        <option value="${cur.id}">${cur}</option>
    </#list>
</select>
<br><br><div id="dataDiv"></div>
<br><br>
<div id="currencyChart" style="display: none">
    <canvas id="myChart" width="200px" height="50px"></canvas>
</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.min.js"></script>
<script>
    function showChart(text) {
        let dailyRateArray = JSON.parse(text);
        let dateArray = dailyRateArray.map(dailyRate => dailyRate.Date)
        let rateArray = dailyRateArray.map(dailyRate => dailyRate.Cur_OfficialRate)
        let ctx = document.querySelector('#myChart').getContext('2d');
        let myChart = new Chart(ctx, {
            type: 'line',
            data: {
                labels: dateArray,
                datasets: [{
                    label: 'Динамика выбранной валюты',
                    data: rateArray,
                    backgroundColorColor: [
                        'white'
                    ],
                    borderColor: [
                        '#80d8ff' // у каждой точки может быть свой цвет
                    ],
                    borderWidth: 4
                }]
            },
            options: {}

        })
    }
</script>
<div style="display: none" id="button">
    <a href="/consultant">
        <button>Онлайн консультант</button>
    </a>
</div>
</body>


</html>
