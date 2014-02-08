$(function() {
	
	var startDate;
	var endDate;

	var selectCurrentWeek = function() {
		window.setTimeout(function() {
			$('.date-picker').find('.ui-datepicker-current-day a').addClass('ui-state-active');
		}, 1);
	};
	
	$("#radio").buttonset();
	$("#chart01MainRadio").buttonset();	
	$("#chart01SubRadio").buttonset();	

	$('.date-picker').datepicker(
			{
				showOtherMonths : true,
				selectOtherMonths : true,
				firstDay : 1,
				onSelect : function(dateText, inst) {
					var date = $(this).datepicker('getDate');
					var flg = $("#radio :radio:checked").val();
					if ( flg == 1) {
						startDate = new Date(date.getFullYear(), date.getMonth(),
								date.getDate() - date.getDay() + 1);
						endDate = new Date(date.getFullYear(), date.getMonth(),
								date.getDate() - date.getDay() + 7);		
					} else {						
						startDate = new Date(date.getFullYear(), date.getMonth(), 1);
						endDate = new Date(date.getFullYear(), date.getMonth() + 1, 0);
					}
					var dateFormat = "yy-mm-dd";
					$('#startDate').text(
							$.datepicker.formatDate(dateFormat, startDate,
									inst.settings));
					$('#endDate').text(
							$.datepicker.formatDate(dateFormat, endDate,
									inst.settings));

					selectCurrentWeek();				
					init();
				},
				beforeShowDay : function(date) {
					var cssClass = '';
					if (date >= startDate && date <= endDate)
						cssClass = 'ui-datepicker-current-day';
					return [ true, cssClass ];
				},
				onChangeMonthYear : function(year, month, inst) {
					selectCurrentWeek();
				}
			});

	$("#btnDatePicker").click(function(){
		$(".date-picker").datepicker("show");
	});
	
	$('.date-picker .ui-datepicker-calendar tr').bind('mousemove', function() {
		$(this).find('td a').addClass('ui-state-hover');
	});
	$('.date-picker .ui-datepicker-calendar tr').bind('mouseleave', function() {
		$(this).find('td a').removeClass('ui-state-hover');
	});
	
	if ( startDate == undefined ) {
		console.log($("#radio :radio:first-child").attr("checked"));
		console.log($("#radio :radio:last-child").attr("checked"));
		var date = new Date();		
		var dateFormat = "yy-mm-dd";
		startDate = new Date(date.getFullYear(), date.getMonth(),
				date.getDate() - date.getDay() + 1);
		endDate = new Date(date.getFullYear(), date.getMonth(),
				date.getDate() - date.getDay() + 7);
		$('#startDate').text(
				$.datepicker.formatDate(dateFormat, startDate));
		$('#endDate').text(
				$.datepicker.formatDate(dateFormat, endDate));	
	}
});