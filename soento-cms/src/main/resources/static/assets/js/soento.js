+function ($) {
    'use strict';
    $.isVoid = function (obj) {
        if (typeof (obj) == "undefined") {
            return true;
        } else if (typeof (obj) == "boolean" && obj != null) {
            return false;
        } else if (obj == null) {
            return true;
        } else if (obj == []) {
            return true;
        } else if (obj == '') {
            return true;
        } else {
            try {
                if ($.toJSON(obj) == $.toJSON({})) {
                    return true;
                } else {
                    return false;
                }
            } catch (e) {
                return false;
            }
        }
    }
    $.isNumber = function (input) {
        var regex = /^([-+]?[1-9]\d*)$/;
        if (input.match(regex)) {
            return true;
        } else {
            return false;
        }
    }
    $.isDecimal = function (input) {
        var regex = /^([-+]?[1-9]\d*\.\d+|-?0\.\d*[1-9]\d*)$/;
        if (input.match(regex)) {
            return true;
        } else {
            return false;
        }
    }
    $.isAlpha = function (input) {
        var regex = /^[A-Za-z]+$/;
        if (input.match(regex)) {
            return true;
        } else {
            return false;
        }
    }
    $.isAlphaOrNumber = function (input) {
        var regex = /^[0-9A-Za-z]+$/;
        if (input.match(regex)) {
            return true;
        } else {
            return false;
        }
    }
    $.isChinese = function (input) {
        var regex = /^[\u4e00-\u9fa5]+$/;
        if (input.match(regex)) {
            return true;
        } else {
            return false;
        }
    }
    $.isLength = function (input, lengthBegin, lengthEnd) {
        var pattern = '^.{' + lengthBegin + ',' + lengthEnd + '}$';
        var regex = new RegExp(pattern);
        if (input.match(regex)) {
            return true;
        } else {
            return false;
        }
    }
    // 对Date的扩展，将 Date 转化为指定格式的String
    // 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q)
    // 可以用 1-2 个占位符， 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
    // 例子：
    // (new Date()).format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
    // (new Date()).format("yyyy-M-d h:m:s.S") ==> 2006-7-2 8:9:4.18
    Date.prototype.format = function (fmt) { // author: meizz
        var o = {
            "M+": this.getMonth() + 1, // 月份
            "d+": this.getDate(), // 日
            "h+": this.getHours(), // 小时
            "m+": this.getMinutes(), // 分
            "s+": this.getSeconds(), // 秒
            "q+": Math.floor((this.getMonth() + 3) / 3), // 季度
            "S": this.getMilliseconds()
            // 毫秒
        };
        if (/(y+)/.test(fmt)) {
            fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        }
        for (var k in o) {
            if (new RegExp("(" + k + ")").test(fmt)) {
                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
            }
        }
        return fmt;
    }
    Date.prototype.toLocaleString = function (fmt) {
        return this.format("yyyy-MM-dd hh:mm:ss");
    }
    $.isDate = function (input) {
        if (Date.parse(input)) {
            return true;
        } else {
            return false;
        }
    }
    $.containsValue = function (arr, val) {
        return RegExp(val).test(arr);
    }
    // 验证固定电话号码 [3位或4位区号；
    // 区号可以用小括号括起来；
    // 区号可以省略；
    // 区号与本地号间可以用减号或空格隔开；
    // 可以有3位数的分机号，分机号前要加减号]
    $.isTelephone = function (input) {
        var regex = /^(((0\d2|0\d{2})[- ]?)?\d{8}|((0\d3|0\d{3})[- ]?)?\d{7})(-\d{3})?$/;
        if (input.match(regex)) {
            return true;
        } else {
            return false;
        }
    }
    // 验证手机号码
    // 可匹配"(+86)013325656352"
    // 括号可以省
    // +号可以省略
    // (+86)可以省略
    // 11位手机号前的0可以省略
    // 11位手机号第二位数可以是3、4、5、7、8中的任意一个
    $.isMobile = function (input) {
        var regex = /^((\+)?86|((\+)?86)?)0?1[34578]\d{9}$/;
        if (input.match(regex)) {
            return true;
        } else {
            return false;
        }
    }
    // 验证电话号码（可以是固定电话号码或手机号码）
    $.isPhone = function (input) {
        if (this.isTelephone(input) || this.isMobile(input)) {
            return true;
        } else {
            return false;
        }
    }
    // 验证邮政编码
    $.isZip = function (input) {
        var regex = /^\d{6}$/;
        if (input.match(regex)) {
            return true;
        } else {
            return false;
        }
    }
    // 验证电子邮箱
    // @字符前可以包含字母、数字、下划线和点号
    // @字符后可以包含字母、数字、下划线和点号
    // @字符后至少包含一个点号且点号不能是最后一个字符
    // 最后一个点号后只能是字母或数字
    $.isEmail = function (input) {
        var regex = /^([\w-\.]+)@([\w-\.]+)(\.[a-zA-Z0-9]+)$/;
        if (input.match(regex)) {
            return true;
        } else {
            return false;
        }
    }
    // 验证网址
    // 可以匹配IPv4地址但没对IPv4地址进行格式验证
    // IPv6暂时没做匹配
    // 允许省略"://"；可以添加端口号；允许层级；允许传参；域名中至少一个点号且此点号前要有内容
    $.isURL = function (input) {
        var regex = /^(((file|gopher|news|nntp|telnet|http|ftp|https|ftps|sftp):\/\/)|(www\.))+(([a-zA-Z0-9\._-]+\.[a-zA-Z]{2,6})|([0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}))(\/[a-zA-Z0-9\&%_\\.\/-~-]*)?$/;
        if (input.match(regex)) {
            return true;
        } else {
            return false;
        }
    }
    // 验证IPv4地址 [第一位和最后一位数字不能是0或255；允许用0补位]
    $.isIPv4 = function (input) {
        var regex = /^(25[0-4]|2[0-4]\d]|[01]?\d{2}|[1-9])\.(25[0-5]|2[0-4]\d]|[01]?\d?\d)\.(25[0-5]|2[0-4]\d]|[01]?\d?\d)\.(25[0-4]|2[0-4]\d]|[01]?\d{2}|[1-9])$/;
        if (input.match(regex)) {
            return true;
        } else {
            return false;
        }
    }
    // 验证IPv6地址 [可用于匹配任何一个合法的IPv6地址]
    $.isIPv6 = function (input) {
        var regex = /^\s*((([0-9A-Fa-f]{1,4}:){7}([0-9A-Fa-f]{1,4}|:))|(([0-9A-Fa-f]{1,4}:){6}(:[0-9A-Fa-f]{1,4}|((25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)(\.(25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)){3})|:))|(([0-9A-Fa-f]{1,4}:){5}(((:[0-9A-Fa-f]{1,4}){1,2})|:((25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)(\.(25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)){3})|:))|(([0-9A-Fa-f]{1,4}:){4}(((:[0-9A-Fa-f]{1,4}){1,3})|((:[0-9A-Fa-f]{1,4})?:((25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)(\.(25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)){3}))|:))|(([0-9A-Fa-f]{1,4}:){3}(((:[0-9A-Fa-f]{1,4}){1,4})|((:[0-9A-Fa-f]{1,4}){0,2}:((25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)(\.(25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)){3}))|:))|(([0-9A-Fa-f]{1,4}:){2}(((:[0-9A-Fa-f]{1,4}){1,5})|((:[0-9A-Fa-f]{1,4}){0,3}:((25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)(\.(25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)){3}))|:))|(([0-9A-Fa-f]{1,4}:){1}(((:[0-9A-Fa-f]{1,4}){1,6})|((:[0-9A-Fa-f]{1,4}){0,4}:((25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)(\.(25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)){3}))|:))|(:(((:[0-9A-Fa-f]{1,4}){1,7})|((:[0-9A-Fa-f]{1,4}){0,5}:((25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)(\.(25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)){3}))|:)))(%.+)?\s*$/;
        if (input.match(regex)) {
            return true;
        } else {
            return false;
        }
    }
    // 验证身份证号 [可验证一代或二代身份证]
    $.isIDCard = function (input) {
        input = input.toUpperCase();
        // 验证身份证号码格式 [一代身份证号码为15位的数字；二代身份证号码为18位的数字或17位的数字加字母X]
        if (!(/(^\d{15}$)|(^\d{17}([0-9]|X)$)/i.test(input))) {
            return false;
        }
        // 验证省份
        var arrCity = {
            11: '北京',
            12: '天津',
            13: '河北',
            14: '山西',
            15: '内蒙古',
            21: '辽宁',
            22: '吉林',
            23: '黑龙江 ',
            31: '上海',
            32: '江苏',
            33: '浙江',
            34: '安徽',
            35: '福建',
            36: '江西',
            37: '山东',
            41: '河南',
            42: '湖北',
            43: '湖南',
            44: '广东',
            45: '广西',
            46: '海南',
            50: '重庆',
            51: '四川',
            52: '贵州',
            53: '云南',
            54: '西藏',
            61: '陕西',
            62: '甘肃',
            63: '青海',
            64: '宁夏',
            65: '新疆',
            71: '台湾',
            81: '香港',
            82: '澳门',
            91: '国外'
        };
        if (arrCity[parseInt(input.substr(0, 2))] == null) {
            return false;
        }
        // 验证出生日期
        var regBirth, birthSplit, birth;
        var len = input.length;
        if (len == 15) {
            regBirth = new RegExp(/^(\d{6})(\d{2})(\d{2})(\d{2})(\d{3})$/);
            birthSplit = input.match(regBirth);
            birth = new Date('19' + birthSplit[2] + '/' + birthSplit[3] + '/' + birthSplit[4]);
            if (!(birth.getYear() == Number(birthSplit[2]) && (birth.getMonth() + 1) == Number(birthSplit[3]) && birth
                    .getDate() == Number(birthSplit[4]))) {
                return false;
            }
            return true;
        } else if (len == 18) {
            regBirth = new RegExp(/^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/i);
            birthSplit = input.match(regBirth);
            birth = new Date(birthSplit[2] + '/' + birthSplit[3] + '/' + birthSplit[4]);
            if (!(birth.getFullYear() == Number(birthSplit[2]) && (birth
                    .getMonth() + 1) == Number(birthSplit[3]) && birth
                    .getDate() == Number(birthSplit[4]))) {
                return false;
            }
            // 验证校验码
            var valnum;
            var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
            var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');
            var nTemp = 0, i;
            for (i = 0; i < 17; i++) {
                nTemp += input.substr(i, 1) * arrInt[i];
            }
            valnum = arrCh[nTemp % 11];
            if (valnum != input.substr(17, 1)) {
                return false;
            }
            return true;
        }
        return false;
    }
    // 验证经度
    $.isLongitude = function (input) {
        var regex = /^[-\+]?((1[0-7]\d{1}|0?\d{1,2})\.\d{1,5}|180\.0{1,5})$/;
        if (input.match(regex)) {
            return true;
        } else {
            return false;
        }
    }
    // 验证纬度
    $.isLatitude = function (input) {
        var regex = /^[-\+]?([0-8]?\d{1}\.\d{1,5}|90\.0{1,5})$/;
        if (input.match(regex)) {
            return true;
        } else {
            return false;
        }
    }
    // 获取checkbox值
    $.checkboxValues = function (name) {
        var values = [];
        $('input[name="' + name + '"]:checked').each(function () {
            values.push($(this).val());
        });
        return values;
    }


    if (layer === "undefined") {
        throw new Error("core requires a layer [http://layer.layui.com]");
    }
    $.tips = function (message, id) {
        layer.tips(message, '#' + id);
    }
    $.alert = function (message) {
        layer.alert(message);
    }
    $.message = function (message) {
        layer.msg(message);
    }
    $.success = function (success) {
        layer.alert(success, {icon: 1, title: '成功'});
    }
    $.error = function (error) {
        layer.alert(error, {icon: 2, title: '错误'});
    }
    $.warning = function (warning) {
        layer.alert(warning, {icon: 0, title: '警告'});
    }
    $.load = function () {
        layer.load();
    }
    $.dialog = function (id, title) {
        if (title == 'undefined') {
            title = false;
        } else {
            title = '<div class="page-header"><h1>' + title + '</h1></div>';
        }
        layer.open({
            type: 1,
            title: title,
            closeBtn: 0,
            shadeClose: true,
            content: $("#" + id).html()
        });
    }
    $.popup = function (url, title) {
        if (title == 'undefined') {
            title = false;
        } else {
            title = '<div class="page-header"><h1>' + title + '</h1></div>';
        }
        layer.open({
            title: title,
            type: 2,
            area: ['800px', '450px'],
            fixed: false,
            maxmin: true,
            content: url
        });
    }
    $.location = function (url) {
        window.location.href = url;
    }
    $.postJson = function (json, url, success) {
        if ($.isVoid(json)) {
            json = null;
        } else if (typeof (json) == "object") {
            json = $.toJSON(json);
        }
        $.ajax({
            url: url,
            type: "post",
            dataType: "json",
            data: json,
            contentType: "application/json",
            traditional: true,
            timeout: 10000,
            error: function () {
                $.error('请求失败,请稍后重试');
            },
            async: false,
            success: function (response) {
                var head = response.head
                if (head.code != '0000') {
                    $.error(head.desc);
                    return;
                }
                success(response.body);
            }
        });
    }
    $.setAuth = function () {
        //权限显示
        $("*[data-auth]").each(function () {
            var val = $.trim($(this).attr("data-auth"));
            if (auths.indexOf(val) != -1) {
                $(this).css("visibility", "visible");
            } else {
                $(this).remove()
            }
        })
    }
}(jQuery);