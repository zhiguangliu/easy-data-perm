function endEditing(gridId) {
    return endCellEdit($("#" + gridId), true);


    // if (editIndex == undefined){return true}
    // if ($('#dg').datagrid('validateRow', editIndex)){
    //     $('#dg').datagrid('endEdit', editIndex);
    //     editIndex = undefined;
    //     return true;
    // } else {
    //     return false;
    // }
}

function endCellEdit(target, accepted) {
    var dg = $(target);
    var cell = dg.datagrid('cell');
    if (cell) {
        var input = dg.datagrid('input', cell);
        if (input) {
            if (accepted) {
                if (dg.datagrid('validateRow', cell.index)) {
                    dg.datagrid('endEdit', cell.index);
                    dg.datagrid('gotoCell', cell);
                } else {
                    dg.datagrid('gotoCell', cell);
                    input.focus();
                    return false;
                }
            } else {
                dg.datagrid('cancelEdit', cell.index);
                dg.datagrid('gotoCell', cell);
            }
        }
    }
    return true;
}

function append(gridId) {
    if (endEditing(gridId)) {
        $('#' + gridId).datagrid('appendRow', {status: 'P'});
        // editIndex = $('#dg').datagrid('getRows').length-1;
        // $('#dg').datagrid('selectRow', editIndex)
        //     .datagrid('beginEdit', editIndex);
    }
}

function removeit(gridId) {
    let cell = $("#" + gridId).datagrid('cell');
    console.log(JSON.stringify(cell));
    console.log(cell.index);
    if (!cell) {
        return
    }
    $("#" + gridId).datagrid('cancelEdit', cell.index)
        .datagrid('deleteRow', cell.index);
}

function acceptit(gridId, saveData) {
    if (endEditing(gridId)) {
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