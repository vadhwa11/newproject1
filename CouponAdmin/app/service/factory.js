(function() {
  "use strict";
  //Datatable
  angular.module("myApp").factory("dataTable", [
    "$filter",
    "ngTableParams",
    function($filter, ngTableParams) {
      var factoryDefinition = {
        render: function($scope, config, componentId, data) {
          if (!config) config = {};
          var config = angular.extend({}, { page: 1, count: 30 }, config);

          $scope[componentId] = new ngTableParams(config, {
            total: data.length, // length of data
            getData: function($defer, params) {
              // use build-in angular filter
              var filteredData = params.filter()
                ? $filter("filter")(data, params.filter())
                : data;
              var orderedData = params.sorting()
                ? $filter("orderBy")(filteredData, params.orderBy())
                : data;
              params.total(orderedData.length); // set total for recalc pagination
              $defer.resolve(
                orderedData.slice(
                  (params.page() - 1) * params.count(),
                  params.page() * params.count()
                )
              );
            }
          });
        }
      };

      return factoryDefinition;
    }
  ]);
  //Factories for distributor and end user
  angular.module("myApp").factory("distributorEndServices", [
    "$http",
    "adminConfigURL",
    "dataService",
    function($http, adminConfigURL, dataService) {
      var factoryDefinitions = {
        getCouponByDistributed: function(url, data) {
          var obj = { Distributor_id: dataService.DistributorId };
          return $http
            .post(adminConfigURL.getCouponByDistributed, obj)
            .success(function(data) {
              return data;
            });
        },
        showAllEndUserAPI: function(url, data) {
          var obj = { Distributor_id: dataService.DistributorId };
          return $http
            .post(adminConfigURL.showAllEndUserAPI, obj)
            .success(function(data) {
              return data;
            });
        }
      };
      return factoryDefinitions;
    }
  ]);

  //Factories for report services
  angular.module("myApp").factory("reportsServices", [
    "$http",
    "adminConfigURL",
    function($http, adminConfigURL) {
      var factoryDefinitions = {
        doVouchersEntry: function(url, data) {
          var obj = { authtoken: "1" };
          return $http
            .post(adminConfigURL.availablecouponApi, obj)
            .success(function(data) {
              return data;
            });
        }
      };

      return factoryDefinitions;
    }
  ]);
  //Factories customerServices
  angular.module("myApp").factory("customerServices", [
    "$http",
    "adminConfigURL",
    function($http, adminConfigURL) {
      var factoryDefinitions = {
        assignVouchers: function(url, data) {
          var obj = { authtoken: "1" };
          return $http
            .post(adminConfigURL.assignVoucherAPI, obj)
            .success(function(data) {
              return data;
            });
        },
        distributorsAssignedVouchers: function(url, data) {
          var obj = { authtoken: "1" };
          return $http
            .post(adminConfigURL.distributorsAssignedVouchers, obj)
            .success(function(data) {
              return data;
            });
        },
        addCustomer: function(customerReq) {
          return $http
            .post("partials/common/mock/success.json", customerReq)
            .success(function(data) {
              return data;
            });
        },
        getCustomer: function(customerId) {
          return $http
            .get("partials/customers/mock/get_customer.json?id=" + customerId)
            .success(function(data) {
              return data;
            });
        },
        updateCustomer: function(customerReq) {
          return $http
            .post("partials/common/mock/success.json", customerReq)
            .success(function(data) {
              return data;
            });
        }
      };

      return factoryDefinitions;
    }
  ]);
  //Factories dashboardServices
  angular.module("myApp").factory("dashboardServices", [
    "$http",
    function($http) {
      var factoryDefinitions = {
        getTodaysStats: function() {
          return $http
            .get("partials/dashboard/mock/todays_stats.json")
            .success(function(data) {
              return data;
            });
        },
        getRecentNews: function() {
          return $http
            .get("partials/dashboard/mock/recent_news.json")
            .success(function(data, status, headers, config) {
              return data;
            });
        },
        getLastFiveCustomers: function() {
          return $http
            .get("partials/dashboard/mock/customers_last_five.json")
            .success(function(data) {
              return data;
            });
        }
      };

      return factoryDefinitions;
    }
  ]);
})();
