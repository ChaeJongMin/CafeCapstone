// JSON Data -------------------------------------------

let coffee = []
let cake = []
let noncoffee = []
let sandwich = []

$(window).ready(function() {
    if (localStorage.getItem('noncoffee') == null) { 
                        // 하나만 있을 경우는 없으므로 그냥 한개만 확인해 판별
        parceJson();
    } else {
        coffee = JSON.parse(localStorage.getItem('coffee'));
        cake = JSON.parse(localStorage.getItem('cake'));
        //div 값
        noncoffee = JSON.parse(localStorage.getItem('noncoffee'));
        sandwich = JSON.parse(localStorage.getItem('sandwich'));
   
        console.log(커피);
    }
})


function parceJson() {
    alert("Welcome to STARUS");
    $.get('../json/coffee.json', function(data) {
        localStorage.setItem("coffee", JSON.stringify(data));
        coffee = JSON.parse(localStorage.getItem('coffee'));
    }, "json");
    $.get('../json/cake.json', function(data) {
        localStorage.setItem("cake", JSON.stringify(data));
        cake = JSON.parse(localStorage.getItem('cake'));
    }, "json");
    $.get('../json/noncoffee.json', function(data) {
        localStorage.setItem("noncoffee", JSON.stringify(data));
        noncoffee = JSON.parse(localStorage.getItem('noncoffee'));
    }, "json");
    $.get('../json/sandwich.json', function(data) {
        localStorage.setItem("sandwich", JSON.stringify(data));
        sandwich = JSON.parse(localStorage.getItem('sandwich'));
    }, "json");
}


// NAVI 바 -------------------------------------------

$(document).ready(function() {

    $('#nav_detail_bev').show();
    
    
    $('#nav_coffee').addClass('underline1');      
            $('#nav_tea').removeClass('underline2');
            $('#nav_juice').removeClass('underline3');
            $('#nav_frappuccino').removeClass('underline5');
            $('#nav_cake').removeClass('underline4');
          
    $('.COFFEE').css('display', 'inline-block');
    $('.TEA').css('display', 'none');
    $('.JUICE').css('display', 'none');
    $('.FRAPPUCCINO').css('display', 'none');
    $('.beforelist').css('display', 'none');
    $('.recommendlist').css('display', 'none');
    

    /*각 세부 메뉴들에대해 터치가되면 색이 반전된다*/
    $('#nav_coffee').on({

        mouseenter: function() {
            $('#nav_coffee').addClass('over3');
        },
        mouseleave: function() {
            $('#nav_coffee').removeClass('over3');
        },
        click: function() {
            $('#nav_coffee').addClass('underline1');
         
            $('#nav_tea').removeClass('underline2');
            $('#nav_juice').removeClass('underline3');
            $('#nav_frappuccino').removeClass('underline5');
            $('#nav_beforebuy').removeClass('underline6');
            $('#nav_recommend').removeClass('underline7');

            $('.COFFEE').css('display', 'inline-block');
            $('.TEA').css('display', 'none');
            $('.JUICE').css('display', 'none');
            $('.FRAPPUCCINO').css('display', 'none');
            $('.beforelist').css('display', 'none');
            $('.recommendlist').css('display', 'none');

        }
    });



    $('#nav_tea').on({
        mouseenter: function() {
            $('#nav_tea').addClass('over1');
        },
        mouseleave: function() {
            $('#nav_tea').removeClass('over1');
        },
        click: function() {
            $('#nav_tea').addClass('underline2');

            $('#nav_coffee').removeClass('underline1');
            $('#nav_juice').removeClass('underline3');
            $('#nav_frappuccino').removeClass('underline5');
            $('#nav_beforebuy').removeClass('underline6');
            $('#nav_recommend').removeClass('underline7');

            /*$('.coffee').removeAttr('display');*/

            $('.COFFEE').hide();
            $('.TEA').show();
            $('.JUICE').hide();
            $('.FRAPPUCCINO').hide();
            $('.beforelist').hide();
            $('.recommendlist').hide();

        }
    });

    $('#nav_juice').on({
        mouseenter: function() {
            $('#nav_juice').addClass('over2');
        },
        mouseleave: function() {
            $('#nav_juice').removeClass('over2');
        },
        click: function() {
            $('#nav_juice').addClass('underline3');

            $('#nav_coffee').removeClass('underline1');
            $('#nav_tea').removeClass('underline2');
            $('#nav_frappuccino').removeClass('underline5');
            $('#nav_beforebuy').removeClass('underline6');
            $('#nav_recommend').removeClass('underline7');

            $('.COFFEE').hide();
            $('.TEA').hide();
            $('.JUICE').show();
            $('.FRAPPUCCINO').hide();
            $('.beforelist').hide();
            $('.recommendlist').hide();
        }
    });
   $('#nav_frappuccino').on({
        mouseenter: function() {
            $('#nav_frappuccino').addClass('over2');
        },
        mouseleave: function() {
            $('#nav_frappuccino').removeClass('over2');
        },
        click: function() {
            $('#nav_frappuccino').addClass('underline5');

            $('#nav_coffee').removeClass('underline1');
            $('#nav_tea').removeClass('underline2');
            $('#nav_juice').removeClass('underline3');
            $('#nav_cake').removeClass('underline4');
            $('#nav_beforebuy').removeClass('underline6');
            $('#nav_recommend').removeClass('underline7');

         $('.FRAPPUCCINO').show();      
            $('.COFFEE').hide();
            $('.TEA').hide();
            $('.JUICE').hide();
            $('.beforelist').hide();
            $('.recommendlist').hide();
        }
    });

    $('#nav_beforebuy').on({
        mouseenter: function() {
            $('#nav_beforebuy').addClass('over4');
        },
        mouseleave: function() {
            $('#nav_beforebuy').removeClass('over4');
        },
        click: function() {
            $('#nav_beforebuy').addClass('underline6');
            
            $('#nav_coffee').removeClass('underline1');
            $('#nav_tea').removeClass('underline2');
            $('#nav_juice').removeClass('underline3');
            $('#nav_frappuccino').removeClass('underline5');
             $('#nav_recommend').removeClass('underline7');

            $('.FRAPPUCCINO').hide();      
            $('.COFFEE').hide();
            $('.TEA').hide();
            $('.JUICE').hide();
            $('.beforelist').show();
            $('.recommendlist').hide();
        }
    });
    
    $('#nav_recommend').on({
        mouseenter: function() {
            $('#nav_recommend').addClass('over4');
        },
        mouseleave: function() {
            $('#nav_recommend').removeClass('over4');
        },
        click: function() {
            $('#nav_recommend').addClass('underline7');
            
            $('#nav_coffee').removeClass('underline1');
            $('#nav_tea').removeClass('underline2');
            $('#nav_juice').removeClass('underline3');
            $('#nav_frappuccino').removeClass('underline5');
            $('#nav_beforebuy').removeClass('underline6');

            $('.FRAPPUCCINO').hide();      
            $('.COFFEE').hide();
            $('.TEA').hide();
            $('.JUICE').hide();
            $('.beforelist').hide();
            $('.recommendlist').show();
        }
    });

    /*음료나 푸드를 클릭(터치)하면 세부메뉴가 보여진다
            그리고 아래 하이라이트 언더라인이 생기고 다른메뉴를
            클릭할시 언더라인이 사라진다*/
    $('#nav_category_bev').click(function() {
        $('#nav_detail_bev').show();
        $('#nav_detail_user').hide();
        $('#nav_category_bev').addClass('underline_a');
        $('#nav_user').removeClass('underline_b');

        $('#nav_coffee').removeClass('underline1');
        $('#nav_tea').removeClass('underline2');
        $('#nav_juice').removeClass('underline3');
        $('#nav_frappuccino').removeClass('underline5');
        $('#nav_beforebuy').removeClass('underline6');
        $('#nav_recommend').removeClass('underline7');
    });

    $('#nav_user').click(function() {
        $('#nav_detail_user').show();
        $('#nav_detail_bev').hide();
        $('#nav_user').addClass('underline_b');
        $('#nav_category_bev').removeClass('underline_a');

         $('#nav_coffee').removeClass('underline1');
        $('#nav_tea').removeClass('underline2');
        $('#nav_juice').removeClass('underline3');
        $('#nav_frappuccino').removeClass('underline5');
        $('#nav_beforebuy').removeClass('underline6');
        $('#nav_recommend').removeClass('underline7');
    });


    // 메뉴 ---------------------------------------


    /* 메뉴이미지 클릭 이벤트
    $('img').on("click", function() {

        let tmpnum = Number(this.id);
        let nowitem;
        if (tmpnum < 10) {
            nowitem = coffee[tmpnum];
        } else if (tmpnum < 20) {
            tmpnum = tmpnum - 10;
            nowitem = noncoffee[tmpnum];
        } else if (tmpnum < 30) {
            tmpnum = tmpnum - 20;
            nowitem = sandwich[tmpnum];
        } else {
            tmpnum = tmpnum - 30;
            nowitem = cake[tmpnum];
        }
        localStorage.setItem('nowitem', JSON.stringify(nowitem));
        location.replace('../jungmin/detail.html');
    });
*/

  /*  // 홈 버튼 이벤트 : 클릭 시 매장/포장 선택 가능한 mainpage 로드
    $('#home').on('click', function() {
        location.replace('../uri/mainpage.html');
    });
*/

});