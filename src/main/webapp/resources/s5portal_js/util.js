function showComponet(id) {

}

function hideComponet(id) {

}

var dtd = $.Deferred(); // 新建一个deferred对象
var wait = function(dtd) {
	var tasks = function() {
		alert("执行完毕！");
		dtd.resolve();
	};
	setTimeout(tasks, 5000);
	return dtd;
};
