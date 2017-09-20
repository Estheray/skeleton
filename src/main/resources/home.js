$(function(){
    const api = " http://localhost:8080";

    $.getJSON(api+"/receipts", function(receipts){
        for(var i=0; i < receipts.length; i++) {
            var receipt = receipts[i];
            console.log(receipt);
            $("<tr class='receipt'>").html(
                "<td class='time'>"+receipt.created+"</td>"+
                "<td class='merchant'>"+receipt.merchantName+"</td>"+
                "<td class='amount'>"+receipt.value+"</td>"+
                "<td class='tags'>"+"<input type='button' class='btn btn-success add-tag' onclick='inputbox(this,receipt.id)' value='AddTag'>"+"</button>"+"</td>"+
                "</tr>").appendTo($(".table tbody"));


        };
    });
});

function cancelUserInput(){
    // $('#merchant').val("");
    // $('#amount').val("");
    // $('#addreceipt').hide();
    console.log(mpty)
    $("#userInputForm").trigger('reset');
}

function save_receipt(){
    var merchant = $("#merchant").val();
    if (!merchant){
        cancelUserInput();
    }

    var amount = $("#amount").val();
    console.log("Merchant: "+ merchant + ", amount: " + amount)
    $.ajax({
        type:"POST",
        contentType: 'application/json',
        url:api+"/receipts",
        data:JSON.stringify({
            "merchant":merchant,"amount":amount,
        }),
        dataType:"json",
        success: function(receiptID){
            console.log('success');
            userInputReceipt(merchant,amount,receiptID);
            // $('#merchant').val("");
            // $('#amount').val("");
            cancelUserInput();
        }
    });


};

function userInputReceipt(merchant, amount, receiptID){
    var date = new Date();
    $("<tr class='receipt'>").html(
        "<td class='time'>"+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds()+
        "<td class='merchant'>"+merchant+"</td>"+
        "<td class='amount'>"+amount+"</td>"+
        "<td class='tags'>"+
        "</tr>").appendTo($(".table tbody"));
}


