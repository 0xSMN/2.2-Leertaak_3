$(document).ready(function() {
    function readTextFile(file, thisMinute, nextMinute) {
        const rawFile = new XMLHttpRequest();
        rawFile.open("GET", file, false);
        rawFile.onreadystatechange = function ()
        {
            if (!(rawFile.readyState === 4 && (rawFile.status === 200 || rawFile.status === 0))) {
                return;
            }
            const allText = rawFile.responseText;
            //Todo: get PRCP data where DATETIME = between thisMinute & nextMinute
        };
        rawFile.send(null);
    }

    function datetime_to_long(date, time) {
        let input = date + " " + time;
        input = input.map(function (date){
            return Date.parse(date+"0100")/1000;
        }
        return input;
    }

    function next_minute(long_datetime) {
        return long_datetime+60000;
    }

    const t_date = $('.tdate').text();
    const t_date_split = t_date.split('-');
    const time = $('.time').text();
    const time_split = time.split(':');
    const current_location = $('.current-location').text();

    const this_min = datetime_to_long(t_date, time);
    const next_min = next_minute(this_min);

    readTextFile("http://localhost/2.2-Leertaak_3/webapp/_database_/" + t_date_split[0] + "/" + t_date_split[1] + "/" + t_date_split[2] + "/" + current_location + "/" + t_date + "_h" + time_split[0] +".csv", this_min, next_min);
});