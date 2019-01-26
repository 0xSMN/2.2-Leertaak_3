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
                <th style="text-align: right"><?php echo $date ?></th>
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
            <tr>
                <td>00:00</td>
                <td>6</td>
                <td>5</td>
                <td>3</td>
                <td>0</td>
                <td>12%</td>
            </tr>
            <tr>
                <td>01:00</td>
                <td>6</td>
                <td>5</td>
                <td>3</td>
                <td>0</td>
                <td>12%</td>
            </tr>
            <tr>
                <td>02:00</td>
                <td>5</td>
                <td>5</td>
                <td>4</td>
                <td>0</td>
                <td>12%</td>
            </tr>
            <tr>
                <td>03:00</td>
                <td>5</td>
                <td>5</td>
                <td>4</td>
                <td>0</td>
                <td>12%</td>
            </tr>
            <tr>
                <td>04:00</td>
                <td>4</td>
                <td>4</td>
                <td>4</td>
                <td>0</td>
                <td>12%</td>
            </tr>
            <tr>
                <td>05:00</td>
                <td>4</td>
                <td>4</td>
                <td>4</td>
                <td>0</td>
                <td>12%</td>
            </tr>
            <tr>
                <td>06:00</td>
                <td>3</td>
                <td>4</td>
                <td>4</td>
                <td>0</td>
                <td>12%</td>
            </tr>
            <tr>
                <td>07:00</td>
                <td>2</td>
                <td>4</td>
                <td>4</td>
                <td>0</td>
                <td>12%</td>
            </tr>
            <tr>
                <td>08:00</td>
                <td>4</td>
                <td>4</td>
                <td>4</td>
                <td>0</td>
                <td>12%</td>
            </tr>
            <tr>
                <td>09:00</td>
                <td>6</td>
                <td>4</td>
                <td>4</td>
                <td>0</td>
                <td>12%</td>
            </tr>
            <tr>
                <td>10:00</td>
                <td>9</td>
                <td>4</td>
                <td>4</td>
                <td>0</td>
                <td>12%</td>
            </tr>
            <tr>
                <td>11:00</td>
                <td>11</td>
                <td>4</td>
                <td>4</td>
                <td>0</td>
                <td>12%</td>
            </tr>
            <tr>
                <td>12:00</td>
                <td>13</td>
                <td>2</td>
                <td>7</td>
                <td>0</td>
                <td>25%</td>
            </tr>
            <tr>
                <td>13:00</td>
                <td>14</td>
                <td>1</td>
                <td>9</td>
                <td>0</td>
                <td>25%</td>
            </tr>
            <tr>
                <td>14:00</td>
                <td>15</td>
                <td>1</td>
                <td>11</td>
                <td>0</td>
                <td>25%</td>
            </tr>
            <tr>
                <td>15:00</td>
                <td>16</td>
                <td>1</td>
                <td>15</td>
                <td>0</td>
                <td>25%</td>
            </tr>
            <tr>
                <td>16:00</td>
                <td>15</td>
                <td>1</td>
                <td>17</td>
                <td>0</td>
                <td>25%</td>
            </tr>
            <tr>
                <td>17:00</td>
                <td>13</td>
                <td>3</td>
                <td>15</td>
                <td>0</td>
                <td>25%</td>
            </tr>
            <tr>
                <td>18:00</td>
                <td>11</td>
                <td>2</td>
                <td>11</td>
                <td>0</td>
                <td>25%</td>
            </tr>
            <tr>
                <td>19:00</td>
                <td>10</td>
                <td>3</td>
                <td>10</td>
                <td>0</td>
                <td>25%</td>
            </tr>
            <tr>
                <td>20:00</td>
                <td>9</td>
                <td>4</td>
                <td>9</td>
                <td>0</td>
                <td>25%</td>
            </tr>
            <tr>
                <td>21:00</td>
                <td>8</td>
                <td>4</td>
                <td>8</td>
                <td>0</td>
                <td>25%</td>
            </tr>
            <tr>
                <td>22:00</td>
                <td>7</td>
                <td>4</td>
                <td>7</td>
                <td>0</td>
                <td>25%</td>
            </tr>
            <tr>
                <td>23:00</td>
                <td>7</td>
                <td>4</td>
                <td>6</td>
                <td>0</td>
                <td>25%</td>
            </tr>
        </tbody>
    </table>


</body>