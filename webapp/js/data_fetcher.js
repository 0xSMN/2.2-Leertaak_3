$(document).ready(function() {
    function readTextFile(file)
    {
        const rawFile = new XMLHttpRequest();
        rawFile.open("GET", file, false);
        rawFile.onreadystatechange = function ()
        {
            if(rawFile.readyState === 4)
            {
                if(rawFile.status === 200 || rawFile.status === 0)
                {
                    const allText = rawFile.responseText;
                    const data_needed = allText.substring(70, 138);
                    const array_data_needed = data_needed.split(',');
                    console.log(array_data_needed[1]);
                    $("#1").append('<td>'+array_data_needed[2]+'</td>');
                }
            }
        };
        rawFile.send(null);
    }

    const current_date = $('.current-date').text();
    const current_date_split = current_date.split('-');

    readTextFile("http://localhost/w/webapp/_database_/" + current_date_split[0] + "/" + current_date_split[1] + "/" + current_date_split[2] + "/1/" + current_date + "_h01.csv");

});