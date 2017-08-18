$(function () {
    /*
     * 抽取所有需要用得元素.
     */
    var systemDictionaryDatagrid, systemDictionaryDialog, systemDictionaryForm, systemDictionaryItemDatagrid;
    systemDictionaryItemDatagrid = $("#systemDictionaryItem_datagrid");
    systemDictionaryDatagrid = $("#systemDictionary_datagrid");
    systemDictionaryDialog = $("#systemDictionary_dialog");
    systemDictionaryForm = $("#systemDictionary_form");
    /*
     * 初始化数据表格
     */
    systemDictionaryDatagrid.datagrid({
        url: "/systemDictionary/list",
        fit: true,
        rownumbers: true,
        singleSelect: true,
        striped: true,
        pagination: true,
        fitColumns: true,
        onClickRow: function (rowIndex, rowData) {
            var options = systemDictionaryItemDatagrid.datagrid("options");
            options.url = "systemDictionaryItem/queryPageFromsystemDictionary?parentId=" + rowData.id;
            systemDictionaryItemDatagrid.datagrid("load");
        }
    });
});
function parentFormatter(value, rowDate, index) {
    console.log(value);
    if (value) {
        return value.name;
    }
    return value;
}


