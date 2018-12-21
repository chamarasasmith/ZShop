/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




function setImg2() {
    $("#vbimg1").fadeIn("fast").attr('src', URL.createObjectURL(event.target.files[0]));
}

function setImg3() {
    $("#vcimg1").fadeIn("fast").attr('src', URL.createObjectURL(event.target.files[0]));
}


function changeStatus(n,p,st,num,id1){
    $.ajax({
        url: "ChangeStatus",
        type: "POST",
        data: { st: st, num: num, id1: id1 },
        success: function (html) {
            setAdminSubView(n,p);
        }
    }); 
}



function addBrand() {
    progressBar("alert_brand");
    var data = new FormData(document.getElementById("badd1"));
    $.ajax({
        url: "Brand_Add",
        type: "POST",
        data: data,
        enctype: 'multipart/form-data',
        processData: false,
        contentType: false,
        dataType: 'html',
        success: function (html) {
            $('#badd1').trigger("reset");
            $("#alert_brand").html(html);
            $("#vbimg1").attr("src","");
        }
    });
}

function addProduct() {
    progressBar("alert_product");
    var data = new FormData(document.getElementById("pform1"));
    $.ajax({
        url: "Products_Add",
        type: "POST",
        data: data,
        enctype: 'multipart/form-data',
        processData: false,
        contentType: false,
        dataType: 'html',
        success: function (html) {
            $('#pform1').trigger("reset");
            $("#alert_product").html(html);
            $("#vproimg1").attr("src","img/p_icon2.png");
        }
    });
}



function addCat() {
    progressBar("alert_cat");
    var data = new FormData(document.getElementById("cadd1"));
    $.ajax({
        url: "Cat_Add",
        type: "POST",
        data: data,
        enctype: 'multipart/form-data',
        processData: false,
        contentType: false,
        dataType: 'html',
        success: function (html) {
            $('#cadd1').trigger("reset");
            $("#alert_cat").html(html);
            $("#vcimg1").attr("src","");
        }
    });
}
