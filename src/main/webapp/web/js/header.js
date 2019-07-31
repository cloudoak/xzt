$(function () {
	function toggleNavTable() {
		if ($(window).width() < 1109) {
			$('#navTable').addClass('nav-table');
		}
		else {
			$('#navTable').removeClass('nav-table');
		}
	}
	$(window).resize(function () {
		toggleNavTable();
	});
	toggleNavTable();
	$('#collapsed').click(function () {
		$('#navTable').toggleClass('active');
		$(this).toggleClass('close');
	});
});