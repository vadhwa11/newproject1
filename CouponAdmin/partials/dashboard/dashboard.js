(function() {
  "use strict";
  // angular.module('dashboard', []);
  angular.module("myApp").controller("dashBoardCtrl", [
    "$scope",
    "$location",
    "adminConfigURL",
    function($scope, $location, adminConfigURL) {
      //Recieving parameter from url
      // var urlParams = $location.search();
      // $scope.uname=urlParams.param;
      //Sending data to parent controller using emit
      // $scope.$emit('sendRecvdata', $scope.uname);
      // console.log($scope.uname);
    }
  ]);
  //Controllers
  angular.module("myApp").controller("todaysStatsController", [
    "$scope",
    "dashboardServices",
    "adminConfigURL",
    function($scope, dashboardServices, adminConfigURL) {
      dashboardServices.getTodaysStats().then(function(result) {
        $scope.data = result.data;
      });
    }
  ]);
  angular.module("myApp").controller("getstatsOfVouchersCtrl", [
    "$scope",
    "adminConfigURL",
    "reportsServices",
    "customerServices",
    function($scope, adminConfigURL, reportsServices, customerServices) {
      reportsServices.doVouchersEntry().then(function(result) {
        //Shows total list of vouchers
        result.data = { success: true, response: result.data.Data };
        if(result.data.response === " no coupon in database"){
          $scope.countofGeneratedVoucher=0;
        }else{
        $scope.countofGeneratedVoucher = result.data.response.length;

        // console.log($scope.data);
        // console.log(result.data.response);
        if (!result.data.error) {
          dataTable.render($scope, "", "vouchersList", result.data.response);
        }
      }
      });

      //Show count of assigned vouchers
      customerServices.distributorsAssignedVouchers().then(function(result) {
        result.data = { success: true, response: result.data.data };
        if(result.data.response==="No Distributor is registered"){
          $scope.assignedVouchersCount=0;
        }else{
        var count = 0;
        for (var i = 0; i < result.data.response.length; i++) {
          if (result.data.response[i].assignCouponList === "N/A") {
            continue;
          }
          var arr = [];
          arr = result.data.response[i].assignCouponList;
          count += arr.length;
        }
        $scope.assignedVouchersCount=count;
      }
      });
    }
  ]);
  angular.module("myApp").controller("getLastFiveCustomersController", [
    "$scope",
    "dashboardServices",
    "adminConfigURL",
    function($scope, dashboardServices, adminConfigURL) {
      dashboardServices.getLastFiveCustomers().then(function(result) {
        $scope.data = result.data;
      });
    }
  ]);
  angular.module("myApp").controller("recentNewsController", [
    "$scope",
    "dashboardServices",
    "adminConfigURL",
    function($scope, dashboardServices, adminConfigURL) {
      dashboardServices.getRecentNews().then(function(result) {
        $scope.data = result.data;
      });
    }
  ]);
})();
