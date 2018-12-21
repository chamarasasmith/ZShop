/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function addToCart(id1) {
    var qty = $("#protqty" + id1).val();
    $.ajax({
        url: "Add_Cart",
        type: "POST",
        data: {id1: id1, qty: qty},
        success: function (html) {
            alert(html);
        }
    });
}





function ViewCheckout() {
    $("#main1").load("Web_Checkout");
    $("#main1").hide().appendTo("#main1").fadeIn();
    $("#micon1").slideUp();
    $("#slider1").slideUp();
}


function ViewOrder() {
    $("#main1").load("Web_Order_View");
    $("#main1").hide().appendTo("#main1").fadeIn();
    $("#micon1").slideUp();
    $("#slider1").slideUp();
}



