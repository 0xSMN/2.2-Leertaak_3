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
    <script src="js/data_fetcher.js"></script>
    <script src="js/weather_stations.js"></script>
</head>
<body>

<p class="title">Weather History</p>

<button class="country-btn" value="pakistan" style="left: 1220px">PK</button>
<button class="country-btn" value="afghanistan" style="left: 1260px">AF</button>
<button class="country-btn" value="iran" style="left: 1300px">IR</button>
<button class="country-btn" value="india" style="left: 1335px">IN</button>

<div class="choices">
    <form method="get" action="">
    <p>Location:</p>
        <select id="stations" title="location" name="location">
            <!--options are added by weather_stations.js-->
        </select><br><br>
    <p>Date:</p>
        <?php
        $fourweeksago = date('Y-m-d',strtotime('-4 week'));
        $today = date('Y-m-d');
        ?>
        <input title="date" type="date" name="date" value="<?php echo $today; ?>" min="<?php echo $fourweeksago; ?>" max="<?php echo $today; ?>">
        <br><br>
        <input type="submit" value="Submit" onclick="<?php $location = $_GET["location"];
        $thedate = $_GET["date"]?>">
    </form>
    <br>

    <?php

    if($location==NULL){
        //Todo: change stn
        $location="415710, Islamabad";
        $thedate=$today;
    }

    $value = (explode(", ",$location));
    $location = $value[1];
    $stn = $value[0];

    ?>

</div>
    <table class="fixed_header">
        <thead>
            <tr>
                <th><?php echo $location ?></th>
                <th style="visibility: hidden" class="current-location"><?php echo $stn ?></th>
                <th></th><th></th><th></th><th></th>
                <th style="text-align: right" class="current-date"><?php echo $thedate ?></th>
            </tr>
            <tr class="head">
                <th>Time</th> <!––in hours-->
                <th>Temperature</th> <!––TEMP-->
                <th>Dew Point</th> <!––DEWP-->
                <th>Wind Speed</th> <!––WDSP-->
                <th>Precipitation</th> <!––PRCP (rainfall)-->
                <th>Snowfall &nbsp; &nbsp;</th> <!--SNDP-->
                <th>Cloud Cover</th> <!––CLDC-->
            </tr>
        </thead>
        <tbody>
        <?php for ($i = 0; $i < 24; $i++) {?>
            <tr id="<?php echo $i ?>">
                <td><?php if ($i < 10){
                    echo "0$i";
                    } else {
                        echo $i;
                    } ?>:00</td>
            </tr>
        <?php } ?>
        </tbody>
    </table>


</body>