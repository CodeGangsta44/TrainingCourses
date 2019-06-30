angular.module("all_users", [])
    .controller("AppCtrl", function ($scope, $http) {
        $scope.users = [];

        let resultMessageEl = document.getElementById('resultMessage');

        $http.get("/api/all_users")
            .then(
                (data)=>{
                $scope.users = data.data.users;
            },
                (error) => {
                console.log(error.data);
                resultMessageEl.style.color = 'red';
                $scope.message = error.data.message;
            })


    });