$(function () {
    transfer.init();
});

transfer = {
    left: {
        id: "left-list",
        dataUrl: "data.json",
        title: "LEFT",
        switchUrl: "",
    },
    right: {
        id: "right-list",
        dataUrl: "data.json",
        title: "RIGHT",
        switchUrl: "",
    },
};

transfer.init = function () {
    for (var x in transfer) {
        this.initOneList(transfer[x]);
    }
};

transfer.initOneList = function (options) {
    $('#' + options.id).datalist({
        url: options.dataUrl,
        title: options.title,
        method: "GET",
        checkbox: true,
        fit: true,
        singleSelect: false,
    });
}

transfer.switch = function (direction) {
    let config = transfer[direction];
    let param = [];
    let selected = $('#' + config.id).datalist("getSelections");

    if (selected) {
        for (let i in selected) {
            param.push(selected[i].id);
        }
    }

    if (param.length > 0) {
        $.ajax({
            type: "POST",
            url: config.switchUrl,
            contentType: "application/json;charset=utf-8",
            data: JSON.stringify(param),
            success: function (result) {
                transfer.init();
            },
            error: function (result) {
                transfer.init();
            }
        });
    }
}