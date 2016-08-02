/**
 * Created by oles on 7/28/2016.
 */

app.factory('auth', ['$log', '$http', '$rootScope', '$cookies', function ($log, $http, $rootScope, $cookies) {

    var authenticate = function (credentials, callback) {

        var headers = credentials ?
        {
            authorization: "Basic "
            + btoa(credentials.username + ":" + credentials.password)
        } : {};

        $http.get('user', {headers: headers}).then(function (response) {
            if (response.data.name) {
                $rootScope.authenticated = true;
                $rootScope.user = response.data;
            } else {
                $rootScope.authenticated = false;
            }
            callback && callback();
        }, function (data) {
            $log.debug(data);
            $rootScope.authenticated = false;
            callback && callback();
        });
    }

    /**
     *
     * @param credentials
     * @param callback
     */
    var login = function (credentials, callback) {
        authenticate(credentials, callback);
    };

    /**
     *
     * @param $location
     */
    var logout = function () {

        $http.get("/csrf", {}).then(function (response) {
            var _csrf = response.data.token;
            $cookies.put('XSRF-TOKEN', _csrf);
            $http.post('logout', {}).then(function () {
                $rootScope.authenticated = false;
                $rootScope.user = {};
            }, function (data) {
                $log.debug(data);
            });
        });

    }

    return {
        login: login,
        logout: logout
    }
}])