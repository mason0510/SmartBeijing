/**
 * 创建日期:2015-12-24上午10:35:58
 * 作者:itheima
 * 描述:TODO
 */
package com.itcast.news03.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.itcast.news03.R;
import com.itcast.news03.utils.CacheUtils;
import com.viewpagerindicator.CirclePageIndicator;

/**
 * @author itheima 引导界面 引导用户熟悉功能
 * 使用第三方库实现小圆点
 */
public class GuideActivity1 extends Activity {

	private ViewPager vpivewPager;
	private Button btn_start;
	private MyAdapter adapter;
	private CirclePageIndicator circle_indicator;

	/***
	 * 方法
	 * 
	 * @param savedInstanceState
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guide1);
		initView();
		initListener();
		initData();
	}

	/***
	 * 方法2:初始化页面的所有的监听器
	 */
	private void initListener() {
		// TODO Auto-generated method stub

		//配置监听器 才能根据滑动页面指示
		circle_indicator.setOnPageChangeListener(new OnPageChangeListener() {
			/*** 方法页面切换完成
			@param position下标
			*/
			@Override
			public void onPageSelected(int position) {
				//只有第三页才显示开始按钮
				if(position==adapter.getCount()-1)
				{
					btn_start.setVisibility(View.VISIBLE);
				}else
				{
					btn_start.setVisibility(View.GONE);
				}
				
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				
			}
		});
		btn_start.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				//保存变量值
				CacheUtils.saveString(getApplicationContext(), "isFirstShow", "false");
				startActivity(new Intent(GuideActivity1.this,MainActivity.class));
				finish();
			}
		});
	}

	/***
	 * 方法1.初始化页面所有的控件
	 */
	private void initView() {
		vpivewPager = (ViewPager) findViewById(R.id.vp_viewpager);
		btn_start = (Button) findViewById(R.id.btn_start);
		circle_indicator = (CirclePageIndicator) findViewById(R.id.circle_indicator);
		adapter = new MyAdapter();
		// 设置内容给高级控件
		vpivewPager.setAdapter(adapter);
		
		//给指示器设置ViewPager
		circle_indicator.setViewPager(vpivewPager);
		//滑动监听器

	}

	/**
	 * 显示图片的内容 适配器
	 */
	private class MyAdapter extends PagerAdapter {
		private int[] imageResIds = new int[] { R.drawable.guide_1, R.drawable.guide_2, R.drawable.guide_3 };

		/***
		 * 方法 页数据
		 * 
		 * @return
		 */
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return imageResIds.length;
		}

		/***
		 * 方法 官方建议
		 * 
		 * @param arg0
		 * @param arg1
		 * @return
		 */
		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 == arg1;
		}

		/***
		 * 方法 显示页面
		 * 
		 * @param container
		 *            指ViewPager
		 * @param position
		 *            当前页面对应的下标
		 * @return
		 */
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// // TODO Auto-generated method stub
			// return super.instantiateItem(container, position); 一定要求 删除该代码
			// super.默认抛出异常
			ImageView itemView = new ImageView(getBaseContext());
			itemView.setBackgroundResource(imageResIds[position]);
			// 添加显示
			container.addView(itemView);
			return itemView;
		}

		/***
		 * 方法:移除显示
		 * 
		 * @param container
		 * @param position
		 * @param object
		 *            页面控件 ImageView
		 */
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// super.destroyItem(container, position, object);
			container.removeView((View) object);
		}
	}
	/***
	 * 方法
	 */
	private void initData() {

	}

}
