<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title><@spring.message "form.login.name"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<div class="container" style="margin-top: 60px">
    <div class="row justify-content-center">
        <div class="col-md-8 col-md-offset-2">
            <h2 class="page-header"><@spring.message "form.login.name"/></h2>

            <#if logout>
                <div class="alert alert-success" role="alert"><@spring.message "logout"/></div>
            </#if>
            <#if error>
                <div class="alert alert-warning" role="alert"><@spring.message "login.error"/></div>
            </#if>
            <form method="post" style="margin-bottom: 30px" name="form" autocomplete="off" >
                <div class="form-group">
                    <label id="inputLoginLabel" for="exampleInputLogin"><@spring.message "label.login"/></label>
                    <input type="text"
                           name="login"
                           class="form-control"
                           id="exampleInputLogin"
                           placeholder="<@spring.message "label.login"/>">
                </div>
                <div class="form-group">
                    <label id="inputPasswordLabel" for="exampleInputPassword"><@spring.message "label.password"/></label>
                    <input type="text"
                           name="password"
                           class="form-control"
                           id="exampleInputPassword"
                           placeholder="<@spring.message "label.password"/>">
                </div>
                <button type="submit" class="btn btn-success" style="margin-top:30px" >
                    <@spring.message "button.login"/>
                </button>
            </form>
            <div class="alert alert-info" role="alert">
                <a href="/registration"><@spring.message "title.registration"/></a>

                <span style="float: right">
                        <a href="?lang=en"><@spring.message "language.en"/></a>
                        |
                        <a href="?lang=ua"><@spring.message "language.ua"/></a>
                </span>
            </div>

        </div>
    </div>
</div>
</body>
</html>