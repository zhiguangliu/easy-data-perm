$(function () {
    $('#DpUserDataGrid').datagrid({
        url: DpUser.searchUserByPageUrl,
        method: 'get',
        singleSelect: true,
        toolbar: '#tbUser',
        pagination: true,
        fit: true,
        onSelect: function (index, row) {
            var userId = row.userId;
            let subSystemCode = $('#subSystemCode').combobox('getValue');
            $('#DpRoleGrid').datagrid("reload", {'userId': row.userId, 'subSystemCode': subSystemCode});
        },
        // onLoadSuccess: function (data) {
        //     $('#DpPermissionItemMetaDataGrid').edatagrid("loadData", []);
        //
        // }
    });
    $('#DpRoleGrid').datagrid({
        url: DpRoleUser.findRoleUrl,
        method: 'get',
        singleSelect: true,
        toolbar: '#tbRole',
        pagination: false,
        fit: true,
        selectOnCheck: false,
        onCheck: DpRoleUser.onCheck,
        onUncheck: DpRoleUser.onUncheck,
    });
    $('#DpUserPermissionGrid').datagrid({
        url: DpRoleUser.findPermissionUrl,
        method: 'get',
        singleSelect: true,
        fit: true,
    });
});

DpUser = {};
DpUser.pageUrl = contextPath + "/rest/role/dp-role-user/page";
DpUser.searchUserByPageUrl = contextPath + "/rest/role/dp-role-user/searchUserByPage";


DpUser.load = function () {
    param = {};
    if ($("#keyword").textbox("getValue")) {
        param.keyword = $("#keyword").textbox("getValue")
    }
    if ($("#subSystemCode").textbox("getValue")) {
        param.subSystemCode = $("#subSystemCode").textbox("getValue")
    }

    $('#DpUserDataGrid').datagrid('reload', param);
};


DpRoleUser = {};
DpRoleUser.findRoleUrl = contextPath + "/rest/role/dp-role-user/listRoleWithCheckInfo";
DpRoleUser.checkUrl = contextPath + "/rest/role/dp-role-user/check";
DpRoleUser.uncheckUrl = contextPath + "/rest/role/dp-role-user/uncheck";

DpRoleUser.onCheck = function (index, row) {
    console.log(JSON.stringify(row));
    $.ajax({
        type: "POST",
        url: DpRoleUser.checkUrl,
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify(row),
        success: function (result) {
            console.log(JSON.stringify(result));
            $('#DpRoleGrid').datagrid('reload');
        }
    });
}

DpRoleUser.onUncheck = function (index, row) {
    console.log(JSON.stringify(row));
    $.ajax({
        type: "POST",
        url: DpRoleUser.uncheckUrl,
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify(row),
        success: function (result) {
            console.log(JSON.stringify(result));
            $('#DpRoleGrid').datagrid('reload');
        }
    });
}
// DpPermission.listUrl = contextPath + "/rest/permission/dp-permission/list";
// DpPermission.dataUrl = contextPath + "/rest/permission/dp-permission/data";
//
// DpPermission.createPermission = function () {
//     let row = $('#DpPermissionMetaDataGrid').datagrid("getSelected");
//     if (!row) {
//         alert("请先选择权限组元数据。")
//     }
//     let url = DpPermission.createUrl.replace("{metadataId}", row.id);
//     $.ajax({
//         type: "POST",
//         url: url,
//         contentType: "application/json;charset=utf-8",
//         // data: JSON.stringify(data),
//         success: function (result) {
//             alert("操作成功");
//             console.log(JSON.stringify(result));
//             $('#DpPermissionMetaDataGrid').datagrid('reload');
//         }
//     });
// };
//
DpRoleUser.loadRole = function (data) {
    // $.ajax({
    //     type: "POST",
    //     url: DpPermission.dataUrl,
    //     contentType: "application/json;charset=utf-8",
    //     data: JSON.stringify(data),
    //     success: function (result) {
    //         alert("操作成功");
    //         console.log(JSON.stringify(result));
    //         $('#DpPermissionItemMetaDataGrid').datagrid('reload');
    //     }
    // });
};
//
// DpPermissionItem = {};
// DpPermissionItem.listUrl = contextPath + "/rest/permission/dp-permission-item/list";
// DpPermissionItem.dataUrl = contextPath + "/rest/permission/dp-permission-item/data";
//
// DpPermissionItem.endEditing = function () {
//     if (DpPermissionItem.editIndex != undefined) {
//         $('#DpPermissionItemGrid').datagrid("endEdit", DpPermissionItem.editIndex);
//         let rows = $('#DpPermissionItemGrid').datagrid("getRows");
//         rows[DpPermissionItem.editIndex].editStatus = "edit"
//         DpPermissionItem.editIndex = undefined
//     }
//     return true;
// };
//
// DpPermissionItem.ecDpDataRoleGroupListEditRow = function (index, field) {
//     if (DpPermissionItem.editIndex != index) {
//         if (DpPermissionItem.endEditing()) {
//             $('#DpPermissionItemGrid').datagrid('selectRow', index)
//                 .datagrid('beginEdit', index);
//             let ed = $('#DpPermissionItemGrid').datagrid('getEditor', {index: index, field: field});
//             if (ed) {
//                 ($(ed.target).data('textbox') ? $(ed.target).textbox('textbox') : $(ed.target)).focus();
//             }
//             DpPermissionItem.editIndex = index;
//         }
//     }
// };
//
// DpPermissionItem.changeValueType = function (record) {
//     let currentIndex = $('#DpPermissionItemGrid').datagrid("getRowIndex", $('#DpPermissionItemGrid').datagrid("getSelected"));
//     let ed = $('#DpPermissionItemGrid').datagrid('getEditors', currentIndex)[2];
//
//     let subsystemCode = $("#subSystemCode").val();
//     console.log(ed + "=====" + subsystemCode + "=====" + currentIndex);
//     if (ed) {
//         $(ed.target).combobox("reload", "/ezdp/rest/prop/dp-base-property-define/getPropNameList?valueType=" + record.value + "&subsystemCode=" + subsystemCode);
//     }
// };
//
// DpPermissionItem.saveData = function (data) {
//     DpPermissionItem.endEditing();
//     let rows = $('#DpPermissionItemGrid').datagrid("getRows");
//
//     console.log(JSON.stringify(rows));
//     let param = [];
//     for (let i in rows) {
//         if (rows[i]["editStatus"] == "edit") {
//             param.push(rows[i]);
//         }
//     }
//     let p = {"edit": param};
//
//     $.ajax({
//         type: "POST",
//         url: DpPermissionItem.dataUrl,
//         contentType: "application/json;charset=utf-8",
//         data: JSON.stringify(p),
//         success: function (result) {
//             alert("操作成功");
//             console.log(JSON.stringify(result));
//             $('#DpPermissionItemGrid').datagrid('reload');
//         }
//     });
// };
//
//
