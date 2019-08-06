let app = angular.module("reports", ['ngRoute']);


function showData(data, $scope) {
    Object.assign($scope, data.data);
}

function showListOfReports(data, $scope) {
    $scope.reports = data.data;
}

function getListOfReports($http, $scope) {
    $http.get('api/reports/')
        .then (
            (data) => {
                console.log(data);
                showListOfReports(data, $scope)
            },
            (error) => {
                console.log(error);
            }
        );
}


app.config(function ($routeProvider) {

    $routeProvider
        .when('/', {
            templateUrl: '/fragments/report/report_list',
            controller: 'ReportListCtrl'
        })
        .when('/add', {
            templateUrl: '/fragments/report/report_form',
            controller: 'CreateReportCtrl'
        })
        .when('/:id', {
            templateUrl: '/fragments/report/report_info',
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

app.controller("ReportListCtrl", function ($scope, $http) {
    $scope.reports = [];
    $scope.editForm = {};

    getListOfReports($http, $scope);

    $scope.editReport = (report) => {
        Object.assign($scope.editForm, report);
    };

    $scope.saveChanges = () => {
        let resultMessageEl = document.getElementById('resultMessage');
        $http({
            method: "PUT",
            url: "/api/reports",
            data: JSON.stringify($scope.editForm),
            headers: {"Content-Type" : "application/json"}
        }).then(
            (data) => {
                console.log(data);

                getListOfReports($http, $scope);
                resultMessageEl.className = 'alert alert-success';
                resultMessageEl.innerText = data.data;
                resultMessageEl.style.visibility='visible';
            },
            (error) => {
                console.log(error);
                resultMessageEl.className = 'alert alert-warning';
                resultMessageEl.innerText = error.data.localizedMessage;
                resultMessageEl.style.visibility='visible';
            }
        );
    };

    $scope.prepareToDeleteReport = (id) => {
        console.log(id);
        $scope.reportIdToDelete = id;
    };

    $scope.cancelReportDeleting = () => {
        delete $scope.reportIdToDelete;
    };

    $scope.deleteReport = () => {
        console.log($scope.reportIdToDelete);
        $http.delete("api/reports/" + $scope.reportIdToDelete)
            .then(
                (data) => {
                    console.log(data);
                    getListOfReports($http, $scope);
                },
                (error) => {
                    console.log(error);
                }
            );
    };
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
//
app.controller("CreateReportCtrl", function ($scope, $http, $routeParams) {
    $scope.form = {};

    $scope.sendForm = function(form){
        console.log(form);
        $http({
            method: "POST",
            url: "/api/conferences/" + $routeParams.id + "/addReport",
            data: JSON.stringify($scope.form),
            headers: { "Content-Type" : "application/json" }
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
