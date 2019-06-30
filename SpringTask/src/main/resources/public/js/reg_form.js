angular.module("registration_form",[])
    .controller("AppCtrl", function ($scope, $http) {
        $scope.auth = {};
        let resultMessageEl = document.getElementById('resultMessage');
        let exampleInputNameEl = document.getElementById('exampleInputName');
        let exampleInputLoginEl = document.getElementById('exampleInputLogin');


        let inputNameLabel = document.getElementById('inputNameLabel');
        let inputLoginLabel = document.getElementById('inputLoginLabel');

        exampleInputLoginEl.addEventListener('input', () => {
            inputNameLabel.style.color = 'black';
            inputLoginLabel.style.color = 'black';
            $scope.message = '';
    });
        $scope.sendForm = function(auth){
            $http({
                method: "POST",
                url: "/api/registration",
                data: $.param(auth),
                headers: { "Content-Type" : "application/x-www-form-urlencoded" }
            }).then(
                (data) => {
                resultMessageEl.style.color = 'green';
            $scope.message = 'Successfully registered';
            exampleInputNameEl.value = '';
            exampleInputLoginEl.value = '';
            inputLoginLabel.style.color = 'black';
        },
            (error) => {
                console.log(error.data);
                resultMessageEl.style.color = 'red';
                inputLoginLabel.style.color = 'red';
                $scope.message = error.data.message;
            }
        );
        }
    });