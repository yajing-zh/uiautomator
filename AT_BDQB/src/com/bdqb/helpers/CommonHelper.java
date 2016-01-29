package com.bdqb.helpers;

import java.io.File;

import jp.jun_nama.test.utf7ime.helper.Utf7ImeHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.uiautotest.utils.UiAutoTestCase;

public class CommonHelper {

	private UiAutoTestCase uiAutoTestCase;

	public CommonHelper(UiAutoTestCase testCase) {
		uiAutoTestCase = testCase;
	}

	public void backToIdle() {
		for (int i = 0; i < 4; i++) {
			uiAutoTestCase.pressKey("back");
			if (uiAutoTestCase.waitForTextExists(Constants.ABANDON, 1000)) {
				try {
					uiAutoTestCase.clickText(Constants.ABANDON, false);
				} catch (UiObjectNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			uiAutoTestCase.pressKey("Home");
		}
	}

	public void pressButton(String Button, int Times) {
		for (int i = 0; i < Times; i++) {
			uiAutoTestCase.pressKey(Button);
		}
	}

	/**
	 * @author Pactera Automation Team
	 *
	 *         Helper Target: drag the point to the bottom.
	 *
	 * @param point
	 *            : The point start to drag.
	 */

	public void dragPointToBottom(Point point) {
		// dragPointToBottom: Drag point to bottom.
		int startX = point.x;
		int startY = point.y;
		int endY = uiAutoTestCase.toScreenY(0.75f);
		uiAutoTestCase.drag(startX, startY, startX, endY, 20);
	}

	/**
	 * Target: Image compare
	 */
	public boolean imageComapre() throws UiObjectNotFoundException {

		String path = "/data/local/tmp/";
		String imageName1 = "image1.png";
		File image1 = new File(path + imageName1);
		uiAutoTestCase.getUiDevice().takeScreenshot(image1, 0.5f, 30);

		String imageName2 = "image2.png";
		File image2 = new File(path + imageName2);
		uiAutoTestCase.getUiDevice().takeScreenshot(image2, 0.5f, 30);

		Bitmap bitmap1 = BitmapFactory.decodeFile(path + imageName1);
		Bitmap bitmap2 = BitmapFactory.decodeFile(path + imageName2);

		boolean isSame = Bitmap.createBitmap(bitmap1, 100, 200, 50, 50).sameAs(
				Bitmap.createBitmap(bitmap2, 100, 200, 50, 50));

		return isSame;
	}

	/**
	 * Target: Compare the time that show the video play progress. The time
	 * format is xx:xx:xx/xx:xx:xx
	 */
	public int timeCompare(String str1, String str2) {
		String[] strArr1 = str1.split("/");
		String[] strArr2 = str2.split("/");
		String[] formerArr = strArr1[0].trim().split(":");
		String[] latterArr = strArr2[0].trim().split(":");

		int time1 = Integer.parseInt(formerArr[0].trim()) * 3600
				+ Integer.parseInt(formerArr[01].trim()) * 60
				+ Integer.parseInt(formerArr[2].trim());
		int time2 = Integer.parseInt(latterArr[0].trim()) * 3600
				+ Integer.parseInt(latterArr[01].trim()) * 60
				+ Integer.parseInt(latterArr[2].trim());
		return (time2 - time1);
	}

	/**
	 * set sleep time to 30 minutes from Settings
	 *
	 * @throws UiObjectNotFoundException
	 */
	public void setSleepTimeToThirtyMinutes() throws UiObjectNotFoundException {
		backToIdle();
		// Launch Settings and verify text 'Settings' display
		uiAutoTestCase.launchApp("com.android.settings", ".Settings");

		UiAutoTestCase.assertTrue("Header text 'Settings' should display",
				uiAutoTestCase.waitForTextExists("Settings", 2000));

		// Scroll to find 'Display' and tap it
		uiAutoTestCase.scrollToFindText("Display", "Vertical", 2);

		uiAutoTestCase.clickText("Display", true);

		// Select 'Sleep' from 'Display'
		UiAutoTestCase
				.assertTrue(
						"The header text 'Settings' should gone now and should header text should be 'Dispaly' now",
						uiAutoTestCase.waitForTextGone("Settings", 3000));
		uiAutoTestCase.scrollToFindText("Sleep", "Vertical", 2);

		uiAutoTestCase.clickText("Sleep", true);

		uiAutoTestCase.assertTextPresent("30 minutes");

		uiAutoTestCase.clickText("30 minutes", true);

		backToIdle();
	}

	/**
	 * Enter Chinese in editor
	 * 修改系统输入法，请参考:http://www.cnblogs.com/yajing-zh/p/5125317.
	 * html和http://www.cnblogs.com/yajing-zh/p/5125387.html
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void enterChinese(String text, String cls, int instance)
			throws UiObjectNotFoundException {

		// 获取手机的默认输入法到inputMethod.log中
		// uiAutoTestCase.executeCmd("def=`settings get secure default_input_method`");
		// 手机默认输入法已在config.properties文件中给出。本来想着动态获取默认输入法，以便在后期恢复默认输入法时使用。
		// 但由于手机的shell支持的语法有限，所以只能写死到config文件中。

		// 修改默认输入法为UTF7 IME for UI Testing.
		uiAutoTestCase
				.executeCmd("settings put secure default_input_method jp.jun_nama.test.utf7ime/.Utf7ImeService");

		UiObject obj = new UiObject(new UiSelector().className(cls).instance(
				instance));

		obj.setText(Utf7ImeHelper.e(text));

		// 恢复默认输入法
		uiAutoTestCase.executeCmd("settings put secure default_input_method "
				+ Constants.DEFAULT_INPUT_METHOD);
	}
}
