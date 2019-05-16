(function() {
  "use strict";
  app.factory("loginService", [
    "http",
    "$location",
    "sessionService",
    function($http, $location, sessionService) {
      return {
        login: function(data, scope) {
          var $promise = $http.post(url, data); //send data to login checker
          $promise.then(function(msg) {
            var uid = msg.data;
            if (uid) {
              sessionService.set("uid", uid);
              $location.path("/home");
            } else {
              $scope.msgtxt = "incorrect information";
              $location.path("/login");
            }
          });
        },
        logout: function() {
          sessionService.destroy("uid");
          $location.path("/login");
        },
        isLogged: function() {
          var $checkSessionServer = $http.post("data/check_session.php");
          return $checkSessionServer;
        }
      };
    }
  ]);
})();
