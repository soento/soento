'use strict';
$(function ($) {
    $('#btnLogin').on('click', function () {
        var username = $('#username').val();
        var password = $('#password').val();
        if ($.isVoid(username)) {
            $.tips('请输入登录用户名', 'username');
            return;
        }
        if ($.isVoid(password)) {
            $.tips('请输入登录密码', 'password');
            return;
        }
        var data = {"username": username, "password": password};
        $.postJson(data, '/doLogin', function (body) {
            $.location("/index");
        });
    });
});