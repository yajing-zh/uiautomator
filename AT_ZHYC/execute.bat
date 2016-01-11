adb devices

adb shell rm -rf /sdcard/uiAutoTest/*

adb shell rm -rf /data/local/tmp/AT_ZHYC.jar
adb push bin\AT_ZHYC.jar /data/local/tmp/

adb push config /data/local/tmp/

rd /s /q reports
mkdir reports

adb shell uiautomator runtest AT_ZHYC.jar -c com.zhyc.test.ZHYC_Test

adb pull /sdcard/uiAutoTest/ reports