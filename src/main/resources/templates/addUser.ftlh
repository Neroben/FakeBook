<#import "parts/common.ftlh" as c>

<@c.page>
    ${message?ifExists}
    <form method="post">
        <input type="text" name="Name" placeholder="Введите имя"><br>
        <input type="text" name="email" placeholder="Введите email"><br>
        <input type="password" name="password" placeholder="Введите пароль"><br>
        <input type="text" name="urlImage" placeholder="Введите url изображения"><br>
        <button type="submit">Добавить</button>
    </form>
</@c.page>