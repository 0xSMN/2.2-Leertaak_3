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
</div>

    <?php

    $dataPoints = array(
        array("x" => 946665000000, "y" => 3),
        array("x" => 978287400000, "y" => 3),
        array("x" => 1009823400000, "y" => 2),
        array("x" => 1041359400000, "y" => 2),
        array("x" => 1072895400000, "y" => 2),
        array("x" => 1104517800000, "y" => 1),
        array("x" => 1136053800000, "y" => 1),
        array("x" => 1167589800000, "y" => 2),
        array("x" => 1199125800000, "y" => 1),
        array("x" => 1230748200000, "y" => 2),
        array("x" => 1262284200000, "y" => 6),
        array("x" => 1293820200000, "y" => 5),
        array("x" => 1325356200000, "y" => 4),
        array("x" => 1356978600000, "y" => 3),
        array("x" => 1388514600000, "y" => 2),
        array("x" => 1420050600000, "y" => 1),
        array("x" => 1451586600000, "y" => 2)
    );

    ?>
        <script>
            window.onload = function () {

                var chart = new CanvasJS.Chart("chartContainer", {
                    animationEnabled: true,
                    title:{
                        text: "<?php echo $location; ?>"
                    },
                    axisY: {
                        title: "Rainfall in ml",
                        suffix: "ml",
                    },
                    axisX: {
                        title: "Minutes"
                    },
                    data: [{
                        type: "spline",
                        markerSize: 5,
                        xValueFormatString: "DD-MM-YYYY",
		                yValueFormatString: "#,#",
		                xValueType: "dateTime",
		                dataPoints: <?php echo json_encode($dataPoints, JSON_NUMERIC_CHECK); ?>
                    }]
                });

                chart.render();

            }
        </script>

    <div class="canvas" id="chartContainer" style="height: 400px; width: 700px;"></div>
    <script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>


</canvas>

</body>

