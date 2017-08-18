$(function () {
    $("#user_datagrid").datagrid({
        url: "/employee/selectByCurrentUser",//根据当前用户的用户名查询用户信息
        fit: true,
        rownumbers: true,
        singleSelect: true,
        striped: true,
        pagination: true,
        fitColumns: true
    });
}) 
function workingConditionFormatter(value, record, index) {
    if (value) {
        return value.name;
    }
}