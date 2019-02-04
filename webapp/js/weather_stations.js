$(document).ready(function() {
    function add_options(options) {

        options.forEach(option =>
            select.add(
                new Option(option.text, option.value, option.selected)
            ))

    }

    let select = document.getElementById("stations").options;

    $(".country-btn").click(function() {
        $("#stations option").remove();
        let country = $(this).attr("value");
        if (country == "afghanistan"){
            add_options(afghanistan);
        } else if (country == "iran"){
            add_options(iran)
        } else if (country == "india"){
            add_options(india)
        } else if (country == "pakistan"){
            add_options(pakistan)
        }
    });

    const pakistan = [
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

    const afghanistan = [
        {
            text: 'Kabul Airport',
            value: '409480, Kabul',
            selected: true
        }
    ];

    const iran = [
        {
            text: 'Abadan',
            value: '408310, Abadan',
            selected: true
        },
        {
            text: 'Ahwaz',
            value: '408110, Ahwaz'
        },
        {
            text: 'Anzali',
            value: '407180, Anzali'
        },
        {
            text: 'Babulsar',
            value: '407360, Babulsar'
        },
        {
            text: 'Bandarabbass',
            value: '408750, Bandarabbass'
        },
        {
            text: 'Birjand',
            value: '408090, Birjand'
        },
        {
            text: 'Esfahan',
            value: '408000, Esfahan'
        },
        {
            text: 'Fasa',
            value: '408590, Fasa'
        },
        {
            text: 'Ghazvin',
            value: '407310, Ghazvin'
        },
        {
            text: 'Imam Khomeni',
            value: '407300, Imam Khomeni'
        },
        {
            text: 'Iranshahr',
            value: '408790, Iranshahr'
        },
        {
            text: 'Kerman',
            value: '408410, Kerman'
        },
        {
            text: 'Kermanshah',
            value: '407660, Kermanshah'
        },
        {
            text: 'Kish Island',
            value: '407802, Kish Island'
        },
        {
            text: 'Mashhad',
            value: '407450, Mashhad'
        },
        {
            text: 'Noshahr',
            value: '407340, Noshahr'
        },
        {
            text: 'Orumieh',
            value: '407120, Orumieh'
        },
        {
            text: 'Ramsar',
            value: '407320, Ramsar'
        },
        {
            text: 'Rasht',
            value: '407190, Rasht'
        },
        {
            text: 'Sabezevar',
            value: '407430, Sabezevar'
        },
        {
            text: 'Sanadaj',
            value: '407470, Sanadaj'
        },
        {
            text: 'Semnan',
            value: '407570, Semnan'
        },
        {
            text: 'Shahrud',
            value: '407390, Shahrud'
        },
        {
            text: 'Shiraz',
            value: '408480, Shiraz'
        },
        {
            text: 'Tabriz',
            value: '407060, Tabriz'
        },
        {
            text: 'Tehran-Mehrabad',
            value: '407540, Tehran-Mehrabad'
        },
        {
            text: 'Torbat-Heydarieh',
            value: '407620, Torbat-Heydarieh'
        },
        {
            text: 'Yazd',
            value: '408210, Yazd'
        },
        {
            text: 'Zahedan',
            value: '408560, Zahedan'
        },
        {
            text: 'Zanjan',
            value: '407290, Zanjan'
        }

    ];

    const india = [
        {
            text: 'Agra',
            value: '422600, Agra',
            selected: true
        },
        {
            text: 'Ahmadabad',
            value: '426470, Ahmadabad'
        },
        {
            text: 'Akola',
            value: '429340, Akola'
        },
        {
            text: 'Allahbad',
            value: '424750, Allahbad'
        },
        {
            text: 'Amritsar',
            value: '420710, Amritsar'
        },
        {
            text: 'Aurangabad Chikalth',
            value: '430140, Aurangabad Chikalth'
        },
        {
            text: 'Balasore',
            value: '428950, Balasore'
        },
        {
            text: 'Bangalore',
            value: '432950, Bangalore'
        },
        {
            text: 'Belgaum',
            value: '431980, Belgaum'
        },
        {
            text: 'Bhopal',
            value: '426670, Bhopal'
        },
        {
            text: 'Bhubaneswar',
            value: '429710, Bhubaneswar'
        },
        {
            text: 'Bhuj-Rudramata',
            value: '426340, Bhuj-Rudramata'
        },
        {
            text: 'Bikaner',
            value: '421650, Bikaner'
        },
        {
            text: 'Bombay',
            value: '430030, Bombay'
        },
        {
            text: 'Calcutta',
            value: '428090, Calcutta'
        },
        {
            text: 'Chitradurga',
            value: '432330, Chireadurga'
        },
        {
            text: 'Cochin',
            value: '433530, Cochin'
        },
        {
            text: 'Coimbatore',
            value: '433210, Coimbatore'
        },
        {
            text: 'Cuddalore',
            value: '433290, Cuddalore'
        },
        {
            text: 'Dehradun',
            value: '421110, Dehradun'
        },
        {
            text: 'Gadag',
            value: '432010, Gadag'
        },
        {
            text: 'Gaya',
            value: '425910, Gaya'
        },
        {
            text: 'Goa',
            value: '431920, Goa'
        },
        {
            text: 'Gorakhpur',
            value: '423790, Gorakhpur'
        },
        {
            text: 'Guna',
            value: '425590, Guna'
        },
        {
            text: 'Hissar',
            value: '421310, Hissar'
        },
        {
            text: 'Honavar',
            value: '432260, Honavar'
        },
        {
            text: 'Hyderabad Airport',
            value: '431280, Hyderabad'
        },
        {
            text: 'Indore',
            value: '427540, Indore'
        },
        {
            text: 'Jabalpur',
            value: '426750, Jabalpur'
        },
        {
            text: 'Jodhpur',
            value: '423390, Jodhpur'
        },
        {
            text: 'Kakinada',
            value: '431890, Kakinada'
        },
        {
            text: 'Karaikal',
            value: '433460, Karaikal'
        },
        {
            text: 'Kota Aerodrome',
            value: '424520, Kora Aerodrome'
        },
        {
            text: 'Kozhikode',
            value: '433140, Kozhikode'
        },
        {
            text: 'Kurnool',
            value: '432130, Kurnool'
        },
        {
            text: 'Lucknow',
            value: '423690, Lucknow'
        },
        {
            text: 'M.O.Ranchi',
            value: '427010, M.O.Ranchi'
        },
        {
            text: 'Machilipatnam',
            value: '431850, Machilipatnam'
        },
        {
            text: 'Madras',
            value: '432790, Madras'
        },
        {
            text: 'Mangalore',
            value: '432840, Mangalore'
        },
        {
            text: 'Minicoy',
            value: '433690, Minicoy'
        },
        {
            text: 'Nagpur Sonegaon',
            value: '428670, Nagpur Sonegaon'
        },
        {
            text: 'Nasik',
            value: '429210, Nasik'
        },
        {
            text: 'Nellore',
            value: '432450, Nellore'
        },
        {
            text: 'New Delhi',
            value: '421820, New Delhi'
        },
        {
            text: 'Ondal India',
            value: '749238, Ondal India'
        },
        {
            text: 'Poona',
            value: '430630, Poona'
        },
        {
            text: 'Port Blair',
            value: '433330, Port Blair'
        },
        {
            text: 'Rajkot',
            value: '427370, Rajkot'
        },
        {
            text: 'Ratnagiri',
            value: '431100, Ratnagiri'
        },
        {
            text: 'Sholapur',
            value: '431170, Sholapur'
        },
        {
            text: 'Surat',
            value: '428400, Surat'
        },
        {
            text: 'Thiruvananthapuram',
            value: '433710, Thiruvananthapuram'
        },
        {
            text: 'Tiruchchirapalli',
            value: '433440, Tiruchchirapalli'
        },
        {
            text: 'Veraval',
            value: '429090, Veraval'
        }
    ];

    if ($('#stations').has('option').length == 0){
        add_options(pakistan);
    }
});