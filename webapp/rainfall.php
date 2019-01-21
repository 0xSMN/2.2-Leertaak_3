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
    <p class="title">Rainfall</p>
</head>
<body>

<div class="choices">
    <p>Location:</p>
    <select>
        <option value="abbottabad" selected>Abbottabad</option>
        <option value="ahmadpureast">Ahmadpur East</option>
        <option value="bahawalpur">Bahawalpur</option>
        <option value="faisalabad">Faisalabad</option>
        <option value="gujranwala">Gujranwala</option>
        <option value="gujrat">Gujrat</option>
        <option value="hyderabad">Hyderabad</option>
        <option value="islamabad">Islamabad</option>
        <option value="jhang">Jhang</option>
        <option value="karachi">Karachi</option>
        <option value="lahore">Lahore</option>
        <option value="larkana">Larkana</option>
        <option value="multan">Multan</option>
        <option value="peshawar">Peshawar</option>
        <option value="quetta">Quetta</option>
        <option value="rawalpindi">Rawalpindi</option>
        <option value="sargodha">Sargodha</option>
        <option value="sheikhupura">Sheikhupura</option>
        <option value="sialkot">Sialkot</option>
        <option value="sukkur">Sukkur</option>
    </select>
    <p>Date:</p>
    <input type="date" name="date">
    <p></p>
    <button>Submit</button>
</div>

<canvas class="canvas" id="raingraph" width="700" height="400"></canvas>

</body>

