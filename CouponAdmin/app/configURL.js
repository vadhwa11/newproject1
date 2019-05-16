//  Contains all URL used as API
// http://122.176.87.170:8080/GSPONE_DEMO/
// https://payworldgsp.com/GST_LIVE/

var apis = "https://app.filegstnow.com/CouponManagement";
// var apis = "http://172.20.3.106:8080/CouponManagement";

// 'getGstr1b2bInvoiceSumUrl': apis+'/gspportal/getInvoiceSummary',
angular.module("myApp").constant("adminConfigURL", {
  loginAPI: apis + "/adminLogin/login",
  DistributorRegister: apis + "/registration/distributorregistration",
  couponGeneration: apis + "/couponservice/generation",
  activeCouponApi: apis + "/couponservice/activecoupon",
  availablecouponApi: apis + "/couponservice/availablecoupon",
  multigenerationCouponApi: apis + "/couponservice/multigeneration",
  getVoucherlistAPI: apis + "/couponservice/availablecoupon",
  bulkAdditionVoucher: apis + "/couponservice/multigeneration",
  distributorsAssignedVouchers: apis + "/couponservice/groupbydistributor", //shows list for assigned vouchers to distributors
  addVoucherAPI: apis + "/couponservice/generation",
  assignVoucherAPI: apis + "/couponservice/assigncoupon",
  assignedtoDistributorAPI: apis + "/couponservice/assigncoupontodistributor",
  registrationSaveUrl: apis + "/couponservice/assigncoupontodistributor",
  endUserRegistrationAPI: apis + "/registration/enduserregistration",
  showAllEndUserAPI: apis + "/couponservice/showAllEndUser",
  distributorLogin: apis + "/adminLogin/distributorMobileLogin",
  getCouponByDistributed: apis +
    "/couponmanagemntservice/getCouponByDistributed"
});
