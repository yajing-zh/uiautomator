adb devices

adb shell rm -rf /sdcard/uiAutoTest/*

adb shell rm -rf /data/local/tmp/AT_BDQB.jar
adb push bin\AT_BDQB.jar /data/local/tmp/

adb push config /data/local/tmp/

rd /s /q reports
mkdir reports

::for /l %%i in (1,1,3) do 
adb shell uiautomator runtest AT_BDQB.jar -c com.bdqb.test.BDQB_Test#testOrder

adb pull /sdcard/uiAutoTest/ reports