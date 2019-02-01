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
    <script src="Highcharts/highcharts.js"></script>
    <script src="Highcharts/modules/exporting.js"></script>
    <script src="Highcharts/modules/export-data.js"></script>
    <script src="js/graph_data_fetcher.js"></script>
</head>
<body>

<p class="title">Rainfall</p>

<div class="choices">
    <form method="get" action="">
        <p>Location:</p>
        <select title="location" name="location">
            <!--todo: add real station numbers-->
            <option value="1, Islamabad">Islamabad Airport</option>
            <option value="2, Jiwani">Jiwani</option>
            <option value="3, Karachi">Karachi Airport</option>
            <option value="4, Lahore">Lahore Airport</option>
            <option value="5, Nawabshah">Nawabshah</option>
            <option value="6, Peshawar">Peshawar</option>
        </select>
        <br><br>
        <input type="submit" value="Submit" oninput="<?php $location = $_GET["location"]; ?>">
    </form>

    <?php
    //todo: remove test date
    $tdate = '2019-01-29';
    $hour = '01';
    //$tdate = date('Y-m-j');
    //$hour = date('H');

    //todo: change station nr
    if($location==NULL){
        $location="1, Islamabad";
    }

    $value = (explode(", ",$location));
    $location = $value[1];
    $stn = $value[0];

    ?>

    <div style="visibility: hidden">
        <p class="tdate"> <?php echo $tdate; ?> </p>
        <p class="thour"> <?php echo $hour; ?> </p>
        <p class="city"> <?php echo $location; ?></p>
        <p class="current-location"> <?php echo $stn ?></p>
    </div>
</div>
<div class="canvas" id="container"></div>


</body>

