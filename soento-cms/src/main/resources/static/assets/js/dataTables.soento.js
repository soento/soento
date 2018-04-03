+function ($) {
    'use strict';
    $.fn.dataTable.defaults.oLanguage = {
        "oAria": {
            "sSortAscending": ": 升序",
            "sSortDescending": ": 降序"
        },
        "oPaginate": {
            "sFirst": "首页",
            "sLast": "末页",
            "sNext": "下页",
            "sPrevious": "上页"
        },
        "sEmptyTable": "没有相关记录",
        "sInfo": "第 _START_ 到 _END_ 条记录，共 _TOTAL_ 条",
        "sInfoEmpty": "第 0 到 0 条记录，共 0 条",
        "sInfoFiltered": "(从 _MAX_ 条记录中检索)",
        "sInfoPostFix": "",
        "sDecimal": "",
        "sThousands": ",",
        "sLengthMenu": "每页显示条数: _MENU_",
        "sLoadingRecords": "正在加载中...",
        "sProcessing": "正在加载中...",
        "sSearch": "搜索:",
        "sSearchPlaceholder": "",
        "sUrl": "",
        "sZeroRecords": "没有相关记录"
    };
    $.fn.dataTable.defaults.bAutoWidth = false;
    $.fn.dataTable.defaults.bServerSide = true;
    $.fn.dataTable.defaults.bProcessing = true;
    $.fn.dataTable.defaults.sScrollY = "400px";
    $.fn.dataTable.defaults.sScrollX = "100%";
    $.fn.dataTable.defaults.sScrollXInner = "100%";
    $.fn.dataTable.defaults.bScrollCollapse = true;
    $.fn.dataTable.defaults.iDisplayLength = 10;
    $.fn.dataTable.defaults.aLengthMenu = [[10, 25, 50, 100, -1], [10, 25, 50, 100, "全部"]];
    $.fn.dataTable.Buttons.defaults.dom.container.className = 'dt-buttons btn-overlap btn-group btn-overlap';
    var DataTables = function (element, options) {
        this.$element = $(element);
        this.options = $.extend(true, {}, DataTables.DEFAULTS, options);
        this.table = null;
    };
    DataTables.DEFAULTS = {
        ajax: "",
        params: [],
        columns: [],
        fnServerData: null,
        order: []
    };
    // 初始化ataTable
    DataTables.prototype.init = function () {
        var that = this;
        that.options.fnServerData = that.fnServerData();
        that.table = that.$element.DataTable(that.options);
        new $.fn.dataTable.Buttons(that.table, {
            buttons: [
                {
                    "extend": "colvis",
                    "text": "<i class='fa fa-search bigger-110 blue'></i> <span class='hidden'>显示/隐藏</span>",
                    "className": "btn btn-white btn-primary btn-bold",
                    columns: ':not(:first):not(:last)'
                },
                {
                    "extend": "copy",
                    "text": "<i class='fa fa-copy bigger-110 pink'></i> <span class='hidden'>复制</span>",
                    "className": "btn btn-white btn-primary btn-bold"
                },
                {
                    "extend": "csv",
                    "text": "<i class='fa fa-database bigger-110 orange'></i> <span class='hidden'>导出CSV</span>",
                    "className": "btn btn-white btn-primary btn-bold"
                },
                {
                    "extend": "excel",
                    "text": "<i class='fa fa-file-excel-o bigger-110 green'></i> <span class='hidden'>导出Excel</span>",
                    "className": "btn btn-white btn-primary btn-bold"
                },
                {
                    "extend": "pdf",
                    "text": "<i class='fa fa-file-pdf-o bigger-110 red'></i> <span class='hidden'>导出PDF</span>",
                    "className": "btn btn-white btn-primary btn-bold"
                },
                {
                    "extend": "print",
                    "text": "<i class='fa fa-print bigger-110 grey'></i> <span class='hidden'>打印</span>",
                    "className": "btn btn-white btn-primary btn-bold",
                    autoPrint: false,
                    message: '查询结果信息'
                }
            ]
        });
        that.table.buttons().container().appendTo($('.tableTools-container'));
        //style the message box
        var defaultCopyAction = that.table.button(1).action();
        that.table.button(1).action(function (e, dt, button, config) {
            defaultCopyAction(e, dt, button, config);
            $('.dt-button-info').addClass('gritter-item-wrapper gritter-info gritter-center white');
        });
        var defaultColvisAction = that.table.button(0).action();
        that.table.button(0).action(function (e, dt, button, config) {
            defaultColvisAction(e, dt, button, config);
            if ($('.dt-button-collection > .dropdown-menu').length == 0) {
                $('.dt-button-collection')
                    .wrapInner('<ul class="dropdown-menu dropdown-light dropdown-caret dropdown-caret" />')
                    .find('a').attr('href', '#').wrap("<li />")
            }
            $('.dt-button-collection').appendTo('.tableTools-container .dt-buttons')
        });
        setTimeout(function () {
            $($('.tableTools-container')).find('a.dt-button').each(function () {
                var div = $(this).find(' > div').first();
                if (div.length == 1) div.tooltip({container: 'body', title: div.parent().text()});
                else $(this).tooltip({container: 'body', title: $(this).text()});
            });
        }, 500);
        return that.table;
    };
    // fnServerData 数据拼接
    DataTables.prototype.fnServerData = function () {
        var that = this;
        var serverData = function (path, aoData, fnCallback) {
            var request = {};
            for (var i in aoData) {
                request[aoData[i]['name']] = aoData[i]['value'];
            }
            var param = {};
            var params = that.options.params;
            for (var i in params) {
                param[params[i]['field']] = params[i]['value']();
            }
            request['param'] = param;
            $.postJson(JSON.stringify(request), that.options.ajax, fnCallback);
        }
        return serverData;
    };

    // ========================
    function DataTablePlugin(option) {
        var options = typeof option == 'object' && option;
        var dataTable = new DataTables(this, options);
        var table = dataTable.init();
        return table;
    };
    $.fn.ViewDataTable = DataTablePlugin;
}($);