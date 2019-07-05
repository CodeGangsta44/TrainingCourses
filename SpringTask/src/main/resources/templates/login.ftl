
<!DOCTYPE html>
<html lang="en"
<head>
    <meta charset="UTF-8">
    <title>Registration form's Main</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<div class="col-md-8 col-md-offset-2">
    <h1>Login form</h1>
</div>
<div class="container" style="margin-top: 60px">
    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <h2 class="page-header">Login form</h2>

            <#if logout>
                <div class="alert alert-success" role="alert">You successfully logged out!</div>
            </#if>
            <#if error>
                <div class="alert alert-warning" role="alert">Login or password is incorrect. Please, try again.</div>
            </#if>
            <form method="post" style="margin-bottom: 30px" name="form" autocomplete="off" >
                <div class="form-group">
                    <label id="inputLoginLabel" for="exampleInputLogin">Login</label>
                    <input type="text"
                           name="login"
                           class="form-control"
                           id="exampleInputLogin"
                           placeholder="Login"
                </div>
                <div class="form-group">
                    <label id="inputPasswordLabel" for="exampleInputPassword">Password</label>
                    <input type="text"
                           name="password"
                           class="form-control"
                           id="exampleInputPassword"
                           placeholder="Password"
                </div>
                <button type="submit" class="btn btn-success" style="margin-top:30px" >
                    Log in
                </button>
            </form>
        </div>
    </div>
</div>
</body>
</html>