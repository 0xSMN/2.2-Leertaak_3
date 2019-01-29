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
</head>
<body>
<p class="title">Download XML</p>
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
        <input type="submit" value="Download" onclick="<?php $location = $_GET["location"];
        $date = $_GET["date"]?>">
    </form>
    <br>

    <?php

    if($location==NULL){
        $location="Islamabad";
        $date=$today;
    }

    ?>

</body>