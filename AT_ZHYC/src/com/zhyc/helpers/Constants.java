package com.zhyc.helpers;

public class Constants {
	public final static String DEFAULT_INPUT_METHOD = Config.getInstance()
			.getValue(Config.Key.DEFAULT_INPUT_METHOD);
	// 退出界面
	public final static String CONFIRM = "确认";

	// 首页界面
	public final static String ME = "我";
	public final static String SEARCH = "请输入职位或企业";

	// 个人中心界面
	public final static String LOGIN_REGISTER = "登录 / 注册";
	public final static String USER_ACCOUNT = "个人中心";

	// 登录界面
	public final static String COMMON_LOGIN = "普通登录";
	public final static String USERNAME_INPUT = "用户名/手机号/邮箱";
	public final static String PASSWORD_INPUT = "密码";
	public final static String PASSWORD_INPUT_ID = "com.chinahr.android.m:id/common_password";
	public final static String USERNAME = "15032051462";
	public final static String PASSWORD = "test@123";
	public final static String PASSWORD_VISIABLE = "com.chinahr.android.m:id/common_imagecheck";
	public final static String LOGIN = "登 录";

	// 账号设置界面
	public final static String ACCOUNT_SETTING = "账号设置";
	public final static String SIGN_OUT = "退出登录";

	// 搜索界面
	public final static String CANCEL = "取消";
	public final static String SEARCH_TEXT = "维修";
	public final static String CITY = "城市";
	public final static String NO_SEARCH_RESULT = "没有找到相关的职位…";
}
