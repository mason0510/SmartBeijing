/**
 * 创建日期:2015-12-24上午9:29:10
 * 作者:itheima
 * 描述:TODO
 */
package com.itcast.news03.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import com.itcast.news03.R;
import com.itcast.news03.utils.CacheUtils;

/**
 * @author itheima 闪屏界面 control 开发一个Activity 必须要有一个<activity
 */
public class SplashActivity extends Activity {

	private AnimationSet animationSet;

	/***
	 * 方法
	 * 
	 * @param savedInstanceState
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		// Ctrl+T

		initView();
		initListeners();

	}



	/***
	 * 方法 1.初始化页面控件
	 */
	private void initView() {
		RelativeLayout rl_root = (RelativeLayout) findViewById(R.id.rl_root);
		// TODO Auto-generated method stub//
		// AnimationSet:动画集合 同时播放动画
		// new AnimationSet(shareInterpolator:加速)
		
		
		animationSet = new AnimationSet(true);
		// 旋转RoateAnimation
		RotateAnimation rotateAnimation = new RotateAnimation(0, 360,// 开始角度 结束
																		// 角度
				RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);// pivot中心
																								// XType
																								// pivotYType
																								// pivotXValue倍数
		rotateAnimation.setDuration(3000);
		rotateAnimation.setFillAfter(true);
		// 透明度动画
		// AlphaANimation
		// new AlphaAnimation(fromAlpha,开始透明度 toAlpha 结束)
		AlphaAnimation alphaAnimation = new AlphaAnimation(0.5f, 1.0f);
		alphaAnimation.setDuration(3000);// 时长
		alphaAnimation.setFillAfter(true);// 保留动画结束状态

		// 缩放
		// RELATIVE_TO_SELF:1 倍大小
		// ScaleAnimation
		ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1f,// x坐标
				0, 1f, // y坐标
				ScaleAnimation.RELATIVE_TO_SELF, 0.5f, ScaleAnimation.RELATIVE_TO_SELF, 0.5f);

		scaleAnimation.setDuration(3000);// 时长
		scaleAnimation.setFillAfter(true);// 保存动态结束后的状态

		animationSet.addAnimation(alphaAnimation);
		animationSet.addAnimation(rotateAnimation);
		animationSet.addAnimation(scaleAnimation);
		rl_root.startAnimation(animationSet);

	}
	
	/*** 方法2:初始页面的控件或者对象的监听器
	 * 获取事件 动画结束的事件
	
	*/
	private void initListeners() {
		
//		只需要知道有这么一个监听器就行了 不需要记方法
		animationSet.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {
				
			}
			@Override
			public void onAnimationRepeat(Animation animation) {
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				Log.i("wzx", "onAnimationEnd--");
//				Intent:意图 》1.打开界面或者服务  》2.传值Map put get 》3.广播
//				isFirstShow
				String isFirstShow=CacheUtils.getString(SplashActivity.this, "isFirstShow");
				Class<?> page="false".equals(isFirstShow)?MainActivity.class:GuideActivity.class;
				startActivity(new Intent(SplashActivity.this,page));
				finish();
			}
		});
		
	}
}
