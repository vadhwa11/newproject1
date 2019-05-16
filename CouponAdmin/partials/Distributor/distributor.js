(function() {
  "use strict";
  angular.module("myApp").controller("getstatsOfVouchersEndUserCtrl", [
    "$scope",
    "$state",
    "userServices",
    "$location",
    "$rootScope",
    "customerServices",
    "adminConfigURL",
    "varConstant",
    "dataTable",
    "dataService",
    "distributorEndServices",
    function(
      $scope,
      $state,
      userServices,
      $location,
      $rootScope,
      customerServices,
      adminConfigURL,
      varConstant,
      dataTable,
      dataService,
      distributorEndServices
    ) {
      $scope.dist_id = dataService.DistributorId;
      $scope.endUserAssignToDb = function(coupon_code, email_Id) {
              // console.log(coupon_code);
              // console.log(email_Id);
      };
      distributorEndServices.getCouponByDistributed().then(function(result) {
        $scope.emailIdWithDistributor = result.data.EndUserEmailList;
        $scope.generatedvouchersCount=result.data.CouponList.length;
        // console.log($scope.emailIdWithDistributor);
        if (!result.data.error) {
          dataTable.render(
            $scope,
            "",
            "assignEnduserVouchers",
            result.data.CouponList
          );
        }
      });
    }
  ]);
  angular.module("myApp").controller("distributorCtrl", [
    "$scope",
    "$state",
    "userServices",
    "$location",
    "$rootScope",
    "customerServices",
    "adminConfigURL",
    "varConstant",
    "dataTable",
    "distributorEndServices",
    function(
      $scope,
      $state,
      userServices,
      $location,
      $rootScope,
      customerServices,
      adminConfigURL,
      varConstant,
      dataTable,
      distributorEndServices
    ) {
      $scope.registration = {};

      $scope.showDiv = true; // variable is used to show div or hide
      $scope.showGifFlag = false;
      $scope.stateCode = varConstant.stateCode;
      //list of states

      // function is used for register the data for distributor
      $scope.AddDistributor = function($event) {
        /// if condition is used to check valid data
        if ($scope.addDistributorForm.$invalid) {
          if ($scope.addDistributorForm.$error.required) {
            alert("Enter Required field");
          } else if ($scope.addDistributorForm.$error.pattern) {
            alert("Fill the valid data");
          }

          return false;
        } else {
          // $scope.showGifFlag = !$scope.showGifFlag;

          $scope.sendObject = {
            name: $scope.name,
            UserName: $scope.Username,
            UserPassword: $scope.Password,
            mobile: $scope.Mobile,
            // PanNo: $scope.PanNo,
            email: $scope.email,
            city: $scope.Address,
            state: $scope.State.state,
            pincode: $scope.pin_code
          };

          // check for the send object in web service
          if ($scope.sendObject) {
            userServices
              .postForm(adminConfigURL.DistributorRegister, $scope.sendObject)
              .then(
                function(res) {
                  $scope.name = res.data.name;
                  alert(res.data.mssg);
                  ($scope.name = ""), ($scope.Username = ""), ($scope.Password =
                    ""), ($scope.Mobile = ""), ($scope.email =
                    ""), ($scope.Address = ""), ($scope.State.state =
                    ""), ($scope.pin_code = "");
                },
                function(err) {
                  alert("Invalid Data" + "-" + err.data);
                }
              );
          }
        }
      };
      $scope.cancel = function() {
        $location.path("/customers");
      };
      //function used to get number of distributor added
      customerServices.distributorsAssignedVouchers().then(function(result) {
        result.data = { success: true, response: result.data.data };
        if (!result.data.response.error) {
          dataTable.render(
            $scope,
            "",
            "DistributortList",
            result.data.response
          );
        }
      });
    }
  ]);
  //End User Registartion
  angular.module("myApp").controller("endUserCtrl", [
    "$scope",
    "$state",
    "userServices",
    "$location",
    "$rootScope",
    "customerServices",
    "distributorEndServices",
    "adminConfigURL",
    "varConstant",
    "dataTable",
    "dataService",
    function(
      $scope,
      $state,
      userServices,
      $location,
      $rootScope,
      customerServices,
      distributorEndServices,
      adminConfigURL,
      varConstant,
      dataTable,
      dataService
    ) {
      $scope.disabled = true;
      $scope.dist_id = dataService.DistributorId;
      $scope.registration = {};
      $scope.showDiv = true; // variable is used to show div or hide
      $scope.showGifFlag = false;
      $scope.stateCode = varConstant.stateCode;
      //list of states

      // function is used for register the data
      $scope.AddendUser = function($event) {
        /// if condition is used to check valid data
        if ($scope.addendUserForm.$invalid) {
          if ($scope.addendUserForm.$error.required) {
            alert("Enter Required field");
          } else if ($scope.addendUserForm.$error.pattern) {
            alert("Fill the valid data");
          }

          return false;
        } else {
          // $scope.showGifFlag = !$scope.showGifFlag;
          $scope.sendObject = {
            Distributed_Id: $scope.dist_id,
            name: $scope.name,
            GSTIN: $scope.GSTIN,
            Mobile: $scope.Mobile,
            email: $scope.email,
            PanNo: $scope.PAN
          };

          // check for the send object in web service
          if ($scope.sendObject) {
            userServices
              .postForm(
                adminConfigURL.endUserRegistrationAPI,
                $scope.sendObject
              )
              .then(
                function(res) {
                  // $scope.name = res.data.name;
                  alert(res.data.Data);
                  ($scope.name = ""), ($scope.Username = ""), ($scope.Password =
                    ""), ($scope.Mobile = ""), ($scope.email =
                    ""), ($scope.Address = ""), ($scope.State.state =
                    ""), ($scope.pin_code = "");
                },
                function(err) {
                  alert(err.data.Data);
                }
              );
          }
        }
      };

      //function used to get number of distributor added
      distributorEndServices.showAllEndUserAPI().then(function(result) {
        result.data = { success: true, response: result.data.Data };
        if (!result.data.response.error) {
          dataTable.render($scope, "", "endUserList", result.data.response);
        }
      });
    }
  ]);
  //Get Assigned Information of End user getendUserController
  angular.module("myApp").controller("getendUserController", [
    "$scope",
    "$state",
    "userServices",
    "$location",
    "$rootScope",
    "customerServices",
    "adminConfigURL",
    "varConstant",
    "dataTable",
    "distributorEndServices",
    function(
      $scope,
      $state,
      userServices,
      $location,
      $rootScope,
      customerServices,
      adminConfigURL,
      varConstant,
      dataTable,
      distributorEndServices
    ) {
      $scope.registration = {};
      $scope.showDiv = true; // variable is used to show div or hide
      $scope.showGifFlag = false;
      $scope.stateCode = varConstant.stateCode;
      //list of states

      customerServices.distributorsAssignedVouchers().then(function(result) {
        result.data = { success: true, response: result.data.data };

        if (!result.data.response.error) {
          dataTable.render(
            $scope,
            "",
            "DistributortList",
            result.data.response
          );
        }
      });
    }
  ]);
})();
