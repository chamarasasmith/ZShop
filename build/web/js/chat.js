/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */





function scrolToDown1() {
    var objDiv = document.getElementById("msgview1");
    objDiv.scrollTop = objDiv.scrollHeight;
}

function saveMsg() {

    var msg = $("#msg1").val();
    var to = $("#toid1").val();

    $.ajax({
        url: "SaveMsg",
        type: "POST",
        data: {msg: msg, to: to},
        success: function (html) {
//            setTimeout(scrolToDown1, 1000);
            $('#msg1').val("");
            $('#msg1').focus();
        }
    });
}

function setMsg() {
//    alert('aaa');
    var cid = $("#uid1").val();
    var to1 = $("#toid1").val();
    
    if (cid != null && to1 != null && cid != "" && to1 != "") {
        $.ajax({
            url: "chat/messages1.jsp",
            type: "POST",
            data: {to: to1, cid: cid},
            success: function (html) {
//            $('#sinform1').trigger("reset");
                $("#msgview1").html(html);
            setInterval(scrolToDown1,500);  
            }
        });
    }
}


function checkCount() {
    var count = $("#msgcount").val();
    var cid = $("#uid1").val();

    if (count != null && count != ""&&cid != null && cid != "") {
        $.ajax({
            url: "CheckMsgCount",
            type: "POST",
            data: {count1: count, cid:cid},
            success: function (html) {

                if (html=="2"){
                    setMsg();                    
                }else{
                    
                }
                
            }
        });
    }
}


function setMsgView(id1, to1) {
    $('#uid1').val(id1);
    $('#toid1').val(to1);
    $.ajax({
        url: "chat/messages1.jsp",
        type: "POST",
        data: {to: to1, cid: id1},
        success: function (html) {
//            $('#sinform1').trigger("reset");
            $("#msgview1").html(html);
            setTimeout(scrolToDown1, 1000);


        }
    });
}



setInterval(checkCount, 1000);
