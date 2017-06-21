/**
 * 创建日期:2015-12-24上午11:49:14
 * 作者:itheima
 * 描述:TODO
 */
package com.itcast.news03.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * @author itheima
 *保存变量
 */
public class CacheUtils {
	
	/*** 方法
	@param context
	@param key
	@param value
	*/
	public  static void saveString(Context context,String key,String value)
	{
		//获取编辑器
		SharedPreferences sharedPreferences = context.getSharedPreferences("config.xml", Context.MODE_PRIVATE);
		Editor edit = sharedPreferences.edit();
		edit.putString(key, value);
		//提交
		edit.commit();
	}
	/*** 方法获取变量值
	@param context
	@param key
	@return
	*/
	public  static String getString(Context context,String key)
	{
		//获取编辑器
		SharedPreferences sharedPreferences = context.getSharedPreferences("config.xml", Context.MODE_PRIVATE);
		return sharedPreferences.getString(key, "");
	}

}
