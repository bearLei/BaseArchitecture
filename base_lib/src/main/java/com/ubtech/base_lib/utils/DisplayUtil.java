package com.ubtech.base_lib.utils;

import android.content.Context;
import android.util.DisplayMetrics;

public class DisplayUtil {

	/**
	 *
	 * @param totalWidth RecyclerView 除去paddingLeft/paddingRight的宽度
	 * @param itemWidth  需要的宽度(实际宽度和屏幕大小以及itemWidth有关)
	 * @param margin     item的左右间距
	 * @return spanCount = result[0] itemWidth = result[1]，然后可以根据itemWidth，margin和长宽比计算itemHeight
	 */
	public static int[] getSpanCountAndItemWidth(int totalWidth, int itemWidth, int margin){
		int[] result = new int[2];
		int percent = (int) (0.8 * (itemWidth + margin)); // 如果余数大于0.8个Item宽度则增加一个Item
		int spanCount = (totalWidth + margin) / (itemWidth + margin); // 大概的一个spanCount
		int mod = (totalWidth + margin) % (itemWidth + margin);
		if(mod >= percent){
			spanCount++;
		}
		result[0] = spanCount; // 列数
		result[1] = (totalWidth - (spanCount + 1) * margin) / spanCount; // item的宽度(左右两边添加了间距)
		return result;
	}

	/**
	 * 将px值转换为dip或dp值，保证尺寸大小不变
	 * 
	 * @param pxValue
	 * @param scale
	 *            （DisplayMetrics类中属性density）
	 * @return
	 */
	public static int px2dip(float pxValue, float scale) {
		return (int) (pxValue / scale + 0.5f);
	}

	public static int px2dip(Context context, float pxValue){
		float scale = context.getResources()
				.getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}


	/**
	 * 将dip或dp值转换为px值，保证尺寸大小不变
	 * 
	 * @param dipValue
	 * @param scale
	 *            （DisplayMetrics类中属性density）
	 * @return
	 */
	public static int dip2px(float dipValue, float scale) {
		return (int) (dipValue * scale + 0.5f);
	}

	public static int dip2px(Context context, float dpValue) {
		if(context != null) {
			final float scale = context.getResources().getDisplayMetrics().density;
			return (int) (dpValue * scale + 0.5f);
		}
		return 0;
	}

	/**
	 * 将px值转换为sp值，保证文字大小不变
	 * 
	 * @param pxValue
	 * @param fontScale
	 *            （DisplayMetrics类中属性scaledDensity）
	 * @return
	 */
	public static int px2sp(float pxValue, float fontScale) {
		return (int) (pxValue / fontScale + 0.5f);
	}

	public static int px2sp(Context context, float pxValue) {
		float fontScale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / fontScale + 0.5f);
	}

	/**
	 * 将sp值转换为px值，保证文字大小不变
	 * 
	 * @param spValue
	 * @param fontScale
	 *            （DisplayMetrics类中属性scaledDensity）
	 * @return
	 */
	public static int sp2px(float spValue, float fontScale) {
		return (int) (spValue * fontScale + 0.5f);
	}

	public static int sp2px(Context context, float spValue){
		float fontScale = context.getResources().getDisplayMetrics().density;
		return (int) (spValue * fontScale + 0.5f);
	}

	/**
	 * 
	 * @Title: getScreenWidth  
	 * @Description: 获取屏幕的宽度，单位像素，注意出错时返回0
	 * @param @param context
	 * @param @return      
	 * @return int  
	 * @throws
	 */
	public static int getScreenWidth(Context context) {

		DisplayMetrics dm = new DisplayMetrics();
		dm = context.getResources().getDisplayMetrics();
		if (dm == null)
			return 0;
		return dm.widthPixels;
	}

	/**
	 * 
	 * @Title: getScreenHeight  
	 * @Description: 获取屏幕高度，单位像素  
	 * @param @param context
	 * @param @return      
	 * @return int  
	 * @throws
	 */
	public static int getScreenHeight(Context context) {
		DisplayMetrics dm = new DisplayMetrics();
		dm = context.getResources().getDisplayMetrics();
		if (dm == null)
			return 0;
		return dm.heightPixels;
	}

	public static int getDimenPixelSize(Context context, int dimenResId) {
		return context.getResources().getDimensionPixelSize(dimenResId);
	}

//	public static int getScreenWidth() {
//		DisplayMetrics dm = new DisplayMetrics();
//		dm = MyApplication.getApplication().getResources().getDisplayMetrics();
//		if (dm == null)
//			return 0;
//		return dm.widthPixels;
//	}
//
//	public static int getScreenHeight() {
//		DisplayMetrics dm = new DisplayMetrics();
//		dm =MyApplication.getApplication().getResources().getDisplayMetrics();
//		if (dm == null)
//			return 0;
//		return dm.heightPixels;
//	}
//
//	public static int sp2px(float spValue) {
//		float fontScale = MyApplication.getApplication()
//				.getResources().getDisplayMetrics().density;
//		return (int) (spValue * fontScale + 0.5f);
//	}
//
//	public static int px2sp(float pxValue) {
//		float fontScale = MyApplication.getApplication()
//				.getResources().getDisplayMetrics().density;
//		return (int) (pxValue / fontScale + 0.5f);
//	}
//
//	public static int dip2px(float dpValue) {
//		final float scale = MyApplication.getApplication()
//				.getResources().getDisplayMetrics().density;
//		return (int) (dpValue * scale + 0.5f);
//	}
//
//	public static int px2dip(float pxValue) {
//		float scale = MyApplication.getApplication()
//				.getResources().getDisplayMetrics().density;
//		return (int) (pxValue / scale + 0.5f);
//	}

//	/**
//	 * 
//	 * @Title: getScreenInches  
//	 * @Description: 获取屏幕尺寸，貌似很多问题，不要用 
//	 * @param @param activity
//	 * @param @return      
//	 * @return double  
//	 * @throws
//	 */
//	@Deprecated
//	public static double getScreenInches(Activity activity){
//		Point point = new Point();  
//		activity.getWindowManager().getDefaultDisplay().getSize(point);
//		DisplayMetrics dm = activity.getResources().getDisplayMetrics();  
//		double x = Math.pow(point.x/ dm.xdpi, 2);  
//		double y = Math.pow(point.y / dm.ydpi, 2);  
//		return Math.sqrt(x + y);  
//	}

}









