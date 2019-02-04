<?php
/**
 * Created by PhpStorm.
 * User: Anne Marijke
 * Date: 15-1-2019
 * Time: 21:09
 */

?>
<!-- navigatie -->
<div class="sidebar">
	<div class="sidebar-inner">
		<div class="sidebar-logo">
            <!--add logo to sidebar-->
            <a href="index.php"><img src="imgs/logo.png"/></a>
		</div>
		<nav class="sidenav">
			<ul class="sidebar-menu">
                <!--buttons to the pages-->
				<li><a href="rainfall.php">Rainfall</a></li>
				<li><a href="history.php">Weather History</a></li>
				<li><a href="downloadxml.php">Download XML</a></li>
			</ul>
            <!--logout button on the bottom of the sidebar-->
			<a href="index.php?logout=1"><button class="logout-button">Logout</button></a>
		</nav>
	</div>
</div>
<div class="container">