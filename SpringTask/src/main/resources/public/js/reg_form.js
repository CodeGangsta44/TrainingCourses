const setColorInputLabel = (name, color) => {
    console.log(typeof name);
    let resultString = 'input' + name.charAt(0).toUpperCase() + name.slice(1) + 'Label';
    document.getElementById(resultString).style.color = color;
};

const setListenerForInput = (name, scope) => {
    console.log(name);
    let resultString = 'exampleInput' +  name.charAt(0).toUpperCase() + name.slice(1);
    let element = document.getElementById(resultString);
    console.log(resultString);
    element.addEventListener('input', () => {
        setColorInputLabel(name, 'black');
        scope.message = '';
    })
};

angular.module("registration_form",[])
    .controller("AppCtrl", function ($scope, $http) {
        $scope.auth = {};

        let resultMessageEl = document.getElementById('resultMessage');


        $scope.sendForm = function(auth){

            Object.keys($scope.auth)
                .forEach(key => setListenerForInput(key, $scope));

            $http({
                method: "POST",
                url: "/api/users",
                data: $.param(auth),
                headers: { "Content-Type" : "application/x-www-form-urlencoded" }
            }).then(
                (data) => {
                    console.log(data);
                    Object.keys($scope.auth)
                        .forEach(key => {
                            setColorInputLabel(key, 'black');
                            $scope.auth[key] = '';
                        });

                resultMessageEl.className = 'alert alert-success';
                resultMessageEl.innerText = data.data;
                resultMessageEl.style.visibility='visible';
        },
            (error) => {
                console.log(error);

                let note = error.data.note;

                Object.keys(note)
                    .forEach(
                        key => {
                            $scope.auth[key] = note[key];
                            if (note[key] === '') setColorInputLabel(key, 'red');
                        });

                resultMessageEl.className = 'alert alert-warning';
                resultMessageEl.innerText = error.data.localizedMessage;
                // $scope.message = error.data.localizedMessage;
                resultMessageEl.style.visibility='visible';
            }
        );
        }
    });