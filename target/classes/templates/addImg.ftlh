<#import "parts/common.ftlh" as c>

<@c.page>
<form method = "post" enctype="multipart/form-data">
    <input type="file" name="file">
    <button type="submit">Добавить</button>
</form>
</@c.page>