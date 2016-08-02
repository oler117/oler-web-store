/**
 * Created by oler117 on 01.08.2016.
 */
app.factory('authHttpResponseInterceptor', ['$q', '$location', '$injector', function ($q, $location, $injector) {
    return {
        response: function (response) {
            var statusPart = Math.ceil(response.status / 100);
            if (statusPart == 4 || statusPart == 5) {
                $q.reject(response);
            }
            return response || $q.when(response);
        },
        responseError: function (response) {
            var status = response.status;
            if (status == 401) {
                console.log("Response 401 error");
                $injector.invoke(function ($state) {
                    $state.go('403');
                })
                return;
            }
            return $q.reject(response);
        }
    }
}])