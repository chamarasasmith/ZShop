/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function progressBar(id1){
    $("#"+id1).html("<div class='progress'><div class='progress-bar progress-bar-striped active progress-bar-danger' role='progressbar' aria-valuenow='45' aria-valuemin='0' aria-valuemax='100' style='width: 100%'><span class='sr-only'>Wait</span></div></div>");
}


function signIn(){
    progressBar("alert_sin");
    var un=$("#un2").val();
    var pw=$("#pw2").val();
    $.ajax({
        url: "SignIn",
        type: "POST",
        data: { un: un, pw: pw },
        success: function (html) {
            $('#sinform1').trigger("reset");
            $("#alert_sin").html(html);
            location.reload(true);
        }
    });
  
}

function signOut(){
    $.ajax({
        url: "SignOut",
        type: "POST",
        success: function (html) {
            location.reload(true);
        }
    }); 
}



function signUp() {
    progressBar("alert_sup");
    var data = new FormData(document.getElementById("supform1"));
    $.ajax({
        url: "SignUp",
        type: "POST",
        data: data,
        enctype: 'multipart/form-data',
        processData: false,
        contentType: false,
        dataType: 'html',
        success: function (html) {
            $('#supform1').trigger("reset");
            $("#alert_sup").html(html);
        }
    });
}


function setImg1() {
    $("#vimg1").fadeIn("fast").attr('src', URL.createObjectURL(event.target.files[0]));
}







$(document).ready(function () {

    var f1 = false;
    var f2 = false;
    var f3 = false;
    var f4 = false;
    var f5 = false;

    $("#fn1").bind('keyup change', function () {
        var v = $('#fn1').val();
        if (v.toString() == "") {
            $("#vv3").attr('class', 'glyphicon glyphicon-remove');
            f1 = false;
            enableSignUp(f1, f2, f3, f4, f5);
        } else {
            $("#vv3").attr('class', 'glyphicon glyphicon-ok');
            f1 = true;
            enableSignUp(f1, f2, f3, f4, f5);
        }
    });

    $("#ln1").bind('keyup change', function () {
        var v = $('#ln1').val();
        if (v.toString() == "") {
            $("#vv4").attr('class', 'glyphicon glyphicon-remove');
            f2 = false;
            enableSignUp(f1, f2, f3, f4, f5);
        } else {
            $("#vv4").attr('class', 'glyphicon glyphicon-ok');
            f2 = true;
            enableSignUp(f1, f2, f3, f4, f5);
        }
    });

    $("#pw1").bind('keyup change', function () {
        var v = $('#pw1').val();
        if (v.toString() == "") {
            $("#vv5").attr('class', 'glyphicon glyphicon-remove');
            f3 = false;
            enableSignUp(f1, f2, f3, f4, f5);
        } else {
            $("#vv5").attr('class', 'glyphicon glyphicon-ok');
            f3 = true;
            enableSignUp(f1, f2, f3, f4, f5);
        }
    });

    $("#un1").bind('keyup change', function () {
        var v = $('#un1').val();
        if (v.toString() == "") {
            $("#vv1").attr('class', 'glyphicon glyphicon-remove');
            f4 = false;
            enableSignUp(f1, f2, f3, f4, f5);
        } else {
            $.ajax({
                url: "Validation1?type1=t1&un=" + v,
                type: "POST",
                dataType: 'html',
                success: function (html) {
                    if (html.toString() == "1") {
                        $("#vv1").attr('class', 'glyphicon glyphicon-remove');
                        f4 = false;
                        enableSignUp(f1, f2, f3, f4, f5);
                    }
                    if (html.toString() == "0") {
                        $("#vv1").attr('class', 'glyphicon glyphicon-ok');
                        f4 = true;
                        enableSignUp(f1, f2, f3, f4, f5);
                    } else {
                        $("#vv1").attr('class', 'glyphicon glyphicon-remove');
                        f4 = false;
                        enableSignUp(f1, f2, f3, f4, f5);
                    }
                }
            });
        }
    });

    $("#email1").bind('keyup change', function () {
        var v = $('#email1').val();
        if (v.toString() == "") {
            $("#vv2").attr('class', 'glyphicon glyphicon-remove');
            f5 = false;
            enableSignUp(f1, f2, f3, f4, f5);
        } else {

            $.ajax({
                url: "Validation1?type1=t2&email=" + v,
                type: "POST",
                dataType: 'html',
                success: function (html) {
                    if (html.toString() == "1") {
                        $("#vv2").attr('class', 'glyphicon glyphicon-remove');
                        f5 = false;
                        enableSignUp(f1, f2, f3, f4, f5);
                    }
                    if (html.toString() == "0") {
                        $("#vv2").attr('class', 'glyphicon glyphicon-ok');
                        f5 = true;
                        enableSignUp(f1, f2, f3, f4, f5);
                    } else {
                        $("#vv2").attr('class', 'glyphicon glyphicon-remove');
                        f5 = false;
                        enableSignUp(f1, f2, f3, f4, f5);
                    }
                }

            });
        }
    });


$("#un2").bind("change",function () {
        var un=$("#un2").val();
    $.ajax({
        url: "SignIn_SetImg",
        type: "POST",
        data: { un: un },
        success: function (html) {
            $("#vimg2").fadeIn("fast").attr('src', html);
        }
    });
        
    });



});


function enableSignUp(f1, f2, f3, f4, f5) {
    if (f1 && f2 && f3 && f4 && f5) {
        $("#supbtn1").attr('disabled', false);
    } else {
        $("#supbtn1").attr('disabled', true);
    }

}