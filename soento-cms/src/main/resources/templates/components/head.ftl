<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<meta charset="utf-8"/>
<title><@spring.title/></title>

<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>

<!-- bootstrap & fontawesome -->
<link rel="stylesheet" href="${domain}assets/css/bootstrap.min.css"/>
<link rel="stylesheet" href="${domain}assets/font-awesome/4.5.0/css/font-awesome.min.css"/>

<!-- page specific plugin styles -->
<#if name?? && style?? && 'true' == style?string('true','false')>
    <#include "../style/${name}.ftl">
</#if>

<!-- text fonts -->
<link rel="stylesheet" href="${domain}assets/css/fonts.googleapis.com.css"/>

<!-- ace styles -->
<link rel="stylesheet" href="${domain}assets/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style"/>

<!--[if lte IE 9]>
<link rel="stylesheet" href="${domain}assets/css/ace-part2.min.css" class="ace-main-stylesheet"/>
<![endif]-->
<link rel="stylesheet" href="${domain}assets/css/ace-skins.min.css"/>
<link rel="stylesheet" href="${domain}assets/css/ace-rtl.min.css"/>

<!--[if lte IE 9]>
<link rel="stylesheet" href="${domain}assets/css/ace-ie.min.css"/>
<![endif]-->

<!-- ace settings handler -->
<script src="${domain}assets/js/ace-extra.min.js"></script>

<!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->
<!--[if lte IE 8]>
<script src="${domain}assets/js/html5shiv.min.js"></script>
<script src="${domain}assets/js/respond.min.js"></script>
<![endif]-->
