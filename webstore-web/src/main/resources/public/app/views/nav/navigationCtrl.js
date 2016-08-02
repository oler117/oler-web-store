/**
 * Created by oles on 7/26/2016.
 */
app.controller('navigationCtrl', ['$log', '$scope', '$rootScope', 'auth',
    function ($log, $scope, $rootScope, auth) {

        $rootScope.user = {};
        $scope.credentials = {};

        $scope.toggleLoginForm = function () {
            $("#collapseExample").collapse('toggle');
        }

        // ++++++++++++++++  Login/Logout  ++++++++++++++++
        $scope.login = function () {
            var loginCallback = function () {
                if ($rootScope.authenticated) {
                    $scope.credentials = {};
                    $scope.error = false;
                    $scope.toggleLoginForm();
                    $log.debug("User authenticated: ", $rootScope.user);
                } else {
                    $scope.error = true;
                }
            }
            auth.login($scope.credentials, loginCallback);
        }
        $scope.logout = function () {
            auth.logout();
        }

        // authenticate();
    }
])