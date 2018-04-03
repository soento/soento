<div class="breadcrumbs ace-save-state" id="breadcrumbs">
<#if breadcrumbs?? && (breadcrumbs?size > 0) >
    <ul class="breadcrumb">
        <#list breadcrumbs?reverse as breadcrumb>
            <#if breadcrumb_index + 1 == breadcrumb?size>
            <li class="active">
            <#else>
            <li>
            </#if>
            <#if breadcrumb.icon??>
                <i class="ace-icon fa ${breadcrumb.icon}"></i>
            </#if>
            <#if breadcrumb.link??>
                <a href="${breadcrumb.link}">${breadcrumb.text}</a>
            <#else>
            ${breadcrumb.text}
            </#if>
        </li>
        </#list>
    </ul><!-- /.breadcrumb -->
</#if>
    <div class="nav-search" id="nav-search">
        <span class="input-icon" id="spanNow"></span>
    </div><!-- /.nav-search -->
</div>