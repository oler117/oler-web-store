/**
 * Created by oles on 7/26/2016.
 */
app.controller('navigationCtrl', function ($scope, $rootScope, $http) {

    // $rootScope.authenticated = false;

    $scope.logout = function () {
        $http.post('logout', {}).then(function () {
            $rootScope.authenticated = false;
            $location.path("/");
        }, function (data) {
            $rootScope.authenticated = false;
            console.log(data);
        });
    }

})