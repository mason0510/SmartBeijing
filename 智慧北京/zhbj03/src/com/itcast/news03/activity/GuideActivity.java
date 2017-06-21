/**
 * 创建日期:2015-12-24上午10:35:58
 * 作者:itheima
 * 描述:TODO
 */
package com.itcast.news03.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

import com.itcast.news03.R;
import com.itcast.news03.utils.CacheUtils;

/**
 * @author itheima 引导界面 引导用户熟悉功能 自已实现小圆点底层
 */
public class GuideActivity extends Activity {

	private ViewPager vpivewPager;
	private Button btn_start;
	private MyAdapter adapter;
	private LinearLayout ll_dots;

	/***
	 * 方法
	 * 
	 * @param savedInstanceState
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guide);
		initView();
		initListener();
		initData();
	}

	/***
	 * 方法2:初始化页面的所有的监听器
	 */
	private void initListener() {
		// TODO Auto-generated method stub

		// 配置监听器 才能根据滑动页面指示
		vpivewPager.setOnPageChangeListener(new OnPageChangeListener() {
			/***
			 * 方法页面切换完成
			 * 
			 * @param position下标
			 */
			@Override
			public void onPageSelected(int position) {
				// 只有第三页才显示开始按钮
				if (position == adapter.getCount() - 1) {
					btn_start.setVisibility(View.VISIBLE);
				} else {
					btn_start.setVisibility(View.GONE);
				}

			}

			/***
			 * 方法 监听控件滑动中
			 * 
			 * @param currPosition
			 *            当前页面的下标 ++currPosition;
			 * @param scorllPercent
			 *            页面切换的 百份比 0.3 0.5f
			 * @param scorllPixeds
			 *            滑动像素
			 */
			@Override
			public void onPageScrolled(int currPosition, float scorllPercent, int scorllPixeds) {
				Log.i("wzx", "onPageScrolled scorllPercent" + scorllPercent+" currPosition"+currPosition);
				// 小红点放在相对布局里面 类型RelativeLayout.LayoutParams
				RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) iv_red_dot.getLayoutParams();
				//currPosition处理了左滑为减  右滑为加
				int newRedLeftMargin=(int) (currPosition*mDotDistance+scorllPercent*mDotDistance);
				params.leftMargin=newRedLeftMargin;
				iv_red_dot.setLayoutParams(params);

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});
		btn_start.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// 保存变量值
				CacheUtils.saveString(getApplicationContext(), "isFirstShow", "false");
				startActivity(new Intent(GuideActivity.this, MainActivity.class));
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
		iv_red_dot = (ImageView) findViewById(R.id.iv_red_dot);
		adapter = new MyAdapter();
		// 设置内容给高级控件
		vpivewPager.setAdapter(adapter);

		ll_dots = (LinearLayout) findViewById(R.id.ll_gray_dots);
		for (int i = 0; i < adapter.getCount(); i++) {
			ImageView grayDot = new ImageView(this);
			// 显示灰色效果
			grayDot.setBackgroundResource(R.drawable.shap_gray_dot);
			// 添加进线性布局
			ll_dots.addView(grayDot);
			// layout_marginLeft
			// XXXX.LayoutParams用来java代码布局控件
			// 选择哪个类型看一下控件放到哪种布局里面
			if (i > 0) {
				LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,// 宽
						LinearLayout.LayoutParams.WRAP_CONTENT);// 高
				params.leftMargin = 10;
				grayDot.setLayoutParams(params);
			}
		}

		// 了解View的工作原理？
		// 需要监听onLayout执行完毕 如果该方法不执行 未执行完 getLeft..都为0
		// OnGlobalLayoutListener监听布局完毕
		ll_dots.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
			/***
			 * 方法 布局完
			 */
			@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
			@Override
			public void onGlobalLayout() {
				// TODO Auto-generated method stub
				// 获取任意两个点
				// getChildAt获取布局上面的子控件
				int xDot2 = ll_dots.getChildAt(1).getLeft();
				int xDot1 = ll_dots.getChildAt(0).getLeft();
				mDotDistance = xDot2 - xDot1;
				Log.i("wzx", "mDotDistance:" + mDotDistance);
				// 使用该监听器 已经获取了点的间距
				ll_dots.getViewTreeObserver().removeOnGlobalLayoutListener(this);
			}
		});

	}

	/** 点的间距 **/
	private int mDotDistance;
	private ImageView iv_red_dot;

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
