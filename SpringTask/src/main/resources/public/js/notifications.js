let app = angular.module("notifications", ['ngRoute']);


app.config(function ($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl: '/fragments/notifications/my_notifications',
            controller: 'MyNotificationsCtrl'
        })
        .when('/all', {
            templateUrl: '/fragments/notifications/all_notifications',
            controller: 'AllNotificationsCtrl'
        })
});

function getListOfNotifications($scope, $http, parameter) {

    $scope.notifications = [];

    $http.get("/api/notifications/" + parameter)
        .then(
            (data)=>{
                console.log(data);
                $scope.notifications = data.data;
            },
            (error) => {
                console.log(error.data);
                $scope.message = error.data.message;
            })
}


app.controller("MyNotificationsCtrl", function ($scope, $http) {
    console.log("IN CONTROLLER");
    getListOfNotifications($scope, $http, "me")

    $scope.deleteNotification = (notification) => {
         $http.delete("/api/notifications/" + notification.id)
             .then(
                 (data) => {
                     let index = $scope.notifications.indexOf(notification);
                     $scope.notifications.splice(index, 1);
                 },
                 (error) => {
                     console.log(error);
                 }
             )
    }
});