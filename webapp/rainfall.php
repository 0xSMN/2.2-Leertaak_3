<?php
/**
 * Created by PhpStorm.
 * User: AnneMarijke
 * Date: 21-1-2019
 * Time: 11:33
 */

include('config.php');

include('header.php');

if ($loggedin) {
    include('sidebar.php');
} else {
    include('login.php');
}

include('footer.php');

?>

<html>
<head>
</head>
<body>

<p class="title">Rainfall</p>

<div class="choices">
    <form method="get" action="">
        <p>Location:</p>
        <select title="location" name="location">
            <option id="415710" value="Islamabad">Islamabad Airport</option>
            <option id="417560" value="Jiwani">Jiwani</option>
            <option id="417800" value="Karachi">Karachi Airport</option>
            <option id="416410" value="Lahore">Lahore Airport</option>
            <option id="417490" value="Nawabshah">Nawabshah</option>
            <option id="415300" value="Peshawar">Peshawar</option>
        </select>
        <br><br>
        <input type="submit" value="Submit" oninput="<?php $location = $_GET["location"]; ?>">
    </form>
</div>

<?php

if($location==NULL){
    $location="Islamabad";
}

?>

<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script src="https://code.highcharts.com/modules/export-data.js"></script>

<div class="canvas" id="container"></div>

<script>
    Highcharts.chart('container', {
        chart: {
            type: 'spline',
            animation: Highcharts.svg, // don't animate in old IE
            events: {
                load: function () {

                    // set up the updating of the chart each second
                    var series = this.series[0];
                    setInterval(function () {
                        var x = (new Date()).getTime(), // current time
                            y = Math.random();
                        series.addPoint([x, y], true, true);
                    }, 1000);
                }
            }
        },

        time: {
            useUTC: false
        },

        title: {
            text: "Rainfall in <?php echo $location; ?>"
        },
        xAxis: {
            type: 'datetime',
            tickPixelInterval: 150
        },
        yAxis: {
            title: {
                text: 'Rain in ml'
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#2e3192'
            }]
        },
        tooltip: {
            headerFormat: '<b>{series.name}</b><br/>',
            pointFormat: '{point.x:%Y-%m-%d %H:%M:%S}<br/>{point.y:.2f}'
        },
        legend: {
            enabled: false
        },
        exporting: {
            enabled: false
        },
        series: [{
            name: 'Rainfall',
            data: (function () {
                // generate an array of random data
                var data = [],
                    time = (new Date()).getTime(),
                    i;

                for (i = -19; i <= 0; i += 1) {
                    data.push({
                        x: time + i * 1000,
                        y: Math.random()
                    });
                }
                return data;
            }())
        }]
    });
</script>

</body>

