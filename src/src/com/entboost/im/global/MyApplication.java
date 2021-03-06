package com.entboost.im.global;

import net.yunim.service.Entboost;
import android.app.Application;
import android.graphics.Bitmap;

import com.entboost.Log4jLog;
import com.entboost.im.R;
import com.entboost.im.WelcomeActivity;
import com.entboost.im.exception.CrashHandler;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration.Builder;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

public class MyApplication extends Application{

	/** The tag. */
	private static String LONG_TAG = MyApplication.class.getName();
	
	//应用对象
	private static MyApplication myInstance;
	//欢迎页面
	private WelcomeActivity welcomeActivity;

	public static long appid = 278573612908l;

	public static String appkey = "ec1b9c69094db40d9ada80d657e08cc6";

	private boolean showNotificationMsg = false;

	private DisplayImageOptions defaultImgOptions; //默认提报加载选项
	private DisplayImageOptions userImgOptions;	 //用户头像图标加载选项
	private DisplayImageOptions funcInfoImgOptions; //内置应用图标加载选项

	public WelcomeActivity getWelcomeActivity() {
		return welcomeActivity;
	}

	public void setWelcomeActivity(WelcomeActivity welcomeActivity) {
		this.welcomeActivity = welcomeActivity;
	}

	public DisplayImageOptions getDefaultImgOptions() {
		return defaultImgOptions;
	}

	public DisplayImageOptions getUserImgOptions() {
		return userImgOptions;
	}

	public DisplayImageOptions getFuncInfoImgOptions() {
		return funcInfoImgOptions;
	}

	public boolean isShowNotificationMsg() {
		return showNotificationMsg;
	}

	public void setShowNotificationMsg(boolean showNotificationMsg) {
		this.showNotificationMsg = showNotificationMsg;
	}

	@Override
	public void onTerminate() {
		welcomeActivity = null;
		myInstance = null;
		
		super.onTerminate();
		
//		ActivityManager am = (ActivityManager) this.getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
//		am.killBackgroundProcesses(getPackageName());
//		System.exit(0);
	}

	@Override
	public void onCreate() {
		super.onCreate();
		myInstance = this;
		initEbConfig("MyApplication");
		Log4jLog.i(LONG_TAG, "My Application Created");
	}

	public void initEbConfig(String type) {
		Log4jLog.i(LONG_TAG, "initEbConfig start, type=" + type);
		
		CrashHandler crashHandler = CrashHandler.getInstance();
		crashHandler.init(getApplicationContext());
		
		Log4jLog.i(LONG_TAG, "initEbConfig 1");
		Entboost.setServiceListener(crashHandler);
		Log4jLog.i(LONG_TAG, "initEbConfig 2");
		Entboost.init(getApplicationContext());
		Log4jLog.i(LONG_TAG, "initEbConfig 3");
		Entboost.showSotpLog(false);
		Log4jLog.i(LONG_TAG, "initEbConfig 4");
		
		Builder imgConfig = new ImageLoaderConfiguration.Builder(this);
		imgConfig.threadPriority(Thread.NORM_PRIORITY - 2);// 设置线程的优先级
		imgConfig.diskCacheFileNameGenerator(new Md5FileNameGenerator());// 设置缓存文件的名字
		imgConfig.diskCacheSize(50 * 1024 * 1024); // 50 MiB
		imgConfig.denyCacheImageMultipleSizesInMemory();// 当同一个Uri获取不同大小的图片，缓存到内存时，只缓存一个。默认会缓存多个不同的大小的相同图片
		imgConfig.tasksProcessingOrder(QueueProcessingType.LIFO);// 设置图片下载和显示的工作队列排序
		ImageLoader.getInstance().init(imgConfig.build());
		
		defaultImgOptions = createImgOptions(R.drawable.entboost_logo, R.drawable.entboost_logo, R.drawable.entboost_logo);
		userImgOptions = createImgOptions(R.drawable.default_user, R.drawable.default_user, R.drawable.default_user);
		funcInfoImgOptions = createImgOptions(R.drawable.default_app, R.drawable.default_app, R.drawable.default_app);
	}
	
	//创建图标加载选项
	private DisplayImageOptions createImgOptions(int loadingResid, int emptyResid, int failResid) {
		return new DisplayImageOptions.Builder()
			.showImageOnLoading(loadingResid).showImageForEmptyUri(emptyResid).showImageOnFail(failResid)
			.cacheInMemory(true).cacheOnDisk(true).considerExifParams(true).bitmapConfig(Bitmap.Config.RGB_565).build();
	}

	public static MyApplication getInstance() {
		return myInstance;
	}

}
