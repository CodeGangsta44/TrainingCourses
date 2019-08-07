let app = angular.module("conferences", ['ngRoute']);


function showData(data, $scope) {
    console.log(data);
    $scope.action = '/api/conferences/' + data.data.id + '/changeRegistration';
    Object.assign($scope, data.data);

    $scope.registrationAction = $scope.registered ? 'Cancel registration' : 'Register';
    console.log($scope.registrationAction);
}

function getConferencesList($scope, $http) {
    $http.get("/api/conferences" + $scope.path)
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
}


app.config(function ($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl: '/fragments/conference/conferences_list',
            controller: 'ConferencesListCtrl'
        })
        .when('/requests', {
            templateUrl: '/fragments/conference/conference_requests_list',
            controller: 'ConferenceRequestsListCtrl'
        })
        .when('/finished', {
            templateUrl: '/fragments/conference/finished_conferences_list',
            controller: 'FinishedConferencesListCtrl'
        })
        .when('/notFinished', {
            templateUrl: '/fragments/conference/not_finished_conferences_list',
            controller: 'NotFinishedConferencesListCtrl'
        })
        .when('/me', {
            templateUrl: '/fragments/conference/conferences_list',
            controller: 'MyConferencesListCtrl'
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

function answerRequest($scope, $http, id, answer) {
    console.log(answer);
    $http({
        method: "POST",
        url: "/api/conferences/" + id + "/processRequest",
        data: answer,
        headers: { "Content-Type" : "application/json" }
    }).then(
        (data) => {
            console.log(data);
            getConferencesList($scope, $http)
        },
        (error) => {
            console.log(error);
        }
    )
}

app.controller("ConferencesListCtrl", function ($scope, $http) {
        $scope.conferences = [];
        $scope.path = '/';
        getConferencesList($scope, $http);
});

app.controller("ConferenceRequestsListCtrl", function ($scope, $http) {
    $scope.conferences = [];
    $scope.path = '/requests';
    getConferencesList($scope, $http);

    $scope.processRequest = (id, answer) => answerRequest.call(this, $scope, $http, id, answer);
});

app.controller("NotFinishedConferencesListCtrl", function ($scope, $http) {
    $scope.conferences = [];
    $scope.path = '/notFinished';
    getConferencesList($scope, $http);

    $scope.prepareForConferenceFinishing = (id) => {
        $scope.conferenceIdToFinish = id;
    };

    $scope.cancelConferenceFinishing = () => {
        delete $scope.conferenceIdToFinish;
    };

    $scope.finishConference = () => {
        console.log($scope.numberOfVisitedGuests);
        $http({
            method: "PUT",
            url: "/api/conferences/" + $scope.conferenceIdToFinish + "/finish",
            data: $scope.numberOfVisitedGuests,
            headers: {"Content-Type" : "application/json"}
        }).then(
            (data) => {
                console.log(data);
                getConferencesList($scope, $http);
            },
            (error) => console.log(error)
        )
    }
});

app.controller("FinishedConferencesListCtrl", function ($scope, $http) {
    $scope.conferences = [];
    $scope.path = '/finished';
    getConferencesList($scope, $http);
});

app.controller("MyConferencesListCtrl", function ($scope, $http) {
    $scope.conferences = [];
    $scope.path = '/me';
    getConferencesList($scope, $http);
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
            url: "/api/reportRequests/request/" + $routeParams.id,
            data: JSON.stringify(form),
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

app.controller("ReportCtrl", function ($scope, $http, $routeParams) {

    $http.get('api/reports/' + $routeParams.id)
        .then (
            (data) => showData(data, $scope),
            (error) => {
                console.log(error);
            }
        );
});