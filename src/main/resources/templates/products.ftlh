<#import "parts/common.ftlh" as c>
<#import "parts/sidebar.ftlh" as sb>
<#import "parts/topbar.ftlh" as tb>
<#import "parts/logoutModal.ftlh" as lm>
<#import "parts/limitation.ftlh" as lim>
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

                <!-- Page Heading -->
                <h1 class="h3 mb-2 text-gray-800">В прозводстве</h1>

                <!-- DataTales Example -->
                <div class="row">



                    <#list products as product>

                    <div class="col-6 col-lg-3">
                        <div class="card shadow mb-4">
                            <!-- Card Header - Dropdown -->
                            <div class="card-header border-bottom-warning py-3 d-flex flex-row align-items-center justify-content-between">
                                <h6 class="m-0 font-weight-bold text-primary"><a class="stretched-link" href="/products/${product.id}">${product.name}</a></h6>
                                <@lim.limitation "RC_EMPLOYEE">
                                <div style="z-index:2;" class="dropdown no-arrow">
                                    <a class="dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown"  aria-haspopup="true" aria-expanded="false">
                                        <i class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>
                                    </a>

                                    <div class="dropdown-menu dropdown-menu-right shadow animated--fade-in" aria-labelledby="dropdownMenuLink">
                                        <div class="dropdown-header">Выберите действие:</div>
                                        <a class="dropdown-item" href="#">Изменить</a>

                                        <div class="dropdown-divider"></div>
                                        <a class="dropdown-item" href="products/delete?id=${product.id}">Удалить</a>
                                    </div>
                                </div>
                                </@lim.limitation>
                            </div>

                            <!-- Card Body -->
                            <div  class="card-body p-0">

                                <#if product.filename??>
                                    <img class="img_product"  src="/image/${product.filename}">
                                </#if>

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




<button type="button" data-toggle="modal" data-target="#addProduct" style="bottom:20px; right: 20px; position:fixed; height:45px; width:45px; font-size:23px; z-index:5;" class="btn btn-primary btn-circle">
    <i class="fas fa-plus"></i>
</button>

<!-- Logout Modal-->
<@lm.logoutModal />


<!-- addproduct Modal-->
<@lim.limitation "RC_EMPLOYEE">
<div class="modal fade" id="addProduct" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog  modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Добавить новую модель?</h5>
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
                                <input type="text" class="form-control" name="name"  placeholder="Название">
                            </div>
                        </div>
                        <div class="mb-3">
                            <div class="form-group">
                                <textarea type="text" class="form-control" name="description"  placeholder="Описание">Описание</textarea>
                            </div>
                        </div>
                        <div class="mb-3">
                            <div class="input-group mb-3">
                                <div class="custom-file">
                                    <input type="file" name="file" class="custom-file-input" id="inputGroupFile02" multiple>
                                    <label class="custom-file-label" for="inputGroupFile02" aria-describedby="inputGroupFileAddon02">Выберите фото</label>
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
</div>\
</@lim.limitation>
<script>
    $(document).ready(function () {
    bsCustomFileInput.init()
    })
</script>
</@c.page>