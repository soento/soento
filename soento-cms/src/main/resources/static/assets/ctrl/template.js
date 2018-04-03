'use strict';

function showNowDate() {
    $("#spanNow").text(new Date().toLocaleString());
}

function showTask() {
    $.postJson(null, domain + 'userTask', function (data) {
        $("#taskSize1").html(data.length);
        $("#taskSize2").html(data.length);
        var html = "";
        for (var i = 0; i < data.length; i++) {
            html += '<li>';
            html += '<a href="' + domain + 'showUserTask?code=' + data[i].code + '">';
            html += '<div class="clearfix">';
            var name = data[i].name;
            if (name.length > 20) {
                name = name.substr(0, 20) + '......';
            }
            html += '<span class="pull-left">' + name + '</span>';
            html += '<span class="pull-right">' + data[i].percent + '%</span>';
            html += '</div>';
            html += '<div class="progress progress-mini">';
            html += '<div style="width:' + data[i].percent + '%" class="progress-bar"></div>';
            html += '</div>';
            html += '</a>';
            html += '</li>';
        }
        $("#taskList").html(html);
    });
}

function showNotice() {
    $.postJson(null, domain + 'userNotice', function (data) {
        $("#noticeSize1").html(data.length);
        $("#noticeSize2").html(data.length);
        var html = "";
        for (var i = 0; i < data.length; i++) {
            html += '<li>';
            html += '<a href="' + domain + 'showUserNotice?code=' + data[i].code + '">';
            html += '<div class="clearfix">';
            html += '<span class="pull-left">';
            var content = data[i].content;
            if (content.length > 20) {
                content = name.substr(0, 20) + '......';
            }
            html += '<i class="btn btn-xs no-hover btn-pink fa fa-comment"></i>' + content;
            html += '</span>';
            html += '<span class="pull-right badge badge-info">' + data[i].type + '</span>';
            html += '</div>';
            html += '</a>';
            html += '</li>';
        }
        $("#noticeList").html(html);
    });
}

function showMessage() {
    $.postJson(null, domain + 'userMessage', function (data) {
        $("#messageSize1").html(data.length);
        $("#messageSize2").html(data.length);
        var html = "";
        for (var i = 0; i < data.length; i++) {
            html += '<li>';
            html += '<a href="' + domain + 'showUserMessage?code=' + data[i].code + '" class="clearfix">';
            html += '<img src="' + domain + data[i].avatar + '" class="msg-photo"';
            html += 'alt="' + data[i].niceName + '"/>';
            html += '<span class="msg-body">';
            html += '<span class="msg-title">';
            var content = data[i].content;
            if (content.length > 20) {
                content = name.substr(0, 20) + '......';
            }
            html += '<span class="blue">' + data[i].niceName + ':</span>' + content + '';
            html += '</span>';
            html += '<span class="msg-time">';
            html += '<i class="ace-icon fa fa-clock-o"></i>';
            html += '<span>' + data[i].sendTime + '</span>';
            html += '</span>';
            html += '</span>';
            html += '</a>';
            html += '</li>';
        }
        $("#messageList").html(html);
    });
}

$(function ($) {
    showNowDate();
    setInterval("showNowDate()", 1000);
    if (auths && $.inArray("UserTask", auths) != -1) {
        showTask();
    }
    if (auths && $.inArray("UserNotice", auths) != -1) {
        showNotice();
    }
    if (auths && $.inArray("UserMessage", auths) != -1) {
        showMessage();
    }
    $('#btnLogout').on('click', function () {
        $.postJson(null, '/doLogout', function (body) {
            $.location("/login");
        });
    });
});