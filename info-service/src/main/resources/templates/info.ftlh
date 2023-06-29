<!DOCTYPE html>
<html >
<head>
    <title>MONEY-BANK</title>
    <link rel="icon" href="../static/favicon.ico" />
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
<h1>Информация о курсах валют</h1>
<select id="select" onchange="showData()">
    <option value="">Выберите валюту</option>
    <#list currencies as cur>
        <option value="${cur.id}">${cur}</option>
    </#list>
</select>
<br><br><div id="dataDiv"></div>
<br><br><div>
    <canvas id="myChart" width="200px" height="50px"></canvas>
</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.min.js"></script>
<script>
    let ctx=document.querySelector('#myChart').getContext('2d');
    let myChart=new Chart(ctx,{
        type:'line',
        data:{
            labels:[1,2,3,4,5],
            datasets:[{
                label:'Динамика выбранной валюты',
                data:[50,40,30,20,10],
                backgroundColorColor:[
                    'white'
                ],
                borderColor:[
                    '#80d8ff' // у каждой точки может быть свой цвет
                ],
                borderWidth: 4
            }]
        },
        options:{}

    })
</script>
</body>


</html>
