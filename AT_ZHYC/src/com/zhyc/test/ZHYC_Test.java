package com.zhyc.test;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.uiautotest.utils.UiAutoTestCase;
import com.zhyc.helpers.CommonHelper;
import com.zhyc.helpers.Constants;

/**
 * @author Zhang Yajing
 *
 */
public class ZHYC_Test extends UiAutoTestCase {

	CommonHelper commonHelper;
	public boolean needSignOut;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		commonHelper = new CommonHelper(this);
		commonHelper.backToIdle();
		needSignOut = false;
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		// TODO Auto-generated method stub
		try {
			commonHelper.backToIdle();
			if (needSignOut) {
				signOut();
				commonHelper.backToIdle();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * Check Point: 成功打开app.
	 *
	 * Precondition: 无.
	 *
	 * Steps: Step1: 通过activity启动app. Expect Result: App能正常启动.
	 *
	 * Postcondition: 无.
	 *
	 * @throws Exception
	 */
	public void testLaunchApp() throws Exception {

		// Precondition: 无
		// Step1: 通过activity启动app.
		// Expect Result: App能正常启动.
		launchZhycApp();
		sleep(1000);

		// Postcondition: 无.
		System.out.println("testLaunchApp_passed");
	}

	/**
	 * Check Point: 以普通登录身份登录app.
	 *
	 * Precondition: 无.
	 *
	 * Steps: Step1: 通过activity启动app. Expect Result: App能正常启动.
	 * 
	 * Step2: 点击首页中右下角的按钮“我”. Expect Result: 跳转到个人中心界面.
	 * 
	 * Step3: 点击按钮“登录/注册”. Expect Result: 跳转到登录界面.
	 * 
	 * Step4: 点击按钮“普通登录”. Expect Result: 跳转到普通登录界面
	 * 
	 * Step5: 输入用户名密码，并点击登录按钮. Expect Result: 能正常登录.
	 *
	 * Postcondition: 退出登录.
	 *
	 * @throws Exception
	 */
	public void testLoginByCommonLogin() throws Exception {
		needSignOut = true;

		// Precondition: 无.
		// Step1: 通过activity启动app.
		// Expect Result: App能正常启动.
		launchZhycApp();

		// Step2: 点击首页中右下角的按钮“我”.
		// Expect Result: 跳转到个人中心界面.
		clickText(Constants.ME, true);
		sleep(1000);
		assertTrue("无法跳转到个人中心界面!",
				waitForTextExists(Constants.USER_ACCOUNT, 2000));

		// Step3: 点击按钮“登录/注册”.
		// Expect Result: 跳转到登录界面.
		if (!waitForTextExists(Constants.LOGIN_REGISTER, 2000)) {
			return;
		}
		clickText(Constants.LOGIN_REGISTER, true);
		sleep(1000);
		assertTrue("无法跳转到登录界面!",
				waitForTextExists(Constants.COMMON_LOGIN, 2000));

		// Step4: 点击按钮“普通登录”.
		// Expect Result: 跳转到普通登录界面
		clickText(Constants.COMMON_LOGIN, false);
		sleep(1000);
		assertTrue("无法跳转到普通登录界面!",
				waitForTextExists(Constants.USERNAME_INPUT, 2000));

		// Step5: 输入用户名密码，并点击登录按钮.
		// Expect Result: 能正常登录.
		enterText(Constants.USERNAME);
		sleep(1000);
		// UI Automator Viewer捕获不到“密码”字样，故使用ID
		// clickText(Constants.PASSWORD_INPUT, false);
		clickResourceId(Constants.PASSWORD_INPUT_ID);
		sleep(1000);
		enterText(Constants.PASSWORD);
		sleep(1000);
		clickResourceId(Constants.PASSWORD_VISIABLE);
		sleep(1000);
		assertTrue("输入账户名密码失败!", waitForTextExists(Constants.USERNAME, 2000)
				&& waitForTextExists(Constants.PASSWORD, 2000));

		// 点击登录按钮
		clickText(Constants.LOGIN, true);
		sleep(2000);
		assertTrue("Fail to 登录账户",
				waitForTextExists(Constants.USER_ACCOUNT, 2000));

		// Postcondition: 退出登录.
		signOut();

		System.out.println("testLoginByCommonLogin_passed");
	}

	/*
	 * 打开App.
	 */
	public void launchZhycApp() {
		launchApp("com.chinahr.android.m",
				"com.chinahr.android.m.main.MainActivity");
		sleep(5000);
		assertPackageAppear("com.chinahr.android.m");
	}

	/*
	 * 退出App.
	 */
	public void signOut() throws UiObjectNotFoundException {
		launchZhycApp();
		// Step: 点击首页中右下角的按钮“我”.
		// Expect Result: 跳转到个人中心界面.
		clickText(Constants.ME, true);
		sleep(1000);
		assertTrue("无法跳转到个人中心界面!",
				waitForTextExists(Constants.USER_ACCOUNT, 2000));

		// 如果有“登录/注册”按钮，则无需退出登录
		if (waitForTextExists(Constants.LOGIN_REGISTER, 2000)) {
			return;
		}

		// Step: 点击“账号设置”
		// Expect Result: 跳转到账号设置界面.
		clickText(Constants.ACCOUNT_SETTING, true);
		sleep(1000);
		assertTrue("无法跳转到账号设置界面", waitForTextExists(Constants.SIGN_OUT, 2000));

		// Step: 点击“退出登录”
		// Expect Result: 跳转到个人中心界面，且已退出登录.
		clickText(Constants.SIGN_OUT, true);
		sleep(1000);
		assertTrue("Fail to sign out",
				waitForTextExists(Constants.LOGIN_REGISTER, 2000));
	}
}
