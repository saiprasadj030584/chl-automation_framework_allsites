d:
cd D:\Program Files\Jenkins\workspace\JDA_WMS_EXIT_Timed\files\
copy ParentRequestId.txt "D:\Program Files\Jenkins\workspace\JDA_WMS_EXIT_Email_Metrics\files\ParentRequestId.txt"
cd D:\Program Files\Jenkins\workspace\JDA_WMS_EXIT_Timed\Cucumber-Reports
del /q *.*
cd D:\Program Files\Jenkins\workspace\JDA_WMS_EXIT_Timed\target
xcopy /d /y /s cucumber-html-reports "D:\Program Files\Jenkins\workspace\JDA_WMS_EXIT_Timed\Cucumber-Reports"
cd D:\Program Files\Jenkins\workspace\JDA_WMS_EXIT_Timed\Cucumber-Reports
rmdir /q /s js
cd D:\Program Files\Jenkins\workspace\JDA_WMS_EXIT_Timed\files\
del *.zip