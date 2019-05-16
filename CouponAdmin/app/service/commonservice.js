(function() {
  "use strict";
  angular.module("myApp").service("userServices", [
    "$http",
    "$q",
    "$state",
    function($http, $q, $state) {
      var _this = this;
      _this.status = "";
      // Post method  is used to call the web service
      _this.postForm = function(url, data) {
        var deferred = $q.defer();
        $http
          .post(url, data, {
            withCredentials: true
          })
          .then(
            function(response) {
              //  _this.getToken = response.data.authtoken;
              // _this.start();
              // _this.status = 1;
              if (response.data.status === "1") {
                return deferred.resolve(response);
              } else if (response.data.status === "0") {
                return deferred.reject(response);
              }
              else if (response.data.status === "2") {
                return deferred.reject(response);
              }
              else if (response.data.status === "21") {
                return deferred.reject(response);
              }
              else {
                return deferred.resolve(response);
              }
            },
            function(err) {
              // _this.status = 0;
              return deferred.reject(err);
            }
          );
        return deferred.promise;
      };
    }
  ]);
})();
