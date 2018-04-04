<form action="tools/codeGenerator/generator" method="post">
    <div>
        <table style="margin-top: 10px;" border="0">
            <tr>
                <td style="width:100px;text-align: right;">上级包全名：</td>
                <td colspan="1"><input type="text" name="packageName" id="packageName" value=""
                                       placeholder="这里输入上级包全名  (请不要输入特殊字符,请用纯字母)" style="width:370px" title="上级包全名"/>
                </td>
                <td>&nbsp;&nbsp;例如:<font color="red" style="font-weight: bold;">com.soento.cms.</font>&nbsp;&nbsp;红色部分
                </td>
            </tr>
            <tr>
                <td style="width:100px;text-align: right;">模块名：</td>
                <td colspan="1"><input type="text" name="moduleName" id="moduleName" value=""
                                       placeholder="这里输入模块名  (请不要输入特殊字符,请用纯字母)" style="width:370px" title="模块名"/>
                </td>
                <td>&nbsp;&nbsp;例如:<font color="red" style="font-weight: bold;">demo</font>&nbsp;&nbsp;红色部分
                </td>
            </tr>
            <tr>
                <td style="width:100px;text-align: right;">页面名：</td>
                <td colspan="1"><input type="text" name="pageName" id="pageName" value=""
                                       placeholder="请不要输入特殊字符,请用纯字母，建议以驼峰式命名规范" style="width:370px"
                                       title="页面名"/>
                </td>
                <td>&nbsp;&nbsp;例如:<font color="red" style="font-weight: bold;">welcome</font>&nbsp;&nbsp;红色部分
                </td>
            </tr>
            <tr>
                <td style="width:100px;text-align: right;">数据库表名：</td>
                <td colspan="1"><input type="text" name="tableName" id="tableName" value=""
                                       placeholder="请不要输入特殊字符,请用纯字母，单词之间以下划线分割" style="width:370px"
                                       title="数据库表名"/>
                </td>
                <td>&nbsp;&nbsp;例如:<font color="red" style="font-weight: bold;">ACCOUNT_INFO</font>&nbsp;&nbsp;红色部分
                </td>
            </tr>
            <tr>
                <td style="width:100px;text-align: right;">数据表前缀：</td>
                <td colspan="1"><input type="text" name="preTableName" id="preTableName" value="T_"
                                       placeholder="请不要输入特殊字符,请用纯字母，单词之间以下划线分割，以下划线结尾" style="width:370px"
                                       title="数据表前缀"/>
                </td>
                <td>&nbsp;&nbsp;例如:<font color="red" style="font-weight: bold;">T_SYS_</font>&nbsp;&nbsp;红色部分
                </td>
            </tr>
        </table>

        <table class="table table-striped table-bordered table-hover" style="margin-top: 10px;">
            <thead>
            <tr>
                <th class="center">属性名</th>
                <th class="center">类型</th>
                <th class="center">长度</th>
                <th class="center">备注</th>
                <th class="center" style="width:80px;">操作</th>
            </tr>
            </thead>
            <tbody id="fields">
            <tr>
                <td class="center">
                    <input type="text" name="fieldCode" value="ID" readonly/>
                </td>
                <td class="center">
                    <select name="fieldType" disabled>
                        <option value="VARCHAR" selected>字符串</option>
                        <option value="DATETIME">日期</option>
                        <option value="INT">整数</option>
                    </select>
                </td>
                <td class="center">
                    <input type="text" name="fieldLength" value="32" readonly/>
                </td>
                <td class="center">
                    <input type="text" name="fieldComment" value="默认主键" readonly/>
                </td>
                <td class="center">
                    ----
                </td>
            </tr>
            <tr>
                <td class="center">
                    <input type="text" name="fieldCode" value="CREATE_BY" readonly/>
                </td>
                <td class="center">
                    <select name="fieldType" disabled>
                        <option value="VARCHAR" selected>字符串</option>
                        <option value="DATETIME">日期</option>
                        <option value="INT">整数</option>
                    </select>
                </td>
                <td class="center">
                    <input type="text" name="fieldLength" value="32" readonly/>
                </td>
                <td class="center">
                    <input type="text" name="fieldComment" value="创建人" readonly/>
                </td>
                <td class="center">
                    ----
                </td>
            </tr>
            <tr>
                <td class="center">
                    <input type="text" name="fieldCode" value="CREATE_DATE" readonly/>
                </td>
                <td class="center">
                    <select name="fieldType" disabled>
                        <option value="VARCHAR">字符串</option>
                        <option value="DATETIME" selected>日期</option>
                        <option value="INT">整数</option>
                    </select>
                </td>
                <td class="center">
                    <input type="text" name="fieldLength" value="-" readonly/>
                </td>
                <td class="center">
                    <input type="text" name="fieldComment" value="创建日期" readonly/>
                </td>
                <td class="center">
                    ----
                </td>
            </tr>
            <tr>
                <td class="center">
                    <input type="text" name="fieldCode" value="MODIFY_BY" readonly/>
                </td>
                <td class="center">
                    <select name="fieldType" disabled>
                        <option value="VARCHAR" selected>字符串</option>
                        <option value="DATETIME">日期</option>
                        <option value="INT">整数</option>
                    </select>
                </td>
                <td class="center">
                    <input type="text" name="fieldLength" value="32" readonly/>
                </td>
                <td class="center">
                    <input type="text" name="fieldComment" value="修改人" readonly/>
                </td>
                <td class="center">
                    ----
                </td>
            </tr>
            <tr>
                <td class="center">
                    <input type="text" name="fieldCode" value="MODIFY_DATE" readonly/>
                </td>
                <td class="center">
                    <select name="fieldType" disabled>
                        <option value="VARCHAR">字符串</option>
                        <option value="DATETIME" selected>日期</option>
                        <option value="INT">整数</option>
                    </select>
                </td>
                <td class="center">
                    <input type="text" name="fieldLength" value="-" readonly/>
                </td>
                <td class="center">
                    <input type="text" name="fieldComment" value="修改日期" readonly/>
                </td>
                <td class="center">
                    ----
                </td>
            </tr>
            <tr>
                <td class="center">
                    <input type="text" name="fieldCode" value="DELETE_FLAG" readonly/>
                </td>
                <td class="center">
                    <select name="fieldType" disabled>
                        <option value="VARCHAR">字符串</option>
                        <option value="DATETIME">日期</option>
                        <option value="INT" selected>整数</option>
                    </select>
                </td>
                <td class="center">
                    <input type="text" name="fieldLength" value="1" readonly/>
                </td>
                <td class="center">
                    <input type="text" name="fieldComment" value="删除标识 0：未删除，1：已删除" readonly/>
                </td>
                <td class="center">
                    ----
                </td>
            </tr>
            </tbody>
        </table>

        <table id="table_report" class="table table-striped table-bordered table-hover">
            <tr>
                <td style="text-align: center;" colspan="100">
                    <a class="btn btn-app btn-success btn-mini" id="addFields" onclick="addFileds();">
                        <i class="icon-ok"></i>新增
                    </a>
                    <a class="btn btn-app btn-success btn-mini" id="generator" onclick="generatorCode();">
                        <i class="icon-print"></i>生成
                    </a>
                </td>
            </tr>
        </table>
    </div>

</form>