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
    <script src="jquery/jquery-3.3.1.js"></script>
    <script src="js/graph_data_fetcher.js"></script>
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script src="https://code.highcharts.com/highcharts.js"></script>
    <script src="https://code.highcharts.com/modules/exporting.js"></script>
    <script src="https://code.highcharts.com/modules/export-data.js"></script>
</head>
<body>

<p class="title">Rainfall</p>

<div class="choices">
    <form method="get" action="">
        <p>Location:</p>
        <select title="location" name="location">
            <option value="415710, Islamabad">Islamabad Airport</option>
            <option value="417560, Jiwani">Jiwani</option>
            <option value="417800, Karachi">Karachi Airport</option>
            <option value="416410, Lahore">Lahore Airport</option>
            <option value="417490, Nawabshah">Nawabshah</option>
            <option value="415300, Peshawar">Peshawar</option>
        </select>
        <br><br>
        <input type="submit" value="Submit" oninput="<?php $location = $_GET["location"]; ?>">
    </form>

    <?php

    $tdate = date('Y-m-j');
    $hour = date('H:i:s');

    if($location==NULL){
        $location="415710, Islamabad";
    }

    $value = (explode(", ",$location));
    $location = $value[1];
    $stn = $value[0];

    ?>

    <div style="visibility: hidden">
        <p class="tdate"> <?php echo $tdate; ?> </p>
        <p class="thour"> <?php echo $hour; ?> </p>
        <p class="current-location"> <?php echo $stn ?></p>
    </div>
</div>
<div class="canvas" id="container"></div>

<script>
    Highcharts.chart('container', {
        chart: {
            type: 'spline',
            animation: Highcharts.svg, // don't animate in old IE
            events: {
                load: function () {

                    // set up the updating of the chart every minute
                    let series = this.series[0];
                    setInterval(function () {
                        let x = (new Date()).getTime(), // current time
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
                text: 'Precipitation in cm'
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
                let data = [],
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

