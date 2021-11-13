$(function () {
    $('#DpPermissionMetaDataGrid').edatagrid({
        url: DpPermissionMetadata.pageUrl
    });
});

DpPermissionMetadata = {};
DpPermissionMetadata.pageUrl = contextPath + "/rest/permission/dp-permission-metadata/page";
DpPermissionMetadata.dataUrl = contextPath + "/rest/permission/dp-permission-metadata/data";

http://localhost:8899/ezdp//rest/permission/dp-permission-metadata/page?pageNum=1&pageSize=10&operationName=aa

DpPermissionMetadata.saveData = function (data) {
    $.ajax({
        type: "POST",
        url: DpPermissionMetadata.dataUrl,
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify(data),
        success: function (result) {
            alert("操作成功");
            console.log(JSON.stringify(result));
            $('#DpSubSystemGrid').datagrid('reload');
            // $('#ecQmsApplicationPortInfo').datagrid('reload');
            // ecQmsApplicationPortInfo.find();
        }
    });
};
