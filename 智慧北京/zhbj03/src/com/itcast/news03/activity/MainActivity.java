package com.itcast.news03.activity;

import android.os.Bundle;
import android.view.Display;

import com.itcast.news03.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

/**
 * @author itheima 使用了SlidingMenu控件
 */
public class MainActivity extends SlidingFragmentActivity {

	/***
	 * 方法 修改protected public
	 * 
	 * @param savedInstanceState
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 显示中间的视图 将视图放到SlidingMenu中间
		setContentView(R.layout.activity_center);
		initView();
	}

	/***
	 * 方法 初始化页面的所有的控件
	 */
	private void initView() {
		// 放置左侧视图在左侧菜单里
		setBehindContentView(R.layout.activity_left_menu);
		// 获取侧滑控件
		SlidingMenu slidingMenu = super.getSlidingMenu();
		// slidingMenu.setSecondaryMenu(R.layout.activity_right_menu);
		// 核心参数1 Mode
		slidingMenu.setMode(SlidingMenu.LEFT);
		// slidingMenu.setMode(SlidingMenu.LEFT_RIGHT);
		// SlidingMenu.LEFT：左侧有菜单
		// SlidingMenu.LEFT_RIGHT:左侧有菜单同时右侧也有菜单
		// 核心参数2 Touch_mode
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
		// SlidingMenu.TOUCHMODE_FULLSCREEN 范围全屏
		// SlidingMenu.TOUCHMODE_NONE 范围为0
		// SlidingMenu.TOUCHMODE_MARGIN 范围为菜单间距
		// 获取屏幕的宽高
		// Display屏幕的显示对象：宽+高..
		Display defaultDisplay = getWindowManager().getDefaultDisplay();
		int centerWidth = (int) (defaultDisplay.getWidth() * 2f / 3);
		// BehindOffset =屏幕宽度-左侧菜单的宽度
		slidingMenu.setBehindOffset(centerWidth);

	}

}
