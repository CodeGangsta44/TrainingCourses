let app = angular.module("users", ['ngRoute']);


app.config(function ($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl: '/fragments/users/user_list',
            controller: 'UserListCtrl'
        })
});

app.controller("UserListCtrl", function ($scope, $http) {
    $scope.users = [];
    console.log("IN CONTROLLER");
    $http.get("/api/users")
        .then(
            (data)=>{
                console.log(data);
                $scope.users = data.data;
            },
            (error) => {
                console.log(error.data);
                resultMessageEl.style.color = 'red';
                $scope.message = error.data.message;
            })

});