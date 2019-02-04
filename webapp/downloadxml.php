<?php
/**
 * Created by PhpStorm.
 * User: AnneMarijke
 * Date: 21-1-2019
 * Time: 11:36
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
    <script src="js/csv_to_xml.js"></script>
</head>
<body>
<p class="title">Download XML</p>

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
        </select><br><br>
        <p>Date:</p>
        <?php
        // the date has to be a date between today and 4 weeks ago
        $fourweeksago = date('Y-m-d',strtotime('-4 week'));
        $today = date('Y-m-d');
        ?>
        <input title="date" type="date" name="date" value="<?php echo $today; ?>" min="<?php echo $fourweeksago; ?>" max="<?php echo $today; ?>">
        <br><br><p>Hour:</p>
        <select title="hour" name="hour">
            <?php for ($i = 0; $i < 24; $i++){
                //loop to put add all hours as options to the select box
                $hour = sprintf("%02d", $i); ?>
                <option value="<?php echo $hour ?>"><?php echo $hour ?>:00 </option>
            <?php } ?>
        </select>
        <br><br><br>
        <input type="submit" value="Submit" onclick="<?php $location = $_GET["location"];
        $date = $_GET["date"];
        $time = $_GET["hour"]; ?> ">
    </form>
    <br>

    <?php
    //the data needed in the data fetcher
    if ($location!=NULL){
        $value = (explode(", ",$location));
        $location = $value[1];
        $stn = $value[0]; ?>
        <a class="download-btn">Download</a>
        <div style="visibility: hidden">
            <p class="date"> <?php echo $date; ?> </p>
            <p class="time"> <?php echo $time; ?> </p>
            <p class="city"> <?php echo $location; ?></p>
            <p class="location"> <?php echo $stn; ?></p>
        </div>
    <?php } ?>

</div>
</body>