$(document).ready(function() {
    function csvtoxml(file) {
        //csv to xml
        const rawFile = new XMLHttpRequest();
        rawFile.open("GET", file, false);
        rawFile.onreadystatechange = function ()
        {
            if (!(rawFile.readyState === 4 && (rawFile.status === 200 || rawFile.status === 0))) {
                return;
            }
            const csvData = rawFile.responseText;
            console.log(csvData);
            let dataArr=csvData.split("\n");
            console.log(dataArr);
            let heading=dataArr[0].split(",");
            console.log(heading);
            let data=dataArr.splice(1,dataArr.length-1);
            let xmlData=document.createElement("XmlData");
            for(let i=0;i<data.length-1;i++){
                console.log(data.length);
                let d=data[i].split(",");
                console.log(d);
                let weatherData=document.createElement("measurement");
                for(let j=0;j<d.length;j++){
                    let tag=document.createElement(heading[j].replace(/(\r\n|\n|\r)/gm,""));
                    tag.innerHTML=d[j].replace(/(\r\n|\n|\r)/gm,"");
                    weatherData.appendChild(tag);
                }
                xmlData.appendChild(weatherData);
            }
            console.log(xmlData);
            download('<?xml version="1.0" encoding="UTF-8"?>' + xmlData.outerHTML);
        };
        rawFile.send();
    }


    function download(xmlfile) {
        let element = document.createElement('a');
        element.setAttribute('href', 'data:text/plain;charset=utf-8,' + encodeURIComponent(xmlfile));
        let name;
        name = city + date + "_h" + hour + ".xml";
        element.setAttribute('download', name.replace(/ /g, ''));
        document.body.appendChild(element);

        element.click();

        document.body.removeChild(element);
    }

    //download chosen file after clicking on download button on the Download XML page
    $(".download-btn").click(function() {
        csvtoxml(csvfile);
    });

    //the choices submitted by the HTML form
    const date = $('.date').text();
    const date_split = date.split('-');
    const hour = $('.time').text();
    const location = $('.location').text();
    const city = $('.city').text();

    //select the right file
    let fileName = "/DB/_database_/"+date_split[0]+"/"+date_split[1]+"/"+date_split[2]+"/"+location+"/"+date+"_h"+hour+".csv";
    let csvfile = fileName.replace(/ /g, '');

});

