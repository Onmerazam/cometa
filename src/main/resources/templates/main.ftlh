<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>
<#import "parts/sidebar.ftlh" as sb>
<#import "parts/topbar.ftlh" as tb>
<@c.page>

<div id="wrapper">

    <!-- Sidebar -->
    <@sb.sidebar />
    <!-- End of Sidebar -->

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

        <!-- Main Content -->
        <div id="content">

            <!-- Topbar -->
            <@tb.topbar "${user.username}" />
            <!-- End of Topbar -->

            <!-- Begin Page Content -->
            <div class="container-fluid">

                <!-- Page Heading -->
                <h1 class="h3 mb-2 text-gray-800">Добавление позиций</h1>
                <p class="mb-4">Форма ввода (Добавить позицию) позволяет добавлять новые позиции, заполнение
                    полей обязательно. Десятичная часть поля "Количество" отделяется при помощи точки</p>

                <!--Input form -->
                <div class="card shadow mb-4">
                    <div class="card-header py-3">
                        <h6 class="m-0 font-weight-bold text-primary">Добавить позицию</h6>
                    </div>
                    <div class="card-body">
                        <form method="post">
                            <div class="form-row d-block d-md-flex">
                                <div class="col">
                                    <div class="form-group">
                                        <input type="number" class="form-control" name="numberColor" aria-describedby="emailHelp" placeholder="RAL">
                                        <small class="form-text text-muted">Необходимо вводить только цифры</small>
                                    </div>
                                </div>
                                <div class="col">
                                    <div class="form-group">
                                        <input type="text" class="form-control" name="coatingType" placeholder="Тип покрытия">
                                    </div>
                                </div>
                                <div class="col">
                                    <div class="form-group">
                                        <input type="text" class="form-control" name="manufact" placeholder="Производитель">
                                    </div>
                                </div>
                                <div class="col">
                                    <div class="form-group">
                                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                                        <input type="number" step="0.1" class="form-control" name="value" placeholder="Количество">
                                        <small class="form-text text-muted">Десятая часть отделяется точкой</small>
                                    </div>
                                </div>
                                <div class="col">
                                    <div class="form-group">
                                        <button type="submit" class="btn btn-primary btn-block">Добавить</button>
                                    </div>
                                </div>
                            </div>




                        </form>
                    </div>
                </div>

                <!-- Page Heading -->
                <h1 class="h3 mb-2 text-gray-800">Остатки краски</h1>
                <p class="mb-4">В данной таблице таблице (Существующие позиции) отражены остатки краски на складе.
                    Имеется возможность выполнить поиск
                    по таблице при промощи поля "Поиск". Удалить позицию (кнопка Удалить) или изменить значения
                    остатка краски на складе (кнопка Изменить).</p>
                <!-- DataTales Example -->
                <div class="card shadow mb-4">
                    <div class="card-header py-3">
                        <h6 class="m-0 font-weight-bold text-primary">Существующие позиции</h6>
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                <thead>
                                <tr>
                                    <th>RAL</th>
                                    <th>Тип покрытия</th>
                                    <th>Производитель</th>
                                    <th>Количество</th>
                                    <th>Действия</th>
                                    <th></th>
                                    <!--<th>Salary</th>-->
                                </tr>
                                </thead>
                                <tfoot>
                                <tr>
                                    <th>RAL</th>
                                    <th>Тип покрытия</th>
                                    <th>Производитель</th>
                                    <th>Количество</th>
                                    <th>Действия</th>
                                    <th></th>
                                    <!--<th>Salary</th>-->
                                </tr>
                                </tfoot>
                                <tbody>
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
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>

            </div>
            <!-- /.container-fluid -->

        </div>
        <!-- End of Main Content -->

        <!-- Footer -->
        <footer class="sticky-footer bg-white">
            <div class="container my-auto">
                <div class="copyright text-center my-auto">
                    <span>Copyright &copy; Техстком 2022</span>
                </div>
            </div>
        </footer>
        <!-- End of Footer -->

    </div>
    <!-- End of Content Wrapper -->

</div>
<!-- End of Page Wrapper -->

<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
</a>

<!-- Logout Modal-->
<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
            <div class="modal-footer">
                <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                <a class="btn btn-primary" href="login.html">Logout</a>
            </div>
        </div>
    </div>
</div>


<script src="js/update.js"></script>
</@c.page>