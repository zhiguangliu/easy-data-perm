$(function () {
    $('#DpRoleGrid').edatagrid({
        url: DpRole.pageUrl,
        method: 'get',
        singleSelect: true,
        toolbar: '#tbPerm',
        pagination: true,
        fit: true,
        onSelect: function (index, row) {
            DpRole.id = row.id;
            $('#DpSelectedPermGrid').edatagrid("reload", {'roleId': row.id});
            $('#DpPermissionPropertyDefineGrid').datagrid("reload", {'roleId': row.id});
        },
        // onLoadSuccess: function (data) {
        //     $('#DpPermissionItemMetaDataGrid').edatagrid("loadData", []);
        //
        // }
    });
    $('#DpSelectedPermGrid').edatagrid({
        url: DpRolePermissionRelation.listUrl,
        method: 'get',
        singleSelect: true,
        toolbar: '#tbSelectedPerm',
        fit: true,
        onSelect: function (index, row) {
            console.log(1)
        },
    });
    $('#DpPermissionGrid').datagrid({
        url: DpRolePermissionWindow.listUrl,
        method: 'get',
        singleSelect: true,
        toolbar: '#DpPermissionTb',
        fit: true,
        onSelect: function (index, row) {
            console.log(1);
            DpRolePermissionWindow.metadataId = row.id;
            transfer.refresh();


        },
    });

    transfer.init();

    $('#DpPermissionPropertyDefineGrid').datagrid({
        url: DpPermissionProperty.define.listUrl,
        method: 'get',
        singleSelect: true,
        toolbar: '#tbDpPermissionPropertyDefineGrid',
        pagination: false,
        fit: true,
        onSelect: function (index, row) {
            DpPermissionProperty.define.id = row.id;
            DpPermissionProperty.define.propertyCode = row.propertyCode;
            $('#DpPermissionPropertyGrid').datagrid("reload", {'roleId':DpRole.id,'propertyCode': row.propertyCode});
        },
        onLoadSuccess: function (data) {
            // $('#DpPermissionItemMetaDataGrid').edatagrid("loadData", []);

        }
    });
    $('#DpPermissionPropertyGrid').datagrid({
        url: DpPermissionProperty.value.roleListUrl,
        method: 'get',
        singleSelect: false,
        toolbar: '#tbItem',
        pagination: true,
        fit: true,
        onCheck: function (index, row) {
            console.log("onCheck" + JSON.stringify(row));
            var data = {}
            data.subSystemCode = $("#subSystemCode").textbox("getValue");
            data.roleId = DpRole.id;
            data.propertyValueId = row.id;
            DpPermissionProperty.relation.relate(data)
        },
        onUncheck: function (index, row) {
            console.log("onUncheck:" + JSON.stringify(row));
            var data = {}
            data.subSystemCode = $("#subSystemCode").textbox("getValue");
            data.roleId = DpRole.id;
            data.propertyValueId = row.id;
            DpPermissionProperty.relation.release(data);
        },
    });
});

DpRole = {};
DpRole.pageUrl = contextPath + "/rest/role/dp-role/page";
DpRole.dataUrl = contextPath + "/rest/role/dp-role/data";

DpRole.load = function () {
    param = {};
    if ($("#subSystemCode").textbox("getValue")) {
        param.subSystemCode = $("#subSystemCode").textbox("getValue")
    }
    if ($("#roleName").textbox("getValue")) {
        param.operationName = $("#roleName").textbox("getValue")
    }

    $('#DpRoleGrid').datagrid('reload', param);
};
DpRole.saveData = function (data) {
    for (const item of data.add) {
        item.subSystemCode = $("#subSystemCode").textbox("getValue");
    }

    $.ajax({
        type: "POST",
        url: DpRole.dataUrl,
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify(data),
        success: function (result) {
            alert("操作成功");
            console.log(JSON.stringify(result));
            $('#DpPermissionMetaDataGrid').datagrid('reload');
        }
    });
};

DpRolePermissionRelation = {};
DpRolePermissionRelation.listUrl = contextPath + "/rest/role/dp-role-permission-relation/list";
DpRolePermissionRelation.dataUrl = contextPath + "/rest/permission/dp-permission-item-metadata/data";


DpRolePermissionWindow = {};

DpRolePermissionWindow.listUrl = contextPath + "/rest/permission/dp-permission-metadata/list";
DpRolePermissionWindow.roleId = null;
DpRolePermissionWindow.metadataId = null;

DpRolePermissionWindow.openWindow = function () {
    DpRolePermissionWindow.roleId = null;
    transfer.refresh();
    let row = $('#DpRoleGrid').datagrid('getSelected');

    if (row != null) {
        DpRolePermissionWindow.roleId = row.id;
        $("#editRelationWindow").window('open');
        DpRolePermissionWindow.operationTableSearch();
    } else {
        alert("选择一个角色以编辑。")
    }
}

DpRolePermissionWindow.operationTableSearch = function () {
    let operationName = $("#operationName").textbox("getValue");
    let subsystemCode = $("#subSystemCode").textbox("getValue");
    $('#DpPermissionGrid').datagrid("reload",
        {'operationName': operationName, 'subSystemCode': subsystemCode});
}

transfer = {
    left: {
        id: "left-list",
        dataUrl: contextPath + "/rest/role/dp-role-permission-relation/unchecked",
        title: "未选择的权限",
        switchUrl: contextPath + "/rest/role/dp-role-permission-relation/relation",
    },
    right: {
        id: "right-list",
        dataUrl: contextPath + "/rest/role/dp-role-permission-relation/checked",
        title: "已选择的权限",
        switchUrl: contextPath + "/rest/role/dp-role-permission-relation/unrelation",
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
transfer.refresh = function () {
    for (var x in transfer) {
        this.refreshList(transfer[x]);
    }
}
transfer.refreshList = function (options) {
    $('#' + options.id).datalist("reload",
        {
            roleId: DpRolePermissionWindow.roleId,
            metadataId: DpRolePermissionWindow.metadataId
        });
}
transfer.switch = function (direction) {
    let config = transfer[direction];
    let param = [];
    let selected = $('#' + config.id).datalist("getSelections");


    if (selected) {
        for (let i in selected) {
            param.push({
                roleId: DpRolePermissionWindow.roleId,
                permissionId: selected[i].id
            });
        }
    }

    if (param.length > 0) {
        $.ajax({
            type: "POST",
            url: config.switchUrl,
            contentType: "application/json;charset=utf-8",
            data: JSON.stringify(param),
            success: function (result) {
                transfer.refresh();
            },
            error: function (result) {
                transfer.refresh();
            }
        });
    }
}


DpPermissionProperty = {define:{},value:{},relation:{}};
// DpPermissionProperty.define.listUrl = contextPath + "/rest/prop/dp-base-property-define/list";
DpPermissionProperty.define.listUrl = contextPath + "/rest/prop/dp-base-property-define/rolePropertyDefine";
DpPermissionProperty.define.dataUrl = contextPath + "/rest/prop/dp-base-property-define/data";

DpPermissionProperty.value.roleListUrl = contextPath + "/rest/prop/dp-base-property-value/rolePropertyList";

DpPermissionProperty.relation.relateUrl = contextPath + "/rest/role/dp-role-property-relation/relate";
DpPermissionProperty.relation.releaseUrl = contextPath + "/rest/role/dp-role-property-relation/release";


DpPermissionProperty.relation.relate = function (data) {
    $.ajax({
        type: "POST",
        url: DpPermissionProperty.relation.relateUrl,
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify(data),
        success: function (result) {
        },
        error: function (result) {
        }
    });
};
DpPermissionProperty.relation.release = function (data) {
    $.ajax({
        type: "POST",
        url: DpPermissionProperty.relation.releaseUrl,
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify(data),
        success: function (result) {
        },
        error: function (result) {
        }
    });

}