function addCard() {
//     alert("登陆成功");
//    var deviceId = document.getElementById("deviceId").value;
//    var cardId = document.getElementById("cardId").value;
//    var cardData = document.getElementById("cardData").value;
//    var effectTime = document.getElementById("effectTime").value;
//    var failureTime = document.getElementById("failureTime").value;
    $.ajax({
        url:"/card/add",
        type:"POST",
        data:JSON.stringify($('form').serializeObject()),
        contentType:"application/json",
        success:function(result){
            alert(result.code + ":" + result.message)
//            console.log("data is :" + result)
//            alert("data is :" + result);
        }
    })
};

/**
 * 自动将form表单封装成json对象
 */
$.fn.serializeObject = function() {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name]) {
            if (!o[this.name].push) {
                o[this.name] = [ o[this.name] ];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};