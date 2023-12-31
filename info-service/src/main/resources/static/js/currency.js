function updateInfo() {
    const select = document.getElementById("select");
    const selectedValue = select.options[select.selectedIndex].value;
    const dataDiv = document.getElementById("dataDiv");
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
        text => updateChart(text)
    );
}