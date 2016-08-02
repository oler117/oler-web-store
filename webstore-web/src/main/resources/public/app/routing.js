/**
 * Created by oles on 7/26/2016.
 */

app
    .config(function ($httpProvider, $stateProvider, $urlRouterProvider, $locationProvider) {

        $locationProvider.html5Mode(true);
        $locationProvider.hashPrefix('!');
        $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
        $httpProvider.interceptors.push('authHttpResponseInterceptor');
        $urlRouterProvider.otherwise("/");

        $stateProvider
            .state('home', {
                url: '/',
                templateUrl: viewsUrlPrefix + 'products/products.html',
                controller: 'productsCtrl'
            })
            .state('403', {
                url: '/403',
                templateUrl: viewsUrlPrefix + 'errors/403.html',
                controller: 'productsCtrl'
            })
            .state('signup', {
                url: '/signup',
                templateUrl: viewsUrlPrefix + 'signup/signup.html',
                controller: 'signupCtrl',
                resolve: {
                    countries: function ($http) {
                        return $http({method: 'GET', url: '/misc/countries'});
                    }
                }
            });


        // var interceptor = ['$rootScope', '$q', '$state', function(scope, $q, $state) {
        //     function success(response) {
        //         return response;
        //     }
        //     function error(response) {
        //         var status = response.status;
        //         if (status == 401) {
        //             $state.go('403');
        //             return;
        //         }
        //         return $q.reject(response);
        //     }
        //     return function(promise) {
        //         return promise.then(success, error);
        //     }
        // }];

    });