<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title><@spring.message "title.all_users"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.2/angular.min.js"></script>
    <script src="http://code.jquery.com/jquery-1.8.3.js"></script>
</head>
<body ng-app="all_users" ng-controller="AppCtrl">

<div class="container" style="margin-top: 30px" >
    <div class="row justify-content-center">
        <div class="col-md-12">
            <#include "fragments/header.ftl">
            <div class="panel panel-default">
                <div class="panel-heading"><@spring.message "table.all_users.name"/></div>
                <div class="panel-body">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th><@spring.message "column.id"/></th>
                            <th><@spring.message "label.surname"/></th>
                            <th><@spring.message "label.name"/></th>
                            <th><@spring.message "label.patronymic"/></th>
                            <th><@spring.message "label.login"/></th>
                            <th><@spring.message "label.email"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr ng-repeat="item in users | orderBy:'id'">
                            <td>{{item.id}}</td>
                            <td>{{item.surname}}</td>
                            <td>{{item.name}}</td>
                            <td>{{item.patronymic}}</td>
                            <td>{{item.login}}</td>
                            <td>{{item.email}}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="/js/all_users.js"></script>
</body>
</html>