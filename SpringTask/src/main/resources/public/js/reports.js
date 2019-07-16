let app = angular.module("reports", ['ngRoute']);


function showData(data, $scope) {
    Object.assign($scope, data.data);
}


app.config(function ($routeProvider) {
    $routeProvider
        .when('/add', {
            templateUrl: 'report_form.ftl',
            controller: 'CreateReportCtrl'
        })
        .when('/:id', {
            templateUrl: 'report_info.ftl',
            controller: 'ReportCtrl'
        })
});

// app.controller("ConferencesListCtrl", function ($scope, $http) {
//     $scope.conferences = [];
//
//     $http.get("/api/conferences")
//         .then(
//             (data)=>{
//                 console.log('SUCCESS');
//                 console.log(data);
//                 $scope.conferences = data.data;
//             },
//             (error) => {
//                 console.log('FAIL');
//                 console.log(data);
//                 console.log(error.data);
//                 resultMessageEl.style.color = 'red';
//                 $scope.message = error.data.message;
//             })
//
//
// });
//
app.controller("ReportCtrl", function ($scope, $http, $routeParams) {

    $http.get('api/reports/' + $routeParams.id)
        .then (
            (data) => showData(data, $scope),
            (error) => {
                console.log(error);
            }
        );
});
//
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