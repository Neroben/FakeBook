<#import "parts/common.ftlh" as c>

<@c.page>

    ${name?ifExists}<br>
    ${email?ifExists}<br>
    <a href="${urlImage?ifExists}">${urlImage?ifExists}</a>

</@c.page>