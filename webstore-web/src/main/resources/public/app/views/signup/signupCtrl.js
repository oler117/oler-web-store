/**
 * Created by oles on 7/26/2016.
 */

app.controller('signupCtrl', ['$scope', '$http', '$log', 'countries', function ($scope, $http, $log, countries) {

    $scope.availableCountries = countries.data;
    $scope.emailFormat = /^[a-z]+[a-z0-9._]+@[a-z]+\.[a-z.]{2,5}$/;

    $scope.success = false;
    $scope.newUser = {};
    $scope.retypedPassword = '';

    $scope.showError = false;
    $scope.errorMsg = '';

    $scope.signUp = function () {

        $http.post('/user', $scope.newUser).then(function (response) {
            $scope.success = true;
            $log.debug(response);
        }, function (error) {
            $log.debug(error);
        })
    };

}])