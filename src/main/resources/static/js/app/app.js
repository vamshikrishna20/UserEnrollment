var app = angular.module('userApp',['ui.router','ngStorage']);

app.constant('urls', {
    BASE: 'http://localhost:8080/UserEnrollment',
    User_SERVICE_API : 'http://localhost:8080/UserEnrollment/api/User/'
});

app.config(['$stateProvider', '$urlRouterProvider',
    function($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('home', {
                url: '/',
                templateUrl: 'partials/usersDetails.',
                controller:'UserController',
                controllerAs:'ctrl',
                resolve: {
                    Users: function ($q, UserService) {
                        console.log('Load all Users');
                        var deferred = $q.defer();
                        UserService.loadAllUsers().then(deferred.resolve, deferred.resolve);
                        return deferred.promise;
                    }
                }
            });
        $urlRouterProvider.otherwise('/');
    }]);

