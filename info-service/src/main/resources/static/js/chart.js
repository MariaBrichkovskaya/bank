let myChart;
let options = {
    month: 'long',
    day: 'numeric'
};
function newChart() {
    let ctx = document.querySelector('#myChart').getContext('2d');
    myChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: [],
            datasets: [{
                label: 'Динамика выбранной валюты',
                data: [],
                backgroundColor: [
                    'rgba(0,0,0,0)'
                ],
                pointBorderColor: '#b61717',

                lineTension: 0,
                borderColor: [
                    '#b61717',
                ],
                borderWidth: 3,
            }]
        },
        options: {
            legend: {
                display: false
            },
            tooltips: {
                callbacks: {
                    label: function (tooltipItem) {
                        return tooltipItem.yLabel;
                    }
                }
            },
        }
    })
}

function updateChart(dataSets) {
    let dailyRateArray = JSON.parse(dataSets);
    let dateArray = dailyRateArray.map(dailyRate =>
        new Date(dailyRate.Date).toLocaleString("ru", options)
    );
    let rateArray = dailyRateArray.map(dailyRate => dailyRate.Cur_OfficialRate);
    myChart.data.labels=dateArray;
    myChart.data.datasets[0].data=rateArray;
    myChart.update();
}