/**
 * Created by oles on 7/26/2016.
 */

app.config(function ($httpProvider, $stateProvider, $urlRouterProvider) {

    $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
    $urlRouterProvider.otherwise("/");

    $stateProvider
        .state('home', {
            url: '/',
            templateUrl: viewsUrlPrefix + 'products/products.html',
            controller: 'productsCtrl'
        });
})