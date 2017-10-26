d:
cd D:\Program Files\Jenkins\workspace\JDA_WMS_GM_Timed\target
xcopy /d /y /s cucumber-html-reports "D:\Program Files\Jenkins\workspace\JDA_WMS_GM_Timed\Cucumber-Reports"
cd D:\Program Files\Jenkins\workspace\JDA_WMS_GM_Timed\Cucumber-Reports
rmdir /q /s js