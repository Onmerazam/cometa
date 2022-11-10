<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>

    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="Sign Out"/>
    </form>
    <span><a href="/user">User list</a></span>
    <div>
        <form method="post" >
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <input type="text" required="true" name="numberColor" placeholder="Введите RAL" />
            <input type="text" required="true" name="coatingType" placeholder="Тип покрытия" />
            <input type="text" required="true" name="manufact" placeholder="Производитель" />
            <input type="number" required="true" name="value" placeholder="Количество" />
            <button type="submit">Добавить</button>
        </form>
    </div>
    <div>Поиск по RAL</div>
    <div>
        <form method="post" action="/filter">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <input type="text" name="filter" placeholder="Введите RAL" />
            <button type="submit">Поиск</button>
        </form>
    </div>
    <div>Список краски</div>
    <div>
    <table>
        <tr>
            <td>RAL</td>
            <td>Тип покрытия</td>
            <td>Производитель</td>
            <td>Значение</td>
        </tr>
        <#list colors as color>
        <tr>
            <td>${color.numberColor}</td>
            <td>${color.coatingType}</td>
            <td>${color.manufact}</td>
            <td class="note__body_${color.id}">${color.value}</td>
            <td class="tb"><a class="action" id='${color.id}'>Изменить</a></td>
            <td class="tb"><a href="/delete/?id=${color.id}">удалить</a></td>
        </tr>
        </#list>

    </table>
    </div>

<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="js/sb-admin-2.min.js"></script>

<!-- Page level plugins -->
<script src="vendor/datatables/jquery.dataTables.min.js"></script>
<script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>

<!-- Page level custom scripts -->
<script src="js/demo/datatables-demo.js"></script>
<script src="js/update.js"></script>
</@c.page>