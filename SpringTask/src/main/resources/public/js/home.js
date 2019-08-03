let app = angular.module("home", []);

app.directive('file', function () {
    return {
        scope: {
            file: '='
        },
        link: function (scope, el, attrs) {
            el.bind('change', function (event) {
                var file = event.target.files[0];
                scope.file = file ? file : undefined;
                scope.$apply();
            });
        }
    };
});

function getUserInfo($scope, $http) {
    $http.get("/api/users/me")
        .then(
            (data)=>{
                console.log(data);
                Object.assign($scope.user, data.data);
            },
            (error) => {
                console.log(error.data);
            });
}

app.controller("AppCtrl", function ($scope, $http) {
    $scope.user = {};
    $scope.editForm = {};

    getUserInfo($scope, $http);

    $scope.changeAvatar = () => {

        let formData = new FormData();

        formData.append('file', $scope.file);

        console.dir($scope.file);

        $http({
            method: "POST",
            url: "/api/users/" + $scope.user.id + "/changeAvatar",
            data: formData,
            headers: { "Content-Type" : undefined },
        }).then(
            (data) => {
                console.log(data);
                getUserInfo($scope, $http);
            },
            (error) => {
                console.log(error);
            }
        )
    }

    $scope.editProfile = () => {
        let resultMessageEl = document.getElementById('resultMessage');
        $http({
            method: "PUT",
            url: "/api/users",
            data: JSON.stringify($scope.user),
            headers: {"Content-Type" : "application/json"}
        }).then(
            (data) => {
                console.log(data);
                getUserInfo($scope, $http);

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
        )
    }
});
