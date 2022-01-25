$(function () {

    $('#left-list').datalist({
        url: transfer.leftDataUrl,
        title:"LEFT",
        method:"GET",
        checkbox: true,
        fit: true,
        singleSelect: false,
    });

    $('#right-list').datalist({
        url: transfer.rightDataUrl,
        title:"RIGHT",
        method:"GET",
        checkbox: true,
        fit: true,
        singleSelect: false,
    });
});

transfer = {
    leftDataUrl:"data.json",
    rightDataUrl:"data.json",
    toLeftUrl:"",
    toRightUrl:"",
};
transfer.toLeft = function(direction){
    let rightSelected = $('#right-list').datalist("getSelections");
    let param = [];
    for (i in rightSelected) param.push(rightSelected[i].id);
    console.log(JSON.stringify(param));

    $.ajax({
        type: "POST",
        url: transfer.toLeftUrl,
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify(param),
        success: function (result) {
            console.log(result);
        }
    });


}