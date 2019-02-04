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
    <script src="js/weather_stations.js"></script>
    <script src="Highcharts/highcharts.js"></script>
    <script src="Highcharts/modules/exporting.js"></script>
    <script src="Highcharts/modules/export-data.js"></script>
    <script src="js/graph_data_fetcher.js"></script>
</head>
<body>

<p class="title">Rainfall</p>

<!--country choice buttons-->
<button class="country-btn" value="pakistan" style="left: 1210px"><img src="imgs/pakistan.png"/></button>
<button class="country-btn" value="afghanistan" style="left: 1250px"><img src="imgs/afghanistan.png"/></button>
<button class="country-btn" value="iran" style="left: 1290px"><img src="imgs/iran.png"/></button>
<button class="country-btn" value="india" style="left: 1330px"><img src="imgs/india.png"/></button>

<div class="choices">
    <form method="get" action="">
        <p>Location:</p>
        <select id="stations" title="location" name="location">
            <!--options are added by weather_stations.js-->
        </select>
        <br><br>
        <input type="submit" value="Submit" oninput="<?php $location = $_GET["location"]; ?>">
    </form>

    <?php
    //data needed by data fetcher
    $tdate = date('Y-m-d');
    $hour = date('H');

    if($location==NULL){
        $location="415710, Islamabad";
    }

    $value = (explode(", ",$location));
    $location = $value[1];
    $stn = $value[0];

    ?>

    <div style="visibility: hidden">
        <!--data needed by data fetcher-->
        <p class="tdate"> <?php echo $tdate; ?> </p>
        <p class="thour"> <?php echo $hour; ?> </p>
        <p class="city"> <?php echo $location; ?></p>
        <p class="current-location"> <?php echo $stn ?></p>
    </div>
</div>
<div class="canvas" id="container"></div>

</body>

