<!-- basic scripts -->
<script language="JavaScript">
    var domain = '${domain}';
    <#if auths??>
    var auths = ${auths};
    </#if>
</script>
<!--[if !IE]> -->
<script src="${domain}assets/js/jquery-2.1.4.min.js"></script>
<!-- <![endif]-->

<!--[if IE]>
<script src="${domain}assets/js/jquery-1.11.3.min.js"></script>
<![endif]-->
<script type="text/javascript">
    if ('ontouchstart' in document.documentElement) document.write("<script src='${domain}assets/js/jquery.mobile.custom.min.js'>" + "<" + "/script>");
</script>
<script src="${domain}assets/js/jquery.json-2.3.min.js"></script>
<script src="${domain}assets/js/bootstrap.min.js"></script>
<script src="${domain}assets/layer/layer.js"></script>

<!-- page specific plugin scripts -->
<#if name?? && script?? && 'true' == script?string('true','false')>
    <#include "../script/${name}.ftl">
</#if>

<!-- ace scripts -->
<script src="${domain}assets/js/ace-elements.min.js"></script>
<script src="${domain}assets/js/ace.min.js"></script>

<!-- soento scripts -->
<script src="${domain}assets/js/soento.js"></script>
<#if name != "login">
<script src="${domain}assets/ctrl/template.js"></script>
</#if>
<!-- inline scripts related to this page -->
<#if name?? && ctrl?? && 'true' == ctrl?string('true','false')>
<script src="${domain}assets/ctrl/${name}.js"></script>
</#if>
<!-- basic scripts -->