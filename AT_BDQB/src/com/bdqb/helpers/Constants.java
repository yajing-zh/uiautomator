package com.bdqb.helpers;

public class Constants {
	public final static String DEFAULT_INPUT_METHOD = Config.getInstance()
			.getValue(Config.Key.DEFAULT_INPUT_METHOD);

	public final static String BAIDU_WALLET = "百度钱包";
	// 版本更新界面
	public final static String NEW_VERSION = "版本更新";
	public final static String LATER = "以后再说";

	// 新版本导航
	public final static String ENJOY = "立即体验";

	// // 退出界面
	// public final static String CONFIRM = "确认";

	// 首页界面
	public final static String LOGIN = "登录";
	public final static String BAIDUJIAZU = "百度家族";
	public final static String ME = "我";

	// 选择地址界面
	public final static String SELECT_ADDRESS = "选择地址";
	public final static String ADDRESS = Config.getInstance().getValue(
			Config.Key.DEFAULT_INPUT_METHOD);

	// 登录界面
	public final static String LOGIN_AS_BAIDU_ACCOUNT = "登录百度账号";
	public final static String BAIDU_ACCOUNT = Config.getInstance().getValue(
			Config.Key.BAIDU_ACCOUNT);
	public final static String BAIDU_PASSWORD = Config.getInstance().getValue(
			Config.Key.BAIDU_PASSWORD);
	public final static String USERNAME_INPUT = "账号";
	public final static String PASSWORD_INPUT = "密码";

	// 付款界面
	public final static String ABANDON = "放弃付款";

}
