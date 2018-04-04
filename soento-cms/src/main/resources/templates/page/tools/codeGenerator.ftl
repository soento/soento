<form action="tools/codeGenerator/generator" name="Form" id="Form" method="post">
    <input type="hidden" name="zindex" id="zindex" value="0">
    <input type="hidden" name="Szindex" id="Szindex" value="0">
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
                                       placeholder="这里输入页面名  (请不要输入特殊字符,请用纯字母，建议以驼峰式命名规范)" style="width:370px"
                                       title="页面名"/>
                </td>
                <td>&nbsp;&nbsp;例如:<font color="red" style="font-weight: bold;">welcome</font>&nbsp;&nbsp;红色部分
                </td>
            </tr>
            <tr>
                <td style="width:100px;text-align: right;">数据库表名：</td>
                <td colspan="1"><input type="text" name="tableName" id="tableName" value=""
                                       placeholder="这里输入数据库表名  (请不要输入特殊字符,请用纯字母，单词之间以下划线分割" style="width:370px"
                                       title="数据库表名"/>
                </td>
                <td>&nbsp;&nbsp;例如:<font color="red" style="font-weight: bold;">ACCOUNT_INFO</font>&nbsp;&nbsp;红色部分
                </td>
            </tr>
            <tr>
                <td style="width:100px;text-align: right;">数据表前缀：</td>
                <td colspan="1"><input type="text" name="preTableName" id="preTableName" value="T_"
                                       placeholder="这里输入数据表前缀  (请不要输入特殊字符,请用纯字母，单词之间以下划线分割" style="width:370px"
                                       title="数据表前缀"/>
                </td>
                <td>&nbsp;&nbsp;例如:<font color="red" style="font-weight: bold;">T_SYS</font>&nbsp;&nbsp;红色部分
                </td>
            </tr>
        </table>

        <table id="table_report" class="table table-striped table-bordered table-hover" style="margin-top: 10px;">
            <thead>
            <tr>
                <th class="center">属性名</th>
                <th class="center">类型</th>
                <th class="center">长度</th>
                <th class="center">备注</th>
            </tr>
            </thead>
            <tbody id="fields">
            <tr>
                <td class="center">ID<input type="hidden" name="field00" value="ID"/></td>
                <td class="center">字符串<input type="hidden" name="field10" value="VARCHAR"/></td>
                <td class="center">32<input type="hidden" name="field20" value="32"/></td>
                <td class="center">默认主键<input type="hidden" name="field30" value="默认主键"/></td>
            </tr>
            <tr>
                <td class="center">CREATE_BY<input type="hidden" name="field01" value="CREATE_BY"/></td>
                <td class="center">字符串<input type="hidden" name="field11" value="VARCHAR"/></td>
                <td class="center">32<input type="hidden" name="field21" value="32"/></td>
                <td class="center">创建人<input type="hidden" name="field31" value="创建人"/></td>
            </tr>
            <tr>
                <td class="center">CREATE_DATE<input type="hidden" name="field02" value="CREATE_DATE"/></td>
                <td class="center">日期<input type="hidden" name="field12" value="DATETIME"/></td>
                <td class="center">-<input type="hidden" name="field22" value="-"/></td>
                <td class="center">创建日期<input type="hidden" name="field32" value="创建日期"/></td>
            </tr>
            <tr>
                <td class="center">MODIFY_BY<input type="hidden" name="field03" value="MODIFY_BY"/></td>
                <td class="center">字符串<input type="hidden" name="field13" value="VARCHAR"/></td>
                <td class="center">32<input type="hidden" name="field23" value="32"/></td>
                <td class="center">修改人<input type="hidden" name="field33" value="修改人"/></td>
            </tr>
            <tr>
                <td class="center">MODIFY_DATE<input type="hidden" name="field02" value="MODIFY_DATE"/></td>
                <td class="center">日期<input type="hidden" name="field12" value="DATETIME"/></td>
                <td class="center">-<input type="hidden" name="field22" value="-"/></td>
                <td class="center">修改日期<input type="hidden" name="field32" value="修改日期"/></td>
            </tr>
            <tr>
                <td class="center">DELETE_FLAG<input type="hidden" name="field03" value="DELETE_FLAG"/></td>
                <td class="center">整数<input type="hidden" name="field13" value="INT"/></td>
                <td class="center">1<input type="hidden" name="field23" value="1"/></td>
                <td class="center">删除标识 0：未删除，1：已删除<input type="hidden" name="field33" value="删除标识 0：未删除，1：已删除"/></td>
            </tr>
            </tbody>
        </table>

        <table id="table_report" class="table table-striped table-bordered table-hover">
            <tr>
                <td style="text-align: center;" colspan="100">
                    <a class="btn btn-app btn-success btn-mini" onclick="dialog_open();">
                        <i class="icon-ok"></i>新增
                    </a>
                    <a class="btn btn-app btn-success btn-mini" onclick="save();" id="productc">
                        <i class="icon-print"></i>生成
                    </a>
                    <a class="btn btn-app btn-danger btn-mini" onclick="top.Dialog.close();">
                        <i class="icon-share-alt"></i>取消
                    </a>
                </td>
            </tr>
        </table>
    </div>

</form>