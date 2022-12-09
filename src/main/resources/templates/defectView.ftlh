<#import "/parts/common.ftlh" as c>
<#import "/parts/sidebar.ftlh" as sb>
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
                <nav aria-label="breadcrumb">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="/products">В производстве</a></li>
                        <li class="breadcrumb-item"><a href="/products/${defect.product.id}">${defect.product.name}</a></li>
                        <li class="breadcrumb-item active" aria-current="page">${defect.id}</li>
                    </ol>
                </nav>
                <div class="row">
                    <!-- start col defect -->
                    <div class="col-lg-6">
                        <div class="card mb-4">
                            <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                                <h4 class="m-0 font-weight-bold text-gray-700">Дефект ${defect.id}</h4>
                                <@lim.limitation "DTK">
                                <div class="dropdown no-arrow">
                                    <a class="dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        <i class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>
                                    </a>
                                    <div class="dropdown-menu dropdown-menu-right shadow animated--fade-in" aria-labelledby="dropdownMenuLink">
                                        <div class="dropdown-header">Выберите дейстивие:</div>
                                        <a class="dropdown-item edit_defect_btn" >Редактировать</a>
                                    </div>
                                </div>
                                </@lim.limitation>
                            </div>
                            <div class="card-body">
                                <div class="defect-head">
                                    <p>Описание: ${defect.description}</p>
                                </div>
                                <div class="form-edit-defect-head">
                                    <form method="post" action="${defect.id}/updateDefect" enctype="multipart/form-data">
                                        <div class="form-row d-block d-md-flex">

                                            <div class="col-lg-12">
                                                <div class="form-group">
                                                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                                                    <textarea type="text" class="form-control" name="desc" placeholder="Описание">${defect.description}</textarea>
                                                </div>
                                            </div>

                                            <div class="col-lg-4">
                                                <div class="form-group">
                                                    <button type="submit" class="btn btn-success">Сохранить</button>
                                                    <div class="cancel_edit_defect_btn btn btn-danger">
                                                        <span class="text">Отменить</span>
                                                    </div>
                                                </div>

                                            </div>
                                        </div>
                                    </form>
                                </div>
                                <div class="row">
                                    <#list defect.defectImages as di>
                                    <#if di??>
                                    <div  class="col-lg-6 p-2">
                                        <@lim.limitation "DTK">
                                        <a href="${defect.id}/deleteDefectImg/${di}" style="bottom: 15px; position: absolute; right: 15px;" class="btn btn-danger">
                                            <i class="fas fa-trash"></i>
                                        </a>
                                        </@lim.limitation>
                                        <img  class="img_def"  src="/image/${di}">
                                    </div>
                                    </#if>
                                    </#list>
                                </div>

                                <@lim.limitation "DTK">
                                <form method="post" enctype="multipart/form-data">
                                        <div class="row d-block d-md-flex">

                                            <div class="col-lg-6 p-2">
                                                <div class="input-group mb-0">
                                                    <div class="custom-file">
                                                        <input type="file" name="file" class="custom-file-input" id="inputGroupFile02" multiple>
                                                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                                                        <label class="custom-file-label" for="inputGroupFile02" aria-describedby="inputGroupFileAddon02">Выберите фото</label>
                                                    </div>

                                                </div>
                                            </div>
                                            <div class="col-lg-2 p-2">
                                                <div class="form-group">
                                                    <button type="submit" class="btn btn-primary btn-block">Добавить</button>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </@lim.limitation>

                            </div>
                        </div>
                    </div>
                    <!-- /.col defect -->

                    <!-- start col defectCorrection -->
                    <div class="col-lg-6">
                        <#if defect.defectCorrection??>
                            <div class="card mb-4">
                                <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                                    <h4 class="m-0 font-weight-bold text-gray-700">Исправление дефекта ${defect.id}</h4>
                                    <@lim.limitation "RC_EMPLOYEE">
                                    <div class="dropdown no-arrow">
                                        <a class="dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                            <i class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>
                                        </a>
                                        <div class="dropdown-menu dropdown-menu-right shadow animated--fade-in" aria-labelledby="dropdownMenuLink">
                                            <div class="dropdown-header">Выберите дейстивие:</div>
                                            <a class="dropdown-item edit_correction_btn" >Редактировать</a>
                                        </div>
                                    </div>
                                    </@lim.limitation>
                                </div>
                                <div class="card-body">
                                    <div class="correction-head">
                                        <p>Комментарий: ${defect.defectCorrection.message}</p>
                                        <p>Виновник: ${defect.defectCorrection.culprit}</p>
                                    </div>

                                    <div class="form-edit-correction-head">
                                        <form method="post" action="${defect.id}/updateCorrection" enctype="multipart/form-data">
                                            <div class="form-row d-block d-md-flex">
                                                <div class="col-lg-12">
                                                    <div class="form-group">
                                                        <input type="text" class="form-control" name="message" value="${defect.defectCorrection.message}"  placeholder="Сообщение">
                                                    </div>
                                                </div>
                                                <div class="col-lg-12">
                                                    <div class="form-group">
                                                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                                                        <input type="text" class="form-control" name="culprit" value="${defect.defectCorrection.culprit}"  placeholder="Виновник брака">
                                                    </div>
                                                </div>
                                                <div class="col-lg-4">
                                                    <div class="form-group">
                                                        <button type="submit" class="btn btn-success">Сохранить</button>
                                                        <div class="cancel_edit_correction_btn btn btn-danger">
                                                            <span class="text">Отменить</span>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </form>
                                    </div>

                                    <div class="row">
                                        <#list defect.defectCorrection.imageCorrections as img>
                                        <#if img??>
                                        <div class="col-lg-6 p-2">
                                            <@lim.limitation "RC_EMPLOYEE">
                                            <a href="${defect.id}/deleteImgCorrection/${img}" style="bottom: 15px; position: absolute; right: 15px;" class="btn btn-danger">
                                                <i class="fas fa-trash"></i>
                                            </a>
                                            </@lim.limitation>
                                            <img  class="img_def"  src="/image/${img}">
                                        </div>
                                        </#if>
                                        </#list>
                                    </div>

                                    <@lim.limitation "RC_EMPLOYEE">
                                    <form method="post" action="${defect.id}/addCorrectionImage" enctype="multipart/form-data">
                                        <div class="row d-block d-md-flex">

                                            <div class="col-lg-6 p-2">
                                                <div class="input-group mb-0">
                                                    <div class="custom-file">
                                                        <input type="file" name="file_1" class="custom-file-input" id="inputGroupFile04" multiple>
                                                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                                                        <label class="custom-file-label" for="inputGroupFile04" aria-describedby="inputGroupFileAddon02">Выберите фото</label>
                                                    </div>

                                                </div>
                                            </div>
                                            <div class="col-lg-2 p-2">
                                                <div class="form-group">
                                                    <button type="submit" class="btn btn-primary btn-block">Добавить</button>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                    </@lim.limitation>

                                </div>
                            </div>
                        <#else>
                            <div class="card mb-4">
                                <div class="card-header py-3">
                                    <h4 class="m-0 font-weight-bold text-gray-700">Еще не было исправлений</h4>
                                </div>
                                <div class="card-body">
                                    <form method="post" action="${defect.id}/addDefectCorrection" enctype="multipart/form-data">
                                        <div class="row d-block d-md-flex">
                                            <div class="col-lg-8">
                                                <div class="form-group">
                                                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                                                    <input type="text" class="form-control" name="culprit"  placeholder="Виновник">

                                                </div>
                                            </div>

                                            <div class="col-lg-8">
                                                <div class="form-group">
                                                    <input type="text" class="form-control" name="message"  placeholder="Сообщение">

                                                </div>
                                            </div>

                                            <div class="col-lg-6">
                                                <div class="input-group mb-3">
                                                    <div class="custom-file">
                                                        <input type="file" name="file" class="custom-file-input" id="inputGroupFile03" multiple>

                                                        <label class="custom-file-label" for="inputGroupFile03" aria-describedby="inputGroupFileAddon02">Выберите фото</label>
                                                    </div>

                                                </div>
                                            </div>
                                            <div class="col-lg-2">
                                                <div class="form-group">
                                                    <button type="submit" class="btn btn-primary btn-block">Добавить</button>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </#if>
                    </div>
                    <!-- /.col defectCorrection -->



                </div>
            <!-- /.row -->

            </div>
            <!-- /.container-fluid -->

        </div>
        <!-- End of Main Content -->
<#if defect.defectCorrection??>
<div class="container">

    <div class="row justify-content-md-center">

        <@lim.limitation "RC_EMPLOYEE">
        <#if defect.defectCorrection.status == "REJECTED">
        <div class="col-md-auto col-sm-12">
            <a href="${defect.id}/changeDefectCorrectionStatus/1">
                <div class="col-12 btn btn-success">
                    <span class="text">Отправить на проверку</span>
                </div>
            </a>
            <div class="my-2"></div>
        </div>
        </#if>
        </@lim.limitation>



        <@lim.limitation "DTK">
        <#if defect.defectCorrection.status == "PENDING">
        <div class="col-md-auto col-sm-12">
            <a href="${defect.id}/changeDefectCorrectionStatus/0">
                <div  class="col-12 btn btn-success">
                    <span class="text">Принять</span>
                </div>

            <div class="my-2"></div>
            <a href="${defect.id}/changeDefectCorrectionStatus/2">
                <div class="col-12 btn btn-danger">
                    <span class="text">Отклонить</span>
                </div>
            </a>
        </div>
        </#if>
        </@lim.limitation>
    </div>
</div>
</#if>
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
<@lm.logoutModal />
<script>
    $(document).ready(function () {
    bsCustomFileInput.init();

    $('.edit_defect_btn').on('click',function(){
        $('.form-edit-defect-head').css('display','block');
        $('.defect-head').css('display','none');
    });
    $('.cancel_edit_defect_btn').on('click',function(){
        $('.form-edit-defect-head').css('display','none');
        $('.defect-head').css('display','block');
    });

    $('.edit_correction_btn').on('click',function(){
        $('.form-edit-correction-head').css('display','block');
        $('.correction-head').css('display','none');
    });
    $('.cancel_edit_correction_btn').on('click',function(){
        $('.form-edit-correction-head').css('display','none');
        $('.correction-head').css('display','block');
    });


    });


 </script>
</@c.page>