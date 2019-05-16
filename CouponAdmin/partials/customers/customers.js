(function() {
  "use strict";
  // angular.module("customers", ["ngTable"]);

  //Routers

  //Controllers

  //getCustomersController is used to get List of distributor along with their assigned vouchers and to view their assigned vouchers in pdf
  angular.module("myApp").controller("getCustomersController", [
    "$scope",
    "customerServices",
    "dataTable",
    "adminConfigURL",
    "reportsServices",
    function(
      $scope,
      customerServices,
      dataTable,
      adminConfigURL,
      reportsServices
    ) {

      $scope.couponCodeWithDistributor = [];
      //function used to get all the the assigned vocuhers along with distributors
      customerServices.distributorsAssignedVouchers().then(function(result) {
        $scope.data = result.data;

        var count = 0;
        for (var i = 0; i < result.data.length; i++) {
          if (result.data[i].assignCouponList === "N/A") {
            continue;
          }
          var arr = [];
          arr = result.data[i].assignCouponList;
          count += arr.length;
        }
        $scope.assignedVouchersCount = count;

        //function used to extract only coupon code fields
        for (var val = 0; val < result.data.data.length; val++) {
          $scope.couponCodeWithDistributor.push({
            Coupon_code: result.data.data[val].assignCouponList
          });
        }

        // $scope.couponCodeWithDistributor = _.map(result.data.data, function(
        //   field
        // ) {
        //   return _.omit(field, [
        //     "DistributorId",
        //     "DistributorName",
        //     "Email",
        //     "phoneNo"
        //   ]).assignCouponList; // only coupon field
        // });

        // $scope.fields = _.map(result.data.response, function(field) {
        //   console.log(field);
        //   return [field.id, field.first_name, field.last_name, field.email, field.phone];
        // });

        if (!result.data.error) {
          dataTable.render($scope, "", "customerstList", result.data.data);
        }
      });

      //Show count of Total vouchers per Distributor
      // customerServices.distributorsAssignedVouchers().then(function(result) {
      //   result.data = { success: true, response: result.data.data };

      // });

      //  To view the assigned the coupon in PDf format
      $scope.assignedVouchersDsitriList = function(val1, val2) {
        // $scope.CouponId = $stateParams.id;
        //ImageUrl
        var val = val1.CouponCode;
        $scope.id = "";
        reportsServices.doVouchersEntry().then(function(result) {
          for (var i = 0; i < result.data.Data.length; i++) {
            if (result.data.Data[i].CouponCode === val) {
              var id = result.data.Data[i].CouponCode;
              $scope.id = id;
              // console.log(id);
              break;
            }
          }
        });
        // for (var i = 0; i < $scope.viewCustomer.Data.length; i++) {
        //   if ($scope.CouponId === $scope.viewCustomer.Data[i].CouponCode) {
        //     $scope.FinalImage = $scope.viewCustomer.Data[i].ImageUrl;
        //     break;
        //   }
        // }
      };
    }
  ]);
  //Get Voucher Entry and add vouchers entry

  angular.module("myApp").controller("addCustomerController", [
    "$scope",
    "customerServices",
    "$location",
    "adminConfigURL",
    function($scope, customerServices, $location, adminConfigURL) {
      $scope.addCustomer = function() {
        if ($scope.addCustomerForm.$valid) {
          customerServices.addCustomer($scope.customer).then(function(result) {
            $scope.data = result;
            if (!result.error) {
              $location.path("/customers");
            }
          });
        }
      };

      $scope.cancel = function() {
        $location.path("/customers");
      };
    }
  ]);
  //Assign Vouchers controller
  angular.module("myApp").controller("assignVouchersCtrl", [
    "$scope",
    "customerServices",
    "userServices",
    "dataTable",
    "adminConfigURL",
    function(
      $scope,
      customerServices,
      userServices,
      dataTable,
      adminConfigURL
    ) {
      $scope.senobj = [];
      $scope.sendObjvaltoServer = {};
      $scope.HideId = false;
      //Show Vouchers List along with distributor name
      customerServices.assignVouchers().then(function(result) {
        result.data = {
          success: true,
          response: result.data
        };
        $scope.distributor_drop = "";
        $scope.distributor_drop = result.data.response.Data.DistributorList;
        // $scope.distributor_name="";
        // for (var i = 0; i < result.data.response.Data.length; i++) {
        //   // $scope.distributor_drop=result.data.response[i].distributor_name;
        //   $scope.distributor_drop.push(result.data.response.Data[i].DistributorList);
        //
        //   // console.log($scope.distributor_drop);
        // }
        // console.log($scope.data);
        // console.log(result.data.response);
        if (!result.data.error) {
          dataTable.render(
            $scope,
            "",
            "AssignVouchersList",
            result.data.response.Data.couponList
          );
        }
      });
      //Add and send voucher to DB
      $scope.assignVouchers = function(val, distributorname) {
        if (distributorname.distributor_name == "Select") {
          $scope.senobj = [];
          // alert("select at least one distributor");
        } else {
          $scope.senobj.push({
            // CoupanSerNo: val.CoupanSerNo,
            coupon_code: val.CouponCode,
            Distributor_name: distributorname.Distributor_name,
            // Status: val.Status,
            Distributor_id: distributorname.Distributor_name_id
          });
          // console.log($scope.senobj);
          //$scope.editData.push(result.data.response[i].distributor_name);
        }
      };
      //Assign to DB
      $scope.assignToDb = function() {
        $scope.sendObjvaltoServer = { assigncouponlist: $scope.senobj };
        if ($scope.sendObjvaltoServer) {
          userServices
            .postForm(
              adminConfigURL.assignedtoDistributorAPI,
              $scope.sendObjvaltoServer
            )
            .then(
              function(res) {
                alert("Voucher Assigned Successfully");
                $scope.distributorname = "";
              },
              function(err) {
                alert("Invalid Data" + "-" + err.data);
              }
            );
        }
      };
    }
  ]);
  // angular.module("myApp").controller("editCustomerController", [
  //   "$scope",
  //   "customerResolved",
  //   "customerServices",
  //   "$location",
  //   "$state",
  //   "adminConfigURL",
  //   function(
  //     $scope,
  //     customerResolved,
  //     customerServices,
  //     $location,
  //     $state,
  //     adminConfigURL
  //   ) {
  //     $scope.customer = customerResolved.data;
  //
  //     $scope.updateCustomer = function() {
  //       if ($scope.editCustomerForm.$valid) {
  //         customerServices
  //           .updateCustomer($scope.customer)
  //           .then(function(result) {
  //             $scope.data = result.data;
  //             if (!result.data.error) {
  //               $state.transitionTo("customer.view", {
  //                 id: $state.params.id
  //               });
  //             }
  //           });
  //       }
  //     };
  //
  //     $scope.cancel = function() {
  //       $location.path("/customers");
  //     };
  //   }
  // ]);
})();
