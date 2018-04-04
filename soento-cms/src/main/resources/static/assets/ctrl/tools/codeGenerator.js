'use strict';

function addFileds() {
    var index = $('#fields tr').length;
    var tr = '<tr>';
    tr += '<td class="center">';
    tr += '<input type="text" id="field0' + index + '" name="field0' + index + '" value=""/>';
    tr += '</td>';
    tr += '<td class="center">';
    tr += '<input type="text" id="field1' + index + '" name="field1' + index + '" value=""/>';
    tr += '</td>';
    tr += '<td class="center">';
    tr += '<input type="text" id="field2' + index + '" name="field2' + index + '" value=""/>';
    tr += '</td>';
    tr += '<td class="center">';
    tr += '<input type="text" id="field3' + index + '" name="field3' + index + '" value=""/>';
    tr += '</td>';
    tr += '<td class="center">';
    tr += '<a class="btn btn-mini btn-danger" title="删除" onclick="$(this).parent().parent().remove()">删除行</a>';
    tr += '</td>'
    tr += '</tr>';
    $("#fields").append(tr);
}

function generatorCode() {

}