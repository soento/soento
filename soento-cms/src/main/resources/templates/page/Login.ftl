<div class="row">
    <div class="col-sm-10 col-sm-offset-1">
        <div class="login-container">
            <div class="center">
                <h1>
                    <i class="ace-icon fa fa-leaf green"></i>
                    <span class="red"><@spring.messageText code="application.simple.name" text="SOENTO"/></span>
                    <span class="white" id="id-text2"><@spring.messageText code="application.full.name" text="管理系统"/></span>
                </h1>
                <h4 class="blue" id="id-company-text">&copy; <@spring.messageText code="company.name" text="索恩托工作室"/></h4>
            </div>
            <div class="space-6"></div>
            <div class="position-relative">
                <div id="login-box" class="login-box visible widget-box no-border">
                    <div class="widget-body">
                        <div class="widget-main">
                            <h4 class="header blue lighter bigger">
                                <i class="ace-icon fa fa-coffee green"></i>
                                欢迎登录
                            </h4>
                            <div class="space-6"></div>
                            <form>
                                <fieldset>
                                    <label class="block clearfix">
                                        <span class="block input-icon input-icon-right">
                                            <input type="text" id="username" class="form-control" placeholder="用户名"/>
                                            <i class="ace-icon fa fa-user"></i>
                                        </span>
                                    </label>
                                    <label class="block clearfix">
                                        <span class="block input-icon input-icon-right">
                                            <input type="password" id="password" class="form-control" placeholder="密码"/>
                                            <i class="ace-icon fa fa-lock"></i>
                                        </span>
                                    </label>
                                    <div class="space"></div>
                                    <div class="clearfix">
                                        <label class="inline">
                                            <input type="checkbox" class="ace"/>
                                            <span class="lbl"> 记住密码</span>
                                        </label>

                                        <button type="button" id="btnLogin"
                                                class="width-35 pull-right btn btn-sm btn-primary">
                                            <i class="ace-icon fa fa-key"></i>
                                            <span class="bigger-110">登录</span>
                                        </button>
                                    </div>
                                    <div class="space-4"></div>
                                </fieldset>
                            </form>
                        </div><!-- /.widget-main -->
                    </div><!-- /.widget-body -->
                </div><!-- /.login-box -->
            </div><!-- /.position-relative -->
        </div>
    </div><!-- /.col -->
</div><!-- /.row -->
