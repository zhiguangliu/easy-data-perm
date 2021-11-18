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


function append(gridId) {
    if (endEditing(gridId)) {
        $('#' + gridId).edatagrid('addRow');
    }
}

function removeit(gridId) {
    $('#' + gridId).edatagrid('destroyRow')


    // let cell = $("#" + gridId).datagrid('cell');
    // console.log(JSON.stringify(cell));
    // console.log(cell.index);
    // if (!cell) {
    //     return
    // }
    // $("#" + gridId).datagrid('cancelEdit', cell.index)
    //     .datagrid('deleteRow', cell.index);
}

function acceptit(gridId, saveData) {
    if (true) {
        let data = getChanges(gridId);
        if (saveData(data)) {
            $('#' + gridId).datagrid('acceptChanges');
        }
    }
}

function reject(gridId) {
    $('#' + gridId).datagrid('rejectChanges');
}

function getChanges(gridId) {
    let data = {"add": [], "del": [], "edit": []}
    data.add = $('#' + gridId).datagrid('getChanges', 'inserted');
    data.del = $('#' + gridId).datagrid('getChanges', 'deleted');
    data.edit = $('#' + gridId).datagrid('getChanges', 'updated');
    return data;
}