(function() {
  "use strict";
angular.module("myApp").factory("authenticationService", ["$http", "$q", "$window",function($http, $q, $window) {
  var userInfo;

  function login(url, data) {
    var deferred = $q.defer();

    $http.post(url, data).then(function(result) {
      userInfo = {
        accessToken: result.data.access_token,
        userName: result.data.name
      };
      $window.sessionStorage["userInfo"] = JSON.stringify(userInfo);
      deferred.resolve(userInfo);
    }, function(error) {
      deferred.reject(error);
    });

    return deferred.promise;
  }

  return {
    login: login
  };
}]);
})();
