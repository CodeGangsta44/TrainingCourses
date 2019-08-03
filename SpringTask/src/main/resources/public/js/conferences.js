let app = angular.module("conferences", ['ngRoute']);


function showData(data, $scope) {
    console.log(data);
    $scope.action = '/api/conferences/' + data.data.id + '/changeRegistration';
    Object.assign($scope, data.data);

    $scope.registrationAction = $scope.registered ? 'Cancel registration' : 'Register';
    console.log($scope.registrationAction);
}


app.config(function ($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl: '/fragments/conference/conferences_list',
            controller: 'ConferencesListCtrl'
        })
        .when('/add', {
            templateUrl: '/fragments/conference/conference_form',
            controller: 'CreateConferenceCtrl'
        })
        .when('/:id', {
            templateUrl: '/fragments/conference/conference_info',
            controller: 'ConferenceCtrl'
        })
        .when('/:id/addReport', {
            templateUrl: '/fragments/report/report_form',
            controller: 'CreateReportCtrl'
        })
});

app.controller("ConferencesListCtrl", function ($scope, $http) {
        $scope.conferences = [];

        $http.get("/api/conferences")
            .then(
                (data)=>{
                    console.log('SUCCESS');
                    console.log(data);
                    $scope.conferences = data.data;
                },
                (error) => {
                    console.log('FAIL');
                    console.log(data);
                    console.log(error.data);
                    resultMessageEl.style.color = 'red';
                    $scope.message = error.data.message;
                })


});

app.controller("ConferenceCtrl", function ($scope, $http, $routeParams) {
    $scope.reports = [];
    $scope.registeredGuests = [];

    let regButton = document.getElementById('registerToConference');

    $http.get('api/conferences/' + $routeParams.id)
        .then (
            (data) => showData(data, $scope),
            (error) => {
                console.log(error);
            }
        );

    regButton.onclick = () => {
        $http.get($scope.action)
            .then (
                (data) => showData(data, $scope),
                (error) => console.log(error)
            );

    }
});

app.controller("CreateConferenceCtrl", function ($scope, $http) {
    $scope.form = {};
    
    $scope.sendForm = function(form){
        // form.eventDateTime = new Date(form.eventDateTime);
        console.log(form);
        $http({
            method: "POST",
            url: "/api/conferences",
            data: $.param(form),
            headers: { "Content-Type" : "application/x-www-form-urlencoded" }
        }).then(
            (data) => {
                console.log(data);
            },
            (error) => {
                console.log(error);
            }
        );
    }
});

app.controller("CreateReportCtrl", function ($scope, $http, $routeParams) {
    $scope.form = {};

    $scope.sendForm = function(form){
        console.log(form);
        $http({
            method: "POST",
            url: "/api/conferences/" + $routeParams.id + "/addReport",
            data: $.param(form),
            headers: { "Content-Type" : "application/x-www-form-urlencoded" }
        }).then(
            (data) => {
                console.log(data);
            },
            (error) => {
                console.log(error);
            }
        );
    }
});

app.controller("ReportCtrl", function ($scope, $http, $routeParams) {

    $http.get('api/reports/' + $routeParams.id)
        .then (
            (data) => showData(data, $scope),
            (error) => {
                console.log(error);
            }
        );
});