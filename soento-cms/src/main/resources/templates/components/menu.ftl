<#if level == 1>
<ul class="nav nav-list">
<#else>
<ul class="submenu">
</#if>
<#list menuList?if_exists as menu>
    <li class="<#if menu.active?? >${menu.active}</#if>" id="<#if menu.name?? >${menu.name}</#if>">
        <#if menu.children?? && (menu.children?size > 0)>
        <a href="#" class="dropdown-toggle">
        <#else>
        <a href="<#if menu.link?? >${menu.link}</#if>">
        </#if>

        <i class="<#if menu.icon?? >${menu.icon}</#if>"></i>

        <#if level == 1><span class="menu-text"></#if>
    ${menu.text}
        <#if level == 1></span></#if>
        
        <#if menu.children?? && (menu.children?size > 0)>
            <b class="arrow fa fa-angle-down"></b>
        </#if>
    </a>
        <b class="arrow"></b>
        <#if menu.children?? && (menu.children?size > 0)>
            <#assign level = level + 1/>
            <#assign menuList = menu.children/>
            <#include "menu.ftl">
        </#if>
    </li>
</#list>
</ul>