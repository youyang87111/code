/**
 * 屏蔽掉前进、后退、刷新键等
 * 
 * */
document.onkeydown = function(){
	// 屏蔽 Alt+ 方向键 ←  屏蔽 Alt+ 方向键 →
	if((window.event.altKey) && ((window.event.keyCode==37) || (window.event.keyCode==39))){
		event.returnValue = false;
	}
	// 屏蔽退格删除键  屏蔽 F5 刷新键  Ctrl + R
	if((event.keyCode==8) || (event.keyCode==116) || (event.ctrlKey && event.keyCode==82)){
		event.keyCode = 0;
		event.returnValue = false;
	}
}
