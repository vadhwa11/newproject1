(function() {
    "use strict";
//For top sub menu (look others menu)
$(function() {
  $(".subnavbar").find("li").each(function(i) {
    var mod = i % 3;
    if (mod === 2) {
      $(this).addClass("subnavbar-open-right");
    }
  });
});
})();
