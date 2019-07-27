<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title><@spring.message "title.registration"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.2/angular.min.js"></script>
    <script src="http://code.jquery.com/jquery-1.8.3.js"></script>

</head>
<body ng-app="home" ng-controller="AppCtrl">
<div class="container" style="margin-top: 60px">
    <div class="row justify-content-center">
        <div class="col-md-8 col-md-offset-2">
            <#include "fragments/header.ftl">
            <div>
                <label id="surnameLabel"><@spring.message "label.surname"/>: {{surname}}</label>
            </div>

            <div>
                <label id="nameLabel"><@spring.message "label.name"/>: {{name}}</label>
            </div>

            <div>
                <label id="patronymicLabel"><@spring.message "label.patronymic"/>: {{patronymic}}</label>
            </div>

            <div>
                <label id="loginLabel"><@spring.message "label.login"/>: {{login}}</label>
            </div>

            <div>
                <label id="emailLabel"><@spring.message "label.email"/>: {{email}}</label>
            </div>

            <div>
                <label id="rolesLabel"><@spring.message "label.roles"/>:</label>
                <ul>
                    <li ng-repeat="role in roles">{{role}}</li>
                </ul>
            </div>

        </div>
    </div>
</div>
<script type="text/javascript" src="/js/home.js"></script>
</body>
</html>