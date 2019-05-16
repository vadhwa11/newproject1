//Routers
angular.module("myApp").config([
  "$stateProvider",
  "$urlRouterProvider",
  "$httpProvider",
  // "$compileProvider",
  function(
    $stateProvider,
    $urlRouterProvider,
    $httpProvider
    // $compileProvider
  ) {
    //Performance

    //HTTP Configuration
    //  $httpProvider.defaults.headers.common = {};
    // $httpProvider.defaults.headers.post = {};
    $httpProvider.defaults.headers.put = {};
    $httpProvider.defaults.headers.patch = {};
    $httpProvider.defaults.useXDomain = true;
    $httpProvider.defaults.headers.common["X-Requested-With"];
    $httpProvider.defaults.headers.common = "Content-Type: application/json";
    $httpProvider.defaults.withCredentials = true;
    $httpProvider.defaults.headers.common["Access-Control-Allow-Origin"] = "*";
    $httpProvider.defaults.headers.common["Access-Control-Allow-Headers"] =
      "Origin, X-Requested-With, Content-Type, Accept";

    //Session
    if (!window.sessionStorage["userInfo"]) {
      $urlRouterProvider.otherwise("/login");
    } else {
      $urlRouterProvider.otherwise("/dashboard");
    }

    //Public States
    $stateProvider.state("public", {
      abstract: true,
      views: {
        "headerLogo@": {
          name: "headerLogo",
          templateUrl: "partials/common/loginheader.html"
        },
        "@": {
          template: "<ui-view></ui-view>"
        }
      }
    });

    //Private States
    $stateProvider.state("private", {
      abstract: true,

      views: {
        "userLogo@": {
          name: "userLogo",
          templateUrl: "partials/common/userheader.html"
        },
        "@": {
          template: "<ui-view></ui-view>"
        }
      }
    });
    //Login
    $stateProvider.state("login", {
      url: "/login",
      templateUrl: "partials/users/login.html",
      controller: "loginController",
      parent: "public"
    });
    //dashboard
    $stateProvider.state("dashboard", {
      url: "/dashboard",
      templateUrl: "partials/dashboard/dashboard.html",
      controller: "dashBoardCtrl",
      data: {
        auth: true
      },
      parent: "private"
    });
    $stateProvider.state("distributorDashboard",{
      url:"/distributorDashboard",
      templateUrl: "partials/Distributor/distributorDashboard.html",
    controller:"getstatsOfVouchersEndUserCtrl",
      data: {
        auth: true
      },
      parent: "private"
    });
    //Signup
    $stateProvider.state("signup", {
      url: "/signup",
      templateUrl: "partials/users/signup.html",
      controller: "signupController",
      parent: "public"
    });

    //Distributor Login
    // $stateProvider.state("distributorLogin", {
    //   url: "distributorLogin",
    //   templateUrl: "partials/users/distributorLogin.html",
    //   controller: "distributorLoginCtrl",
    //   parent: "public"
    //
    //   // data: {
    //   //   auth: true
    //   // }
    // });

    //Reports

    $stateProvider.state("distributorreports", {
      url: "/distributorreports",
      templateUrl: "partials/Distributor/distributorreports.html",
      controller:"endUserCtrl",
      data: {
        auth: true
      },
      parent: "private"
    });
$stateProvider.state("IssuedVouchers", {
  url: "/IssuedVouchers",
  templateUrl: "partials/Distributor/endUserAssigned.html",
  controller:"getendUserController",
  data: {
    auth: true
  },
  parent: "private"
});

    $stateProvider.state("reports", {
      url: "/reports",
      templateUrl: "partials/reports/reports.html",
      data: {
        auth: true
      },
      parent: "private"
    });
    //addDistributor
    $stateProvider.state("addDistributor", {
      url: "/addDistributor",
      templateUrl: "partials/Distributor/addDistributor.html",
      data: {
        auth: true
      },
      parent: "private"
    });

    //Search Customers
    $stateProvider.state("customers", {
      url: "/customers",
      templateUrl: "partials/customers/customers.html",
      data: {
        auth: true
      },
      parent: "private"
    });

    //Add Customer
    $stateProvider.state("addCustomer", {
      url: "/addCustomer",
      templateUrl: "partials/customers/addCustomer.html",
      data: {
        auth: true
      },
        parent: "private"
    });

    //Customer Tab
    $stateProvider.state("customer", {
      url: "",
      abstract: true,
      templateUrl: "partials/customers/customerTab.html",
      data: {
        auth: true
      },
        parent: "private"
    });

    //View customer
    $stateProvider.state("customer.view", {
      url: "/view/{id}",
      views: {
        viewCustomer: {
          templateUrl: "partials/customers/viewCustomer.html",
          controller: "viewCustomerController"
        }
      },
      resolve: {
        customerResolved: function(reportsServices, $stateParams) {
          return reportsServices.doVouchersEntry($stateParams.id);
        }
      },
      data: {
        auth: true
      },
        parent: "customer"
    });

    //Edit customer
    // $stateProvider.state("customer.edit", {
    //   url: "/edit/{id}",
    //   views: {
    //     editCustomer: {
    //       templateUrl: "partials/customers/editCustomer.html",
    //       controller: "editCustomerController"
    //     }
    //   },
    //   resolve: {
    //     customerResolved: function(customerServices, $stateParams) {
    //       return customerServices.getCustomer($stateParams.id);
    //     }
    //   },
    //   data: {
    //     auth: true
    //   }
    // });
    //Logout
    $stateProvider.state("logout", {
      url: "/logout",
      template: "<h3>Logging out...</h3>",
      controller: "logoutController"
    });
  }
]);

//Run Phase with session management
angular.module("myApp").run(function($rootScope, $state) {
  $rootScope.$state = $state; //Get state info in view

  if (window.sessionStorage["userInfo"]) {
    $rootScope.userInfo = JSON.parse(window.sessionStorage["userInfo"]);
  }

  //Check session and redirect to specific page
  $rootScope.$on("$stateChangeStart", function(
    event,
    toState,
    toParams,
    fromState,
    fromParams
  ) {
    if (
      toState &&
      toState.data &&
      toState.data.auth &&
      !window.sessionStorage["userInfo"]
    ) {
      event.preventDefault();
      window.location.href = "#login";
    }

    if (
      !toState &&
      !toState.data &&
      !toState.data.auth &&
      window.sessionStorage["userInfo"]
    ) {
      event.preventDefault();
      window.location.href = "#dashboard";
    }
  });
});
