<?php
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
    <p class="title">Welcome</p>
</head>
<body>
<p class="text">This is the weather application for UIC Crop Insurance Pakistan.</p>
<p class="text">On the page "Rainfall" you can view a live data visualisation of the rainfall in all the locations with weather stations in Pakistan.</p>
<p class="text">You can also look up weather data up to four weeks back on the page "Weather History".</p>
</body>