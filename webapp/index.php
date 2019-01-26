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
	include('welcome.php');
} else {
	include('login.php');
}

include('footer.php');
