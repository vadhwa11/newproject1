(function() {
  "use strict";
  // angular.module("reports", ["ngTable"]);
  //Controllers
  angular.module("myApp").controller("viewCustomerController", [
    "$scope",
    "customerResolved",
    "adminConfigURL",
    "$stateParams",
    function($scope, customerResolved, adminConfigURL, $stateParams) {
      $scope.viewCustomer = customerResolved.data;
      $scope.CouponId = $stateParams.id;
      for (var i = 0; i < $scope.viewCustomer.Data.length; i++) {
        if ($scope.CouponId === $scope.viewCustomer.Data[i].CouponCode) {
          $scope.FinalImage = $scope.viewCustomer.Data[i].ImageUrl;
          break;
        }
      }
      //PDF make

      //PDF function
      // $scope.generatePDF = function() {
      //   alert("hii");
      //   kendo.drawing.drawDOM($("#exportable")).then(function(group) {
      //     kendo.drawing.pdf.saveAs(group, "jay.pdf");
      //   });
      // };
      //PDF function
      var marginLR = "2cm";
      var marginTB = "3cm";
      $scope.generatePDF = function() {

        var draw = kendo.drawing;
        draw.drawDOM($("#exportable"))
          .then(function(root) {

            // Chaining the promise via then
            return draw.exportPDF(root, {
              paperSize: "A4",
              portrait: true,
              margin: {
                left: marginLR,
                top: marginTB,
                right: marginLR,
                bottom: marginTB
              },
              sizeToContent: "A4",
              pageSizes: true,
              paperSize: "auto",
              scale: 0.8
            });
          })
          .done(function(data) {
            // console.log(data);
            //Extracting the base64-encoded string and the contentType
            kendo.saveAs({
              dataURI: data,
              fileName: "Voucher.pdf"
            });
            console.log(saveAs);
          });
      };

      //   $scope.exportAction = function(option) {
      //     alert(option);
      //     switch (option) {
      //       case "pdf":
      //         $scope.$broadcast("export-pdf", {});
      //         break;
      //       case "excel":
      //         $scope.$broadcast("export-excel", {});
      //         break;
      //       case "doc":
      //         $scope.$broadcast("export-doc", {});
      //         break;
      //       case "csv":
      //         $scope.$broadcast("export-csv", {});
      //         break;
      //       default:
      //         console.log("no event caught");
      //     }
      //   };
      // angular.module("myApp").directive("exportTable", function() {
      //     var link = function($scope, elm, attr) {
      //       $scope.$on("export-pdf", function(e, d) {
      //         elm.tableExport({ type: "pdf", escape: false });
      //       });
      //       $scope.$on("export-excel", function(e, d) {
      //         elm.tableExport({ type: "excel", escape: false });
      //       });
      //       $scope.$on("export-doc", function(e, d) {
      //         elm.tableExport({ type: "doc", escape: false });
      //       });
      //       $scope.$on("export-csv", function(e, d) {
      //         elm.tableExport({ type: "csv", escape: false });
      //       });
      //     };
      //     return {
      //       restrict: "C",
      //       link: link
      //     };
      //   });
    }
  ]);

  angular.module("myApp").controller("getAddVoucherEntry", [
    "$scope",
    "reportsServices",
    "dataTable",
    "userServices",
    "adminConfigURL",
    "$http",
    function(
      $scope,
      reportsServices,
      dataTable,
      userServices,
      adminConfigURL,
      $http
    ) {
      $scope.acceptRejected = false;
      //Get Voucher Entry
      reportsServices.doVouchersEntry().then(function(result) {
        // result.data = { success: true, response: result.data.Data };
        result.data = { success: true, response: result.data.Data };
        // console.log($scope.data);
        // console.log(result.data.response);
        if (!result.data.error) {
          dataTable.render($scope, "", "vouchersList", result.data.response);
        }
      });
      //Add voucher Entry
      $scope.addVouchers = function() {
                $scope.sendObject = {
          authtoken: "1"
          // addVouchers_No: $scope.addvoucher_no
          // addVouchers_Id: $scope.addvoucher_id
        };
        // check for the send object in web service
        if ($scope.sendObject) {
          userServices
            .postForm(adminConfigURL.couponGeneration, $scope.sendObject)
            .then(
              function(res) {
                alert("Voucher Created Successfully");

                // $scope.addvoucher_no = "";
                // $scope.addvoucher_id = "";

                // $scope.name = res.data.name;
                // if (!res.error) {
                //   window.sessionStorage["userInfo"] = JSON.stringify(res.data);
                //   $rootScope.userInfo = JSON.parse(
                //     window.sessionStorage["userInfo"]
                //   );
                //   $location.path("/dashboard").search({ param: $scope.name });
                // }
              },
              function(err) {
                $scope.addvoucher_no = "";
                if (err.data.status === "2") {
                  alert(err.data.Data);
                } else {
                  alert("Invalid Data");
                }
              }
            );
        }
      };
      //Upload Excel file
      $scope.selectedFile = null;
      $scope.msg = "";
      $scope.putarrayData = {};

      $scope.loadFile = function(files) {
        $scope.$apply(function() {
          $scope.selectedFile = files[0];
        });
      };

      $scope.handleFile = function() {
        var file = $scope.selectedFile;

        if (file) {
          var reader = new FileReader();

          reader.onload = function(e) {
            var data = e.target.result;

            var workbook = XLSX.read(data, { type: "binary" });

            var first_sheet_name = workbook.SheetNames[0];

            var dataObjects = XLSX.utils.sheet_to_json(
              workbook.Sheets[first_sheet_name]
            );

            if (dataObjects.length > 0) {
              $scope.putarrayData = { authtoken: 1, multicoupon: dataObjects };
              // console.log($scope.putarrayData);
              $scope.save($scope.putarrayData);
            } else {
              $scope.msg = "Error : Something Wrong !";
            }
          };

          reader.onerror = function(ex) {};

          reader.readAsBinaryString(file);
        }
      };

      $scope.save = function(data) {
        userServices.postForm(adminConfigURL.bulkAdditionVoucher, data).then(
          function(res) {
            alert("Voucher Added Successfully");
          },
          function(err) {
            if (err.data.status === "21") {
              $scope.acceptRejected = true;
              $scope.accepted = "";
              $scope.rejected = "";
              $scope.data = err.data.Data;
              $scope.accepted = $scope.data.addcouponlist;
              $scope.rejected = $scope.data.rejectedcouponlist;
              // alert(err.data.Data);
            } else {
              alert("Invalid Data" + "-" + err.data.Data);
            }
          }
        );
      };
    }
  ]);
})();
