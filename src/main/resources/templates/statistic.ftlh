<#import "parts/common.ftlh" as c>

<@c.page>

    <#list statistic as stat>
        ${stat.getUser().getName()}<br>
        ${stat.getUser().getUrlImage()}<br><br>
    </#list>

</@c.page>