let app = angular.module("report_requests", ['ngRoute']);


app.config(function ($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl: '/fragments/reportRequests/report_requests_list',
            controller: 'ReportRequestsListCtrl'
        })
});

function getListOfRequests($scope, $http) {

    $scope.report_requests = [];

    $http.get("/api/reportRequests")
        .then(
            (data)=>{
                console.log(data);
                $scope.report_requests = data.data;
            },
            (error) => {
                console.log(error.data);
                $scope.message = error.data.message;
            })
}

app.controller("ReportRequestsListCtrl", function ($scope, $http) {
    console.log("IN CONTROLLER");

    $scope.processRequest = (id, answer) => {
        console.log(answer);
        $http({
            method: "POST",
            url: "/api/reportRequests/" + id,
            data: answer,
            headers: { "Content-Type" : "application/json" }
        }).then(
            (data) => {
                console.log(data);
                console.dir($scope.report_requests);

                getListOfRequests($scope, $http)
            },
            (error) => {
                console.log(error);
            }
        )
    };

    getListOfRequests($scope, $http)

});