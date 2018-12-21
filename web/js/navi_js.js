/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function scrolToId(vid) {
    $('html,body').animate({scrollTop: $(vid).offset().top}, 500);
}

function setAdmin() {
    $("#main1").load("admin/admin.jsp");
    $("#main1").hide().appendTo("#main1").fadeIn();
    $("#micon1").slideUp();
    $("#slider1").slideUp();

}

function setBuy() {
    $("#main1").load("advanced_search.jsp");
//    $("#main1").hide().appendTo("#main1").fadeIn();
    $("#micon1").slideUp();
    $("#slider1").slideUp();
}

function setChat() {
    $("#main1").load("chat/chat.jsp");
    $("#main1").hide().appendTo("#main1").fadeIn();
    $("#micon1").slideUp();
    $("#slider1").slideUp();
}


function searchProduct(id1, ele) {
    
    var st1= $("#singst").is(':checked');
    var st2= $("#multist").is(':checked');
    
    var st="Single";
    
    if (st1) {
        st="s1";
    }
    if (st2) {
        st="s2";
    }
    
    alert(st);
    
    var f1= $("#bsearch1").val();
    var f2= $("#ccon1").val();
    var f3= $("#ccon2").val();
    var f4= $("#smin1").val();
    var f5= $("#smax1").val();
    
    if (st=="s1") {
        
    }
    
//    if (ele==1){
//    var t1= $("#bsearch1").val();
//    $("#adv_view").load("sub/protview.jsp?f=0&item=4&w=1&t1="+t1+"");
//    $("#adv_view").hide().appendTo("#adv_view").fadeIn();
//    }
//    if (ele==2){
//    var t1= $("#ccon1").val();
//    $("#adv_view").load("sub/protview.jsp?f=0&item=4&w=2&t1="+t1+"");
//    $("#adv_view").hide().appendTo("#adv_view").fadeIn();
//        
//    }
    
    
}

function setAdvSortGrid() {
    $("#adv_view").load("sub/protview.jsp?f=0&item=4&t1=0");
    $("#adv_view").hide().appendTo("#adv_view").fadeIn();
}

function setAdvSortList() {
    $("#adv_view").load("sub/prolview.jsp?f=0&item=4&t1=0");
    $("#adv_view").hide().appendTo("#adv_view").fadeIn();
}

function setHome() {
//    window.location.replace("index.jsp");
    $("#micon1").slideDown();
    $("#slider1").slideDown();
    $("#main1").hide().appendTo("#main1").fadeOut();
}



//------------Admin-------------//

function setAdminView(p) {
    $.ajax({
        url: p,
        type: "POST",
        success: function (html) {
            $("#adminview1").html(html);
        }
    });
//    $("#adminview1").load(p);
//    $("#adminview1").hide().appendTo("#adminview1").fadeIn();
}


//------------Admin > Sub View-------------//

function setAdminSubView(n,p) {
    $.ajax({
        url: p,
        type: "POST",
        success: function (html) {
            $("#sview"+n).html(html);
        }
    }); 
//    $("#"+id1).html("");
//    $("#"+id1).load(p);
}




//------------Buy View-------------//



function setBuyViewPrev() {
    var s= $("#pnum").val();
    $("#pnum").val(parseInt(s)-4);
    var s2= $("#pnum").val();
    var p="sub/protview.jsp?f="+s2+"&item=4";
    $("#adv_view").load(p);
    $("#adv_view").hide().appendTo("#adv_view").fadeIn();
    
}


