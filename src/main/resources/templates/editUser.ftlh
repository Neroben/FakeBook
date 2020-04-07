<#import "parts/common.ftlh" as c>

<@c.page>
    <div>
        ${message?ifExists}
        <form method="post">
            <input type="text" name="id" placeholder="Введите ID пользователя"><br>
            <input type="checkbox" name="active">Активность</input><br>
            <input type="submit">Изменить активность</input>
        </form>
    </div>
</@c.page>