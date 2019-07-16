angular.module("all_users", [])
    .controller("AppCtrl", function ($scope, $http) {
        $scope.users = [];

        let resultMessageEl = document.getElementById('resultMessage');

        console.log("HERE");
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