var options = {
    year: 'numeric',
    month: 'long',
    day: 'numeric'
};
function showChart(text) {
    let dailyRateArray = JSON.parse(text);
    let dateArray = dailyRateArray.map(dailyRate =>
        new Date(dailyRate.Date).toLocaleString("ru", options)
    );
    let rateArray = dailyRateArray.map(dailyRate => dailyRate.Cur_OfficialRate);
    let ctx = document.querySelector('#myChart').getContext('2d');
    let myChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: dateArray,
            datasets: [{
                label: 'Динамика выбранной валюты',
                data: rateArray,
                backgroundColor: [
                    'rgba(0,0,0,0)'
                ],
                pointBorderColor: '#b61717',

                lineTension:0,
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
                    label: function(tooltipItem) {
                        return tooltipItem.yLabel;
                    }
                }
            },
        }
    })
}