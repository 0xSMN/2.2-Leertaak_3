<?php
include('config.php');

include('header.php');

if ($loggedin) {
	include('sidebar.php');
	include('welcome.php');
} else {
	include('login.php');
}

include('footer.php');

?>