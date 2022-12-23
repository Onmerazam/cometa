<#import "/parts/common.ftlh" as c>
<#import "/parts/sidebar.ftlh" as sb>
<#import "/parts/topbar.ftlh" as tb>
<#import "/parts/logoutModal.ftlh" as lm>
<#import "/parts/limitation.ftlh" as lim>
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
                <h1 class="h3 mb-2 text-gray-800">Станочный парк</h1>

                <!-- DataTales Example -->
                <div class="row ">






                    <#list machines as machine>

                    <div class="col-lg-2 my-2">
                        <div class="card">

                            <img src="/image/${machine.name}__${machine.id}/${machine.imageAddress}" class="card-img-top img_machine" alt="...">
                            <div class="card-body">


                                <h5 class="card-title">${machine.name}</h5>

                                <small class="text-mute">${machine.category}</small>
                            </div>
                            <div class="card-footer text-success">
                                <small class="text-success">Текущий статус: Работает</small>
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
<!--<@lim.limitation "RC_EMPLOYEE">-->
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
                                <select name="category" class="form-control">
                                    <option>Лазерная резка</option>
                                    <option>Токарная обработка</option>
                                    <option>Гибка металла</option>
                                </select>
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
<!--</@lim.limitation>-->
<script>
    $(document).ready(function () {
    bsCustomFileInput.init()
    })
</script>
</@c.page>