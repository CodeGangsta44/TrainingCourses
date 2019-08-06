let app = angular.module("users", ['ngRoute']);


app.config(function ($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl: '/fragments/users/user_list',
            controller: 'UserListCtrl'
        })
        .when('/me', {
            templateUrl: '/fragments/users/home',
            controller: 'UserListCtrl'
        })
});

function getListOfUsers($scope, $http) {
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
            });
}

app.controller("UserListCtrl", function ($scope, $http) {
    $scope.users = [];
    $scope.editForm = {};
    $scope.roles = ['ADMIN', 'MODER', 'SPEAKER', 'USER'];

    getListOfUsers($scope, $http);

    $scope.prepareToDeleteUser = (id) => {
        console.log(id);
        $scope.userIdToDelete = id;
    };

    $scope.cancelUserDeleting = () => {
        delete $scope.userIdToDelete;
    };

    $scope.deleteUser = () => {
        console.log($scope.userIdToDelete);
        $http.delete("api/users/" + $scope.userIdToDelete)
            .then(
                (data) => {
                    console.log(data);
                    getListOfUsers($scope, $http);
                },
                (error) => {
                    console.log(error);
                }
            );
    };

    $scope.editUser = (user) => {
        Object.assign($scope.editForm, user);
        $scope.editForm.roles = [];
        Object.assign($scope.editForm.roles, user.roles);
    };

    $scope.toggleRoleSelection = (role) => {
      let index = $scope.editForm.roles.indexOf(role);

      if (index === -1) {
          $scope.editForm.roles.push(role);
      } else {
          $scope.editForm.roles.splice(index, 1);
      }

    };

    $scope.saveChanges = () => {
        let resultMessageEl = document.getElementById('resultMessage');
        $http({
            method: "PUT",
            url: "/api/users",
            data: JSON.stringify($scope.editForm),
            headers: {"Content-Type" : "application/json"}
        }).then(
            (data) => {
                console.log(data);

                getListOfUsers($scope, $http);
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

    $scope.prepareToProposeReport = (user) => {
        $scope.conferences = [];
        $scope.reportProposeForm = {};

        $scope.reportProposeForm.speaker = user;

        $http.get("/api/conferences")
            .then(
                (data)=>{
                    console.log(data);
                    $scope.conferences = data.data;
                },
                (error) => {
                    console.log(error.data);
                })
    };

    $scope.proposeReport = () => {
        console.log($scope.reportProposeForm);
        console.log($scope.chosenConferenceId);
        $http({
            method: "POST",
            url: "/api/conferences/" + $scope.chosenConferenceId + "/addReport",
            data: JSON.stringify($scope.reportProposeForm),
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