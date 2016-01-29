package com.bdqb.test;

import android.graphics.Point;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.bdqb.helpers.CommonHelper;
import com.bdqb.helpers.Constants;
import com.uiautotest.utils.UiAutoTestCase;

/**
 * @author Zhang Yajing
 *
 */
public class BDQB_Test extends UiAutoTestCase {

	CommonHelper commonHelper;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		commonHelper = new CommonHelper(this);
		commonHelper.backToIdle();
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		// TODO Auto-generated method stub
		try {
			commonHelper.backToIdle();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * Check Point: 下订单.
	 *
	 * Precondition: 已用百度账户登录app.
	 *
	 * Steps: Step1: 通过activity启动app. Expect Result: App能正常启动.
	 * 
	 * Step2: 点击首页中外卖按钮. Expect Result: 跳转到外卖界面.
	 * 
	 * Step3: 选择外卖首页的居于屏幕中间的一家餐厅. Expect Result: 进入餐厅主页.
	 * 
	 * Step4: 选择餐厅中的一份美食. Expect Result: 添加到购物车.
	 * 
	 * Step5: 点击"选好了"按钮. Expect Result: 跳转到订单界面.
	 * 
	 * Step6: 点击"确认下单". Expect Result: 跳转到银行支付界面.
	 *
	 * Postcondition: 无.
	 * 
	 * @throws Exception
	 */
	public void testOrder() throws Exception {

		// Precondition: 已用百度账户登录app.
		// 由于登录时有验证码，所以只能先手动登录账号，再或者用手机号码动态登录，也是可以实现的，只是比较复杂而已。
		// Step1: 通过activity启动app.
		// Expect Result: App能正常启动.
		launchBdqbApp();

		// Step2: 点击首页中外卖按钮.
		// Expect Result: 跳转到外卖界面.
		// 由于抓取不到外卖相关的空间，故使用文本百度家族的坐标，算出相对外卖坐标
		Point p = getCoordinateByText(Constants.BAIDUJIAZU);
		System.out.println(p.x + "+" + p.y);
		clickPoint(p.x, p.y + 100);
		sleep(5000);
		assertTrue("无法跳转到外卖界面", !waitForTextExists(Constants.BAIDUJIAZU, 7000));

		// 选择地址界面
		if (waitForTextExists(Constants.SELECT_ADDRESS, 2000)) {

			// (360,170)/(720,1280)地址输入框
			clickPoint(toScreenX(0.5f), toScreenY(0.13f));
			sleep(1000);
			enterText(Constants.ADDRESS);

			// (360,270)/(720,1280)匹配到的第一个地址
			clickPoint(toScreenX(0.5f), toScreenY(0.21f));
			sleep(5000);
		}

		// Step3: 选择外卖首页的居于屏幕中间的一家餐厅.
		// Expect Result: 进入餐厅主页.
		clickPoint(toScreenX(0.5f), toScreenY(0.5f));
		sleep(5000);

		// Step4: 选择餐厅中的一份美食.
		// Expect Result: 添加到购物车.
		// (677,709)美食右边的添加按钮
		for (int i = 0; i < 5; i++) {
			clickPoint(toScreenX(0.94f), toScreenY(0.55f));
			sleep(2000);
		}

		// Step5: 点击"选好了"按钮.
		// Expect Result: 跳转到订单界面.
		// (626,1224)右下角的"选好了"按钮
		clickPoint(toScreenX(0.87f), toScreenY(0.96f));
		sleep(5000);

		// Step6: 点击"确认下单".
		// Expect Result: 完成下单.
		// (626,1224)右下角的"选好了"按钮
		clickPoint(toScreenX(0.87f), toScreenY(0.96f));
		sleep(8000);

		takeScreenshot("/sdcard/uiAutoTest/");

		// Postcondition: 无.
		System.out.println("testOrder_passed");
	}

	/*
	 * 打开App.
	 */
	public void launchBdqbApp() throws UiObjectNotFoundException {
		launchApp("com.baidu.wallet", "com.baidu.wallet.home.MainActivity");
		sleep(5000);
		if (waitForTextExists(Constants.NEW_VERSION, 2000)) {
			clickText(Constants.LATER, false);
			sleep(2000);
		}
		if (!waitForTextExists(Constants.BAIDU_WALLET, 2000)) {
			for (int i = 0; i < 5; i++) {
				scroll("left");
				sleep(1000);
			}
			clickText(Constants.ENJOY, false);
			sleep(1000);
		}
		assertPackageAppear("com.baidu.wallet");
	}

	/*
	 * 用百度账户登录App.
	 */
	public void login(String username, String passwd) throws Exception {
		launchBdqbApp();

		// 检查是否有升级提示
		if (!waitForTextExists(Constants.LOGIN, 2000)) {
			return;
		}

		// 点击左上角的登录按钮
		clickText(Constants.LOGIN, true);
		assertTrue("无法跳转到登录界面",
				waitForTextExists(Constants.LOGIN_AS_BAIDU_ACCOUNT, 2000));

		// 输入用户名
		clickText(Constants.USERNAME_INPUT, false);
		sleep(1000);
		enterText(Constants.BAIDU_ACCOUNT);
		sleep(1000);

		// 输入密码
		clickText(Constants.PASSWORD_INPUT, false);
		sleep(1000);
		enterText(Constants.BAIDU_PASSWORD);
		sleep(1000);

		// 点击登录
		clickText(Constants.LOGIN, true);
		sleep(1000);
	}
}
