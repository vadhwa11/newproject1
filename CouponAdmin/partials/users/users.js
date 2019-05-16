(function() {
  "use strict";
  //Controllers
  angular.module("myApp").controller("loginController", [
    "$scope",
    "userServices",
    "$location",
    "$rootScope",
    "customerServices",
    "adminConfigURL",
    "dataService",
    '$window',
    function(
      $scope,
      userServices,
      $location,
      $rootScope,
      customerServices,
      adminConfigURL,
      dataService,
      $window
    ) {
      $scope.loader = false;
      $scope.userType='';
      $scope.userType = "distributor";
      // distributorLogin
      $scope.doLogin = function($event) {
        if ($scope.userType === "admin") {
          /// if condition is used to check valid data
          if ($scope.loginForm.$invalid) {
            if ($scope.loginForm.$error.required) {
              alert("Enter Required field");
            } else if ($scope.loginForm.$error.pattern) {
              alert("Fill the valid data");
            }

            return false;
          } else {
            $scope.loader = true;
            // $scope.showGifFlag = !$scope.showGifFlag;
            $scope.sendObject = {
              admin_user: $scope.login.email,
              admin_pass: $scope.login.password
            };
            // check for the send object in web service
            if ($scope.sendObject) {
              userServices
                .postForm(adminConfigURL.loginAPI, $scope.sendObject)
                .then(
                  function(res) {
                    $scope.loader = false;
                    dataService.username = res.data.username;
                    dataService.DistributorId=res.data.DistributorId;
                    dataService.usertype = $scope.userType;
                  //   var data = {"userType":$scope.userType, "username":res.data.username};
                   $window.localStorage['userType'] = $scope.userType;
                   $window.localStorage['userName'] = res.data.username;
                  // $window.localStorage['userType']={"userType":$scope.userType, "username":res.data.username};
                    // dataService.usertype = "distributor";
                    // customerServices.getUsername = $scope.name;
                    // var cookiesValue = {
                    //   'userName':$scope.name
                    // };
                    // $cookieStore.put("cookiesValue", cookiesValue);
                    if (!res.error) {
                      window.sessionStorage["userInfo"] = JSON.stringify(
                        res.data
                      );
                      $rootScope.userInfo = JSON.parse(
                        window.sessionStorage["userInfo"]
                      );
                      $location.path("/dashboard");
                    }
                  },
                  function(err) {
                    $scope.loader = false;
                    alert("Invalid User Name or Password");
                  }
                );
            }
          }
          //}
        }

        //User Type
        else{
          /// if condition is used to check valid data
          if ($scope.loginForm.$invalid) {
            if ($scope.loginForm.$error.required) {
              alert("Enter Required field");
            } else if ($scope.loginForm.$error.pattern) {
              alert("Fill the valid data");
            }

            return false;
          } else {
            $scope.loader = true;
            // $scope.showGifFlag = !$scope.showGifFlag;
            $scope.sendObject = {
              Distributor_user: $scope.login.email,
              Distributor_pass: $scope.login.password
            };
            // check for the send object in web service
            if ($scope.sendObject) {
              userServices
                .postForm(adminConfigURL.distributorLogin, $scope.sendObject)
                .then(
                  function(res) {
                    $scope.loader = false;
                    dataService.username = res.data.username;
                    dataService.DistributorId=res.data.DistributorId;
                    dataService.usertype = $scope.userType;
                    $window.localStorage['userType'] = $scope.userType;
                    $window.localStorage['userName'] = res.data.username;
                  // $window.localStorage['userType']={userType:$scope.userType, username:res.data.username};
                    if (!res.error) {
                      window.sessionStorage["userInfo"] = JSON.stringify(
                        res.data
                      );
                      $rootScope.userInfo = JSON.parse(
                        window.sessionStorage["userInfo"]
                      );
                      $location.path("/distributorDashboard");
                    }
                  },
                  function(err) {
                    $scope.loader = false;
                    alert("Invalid User Name or Password");
                  }
                );
            }
          }
          //}
        }


      };
    }
  ]);
  angular.module("myApp").controller("adminCtrl", [
    "$scope",
    "customerServices",
    "adminConfigURL",
    "dataService",
    "$window",
    function($scope, customerServices, adminConfigURL, dataService, $window) {
      // $scope.U_name=$cookieStore.get("cookiesValue");
      // alert("one");
      // $scope.Portal='';
      // $scope.portal="admin";
      $scope.u_name = dataService.username;
      $scope.usertype = dataService.usertype;

      if(!$scope.usertype){
          $scope.usertype =  $window.localStorage['userType'];
      }
      if(!$scope.u_name){
        $scope.u_name= $window.localStorage['userName'];
      }

      // console.log($scope.anotherVar);
    }
  ]);
  angular.module("myApp").controller("signupController", [
    "$scope",
    "userServices",
    "$location",
    "adminConfigURL",
    function($scope, userServices, $location, adminConfigURL) {
      $scope.doSignup = function() {
        if ($scope.signupForm.$valid) {
          userServices.signup($scope.signup).then(function(result) {
            $scope.data = result;
            if (!result.error) {
              $location.path("/login");
            }
          });
        }
      };
    }
  ]);
  angular
    .module("myApp")
    .controller("distributorLoginCtrl", [
      "$scope",
      "userServices",
      "$location",
      "adminConfigURL",
      function($scope, userServices, $location, adminConfigURL) {}
    ]);
  angular.module("myApp").controller("logoutController", [
    "$scope",
    "$location",
    "$rootScope",
    "adminConfigURL",
    function($scope, $location, $rootScope, adminConfigURL) {
      sessionStorage.clear();
      $rootScope.userInfo = false;
      $location.path("/login");
    }
  ]);
})();
