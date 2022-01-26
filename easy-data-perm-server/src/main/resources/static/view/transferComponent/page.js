$(function () {

    $('#left-list').datalist({
        url: transfer.leftDataUrl,
        title: "LEFT",
        method: "GET",
        checkbox: true,
        fit: true,
        singleSelect: false,
    });

    $('#right-list').datalist({
        url: transfer.rightDataUrl,
        title: "RIGHT",
        method: "GET",
        checkbox: true,
        fit: true,
        singleSelect: false,
    });
});

transfer = {
    leftDataUrl: "data.json",
    rightDataUrl: "data.json",
    toLeftUrl: "",
    toRightUrl: "",
};

transfer.switch = function (direction) {
    let param = [];
    let selected;
    if (direction == "toRight") {
        selected = $('#left-list').datalist("getSelections");
    } else {
        selected = $('#right-list').datalist("getSelections");
    }
    if (selected) {
        for (let i in selected) {
            param.push(selected[i].id);
        }
    }

    console.log(JSON.stringify(param));
    if (param.length > 0) {
        $.ajax({
            type: "POST",
            url: direction == "toRight" ? transfer.toRightUrl : transfer.toLeftUrl,
            contentType: "application/json;charset=utf-8",
            data: JSON.stringify(param),
            success: function (result) {
                console.log(result);
            }
        });
    }
}