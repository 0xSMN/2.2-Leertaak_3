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
        <select name="location">
            <option value="Abbottabad">Abbottabad</option>
            <option value="Ahmadpur East">Ahmadpur East</option>
            <option value="Bahawalpur">Bahawalpur</option>
            <option value="Faisalabad">Faisalabad</option>
            <option value="Gujranwala">Gujranwala</option>
            <option value="Gujrat">Gujrat</option>
            <option value="Hyderabad">Hyderabad</option>
            <option value="Islamabad">Islamabad</option>
            <option value="Jhang">Jhang</option>
            <option value="Karachi">Karachi</option>
            <option value="Lahore">Lahore</option>
            <option value="Larkana">Larkana</option>
            <option value="Multan">Multan</option>
            <option value="Peshawar">Peshawar</option>
            <option value="Quetta">Quetta</option>
            <option value="Rawalpindi">Rawalpindi</option>
            <option value="Sargodha">Sargodha</option>
            <option value="Sheikhupura">Sheikhupura</option>
            <option value="Sialkot">Sialkot</option>
            <option value="Sukkur">Sukkur</option>
        </select>
        <br><br>
        <input type="submit" value="Submit" onclick="<?php $location = $_GET["location"];?>">
    </form>
    <br><br><br>

    <?php echo $location; ?>

</div>

<canvas class="canvas" id="raingraph" width="700" height="400"></canvas>

</body>

