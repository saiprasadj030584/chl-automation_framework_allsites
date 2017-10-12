d:
cd D:\Program Files\Jenkins\workspace\JDA_WMS_GM_Timed\files\
copy ParentRequestId.txt "D:\Program Files\Jenkins\workspace\JDA_WMS_GM_Email_Metrics\files\ParentRequestId.txt"
cd D:\Program Files\Jenkins\workspace\JDA_WMS_GM_Timed\target
xcopy /d /y /s cucumber-html-reports "D:\Program Files\Jenkins\workspace\JDA_WMS_GM_Timed\Cucumber-Reports"
cd D:\Program Files\Jenkins\workspace\JDA_WMS_GM_Timed\Cucumber-Reports
rmdir /q /s js