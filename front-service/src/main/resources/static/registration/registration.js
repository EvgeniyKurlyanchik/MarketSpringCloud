angular.module('market').controller('registrationController', function ($scope, $http, $location, $localStorage) {
    $scope.functionRegistration = function () {
        $http.post('http://localhost:5555/auth/registration', $scope.reguser).then(function (response) {
            if (response.data.token) {
                $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                $localStorage.marchMarketUser = {username: $scope.reguser.username, token: response.data.token};
                $localStorage.reguser = null;
                $location.path("/");
            }
        });
    }
});