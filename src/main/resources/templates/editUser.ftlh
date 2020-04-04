<#import "parts/common.ftlh" as c>

<@c.page>
    ${message?ifExists}

    <form method="post">
        <input type="text" name="id" placeholder="Введите ID">
        <input type="checkbox" name="active" placeholder="Активность">Активность</input>
        <button type="submit">Установить</button>
    </form>

</@c.page>