$(function () {
    $('#DpSubSystemGrid').datagrid('enableCellEditing');
});

SubSystem = {};
SubSystem.dataUrl = contextPath + "/rest/DpSubSystem/data";


SubSystem.saveData = function (data) {
    $.ajax({
        type: "POST",
        url: SubSystem.dataUrl,
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
