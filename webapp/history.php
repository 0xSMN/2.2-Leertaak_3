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
    <script src="jquery/jquery.min.js"></script>
    <script src="js/data_fetcher.js"></script>

</head>
<body>

<p class="title">Weather History</p>

<div class="choices">
    <form method="get" action="">
    <p>Location:</p>
        <select title="location" name="location">
            <option value="Islamabad">Islamabad Airport</option>
            <option value="Jiwani">Jiwani</option>
            <option value="Karachi">Karachi Airport</option>
            <option value="Lahore">Lahore Airport</option>
            <option value="Nawabshah">Nawabshah</option>
            <option value="Peshawar">Peshawar</option>
        </select><br><br>
    <p>Date:</p>
        <?php
        $fourweeksago = date('Y-m-j',strtotime('-4 week'));
        $today = date('Y-m-j');
        ?>
        <input title="date" type="date" name="date" value="<?php echo $today; ?>" min="<?php echo $fourweeksago; ?>" max="<?php echo $today; ?>">
        <br><br>
        <input type="submit" value="Submit" onclick="<?php $location = $_GET["location"];
        $date = $_GET["date"]?>">
    </form>
    <br>

    <?php

    if($location==NULL){
        $location="Islamabad";
        $date=$today;
    }

    ?>

</div>
    <table class="fixed_header">
        <thead>
            <tr>
                <th><?php echo $location ?></th>
                <th></th>
                <th></th>
                <th style="text-align: right" class="current-date"><?php echo $date ?></th>
            </tr>
            <tr class="head">
                <th>Time</th> <!––in hours-->
                <th>Temperature</th> <!––TEMP-->
                <th>Dew Point</th> <!––DEWP-->
                <th>Wind Speed</th> <!––WDSP-->
                <th>Precipitation</th> <!––PRCP (rainfall)-->
                <th>Cloud Cover</th> <!––CLDC-->
            </tr>
        </thead>
        <tbody>
        <?php for ($i = 0; $i < 24; $i++) {?>
            <tr id="<?php echo $i ?>">
                <td><?php echo $i ?>:00</td>
            </tr>
        <?php } ?>
        </tbody>
    </table>


</body>