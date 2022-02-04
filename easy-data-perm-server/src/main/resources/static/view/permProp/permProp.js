$(function () {
    $('#DpPermissionPropertyDefineGrid').edatagrid({
        url: DpPermissionPropertyDefine.pageUrl,
        method: 'get',
        singleSelect: true,
        toolbar: '#tbPerm',
        pagination: true,
        fit: true,
        onSelect: function (index, row) {
            DpPermissionPropertyDefine.id = row.id;
            DpPermissionPropertyDefine.propertyCode = row.propertyCode;
            $('#DpPermissionPropertyGrid').edatagrid("reload", {'propertyCode': row.propertyCode});
        },
        onLoadSuccess: function (data) {
            // $('#DpPermissionItemMetaDataGrid').edatagrid("loadData", []);

        }
    });
    $('#DpPermissionPropertyGrid').edatagrid({
        url: DpPermissionProperty.listUrl,
        method: 'get',
        singleSelect: true,
        toolbar: '#tbItem',
        pagination: true,
        fit: true,
        onSelect: function (index, row) {
            console.log(1)
        },
    });
});

DpPermissionPropertyDefine = {};
DpPermissionPropertyDefine.pageUrl = contextPath + "/rest/prop/dp-base-property-define/page";
DpPermissionPropertyDefine.dataUrl = contextPath + "/rest/prop/dp-base-property-define/data";


DpPermissionPropertyDefine.load = function () {
    param = {};
    if($("#subSystemCode").textbox("getValue")){param.subSystemCode=$("#subSystemCode").textbox("getValue")}
    if($("#operationName").textbox("getValue")){param.operationName=$("#operationName").textbox("getValue")}
    if($("#operationIdentifier").textbox("getValue")){param.operationIdentifier=$("#operationIdentifier").textbox("getValue")}

    $('#DpPermissionPropertyDefineGrid').datagrid('reload', param);

};

DpPermissionPropertyDefine.saveData = function (data) {
    for (const item of data.add) {
        item.subSystemCode = $("#subSystemCode").textbox("getValue");
    }

    $.ajax({
        type: "POST",
        url: DpPermissionPropertyDefine.dataUrl,
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify(data),
        success: function (result) {
            alert("操作成功");
            console.log(JSON.stringify(result));
            $('#DpPermissionMetaDataGrid').datagrid('reload');
        }
    });
};

// 以下是属性值维护区域代码

DpPermissionProperty = {};
DpPermissionProperty.listUrl = contextPath + "/rest/prop/dp-base-property-value/list";
DpPermissionProperty.dataUrl = contextPath + "/rest/prop/dp-base-property-value/data";


DpPermissionProperty.saveData = function (data) {
    for (const item of data.add) {
        item.propertyCode = DpPermissionPropertyDefine.propertyCode;
    }

    $.ajax({
        type: "POST",
        url: DpPermissionProperty.dataUrl,
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify(data),
        success: function (result) {
            alert("操作成功");
            console.log(JSON.stringify(result));
            $('#DpPermissionPropertyGrid').datagrid('reload');
        }
    });
};

