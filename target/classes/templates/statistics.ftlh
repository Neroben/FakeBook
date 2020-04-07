<#import "parts/common.ftlh" as c>

<@c.page>
    <form method="post">
        <input type="text" name="timestamp" placeholder="Введите уникальный ID запроса">
        <input type="checkbox" name="active">Активность</input>
        <button type="submit">Показать статистику</button>
    </form>
</@c.page>