$(function () {
    $('#DpPermissionMetaDataGrid').edatagrid({
        url: DpPermissionMetadata.pageUrl,
        method: 'get',
        singleSelect: true,
        toolbar: '#tbPerm',
        pagination: true,
        fit: true,
        onSelect: function (index, row) {
            DpPermissionMetadata.id = row.id;
            $('#DpPermissionItemMetaDataGrid').edatagrid("reload", {'permissionMetadataId': row.id});
        },
        onLoadSuccess: function (data) {
            $('#DpPermissionItemMetaDataGrid').edatagrid("loadData", []);

        }
    });
    $('#DpPermissionItemMetaDataGrid').edatagrid({
        url: DpPermissionItemMetadata.listUrl,
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

DpPermissionMetadata = {};
DpPermissionMetadata.pageUrl = contextPath + "/rest/permission/dp-permission-metadata/page";
DpPermissionMetadata.dataUrl = contextPath + "/rest/permission/dp-permission-metadata/data";

DpPermissionItemMetadata = {};
DpPermissionItemMetadata.listUrl = contextPath + "/rest/permission/dp-permission-item-metadata/list";
DpPermissionItemMetadata.dataUrl = contextPath + "/rest/permission/dp-permission-item-metadata/data";


// http://localhost:8899/ezdp//rest/permission/dp-permission-metadata/page?pageNum=1&pageSize=10&operationName=aa
DpPermissionMetadata.load = function () {
    param = {};
    if($("#subSystemCode").textbox("getValue")){param.subSystemCode=$("#subSystemCode").textbox("getValue")}
    if($("#operationName").textbox("getValue")){param.operationName=$("#operationName").textbox("getValue")}
    if($("#operationIdentifier").textbox("getValue")){param.operationIdentifier=$("#operationIdentifier").textbox("getValue")}

    $('#DpPermissionMetaDataGrid').datagrid('reload', param);

};
DpPermissionMetadata.saveData = function (data) {
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

DpPermissionItemMetadata.saveData = function (data) {
    for (const item of data.add) {
        item.permissionMetadataId = DpPermissionMetadata.id;
        item.subSystemCode = $("#subSystemCode").textbox("getValue");
    }

    $.ajax({
        type: "POST",
        url: DpPermissionItemMetadata.dataUrl,
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify(data),
        success: function (result) {
            alert("操作成功");
            console.log(JSON.stringify(result));
            $('#DpPermissionItemMetaDataGrid').datagrid('reload');
        }
    });
};

