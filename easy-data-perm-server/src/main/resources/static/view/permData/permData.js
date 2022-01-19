$(function () {
    $('#DpPermissionMetaDataGrid').datagrid({
        url: DpPermissionMetadata.pageUrl,
        method: 'get',
        singleSelect: true,
        toolbar: '#tbPerm',
        pagination: true,
        fit: true,
        onSelect: function (index, row) {
            DpPermissionMetadata.id = row.id;
            $('#DpPermissionGrid').edatagrid("reload", {'metadataId': row.id});
        },
        onLoadSuccess: function (data) {
            $('#DpPermissionItemMetaDataGrid').edatagrid("loadData", []);

        }
    });
    $('#DpPermissionGrid').edatagrid({
        url: DpPermission.listUrl,
        method: 'get',
        singleSelect: true,
        toolbar: '#DpPermissionTb',
        pagination: false,
        fit: true,
        onSelect: function (index, row) {
            console.log(row.id);
            $('#DpPermissionItemGrid').edatagrid("reload", {'permissionId': row.id});
        },
    });
    $('#DpPermissionItemGrid').edatagrid({
        url: DpPermissionItem.listUrl,
        method: 'get',
        singleSelect: true,
        toolbar: '#tbItem',
        pagination: true,
        fit: true,
    });
});

DpPermissionMetadata = {};
DpPermissionMetadata.pageUrl = contextPath + "/rest/permission/dp-permission-metadata/page";
DpPermissionMetadata.dataUrl = contextPath + "/rest/permission/dp-permission-metadata/data";

// http://localhost:8899/ezdp//rest/permission/dp-permission-metadata/page?pageNum=1&pageSize=10&operationName=aa
DpPermissionMetadata.load = function () {
    param = {};
    if($("#subSystemCode").textbox("getValue")){param.subSystemCode=$("#subSystemCode").textbox("getValue")}
    if($("#operationName").textbox("getValue")){param.operationName=$("#operationName").textbox("getValue")}
    if($("#operationIdentifier").textbox("getValue")){param.operationIdentifier=$("#operationIdentifier").textbox("getValue")}

    $('#DpPermissionMetaDataGrid').datagrid('reload', param);

};



DpPermission = {};
DpPermission.createUrl = contextPath + "/rest/permission/dp-permission/permission/{metadataId}";
DpPermission.listUrl = contextPath + "/rest/permission/dp-permission/list";
DpPermission.dataUrl = contextPath + "/rest/permission/dp-permission/data";

DpPermission.createPermission = function () {
    let row = $('#DpPermissionMetaDataGrid').datagrid("getSelected");
    if (!row) {
        alert("请先选择权限组元数据。")
    }
    let url = DpPermission.createUrl.replace("{metadataId}", row.id);
    $.ajax({
        type: "POST",
        url: url,
        contentType: "application/json;charset=utf-8",
        // data: JSON.stringify(data),
        success: function (result) {
            alert("操作成功");
            console.log(JSON.stringify(result));
            $('#DpPermissionMetaDataGrid').datagrid('reload');
        }
    });
};

DpPermission.saveData = function (data) {
    $.ajax({
        type: "POST",
        url: DpPermission.dataUrl,
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify(data),
        success: function (result) {
            alert("操作成功");
            console.log(JSON.stringify(result));
            $('#DpPermissionItemMetaDataGrid').datagrid('reload');
        }
    });
};

DpPermissionItem = {};
DpPermissionItem.listUrl = contextPath + "/rest/permission/dp-permission-item/list";
DpPermissionItem.dataUrl = contextPath + "/rest/permission/dp-permission-item/data";


DpPermissionItem.saveData = function (data) {
    for (const item of data.add) {
        item.subSystemCode = $("#subSystemCode").textbox("getValue");
    }

    $.ajax({
        type: "POST",
        url: DpPermissionMetadata.dataUrl,
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify(data),
        success: function (result) {
            alert("操作成功");
            console.log(JSON.stringify(result));
            $('#DpPermissionMetaDataGrid').datagrid('reload');
        }
    });
};


