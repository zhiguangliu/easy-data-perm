function endEditing(gridId) {
    let selectedRow = $("#" + gridId).datagrid("getSelected");
    if (selectedRow) {
        let index = $("#" + gridId).datagrid("getRowIndex", selectedRow);
        $("#" + gridId).datagrid("endEdit", index);
        return true;
    } else {
        return true;
    }
}

function appendItem(gridId) {
    if (endEditing(gridId)) {
        $('#' + gridId).edatagrid('addRow');
    }
}

function removeItem(gridId) {
    $('#' + gridId).edatagrid('destroyRow')
}

function acceptItems(gridId, saveData) {
    let data = getChanges(gridId);
    if (saveData(data)) {
        $('#' + gridId).datagrid('acceptChanges');
    }
}

function reject(gridId) {
    $('#' + gridId).datagrid('rejectChanges');
}

function getChanges(gridId) {
    let data = {"add": [], "del": [], "edit": []}
    if (endEditing(gridId)) {
        data.add = $('#' + gridId).datagrid('getChanges', 'inserted');
        data.del = $('#' + gridId).datagrid('getChanges', 'deleted');
        data.edit = $('#' + gridId).datagrid('getChanges', 'updated');
    }
    return data;
}
