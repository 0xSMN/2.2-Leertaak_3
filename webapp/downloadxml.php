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
    <script src="js/csv_to_xml.js"></script>
</head>
<body>
<p class="title">Download XML</p>
<div class="choices">
    <form method="get" action="">
        <p>Location:</p>
        <select title="location" name="location">
            <!--todo: change to real stn-->
            <option value="1, Islamabad">Islamabad Airport</option>
            <!--<option value="415710, Islamabad">Islamabad Airport</option>-->
            <option value="417560, Jiwani">Jiwani</option>
            <option value="417800, Karachi">Karachi Airport</option>
            <option value="416410, Lahore">Lahore Airport</option>
            <option value="417490, Nawabshah">Nawabshah</option>
            <option value="415300, Peshawar">Peshawar</option>
        </select><br><br>
        <p>Date:</p>
        <?php
        $fourweeksago = date('Y-m-d',strtotime('-4 week'));
        $today = date('Y-m-d');
        ?>
        <input title="date" type="date" name="date" value="<?php echo $today; ?>" min="<?php echo $fourweeksago; ?>" max="<?php echo $today; ?>">
        <br><br><p>Hour:</p>
        <select title="hour" name="hour">
            <?php for ($i = 0; $i < 24; $i++){
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
    if ($location!=NULL){
        $value = (explode(", ",$location));
        $location = $value[1];
        $stn = $value[0]; ?>
        <a class="download-btn">Download</a>
        <div style="visibility: hidden">
            <p class="date"> <?php echo $date; ?> </p>
            <p class="time"> <?php echo $time; ?> </p>
            <p class="location"> <?php echo $stn; ?></p>
        </div>
    <?php } ?>
</div>
</body>