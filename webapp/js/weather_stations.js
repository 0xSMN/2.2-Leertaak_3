$(document).ready(function() {
    function add_options(options) {

        options.forEach(option =>
            select.add(
                new Option(option.text, option.value, option.selected)
            ))

    }

    let select = document.getElementById("stations").options;

    let pakistan = [
        {
            text: 'Islamabad Airport',
            value: '415710, Islamabad',
            selected: true
        },
        {
            text: 'Jiwani',
            value: '417560, Jiwani'
        },
        {
            text: 'Karachi Airport',
            value: '417800, Karachi'
        },
        {
            text: 'Lahore Airport',
            value: '416410, Lahore'
        },
        {
            text: 'Nawabshah',
            value: '417490, Nawabshah'
        },
        {
            text: 'Peshawar',
            value: '415300, Peshawar'
        }
    ];

    add_options(pakistan);

});