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
<body ng-app="registration_form" ng-controller="AppCtrl">
<div class="container" style="margin-top: 60px">
    <div class="row justify-content-center">
        <div class="col-md-8 col-md-offset-2">
            <#include "fragments/header.ftl">

            <div class="alert alert-success" role="alert" id="resultMessage" style="visibility: hidden"></div>
            <h2 class="page-header"><@spring.message "form.registration.name"/></h2>
            <form style="margin-bottom: 30px" name="form" autocomplete="off" novalidate ng-submit="sendForm(auth)">
                <div class="form-group">
                    <label id="inputSurnameLabel" for="exampleInputSurname"><@spring.message "label.surname"/></label>
                    <input type="text"
                           class="form-control"
                           id="exampleInputSurname"
                           placeholder="<@spring.message "label.surname"/>"
                           required
                           ng-model="auth.surname">
                    <span id="surnameMessages" class="validationMessages" hidden>TEST</span>
                </div>
                <div class="form-group">
                    <label id="inputNameLabel" for="exampleInputName"><@spring.message "label.name"/></label>
                    <input type="text"
                           class="form-control"
                           id="exampleInputName"
                           placeholder="<@spring.message "label.name"/>"
                           required
                           ng-model="auth.name">
                    <span id="nameMessages" class="validationMessages" hidden></span>
                </div>
                <div class="form-group">
                    <label id="inputPatronymicLabel" for="exampleInputPatronymic"><@spring.message "label.patronymic"/></label>
                    <input type="text"
                           class="form-control"
                           id="exampleInputPatronymic"
                           placeholder="<@spring.message "label.patronymic"/>"
                           required
                           ng-model="auth.patronymic">
                    <span id="patronymicMessages" class="validationMessages" hidden></span>
                </div>
                <div class="form-group">
                    <label id="inputLoginLabel" for="exampleInputLogin"><@spring.message "label.login"/></label>
                    <input type="text"
                           class="form-control"
                           id="exampleInputLogin"
                           placeholder="<@spring.message "label.login"/>"
                           required
                           ng-model="auth.login">
                    <span id="loginMessages" class="validationMessages" hidden></span>
                </div>
                <div class="form-group">
                    <label id="inputEmailLabel" for="exampleInputEmail"><@spring.message "label.email"/></label>
                    <input type="text"
                           class="form-control"
                           id="exampleInputEmail"
                           placeholder="<@spring.message "label.email"/>"
                           required
                           ng-model="auth.email">
                    <span id="emailMessages" class="validationMessages" hidden></span>
                </div>
                <div class="form-group">
                    <label id="inputPasswordLabel" for="exampleInputPassword"><@spring.message "label.password"/></label>
                    <input type="text"
                           class="form-control"
                           id="exampleInputPassword"
                           placeholder="<@spring.message "label.password"/>"
                           required
                           ng-model="auth.password">
                    <span id="passwordMessages" class="validationMessages" hidden></span>
                </div>
                <div class="form-group">
                    <label id="inputIsSpeakerLabel" for="exampleInputIsSpeaker"><@spring.message "label.speaker"/></label>
                    <input type="checkbox"
                           class="form-control"
                           id="exampleInputIsSpeaker"
                           placeholder="<@spring.message "label.speaker"/>"
                           ng-model="auth.isSpeaker"
                           ng-true-value="true" ng-false-value="false">
                </div>
                <button type="submit" class="btn btn-success" style="margin-top:30px" ng-disabled="form.$invalid">
                    <@spring.message "button.registration"/>
                </button>
            </form>
            <#include "fragments/footer.ftl">
        </div>
    </div>
</div>
<script type="text/javascript" src="/js/reg_form.js"></script>
</body>
</html>