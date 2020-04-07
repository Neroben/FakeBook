<#import "parts/common.ftlh" as c>

<@c.page>

    <#list statistic as stat>
        ${stat.getUser().getName()}<p> </p>
        ${stat.getUser().getUrlImage()}<br>
    </#list>

</@c.page>