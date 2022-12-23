<#import "/parts/common.ftlh" as c>
<#import "/parts/sidebar.ftlh" as sb>
<#import "parts/topbar.ftlh" as tb>
<#import "parts/logoutModal.ftlh" as lm>
<#import "parts/limitation.ftlh" as lim>
<#import "functions/functions.ftlh" as func>
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
                <nav aria-label="breadcrumb">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="/products">В производстве</a></li>
                        <li class="breadcrumb-item active" aria-current="page">${product.name}</li>
                    </ol>
                </nav>

                <!-- Page Heading -->
                <h1 class="h3 mb-2 text-gray-800">${product.name}</h1>
                <p class="mb-4">${product.description}</p>
                <!-- DataTales Example -->
                <div class="mt-4 text-center small mb-4">
                    <span class="mr-2">
                        <i class="fas fa-circle text-success mr-2"></i> Исправление принято
                    </span>
                    <span class="mr-2">
                        <i class="fas fa-circle text-danger mr-2"></i> Исправление отклонено
                    </span>
                    <span class="mr-2">
                        <i class="fas fa-circle text-warning mr-2"></i> Ожидает проверки
                    </span>
                    <span class="mr-2">
                        <i class="fas fa-circle text-secondary mr-2"></i> Еще не было исправлений
                    </span>
                </div>
                <div class="row">



                    <#list defects as def>

                    <div  class="col-lg-3">

                        <div class="card shadow mb-4 ">
                            <!-- Card Header - Dropdown -->

                            <div class="card-header <#if def.defectCorrection??>  <@func.border_color "${def.defectCorrection.status}" /> <#else> border-bottom-secondary </#if> py-3 d-flex flex-row align-items-center justify-content-between">


                                <h6 class="m-0 font-weight-bold text-primary"><a class="stretched-link" href="/products/${product.id}/${def.id}">#${def.id}</a></h6>

                                <@lim.limitation "DTK">
                                    <div style="z-index:2;" class="dropdown no-arrow">
                                    <a class="dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown"  aria-haspopup="true" aria-expanded="false">
                                        <i class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>
                                    </a>

                                    <div class="dropdown-menu dropdown-menu-right shadow animated--fade-in" aria-labelledby="dropdownMenuLink">
                                        <div class="dropdown-header">Выберите действие:</div>
                                        <a class="dropdown-item" href="#">Изменить</a>

                                        <div class="dropdown-divider"></div>
                                        <a class="dropdown-item" href="${product.id}/delete?id=${def.id}">Удалить</a>
                                    </div>
                                </div>
                                </@lim.limitation>

                            </div>






                            <!-- Card Body -->
                            <div class="card-body">
                                <p class="text-truncate">${def.description}</p>
                                    <div class="row" style="flex-wrap:nowrap; overflow:hidden;">


                                        <#list def.defectImages as img>
                                        <#if img??>
                                        <div class="col-6">
                                            <img  class="img_def_prev"  src="/image/${def.product.name}__${def.product.UUID}/${def.id}/${img}">
                                        </div>
                                        </#if>
                                        </#list>


                                    </div>

                            </div>
                    </div>

                </div>

            </#list>



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
<!--<a class="scroll-to-top rounded" href="#page-top">-->
<!--    <i class="fas fa-angle-up"></i>-->
<!--</a>-->
<button type="button" data-toggle="modal" data-target="#addDefectModal" style="bottom:20px; right: 20px; position:fixed; height:45px; width:45px; font-size:23px; z-index:5;" class="btn btn-primary btn-circle">
    <i class="fas fa-plus"></i>
</button>
<!-- Logout Modal-->
<@lm.logoutModal />

<!-- addDefect Modal-->
<@lim.limitation "DTK">
<div class="modal fade" id="addDefectModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog  modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Добавить новый дефект?</h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <form method="post" enctype="multipart/form-data">
            <div class="modal-body">

                    <div class="form-row d-block">
                        <div class="mb-3">
                            <div class="form-group">
                                <input type="hidden" name="_csrf" value="${_csrf.token}">
                                <label>Введите описание</label>
                                <textarea type="text" class="form-control" name="desc"  placeholder="Описание"></textarea>
                            </div>
                        </div>
                        <div class="mb-3">
                            <div class="input-group mb-3">
                                <div class="custom-file">
                                    <input type="file" name="file" class="custom-file-input" id="inputGroupFile03" multiple>

                                    <label class="custom-file-label" for="inputGroupFile03" aria-describedby="inputGroupFileAddon03">Выберите фото</label>
                                </div>

                            </div>
                        </div>

                    </div>

            </div>
            <div class="modal-footer">
                <button class="btn btn-secondary" type="button" data-dismiss="modal">Отмена</button>
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <input class="btn btn-primary" type="submit" value="Добавить"/>
            </div>
            </form>
        </div>
    </div>
</div>
</@lim.limitation>

<script>
    $(document).ready(function () {
    bsCustomFileInput.init()
    })
</script>
</@c.page>