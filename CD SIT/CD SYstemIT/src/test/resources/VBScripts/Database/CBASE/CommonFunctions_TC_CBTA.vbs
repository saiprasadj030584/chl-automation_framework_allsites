

'Public TestPath
Function Callscript(Scriptname,TC,value3,value4,value5)

Dim WshShell, oExec
Set WshShell = CreateObject("WScript.Shell")

'Dim WshShell1, oExec
'Set WshShell1 = CreateObject("WScript.Shell")


'Set oExec = WshShell1.Exec("taskkill /fi ""imagename eq sapgui.exe""")
'WScript.Sleep 3000

'Set oExec = WshShell1.Exec("taskkill /fi ""imagename eq iexplore.exe""")
'WScript.Sleep 3000
Set oExec = nothing

'Set fsObj = CreateObject("Scripting.FileSystemObject")
Scriptnamearr = split(Scriptname,"_")

Select Case Ucase(Scriptnamearr(0))
		Case "MDC"
			L3="Master Data\C&H"
		Case "MDF"
			L3="Master Data\Foods"
		Case "FIN"
			L3="Finance"
		Case "GMR"
			L3="GMOR"
		Case "BYF"
			L3="Buying\Foods"			
		'Case Else
			'xmlDoc.load Environment("RelativePath") & "\DataSheet\" & "SAPAutomation.xml"
	End Select
'TestPath = "\\mshsrmnsukc0270\d$\SAPISWARYA\Scripts\Stock\C&H\STC_004_Direct delivery to store\"&Scriptname& "\" &TC& ".vbs"
TestPath = "\\mshsrmnsukc0270\d$\SAPISWARYA\Scripts\" & L3 & "\" &Scriptname&"\" &TC& ".vbs"
'msgbox TestPath
'Set vbsFile = fsObj.OpenTextFile(Testpath, 1, False)

'myFunctionsStr = vbsFile.ReadAll
'vbsFile.Close
'Set vbsFile = Nothing
'Set fsObj = Nothing

'ExecuteGlobal myFunctionsStr
Dim strFileName
strFileName =  Chr(34) & TestPath  & Chr(34)
'msgbox strFileName
Set WSHShell = CreateObject("WScript.Shell") 
WSHShell.Run "wscript " & strFileName, , True

End Function
'Call Callscript("MDF_005_Create Foods Normal article through I1212 Interface from PLuM","TC02_Validate and process I1212 IDOC in SAP ECC","","","")

Dim TCStrtTime,TCEndTime
Public ExeMode
Function InitializeTest()
		
		
		If Instr(testDir,"\Scripts\")>0 Then 
		rPath = split(testDir,"\Scripts\")
		ExeMode = "Vibase"
		Else
		rPath = split(testDir,"\Database\")
		'msgbox testDir
		ExeMode = "CBTA"
		End If
		RelativePath = rPath(0)

		
	'ResTemp = split(testDir,"\R2 2014")
	
	'Set  xmlDoc = CreateObject("Msxml2.DOMDocument.3.0")
		'xmlDoc.load Environment("RelativePath") & "\DataSheet\" & "ResultFolder.xml"
		'TestCycle = xmlDoc.SelectSingleNode("/ExecutePath/TESTCYCLE").text
		'ReleaseResult = xmlDoc.SelectSingleNode("/ExecutePath/ResultPath").text
	
		
	
	InitialResultPath = RelativePath &"\Results\"& trim(ReleaseResult)
	'ResultReleaseFolder = Environment.Value("InitialPath")&"\"& trim(ReleaseResult)
	ResultPath = InitialResultPath &"\"& TestCycle
	'Environment.Value("ScriptWiseResultPath") = Environment.Value("RelativePath") & "\Results\" & Environment.Value("TestCycle") & "\Res\Script_Wise"
	Set fso = CreateObject("Scripting.FileSystemObject")
	If NOT(fso.FolderExists(InitialResultPath)) Then
		tempFolder1 = fso.CreateFolder(InitialResultPath)
	End If
	If NOT(fso.FolderExists(ResultPath)) Then
		tempFolder = fso.CreateFolder(ResultPath)
	End If
'
'	PassFlag = False ' initialized to False
	'Call AddRepository("\Generic\CommonFunctions.tsr")
End Function
Function CreateTestcaseWiseResult()
   Call CreateDayWiseFolder()
   Call CreateTCHTML(tcwiseResPath)
End Function
Function CreateTestcaseWiseResult_ALM()
   Call CreateDayWiseFolder_ALM()
   Call CreateTCHTML(tcwiseResPath)
End Function

Function CreateDayWiseFolder()
   'relPath = Environment.Value("RelativePath")
   DayWisefolder = ResultPath & "\" & replace(Date,"/","-")
   foldername = ScenarioID & "_" & replace(Date,"/","") & "_" & replace(Time,":","")
   'foldernameExport = foldername
   GenricResultPath = DayWisefolder & "\" & foldername
   tcwiseResPath = GenricResultPath & "\Screenshots"
   ExcelPath =  GenricResultPath & "\ExcelResults"
   HTMLPath =  GenricResultPath & "\HTMLResults"
   Dim fso,f
   Set fso = CreateObject("Scripting.FileSystemObject")
   If NOT(fso.FolderExists(DayWisefolder)) then
	   Set f = fso.CreateFolder(DayWisefolder)
	   DayWisefolder = f.Path 
	Else
		DayWisefolder = fso.GetFolder(DayWisefolder).Path 
   End if
   If NOT(fso.FolderExists(GenricResultPath)) then
	   Set f = fso.CreateFolder(GenricResultPath)
	   GenricResultPath = f.Path 
	Else
		GenricResultPath = fso.GetFolder(GenricResultPath).Path 
   End if
   If NOT(fso.FolderExists(tcwiseResPath)) then
	   Set f = fso.CreateFolder(tcwiseResPath)
	   SSResultPath = f.Path 
	Else
		SSResultPath = fso.GetFolder(tcwiseResPath).Path 
   End if
   
   If NOT(fso.FolderExists(ExcelPath)) then
	   Set f = fso.CreateFolder(ExcelPath)
	   ExcelResultPath = f.Path 
	 Else
		ExcelResultPath = fso.GetFolder(ExcelPath).Path
   End if
   
   If NOT(fso.FolderExists(HTMLPath)) then
	   Set f = fso.CreateFolder(HTMLPath)
	   HTMLResultPath = f.Path 
	Else
		HTMLResultPath = fso.GetFolder(HTMLPath).Path   
   End if
   
   
End Function
Function CreateDayWiseFolder_ALM()
   'relPath = Environment.Value("RelativePath")
   If Instr(1,TestCase,"TC01") > 0 Then
		Call ExportValueXML("ResultPath","")
		Call GetXMLData(ObjXMLData)
   End If
	
   'relPath = Environment.Value("RelativePath")
   If ObjXMLData.Item("ResultPath")= "" Then
	   	DayWisefolder = ResultPath & "\" & replace(Date,"/","-")
	   	foldername = ScenarioID & "_" & replace(Date,"/","") & "_" & replace(Time,":","")
	   	'foldername = Environment("TestName") & "_" & replace(Date,"/","") & "_" & replace(Time,":","")	   	
	   	GenricResultPath = DayWisefolder & "\" & foldername
	   '	InputExcel = Environment.Value("RelativePath") & "\DataSheet\" & "TS01_NewPLcreation_Config_Validation" & ".xls"
	   	Call ExportValueXML("ResultPath",GenricResultPath)
		'Call ExportValues("ResultPath",GenricResultPath)
		Call GetXMLData(ObjXMLData)
	   	'Call UpdateDataTable("ResultPath",GenricResultPath
   else
	   	GenricResultPath = ObjXMLData.Item("ResultPath")
	   	GenricResultPathArr = Split(GenricResultPath,"\")
	   	foldername = GenricResultPathArr(Ubound(GenricResultPathArr))
	   	Daywise = Split(GenricResultPath,"\"&foldername)
	   	DayWisefolder = Daywise(0)
   End If 
   tcwiseResPath = GenricResultPath & "\Screenshots"
   ExcelPath =  GenricResultPath & "\ExcelResults"
   HTMLPath =  GenricResultPath & "\HTMLResults"
   Dim fso,f
   Set fso = CreateObject("Scripting.FileSystemObject")
   If NOT(fso.FolderExists(DayWisefolder)) then
	   Set f = fso.CreateFolder(DayWisefolder)
	   DayWisefolder = f.Path 
	Else
		DayWisefolder = fso.GetFolder(DayWisefolder).Path 
   End if
   If NOT(fso.FolderExists(GenricResultPath)) then
	   Set f = fso.CreateFolder(GenricResultPath)
	   GenricResultPath = f.Path 
	Else
		GenricResultPath = fso.GetFolder(GenricResultPath).Path 
   End if
   If NOT(fso.FolderExists(tcwiseResPath)) then
	   Set f = fso.CreateFolder(tcwiseResPath)
	   SSResultPath = f.Path 
	Else
		SSResultPath = fso.GetFolder(tcwiseResPath).Path 
   End if
   
   If NOT(fso.FolderExists(ExcelPath)) then
	   Set f = fso.CreateFolder(ExcelPath)
	   ExcelResultPath = f.Path 
	 Else
		ExcelResultPath = fso.GetFolder(ExcelPath).Path
   End if
   
   If NOT(fso.FolderExists(HTMLPath)) then
	   Set f = fso.CreateFolder(HTMLPath)
	   HTMLResultPath = f.Path 
	Else
		HTMLResultPath = fso.GetFolder(HTMLPath).Path   
   End if
   
   
End Function
Function ImportXMLFile()
	
	Set  xmlDoc = CreateObject("MSXML2.DOMDocument")
	ExecutionRegion = ObjXMLData.Item("Environment")
	ExeMachine = CreateObject("WScript.Network").UserName

	Set wshNetwork = CreateObject( "WScript.Network" )
	strComputerName = lcase(wshNetwork.ComputerName)
	'msgbox ExeMachine
	'\\mshsrmnsukc0270\d$\SAPISWARYA\
	Select Case Ucase(Trim(ExecutionRegion))
		Case "CATEA"
			'xmlDoc.load "\\" & strComputerName & "\d$\SAPISWARYA\DataSheets\" & "SAPAutomationCATEA.xml"
			xmlDoc.load RelativePath & "\Login\" & strComputerName & "\SAPAutomationCATEA.xml"
		Case "EAR"
			xmlDoc.load RelativePath & "\Datasheets\" & "SAPAutomationEAR.xml"
		Case "CATEC"
			xmlDoc.load RelativePath & "\DataSheets\" & "SAPAutomationCATEC.xml"
                Case "CATEL"
			xmlDoc.load RelativePath & "\Login\" & strComputerName & "\SAPAutomationCATEL.xml"
			'xmlDoc.load RelativePath & "\DataSheets\" & "SAPAutomationCATEL.xml"			
		'Case "CATEL"
			'xmlDoc.load Environment("RelativePath") & "\DataSheet\" & "SAPAutomationCATEL.xml"
		Case "INTA"
			xmlDoc.load RelativePath & "\DataSheets\" & "SAPAutomationINTA.xml"
		Case "INTB"
			xmlDoc.load RelativePath & "\Login\" & strComputerName & "\SAPAutomationINTB.xml"		
		Case "FCT"
			xmlDoc.load Environment("RelativePath") & "\DataSheet\" & "SAPAutomationQARegion.xml"
		Case "FNQ"
			xmlDoc.load Environment("RelativePath") & "\DataSheet\" & "SAPAutomationFNQ.xml"
		Case "EAR"
			xmlDoc.load RelativePath & "\Datasheets\" & "SAPAutomationEAR.xml"
		Case Else
			xmlDoc.load Environment("RelativePath") & "\DataSheet\" & "SAPAutomation.xml"
	End Select
	'Msgbox ExeMachine
	'ReportURL = xmlDoc.SelectSingleNode("/DataFile/General/SAPUserLoginName").text
	LoginID = xmlDoc.SelectSingleNode("/DataFile/" & ExeMachine & "/SAPLoginUsername").text
	Password = xmlDoc.SelectSingleNode("/DataFile/" & ExeMachine & "/SAPLoginPassword").text
	MasterLoginID = xmlDoc.SelectSingleNode("/DataFile/"&ExeMachine&"/MasterSAPLoginUsername").text
	MasterPassword = xmlDoc.SelectSingleNode("/DataFile/"&ExeMachine&"/MasterSAPLoginPassword").text
	BILoginID = xmlDoc.SelectSingleNode("/DataFile/"&ExeMachine&"/BISAPLoginUsername").text
	BIPassword = xmlDoc.SelectSingleNode("/DataFile/"&ExeMachine&"/BISAPLoginPassword").text
	PILoginID = xmlDoc.SelectSingleNode("/DataFile/"&ExeMachine&"/PILogin").text
	PIPassword = xmlDoc.SelectSingleNode("/DataFile/"&ExeMachine&"/PIPassword").text

  '  Environment.Value("SAP_LoginECC_User") = LoginID
	'Environment.Value("SAP_LoginECC_PW") = Password
	'Environment.Value("SAP_LoginPI_User") = PILoginID
	'Environment.Value("SAP_LoginPI_PW") = PIPassword
End Function

Public Function SAPLogonECC(session)

If ExeMode = "Vibase" Then
'Vibase
    Set WSHShell = CreateObject("WScript.Shell")
    If IsObject(WSHShell) Then
        SAPGUIPath = "C:\Program Files (x86)\SAP\FrontEnd\SAPgui\"


	ExecutionRegion = ObjXMLData.Item("Environment")
	Select Case Ucase(Trim(ExecutionRegion))
	Case "INTA"
		SID = "fcics.unix.marksandspencerdev.com"
		InstanceNo = "13"
	Case "CATEA"
		SID = "10.144.32.146"
		InstanceNo = "13"			
	'Case "FNL"
		'SID = "10.144.52.1"
		'InstanceNo = "14"
        Case "CATEL"
		SID = "10.144.52.1"
		InstanceNo = "14"
	Case "INTB"
		SID = "c1bfcr21.unix.marksandspencercate.com"
		InstanceNo = "14"
	Case "EAR"
		SID = "huxc8002.unix.marksandspencercate.com"
		InstanceNo = "04"
	End Select
	
        WSHShell.Exec SAPGUIPath & "sapgui.exe " & SID & " " & InstanceNo
        WinTitle = "SAP"
      	While Not WSHShell.AppActivate(WinTitle)
        	WScript.Sleep 3000
     	Wend
Wscript.Sleep 2000
      	Set WSHShell = Nothing
    End If

If Not IsObject(application) Then
   Set SapGuiAuto  = GetObject("SAPGUI")
   Set application = SapGuiAuto.GetScriptingEngine
End If

If Not IsObject(connection) Then
   Set connection = application.Children(0)
End If
If Not IsObject(session) Then
   Set session    = connection.Children(0)
End If
If IsObject(WScript) Then
   WScript.ConnectObject session,     "on"
   WScript.ConnectObject application, "on"
End If

session.findById("wnd[0]/usr/txtRSYST-BNAME").text = LoginID
session.findById("wnd[0]/usr/pwdRSYST-BCODE").text = Password
session.findById("wnd[0]/usr/pwdRSYST-BCODE").setFocus
session.findById("wnd[0]/usr/pwdRSYST-BCODE").caretPosition = 6
session.findById("wnd[0]").sendVKey 0
session.findById("wnd[0]").sendVKey 0
session.findById("wnd[0]").sendVKey 0
ElseIf ExeMode="CBTA_Vibase" Then
'CBTA_Vibase
Set WSHShell = CreateObject("WScript.Shell")
    If IsObject(WSHShell) Then
        SAPGUIPath = "C:\Program Files (x86)\SAP\FrontEnd\SAPgui\"
	ExecutionRegion = ObjXMLData.Item("Environment")
	Select Case Ucase(Trim(ExecutionRegion))
	Case "INTA"
		SID = "fcics.unix.marksandspencerdev.com"
		InstanceNo = "13"
	Case "CATEA"
		SID = "10.144.32.146"
		InstanceNo = "13"			
	'Case "FNL"
		'SID = "10.144.52.1"
		'InstanceNo = "14"
        Case "CATEL"
		SID = "10.144.52.1"
		InstanceNo = "14"
	Case "INTB"
		SID = "c1bfcr21.unix.marksandspencercate.com"
		InstanceNo = "14"
	Case "EAR"
		SID = "huxc8002.unix.marksandspencercate.com"
		InstanceNo = "04"
	End Select
	
        WSHShell.Exec SAPGUIPath & "sapgui.exe " & SID & " " & InstanceNo
        WinTitle = "SAP"
      	While Not WSHShell.AppActivate(WinTitle)
        	WScript.Sleep 3
     	Wend
      	Set WSHShell = Nothing
    End If

If Not IsObject(application) Then
   Set SapGuiAuto  = GetObject("SAPGUI")
   Set application = SapGuiAuto.GetScriptingEngine
End If
If Not IsObject(connection) Then
   Set connection = application.Children(1)
End If
If Not IsObject(session) Then
   Set session    = connection.Children(0)
End If
If IsObject(WScript) Then
   WScript.ConnectObject session,     "on"
   WScript.ConnectObject application, "on"
End If

session.findById("wnd[0]").maximize
session.findById("wnd[0]/usr/txtRSYST-BNAME").text = BILoginID
session.findById("wnd[0]/usr/pwdRSYST-BCODE").text = BIPassword
session.findById("wnd[0]/usr/pwdRSYST-BCODE").setFocus
session.findById("wnd[0]/usr/pwdRSYST-BCODE").caretPosition = 6
session.findById("wnd[0]").sendVKey 0
session.findById("wnd[0]").sendVKey 0
session.findById("wnd[0]").sendVKey 0
Else
'CBTA
If Not IsObject(application) Then
   Set SapGuiAuto  = GetObject("SAPGUI")
   Set application = SapGuiAuto.GetScriptingEngine
End If

If Not IsObject(connection) Then
   Set connection = application.Children(1)
End If
If Not IsObject(session) Then
   Set session    = connection.Children(0)
End If
If IsObject(WScript) Then
   WScript.ConnectObject session,     "on"
   WScript.ConnectObject application, "on"
End If
ExeMode = "CBTA_Vibase"
End If ' If ExeMode <> "CBTA" Then

End Function





Public Function SAPLogonBI(session)
If ExeMode = "Vibase" Then
'Vibase
Set WSHShell = CreateObject("WScript.Shell")
    If IsObject(WSHShell) Then
        SAPGUIPath = "C:\Program Files (x86)\SAP\FrontEnd\SAPgui\"
	ExecutionRegion = ObjXMLData.Item("Environment")
	Select Case Ucase(Trim(ExecutionRegion))
	Case "INTA"
		SID = "fcics.unix.marksandspencerdev.com"
		InstanceNo = "13"
	Case "CATEA"
		SID = "10.144.32.147"
		InstanceNo = "23"			
	'Case "FNL"
		'SID = "10.144.52.1"
		'InstanceNo = "14"
        Case "CATEL"
		SID = "10.144.52.1"
		InstanceNo = "24"  	
	Case "INTB"
		SID = "c1bfbr21.unix.marksandspencercate.com"
		InstanceNo = "24"
	End Select
	
        WSHShell.Exec SAPGUIPath & "sapgui.exe " & SID & " " & InstanceNo
        WinTitle = "SAP"
      	While Not WSHShell.AppActivate(WinTitle)
        	WScript.Sleep 3
     	Wend
      	Set WSHShell = Nothing
    End If

If Not IsObject(application) Then
   Set SapGuiAuto  = GetObject("SAPGUI")
   Set application = SapGuiAuto.GetScriptingEngine
End If
If Not IsObject(connection) Then
   Set connection = application.Children(0)
End If
If Not IsObject(session) Then
   Set session    = connection.Children(0)
End If
If IsObject(WScript) Then
   WScript.ConnectObject session,     "on"
   WScript.ConnectObject application, "on"
End If

session.findById("wnd[0]").maximize
session.findById("wnd[0]/usr/txtRSYST-BNAME").text = BILoginID
session.findById("wnd[0]/usr/pwdRSYST-BCODE").text = BIPassword
session.findById("wnd[0]/usr/pwdRSYST-BCODE").setFocus
session.findById("wnd[0]/usr/pwdRSYST-BCODE").caretPosition = 6
session.findById("wnd[0]").sendVKey 0
session.findById("wnd[0]").sendVKey 0
session.findById("wnd[0]").sendVKey 0

ElseIf ExeMode="CBTA_Vibase" Then
'CBTA_Vibase
Set WSHShell = CreateObject("WScript.Shell")
    If IsObject(WSHShell) Then
        SAPGUIPath = "C:\Program Files (x86)\SAP\FrontEnd\SAPgui\"
	ExecutionRegion = ObjXMLData.Item("Environment")
	Select Case Ucase(Trim(ExecutionRegion))
	Case "INTA"
		SID = "fcics.unix.marksandspencerdev.com"
		InstanceNo = "13"
	Case "CATEA"
		SID = "10.144.32.147"
		InstanceNo = "23"			
	'Case "FNL"
		'SID = "10.144.52.1"
		'InstanceNo = "14"
        Case "CATEL"
		SID = "10.144.52.1"
		InstanceNo = "24"  	
	Case "INTB"
		SID = "c1bfbr21.unix.marksandspencercate.com"
		InstanceNo = "24"
	End Select
	
        WSHShell.Exec SAPGUIPath & "sapgui.exe " & SID & " " & InstanceNo
        WinTitle = "SAP"
      	While Not WSHShell.AppActivate(WinTitle)
        	WScript.Sleep 3
     	Wend
      	Set WSHShell = Nothing
    End If

If Not IsObject(application) Then
   Set SapGuiAuto  = GetObject("SAPGUI")
   Set application = SapGuiAuto.GetScriptingEngine
End If
If Not IsObject(connection) Then
   Set connection = application.Children(1)
End If
If Not IsObject(session) Then
   Set session    = connection.Children(0)
End If
If IsObject(WScript) Then
   WScript.ConnectObject session,     "on"
   WScript.ConnectObject application, "on"
End If

session.findById("wnd[0]").maximize
session.findById("wnd[0]/usr/txtRSYST-BNAME").text = BILoginID
session.findById("wnd[0]/usr/pwdRSYST-BCODE").text = BIPassword
session.findById("wnd[0]/usr/pwdRSYST-BCODE").setFocus
session.findById("wnd[0]/usr/pwdRSYST-BCODE").caretPosition = 6
session.findById("wnd[0]").sendVKey 0
session.findById("wnd[0]").sendVKey 0
session.findById("wnd[0]").sendVKey 0
Else
'CBTA
If Not IsObject(application) Then
   Set SapGuiAuto  = GetObject("SAPGUI")
   Set application = SapGuiAuto.GetScriptingEngine
End If
If Not IsObject(connection) Then
   Set connection = application.Children(1)
End If
If Not IsObject(session) Then
   Set session    = connection.Children(0)
End If
If IsObject(WScript) Then
   WScript.ConnectObject session,     "on"
   WScript.ConnectObject application, "on"
End If
ExeMode = "CBTA_Vibase"
End If 'If ExeMode <> "CBTA" Then
End Function


Public Function SAPLogonBW(session)

If ExeMode = "Vibase" Then
'Vibase
    Set WSHShell = CreateObject("WScript.Shell")
    If IsObject(WSHShell) Then
        SAPGUIPath = "C:\Program Files (x86)\SAP\FrontEnd\SAPgui\"


	ExecutionRegion = ObjXMLData.Item("Environment")
	Select Case Ucase(Trim(ExecutionRegion))
	Case "INTA"
		SID = "fcics.unix.marksandspencerdev.com"
		InstanceNo = "13"
	Case "CATEA"
		SID = "hlxc0bf010.unix.marksandspencercate.com"
		InstanceNo = "14"			
	Case "FNL"
		SID = "10.144.52.1"
		InstanceNo = "14"
	End Select
        WSHShell.Exec SAPGUIPath & "sapgui.exe " & SID & " " & InstanceNo
        WinTitle = "SAP"
      	While Not WSHShell.AppActivate(WinTitle)
        	WScript.Sleep 3000
     	Wend
Wscript.Sleep 2000
      	Set WSHShell = Nothing
    End If

If Not IsObject(application) Then
   Set SapGuiAuto  = GetObject("SAPGUI")
   Set application = SapGuiAuto.GetScriptingEngine
End If

If Not IsObject(connection) Then
   Set connection = application.Children(0)
End If
If Not IsObject(session) Then
   Set session    = connection.Children(0)
End If
If IsObject(WScript) Then
   WScript.ConnectObject session,     "on"
   WScript.ConnectObject application, "on"
End If

session.findById("wnd[0]/usr/txtRSYST-BNAME").text = MasterLoginID
session.findById("wnd[0]/usr/pwdRSYST-BCODE").text = MasterPassword
session.findById("wnd[0]/usr/pwdRSYST-BCODE").setFocus
session.findById("wnd[0]/usr/pwdRSYST-BCODE").caretPosition = 6
session.findById("wnd[0]").sendVKey 0
session.findById("wnd[0]").sendVKey 0
session.findById("wnd[0]").sendVKey 0
ElseIf ExeMode="CBTA_Vibase" Then
'CBTA_Vibase
Set WSHShell = CreateObject("WScript.Shell")
    If IsObject(WSHShell) Then
        SAPGUIPath = "C:\Program Files (x86)\SAP\FrontEnd\SAPgui\"
	ExecutionRegion = ObjXMLData.Item("Environment")
	Select Case Ucase(Trim(ExecutionRegion))
	Case "INTA"
		SID = "fcics.unix.marksandspencerdev.com"
		InstanceNo = "13"
	Case "CATEA"
		SID = "hlxc0bf010.unix.marksandspencercate.com"
		InstanceNo = "14"			
	Case "FNL"
		SID = "10.144.52.1"
		InstanceNo = "14"
	End Select
	
        WSHShell.Exec SAPGUIPath & "sapgui.exe " & SID & " " & InstanceNo
        WinTitle = "SAP"
      	While Not WSHShell.AppActivate(WinTitle)
        	WScript.Sleep 3
     	Wend
      	Set WSHShell = Nothing
    End If

If Not IsObject(application) Then
   Set SapGuiAuto  = GetObject("SAPGUI")
   Set application = SapGuiAuto.GetScriptingEngine
End If
If Not IsObject(connection) Then
   Set connection = application.Children(1)
End If
If Not IsObject(session) Then
   Set session    = connection.Children(0)
End If
If IsObject(WScript) Then
   WScript.ConnectObject session,     "on"
   WScript.ConnectObject application, "on"
End If

session.findById("wnd[0]").maximize
session.findById("wnd[0]/usr/txtRSYST-BNAME").text = MasterLoginID
session.findById("wnd[0]/usr/pwdRSYST-BCODE").text = MasterPassword
session.findById("wnd[0]/usr/pwdRSYST-BCODE").setFocus
session.findById("wnd[0]/usr/pwdRSYST-BCODE").caretPosition = 6
session.findById("wnd[0]").sendVKey 0
session.findById("wnd[0]").sendVKey 0
session.findById("wnd[0]").sendVKey 0
Else
'CBTA
If Not IsObject(application) Then
   Set SapGuiAuto  = GetObject("SAPGUI")
   Set application = SapGuiAuto.GetScriptingEngine
End If

If Not IsObject(connection) Then
   Set connection = application.Children(1)
End If
If Not IsObject(session) Then
   Set session    = connection.Children(0)
End If
If IsObject(WScript) Then
   WScript.ConnectObject session,     "on"
   WScript.ConnectObject application, "on"
End If
ExeMode = "CBTA_Vibase"
End If ' If ExeMode <> "CBTA" Then

End Function

Public Function SAPLogonBW_Old(session)
If ExeMode = "Vibase" Then
'Vibase
Set WSHShell = CreateObject("WScript.Shell")
    If IsObject(WSHShell) Then
        SAPGUIPath = "C:\Program Files (x86)\SAP\FrontEnd\SAPgui\"
	ExecutionRegion = ObjXMLData.Item("Environment")

	Select Case Ucase(Trim(ExecutionRegion))
	Case "INTA"
		SID = "fcics.unix.marksandspencerdev.com"
		InstanceNo = "13"
	Case "CATEA"
		SID = "hlxc0bf010.unix.marksandspencercate.com"
		InstanceNo = "14"			
	Case "FNL"
		SID = "10.144.52.1"
		InstanceNo = "14"
	End Select
	
        WSHShell.Exec SAPGUIPath & "sapgui.exe " & SID & " " & InstanceNo
        WinTitle = "SAP"
      	While Not WSHShell.AppActivate(WinTitle)
        	WScript.Sleep 3000
     	Wend
      	Set WSHShell = Nothing
    End If

If Not IsObject(application) Then
   Set SapGuiAuto  = GetObject("SAPGUI")
   Set application = SapGuiAuto.GetScriptingEngine
End If
If Not IsObject(connection) Then
   Set connection = application.Children(0)
End If
If Not IsObject(session) Then
   Set session   = connection.Children(0)
End If
If IsObject(WScript) Then

   WScript.ConnectObject session,     "on"
   WScript.ConnectObject application, "on"

End If

session.findById("wnd[0]").maximize
session.findById("wnd[0]/usr/txtRSYST-BNAME").text = MasterLoginID
session.findById("wnd[0]/usr/pwdRSYST-BCODE").text = MasterPassword
session.findById("wnd[0]/usr/pwdRSYST-BCODE").setFocus
session.findById("wnd[0]/usr/pwdRSYST-BCODE").caretPosition = 6
session.findById("wnd[0]").sendVKey 0
If IsObject(session.findById("wnd[1]/usr/radMULTI_LOGON_OPT2")) Then
session.findById("wnd[1]/usr/radMULTI_LOGON_OPT2").select
session.findById("wnd[1]/usr/radMULTI_LOGON_OPT2").setFocus
session.findById("wnd[1]/tbar[0]/btn[0]").press
Else
session.findById("wnd[0]").sendVKey 0
session.findById("wnd[0]").sendVKey 0
End If

ElseIf ExeMode = "CBTA_Vibase" Then
'CBTA_Vibase
Set WSHShell = CreateObject("WScript.Shell")
    If IsObject(WSHShell) Then
        SAPGUIPath = "C:\Program Files (x86)\SAP\FrontEnd\SAPgui\"
	ExecutionRegion = ObjXMLData.Item("Environment")

	Select Case Ucase(Trim(ExecutionRegion))
	Case "INTA"
		SID = "fcics.unix.marksandspencerdev.com"
		InstanceNo = "13"
	Case "CATEA"
		SID = "hlxc0bf010.unix.marksandspencercate.com"
		InstanceNo = "14"			
	Case "FNL"
		SID = "10.144.52.1"
		InstanceNo = "14"
	End Select
	
        WSHShell.Exec SAPGUIPath & "sapgui.exe " & SID & " " & InstanceNo
        WinTitle = "SAP"
      	While Not WSHShell.AppActivate(WinTitle)
        	WScript.Sleep 3000
     	Wend
      	Set WSHShell = Nothing
    End If

If Not IsObject(application) Then
   Set SapGuiAuto  = GetObject("SAPGUI")
   Set application = SapGuiAuto.GetScriptingEngine
End If
If Not IsObject(connection) Then
   Set connection = application.Children(1)
End If
If Not IsObject(session) Then
   Set session   = connection.Children(0)
End If
If IsObject(WScript) Then

   WScript.ConnectObject session,     "on"
   WScript.ConnectObject application, "on"

End If

session.findById("wnd[0]").maximize
session.findById("wnd[0]/usr/txtRSYST-BNAME").text = MasterLoginID
session.findById("wnd[0]/usr/pwdRSYST-BCODE").text = MasterPassword
session.findById("wnd[0]/usr/pwdRSYST-BCODE").setFocus
session.findById("wnd[0]/usr/pwdRSYST-BCODE").caretPosition = 6
session.findById("wnd[0]").sendVKey 0
If IsObject(session.findById("wnd[1]/usr/radMULTI_LOGON_OPT2")) Then
session.findById("wnd[1]/usr/radMULTI_LOGON_OPT2").select
session.findById("wnd[1]/usr/radMULTI_LOGON_OPT2").setFocus
session.findById("wnd[1]/tbar[0]/btn[0]").press
Else
session.findById("wnd[0]").sendVKey 0
session.findById("wnd[0]").sendVKey 0
End If
ExeMode = "CBTA_Vibase"

Else
'CBTA
If Not IsObject(application) Then
   Set SapGuiAuto  = GetObject("SAPGUI")
   Set application = SapGuiAuto.GetScriptingEngine
End If
If Not IsObject(connection) Then
   Set connection = application.Children(1)
End If
If Not IsObject(session) Then
   Set session   = connection.Children(0)
End If
If IsObject(WScript) Then

   WScript.ConnectObject session,     "on"
   WScript.ConnectObject application, "on"

End If
ExeMode = "CBTA_Vibase"
End IF'If ExeMode <> "CBTA" Then
End Function

Function SAP_Kill()
Dim objShell
Set objShell = WScript.CreateObject( "WScript.Shell" )
SAPKILLPATH = strCurDir&"\sapkill.lnk"
'Msgbox SAPKILLPATH
objShell.run(SAPKILLPATH)
Set objShell = Nothing
End Function



Public Function SAPSession(session)

If Not IsObject(application) Then
   Set SapGuiAuto  = GetObject("SAPGUI")
   Set application = SapGuiAuto.GetScriptingEngine

End If
If Not IsObject(connection) Then

   Set connection = application.Children(0)
End If
If Not IsObject(session) Then
   Set session    = connection.Children(0)

End If
If IsObject(WScript) Then
   WScript.ConnectObject session,     "on"
   WScript.ConnectObject application, "on"
End If

End Function





Public Function SAPLogonECCMultipleWindow(session,session_2)

If Not IsObject(Application) Then
   Set SapGuiAuto = GetObject("SAPGUI")
   Set Application = SapGuiAuto.GetScriptingEngine
End If

If Not IsObject(Connection) Then
   Set Connection = Application.Children(0)
End If

If Not IsObject(session_2) Then
   Set session = Connection.Children(0)
   Set session_2 = Connection.Children(1)
End If

If IsObject(WScript) Then
   WScript.ConnectObject session, "on"
   WScript.ConnectObject session_2, "on"
   WScript.ConnectObject Application, "on"
End If

End Function


Public Function CreateTCHTML(tcwiseResPath)

	Dim fso, MyFile	        
	Set fso = CreateObject("Scripting.FileSystemObject")	
	FileName1 =  replace(ScenarioID," ","_") & "_" & replace(replace(now,":",""),"/","")
	TCWiseResult = HTMLPath & "\"&FileName1 &".html"
	Set MyFile = fso.CreateTextFile(TCWiseResult, True)         
	MyFile.Close 
	'intIterVal = Environment.Value("ActionIteration")	
	Set fso = CreateObject("Scripting.FileSystemObject")		
	Set MyFile = fso.OpenTextFile(TCWiseResult,8)
	Myfile.Writeline("<html>")
	Myfile.Writeline("<head>")
	Myfile.Writeline("<meta http-equiv=" & "Content-Language" & "content=" & "en-us>")
	Myfile.Writeline("<meta http-equiv="& "Content-Type" & "content=" & "text/html; charset=windows-1252" & ">")
	Myfile.Writeline("<title>M&S SAP Automation Test Case Wise Results</title>")
	Myfile.Writeline("</head>")
	Myfile.Writeline("<body bgcolor="&"Silver" & ">")
nbsp="<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
nbsp= nbsp & "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
nbsp= nbsp & "&nbsp;&nbsp;&nbsp;&nbsp;<img alt='TCS Logo does not exist' border=2 src= '..\..\..\..\..\SupportFilesTCS.jpg'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
nbsp= nbsp & "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
nbsp= nbsp & "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
nbsp= nbsp & "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
nbsp= nbsp & "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
nbsp= nbsp & "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
nbsp= nbsp & "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img alt='M&S Logo does not exist' border=2 src= '..\..\..\..\..\SupportFiles\MS.jpg'>"
    	   MyFile.WriteLine(nbsp)
    MyFile.WriteLine("</td>")	
	Myfile.Writeline("<center>")
	Myfile.Writeline("<blockquote>")
	Myfile.Writeline("<table border=2 bordercolor=" & "#000000 id=table1 width=844 height=31 bordercolorlight=" & "#000000>")
	Myfile.Writeline("<tr>")		
	Myfile.Writeline("<td COLSPAN = 5")		
	Myfile.Writeline("<p align=center><font color=#000080 size=4 face= "& chr(34)&"Copperplate Gothic Bold"&chr(34) & ">&nbsp;M&S SAP Automation Test Case Wise Results</font><font face= " & chr(34)&"Copperplate Gothic Bold"&chr(34) & "></font> </p>")
    Myfile.Writeline("</td>")		
	Myfile.Writeline("</tr>")
	Myfile.Writeline("<tr>")		
	Myfile.Writeline("<td COLSPAN = 5")		
	Myfile.Writeline("<p align=center><font color=#000080 size=4 face= "& chr(34)&"Copperplate Gothic Bold"&chr(34) & ">&nbsp;Test Script - "& ScenarioID  & "</font><font face= " & chr(34)&"Copperplate Gothic Bold"&chr(34) & "></font> </p>")
    Myfile.Writeline("</td>")		
	Myfile.Writeline("</tr>")
	Myfile.Writeline("<tr>")	
	Myfile.Writeline("<td COLSPAN = 5 >")	
	Myfile.Writeline("<p align=justify><b><font color=#000080 size=2 face= Verdana>"&"Start Date and Time :&nbsp;&nbsp;" &  now  & "&nbsp;")
	TCStrtTime = Time()
    Myfile.Writeline("</td>")			
	Myfile.Writeline("</tr>")	
    Myfile.Writeline("<tr bgcolor=#000080>")
	Myfile.Writeline("<td width=" & "180")
	Myfile.Writeline("<p align=" & "center><b><font color = #FFFFFF face=" & "Arial Narrow " & "size=" & "2" & ">" & "Scenario ID</b></td>")
    Myfile.Writeline("<td width=" & "100")
	Myfile.Writeline("<p align=" & "center" & ">" & "<b><font color = #FFFFFF face=" & "Arial Narrow " & "size=" & "2" & ">" & "Functionality</b></td>")
	Myfile.Writeline("<td width=" & "180")
	Myfile.Writeline("<p align=" & "center><b><font color = #FFFFFF face=" & "Arial Narrow " & "size=" & "2" & ">" & "Step Description</b></td>")
	Myfile.Writeline("<td width=" & "100")
	Myfile.Writeline("<p align=" & "center" & ">" & "<b><font color = #FFFFFF face=" & "Arial Narrow " & "size=" & "2" & ">" & "Step Status</b></td>")
	Myfile.Writeline("</tr>")
	Myfile.Writeline("</blockquote>")
	Myfile.Writeline("</body>")
	Myfile.Writeline("</html>")
	MyFile.Close
	TotalStepsPassed=0
	TotalStepsFailed=0

End Function
Public Function AddStepResultsToHTML_2(StepDesc,StepStatus,scrnShotname)

		
		
		
		Set fso = CreateObject("Scripting.FileSystemObject")
     		Set MyFile = fso.OpenTextFile(TCWiseResult,8)
			Myfile.Writeline("<tr bgcolor = aliceblue >")
			Myfile.Writeline("<td width=  100 bgcolor =  FFFFFF>")
			Myfile.Writeline("<p align=center><font face=Verdana size=2>" & ScenarioID & "</td>")
			Myfile.Writeline("<td width= 175  bgcolor = FFFFFF>")
			If Functionality = "" Then
				Myfile.Writeline("<p align=center><font face=Verdana size=2>" & TestCase & "</td>")
			Else
				Myfile.Writeline("<p align=center><font face=Verdana size=2>" &  TestCase & "</td>")
			End If
			
			Myfile.Writeline("<td width=180 bgcolor =  FFFFFF>")
			Myfile.Writeline("<font face=Verdana size=2>" &  Trim(StepDesc) & "</td>")
			Myfile.Writeline("<td width= 180  bgcolor = FFFFFF>")
            'TempSS = Split(scrnShotname,"\")
            'scrnShotnameResults = "..\" & TempSS(UBound(TempSS)-1) & "\" & TempSS(UBound(TempSS))
	     scrnShotnameResults = scrnShotname
		
			'If Matchval(StepDesc,"DOCNUM,CREDAT,CRETIM,SNDPOR,RCVPOR,MessageId,CorrelationId,CreatedTimestamp")=0 Then	
			Select Case UCase(StepStatus)
			Case "PASS"		Myfile.Writeline("<p align=center><b><font face=Verdana size=2 color=#008000><a href='" &scrnShotnameResults& "'>" & StepStatus & "</a></font></b></td>")
											TotalStepsPassed = TotalStepsPassed+1
											'Call ReportToALM(stepName,status,desc,expectedResult,actualResult)
											'Call ReportToALM(StepDesc,"PASSED",StepDesc,StepDesc,StepDesc)
			Case "FAIL"		Myfile.Writeline("<p align=center><b><font face=Verdana size=2 color=#FF0000><a href='" &scrnShotnameResults& "'>" & StepStatus & "</a></font></b></td>")
											TotalStepsFailed = TotalStepsFailed+1
											'Call ReportToALM(StepDesc,"FAILED",StepDesc,StepDesc,StepDesc)
			Case Else		Myfile.Writeline("<p align=center><b><font face=Verdana size=2 color=#FF0000><a href='" &scrnShotnameResults& "'>" & StepStatus & "</a></font></b></td>")
			End Select
			'Else
				'Myfile.Writeline("<p align=center><b><font face=Verdana size=2 color=#FF0000><a href='" &scrnShotnameResults& "'>" & "Warning" & "</a></font></b></td>")
			'End If
			Myfile.Writeline("</tr>")
	    	Myfile.Close
		
	
End Function
	
'Public Function GetOR(ORrs,ORConn,InputExcel)
'		Set ORConn = CreateObject("ADODB.Connection")
'		FilePath = "\\mshsrmnsukc0173\c$\VB_Scripts\Resources\ObjectRepository\" & InputExcel & ".xls"
'		ConnectionString ="Provider=Microsoft.ACE.OLEDB.12.0;Data Source=" & FilePath & ";Extended Properties=" & "Excel 12.0;IMEX=1;" & strHeader & """"
'		'ConnectionString = "DBQ="& FilePath &";Driver={Microsoft Excel Driver (*.xls)};DriverId=790;FIL=excel 8.0;MaxBufferSize=2048;MaxScanRows=8;PageTimeout=5;ReadOnly=FALSE;SafeTransactions=0;Threads=3;UserCommitSync=Yes;" 
'		ORConn.Open ConnectionString
'		Set ORrs = CreateObject("ADODB.recordset")
 '		Query ="Select * from [OR$]"
'		Set ORrs = ORConn.Execute(Query)			
'End Function

Public Function GetOR(Range,InputExcel)
Set myxl = createobject("excel.application")
myxl.Application.Visible = False
myxl.Workbooks.Open "\\mshsrmnsukc0173\c$\VB_Scripts\Resources\ObjectRepository\" & InputExcel & ".xls"
set mysheet = myxl.ActiveWorkbook.Worksheets("OR")
rowcnt=mysheet.Usedrange.rows.count
'colcnt=mysheet.Usedrange.columns.count
Set range = mysheet.Range("A2","B" & cstr(rowcnt))
'Call GetOR(ORrs,ORConn,"I185")
Set myxl=Nothing
Set mysheet=Nothing			
End Function

Public Function GetData(ObjData,InputExcel)

Set myxl = createobject("excel.application")

myxl.Application.Visible = False
myxl.Workbooks.Open "\\mshsrmnsukc0270\d$\SAPISWARYA\Datasheets\" & InputExcel & ".xls"

set mysheet = myxl.ActiveWorkbook.Worksheets(1)

'rowcnt=mysheet.Usedrange.rows.count
coladd=mysheet.Usedrange.columns.Address
Colnamearr=split(coladd,"$")

Set DataRange = mysheet.Range("A1",Colnamearr(3) & "2")

'Dim ObjData
  Set ObjData = CreateObject("Scripting.Dictionary")


  div=DataRange.count/2


  For rangedata = 1 To div

	If DataRange(rangedata).Value="" then
		Exit For
	End If
	
  	ObjData.Add DataRange(rangedata).Value , DataRange(rangedata+div).Value

  Next

myxl.Workbooks.Close
myxl.Quit
Set myxl=Nothing
Set mysheet=Nothing				
End Function

Public Function ExportValues(Col_Name,Col_Value)

	Call GetData(ObjData,Testname)
  
  	a = ObjData.Keys
  
  	'Col_Name = "S.No"

  	For i = 0 To Ubound(a)
  
  	If a(i)=Col_Name Then
  		Col_Num = i+1
  		Exit For
  	End If
  	
	Next
	
	Set myxl2 = createobject("excel.application")
	myxl2.Application.Visible = False
	Set oWB2 = myxl2.Workbooks.Open ("\\mshsrmnsukc0270\d$\SAPISWARYA\Datasheets\"  & Testname & ".xls")
	set mysheet2 = myxl2.ActiveWorkbook.Worksheets(1)
	mysheet2.Cells(2,cint(Col_Num)).Value = Col_Value

	myxl2.ActiveWorkbook.Save

   	'oWB2.Save

	oWB2.Close
	myxl2.Quit
	'objExcel.Application.Quit     

	Set oWB2=Nothing 
	Set myxl2=Nothing
	'SystemUtil.CloseProcessByName("EXCEL.EXE")
	
  End Function
Public Function CreateXML()


    Call GetData(ObjData,Testname)
	
    FullPath = "\\mshsrmnsukc0270\d$\SAPISWARYA\Datasheets\InputXML\" & Testname & ".xml"
	
    Set objStream = CreateObject("ADODB.Stream")
    objStream.Charset = "iso-8859-1"

    objStream.Open
    objStream.WriteText ("<?xml version='1.0' encoding='UTF-8'?>" & vbLf)
    objStream.WriteText ("<Datasheet xmlns:y='http://www.test.com/engine/3'>" & vbLf)
    objStream.WriteText ("  <Inputdata>" & vbLf)
    objStream.WriteText ("" & vbLf)

	colname = ObjData.Keys
	colval = ObjData.items
	For i = 0 To Ubound(colname)
	    objStream.WriteText ("  <" & colname(i) & ">" & colval(i) & "</" & colname(i) & ">" & vbLf)    
   	Next

    objStream.WriteText ("  </Inputdata>" & vbLf)
    objStream.WriteText ("</Datasheet>" & vbLf)
              
    objStream.SaveToFile FullPath, 2
    objStream.Close
	Set objStream= Nothing
End Function

Public Function GetXMLData(ObjXMLData)
		Set objDoc2 = CreateObject("MSXML2.DOMDocument")
		XMLPath = "\\mshsrmnsukc0270\d$\SAPISWARYA\Datasheets\InputXML\" & Testname & ".xml"
		
		objDoc2.Load (XMLPath)

		Set objparent = objDoc2.SelectSingleNode("/Datasheet/Inputdata")
		childcnt=objparent.childNodes.length
		Set ObjXMLData = CreateObject("Scripting.Dictionary")

  		For i=0 to childcnt-1
			ObjXMLData.Add objparent.childNodes(i).nodeName , objparent.childNodes(i).text			
		Next
		Set objDoc2= Nothing
End Function

Public Function GetXMLData_HANA(Nodenam, nodeval)
		Set objDoc3 = CreateObject("MSXML2.DOMDocument")
		XMLPath2 = "\\mshsrmnsukc0270\d$\SAPISWARYA\Datasheets\InputXML\HANA_Input.xml"
		
		objDoc3.Load (XMLPath2)

		Set objparent2 = objDoc3.SelectSingleNode("/HANA_Data/Inputdata")
		childcnt2=objparent2.childNodes.length
		'Set ObjXMLData = CreateObject("Scripting.Dictionary")

  		For i=0 to childcnt2-1
			If objparent2.childNodes(i).nodeName = Nodenam Then 
				nodeval = objparent2.childNodes(i).text	
			End If		
		Next
		Set objDoc3= Nothing
End Function
Public Function GetXMLData_ERROR(Nodenam, nodeval)
		Set objDoc3 = CreateObject("MSXML2.DOMDocument")
		XMLPath2 = "\\mshsrmnsukc0270\d$\SAPISWARYA\Datasheets\InputXML\Execution_Error.xml"
		
		objDoc3.Load (XMLPath2)

		Set objparent2 = objDoc3.SelectSingleNode("/Execution_Error/Inputdata")
		childcnt2=objparent2.childNodes.length
		'Set ObjXMLData = CreateObject("Scripting.Dictionary")

  		For i=0 to childcnt2-1
			If objparent2.childNodes(i).nodeName = Nodenam Then 
				nodeval = objparent2.childNodes(i).text	
			End If		
		Next
		Set objDoc3= Nothing
End Function

Public Function ExportValueXML(Colname,Colvalue)
    	Set objDoc = CreateObject("MSXML2.DOMDocument")
		XMLFileName ="\\mshsrmnsukc0270\d$\SAPISWARYA\Datasheets\InputXML\" & Testname & ".xml"
		objDoc.Load (XMLFileName)		
		
		Set objfield = objDoc.SelectSingleNode("/Datasheet/Inputdata/" & Colname)
		objfield.text= Colvalue
		objDoc.save(XMLFileName)
		Set objDoc= Nothing
End Function
	
Public Function ExportValueXML_HANA(Colname,Colvalue)
    	Set objDoc = CreateObject("MSXML2.DOMDocument")
		XMLFileName ="\\mshsrmnsukc0270\d$\SAPISWARYA\Datasheets\InputXML\HANA_Input.xml"
		objDoc.Load (XMLFileName)		
		
		Set objfield = objDoc.SelectSingleNode("/HANA_Data/Inputdata/" & Colname)
		objfield.text= Colvalue
		objDoc.save(XMLFileName)
		Set objDoc= Nothing
End Function

Public Function ExportValueXML_ERROR(Colname,Colvalue)

If ExeMode = "CBTA" Then
    		Set objDoc = CreateObject("MSXML2.DOMDocument")
		XMLFileName ="\\mshsrmnsukc0270\d$\SAPISWARYA\Datasheets\InputXML\Execution_Error.xml"
		objDoc.Load (XMLFileName)
		
  If Colname = "" Then
	ExeMachinename = CreateObject("WScript.Network").UserName
	servername = lcase(CreateObject( "WScript.Network" ).ComputerName)
	ExeMachineArr = Split(ExeMachinename,".")
	User = ExeMachineArr(0)

	ServerNameArr= Split(servername,"c")
	ServerName = ServerNameArr(1)
	Nodename = Ucase(ExeMachineArr(0))&"_"&ServerName
	Set objfield = objDoc.SelectSingleNode("/Execution_Error/Inputdata/" & Nodename)
		objfield.text= Colvalue
		objDoc.save(XMLFileName)
		Set objDoc= Nothing
End If
End If
End Function


Public Function ExportValueNOTEPAD_ERROR(Nodename,Error)

Dim fso, MyFile
Set fso = CreateObject("Scripting.FileSystemObject")
Set objFolder = fso.GetFolder("\\mshsrmnsukc0270\d$\SAPISWARYA\Batch")

findflag = 0
for each file1 in objFolder.files
    x = fso.getbasename(file1)
    if x = Nodename then
       findflag = 1
       exit for
   
    end if
next

If findflag = 0 Then
   Set MyFile = fso.CreateTextFile("\\mshsrmnsukc0270\d$\SAPISWARYA\Batch\"&Nodename&".txt", True)
Else

Set MyFile = fso.OpenTextFile("\\mshsrmnsukc0270\d$\SAPISWARYA\Batch\"&Nodename&".txt" , 1)  
  
End if


textfindflag = 0
Set objRegEx = CreateObject("VBScript.RegExp")
objRegEx.Pattern = Date

do Until MyFile.AtEndOfStream

strSearchString = MyFile.ReadLine


Set colMatches = objRegEx.Execute(strSearchString)

If colMatches.Count > 0 Then

textfindflag = 1

End If

Loop

MyFile.Close



Set MyFileWrite = fso.OpenTextFile("\\mshsrmnsukc0270\d$\SAPISWARYA\Batch\"&Nodename&".txt" , 8) 
If textfindflag = 1 Then

MyFileWrite.WriteLine Error
ELse

MyFileWrite.WriteLine Date
MyFileWrite.WriteLine Error
End If
MyFileWrite.Close

End Function

Public Function HANA_wait()
For inc = 1 to 100

Call GetXMLData_HANA("Status", nodeval)

If nodeval = "Y" Then
	Call ExportValueXML_HANA("Status","N")
	Exit For
End If
wscript.sleep 2000
Next
End Function

Public Function ScreenshotExcel(Heading,WindowIndex)

On Error Resume Next 
	Call CaptureScreen_2(session,Heading,WindowIndex)
	Call CaptureScreen_2(session2,Heading,WindowIndex)
	'Call CaptureScreen_2(session_2,Heading,WindowIndex)
	Call CaptureScreen_2(sessionBI,Heading,WindowIndex)
	Err.clear
On Error Goto 0
'For Excel Screenshot
NdcArr1 = Array(" A10 : R35"," P10 : AH35 "," AF10 : AZ35"," AX10 : BR35"," BP10 : CJ35 " )
NdcArr2 = Array(" A40 : R65"," P40 : AH65 "," AF40 : AZ65"," AX40 : BR65"," BP40 : CJ65 " )
NdcArr3 = Array(" A70 : R95"," P70 : AH95 "," AF70 : AZ95"," AX70 : BR95"," BP70 : CJ95 " )
NdcArr4 = Array(" A100 : R125"," P100 : AH125 "," AF100 : AZ125"," AX100 : BR125"," BP100 : CJ125 " )
NdcArr5 = Array(" A130 : R155"," P130 : AH155 "," AF130 : AZ155"," AX130 : BR155"," BP130 : CJ155 " )
NdcArr6 = Array(" A160 : R185"," P160 : AH185 "," AF160 : AZ185"," AX160 : BR185"," BP160 : CJ185 " )
NdcArr7 = Array(" A190 : R215"," P190 : AH215 "," AF190 : AZ215"," AX190 : BR215"," BP190 : CJ215 " )
NdcArr8 = Array(" A220 : R245"," P220 : AH245 "," AF220 : AZ245"," AX220 : BR245"," BP220 : CJ245 " )
NdcArr9 = Array(" A250 : R275"," P250 : AH275 "," AF250 : AZ275"," AX250 : BR275"," BP250 : CJ275 " )
NdcArr10 = Array(" A280 : R305"," P280 : AH305 "," AF280 : AZ305"," AX280 : BR305"," BP280 : CJ305 " )
NdcArr11 = Array(" A310 : R335"," P310 : AH335 "," AF310 : AZ335"," AX310 : BR335"," BP310 : CJ335 " )
NdcArr12 = Array(" A340 : R365"," P340 : AH365 "," AF340 : AZ365"," AX340 : BR365"," BP340 : CJ365 " )
NdcArr13 = Array(" A370 : R395"," P370 : AH395 "," AF370 : AZ395"," AX370 : BR395"," BP370 : CJ395 " )
NdcArr14 = Array(" A400 : R425"," P400 : AH425 "," AF400 : AZ425"," AX400 : BR425"," BP400 : CJ425 " )
NdcArr15 = Array(" A430 : R455"," P430 : AH455 "," AF430 : AZ455"," AX430 : BR455"," BP430 : CJ455 " )
NdcArr16 = Array(" A460 : R485"," P460 : AH485 "," AF460 : AZ485"," AX460 : BR485"," BP460 : CJ485 " )
NdcArr17 = Array(" A490 : R515"," P490 : AH515 "," AF490 : AZ515"," AX490 : BR515"," BP490 : CJ515 " )
NdcArr18 = Array(" A520 : R545"," P520 : AH545 "," AF520 : AZ545"," AX520 : BR545"," BP520 : CJ545 " )
NdcArr19 = Array(" A550 : R575"," P550 : AH575 "," AF550 : AZ575"," AX550 : BR575"," BP550 : CJ575 " )
NdcArr20 = Array(" A580 : R605"," P580 : AH605 "," AF580 : AZ605"," AX580 : BR605"," BP580 : CJ605 " )
NdcArr21 = Array(" A610 : R635"," P610 : AH635 "," AF610 : AZ635"," AX610 : BR635"," BP610 : CJ635 " )
NdcArr22 = Array(" A640 : R665"," P640 : AH665 "," AF640 : AZ665"," AX640 : BR665"," BP640 : CJ665 " )
NdcArr23 = Array(" A670 : R695"," P670 : AH695 "," AF670 : AZ695"," AX670 : BR695"," BP670 : CJ695 " )
NdcArr24 = Array(" A700 : R725"," P700 : AH725 "," AF700 : AZ725"," AX700 : BR725"," BP700 : CJ725 " )
NdcArr25 = Array(" A730 : R755"," P730 : AH755 "," AF730 : AZ755"," AX730 : BR755"," BP730 : CJ755 " )
NdcArr26 = Array(" A760 : R785"," P760 : AH785 "," AF760 : AZ785"," AX760 : BR785"," BP760 : CJ785 " )
NdcArr27 = Array(" A790 : R815"," P790 : AH815 "," AF790 : AZ815"," AX790 : BR815"," BP790 : CJ815 " )
NdcArr28 = Array(" A820 : R845"," P820 : AH845 "," AF820 : AZ845"," AX820 : BR845"," BP820 : CJ845 " )
NdcArr29 = Array(" A850 : R875"," P850 : AH875 "," AF850 : AZ875"," AX850 : BR875"," BP850 : CJ875 " )
NdcArr30 = Array(" A880 : R905"," P880 : AH905 "," AF880 : AZ905"," AX880 : BR905"," BP880 : CJ905 " )
NdcArr31 = Array(" A910 : R935"," P910 : AH935 "," AF910 : AZ935"," AX910 : BR935"," BP910 : CJ935 " )
NdcArr32 = Array(" A940 : R965"," P940 : AH965 "," AF940 : AZ965"," AX940 : BR965"," BP940 : CJ965 " )
NdcArr33 = Array(" A970 : R995"," P970 : AH995 "," AF970 : AZ995"," AX970 : BR995"," BP970 : CJ995 " )
NdcArr34 = Array(" A1000 : R1025"," P1000 : AH1025 "," AF1000 : AZ1025"," AX1000 : BR1025"," BP1000 : CJ1025 " )
NdcArr35 = Array(" A1030 : R1055"," P1030 : AH1055 "," AF1030 : AZ1055"," AX1030 : BR1055"," BP1030 : CJ1055 " )
NdcArr36 = Array(" A1060 : R1085"," P1060 : AH1085 "," AF1060 : AZ1085"," AX1060 : BR1085"," BP1060 : CJ1085 " )
NdcArr37 = Array(" A1090 : R1115"," P1090 : AH1115 "," AF1090 : AZ1115"," AX1090 : BR1115"," BP1090 : CJ1115 " )
NdcArr38 = Array(" A1120 : R1145"," P1120 : AH1145 "," AF1120 : AZ1145"," AX1120 : BR1145"," BP1120 : CJ1145 " )
NdcArr39 = Array(" A1150 : R1175"," P1150 : AH1175 "," AF1150 : AZ1175"," AX1150 : BR1175"," BP1150 : CJ1175 " )
NdcArr40 = Array(" A1180 : R1205"," P1180 : AH1205 "," AF1180 : AZ1205"," AX1180 : BR1205"," BP1180 : CJ1205 " )
NdcArr41 = Array(" A1210 : R1235"," P1210 : AH1235 "," AF1210 : AZ1235"," AX1210 : BR1235"," BP1210 : CJ1235 " )
NdcArr42 = Array(" A1240 : R1265"," P1240 : AH1265 "," AF1240 : AZ1265"," AX1240 : BR1265"," BP1240 : CJ1265 " )
NdcArr43 = Array(" A1270 : R1295"," P1270 : AH1295 "," AF1270 : AZ1295"," AX1270 : BR1295"," BP1270 : CJ1295 " )
NdcArr44 = Array(" A1300 : R1325"," P1300 : AH1325 "," AF1300 : AZ1325"," AX1300 : BR1325"," BP1300 : CJ1325 " )
NdcArr45 = Array(" A1330 : R1355"," P1330 : AH1355 "," AF1330 : AZ1355"," AX1330 : BR1355"," BP1330 : CJ1355 " )
NdcArr46 = Array(" A1360 : R1385"," P1360 : AH1385 "," AF1360 : AZ1385"," AX1360 : BR1385"," BP1360 : CJ1385 " )
NdcArr47 = Array(" A1390 : R1415"," P1390 : AH1415 "," AF1390 : AZ1415"," AX1390 : BR1415"," BP1390 : CJ1415 " )
NdcArr48 = Array(" A1420 : R1445"," P1420 : AH1445 "," AF1420 : AZ1445"," AX1420 : BR1445"," BP1420 : CJ1445 " )
NdcArr49 = Array(" A1450 : R1475"," P1450 : AH1475 "," AF1450 : AZ1475"," AX1450 : BR1475"," BP1450 : CJ1475 " )
NdcArr50 = Array(" A1480 : R1505"," P1480 : AH1505 "," AF1480 : AZ1505"," AX1480 : BR1505"," BP1480 : CJ1505 " )
NdcArr51 = Array(" A1510 : R1535"," P1510 : AH1535 "," AF1510 : AZ1535"," AX1510 : BR1535"," BP1510 : CJ1535 " )
NdcArr52 = Array(" A1540 : R1565"," P1540 : AH1565 "," AF1540 : AZ1565"," AX1540 : BR1565"," BP1540 : CJ1565 " )
NdcArr53 = Array(" A1570 : R1595"," P1570 : AH1595 "," AF1570 : AZ1595"," AX1570 : BR1595"," BP1570 : CJ1595 " )
NdcArr54 = Array(" A1600 : R1625"," P1600 : AH1625 "," AF1600 : AZ1625"," AX1600 : BR1625"," BP1600 : CJ1625 " )
NdcArr55 = Array(" A1630 : R1655"," P1630 : AH1655 "," AF1630 : AZ1655"," AX1630 : BR1655"," BP1630 : CJ1655 " )
NdcArr56 = Array(" A1660 : R1685"," P1660 : AH1685 "," AF1660 : AZ1685"," AX1660 : BR1685"," BP1660 : CJ1685 " )
NdcArr57 = Array(" A1690 : R1715"," P1690 : AH1715 "," AF1690 : AZ1715"," AX1690 : BR1715"," BP1690 : CJ1715 " )
NdcArr58 = Array(" A1720 : R1745"," P1720 : AH1745 "," AF1720 : AZ1745"," AX1720 : BR1745"," BP1720 : CJ1745 " )
NdcArr59 = Array(" A1750 : R1775"," P1750 : AH1775 "," AF1750 : AZ1775"," AX1750 : BR1775"," BP1750 : CJ1775 " )
NdcArr60 = Array(" A1780 : R1805"," P1780 : AH1805 "," AF1780 : AZ1805"," AX1780 : BR1805"," BP1780 : CJ1805 " )
NdcArr61 = Array(" A1810 : R1835"," P1810 : AH1835 "," AF1810 : AZ1835"," AX1810 : BR1835"," BP1810 : CJ1835 " )
NdcArr62 = Array(" A1840 : R1865"," P1840 : AH1865 "," AF1840 : AZ1865"," AX1840 : BR1865"," BP1840 : CJ1865 " )
NdcArr63 = Array(" A1870 : R1895"," P1870 : AH1895 "," AF1870 : AZ1895"," AX1870 : BR1895"," BP1870 : CJ1895 " )
NdcArr64 = Array(" A1900 : R1925"," P1900 : AH1925 "," AF1900 : AZ1925"," AX1900 : BR1925"," BP1900 : CJ1925 " )
NdcArr65 = Array(" A1930 : R1955"," P1930 : AH1955 "," AF1930 : AZ1955"," AX1930 : BR1955"," BP1930 : CJ1955 " )
NdcArr66 = Array(" A1960 : R1985"," P1960 : AH1985 "," AF1960 : AZ1985"," AX1960 : BR1985"," BP1960 : CJ1985 " )
NdcArr67 = Array(" A1990 : R2015"," P1990 : AH2015 "," AF1990 : AZ2015"," AX1990 : BR2015"," BP1990 : CJ2015 " )
NdcArr68 = Array(" A2020 : R2045"," P2020 : AH2045 "," AF2020 : AZ2045"," AX2020 : BR2045"," BP2020 : CJ2045 " )
NdcArr69 = Array(" A2050 : R2075"," P2050 : AH2075 "," AF2050 : AZ2075"," AX2050 : BR2075"," BP2050 : CJ2075 " )
NdcArr70 = Array(" A2080 : R2105"," P2080 : AH2105 "," AF2080 : AZ2105"," AX2080 : BR2105"," BP2080 : CJ2105 " )
NdcArr71 = Array(" A2110 : R2135"," P2110 : AH2135 "," AF2110 : AZ2135"," AX2110 : BR2135"," BP2110 : CJ2135 " )
NdcArr72 = Array(" A2140 : R2165"," P2140 : AH2165 "," AF2140 : AZ2165"," AX2140 : BR2165"," BP2140 : CJ2165 " )
NdcArr73 = Array(" A2170 : R2195"," P2170 : AH2195 "," AF2170 : AZ2195"," AX2170 : BR2195"," BP2170 : CJ2195 " )
NdcArr74 = Array(" A2200 : R2225"," P2200 : AH2225 "," AF2200 : AZ2225"," AX2200 : BR2225"," BP2200 : CJ2225 " )
NdcArr75 = Array(" A2230 : R2255"," P2230 : AH2255 "," AF2230 : AZ2255"," AX2230 : BR2255"," BP2230 : CJ2255 " )
NdcArr76 = Array(" A2260 : R2285"," P2260 : AH2285 "," AF2260 : AZ2285"," AX2260 : BR2285"," BP2260 : CJ2285 " )
NdcArr77 = Array(" A2290 : R2315"," P2290 : AH2315 "," AF2290 : AZ2315"," AX2290 : BR2315"," BP2290 : CJ2315 " )
NdcArr78 = Array(" A2320 : R2345"," P2320 : AH2345 "," AF2320 : AZ2345"," AX2320 : BR2345"," BP2320 : CJ2345 " )
NdcArr79 = Array(" A2350 : R2375"," P2350 : AH2375 "," AF2350 : AZ2375"," AX2350 : BR2375"," BP2350 : CJ2375 " )
NdcArr80 = Array(" A2380 : R2405"," P2380 : AH2405 "," AF2380 : AZ2405"," AX2380 : BR2405"," BP2380 : CJ2405 " )
NdcArr81 = Array(" A2410 : R2435"," P2410 : AH2435 "," AF2410 : AZ2435"," AX2410 : BR2435"," BP2410 : CJ2435 " )
NdcArr82 = Array(" A2440 : R2465"," P2440 : AH2465 "," AF2440 : AZ2465"," AX2440 : BR2465"," BP2440 : CJ2465 " )
NdcArr83 = Array(" A2470 : R2495"," P2470 : AH2495 "," AF2470 : AZ2495"," AX2470 : BR2495"," BP2470 : CJ2495 " )
NdcArr84 = Array(" A2500 : R2525"," P2500 : AH2525 "," AF2500 : AZ2525"," AX2500 : BR2525"," BP2500 : CJ2525 " )
NdcArr85 = Array(" A2530 : R2555"," P2530 : AH2555 "," AF2530 : AZ2555"," AX2530 : BR2555"," BP2530 : CJ2555 " )
NdcArr86 = Array(" A2560 : R2585"," P2560 : AH2585 "," AF2560 : AZ2585"," AX2560 : BR2585"," BP2560 : CJ2585 " )
NdcArr87 = Array(" A2590 : R2615"," P2590 : AH2615 "," AF2590 : AZ2615"," AX2590 : BR2615"," BP2590 : CJ2615 " )
NdcArr88 = Array(" A2620 : R2645"," P2620 : AH2645 "," AF2620 : AZ2645"," AX2620 : BR2645"," BP2620 : CJ2645 " )
NdcArr89 = Array(" A2650 : R2675"," P2650 : AH2675 "," AF2650 : AZ2675"," AX2650 : BR2675"," BP2650 : CJ2675 " )
NdcArr90 = Array(" A2680 : R2705"," P2680 : AH2705 "," AF2680 : AZ2705"," AX2680 : BR2705"," BP2680 : CJ2705 " )
NdcArr91 = Array(" A2710 : R2735"," P2710 : AH2735 "," AF2710 : AZ2735"," AX2710 : BR2735"," BP2710 : CJ2735 " )
NdcArr92 = Array(" A2740 : R2765"," P2740 : AH2765 "," AF2740 : AZ2765"," AX2740 : BR2765"," BP2740 : CJ2765 " )
NdcArr93 = Array(" A2770 : R2795"," P2770 : AH2795 "," AF2770 : AZ2795"," AX2770 : BR2795"," BP2770 : CJ2795 " )
NdcArr94 = Array(" A2800 : R2825"," P2800 : AH2825 "," AF2800 : AZ2825"," AX2800 : BR2825"," BP2800 : CJ2825 " )
NdcArr95 = Array(" A2830 : R2855"," P2830 : AH2855 "," AF2830 : AZ2855"," AX2830 : BR2855"," BP2830 : CJ2855 " )
NdcArr96 = Array(" A2860 : R2885"," P2860 : AH2885 "," AF2860 : AZ2885"," AX2860 : BR2885"," BP2860 : CJ2885 " )
NdcArr97 = Array(" A2890 : R2915"," P2890 : AH2915 "," AF2890 : AZ2915"," AX2890 : BR2915"," BP2890 : CJ2915 " )
NdcArr98 = Array(" A2920 : R2945"," P2920 : AH2945 "," AF2920 : AZ2945"," AX2920 : BR2945"," BP2920 : CJ2945 " )
NdcArr99 = Array(" A2950 : R2975"," P2950 : AH2975 "," AF2950 : AZ2975"," AX2950 : BR2975"," BP2950 : CJ2975 " )
NdcArr100 = Array(" A2980 : R3005"," P2980 : AH3005 "," AF2980 : AZ3005"," AX2980 : BR3005"," BP2980 : CJ3005 " )
NdcArr101 = Array(" A3010 : R3035"," P3010 : AH3035 "," AF3010 : AZ3035"," AX3010 : BR3035"," BP3010 : CJ3035 " )
NdcArr102 = Array(" A3040 : R3065"," P3040 : AH3065 "," AF3040 : AZ3065"," AX3040 : BR3065"," BP3040 : CJ3065 " )
NdcArr103 = Array(" A3070 : R3095"," P3070 : AH3095 "," AF3070 : AZ3095"," AX3070 : BR3095"," BP3070 : CJ3095 " )
NdcArr104 = Array(" A3100 : R3125"," P3100 : AH3125 "," AF3100 : AZ3125"," AX3100 : BR3125"," BP3100 : CJ3125 " )
NdcArr105 = Array(" A3130 : R3155"," P3130 : AH3155 "," AF3130 : AZ3155"," AX3130 : BR3155"," BP3130 : CJ3155 " )
NdcArr106 = Array(" A3160 : R3185"," P3160 : AH3185 "," AF3160 : AZ3185"," AX3160 : BR3185"," BP3160 : CJ3185 " )
NdcArr107 = Array(" A3190 : R3215"," P3190 : AH3215 "," AF3190 : AZ3215"," AX3190 : BR3215"," BP3190 : CJ3215 " )
NdcArr108 = Array(" A3220 : R3245"," P3220 : AH3245 "," AF3220 : AZ3245"," AX3220 : BR3245"," BP3220 : CJ3245 " )
NdcArr109 = Array(" A3250 : R3275"," P3250 : AH3275 "," AF3250 : AZ3275"," AX3250 : BR3275"," BP3250 : CJ3275 " )
NdcArr110 = Array(" A3280 : R3305"," P3280 : AH3305 "," AF3280 : AZ3305"," AX3280 : BR3305"," BP3280 : CJ3305 " )
NdcArr111 = Array(" A3310 : R3335"," P3310 : AH3335 "," AF3310 : AZ3335"," AX3310 : BR3335"," BP3310 : CJ3335 " )
NdcArr112 = Array(" A3340 : R3365"," P3340 : AH3365 "," AF3340 : AZ3365"," AX3340 : BR3365"," BP3340 : CJ3365 " )
NdcArr113 = Array(" A3370 : R3395"," P3370 : AH3395 "," AF3370 : AZ3395"," AX3370 : BR3395"," BP3370 : CJ3395 " )
NdcArr114 = Array(" A3400 : R3425"," P3400 : AH3425 "," AF3400 : AZ3425"," AX3400 : BR3425"," BP3400 : CJ3425 " )
NdcArr115 = Array(" A3430 : R3455"," P3430 : AH3455 "," AF3430 : AZ3455"," AX3430 : BR3455"," BP3430 : CJ3455 " )
NdcArr116 = Array(" A3460 : R3485"," P3460 : AH3485 "," AF3460 : AZ3485"," AX3460 : BR3485"," BP3460 : CJ3485 " )
NdcArr117 = Array(" A3490 : R3515"," P3490 : AH3515 "," AF3490 : AZ3515"," AX3490 : BR3515"," BP3490 : CJ3515 " )
NdcArr118 = Array(" A3520 : R3545"," P3520 : AH3545 "," AF3520 : AZ3545"," AX3520 : BR3545"," BP3520 : CJ3545 " )
NdcArr119 = Array(" A3550 : R3575"," P3550 : AH3575 "," AF3550 : AZ3575"," AX3550 : BR3575"," BP3550 : CJ3575 " )
NdcArr120 = Array(" A3580 : R3605"," P3580 : AH3605 "," AF3580 : AZ3605"," AX3580 : BR3605"," BP3580 : CJ3605 " )
NdcArr121 = Array(" A3610 : R3635"," P3610 : AH3635 "," AF3610 : AZ3635"," AX3610 : BR3635"," BP3610 : CJ3635 " )
NdcArr122 = Array(" A3640 : R3665"," P3640 : AH3665 "," AF3640 : AZ3665"," AX3640 : BR3665"," BP3640 : CJ3665 " )
NdcArr123 = Array(" A3670 : R3695"," P3670 : AH3695 "," AF3670 : AZ3695"," AX3670 : BR3695"," BP3670 : CJ3695 " )
NdcArr124 = Array(" A3700 : R3725"," P3700 : AH3725 "," AF3700 : AZ3725"," AX3700 : BR3725"," BP3700 : CJ3725 " )
NdcArr125 = Array(" A3730 : R3755"," P3730 : AH3755 "," AF3730 : AZ3755"," AX3730 : BR3755"," BP3730 : CJ3755 " )
NdcArr126 = Array(" A3760 : R3785"," P3760 : AH3785 "," AF3760 : AZ3785"," AX3760 : BR3785"," BP3760 : CJ3785 " )
NdcArr127 = Array(" A3790 : R3815"," P3790 : AH3815 "," AF3790 : AZ3815"," AX3790 : BR3815"," BP3790 : CJ3815 " )
NdcArr128 = Array(" A3820 : R3845"," P3820 : AH3845 "," AF3820 : AZ3845"," AX3820 : BR3845"," BP3820 : CJ3845 " )
NdcArr129 = Array(" A3850 : R3875"," P3850 : AH3875 "," AF3850 : AZ3875"," AX3850 : BR3875"," BP3850 : CJ3875 " )
NdcArr130 = Array(" A3880 : R3905"," P3880 : AH3905 "," AF3880 : AZ3905"," AX3880 : BR3905"," BP3880 : CJ3905 " )
NdcArr131 = Array(" A3910 : R3935"," P3910 : AH3935 "," AF3910 : AZ3935"," AX3910 : BR3935"," BP3910 : CJ3935 " )
NdcArr132 = Array(" A3940 : R3965"," P3940 : AH3965 "," AF3940 : AZ3965"," AX3940 : BR3965"," BP3940 : CJ3965 " )
NdcArr133 = Array(" A3970 : R3995"," P3970 : AH3995 "," AF3970 : AZ3995"," AX3970 : BR3995"," BP3970 : CJ3995 " )
NdcArr134 = Array(" A4000 : R4025"," P4000 : AH4025 "," AF4000 : AZ4025"," AX4000 : BR4025"," BP4000 : CJ4025 " )
NdcArr135 = Array(" A4030 : R4055"," P4030 : AH4055 "," AF4030 : AZ4055"," AX4030 : BR4055"," BP4030 : CJ4055 " )
NdcArr136 = Array(" A4060 : R4085"," P4060 : AH4085 "," AF4060 : AZ4085"," AX4060 : BR4085"," BP4060 : CJ4085 " )
NdcArr137 = Array(" A4090 : R4115"," P4090 : AH4115 "," AF4090 : AZ4115"," AX4090 : BR4115"," BP4090 : CJ4115 " )
NdcArr138 = Array(" A4120 : R4145"," P4120 : AH4145 "," AF4120 : AZ4145"," AX4120 : BR4145"," BP4120 : CJ4145 " )
NdcArr139 = Array(" A4150 : R4175"," P4150 : AH4175 "," AF4150 : AZ4175"," AX4150 : BR4175"," BP4150 : CJ4175 " )
NdcArr140 = Array(" A4180 : R4205"," P4180 : AH4205 "," AF4180 : AZ4205"," AX4180 : BR4205"," BP4180 : CJ4205 " )
NdcArr141 = Array(" A4210 : R4235"," P4210 : AH4235 "," AF4210 : AZ4235"," AX4210 : BR4235"," BP4210 : CJ4235 " )
NdcArr142 = Array(" A4240 : R4265"," P4240 : AH4265 "," AF4240 : AZ4265"," AX4240 : BR4265"," BP4240 : CJ4265 " )
NdcArr143 = Array(" A4270 : R4295"," P4270 : AH4295 "," AF4270 : AZ4295"," AX4270 : BR4295"," BP4270 : CJ4295 " )
NdcArr144 = Array(" A4300 : R4325"," P4300 : AH4325 "," AF4300 : AZ4325"," AX4300 : BR4325"," BP4300 : CJ4325 " )
NdcArr145 = Array(" A4330 : R4355"," P4330 : AH4355 "," AF4330 : AZ4355"," AX4330 : BR4355"," BP4330 : CJ4355 " )
NdcArr146 = Array(" A4360 : R4385"," P4360 : AH4385 "," AF4360 : AZ4385"," AX4360 : BR4385"," BP4360 : CJ4385 " )
NdcArr147 = Array(" A4390 : R4415"," P4390 : AH4415 "," AF4390 : AZ4415"," AX4390 : BR4415"," BP4390 : CJ4415 " )
NdcArr148 = Array(" A4420 : R4445"," P4420 : AH4445 "," AF4420 : AZ4445"," AX4420 : BR4445"," BP4420 : CJ4445 " )
NdcArr149 = Array(" A4450 : R4475"," P4450 : AH4475 "," AF4450 : AZ4475"," AX4450 : BR4475"," BP4450 : CJ4475 " )
NdcArr150 = Array(" A4480 : R4505"," P4480 : AH4505 "," AF4480 : AZ4505"," AX4480 : BR4505"," BP4480 : CJ4505 " )
NdcArr151 = Array(" A4510 : R4535"," P4510 : AH4535 "," AF4510 : AZ4535"," AX4510 : BR4535"," BP4510 : CJ4535 " )
NdcArr152 = Array(" A4540 : R4565"," P4540 : AH4565 "," AF4540 : AZ4565"," AX4540 : BR4565"," BP4540 : CJ4565 " )
NdcArr153 = Array(" A4570 : R4595"," P4570 : AH4595 "," AF4570 : AZ4595"," AX4570 : BR4595"," BP4570 : CJ4595 " )
NdcArr154 = Array(" A4600 : R4625"," P4600 : AH4625 "," AF4600 : AZ4625"," AX4600 : BR4625"," BP4600 : CJ4625 " )
NdcArr155 = Array(" A4630 : R4655"," P4630 : AH4655 "," AF4630 : AZ4655"," AX4630 : BR4655"," BP4630 : CJ4655 " )
NdcArr156 = Array(" A4660 : R4685"," P4660 : AH4685 "," AF4660 : AZ4685"," AX4660 : BR4685"," BP4660 : CJ4685 " )
NdcArr157 = Array(" A4690 : R4715"," P4690 : AH4715 "," AF4690 : AZ4715"," AX4690 : BR4715"," BP4690 : CJ4715 " )
NdcArr158 = Array(" A4720 : R4745"," P4720 : AH4745 "," AF4720 : AZ4745"," AX4720 : BR4745"," BP4720 : CJ4745 " )
NdcArr159 = Array(" A4750 : R4775"," P4750 : AH4775 "," AF4750 : AZ4775"," AX4750 : BR4775"," BP4750 : CJ4775 " )
NdcArr160 = Array(" A4780 : R4805"," P4780 : AH4805 "," AF4780 : AZ4805"," AX4780 : BR4805"," BP4780 : CJ4805 " )
NdcArr161 = Array(" A4810 : R4835"," P4810 : AH4835 "," AF4810 : AZ4835"," AX4810 : BR4835"," BP4810 : CJ4835 " )
NdcArr162 = Array(" A4840 : R4865"," P4840 : AH4865 "," AF4840 : AZ4865"," AX4840 : BR4865"," BP4840 : CJ4865 " )
NdcArr163 = Array(" A4870 : R4895"," P4870 : AH4895 "," AF4870 : AZ4895"," AX4870 : BR4895"," BP4870 : CJ4895 " )
NdcArr164 = Array(" A4900 : R4925"," P4900 : AH4925 "," AF4900 : AZ4925"," AX4900 : BR4925"," BP4900 : CJ4925 " )
NdcArr165 = Array(" A4930 : R4955"," P4930 : AH4955 "," AF4930 : AZ4955"," AX4930 : BR4955"," BP4930 : CJ4955 " )
NdcArr166 = Array(" A4960 : R4985"," P4960 : AH4985 "," AF4960 : AZ4985"," AX4960 : BR4985"," BP4960 : CJ4985 " )
NdcArr167 = Array(" A4990 : R5015"," P4990 : AH5015 "," AF4990 : AZ5015"," AX4990 : BR5015"," BP4990 : CJ5015 " )
NdcArr168 = Array(" A5020 : R5045"," P5020 : AH5045 "," AF5020 : AZ5045"," AX5020 : BR5045"," BP5020 : CJ5045 " )
NdcArr169 = Array(" A5050 : R5075"," P5050 : AH5075 "," AF5050 : AZ5075"," AX5050 : BR5075"," BP5050 : CJ5075 " )
NdcArr170 = Array(" A5080 : R5105"," P5080 : AH5105 "," AF5080 : AZ5105"," AX5080 : BR5105"," BP5080 : CJ5105 " )
NdcArr171 = Array(" A5110 : R5135"," P5110 : AH5135 "," AF5110 : AZ5135"," AX5110 : BR5135"," BP5110 : CJ5135 " )
NdcArr172 = Array(" A5140 : R5165"," P5140 : AH5165 "," AF5140 : AZ5165"," AX5140 : BR5165"," BP5140 : CJ5165 " )
NdcArr173 = Array(" A5170 : R5195"," P5170 : AH5195 "," AF5170 : AZ5195"," AX5170 : BR5195"," BP5170 : CJ5195 " )
NdcArr174 = Array(" A5200 : R5225"," P5200 : AH5225 "," AF5200 : AZ5225"," AX5200 : BR5225"," BP5200 : CJ5225 " )
NdcArr175 = Array(" A5230 : R5255"," P5230 : AH5255 "," AF5230 : AZ5255"," AX5230 : BR5255"," BP5230 : CJ5255 " )
NdcArr176 = Array(" A5260 : R5285"," P5260 : AH5285 "," AF5260 : AZ5285"," AX5260 : BR5285"," BP5260 : CJ5285 " )
NdcArr177 = Array(" A5290 : R5315"," P5290 : AH5315 "," AF5290 : AZ5315"," AX5290 : BR5315"," BP5290 : CJ5315 " )
NdcArr178 = Array(" A5320 : R5345"," P5320 : AH5345 "," AF5320 : AZ5345"," AX5320 : BR5345"," BP5320 : CJ5345 " )
NdcArr179 = Array(" A5350 : R5375"," P5350 : AH5375 "," AF5350 : AZ5375"," AX5350 : BR5375"," BP5350 : CJ5375 " )
NdcArr180 = Array(" A5380 : R5405"," P5380 : AH5405 "," AF5380 : AZ5405"," AX5380 : BR5405"," BP5380 : CJ5405 " )
NdcArr181 = Array(" A5410 : R5435"," P5410 : AH5435 "," AF5410 : AZ5435"," AX5410 : BR5435"," BP5410 : CJ5435 " )
NdcArr182 = Array(" A5440 : R5465"," P5440 : AH5465 "," AF5440 : AZ5465"," AX5440 : BR5465"," BP5440 : CJ5465 " )
NdcArr183 = Array(" A5470 : R5495"," P5470 : AH5495 "," AF5470 : AZ5495"," AX5470 : BR5495"," BP5470 : CJ5495 " )
NdcArr184 = Array(" A5500 : R5525"," P5500 : AH5525 "," AF5500 : AZ5525"," AX5500 : BR5525"," BP5500 : CJ5525 " )
NdcArr185 = Array(" A5530 : R5555"," P5530 : AH5555 "," AF5530 : AZ5555"," AX5530 : BR5555"," BP5530 : CJ5555 " )
NdcArr186 = Array(" A5560 : R5585"," P5560 : AH5585 "," AF5560 : AZ5585"," AX5560 : BR5585"," BP5560 : CJ5585 " )
NdcArr187 = Array(" A5590 : R5615"," P5590 : AH5615 "," AF5590 : AZ5615"," AX5590 : BR5615"," BP5590 : CJ5615 " )
NdcArr188 = Array(" A5620 : R5645"," P5620 : AH5645 "," AF5620 : AZ5645"," AX5620 : BR5645"," BP5620 : CJ5645 " )
NdcArr189 = Array(" A5650 : R5675"," P5650 : AH5675 "," AF5650 : AZ5675"," AX5650 : BR5675"," BP5650 : CJ5675 " )
NdcArr190 = Array(" A5680 : R5705"," P5680 : AH5705 "," AF5680 : AZ5705"," AX5680 : BR5705"," BP5680 : CJ5705 " )
NdcArr191 = Array(" A5710 : R5735"," P5710 : AH5735 "," AF5710 : AZ5735"," AX5710 : BR5735"," BP5710 : CJ5735 " )
NdcArr192 = Array(" A5740 : R5765"," P5740 : AH5765 "," AF5740 : AZ5765"," AX5740 : BR5765"," BP5740 : CJ5765 " )
NdcArr193 = Array(" A5770 : R5795"," P5770 : AH5795 "," AF5770 : AZ5795"," AX5770 : BR5795"," BP5770 : CJ5795 " )
NdcArr194 = Array(" A5800 : R5825"," P5800 : AH5825 "," AF5800 : AZ5825"," AX5800 : BR5825"," BP5800 : CJ5825 " )
NdcArr195 = Array(" A5830 : R5855"," P5830 : AH5855 "," AF5830 : AZ5855"," AX5830 : BR5855"," BP5830 : CJ5855 " )
NdcArr196 = Array(" A5860 : R5885"," P5860 : AH5885 "," AF5860 : AZ5885"," AX5860 : BR5885"," BP5860 : CJ5885 " )
NdcArr197 = Array(" A5890 : R5915"," P5890 : AH5915 "," AF5890 : AZ5915"," AX5890 : BR5915"," BP5890 : CJ5915 " )
NdcArr198 = Array(" A5920 : R5945"," P5920 : AH5945 "," AF5920 : AZ5945"," AX5920 : BR5945"," BP5920 : CJ5945 " )
NdcArr199 = Array(" A5950 : R5975"," P5950 : AH5975 "," AF5950 : AZ5975"," AX5950 : BR5975"," BP5950 : CJ5975 " )
NdcArr200 = Array(" A5980 : R6005"," P5980 : AH6005 "," AF5980 : AZ6005"," AX5980 : BR6005"," BP5980 : CJ6005 " )
NdcArr201 = Array(" A6010 : R6035"," P6010 : AH6035 "," AF6010 : AZ6035"," AX6010 : BR6035"," BP6010 : CJ6035 " )
NdcArr202 = Array(" A6040 : R6065"," P6040 : AH6065 "," AF6040 : AZ6065"," AX6040 : BR6065"," BP6040 : CJ6065 " )
NdcArr203 = Array(" A6070 : R6095"," P6070 : AH6095 "," AF6070 : AZ6095"," AX6070 : BR6095"," BP6070 : CJ6095 " )
NdcArr204 = Array(" A6100 : R6125"," P6100 : AH6125 "," AF6100 : AZ6125"," AX6100 : BR6125"," BP6100 : CJ6125 " )
NdcArr205 = Array(" A6130 : R6155"," P6130 : AH6155 "," AF6130 : AZ6155"," AX6130 : BR6155"," BP6130 : CJ6155 " )
NdcArr206 = Array(" A6160 : R6185"," P6160 : AH6185 "," AF6160 : AZ6185"," AX6160 : BR6185"," BP6160 : CJ6185 " )
NdcArr207 = Array(" A6190 : R6215"," P6190 : AH6215 "," AF6190 : AZ6215"," AX6190 : BR6215"," BP6190 : CJ6215 " )
NdcArr208 = Array(" A6220 : R6245"," P6220 : AH6245 "," AF6220 : AZ6245"," AX6220 : BR6245"," BP6220 : CJ6245 " )
NdcArr209 = Array(" A6250 : R6275"," P6250 : AH6275 "," AF6250 : AZ6275"," AX6250 : BR6275"," BP6250 : CJ6275 " )
NdcArr210 = Array(" A6280 : R6305"," P6280 : AH6305 "," AF6280 : AZ6305"," AX6280 : BR6305"," BP6280 : CJ6305 " )
NdcArr211 = Array(" A6310 : R6335"," P6310 : AH6335 "," AF6310 : AZ6335"," AX6310 : BR6335"," BP6310 : CJ6335 " )
NdcArr212 = Array(" A6340 : R6365"," P6340 : AH6365 "," AF6340 : AZ6365"," AX6340 : BR6365"," BP6340 : CJ6365 " )
NdcArr213 = Array(" A6370 : R6395"," P6370 : AH6395 "," AF6370 : AZ6395"," AX6370 : BR6395"," BP6370 : CJ6395 " )
NdcArr214 = Array(" A6400 : R6425"," P6400 : AH6425 "," AF6400 : AZ6425"," AX6400 : BR6425"," BP6400 : CJ6425 " )
NdcArr215 = Array(" A6430 : R6455"," P6430 : AH6455 "," AF6430 : AZ6455"," AX6430 : BR6455"," BP6430 : CJ6455 " )
NdcArr216 = Array(" A6460 : R6485"," P6460 : AH6485 "," AF6460 : AZ6485"," AX6460 : BR6485"," BP6460 : CJ6485 " )
NdcArr217 = Array(" A6490 : R6515"," P6490 : AH6515 "," AF6490 : AZ6515"," AX6490 : BR6515"," BP6490 : CJ6515 " )
NdcArr218 = Array(" A6520 : R6545"," P6520 : AH6545 "," AF6520 : AZ6545"," AX6520 : BR6545"," BP6520 : CJ6545 " )
NdcArr219 = Array(" A6550 : R6575"," P6550 : AH6575 "," AF6550 : AZ6575"," AX6550 : BR6575"," BP6550 : CJ6575 " )
NdcArr220 = Array(" A6580 : R6605"," P6580 : AH6605 "," AF6580 : AZ6605"," AX6580 : BR6605"," BP6580 : CJ6605 " )
NdcArr221 = Array(" A6610 : R6635"," P6610 : AH6635 "," AF6610 : AZ6635"," AX6610 : BR6635"," BP6610 : CJ6635 " )
NdcArr222 = Array(" A6640 : R6665"," P6640 : AH6665 "," AF6640 : AZ6665"," AX6640 : BR6665"," BP6640 : CJ6665 " )
NdcArr223 = Array(" A6670 : R6695"," P6670 : AH6695 "," AF6670 : AZ6695"," AX6670 : BR6695"," BP6670 : CJ6695 " )
NdcArr224 = Array(" A6700 : R6725"," P6700 : AH6725 "," AF6700 : AZ6725"," AX6700 : BR6725"," BP6700 : CJ6725 " )
NdcArr225 = Array(" A6730 : R6755"," P6730 : AH6755 "," AF6730 : AZ6755"," AX6730 : BR6755"," BP6730 : CJ6755 " )
NdcArr226 = Array(" A6760 : R6785"," P6760 : AH6785 "," AF6760 : AZ6785"," AX6760 : BR6785"," BP6760 : CJ6785 " )
NdcArr227 = Array(" A6790 : R6815"," P6790 : AH6815 "," AF6790 : AZ6815"," AX6790 : BR6815"," BP6790 : CJ6815 " )
NdcArr228 = Array(" A6820 : R6845"," P6820 : AH6845 "," AF6820 : AZ6845"," AX6820 : BR6845"," BP6820 : CJ6845 " )
NdcArr229 = Array(" A6850 : R6875"," P6850 : AH6875 "," AF6850 : AZ6875"," AX6850 : BR6875"," BP6850 : CJ6875 " )
NdcArr230 = Array(" A6880 : R6905"," P6880 : AH6905 "," AF6880 : AZ6905"," AX6880 : BR6905"," BP6880 : CJ6905 " )
NdcArr231 = Array(" A6910 : R6935"," P6910 : AH6935 "," AF6910 : AZ6935"," AX6910 : BR6935"," BP6910 : CJ6935 " )
NdcArr232 = Array(" A6940 : R6965"," P6940 : AH6965 "," AF6940 : AZ6965"," AX6940 : BR6965"," BP6940 : CJ6965 " )
NdcArr233 = Array(" A6970 : R6995"," P6970 : AH6995 "," AF6970 : AZ6995"," AX6970 : BR6995"," BP6970 : CJ6995 " )
NdcArr234 = Array(" A7000 : R7025"," P7000 : AH7025 "," AF7000 : AZ7025"," AX7000 : BR7025"," BP7000 : CJ7025 " )
NdcArr235 = Array(" A7030 : R7055"," P7030 : AH7055 "," AF7030 : AZ7055"," AX7030 : BR7055"," BP7030 : CJ7055 " )
NdcArr236 = Array(" A7060 : R7085"," P7060 : AH7085 "," AF7060 : AZ7085"," AX7060 : BR7085"," BP7060 : CJ7085 " )
NdcArr237 = Array(" A7090 : R7115"," P7090 : AH7115 "," AF7090 : AZ7115"," AX7090 : BR7115"," BP7090 : CJ7115 " )
NdcArr238 = Array(" A7120 : R7145"," P7120 : AH7145 "," AF7120 : AZ7145"," AX7120 : BR7145"," BP7120 : CJ7145 " )
NdcArr239 = Array(" A7150 : R7175"," P7150 : AH7175 "," AF7150 : AZ7175"," AX7150 : BR7175"," BP7150 : CJ7175 " )
NdcArr240 = Array(" A7180 : R7205"," P7180 : AH7205 "," AF7180 : AZ7205"," AX7180 : BR7205"," BP7180 : CJ7205 " )
NdcArr241 = Array(" A7210 : R7235"," P7210 : AH7235 "," AF7210 : AZ7235"," AX7210 : BR7235"," BP7210 : CJ7235 " )
NdcArr242 = Array(" A7240 : R7265"," P7240 : AH7265 "," AF7240 : AZ7265"," AX7240 : BR7265"," BP7240 : CJ7265 " )
NdcArr243 = Array(" A7270 : R7295"," P7270 : AH7295 "," AF7270 : AZ7295"," AX7270 : BR7295"," BP7270 : CJ7295 " )
NdcArr244 = Array(" A7300 : R7325"," P7300 : AH7325 "," AF7300 : AZ7325"," AX7300 : BR7325"," BP7300 : CJ7325 " )
NdcArr245 = Array(" A7330 : R7355"," P7330 : AH7355 "," AF7330 : AZ7355"," AX7330 : BR7355"," BP7330 : CJ7355 " )
NdcArr246 = Array(" A7360 : R7385"," P7360 : AH7385 "," AF7360 : AZ7385"," AX7360 : BR7385"," BP7360 : CJ7385 " )
NdcArr247 = Array(" A7390 : R7415"," P7390 : AH7415 "," AF7390 : AZ7415"," AX7390 : BR7415"," BP7390 : CJ7415 " )
NdcArr248 = Array(" A7420 : R7445"," P7420 : AH7445 "," AF7420 : AZ7445"," AX7420 : BR7445"," BP7420 : CJ7445 " )
NdcArr249 = Array(" A7450 : R7475"," P7450 : AH7475 "," AF7450 : AZ7475"," AX7450 : BR7475"," BP7450 : CJ7475 " )
NdcArr250 = Array(" A7480 : R7505"," P7480 : AH7505 "," AF7480 : AZ7505"," AX7480 : BR7505"," BP7480 : CJ7505 " )
NdcArr251 = Array(" A7510 : R7535"," P7510 : AH7535 "," AF7510 : AZ7535"," AX7510 : BR7535"," BP7510 : CJ7535 " )
NdcArr252 = Array(" A7540 : R7565"," P7540 : AH7565 "," AF7540 : AZ7565"," AX7540 : BR7565"," BP7540 : CJ7565 " )
NdcArr253 = Array(" A7570 : R7595"," P7570 : AH7595 "," AF7570 : AZ7595"," AX7570 : BR7595"," BP7570 : CJ7595 " )
NdcArr254 = Array(" A7600 : R7625"," P7600 : AH7625 "," AF7600 : AZ7625"," AX7600 : BR7625"," BP7600 : CJ7625 " )
NdcArr255 = Array(" A7630 : R7655"," P7630 : AH7655 "," AF7630 : AZ7655"," AX7630 : BR7655"," BP7630 : CJ7655 " )
NdcArr256 = Array(" A7660 : R7685"," P7660 : AH7685 "," AF7660 : AZ7685"," AX7660 : BR7685"," BP7660 : CJ7685 " )
NdcArr257 = Array(" A7690 : R7715"," P7690 : AH7715 "," AF7690 : AZ7715"," AX7690 : BR7715"," BP7690 : CJ7715 " )
NdcArr258 = Array(" A7720 : R7745"," P7720 : AH7745 "," AF7720 : AZ7745"," AX7720 : BR7745"," BP7720 : CJ7745 " )
NdcArr259 = Array(" A7750 : R7775"," P7750 : AH7775 "," AF7750 : AZ7775"," AX7750 : BR7775"," BP7750 : CJ7775 " )
NdcArr260 = Array(" A7780 : R7805"," P7780 : AH7805 "," AF7780 : AZ7805"," AX7780 : BR7805"," BP7780 : CJ7805 " )
NdcArr261 = Array(" A7810 : R7835"," P7810 : AH7835 "," AF7810 : AZ7835"," AX7810 : BR7835"," BP7810 : CJ7835 " )
NdcArr262 = Array(" A7840 : R7865"," P7840 : AH7865 "," AF7840 : AZ7865"," AX7840 : BR7865"," BP7840 : CJ7865 " )
NdcArr263 = Array(" A7870 : R7895"," P7870 : AH7895 "," AF7870 : AZ7895"," AX7870 : BR7895"," BP7870 : CJ7895 " )
NdcArr264 = Array(" A7900 : R7925"," P7900 : AH7925 "," AF7900 : AZ7925"," AX7900 : BR7925"," BP7900 : CJ7925 " )
NdcArr265 = Array(" A7930 : R7955"," P7930 : AH7955 "," AF7930 : AZ7955"," AX7930 : BR7955"," BP7930 : CJ7955 " )
NdcArr266 = Array(" A7960 : R7985"," P7960 : AH7985 "," AF7960 : AZ7985"," AX7960 : BR7985"," BP7960 : CJ7985 " )
NdcArr267 = Array(" A7990 : R8015"," P7990 : AH8015 "," AF7990 : AZ8015"," AX7990 : BR8015"," BP7990 : CJ8015 " )
NdcArr268 = Array(" A8020 : R8045"," P8020 : AH8045 "," AF8020 : AZ8045"," AX8020 : BR8045"," BP8020 : CJ8045 " )
NdcArr269 = Array(" A8050 : R8075"," P8050 : AH8075 "," AF8050 : AZ8075"," AX8050 : BR8075"," BP8050 : CJ8075 " )
NdcArr270 = Array(" A8080 : R8105"," P8080 : AH8105 "," AF8080 : AZ8105"," AX8080 : BR8105"," BP8080 : CJ8105 " )
NdcArr271 = Array(" A8110 : R8135"," P8110 : AH8135 "," AF8110 : AZ8135"," AX8110 : BR8135"," BP8110 : CJ8135 " )
NdcArr272 = Array(" A8140 : R8165"," P8140 : AH8165 "," AF8140 : AZ8165"," AX8140 : BR8165"," BP8140 : CJ8165 " )
NdcArr273 = Array(" A8170 : R8195"," P8170 : AH8195 "," AF8170 : AZ8195"," AX8170 : BR8195"," BP8170 : CJ8195 " )
NdcArr274 = Array(" A8200 : R8225"," P8200 : AH8225 "," AF8200 : AZ8225"," AX8200 : BR8225"," BP8200 : CJ8225 " )
NdcArr275 = Array(" A8230 : R8255"," P8230 : AH8255 "," AF8230 : AZ8255"," AX8230 : BR8255"," BP8230 : CJ8255 " )
NdcArr276 = Array(" A8260 : R8285"," P8260 : AH8285 "," AF8260 : AZ8285"," AX8260 : BR8285"," BP8260 : CJ8285 " )
NdcArr277 = Array(" A8290 : R8315"," P8290 : AH8315 "," AF8290 : AZ8315"," AX8290 : BR8315"," BP8290 : CJ8315 " )
NdcArr278 = Array(" A8320 : R8345"," P8320 : AH8345 "," AF8320 : AZ8345"," AX8320 : BR8345"," BP8320 : CJ8345 " )
NdcArr279 = Array(" A8350 : R8375"," P8350 : AH8375 "," AF8350 : AZ8375"," AX8350 : BR8375"," BP8350 : CJ8375 " )
NdcArr280 = Array(" A8380 : R8405"," P8380 : AH8405 "," AF8380 : AZ8405"," AX8380 : BR8405"," BP8380 : CJ8405 " )
NdcArr281 = Array(" A8410 : R8435"," P8410 : AH8435 "," AF8410 : AZ8435"," AX8410 : BR8435"," BP8410 : CJ8435 " )
NdcArr282 = Array(" A8440 : R8465"," P8440 : AH8465 "," AF8440 : AZ8465"," AX8440 : BR8465"," BP8440 : CJ8465 " )
NdcArr283 = Array(" A8470 : R8495"," P8470 : AH8495 "," AF8470 : AZ8495"," AX8470 : BR8495"," BP8470 : CJ8495 " )
NdcArr284 = Array(" A8500 : R8525"," P8500 : AH8525 "," AF8500 : AZ8525"," AX8500 : BR8525"," BP8500 : CJ8525 " )
NdcArr285 = Array(" A8530 : R8555"," P8530 : AH8555 "," AF8530 : AZ8555"," AX8530 : BR8555"," BP8530 : CJ8555 " )
NdcArr286 = Array(" A8560 : R8585"," P8560 : AH8585 "," AF8560 : AZ8585"," AX8560 : BR8585"," BP8560 : CJ8585 " )
NdcArr287 = Array(" A8590 : R8615"," P8590 : AH8615 "," AF8590 : AZ8615"," AX8590 : BR8615"," BP8590 : CJ8615 " )
NdcArr288 = Array(" A8620 : R8645"," P8620 : AH8645 "," AF8620 : AZ8645"," AX8620 : BR8645"," BP8620 : CJ8645 " )
NdcArr289 = Array(" A8650 : R8675"," P8650 : AH8675 "," AF8650 : AZ8675"," AX8650 : BR8675"," BP8650 : CJ8675 " )
NdcArr290 = Array(" A8680 : R8705"," P8680 : AH8705 "," AF8680 : AZ8705"," AX8680 : BR8705"," BP8680 : CJ8705 " )
NdcArr291 = Array(" A8710 : R8735"," P8710 : AH8735 "," AF8710 : AZ8735"," AX8710 : BR8735"," BP8710 : CJ8735 " )
NdcArr292 = Array(" A8740 : R8765"," P8740 : AH8765 "," AF8740 : AZ8765"," AX8740 : BR8765"," BP8740 : CJ8765 " )
NdcArr293 = Array(" A8770 : R8795"," P8770 : AH8795 "," AF8770 : AZ8795"," AX8770 : BR8795"," BP8770 : CJ8795 " )
NdcArr294 = Array(" A8800 : R8825"," P8800 : AH8825 "," AF8800 : AZ8825"," AX8800 : BR8825"," BP8800 : CJ8825 " )
NdcArr295 = Array(" A8830 : R8855"," P8830 : AH8855 "," AF8830 : AZ8855"," AX8830 : BR8855"," BP8830 : CJ8855 " )
NdcArr296 = Array(" A8860 : R8885"," P8860 : AH8885 "," AF8860 : AZ8885"," AX8860 : BR8885"," BP8860 : CJ8885 " )
NdcArr297 = Array(" A8890 : R8915"," P8890 : AH8915 "," AF8890 : AZ8915"," AX8890 : BR8915"," BP8890 : CJ8915 " )
NdcArr298 = Array(" A8920 : R8945"," P8920 : AH8945 "," AF8920 : AZ8945"," AX8920 : BR8945"," BP8920 : CJ8945 " )
NdcArr299 = Array(" A8950 : R8975"," P8950 : AH8975 "," AF8950 : AZ8975"," AX8950 : BR8975"," BP8950 : CJ8975 " )
NdcArr300 = Array(" A8980 : R9005"," P8980 : AH9005 "," AF8980 : AZ9005"," AX8980 : BR9005"," BP8980 : CJ9005 " )

'For Heading
Heading1 = Array(" 8 " )
Heading2 = Array(" 38 " )
Heading3 = Array(" 68 " )
Heading4 = Array(" 98 " )
Heading5 = Array(" 128 " )
Heading6 = Array(" 158 " )
Heading7 = Array(" 188 " )
Heading8 = Array(" 218 " )
Heading9 = Array(" 248 " )
Heading10 = Array(" 278 " )
Heading11 = Array(" 308 " )
Heading12 = Array(" 338 " )
Heading13 = Array(" 368 " )
Heading14 = Array(" 398 " )
Heading15 = Array(" 428 " )
Heading16 = Array(" 458 " )
Heading17 = Array(" 488 " )
Heading18 = Array(" 518 " )
Heading19 = Array(" 548 " )
Heading20 = Array(" 578 " )
Heading21 = Array(" 608 " )
Heading22 = Array(" 638 " )
Heading23 = Array(" 668 " )
Heading24 = Array(" 698 " )
Heading25 = Array(" 728 " )
Heading26 = Array(" 758 " )
Heading27 = Array(" 788 " )
Heading28 = Array(" 818 " )
Heading29 = Array(" 848 " )
Heading30 = Array(" 878 " )
Heading31 = Array(" 908 " )
Heading32 = Array(" 938 " )
Heading33 = Array(" 968 " )
Heading34 = Array(" 998 " )
Heading35 = Array(" 1028 " )
Heading36 = Array(" 1058 " )
Heading37 = Array(" 1088 " )
Heading38 = Array(" 1118 " )
Heading39 = Array(" 1148 " )
Heading40 = Array(" 1178 " )
Heading41 = Array(" 1208 " )
Heading42 = Array(" 1238 " )
Heading43 = Array(" 1268 " )
Heading44 = Array(" 1298 " )
Heading45 = Array(" 1328 " )
Heading46 = Array(" 1358 " )
Heading47 = Array(" 1388 " )
Heading48 = Array(" 1418 " )
Heading49 = Array(" 1448 " )
Heading50 = Array(" 1478 " )
Heading51 = Array(" 1508 " )
Heading52 = Array(" 1538 " )
Heading53 = Array(" 1568 " )
Heading54 = Array(" 1598 " )
Heading55 = Array(" 1628 " )
Heading56 = Array(" 1658 " )
Heading57 = Array(" 1688 " )
Heading58 = Array(" 1718 " )
Heading59 = Array(" 1748 " )
Heading60 = Array(" 1778 " )
Heading61 = Array(" 1808 " )
Heading62 = Array(" 1838 " )
Heading63 = Array(" 1868 " )
Heading64 = Array(" 1898 " )
Heading65 = Array(" 1928 " )
Heading66 = Array(" 1958 " )
Heading67 = Array(" 1988 " )
Heading68 = Array(" 2018 " )
Heading69 = Array(" 2048 " )
Heading70 = Array(" 2078 " )
Heading71 = Array(" 2108 " )
Heading72 = Array(" 2138 " )
Heading73 = Array(" 2168 " )
Heading74 = Array(" 2198 " )
Heading75 = Array(" 2228 " )
Heading76 = Array(" 2258 " )
Heading77 = Array(" 2288 " )
Heading78 = Array(" 2318 " )
Heading79 = Array(" 2348 " )
Heading80 = Array(" 2378 " )
Heading81 = Array(" 2408 " )
Heading82 = Array(" 2438 " )
Heading83 = Array(" 2468 " )
Heading84 = Array(" 2498 " )
Heading85 = Array(" 2528 " )
Heading86 = Array(" 2558 " )
Heading87 = Array(" 2588 " )
Heading88 = Array(" 2618 " )
Heading89 = Array(" 2648 " )
Heading90 = Array(" 2678 " )
Heading91 = Array(" 2708 " )
Heading92 = Array(" 2738 " )
Heading93 = Array(" 2768 " )
Heading94 = Array(" 2798 " )
Heading95 = Array(" 2828 " )
Heading96 = Array(" 2858 " )
Heading97 = Array(" 2888 " )
Heading98 = Array(" 2918 " )
Heading99 = Array(" 2948 " )
Heading100 = Array(" 2978 " )
Heading101 = Array(" 3008 " )
Heading102 = Array(" 3038 " )
Heading103 = Array(" 3068 " )
Heading104 = Array(" 3098 " )
Heading105 = Array(" 3128 " )
Heading106 = Array(" 3158 " )
Heading107 = Array(" 3188 " )
Heading108 = Array(" 3218 " )
Heading109 = Array(" 3248 " )
Heading110 = Array(" 3278 " )
Heading111 = Array(" 3308 " )
Heading112 = Array(" 3338 " )
Heading113 = Array(" 3368 " )
Heading114 = Array(" 3398 " )
Heading115 = Array(" 3428 " )
Heading116 = Array(" 3458 " )
Heading117 = Array(" 3488 " )
Heading118 = Array(" 3518 " )
Heading119 = Array(" 3548 " )
Heading120 = Array(" 3578 " )
Heading121 = Array(" 3608 " )
Heading122 = Array(" 3638 " )
Heading123 = Array(" 3668 " )
Heading124 = Array(" 3698 " )
Heading125 = Array(" 3728 " )
Heading126 = Array(" 3758 " )
Heading127 = Array(" 3788 " )
Heading128 = Array(" 3818 " )
Heading129 = Array(" 3848 " )
Heading130 = Array(" 3878 " )
Heading131 = Array(" 3908 " )
Heading132 = Array(" 3938 " )
Heading133 = Array(" 3968 " )
Heading134 = Array(" 3998 " )
Heading135 = Array(" 4028 " )
Heading136 = Array(" 4058 " )
Heading137 = Array(" 4088 " )
Heading138 = Array(" 4118 " )
Heading139 = Array(" 4148 " )
Heading140 = Array(" 4178 " )
Heading141 = Array(" 4208 " )
Heading142 = Array(" 4238 " )
Heading143 = Array(" 4268 " )
Heading144 = Array(" 4298 " )
Heading145 = Array(" 4328 " )
Heading146 = Array(" 4358 " )
Heading147 = Array(" 4388 " )
Heading148 = Array(" 4418 " )
Heading149 = Array(" 4448 " )
Heading150 = Array(" 4478 " )
Heading151 = Array(" 4508 " )
Heading152 = Array(" 4538 " )
Heading153 = Array(" 4568 " )
Heading154 = Array(" 4598 " )
Heading155 = Array(" 4628 " )
Heading156 = Array(" 4658 " )
Heading157 = Array(" 4688 " )
Heading158 = Array(" 4718 " )
Heading159 = Array(" 4748 " )
Heading160 = Array(" 4778 " )
Heading161 = Array(" 4808 " )
Heading162 = Array(" 4838 " )
Heading163 = Array(" 4868 " )
Heading164 = Array(" 4898 " )
Heading165 = Array(" 4928 " )
Heading166 = Array(" 4958 " )
Heading167 = Array(" 4988 " )
Heading168 = Array(" 5018 " )
Heading169 = Array(" 5048 " )
Heading170 = Array(" 5078 " )
Heading171 = Array(" 5108 " )
Heading172 = Array(" 5138 " )
Heading173 = Array(" 5168 " )
Heading174 = Array(" 5198 " )
Heading175 = Array(" 5228 " )
Heading176 = Array(" 5258 " )
Heading177 = Array(" 5288 " )
Heading178 = Array(" 5318 " )
Heading179 = Array(" 5348 " )
Heading180 = Array(" 5378 " )
Heading181 = Array(" 5408 " )
Heading182 = Array(" 5438 " )
Heading183 = Array(" 5468 " )
Heading184 = Array(" 5498 " )
Heading185 = Array(" 5528 " )
Heading186 = Array(" 5558 " )
Heading187 = Array(" 5588 " )
Heading188 = Array(" 5618 " )
Heading189 = Array(" 5648 " )
Heading190 = Array(" 5678 " )
Heading191 = Array(" 5708 " )
Heading192 = Array(" 5738 " )
Heading193 = Array(" 5768 " )
Heading194 = Array(" 5798 " )
Heading195 = Array(" 5828 " )
Heading196 = Array(" 5858 " )
Heading197 = Array(" 5888 " )
Heading198 = Array(" 5918 " )
Heading199 = Array(" 5948 " )
Heading200 = Array(" 5978 " )
Heading201 = Array(" 6008 " )
Heading202 = Array(" 6038 " )
Heading203 = Array(" 6068 " )
Heading204 = Array(" 6098 " )
Heading205 = Array(" 6128 " )
Heading206 = Array(" 6158 " )
Heading207 = Array(" 6188 " )
Heading208 = Array(" 6218 " )
Heading209 = Array(" 6248 " )
Heading210 = Array(" 6278 " )
Heading211 = Array(" 6308 " )
Heading212 = Array(" 6338 " )
Heading213 = Array(" 6368 " )
Heading214 = Array(" 6398 " )
Heading215 = Array(" 6428 " )
Heading216 = Array(" 6458 " )
Heading217 = Array(" 6488 " )
Heading218 = Array(" 6518 " )
Heading219 = Array(" 6548 " )
Heading220 = Array(" 6578 " )
Heading221 = Array(" 6608 " )
Heading222 = Array(" 6638 " )
Heading223 = Array(" 6668 " )
Heading224 = Array(" 6698 " )
Heading225 = Array(" 6728 " )
Heading226 = Array(" 6758 " )
Heading227 = Array(" 6788 " )
Heading228 = Array(" 6818 " )
Heading229 = Array(" 6848 " )
Heading230 = Array(" 6878 " )
Heading231 = Array(" 6908 " )
Heading232 = Array(" 6938 " )
Heading233 = Array(" 6968 " )
Heading234 = Array(" 6998 " )
Heading235 = Array(" 7028 " )
Heading236 = Array(" 7058 " )
Heading237 = Array(" 7088 " )
Heading238 = Array(" 7118 " )
Heading239 = Array(" 7148 " )
Heading240 = Array(" 7178 " )
Heading241 = Array(" 7208 " )
Heading242 = Array(" 7238 " )
Heading243 = Array(" 7268 " )
Heading244 = Array(" 7298 " )
Heading245 = Array(" 7328 " )
Heading246 = Array(" 7358 " )
Heading247 = Array(" 7388 " )
Heading248 = Array(" 7418 " )
Heading249 = Array(" 7448 " )
Heading250 = Array(" 7478 " )
Heading251 = Array(" 7508 " )
Heading252 = Array(" 7538 " )
Heading253 = Array(" 7568 " )
Heading254 = Array(" 7598 " )
Heading255 = Array(" 7628 " )
Heading256 = Array(" 7658 " )
Heading257 = Array(" 7688 " )
Heading258 = Array(" 7718 " )
Heading259 = Array(" 7748 " )
Heading260 = Array(" 7778 " )
Heading261 = Array(" 7808 " )
Heading262 = Array(" 7838 " )
Heading263 = Array(" 7868 " )
Heading264 = Array(" 7898 " )
Heading265 = Array(" 7928 " )
Heading266 = Array(" 7958 " )
Heading267 = Array(" 7988 " )
Heading268 = Array(" 8018 " )
Heading269 = Array(" 8048 " )
Heading270 = Array(" 8078 " )
Heading271 = Array(" 8108 " )
Heading272 = Array(" 8138 " )
Heading273 = Array(" 8168 " )
Heading274 = Array(" 8198 " )
Heading275 = Array(" 8228 " )
Heading276 = Array(" 8258 " )
Heading277 = Array(" 8288 " )
Heading278 = Array(" 8318 " )
Heading279 = Array(" 8348 " )
Heading280 = Array(" 8378 " )
Heading281 = Array(" 8408 " )
Heading282 = Array(" 8438 " )
Heading283 = Array(" 8468 " )
Heading284 = Array(" 8498 " )
Heading285 = Array(" 8528 " )
Heading286 = Array(" 8558 " )
Heading287 = Array(" 8588 " )
Heading288 = Array(" 8618 " )
Heading289 = Array(" 8648 " )
Heading290 = Array(" 8678 " )
Heading291 = Array(" 8708 " )
Heading292 = Array(" 8738 " )
Heading293 = Array(" 8768 " )
Heading294 = Array(" 8798 " )
Heading295 = Array(" 8828 " )
Heading296 = Array(" 8858 " )
Heading297 = Array(" 8888 " )
Heading298 = Array(" 8918 " )
Heading299 = Array(" 8948 " )
Heading300 = Array(" 8978 " )

ScnShtCount = ScnShtCount +1
Execute "Arrval = NdcArr" & ScnShtCount & "(0)"
HeadingCount = HeadingCount + 1
Execute "Arrval1 = Heading" & HeadingCount & "(0)"
Call ScreenshotHeading(Arrval,Arrval1, Heading,WindowIndex)
End Function

Public Function ScreenshotHeading(strcellvalue,cellval,heading1,index)

'If Not IsObject(application) Then
  ' Set SapGuiAuto  = GetObject("SAPGUI")
   'Set application = SapGuiAuto.GetScriptingEngine
'End If
'If Not IsObject(connection) Then
   'Set connection = application.Children(0)
'End If
'If Not IsObject(session) Then
   'Set session    = connection.Children(0)
'End If

timestamp8 = Replace(formatdatetime(now), "/"," ")
timestamp8 = Replace(timestamp8,":"," ")

heading = heading1&" "&timestamp8

	strSheetname = Testname
	FilePath= ExcelPath &"\"&strSheetname & ".xls"
	'strFilesname="Screenshot"&Day(now)&Minute(Now) & Hour(Now)&Second(Now)&".png"
	'Set objDesktop = Desktop
On Error Resume Next 
If index = 0 Or index= 1 Then
	Path=session.findById("wnd["& index &"]").Hardcopy(tcwiseResPath&"\"& heading &".jpg",1)
	Path=session2.findById("wnd["& index &"]").Hardcopy(tcwiseResPath&"\"& heading &".jpg",1)
	Path=sessionBI.findById("wnd["& index &"]").Hardcopy(tcwiseResPath&"\"& heading &".jpg",1)
	
ElseIf index = 20 Or index= 21 Then
	indexstr= cstr(index)
	indexstr=Right(indexstr,1)
	Path=session_2.findById("wnd["& indexstr &"]").Hardcopy(tcwiseResPath&"\"& heading &".jpg",1)
End If
Err.clear
On Error Goto 0
	'Set objDesktop = Nothing
	Set objExcel = CreateObject("Excel.Application") 
	set objFso=CreateObject("Scripting.FileSystemObject")
	If objFso.FileExists(FilePath) then
		set oWB=objExcel.Workbooks.Open(FilePath)
	else
		Set oWB = objExcel.Workbooks.Add() 
		objExcel.ActiveWorkbook.SaveAs FilePath
	End if
	On Error Resume Next
	DateForm=Replace(time(),"/","")
 'If strcomp(ucase(trim(datatable.Value("Execution","Global"))),"YES")=0 Then

		'SetSheetNameArr = Split(Environment.Value("TestName"),"_")
		SetSheetName = TestCase

'End If
	
	SheetExists = IsObject(oWB.Worksheets(SetSheetName))

	err.number=0
	err.description = ""

	If Not SheetExists Then
		Set oSheet1=oWB.Sheets.Add  'a new sheet is added with default name i.e. Sheet4 etc.
		oSheet1.Name= SetSheetName 'rename newly added sheet
        oSheet1.Move ,oWB.Sheets(oWB.Sheets.Count)

		oWB.Save
		If ScnShtCount<>1 Then
			ScnShtCount=1
		End If
		If HeadingCount<>1 Then
			HeadingCount=1
		End If

	Else 
		'Set oSheet1=oWB.Worksheets(SetSheetName)

	End If

	For t=1 to 3

	flag=0
		SheetExists1 = IsObject(oWB.Worksheets("Sheet"&t))
		If Strcomp(err.Description,"Subscript out of range",vbtextcompare)=0 Then
			err.Description=""
			flag=1
			SheetExists1=False
		End If

		If flag=0 and SheetExists1 Then
			oWB.Worksheets("Sheet"&t).Delete
		End If
		'err.number=0
		
	Next
	
	objExcel.Visible = False
	objExcel.Cells(cellval,1).Value = heading
	objExcel.Cells(1,1).Value = "Description"
	objExcel.Cells(2,1).Value = TCDescription
	
	Set Picture = objExcel.ActiveWorkbook.ActiveSheet.Pictures.Insert(Path) 

    Set objRng = objExcel.ActiveWorkbook.ActiveSheet.Range(strcellvalue)
	With Picture
	   .Top = objRng.Top
	   .Left = objRng.Left
	   .Width = objRng.Width 
	   .Height = objRng.Height
	End With
   objExcel.ActiveWorkbook.Save
   oWB.Save
	oWB.Close
	objExcel.Quit
	'objExcel.Application.Quit     
  
	Set oWB=Nothing 
	Set objExcel=Nothing
	SystemUtil.CloseProcessByName("EXCEL.EXE")
	'Set obj = CreateObject("Scripting.FileSystemObject") 'Calls the File System Object   
    'obj.DeleteFile ExcelPath &strFilesname
	'Set obj=Nothing
StepStatus=Split(heading," ")
index=Ubound(StepStatus)
ActualStatus=StepStatus(index)
StepDesc=Split(heading,"-")
	
	Call AddStepResultsToHTML_2(StepDesc(0),ActualStatus,Path)

	If UCase(Trim(StepDesc(1))) = "FAIL" Then
		errormes = StepDesc(0)
		Call ExportValueXML_ERROR("",errormes)
	End If
End Function

Function UpdateStatus()
   If TotalError<>"" Then
   		Status="FAIL"
	   'Datatable.Value("Status","Global") = "FAIL"
	   'Datatable.Value("ActualResult","Global")= "ERROR:" & TotalError
	   'loc = FilterTestCasewiseResultLocation("Fail")
	Else
		'Datatable.Value("Status","Global") = "PASS"
		'If instr(1,Datatable.Value("ExpectedResult","Global"),"should be",1)>0 Then
			'Datatable.Value("ActualResult","Global")= replace(Datatable.Value("ExpectedResult","Global"),"should be","")
		'Else 
			'Datatable.Value("ActualResult","Global")= "As Expected"
		'End If
		'loc = FilterTestCasewiseResultLocation("Pass")
		PassFlag =  True
		Status="PASS"
   End If
	'Call UpdateDataTable("ScreenShotLocation",tcwiseResPath)'TestcaseWiseResultPath)
	fpath =  InitialResultPath & "\" & TestCycle &"\ExecutionList.xls"
	'Call UpdateExecutionListExcel(fpath,TestcaseName,Status,ScriptName)
	sheetName = Testname & "_Result_" & replace(replace(now,":",""),"/","") & ".xls"
	FinalExcelResult= ExcelPath & "\"  & sheetName
	Call CreateTCHTMLFooter()
	'Print Datatable.Value("ScenarioID") & " - ResultPath: " & TCWiseResult & Chr(10)
	'SystemUtil.Run TCWiseResult
Dim oShell
'Set oShell = WScript.CreateObject ("WScript.Shell")
'oShell.run "notepad.exe" & TCWiseResult
'Set oShell = Nothing
    Dim IE
Dim MyDocument
Set IE = CreateObject("InternetExplorer.Application")
IE.Visible = 1
  TCWiseResult2=Replace(TCWiseResult, "\\","\")
IE.navigate TCWiseResult2
Set IE=Nothing
Set MyDocument=Nothing
End Function

Public Function CreateTCHTMLFooter()

	Set fso = CreateObject("Scripting.FileSystemObject")	 
    Set Myfile = fso.OpenTextFile(TCWiseResult,8)		
	Myfile.Writeline("<tr>")	
	Myfile.Writeline("<td COLSPAN = 5 >")	
	Myfile.Writeline("<p align=justify><b><font color=#000080 size=2 face= Verdana>"& "&nbsp;"& "End Date and Time :&nbsp;&nbsp;" &  now  & "&nbsp;")	 
    Myfile.Writeline("<p align=right><b><font color=#000080 size=2 face= Verdana><a href='"& FinalExcelResult &"'>"&"EXCEL RESULTS</a>") 
	TCEndTime = Time()
	Myfile.Writeline("</td>")			
	Myfile.Writeline("</tr>")
	Myfile.Writeline("</Table>")
	Myfile.Writeline("<Table border=2 bordercolor=#000000 id=table1 width=844 height=31 bordercolorlight=#000000>")
	Myfile.Writeline("<TR bgcolor=#000080>")
	Myfile.Writeline("<TH colspan=2 width = 200><b><p align=center><font color = #FFFFFF face=Arial Narrow size=2>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Test Case Execution Summary</Font></p></b>")
	Myfile.Writeline("</TH>")
	Myfile.Writeline("<TH colspan=2 width = 200><p align=center><b><font color = #FFFFFF face=Arial Narrow size=2>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Test Case Execution Details</Font></b></p>")
	Myfile.Writeline("</TH>")
	Myfile.Writeline("</TR>")
	Myfile.Writeline("<TR border=2 bordercolor=#000000 bordercolorlight=#000000>")
	
	N = TotalStepsPassed+TotalStepsFailed
	Myfile.Writeline("<TD bgcolor =  FFFFFF>Total Steps Validated")
	Myfile.Writeline("</TD>")
	Myfile.Writeline("<TD bgcolor =  FFFFFF><p align=center>" &N)
	Myfile.Writeline("</TD>")
	Myfile.Writeline("<TD bgcolor =  FFFFFF>Test case Start Time")
	Myfile.Writeline("</TD>")
	Myfile.Writeline("<TD bgcolor =  FFFFFF><p align=center>" &TCStrtTime)
	Myfile.Writeline("</TD>")
	Myfile.Writeline("</TR>")
	Myfile.Writeline("<TR border=2 bordercolor=#000000 bordercolorlight=#000000>")
	Myfile.Writeline("<TD bgcolor =  FFFFFF>Total Test Steps Passed")
	Myfile.Writeline("</TD>")
	Myfile.Writeline("<TD bgcolor =  FFFFFF><p align=center>" &TotalStepsPassed)
	Myfile.Writeline("</TD>")
	Myfile.Writeline("<TD bgcolor =  FFFFFF>Test case End Time")
	Myfile.Writeline("</TD>")
	Myfile.Writeline("<TD bgcolor =  FFFFFF><p align=center>" &TCEndTime)
	Myfile.Writeline("</TD>")
	Myfile.Writeline("</TR>")
	Myfile.Writeline("<TR border=2 bordercolor=#000000 bordercolorlight=#000000>")
	Myfile.Writeline("<TD bgcolor =  FFFFFF>Total Test Steps Failed")
	Myfile.Writeline("</TD>")
	Myfile.Writeline("<TD bgcolor =  FFFFFF><p align=center>" &TotalStepsFailed)
	Myfile.Writeline("</TD>")
	Myfile.Writeline("<TD bgcolor =  FFFFFF>Test case Execution Time")
	Myfile.Writeline("</TD>")
	ExecutionTime = CallTimeSeconds(TCStrtTime,TCEndTime)
	Myfile.Writeline("<TD bgcolor =  FFFFFF><p align=center>" &ExecutionTime)
	Myfile.Writeline("</TD>")
	Myfile.Writeline("</TR>")
	Myfile.Writeline("</Table>")
	Myfile.Close

End Function
Function CallTimeSeconds(StartTime,EndTime)

	StartHour = Hour(StartTime)
	StartMin =  Minute(StartTime)
	StartSec = Second(StartTime)
	EndHour = Hour(EndTime)
	EndMin = Minute(EndTime)
	EndSec = Second(EndTime)
	StartingSeconds = (StartSec + (StartMin * 60) + ((StartHour * 60)*60))
	EndingSeconds = (EndSec + (EndMin * 60) + ((EndHour * 60)*60))
	TimeDiff = EndingSeconds - StartingSeconds
	hr=TimeDiff\3600
	If len(hr)=1 Then
		hr="0" & hr 
	End If
	min=TimeDiff\60 Mod 60
	If len(min)=1 Then
		min="0" & min 
	End If
	sec=TimeDiff Mod 60
	If len(min)=1 Then
		sec="0" & sec 
	End If
	TotalTimeDiff= hr & ":"& min & ":" & sec 
	CallTimeSeconds = TotalTimeDiff

End Function

Function CreationDate(DateVal)
	DateVal = Cdate(Replace(DateVal,".","/"))
	Monthval = Month(DateVal)
	If Monthval<10 Then
		Monthval = "0"&Cstr(Monthval)
	Else
		Monthval = Cstr(Monthval)
	End If
	DayVal = Day(DateVal)
	If DayVal<10 Then
		DayVal = "0"&Cstr(DayVal)
	Else
		DayVal = Cstr(DayVal)
	End If
	CreationDate = Cstr(Year(DateVal))& Monthval & DayVal
End Function
Function CreationDate_New(DateVal)
	DateVal = Cdate(Replace(DateVal,".","/"))
	Monthval = Month(DateVal)
	If Monthval<10 Then
		Monthval = "0"&Cstr(Monthval)
	Else
		Monthval = Cstr(Monthval)
	End If
	DayVal = Day(DateVal)
	If DayVal<10 Then
		DayVal = "0"&Cstr(DayVal)
	Else
		DayVal = Cstr(DayVal)
	End If
	CreationDate_New = Cstr(Year(DateVal))&"-"& Monthval &"-"& DayVal
End Function
Function CreationDateReverse(DateVal)
	DateVal = Cdate(Replace(DateVal,".","/"))
	Monthval = Month(DateVal)
	If Monthval<10 Then
		Monthval = "0"&Cstr(Monthval)
	Else
		Monthval = Cstr(Monthval)
	End If
	DayVal = Day(DateVal)
	If DayVal<10 Then
		DayVal = "0"&Cstr(DayVal)
	Else
		DayVal = Cstr(DayVal)
	End If
	CreationDatereverse = Cstr (Dayval& Monthval & (Year(DateVal)))
End Function
Function CreationDate_General(DateVal)
	Monthval = Month(DateVal)
	If Monthval<10 Then
		Monthval = "0"&Cstr(Monthval)
	Else
		Monthval = Cstr(Monthval)
	End If
	DayVal = Day(DateVal)
	If DayVal<10 Then
		DayVal = "0"&Cstr(DayVal)
	Else
		DayVal = Cstr(DayVal)
	End If
	CreationDate_General =  DayVal&"."&Monthval&"."&Cstr(Year(DateVal))

End Function 

Public Function Posting(totalfiles,QueueManagerName,QueueName)

If err.description <> "" Then
		TotalError = TotalError&err.description
	End If
	'If TotalError<>"" Then
	'	Exit Function
	'End If
  	
	'On Error Resume Next
	If UCase(QueueManagerName) = "NA" Then
		Exit Function
	End If
	If Not Isarray(totalfiles) Then
		FileToPost = Split(Totalfiles,",") 
	else
		FileToPost = Totalfiles	
	End If
	
	If FileToPost(0) = "NA" Then
		Exit Function
	End If
	ExeMachine = UserName
	
	filecount = Ubound(FileToPost)+1
	Textfilepath = "\\mshsrmnsukc0173\c$\R2 2014\SupportFiles\DC2\CommandTemp_"&ExeMachine&".txt"
	
	PostingPath = "\\mshsrmnsukc0173\c$\R2 2014\SupportFiles\DC2\Test_"&ExeMachine&".txt"
	Set FSO = CreateObject("Scripting.FileSystemObject")
	Set flWrite = fso.CreateTextFile(PostingPath,True)
	
	flWrite.WriteLine "[header]"
	flWrite.WriteLine "qname=TEMP.OUT"
	flWrite.WriteLine "qmgr=MQSI"
	flWrite.WriteLine "msgcount=3"
	flWrite.WriteLine "qdepth=3"
	flWrite.WriteLine "qmax=100"
	flWrite.WriteLine "sleeptime=20"
	flWrite.WriteLine "tune=0"
	flWrite.WriteLine "batchsize=2"
	flWrite.WriteLine "format= "&chr(34)&"MQSTR   "&chr(34)
	flWrite.WriteLine "priority=2"
	flWrite.WriteLine "replyqm=TEST"
	flWrite.WriteLine "replyq=TEST.REMOTE"
	flWrite.WriteLine "persist=0"
	flWrite.WriteLine "msgtype=1"
	flWrite.WriteLine "encoding=546"
	flWrite.WriteLine "codepage=819"   '' Code page varies based on the application
	flWrite.WriteLine "rfh=N"
	flWrite.WriteLine "RFH_CCSID=500"
	flWrite.WriteLine "RFH_ENCODING=785"
	flWrite.WriteLine "RFH_NAME_CCSID=1208"
	flWrite.WriteLine "RFH_DOMAIN=MRM"
	flWrite.WriteLine "RFH_MSG_SET=DH4M6DO072001	"
	flWrite.WriteLine "RFH_MSG_TYPE=Customer_Root"
	flWrite.WriteLine "RFH_MSG_FMT=CWF"
	flWrite.WriteLine "RFH_APP_GROUP=Customer_Msgs"
	flWrite.WriteLine "RFH_FORMAT=Customer_Root"
	flWrite.WriteLine "[filelist]"
	
	For fileList = 0 To Ubound(FileToPost)
''		flWrite.WriteLine "\\mshsrmnsukc0173\c$\R2 2014\"\SupportFiles\DC2\CommandTemp_"&ExeMachine&".txt"
		flWrite.WriteLine FileToPost(fileList)
	Next
	''''\\mshsrmnsukc0173\c$\R2 2014\SupportFiles\DC2\ProcessedXmls\
	flWrite.Close
	
	 Dim oShell
	 Set oShell = WScript.CreateObject ("WScript.Shell")
	oShell.Run "CMD /K"
WScript.Sleep 1000
	oShell.SendKeys "cd\"
	oShell.SendKeys "{ENTER}"
	oShell.SendKeys "cd \\mshsrmnsukc0173\c$\R2 2014\SupportFiles\DC2"
	oShell.SendKeys "{ENTER}"
WScript.Sleep 500
	oShell.SendKeys "mqputsc -f Test_"&ExeMachine&".txt -m SYSTEM.ADMIN.SVRCONN/TCP/"& QueueManagerName &"  -q "&QueueName& " -c " & 1
WScript.Sleep 500

	oShell.SendKeys "{ENTER}"

	
	'Set qfile=fso.OpenTextFile(Textfilepath,1,True)
	'visitext = qfile.ReadAll  		
	'qfile.Close
	'Set qfile=nothing
	'Set Fso = Nothing
	If Instr(1,visitext,"Total messages written "&filecount &" out of "&filecount &"") > 0 Then
'		Call AddStepResultsToHTML("Posting Message ",filecount &"Message(s) should be posted Successfully",filecount &"Message(s) is/are posted successfully","PASS","Y")
'		print filecount &" Messages Posted in Queue Successfully"
	else
'		Call AddStepResultsToHTML("Posting Message ","Message should be posted Successfully","Message is not  posted successfully: "&visitext,"FAIL","Y")
'		Print "Message not  posted successfully"	
	End If
	'systemutil.CloseProcessByName "cmd.exe"
	'If err.description <> "" Then
		'TotalError = TotalError&err.description
	'End If	

Set WSHShell = CreateObject("WScript.Shell")

 WinTitle = "SAP Easy Access"

      While Not WSHShell.AppActivate(WinTitle)

        WScript.Sleep 3

      Wend
	
	'WSHShell.AppActivate("SAP Easy Access")	

      Set WSHShell = Nothing

END FUNCTION 

Function Result(Project,TestName)
Call GetXMLData(ObjXMLData)
	AUTOMATION_SUITE= TestName
	CLUSTER="SAP"
	RUN_FOR_PROJECT= Project
	'Datatable.Value("Project Name")
	'PROJECT_ID=Datatable.Value("Project ID")
	RUN_DATE = Formatdatetime(Date)
	'RUN_TRIGGERED_BY = Environment.Value("SAP_LoginECC_User")
	TEMPREGION=ObjXMLData.Item("Environment")
	Select Case Ucase(Trim(TEMPREGION))
					Case "CATEA"
						REGION="CATE_A"
					Case  "CATEC"
						REGION="CATE_C"
					Case "INTA"
						REGION="INT_A"
					Case "CATEB"
						REGION="CATE_B"
					Case Else
						REGION=TEMPREGION
	End Select
	SCENARIO_ID = ObjXMLData.Item("TCID")
	'START_TIME  = Formatdatetime(TCDateTime)
	START_TIME= TCStrtTime
	END_TIME = TCEndTime
	TOTAL_PASS = TotalStepsPassed
	TOTAL_FAIL = TotalStepsFailed
	TOTAL_STEPS = TotalStepsPassed + TotalStepsFailed
	SCENARIO_STATUS = CompletionStatus
	
	ConnectnString = "Driver={SQL Server};Server=MSHSRMNSUKC0117;Database=NPS_TEST;uid=NPS_TEST;pwd=Webservice2017;Connection Timeout=300"

	Set connectionOBJ = CreateObject("ADODB.Connection") 
	set recordSetOBJ = CreateObject("ADODB.recordset")
    connectionOBJ.ConnectionString = ConnectnString
	connectionOBJ.Open ConnectnString
	connectionOBJ.CommandTimeout=500
	Query = "INSERT INTO CSAP_RUN_RESULTS (CLUSTER,PROJECT,REQUESTED_BY,REQUEST_DATE,REGION,TOTAL_TESTS,TOTAL_PASS,TOTAL_FAIL,RUN_START,RUN_END) VALUES ('"&CLUSTER&"','"&RUN_FOR_PROJECT&"','"&RUN_TRIGGERED_BY&"',convert(datetime,'"&RUN_DATE&"',105),'"&REGION&"','"&TOTAL_STEPS&"','"&TOTAL_PASS&"','"&TOTAL_FAIL&"',convert(datetime,'"&START_TIME&"',105),convert(datetime,'"&END_TIME&"',105))"
										
	set recordSetOBJ = connectionOBJ.Execute(Query)
	set recordSetOBJ = Nothing
	Set connectionOBJ = Nothing
End Function


Function CloseSAP(strProgramName)
Dim objShell
Set objShell = CreateObject("WScript.Shell")
objShell.Run "TASKKILL /F /IM " & strProgramName
Set objShell = Nothing
End Function

Function CopyXML_Contents(InputXml)
	Set objFSO = 	CreateObject("Scripting.FileSystemObject") 
	Set objFile = objFSO.OpenTextFile(InputXml,1, False) 
	CopyXML_Contents = objFile.ReadAll 
End Function




Function PO_Post(SenderInterface,InputXml)

Dim WshShell1, oExec
Set WshShell1 = CreateObject("WScript.Shell")



Set oExec = WshShell1.Exec("taskkill /fi ""imagename eq iexplore.exe""")
WScript.Sleep 5000
Set oExec = nothing
'Dim IE


On Error Resume Next

Set IE = CreateObject("InternetExplorer.Application")
'msgbox Err.Description & "11111111111111111111111111111111111111111111111111111"
For ila=1 to 10

If Err.Description = "A system shutdown has already been scheduled" Then
msgbox Err.Description & "000000000000000000000000000000000000000000000000000000"
Err.Description="" 

WScript.Sleep 5000
Set IE = CreateObject("InternetExplorer.Application")

Else
Exit For
End If
Next 

IE.Visible = 1

On Error Goto 0



'Set objWord22 = CreateObject("Word.Application")
'Set colTasks = objWord22.Tasks
'For Each objTask22 in colTasks
	'If Instr(objTask22.Name, "Internet Explorer") Then
	'XLSrunning = 3
	'objTask22.Activate
	'objTask22.WindowState =&H00000001' wdWindowStateMaximize
	'Exit For 'Exit this loop after the first Excel Application found!
	'End if
	'Next
	'objWord22.quit
	'Set objWord22 = nothing	


ExecutionRegion = ObjXMLData.Item("Environment")
	Select Case Ucase(Trim(ExecutionRegion))
	
	Case "CATEA"
		IE.Navigate "http://hlxc0bf027.unix.marksandspencercate.com:54400/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true#" 'FQJ			
        Case "CATEL"
		IE.Navigate "http://hlxc0bf029.unix.marksandspencercate.com:54400/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true" 'FQL
	End Select
'IE.navigate "http://hlxc0bf033.unix.marksandspencercate.com:8147/webdynpro/dispatcher/sap.com/tc~lm~itsam~co~ui~xi~ovcustom~wd/MonitoringHomeApp" 'catec  
'IE.navigate "http://clxc8bfqr1.unix.marksandspencercate.com:8147/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true" 'INTB
'IE.Navigate "http://hlxd0bf032.unix.marksandspencerdev.com:54400/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true" 'FCQ
'IE.Navigate "http://hlxc0bf030.unix.marksandspencercate.com:8147/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true" 'FQL
'IE.Navigate "http://hlxc0bf029.unix.marksandspencercate.com:54400/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true" 'FQL
'IE.Navigate "http://hlxc0bf027.unix.marksandspencercate.com:54400/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true#" 'FQJ


'Click Configuration and Monitoring Home link
'wait(2)
'For Each l In IE.Document.getElementsByTagName("A")  
'If l.classname = "urLnk" Then 
'If l.innertext = "Configuration and Monitoring Home" Then 
'l.click
'end if           
'End If 
'Next 
'
''Point to second Tab - Login page

For Each wnd In CreateObject("Shell.Application").Windows
If InStr(1, wnd.FullName, "iexplore.exe", vbTextCompare) > 0 Then
Set IE = wnd
End If
Next
'wait(3)
WScript.Sleep 3000
'Enter Credentials
Msgbox "1"
ExecutionRegion = ObjXMLData.Item("Environment")
	Select Case Ucase(Trim(ExecutionRegion))
	
	Case "CATEA"
For Each wnd In CreateObject("Shell.Application").Windows
If InStr(1, wnd.FullName, "iexplore.exe", vbTextCompare) > 0 Then
Set IE = wnd
End If
Next
'wait(3)
WScript.Sleep 3000
		IE.Document.getElementByID("logonuidfield").value = "P9134904"
		IE.Document.getElementByID("logonpassfield").value = "Sapfqj123"
        Case "CATEL"
		IE.Document.getElementByID("logonuidfield").value = "P9134904"
		IE.Document.getElementByID("logonpassfield").value = "Sapfql123"
	End Select
'IE.Document.getElementByID("logonuidfield").value = "P9134904"
'IE.Document.getElementByID("logonpassfield").value = "Sapfqj123"



IE.Document.Forms(0).Submit()



'wait(2)
WScript.Sleep 4000



For Each wnd In CreateObject("Shell.Application").Windows
If InStr(1, wnd.FullName, "iexplore.exe", vbTextCompare) > 0 Then
Set IE = wnd
End If
Next
'wait(3)
WScript.Sleep 4000



'Click SOA tab
'IE.FullScreen = True
On error resume next
IE.Document.getElementByID("CEPJ.IDPView.HorizontalContextualPanel1.2-title").click
IE.Document.getElementByID("CEPJ.IDPView.HorizontalContextualPanel1.2-2").click
'IE.Document.getElementByID("CEPJ.IDPView.HorizontalContextualPanel1.2-title").click
On error goto 0
'IE.Document.getElementsByTagName("A")
'Click Send Test message link
'wait(2)
On error resume next
WScript.Sleep 3000
For Each l In IE.Document.getElementsByTagName("A")  
If l.classname = "lsControl--valign lsLink urLnkFunction urTxtStd lsTextNoWrapping" Then
If l.innertext = "Send Test Message" Then 
l.click
IEnewpage1 = l.href
End if           
End If 
Next
'*********
For Each l In IE.Document.getElementsByTagName("A")  
If l.classname = "lsControl--valign lsLink urLnkFunction urTxtStd lsTextNoWrapping lsControl--noWrapping" Then
If l.innertext = "Send Test Message" Then 
l.click
IEnewpage1 = l.href
End if           
End If 
Next
'*********
On error goto 0


'Point to 3rd Tab - Send Test message
For Each wnd In CreateObject("Shell.Application").Windows
If InStr(1, wnd.FullName, "iexplore.exe", vbTextCompare) > 0 Then
Set IE = wnd
End If
Next

'Set WshShell = CreateObject("WScript.Shell")
'WshShell.AppActivate IEs


'wait(2)
WScript.Sleep 3000
PayloadContent = CopyXML_Contents(InputXml)

IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").select
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").innertext = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").ContentEditable = False
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").disabled = True
'ScreenshotExcel_NonSAP("XML Copied")
'Msgbox "Copy to clipboard"
WScript.Sleep 3000
'Click Search button
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.Button-img").click

'wait(2)
WScript.Sleep 2000
'IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
'IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").value = PayloadContent


'Search for the interface
Set aHTML = IE.document
set iframes = aHTML.getElementById("URLSPW-0")
WScript.Sleep 2000
Set obabc=iframes.contentdocument
'obabc.getElementById("CEPJICNK.PrefillFieldsView.PreFillFeildsTable:2147483639").value="I101_PickPGI"
obabc.getElementById("CEPJICNK.PrefillFieldsView.PreFillFeildsTable:2147483639").value=SenderInterface
WScript.Sleep 3000
obabc.getElementById("CEPJICNK.PrefillFieldsView.PreFillFeildsTable:2147483639").focus
'wait(2)
WScript.Sleep 3000
Set WshShell = CreateObject("WScript.Shell")
'WshShell.AppActivate IEs
WshShell.SendKeys "{ENTER}"
obabc.getElementById("CEPJICNK.PrefillFieldsView.PreFillFeildsTable:2147483639").focus
WScript.Sleep 1000
WshShell.SendKeys "{ENTER}"
'Msgbox "Check"

WScript.Sleep 3000
obabc.getElementById("CEPJICNK.PrefillFieldsView.SITextView.0").focus
WshShell.SendKeys "{ENTER}"

WshShell.SendKeys "{ENTER}"
'Msgbox "done"
WScript.Sleep 2000
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.QOSDropDownByKey").click
WshShell.SendKeys "{DOWN}"
WshShell.SendKeys "{DOWN}"
WshShell.SendKeys "{ENTER}"
WScript.Sleep 2000

IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").select
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").innertext = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").ContentEditable = False
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").disabled = True

'ScreenshotExcel_NonSAP("Interface Details enetered")
IE.Document.getElementByID("CEPJ.IDPView.InputField").focus
WScript.Sleep 2000

'Msgbox "125"
On error resume next
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text urBtnCntTxt" Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'Msgbox "Post click"
'***********
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text " Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'***********
On error goto 0

WScript.Sleep 2000

Status = IE.Document.getElementByID("CEPJ.IDPView.MessageArea-txt").innertext
	If Status <> "Message sent successfully" Then
		errormes = "Message Posting failed"
		Call ExportValueXML_ERROR("",errormes)
		ScreenshotExcel_NonSAP("Message posting failed")
	Else
		Call ScreenshotExcel_NonSAP("Message Posted")
	End If

On Error Resume Next
For Each t in IE.Document.getElementsByTagName("A")

If t.classname = "lsControl--valign lsLink urLnkReportGl20 urTxtStd lsTextNoWrapping lsControl--noWrapping" Or t.classname = "lsControl--valign lsLink urLnkReportGl20 urTxtStd lsTextNoWrapping" Then
		 
If t.innertext = "Log Off" Then
t.click
End If
End If

Next
On Error Goto 0
WScript.Sleep 3000
IE.quit
IE.quit

Set oExec = WshShell.Exec("taskkill /fi ""imagename eq iexplore.exe""")
WScript.Sleep 3000
Set oExec = nothing
ExeMode="CBTA_Vibase"

End Function






Function PO_Post_VB(SenderInterface,InputXml)

Dim WshShell1, oExec
Set WshShell1 = CreateObject("WScript.Shell")



'Set oExec = WshShell1.Exec("taskkill /fi ""imagename eq iexplore.exe""")
'WScript.Sleep 20000
'Set oExec = nothing
Dim IE
Set IE = CreateObject("InternetExplorer.Application")

IE.Visible = 1
'IE.navigate "http://hlxc0bf033.unix.marksandspencercate.com:8147/webdynpro/dispatcher/sap.com/tc~lm~itsam~co~ui~xi~ovcustom~wd/MonitoringHomeApp" 'catec  
'IE.navigate "http://clxc8bfqr1.unix.marksandspencercate.com:8147/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true" 'INTB
'IE.Navigate "http://hlxd0bf032.unix.marksandspencerdev.com:54400/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true" 'FCQ
'IE.Navigate "http://hlxc0bf030.unix.marksandspencercate.com:8147/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true" 'FQL
'IE.Navigate "http://hlxc0bf029.unix.marksandspencercate.com:54400/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true" 'FQL
IE.Navigate "http://hlxc0bf027.unix.marksandspencercate.com:54400/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true#" 'FQJ


'Click Configuration and Monitoring Home link
'wait(2)
'For Each l In IE.Document.getElementsByTagName("A")  
'If l.classname = "urLnk" Then 
'If l.innertext = "Configuration and Monitoring Home" Then 
'l.click
'end if           
'End If 
'Next 
'
''Point to second Tab - Login page

For Each wnd In CreateObject("Shell.Application").Windows
If InStr(1, wnd.FullName, "iexplore.exe", vbTextCompare) > 0 Then
Set IE = wnd
End If
Next
'wait(3)
WScript.Sleep 3000
'Enter Credentials

Msgbox "1"
IE.Document.getElementByID("logonuidfield").value = "P9134904"
IE.Document.getElementByID("logonpassfield").value = "Sapfqj123"
IE.Document.Forms(0).Submit()

'wait(2)
WScript.Sleep 3000


For Each wnd In CreateObject("Shell.Application").Windows
If InStr(1, wnd.FullName, "iexplore.exe", vbTextCompare) > 0 Then
Set IE = wnd
End If
Next
'wait(3)
WScript.Sleep 3000
'Click SOA tab
IE.Document.getElementByID("CEPJ.IDPView.HorizontalContextualPanel1.2-2").click
'IE.Document.getElementsByTagName("A")
'Click Send Test message link
'wait(2)
WScript.Sleep 3000
For Each l In IE.Document.getElementsByTagName("A")  
If l.classname = "lsControl--valign lsLink urLnkFunction urTxtStd lsTextNoWrapping" Then 
If l.innertext = "Send Test Message" Then 
l.click
IEnewpage1 = l.href
End if           
End If 
Next

'Point to 3rd Tab - Send Test message
For Each wnd In CreateObject("Shell.Application").Windows
If InStr(1, wnd.FullName, "iexplore.exe", vbTextCompare) > 0 Then
Set IE = wnd
End If
Next

'Set WshShell = CreateObject("WScript.Shell")
'WshShell.AppActivate IEs


'wait(2)
WScript.Sleep 3000
PayloadContent = CopyXML_Contents(InputXml)
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").select
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").innertext = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").ContentEditable = False
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").disabled = True
'ScreenshotExcel_NonSAP("XML Copied")
'Msgbox "Copy to clipboard"
WScript.Sleep 3000
'Click Search button
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.Button-img").click

'wait(2)
WScript.Sleep 2000
'IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
'IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").value = PayloadContent


'Search for the interface
Set aHTML = IE.document
set iframes = aHTML.getElementById("URLSPW-0")
WScript.Sleep 2000
Set obabc=iframes.contentdocument
'obabc.getElementById("CEPJICNK.PrefillFieldsView.PreFillFeildsTable:2147483639").value="I101_PickPGI"
obabc.getElementById("CEPJICNK.PrefillFieldsView.PreFillFeildsTable:2147483639").value=SenderInterface
WScript.Sleep 3000
obabc.getElementById("CEPJICNK.PrefillFieldsView.PreFillFeildsTable:2147483639").focus
'wait(2)
WScript.Sleep 3000
Set WshShell = CreateObject("WScript.Shell")
'WshShell.AppActivate IEs
WshShell.SendKeys "{ENTER}"
obabc.getElementById("CEPJICNK.PrefillFieldsView.PreFillFeildsTable:2147483639").focus
'wait(2)
WScript.Sleep 1000
WshShell.SendKeys "{ENTER}"
'Msgbox "Check"

WScript.Sleep 3000
obabc.getElementById("CEPJICNK.PrefillFieldsView.SITextView.0").focus
WshShell.SendKeys "{ENTER}"

WshShell.SendKeys "{ENTER}"
'Msgbox "done"
WScript.Sleep 2000
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.QOSDropDownByKey").click
WshShell.SendKeys "{DOWN}"
WshShell.SendKeys "{DOWN}"
WshShell.SendKeys "{ENTER}"
WScript.Sleep 2000

IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").select
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").innertext = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").ContentEditable = False
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").disabled = True

'ScreenshotExcel_NonSAP("Interface Details enetered")
IE.Document.getElementByID("CEPJ.IDPView.InputField").focus
WScript.Sleep 2000

'Msgbox "125"
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text urBtnCntTxt" Then

If m.innertext = "Send" Then

m.click

End If

End If

Next
'Msgbox "Post click"
WScript.Sleep 2000
'Status = IE.Document.getElementByID("CEPJ.IDPView.MessageArea-txt").innertext
ScreenshotExcel_NonSAP("Message Posted")
'Msgbox Status
IE.quit
IE.quit
End Function


Function PO_validate(Interface)

Dim WshShell, oExec
Set WshShell = CreateObject("WScript.Shell")



Set oExec = WshShell.Exec("taskkill /fi ""imagename eq iexplore.exe""")
WScript.Sleep 5000
Set oExec = nothing
'Dim IE
Set IE = CreateObject("InternetExplorer.Application")




On Error Resume Next

Set IE = CreateObject("InternetExplorer.Application")
'msgbox Err.Description & "11111111111111111111111111111111111111111111111111111"
For ila=1 to 10

If Err.Description = "A system shutdown has already been scheduled" Then
msgbox Err.Description & "000000000000000000000000000000000000000000000000000000"
Err.Description="" 

WScript.Sleep 5000
Set IE = CreateObject("InternetExplorer.Application")

Else
Exit For
End If
Next 
On Error Goto 0


IE.Visible = 1

Set objWord22 = CreateObject("Word.Application")
Set colTasks = objWord22.Tasks
For Each objTask22 in colTasks
	If Instr(objTask22.Name, "Internet Explorer") Then
	XLSrunning = 3
	objTask22.Activate
	objTask22.WindowState =&H00000001' wdWindowStateMaximize
	Exit For 'Exit this loop after the first Excel Application found!
	End if
	Next
	objWord22.quit
	Set objWord22 = nothing	

ExecutionRegion = ObjXMLData.Item("Environment")
	Select Case Ucase(Trim(ExecutionRegion))
	
	Case "CATEA"
		IE.Navigate "http://hlxc0bf027.unix.marksandspencercate.com:54400/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true#" 'FQJ			
        Case "CATEL"
		IE.Navigate "http://hlxc0bf029.unix.marksandspencercate.com:54400/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true" 'FQL
	End Select
'IE.navigate "http://hlxc0bf033.unix.marksandspencercate.com:8147/webdynpro/dispatcher/sap.com/tc~lm~itsam~co~ui~xi~ovcustom~wd/MonitoringHomeApp" 'catec  
'IE.navigate "http://clxc8bfqr1.unix.marksandspencercate.com:8147/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true" 'INTB
'IE.Navigate "http://hlxd0bf032.unix.marksandspencerdev.com:54400/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true" 'FCQ
'IE.Navigate "http://hlxc0bf030.unix.marksandspencercate.com:8147/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true" 'FQL
'IE.Navigate "http://hlxc0bf029.unix.marksandspencercate.com:54400/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true" 'FQL
'IE.Navigate "http://hlxc0bf027.unix.marksandspencercate.com:54400/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true#" 'FQJ


'Click Configuration and Monitoring Home link
'wait(2)
'For Each l In IE.Document.getElementsByTagName("A")  
'If l.classname = "urLnk" Then 
'If l.innertext = "Configuration and Monitoring Home" Then 
'l.click
'end if           
'End If 
'Next 
'
''Point to second Tab - Login page

For Each wnd In CreateObject("Shell.Application").Windows
If InStr(1, wnd.FullName, "iexplore.exe", vbTextCompare) > 0 Then
Set IE = wnd
End If
Next
'wait(3)
WScript.Sleep 3000
'Enter Credentials
Msgbox "1"
ExecutionRegion = ObjXMLData.Item("Environment")
	Select Case Ucase(Trim(ExecutionRegion))
	
	Case "CATEA"
For Each wnd In CreateObject("Shell.Application").Windows
If InStr(1, wnd.FullName, "iexplore.exe", vbTextCompare) > 0 Then
Set IE = wnd
End If
Next
'wait(3)
WScript.Sleep 3000
		IE.Document.getElementByID("logonuidfield").value = "P9134904"
		IE.Document.getElementByID("logonpassfield").value = "Sapfqj123"
        Case "CATEL"
		IE.Document.getElementByID("logonuidfield").value = "P9134904"
		IE.Document.getElementByID("logonpassfield").value = "Sapfql123"
	End Select


IE.Document.Forms(0).Submit()


'wait(2)
WScript.Sleep 4000

For Each wnd In CreateObject("Shell.Application").Windows
If InStr(1, wnd.FullName, "iexplore.exe", vbTextCompare) > 0 Then
Set IE = wnd
End If
Next
'wait(3)
WScript.Sleep 3000


'Click SOA tab
'IE.FullScreen = True
On error resume next
IE.Document.getElementByID("CEPJ.IDPView.HorizontalContextualPanel1.2-title").click
IE.Document.getElementByID("CEPJ.IDPView.HorizontalContextualPanel1.2-2").click

On error goto 0


On error resume next
WScript.Sleep 3000
For Each l In IE.Document.getElementsByTagName("A")  
If l.classname = "lsControl--valign lsLink urLnkFunction urTxtStd lsTextNoWrapping lsControl--noWrapping" Then
If l.innertext = "Message Monitor" Then 
l.click
IEnewpage1 = l.href
End if           
End If 
Next
'*********
On error goto 0

WScript.Sleep 3000

IE.Document.getElementByID("CEPJICNK.MessageOverviewView.timeInterval_DropDown").click
WshShell.SendKeys "{HOME}"
WshShell.SendKeys "{DOWN}"
WshShell.SendKeys "{ENTER}"
WScript.Sleep 1000
IE.Document.getElementByID("CEPJICNKDKEC.TableView.S_FILTER-img").focus
IE.Document.getElementByID("CEPJICNKDKEC.TableView.S_FILTER-img").click
WScript.Sleep 1000

IE.Document.getElementByID("CEPJICNKDKEC.TableView.ALVTable:2147483633").value="*"&Interface&"*"
IE.Document.getElementByID("CEPJICNKDKEC.TableView.ALVTable:2147483633").focus
WshShell.SendKeys "{ENTER}"

WScript.Sleep 1000
On Error Resume Next
IE.Document.getElementByID("CEPJICNKDKEC.TableView.LinkActionSUCCESSFUL.0").focus
IE.Document.getElementByID("CEPJICNKDKEC.TableView.LinkActionSUCCESSFUL.0-text").focus
On Error Goto 0
Call ScreenshotExcel_NonSAP("Message triggered from SAP and reached PO - PASS")


On Error Resume Next
For Each t in IE.Document.getElementsByTagName("A")

If t.classname = "lsControl--valign lsLink urLnkReportGl20 urTxtStd lsTextNoWrapping lsControl--noWrapping" Or t.classname = "lsControl--valign lsLink urLnkReportGl20 urTxtStd lsTextNoWrapping" Then
		 
If t.innertext = "Log Off" Then
t.click
End If
End If

Next
On Error Goto 0
WScript.Sleep 3000
IE.quit
IE.quit

Err.Description = "Object required"
Call ExportValueXML_ERROR("",Err.Description)


Set oExec = WshShell.Exec("taskkill /fi ""imagename eq iexplore.exe""")
WScript.Sleep 3000
Set oExec = nothing
ExeMode="CBTA_Vibase"



End Function



Function PO_validate_VB(Interface)

Dim IE
Set IE = CreateObject("InternetExplorer.Application")
Set WSHShell = CreateObject("WScript.Shell")


Wscript.Sleep 2000
IE.Visible = True
IE.navigate "http://hlxc0bf027.unix.marksandspencercate.com:54400/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true#" 'FCJ


Wscript.Sleep 3000
'WindowName = "Internet Explorer"
'While Not WSHShell.AppActivate(WindowName)

       ' WScript.Sleep 3

     ' Wend
'IE.Visible = True
'IE.FullScreen = True

For Each wnd In CreateObject("Shell.Application").Windows
If InStr(1, wnd.FullName, "iexplore.exe", vbTextCompare) > 0 Then
Set IE = wnd
End If
Next

Set objWord22 = CreateObject("Word.Application")
Set colTasks = objWord22.Tasks
For Each objTask22 in colTasks
	If Instr(objTask22.Name, "Internet Explorer") Then
	XLSrunning = 3
	objTask22.Activate
	objTask22.WindowState =&H00000001' wdWindowStateMaximize
	Exit For 'Exit this loop after the first Excel Application found!
	End if
	Next
	objWord22.quit
	Set objWord22 = nothing	
'wait(3)
'WScript.Sleep 3000
WScript.Sleep 3000
IE.Document.getElementByID("logonuidfield").value = "P9134904"
IE.Document.getElementByID("logonpassfield").value = "Sapfqj123"
IE.Document.Forms(0).Submit()
'WScript.Sleep 4000
'For Each wnd In CreateObject("Shell.Application").Windows
'If InStr(1, wnd.FullName, "iexplore.exe", vbTextCompare) > 0 Then
'Set IE = wnd
'End If
'Next
'wait(3)
WScript.Sleep 3000
'Click SOA tab
IE.Document.getElementByID("CEPJ.IDPView.HorizontalContextualPanel1.2-2").click
WScript.Sleep 3000
For Each l In IE.Document.getElementsByTagName("A")  
If l.classname = "lsControl--valign lsLink urLnkFunction urTxtStd lsTextNoWrapping" Then 
If l.innertext = "Message Monitor" Then 
l.click
IEnewpage1 = l.href
End if           
End If 
Next
WScript.Sleep 1500

IE.Document.getElementByID("CEPJICNK.MessageOverviewView.timeInterval_DropDown").click
WshShell.SendKeys "{HOME}"
WshShell.SendKeys "{DOWN}"
WshShell.SendKeys "{ENTER}"
WScript.Sleep 1000
IE.Document.getElementByID("CEPJICNKDKEC.TableView.S_FILTER-img").focus
IE.Document.getElementByID("CEPJICNKDKEC.TableView.S_FILTER-img").click
WScript.Sleep 1000

IE.Document.getElementByID("CEPJICNKDKEC.TableView.ALVTable:2147483633").value="*"&Interface&"*"
IE.Document.getElementByID("CEPJICNKDKEC.TableView.ALVTable:2147483633").focus
WshShell.SendKeys "{ENTER}"

WScript.Sleep 1000
IE.Document.getElementByID("CEPJICNKDKEC.TableView.LinkActionSUCCESSFUL.0").focus
Call ScreenshotExcel_NonSAP("Message triggered from SAP and reached PO - PASS")
IE.quit
End Function

Function PO_validate_idoc_VB(Interface,Idoc_number)

Dim IE
Set IE = CreateObject("InternetExplorer.Application")
Set WSHShell = CreateObject("WScript.Shell")


Wscript.Sleep 2000
IE.Visible = True
IE.navigate "http://hlxc0bf027.unix.marksandspencercate.com:54400/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true#" 'FCJ


Wscript.Sleep 3000
'WindowName = "Internet Explorer"
'While Not WSHShell.AppActivate(WindowName)

       ' WScript.Sleep 3

     ' Wend
'IE.Visible = True
'IE.FullScreen = True

For Each wnd In CreateObject("Shell.Application").Windows
If InStr(1, wnd.FullName, "iexplore.exe", vbTextCompare) > 0 Then
Set IE = wnd
End If
Next
'wait(3)
WScript.Sleep 3000
'WScript.Sleep 3000
IE.Document.getElementByID("logonuidfield").value = "P9134904"
IE.Document.getElementByID("logonpassfield").value = "Sapfqj123"
IE.Document.Forms(0).Submit()
WScript.Sleep 4000


For Each wnd In CreateObject("Shell.Application").Windows
If InStr(1, wnd.FullName, "iexplore.exe", vbTextCompare) > 0 Then
Set IE = wnd
End If
Next
'wait(3)
WScript.Sleep 3000
'Click SOA tab
IE.Document.getElementByID("CEPJ.IDPView.HorizontalContextualPanel1.2-2").click
WScript.Sleep 4000
For Each l In IE.Document.getElementsByTagName("A")  
If l.classname = "lsControl--valign lsLink urLnkFunction urTxtStd lsTextNoWrapping" Then 
If l.innertext = "Message Monitor" Then 
l.click
IEnewpage1 = l.href
End if           
End If 
Next
WScript.Sleep 2500

IE.Document.getElementByID("CEPJICNK.MessageOverviewView.timeInterval_DropDown").click
WshShell.SendKeys "{HOME}"
WshShell.SendKeys "{DOWN}"
WshShell.SendKeys "{ENTER}"
WScript.Sleep 1000
IE.Document.getElementByID("CEPJICNKDKEC.TableView.S_FILTER-img").focus
IE.Document.getElementByID("CEPJICNKDKEC.TableView.S_FILTER-img").click
WScript.Sleep 1000
IE.Document.getElementByID("CEPJICNKDKEC.TableView.ALVTable:2147483633").value="*"&Interface&"*"
Msgbox Interface
WshShell.SendKeys "{ENTER}"
WScript.Sleep 1000
Call ScreenshotExcel_NonSAP("Message reached - PASS")
IE.Document.getElementByID("CEPJICNKDKEC.TableView.LinkActionSUCCESSFUL.0").focus
IE.Document.getElementByID("CEPJICNKDKEC.TableView.LinkActionSUCCESSFUL.0").click

WScript.Sleep 1000

IE.Document.getElementByID("CEPJICNK.MessageFilterView.IDocAttributesCheckBox-img").click
WScript.Sleep 1000
IE.Document.getElementByID("CEPJICNK.MessageFilterView.IDocNumberInputField").value = "000000"&Idoc_number
IE.Document.getElementByID("CEPJICNK.MessageFilterView.Button1-txt").click
WScript.Sleep 1000

IE.Document.getElementByID("CEPJICNKPDIF.TableView.ALVTable:1.0").focus
IE.Document.getElementByID("CEPJICNKPDIF.TableView.ALVTable:1.0").click
Call ScreenshotExcel_NonSAP("Message triggered from SAP and reached PO - PASS")
IE.quit
End Function

Function PO_validate_idoc(Interface,Idoc_number)

Dim WshShell, oExec
Set WshShell = CreateObject("WScript.Shell")



Set oExec = WshShell.Exec("taskkill /fi ""imagename eq iexplore.exe""")
WScript.Sleep 5000
Set oExec = nothing
'Dim IE
Set IE = CreateObject("InternetExplorer.Application")




On Error Resume Next

Set IE = CreateObject("InternetExplorer.Application")
'msgbox Err.Description & "11111111111111111111111111111111111111111111111111111"
For ila=1 to 10

If Err.Description = "A system shutdown has already been scheduled" Then
msgbox Err.Description & "000000000000000000000000000000000000000000000000000000"
Err.Description="" 

WScript.Sleep 5000
Set IE = CreateObject("InternetExplorer.Application")

Else
Exit For
End If
Next 
On Error Goto 0


IE.Visible = 1

'Set objWord22 = CreateObject("Word.Application")
'Set colTasks = objWord22.Tasks
'For Each objTask22 in colTasks
	'If Instr(objTask22.Name, "Internet Explorer") Then
	'XLSrunning = 3
	'objTask22.Activate
	'objTask22.WindowState =&H00000001' wdWindowStateMaximize
	'Exit For 'Exit this loop after the first Excel Application found!
	'End if
	'Next
	'objWord22.quit
	'Set objWord22 = nothing	


ExecutionRegion = ObjXMLData.Item("Environment")
	Select Case Ucase(Trim(ExecutionRegion))
	
	Case "CATEA"
		IE.Navigate "http://hlxc0bf027.unix.marksandspencercate.com:54400/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true#" 'FQJ			
        Case "CATEL"
		IE.Navigate "http://hlxc0bf029.unix.marksandspencercate.com:54400/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true" 'FQL
	End Select
'IE.navigate "http://hlxc0bf033.unix.marksandspencercate.com:8147/webdynpro/dispatcher/sap.com/tc~lm~itsam~co~ui~xi~ovcustom~wd/MonitoringHomeApp" 'catec  
'IE.navigate "http://clxc8bfqr1.unix.marksandspencercate.com:8147/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true" 'INTB
'IE.Navigate "http://hlxd0bf032.unix.marksandspencerdev.com:54400/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true" 'FCQ
'IE.Navigate "http://hlxc0bf030.unix.marksandspencercate.com:8147/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true" 'FQL
'IE.Navigate "http://hlxc0bf029.unix.marksandspencercate.com:54400/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true" 'FQL
'IE.Navigate "http://hlxc0bf027.unix.marksandspencercate.com:54400/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true#" 'FQJ


'Click Configuration and Monitoring Home link
'wait(2)
'For Each l In IE.Document.getElementsByTagName("A")  
'If l.classname = "urLnk" Then 
'If l.innertext = "Configuration and Monitoring Home" Then 
'l.click
'end if           
'End If 
'Next 
'
''Point to second Tab - Login page

For Each wnd In CreateObject("Shell.Application").Windows
If InStr(1, wnd.FullName, "iexplore.exe", vbTextCompare) > 0 Then
Set IE = wnd
End If
Next
'wait(3)
WScript.Sleep 3000
'Enter Credentials
Msgbox "1"
ExecutionRegion = ObjXMLData.Item("Environment")
	Select Case Ucase(Trim(ExecutionRegion))
	
	Case "CATEA"
For Each wnd In CreateObject("Shell.Application").Windows
If InStr(1, wnd.FullName, "iexplore.exe", vbTextCompare) > 0 Then
Set IE = wnd
End If
Next
'wait(3)
WScript.Sleep 3000
		IE.Document.getElementByID("logonuidfield").value = "P9134904"
		IE.Document.getElementByID("logonpassfield").value = "Sapfqj123"
        Case "CATEL"
		IE.Document.getElementByID("logonuidfield").value = "P9134904"
		IE.Document.getElementByID("logonpassfield").value = "Sapfql123"
	End Select


IE.Document.Forms(0).Submit()


'wait(2)
WScript.Sleep 4000

For Each wnd In CreateObject("Shell.Application").Windows
If InStr(1, wnd.FullName, "iexplore.exe", vbTextCompare) > 0 Then
Set IE = wnd
End If
Next
'wait(3)
WScript.Sleep 3000


'Click SOA tab
'IE.FullScreen = True
On error resume next
IE.Document.getElementByID("CEPJ.IDPView.HorizontalContextualPanel1.2-title").click
IE.Document.getElementByID("CEPJ.IDPView.HorizontalContextualPanel1.2-2").click

On error goto 0


On error resume next
WScript.Sleep 3000
For Each l In IE.Document.getElementsByTagName("A")  
If l.classname = "lsControl--valign lsLink urLnkFunction urTxtStd lsTextNoWrapping lsControl--noWrapping" Then
If l.innertext = "Message Monitor" Then 
l.click
IEnewpage1 = l.href
End if           
End If 
Next
'*********
On error goto 0

WScript.Sleep 3000

IE.Document.getElementByID("CEPJICNK.MessageOverviewView.timeInterval_DropDown").click
WshShell.SendKeys "{HOME}"
WshShell.SendKeys "{DOWN}"
WshShell.SendKeys "{ENTER}"
WScript.Sleep 1000
IE.Document.getElementByID("CEPJICNKDKEC.TableView.S_FILTER-img").focus
IE.Document.getElementByID("CEPJICNKDKEC.TableView.S_FILTER-img").click
WScript.Sleep 1000
IE.Document.getElementByID("CEPJICNKDKEC.TableView.ALVTable:2147483633").value="*"&Interface&"*"
IE.Document.getElementByID("CEPJICNKDKEC.TableView.ALVTable:2147483633").focus
Wscript.sleep 1000
WshShell.SendKeys "{ENTER}"
WScript.Sleep 1000
Call ScreenshotExcel_NonSAP("Message reached - PASS")

IE.Document.getElementByID("CEPJICNKDKEC.TableView.LinkActionSUCCESSFUL.0").focus
IE.Document.getElementByID("CEPJICNKDKEC.TableView.LinkActionSUCCESSFUL.0").click

WScript.Sleep 1000

IE.Document.getElementByID("CEPJICNK.MessageFilterView.IDocAttributesCheckBox-img").click
WScript.Sleep 1000
'IE.Document.getElementByID("CEPJICNK.MessageFilterView.IDocNumberInputField").value = "000000"&Idoc_number
IE.Document.getElementByID("CEPJICNK.MessageFilterView.IDocNumberInputField").value = Idoc_number
IE.Document.getElementByID("CEPJICNK.MessageFilterView.IDocNumberInputField").focus
WScript.Sleep 2000
IE.Document.getElementByID("CEPJICNK.MessageFilterView.Button1").click
WScript.Sleep 1000
IE.Document.getElementByID("CEPJICNKPDIF.TableView.ALVTable:1.0").focus
IE.Document.getElementByID("CEPJICNKPDIF.TableView.ALVTable:1.0").click
Call ScreenshotExcel_NonSAP("Message triggered from SAP and reached PO - PASS")
'IE.quit

On Error Resume Next
For Each t in IE.Document.getElementsByTagName("A")

If t.classname = "lsControl--valign lsLink urLnkReportGl20 urTxtStd lsTextNoWrapping lsControl--noWrapping" Or t.classname = "lsControl--valign lsLink urLnkReportGl20 urTxtStd lsTextNoWrapping" Then
		 
If t.innertext = "Log Off" Then
t.click
End If
End If

Next
On Error Goto 0
WScript.Sleep 3000
IE.quit
IE.quit

Err.Description = "Object required"
Call ExportValueXML_ERROR("",Err.Description)

Set oExec = WshShell.Exec("taskkill /fi ""imagename eq iexplore.exe""")
WScript.Sleep 3000
Set oExec = nothing
ExeMode="CBTA_Vibase"

End Function
'''&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&

Function PO_validate_idoc_isap(Interface,Idoc_number)

'Dim IE
'Set IE = CreateObject("InternetExplorer.Application")
Set WSHShell = CreateObject("WScript.Shell")


'Wscript.Sleep 2000
'IE.Visible = True
'IE.navigate "http://hlxc0bf027.unix.marksandspencercate.com:54400/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true#" 'FCJ
'IE.navigate "http://clxc8bfqr1.unix.marksandspencercate.com:8147/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true" 'FQR


'Wscript.Sleep 3000
'WindowName = "Internet Explorer"
'While Not WSHShell.AppActivate(WindowName)

       'WScript.Sleep 3

      'Wend
'IE.Visible = True
'IE.FullScreen = True

'For Each wnd In CreateObject("Shell.Application").Windows
'If InStr(1, wnd.FullName, "iexplore.exe", vbTextCompare) > 0 Then
'Set IE = wnd
'End If
'Next
'wait(3)
'WScript.Sleep 3000
'WScript.Sleep 3000
'IE.Document.getElementByID("logonuidfield").value = "P3008861"
'IE.Document.getElementByID("logonpassfield").value = "sapfqr123"
'IE.Document.Forms(0).Submit()
'WScript.Sleep 4000

For Each wnd In CreateObject("Shell.Application").Windows
If InStr(1, wnd.FullName, "iexplore.exe", vbTextCompare) > 0 Then
Set IE = wnd
End If
Next
'wait(3)
WScript.Sleep 3000

'Click SOA tab

IE.Document.getElementByID("CEPJ.IDPView.HorizontalContextualPanel1.2-2").click
WScript.Sleep 4000
For Each l In IE.Document.getElementsByTagName("A")  
If l.classname = "lsControl--valign lsLink urLnkFunction urTxtStd lsTextNoWrapping" Then 
If l.innertext = "Message Monitor" Then 
l.click
IEnewpage1 = l.href
End if           
End If 
Next
WScript.Sleep 6000

IE.Document.getElementByID("CEPJICNK.MessageOverviewView.timeInterval_DropDown").click
WshShell.SendKeys "{HOME}"
WshShell.SendKeys "{DOWN}"
WshShell.SendKeys "{ENTER}"
WScript.Sleep 1000
IE.Document.getElementByID("CEPJICNKDKEC.TableView.S_FILTER-img").focus
IE.Document.getElementByID("CEPJICNKDKEC.TableView.S_FILTER-img").click
WScript.Sleep 1000
IE.Document.getElementByID("CEPJICNKDKEC.TableView.ALVTable:2147483633").value="*"&Interface&"*"
WshShell.SendKeys "{ENTER}"
WScript.Sleep 1000
'Call ScreenshotExcel_NonSAP("Message reached - PASS")
WScript.Sleep 2000
IE.Document.getElementByID("CEPJICNKDKEC.TableView.LinkActionSUCCESSFUL.0").focus
IE.Document.getElementByID("CEPJICNKDKEC.TableView.LinkActionSUCCESSFUL.0").click

WScript.Sleep 1000

IE.Document.getElementByID("CEPJICNK.MessageFilterView.IDocAttributesCheckBox-img").click
WScript.Sleep 1000
IE.Document.getElementByID("CEPJICNK.MessageFilterView.IDocNumberInputField").value = "000000"&Idoc_number
IE.Document.getElementByID("CEPJICNK.MessageFilterView.Button1-txt").click
WScript.Sleep 1000

IE.Document.getElementByID("CEPJICNKPDIF.TableView.ALVTable:1.0").focus
IE.Document.getElementByID("CEPJICNKPDIF.TableView.ALVTable:1.0").click
'Call ScreenshotExcel_NonSAP("Message triggered from SAP and reached PO - PASS")
WScript.Sleep 1000
IE.Document.getElementByID("CEPJ.IDPView.LogOffLinkToAction").click
WScript.Sleep 2000

IE.quit
End Function
'''&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&

Function PO_Post_ISAP(SenderInterface,InputXml)
Dim IE
Set IE = CreateObject("InternetExplorer.Application")

IE.Visible = 1
'IE.navigate "http://hlxc0bf033.unix.marksandspencercate.com:8147/webdynpro/dispatcher/sap.com/tc~lm~itsam~co~ui~xi~ovcustom~wd/MonitoringHomeApp" 'catec  
'IE.navigate "http://clxc8bfqr1.unix.marksandspencercate.com:8147/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true" 'INTB
'IE.Navigate "http://hlxd0bf032.unix.marksandspencerdev.com:54400/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true" 'FCQ
'IE.Navigate "http://hlxc0bf030.unix.marksandspencercate.com:8147/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true" 'FQL
'IE.Navigate "http://hlxc0bf027.unix.marksandspencercate.com:54400/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true#" 'FQJ
IE.Navigate "http://clxc8bfqr1.unix.marksandspencercate.com:8147/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true" 'FQR


'Click Configuration and Monitoring Home link
'wait(2)
'For Each l In IE.Document.getElementsByTagName("A")  
'If l.classname = "urLnk" Then 
'If l.innertext = "Configuration and Monitoring Home" Then 
'l.click
'End if           
'End If 
'Next 
'
''Point to second Tab - Login page
For Each wnd In CreateObject("Shell.Application").Windows
If InStr(1, wnd.FullName, "iexplore.exe", vbTextCompare) > 0 Then
Set IE = wnd
End If
Next
'wait(3)
WScript.Sleep 3000
'Enter Credentials

IE.Document.getElementByID("logonuidfield").value = "P9124459"
IE.Document.getElementByID("logonpassfield").value = "fqr12345"
IE.Document.Forms(0).Submit()
'Msgbox "login success"

'wait(2)
WScript.Sleep 4000


'Click SOA tab
IE.Document.getElementByID("CEPJ.IDPView.HorizontalContextualPanel1.2-2").click
'IE.Document.getElementsByTagName("A")
'Click Send Test message link
'wait(2)
WScript.Sleep 3000
'msgbox "checkpoint"
For Each l In IE.Document.getElementsByTagName("A")  
If l.classname = "lsControl--valign lsLink urLnkFunction urTxtStd lsTextNoWrapping" Then 
If l.innertext = "Send Test Message" Then 
l.click
IEnewpage1 = l.href
End if           
End If 
Next
'Msgbox "Send Test Message"

'Point to 3rd Tab - Send Test message
For Each wnd In CreateObject("Shell.Application").Windows
If InStr(1, wnd.FullName, "iexplore.exe", vbTextCompare) > 0 Then
Set IE = wnd
End If
Next

'Set WshShell = CreateObject("WScript.Shell")
'WshShell.AppActivate IEs


'wait(2)
WScript.Sleep 3000
PayloadContent = CopyXML_Contents(InputXml)
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").select
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").innertext = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").ContentEditable = False
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").disabled = True
'Msgbox "payload pasted"
WScript.Sleep 1000
'ScreenshotExcel_NonSAP("XML Copied")
WScript.Sleep 1000

WScript.Sleep 3000
'Click Search button
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.Button-img").click

'wait(2)
WScript.Sleep 2000
'IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
'IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").value = PayloadContent


'Search for the interface
Set aHTML = IE.document
set iframes = aHTML.getElementById("URLSPW-0")
WScript.Sleep 2000
Set obabc=iframes.contentdocument
'obabc.getElementById("CEPJICNK.PrefillFieldsView.PreFillFeildsTable:2147483639").value="I101_PickPGI"
obabc.getElementById("CEPJICNK.PrefillFieldsView.PreFillFeildsTable:2147483639").value=SenderInterface
'Msgbox "filter"
WScript.Sleep 3000
obabc.getElementById("CEPJICNK.PrefillFieldsView.PreFillFeildsTable:2147483639").focus
'wait(2)
WScript.Sleep 3000
Set WshShell = CreateObject("WScript.Shell")
'WshShell.AppActivate IEs
WshShell.SendKeys "{ENTER}"
obabc.getElementById("CEPJICNK.PrefillFieldsView.PreFillFeildsTable:2147483639").focus
'wait(2)
WScript.Sleep 1000
WshShell.SendKeys "{ENTER}"
'Msgbox "filter1"


WScript.Sleep 3000
obabc.getElementById("CEPJICNK.PrefillFieldsView.SITextView.0").focus
WshShell.SendKeys "{ENTER}"

WshShell.SendKeys "{ENTER}"

WScript.Sleep 2000
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.QOSDropDownByKey").click
WshShell.SendKeys "{DOWN}"
WshShell.SendKeys "{DOWN}"
WshShell.SendKeys "{ENTER}"
WScript.Sleep 2000

IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").select
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").innertext = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").ContentEditable = False
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").disabled = True
'Msgbox "payload1"

'ScreenshotExcel_NonSAP("Interface Details enetered")
IE.Document.getElementByID("CEPJ.IDPView.InputField").focus
WScript.Sleep 2000


For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text urBtnCntTxt" Then

If m.innertext = "Send" Then

m.click

End If

End If

Next
'Msgbox "sent"

WScript.Sleep 2000
'Status = IE.Document.getElementByID("CEPJ.IDPView.MessageArea-txt").innertext
'ScreenshotExcel_NonSAP("Message Posted")

IE.quit
IE.quit
End Function
'&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&

Public Function ScreenshotExcel_NonSAP(Description)

'For Heading

DescArr1 = Array(" 3 " )
DescArr2 = Array(" 57 " )
DescArr3 = Array(" 107 " )
DescArr4 = Array(" 157 " )
DescArr5 = Array(" 207 " )
DescArr6 = Array(" 257 " )
DescArr7 = Array(" 307 " )
DescArr8 = Array(" 357 " )
DescArr9 = Array(" 407 " )
DescArr10 = Array(" 457 " )
DescArr11 = Array(" 507 " )
DescArr12 = Array(" 557 " )
DescArr13 = Array(" 607 " )
DescArr14 = Array(" 657 " )
DescArr15 = Array(" 707 " )
'For Screenshot
Heading1 = Array(" 4 " )
Heading2 = Array(" 58 " )
Heading3 = Array(" 108 " )
Heading4 = Array(" 158 " )
Heading5 = Array(" 208 " )
Heading6 = Array(" 258 " )
Heading7 = Array(" 308 " )
Heading8 = Array(" 358 " )
Heading9 = Array(" 408 " )
Heading10 = Array(" 458 " )
Heading11 = Array(" 508 " )
Heading12 = Array(" 558 " )
Heading13 = Array(" 608 " )
Heading14 = Array(" 658 " )
Heading15 = Array(" 708 " )
ScnShtCount = ScnShtCount +1
Execute "Arrval = DescArr" & ScnShtCount & "(0)"
HeadingCount = HeadingCount + 1
Execute "Arrval1 = Heading" & HeadingCount & "(0)"
Call ScreenshotHeading_NonSAP(Arrval,Arrval1,Description)
End Function






Public Function ScreenshotHeading_NonSAP(Descval,cellval,heading)
	
strSheetname = Testname

	FilePath= ExcelPath &"\"&strSheetname&".xls"

	'mSGBOX FilePath


	Set objExcel = CreateObject("Excel.Application") 
	set objFso=CreateObject("Scripting.FileSystemObject")
	If objFso.FileExists(FilePath) then
		set oWB=objExcel.Workbooks.Open(FilePath)
	else
		Set oWB = objExcel.Workbooks.Add() 
		objExcel.ActiveWorkbook.SaveAs FilePath
	End if
	On Error Resume Next
	DateForm=Replace(time(),"/","")
 'If strcomp(ucase(trim(datatable.Value("Execution","Global"))),"YES")=0 Then

		'SetSheetNameArr = Split(Environment.Value("TestName"),"_")
		SetSheetName = TestCase

'End If
	
	SheetExists = IsObject(oWB.Worksheets(SetSheetName))

	err.number=0
	err.description = ""

	If Not SheetExists Then
		Set oSheet1=oWB.Sheets.Add  'a new sheet is added with default name i.e. Sheet4 etc.
		oSheet1.Name= SetSheetName 'rename newly added sheet
        oSheet1.Move ,oWB.Sheets(oWB.Sheets.Count)

		oWB.Save
		If HeadingCount<>1 Then
			HeadingCount=1
		End If
		If ScnShtCount<>1 Then
			ScnShtCount=1
		End If

	Else 
		'Set oSheet1=oWB.Worksheets(SetSheetName)

	End If
	For t=1 to 3

	flag=0
		SheetExists1 = IsObject(oWB.Worksheets("Sheet"&t))
		If Strcomp(err.Description,"Subscript out of range",vbtextcompare)=0 Then
			err.Description=""
			flag=1
			SheetExists1=False
		End If

		If flag=0 and SheetExists1 Then
			oWB.Worksheets("Sheet"&t).Delete
		End If
		'err.number=0
		
	Next
	
	objExcel.Visible = True

	

	
	Set objWord22 = CreateObject("Word.Application")
	Set colTasks = objWord22.Tasks

	'You may use WMI instead to detect the running Excel instance!
	'TheCount = GetObject("winmgmts:root\CIMV2").ExecQuery("SELECT * FROM Win32_Process WHERE Name='EXCEL.EXE'").Count





	objExcel.ActiveWorkbook.ActiveSheet.Cells(Descval,1).value = heading
	objExcel.ActiveWorkbook.ActiveSheet.Activate
	objExcel.ActiveWorkbook.ActiveSheet.Cells(cellval,1).Select
	Set obj = createobject("Word.Basic")
	
	
		
	For Each objTask22 in colTasks
	If Instr(objTask22.Name, "Microsoft Excel") Then
	XLSrunning = 2
	objTask22.Activate
	objTask22.WindowState =&H00000002' wdWindowStateMinimize
	Exit For 'Exit this loop after the first Excel Application found!
	End if
	Next

	obj.sendkeys "%{prtsc}"

	Wscript.Sleep 2000
	

	XLSrunning = 0
	For Each objTask22 in colTasks
	If Instr(objTask22.Name, "SAP HANA Administration Console") Then
	XLSrunning = 1
	objTask22.Activate
	objTask22.WindowState =&H00000002' wdWindowStateMinimize
	Exit For 'Exit this loop after the first Excel Application found!
	End If
	Next

	objExcel.Visible = True

	Wscript.Sleep 2000

	Set Wshshell = WScript.CreateObject("WScript.Shell")

	WshShell.AppActivate "Microsoft Excel"
	Wscript.Sleep 3000
	
	For Each objTask22 in colTasks
	If Instr(objTask22.Name, "Microsoft Excel") Then
	XLSrunning = 2
	objTask22.Activate
	objTask22.WindowState =&H00000001' wdWindowStateMaximize
	Exit For 'Exit this loop after the first Excel Application found!
	End if
	Next

	

	objExcel.Visible = True
	objExcel.ActiveWorkbook.ActiveSheet.Cells(cellval,1).Select
	Wscript.Sleep 3000

	WshShell.sendkeys "^(v)"
	Wscript.Sleep 3000
   objExcel.ActiveWorkbook.Save
   oWB.Save
	oWB.Close
	objExcel.Quit
	'objExcel.Application.Quit     
  
	Set oWB=Nothing 
	Set objExcel=Nothing
	SystemUtil.CloseProcessByName("EXCEL.EXE")
	'Set obj = CreateObject("Scripting.FileSystemObject") 'Calls the File System Object   
    'obj.DeleteFile ExcelPath &strFilesname
	'Set obj=Nothing
StepStatus=Split(heading," ")
index=Ubound(StepStatus)
ActualStatus=StepStatus(index)
StepDesc=Split(heading,"-")
	
	Call AddStepResultsToHTML_2(StepDesc(0),ActualStatus,"")
 	
	'For Each objTask22 in colTasks
	'If Instr(objTask22.Name, "Internet Explorer") Then
	'XLSrunning = 3
	'objTask22.Activate
	'objTask22.WindowState =&H00000001' wdWindowStateMaximize
	'Exit For 'Exit this loop after the first Excel Application found!
	'End if
	'Next
	'objWord22.quit
	'Set objWord22 = nothing	
	 	
	'Call AddStepResultsToHTML_2(StepDesc(0),ActualStatus,Path)

	If UCase(StepDesc(1)) = "FAIL" Then
		errormes = StepDesc(0)
		Call ExportValueXML_ERROR("",errormes)
	End If

End Function




Function PO_Post_I269_OrderInt_Original(SenderInterface,InputXml1,InputXml3,InputXml4,InputXml5,InputXml6,InputXml7)

Dim WshShell1, oExec
Set WshShell1 = CreateObject("WScript.Shell")



Set oExec = WshShell1.Exec("taskkill /fi ""imagename eq iexplore.exe""")
WScript.Sleep 20000
Set oExec = nothing
'Dim IE
Set IE = CreateObject("InternetExplorer.Application")


IE.Visible = 1

Set objWord22 = CreateObject("Word.Application")
Set colTasks = objWord22.Tasks
For Each objTask22 in colTasks
	If Instr(objTask22.Name, "Internet Explorer") Then
	XLSrunning = 3
	objTask22.Activate
	objTask22.WindowState =&H00000001' wdWindowStateMaximize
	Exit For 'Exit this loop after the first Excel Application found!
	End if
	Next
	objWord22.quit
	Set objWord22 = nothing	
ExecutionRegion = ObjXMLData.Item("Environment")
	Select Case Ucase(Trim(ExecutionRegion))
	
	Case "CATEA"
		IE.Navigate "http://hlxc0bf027.unix.marksandspencercate.com:54400/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true#" 'FQJ			
        Case "CATEL"
		IE.Navigate "http://hlxc0bf029.unix.marksandspencercate.com:54400/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true" 'FQL
	End Select
'IE.navigate "http://hlxc0bf033.unix.marksandspencercate.com:8147/webdynpro/dispatcher/sap.com/tc~lm~itsam~co~ui~xi~ovcustom~wd/MonitoringHomeApp" 'catec  
'IE.navigate "http://clxc8bfqr1.unix.marksandspencercate.com:8147/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true" 'INTB
'IE.Navigate "http://hlxd0bf032.unix.marksandspencerdev.com:54400/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true" 'FCQ
'IE.Navigate "http://hlxc0bf030.unix.marksandspencercate.com:8147/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true" 'FQL
'IE.Navigate "http://hlxc0bf029.unix.marksandspencercate.com:54400/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true" 'FQL
'IE.Navigate "http://hlxc0bf027.unix.marksandspencercate.com:54400/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true#" 'FQJ


'Click Configuration and Monitoring Home link
'wait(2)
'For Each l In IE.Document.getElementsByTagName("A")  
'If l.classname = "urLnk" Then 
'If l.innertext = "Configuration and Monitoring Home" Then 
'l.click
'end if           
'End If 
'Next 
'
''Point to second Tab - Login page

For Each wnd In CreateObject("Shell.Application").Windows
If InStr(1, wnd.FullName, "iexplore.exe", vbTextCompare) > 0 Then
Set IE = wnd
End If
Next
'wait(3)
WScript.Sleep 3000
'Enter Credentials
Msgbox "1"
ExecutionRegion = ObjXMLData.Item("Environment")
	Select Case Ucase(Trim(ExecutionRegion))
	
	Case "CATEA"
		IE.Document.getElementByID("logonuidfield").value = "P9134904"
		IE.Document.getElementByID("logonpassfield").value = "Sapfqj123"
        Case "CATEL"
		IE.Document.getElementByID("logonuidfield").value = "P9134904"
		IE.Document.getElementByID("logonpassfield").value = "Sapfql123"
	End Select
'IE.Document.getElementByID("logonuidfield").value = "P9134904"
'IE.Document.getElementByID("logonpassfield").value = "Sapfqj123"



IE.Document.Forms(0).Submit()



'wait(2)
WScript.Sleep 4000



For Each wnd In CreateObject("Shell.Application").Windows
If InStr(1, wnd.FullName, "iexplore.exe", vbTextCompare) > 0 Then
Set IE = wnd
End If
Next
'wait(3)
WScript.Sleep 4000



'Click SOA tab
'IE.FullScreen = True
On error resume next
IE.Document.getElementByID("CEPJ.IDPView.HorizontalContextualPanel1.2-title").click
IE.Document.getElementByID("CEPJ.IDPView.HorizontalContextualPanel1.2-2").click
'IE.Document.getElementByID("CEPJ.IDPView.HorizontalContextualPanel1.2-title").click
On error goto 0
'IE.Document.getElementsByTagName("A")
'Click Send Test message link
'wait(2)
On error resume next
WScript.Sleep 3000
For Each l In IE.Document.getElementsByTagName("A")  
If l.classname = "lsControl--valign lsLink urLnkFunction urTxtStd lsTextNoWrapping" Then
If l.innertext = "Send Test Message" Then 
l.click
IEnewpage1 = l.href
End if           
End If 
Next
'*********
For Each l In IE.Document.getElementsByTagName("A")  
If l.classname = "lsControl--valign lsLink urLnkFunction urTxtStd lsTextNoWrapping lsControl--noWrapping" Then
If l.innertext = "Send Test Message" Then 
l.click
IEnewpage1 = l.href
End if           
End If 
Next
'*********
On error goto 0


'Point to 3rd Tab - Send Test message
For Each wnd In CreateObject("Shell.Application").Windows
If InStr(1, wnd.FullName, "iexplore.exe", vbTextCompare) > 0 Then
Set IE = wnd
End If
Next

'Set WshShell = CreateObject("WScript.Shell")
'WshShell.AppActivate IEs


'wait(2)
WScript.Sleep 3000
PayloadContent = CopyXML_Contents(InputXml1)
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").select
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").innertext = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").ContentEditable = False
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").disabled = True
ScreenshotExcel_NonSAP("XML Copied")
'Msgbox "Copy to clipboard"
WScript.Sleep 3000
'Click Search button
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.Button-img").click

'wait(2)
WScript.Sleep 2000
'IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
'IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").value = PayloadContent


'Search for the interface
Set aHTML = IE.document
set iframes = aHTML.getElementById("URLSPW-0")
WScript.Sleep 2000
Set obabc=iframes.contentdocument
'obabc.getElementById("CEPJICNK.PrefillFieldsView.PreFillFeildsTable:2147483639").value="I101_PickPGI"
obabc.getElementById("CEPJICNK.PrefillFieldsView.PreFillFeildsTable:2147483639").value=SenderInterface
WScript.Sleep 3000
obabc.getElementById("CEPJICNK.PrefillFieldsView.PreFillFeildsTable:2147483639").focus
'wait(2)
WScript.Sleep 3000
Set WshShell = CreateObject("WScript.Shell")
'WshShell.AppActivate IEs
WshShell.SendKeys "{ENTER}"
obabc.getElementById("CEPJICNK.PrefillFieldsView.PreFillFeildsTable:2147483639").focus
'wait(2)
WScript.Sleep 1000
WshShell.SendKeys "{ENTER}"
'Msgbox "Check"

WScript.Sleep 3000
obabc.getElementById("CEPJICNK.PrefillFieldsView.SITextView.0").focus
WshShell.SendKeys "{ENTER}"

WshShell.SendKeys "{ENTER}"
'Msgbox "done"
WScript.Sleep 2000
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.QOSDropDownByKey").click
WshShell.SendKeys "{DOWN}"
WshShell.SendKeys "{DOWN}"
WshShell.SendKeys "{ENTER}"
WScript.Sleep 2000

IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").select
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").innertext = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").ContentEditable = False
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").disabled = True

ScreenshotExcel_NonSAP("Interface Details enetered")
IE.Document.getElementByID("CEPJ.IDPView.InputField").focus
WScript.Sleep 2000

On error resume next
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text urBtnCntTxt" Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'Msgbox "Post click"
'***********
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text " Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'***********
On error goto 0

Msgbox "1 done"
'XML 3
PayloadContent = CopyXML_Contents(InputXml3)
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").select
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").innertext = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").ContentEditable = False
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").disabled = True
ScreenshotExcel_NonSAP("XML Copied")
On error resume next
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text urBtnCntTxt" Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'Msgbox "Post click"
'***********
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text " Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'***********
On error goto 0

Msgbox "2 done"

'XML 4
PayloadContent = CopyXML_Contents(InputXml4)
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").select
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").innertext = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").ContentEditable = False
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").disabled = True
ScreenshotExcel_NonSAP("XML Copied")
On error resume next
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text urBtnCntTxt" Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'Msgbox "Post click"
'***********
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text " Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'***********
On error goto 0
Msgbox "3 done"

'XML 5
PayloadContent = CopyXML_Contents(InputXml5)
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").select
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").innertext = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").ContentEditable = False
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").disabled = True


ScreenshotExcel_NonSAP("XML Copied")
On error resume next
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text urBtnCntTxt" Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'Msgbox "Post click"
'***********
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text " Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'***********
On error goto 0
Msgbox "4 done"

'XML 6
PayloadContent = CopyXML_Contents(InputXml6)
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").select
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").innertext = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").ContentEditable = False
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").disabled = True
ScreenshotExcel_NonSAP("XML Copied")
On error resume next
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text urBtnCntTxt" Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'Msgbox "Post click"
'***********
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text " Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'***********
On error goto 0
Msgbox "5 done"

'XML 7
PayloadContent = CopyXML_Contents(InputXml7)
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").select
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").innertext = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").ContentEditable = False
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").disabled = True
ScreenshotExcel_NonSAP("XML Copied")
On error resume next
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text urBtnCntTxt" Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'Msgbox "Post click"
'***********
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text " Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'***********
On error goto 0
Msgbox "6 done"

'Msgbox "Post click"
WScript.Sleep 2000
'Status = IE.Document.getElementByID("CEPJ.IDPView.MessageArea-txt").innertext
ScreenshotExcel_NonSAP("Message Posted")
'Msgbox Status
IE.quit
IE.quit

Set oExec = WshShell.Exec("taskkill /fi ""imagename eq iexplore.exe""")
WScript.Sleep 3000
Set oExec = nothing
End Function


Function PO_Post_I269_OrderIntFranc_Original(SenderInterface,InputXml1,InputXml3,InputXml5,InputXml6,InputXml7)

Dim WshShell1, oExec
Set WshShell1 = CreateObject("WScript.Shell")



Set oExec = WshShell1.Exec("taskkill /fi ""imagename eq iexplore.exe""")
WScript.Sleep 20000
Set oExec = nothing
'Dim IE
Set IE = CreateObject("InternetExplorer.Application")


IE.Visible = 1
Set objWord22 = CreateObject("Word.Application")
Set colTasks = objWord22.Tasks
For Each objTask22 in colTasks
	If Instr(objTask22.Name, "Internet Explorer") Then
	XLSrunning = 3
	objTask22.Activate
	objTask22.WindowState =&H00000001' wdWindowStateMaximize
	Exit For 'Exit this loop after the first Excel Application found!
	End if
	Next
	objWord22.quit
	Set objWord22 = nothing	

ExecutionRegion = ObjXMLData.Item("Environment")
	Select Case Ucase(Trim(ExecutionRegion))
	
	Case "CATEA"
		IE.Navigate "http://hlxc0bf027.unix.marksandspencercate.com:54400/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true#" 'FQJ			
        Case "CATEL"
		IE.Navigate "http://hlxc0bf029.unix.marksandspencercate.com:54400/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true" 'FQL
	End Select
'IE.navigate "http://hlxc0bf033.unix.marksandspencercate.com:8147/webdynpro/dispatcher/sap.com/tc~lm~itsam~co~ui~xi~ovcustom~wd/MonitoringHomeApp" 'catec  
'IE.navigate "http://clxc8bfqr1.unix.marksandspencercate.com:8147/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true" 'INTB
'IE.Navigate "http://hlxd0bf032.unix.marksandspencerdev.com:54400/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true" 'FCQ
'IE.Navigate "http://hlxc0bf030.unix.marksandspencercate.com:8147/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true" 'FQL
'IE.Navigate "http://hlxc0bf029.unix.marksandspencercate.com:54400/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true" 'FQL
'IE.Navigate "http://hlxc0bf027.unix.marksandspencercate.com:54400/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true#" 'FQJ


'Click Configuration and Monitoring Home link
'wait(2)
'For Each l In IE.Document.getElementsByTagName("A")  
'If l.classname = "urLnk" Then 
'If l.innertext = "Configuration and Monitoring Home" Then 
'l.click
'end if           
'End If 
'Next 
'
''Point to second Tab - Login page

For Each wnd In CreateObject("Shell.Application").Windows
If InStr(1, wnd.FullName, "iexplore.exe", vbTextCompare) > 0 Then
Set IE = wnd
End If
Next
'wait(3)
WScript.Sleep 3000
'Enter Credentials
Msgbox "1"
ExecutionRegion = ObjXMLData.Item("Environment")
	Select Case Ucase(Trim(ExecutionRegion))
	
	Case "CATEA"
For Each wnd In CreateObject("Shell.Application").Windows
If InStr(1, wnd.FullName, "iexplore.exe", vbTextCompare) > 0 Then
Set IE = wnd
End If
Next
'wait(3)
WScript.Sleep 3000
		IE.Document.getElementByID("logonuidfield").value = "P9134904"
		IE.Document.getElementByID("logonpassfield").value = "Sapfqj123"
        Case "CATEL"
		IE.Document.getElementByID("logonuidfield").value = "P9134904"
		IE.Document.getElementByID("logonpassfield").value = "Sapfql123"
	End Select
'IE.Document.getElementByID("logonuidfield").value = "P9134904"
'IE.Document.getElementByID("logonpassfield").value = "Sapfqj123"



IE.Document.Forms(0).Submit()



'wait(2)
WScript.Sleep 4000



For Each wnd In CreateObject("Shell.Application").Windows
If InStr(1, wnd.FullName, "iexplore.exe", vbTextCompare) > 0 Then
Set IE = wnd
End If
Next
'wait(3)
WScript.Sleep 4000



'Click SOA tab
'IE.FullScreen = True
On error resume next
IE.Document.getElementByID("CEPJ.IDPView.HorizontalContextualPanel1.2-title").click
IE.Document.getElementByID("CEPJ.IDPView.HorizontalContextualPanel1.2-2").click
'IE.Document.getElementByID("CEPJ.IDPView.HorizontalContextualPanel1.2-title").click
On error goto 0
'IE.Document.getElementsByTagName("A")
'Click Send Test message link
'wait(2)
On error resume next
WScript.Sleep 3000
For Each l In IE.Document.getElementsByTagName("A")  
If l.classname = "lsControl--valign lsLink urLnkFunction urTxtStd lsTextNoWrapping" Then
If l.innertext = "Send Test Message" Then 
l.click
IEnewpage1 = l.href
End if           
End If 
Next
'*********
For Each l In IE.Document.getElementsByTagName("A")  
If l.classname = "lsControl--valign lsLink urLnkFunction urTxtStd lsTextNoWrapping lsControl--noWrapping" Then
If l.innertext = "Send Test Message" Then 
l.click
IEnewpage1 = l.href
End if           
End If 
Next
'*********
On error goto 0

'Point to 3rd Tab - Send Test message
For Each wnd In CreateObject("Shell.Application").Windows
If InStr(1, wnd.FullName, "iexplore.exe", vbTextCompare) > 0 Then
Set IE = wnd
End If
Next

'Set WshShell = CreateObject("WScript.Shell")
'WshShell.AppActivate IEs


'wait(2)
WScript.Sleep 3000
PayloadContent = CopyXML_Contents(InputXml1)
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").select
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").innertext = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").ContentEditable = False
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").disabled = True
'ScreenshotExcel_NonSAP("XML Copied")
'Msgbox "Copy to clipboard"
WScript.Sleep 3000
'Click Search button
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.Button-img").click
'wait(2)
WScript.Sleep 2000
'IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
'IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").value = PayloadContent


'Search for the interface
Set aHTML = IE.document
set iframes = aHTML.getElementById("URLSPW-0")
WScript.Sleep 2000
Set obabc=iframes.contentdocument
'obabc.getElementById("CEPJICNK.PrefillFieldsView.PreFillFeildsTable:2147483639").value="I101_PickPGI"
obabc.getElementById("CEPJICNK.PrefillFieldsView.PreFillFeildsTable:2147483639").value=SenderInterface
WScript.Sleep 3000
obabc.getElementById("CEPJICNK.PrefillFieldsView.PreFillFeildsTable:2147483639").focus
'wait(2)
WScript.Sleep 3000
Set WshShell = CreateObject("WScript.Shell")
'WshShell.AppActivate IEs
WshShell.SendKeys "{ENTER}"
obabc.getElementById("CEPJICNK.PrefillFieldsView.PreFillFeildsTable:2147483639").focus
'wait(2)
WScript.Sleep 1000
WshShell.SendKeys "{ENTER}"
'Msgbox "Check"

WScript.Sleep 3000
obabc.getElementById("CEPJICNK.PrefillFieldsView.SITextView.0").focus
WshShell.SendKeys "{ENTER}"

WshShell.SendKeys "{ENTER}"
'Msgbox "done"
WScript.Sleep 2000
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.QOSDropDownByKey").click
WshShell.SendKeys "{DOWN}"
WshShell.SendKeys "{DOWN}"
WshShell.SendKeys "{ENTER}"
WScript.Sleep 2000

IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").select
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").innertext = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").ContentEditable = False
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").disabled = True

'ScreenshotExcel_NonSAP("Interface Details enetered")
IE.Document.getElementByID("CEPJ.IDPView.InputField").focus
WScript.Sleep 2000

On error resume next
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text urBtnCntTxt" Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'Msgbox "Post click"
'***********
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text " Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'***********
On error goto 0

Msgbox "1 done"

'XML 3
PayloadContent = CopyXML_Contents(InputXml3)
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").select
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").innertext = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").ContentEditable = False
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").disabled = True
'ScreenshotExcel_NonSAP("XML Copied")
On error resume next
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text urBtnCntTxt" Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'Msgbox "Post click"
'***********
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text " Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'***********
On error goto 0

Msgbox "2 done"

'XML 5
PayloadContent = CopyXML_Contents(InputXml5)
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").select
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").innertext = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").ContentEditable = False
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").disabled = True


'ScreenshotExcel_NonSAP("XML Copied")
On error resume next
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text urBtnCntTxt" Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'Msgbox "Post click"
'***********
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text " Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'***********
On error goto 0
Msgbox "3 done"

'XML 6
PayloadContent = CopyXML_Contents(InputXml6)
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").select
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").innertext = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").ContentEditable = False
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").disabled = True
ScreenshotExcel_NonSAP("XML Copied")
'On error resume next
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text urBtnCntTxt" Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'Msgbox "Post click"
'***********
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text " Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'***********
On error goto 0
Msgbox "4 done"
'XML 7
PayloadContent = CopyXML_Contents(InputXml7)
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").select
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").innertext = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").ContentEditable = False
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").disabled = True
'ScreenshotExcel_NonSAP("XML Copied")
On error resume next
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text urBtnCntTxt" Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'Msgbox "Post click"
'***********
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text " Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'***********
On error goto 0
Msgbox "5 done"
'Msgbox "Post click"
WScript.Sleep 2000
'Status = IE.Document.getElementByID("CEPJ.IDPView.MessageArea-txt").innertext
'ScreenshotExcel_NonSAP("Message Posted")
'Msgbox Status
IE.quit
IE.quit

Set oExec = WshShell.Exec("taskkill /fi ""imagename eq iexplore.exe""")
WScript.Sleep 3000
Set oExec = nothing
End Function

Function PO_Post_I269_Franchise_Original(SenderInterface,InputXml1,InputXml2,InputXml3,InputXml4,InputXml5,InputXml6)

Dim WshShell1, oExec
Set WshShell1 = CreateObject("WScript.Shell")



Set oExec = WshShell1.Exec("taskkill /fi ""imagename eq iexplore.exe""")
WScript.Sleep 20000
Set oExec = nothing
'Dim IE
Set IE = CreateObject("InternetExplorer.Application")


IE.Visible = 1
Set objWord22 = CreateObject("Word.Application")
Set colTasks = objWord22.Tasks
For Each objTask22 in colTasks
	If Instr(objTask22.Name, "Internet Explorer") Then
	XLSrunning = 3
	objTask22.Activate
	objTask22.WindowState =&H00000001' wdWindowStateMaximize
	Exit For 'Exit this loop after the first Excel Application found!
	End if
	Next
	objWord22.quit
	Set objWord22 = nothing	

ExecutionRegion = ObjXMLData.Item("Environment")
	Select Case Ucase(Trim(ExecutionRegion))
	
	Case "CATEA"
		IE.Navigate "http://hlxc0bf027.unix.marksandspencercate.com:54400/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true#" 'FQJ			
        Case "CATEL"
		IE.Navigate "http://hlxc0bf029.unix.marksandspencercate.com:54400/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true" 'FQL
	End Select
'IE.navigate "http://hlxc0bf033.unix.marksandspencercate.com:8147/webdynpro/dispatcher/sap.com/tc~lm~itsam~co~ui~xi~ovcustom~wd/MonitoringHomeApp" 'catec  
'IE.navigate "http://clxc8bfqr1.unix.marksandspencercate.com:8147/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true" 'INTB
'IE.Navigate "http://hlxd0bf032.unix.marksandspencerdev.com:54400/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true" 'FCQ
'IE.Navigate "http://hlxc0bf030.unix.marksandspencercate.com:8147/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true" 'FQL
'IE.Navigate "http://hlxc0bf029.unix.marksandspencercate.com:54400/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true" 'FQL
'IE.Navigate "http://hlxc0bf027.unix.marksandspencercate.com:54400/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true#" 'FQJ


'Click Configuration and Monitoring Home link
'wait(2)
'For Each l In IE.Document.getElementsByTagName("A")  
'If l.classname = "urLnk" Then 
'If l.innertext = "Configuration and Monitoring Home" Then 
'l.click
'end if           
'End If 
'Next 
'
''Point to second Tab - Login page

For Each wnd In CreateObject("Shell.Application").Windows
If InStr(1, wnd.FullName, "iexplore.exe", vbTextCompare) > 0 Then
Set IE = wnd
End If
Next
'wait(3)
WScript.Sleep 3000
'Enter Credentials
Msgbox "1"
ExecutionRegion = ObjXMLData.Item("Environment")
	Select Case Ucase(Trim(ExecutionRegion))
	
	Case "CATEA"
		IE.Document.getElementByID("logonuidfield").value = "P9134904"
		IE.Document.getElementByID("logonpassfield").value = "Sapfqj123"
        Case "CATEL"
		IE.Document.getElementByID("logonuidfield").value = "P9134904"
		IE.Document.getElementByID("logonpassfield").value = "Sapfql123"
	End Select
'IE.Document.getElementByID("logonuidfield").value = "P9134904"
'IE.Document.getElementByID("logonpassfield").value = "Sapfqj123"



IE.Document.Forms(0).Submit()



'wait(2)
WScript.Sleep 4000



For Each wnd In CreateObject("Shell.Application").Windows
If InStr(1, wnd.FullName, "iexplore.exe", vbTextCompare) > 0 Then
Set IE = wnd
End If
Next
'wait(3)
WScript.Sleep 4000



'Click SOA tab
'IE.FullScreen = True
On error resume next
IE.Document.getElementByID("CEPJ.IDPView.HorizontalContextualPanel1.2-title").click
IE.Document.getElementByID("CEPJ.IDPView.HorizontalContextualPanel1.2-2").click
'IE.Document.getElementByID("CEPJ.IDPView.HorizontalContextualPanel1.2-title").click
On error goto 0
'IE.Document.getElementsByTagName("A")
'Click Send Test message link
'wait(2)
On error resume next
WScript.Sleep 3000
For Each l In IE.Document.getElementsByTagName("A")  
If l.classname = "lsControl--valign lsLink urLnkFunction urTxtStd lsTextNoWrapping" Then
If l.innertext = "Send Test Message" Then 
l.click
IEnewpage1 = l.href
End if           
End If 
Next
'*********
For Each l In IE.Document.getElementsByTagName("A")  
If l.classname = "lsControl--valign lsLink urLnkFunction urTxtStd lsTextNoWrapping lsControl--noWrapping" Then
If l.innertext = "Send Test Message" Then 
l.click
IEnewpage1 = l.href
End if           
End If 
Next
'*********
On error goto 0

'Point to 3rd Tab - Send Test message
For Each wnd In CreateObject("Shell.Application").Windows
If InStr(1, wnd.FullName, "iexplore.exe", vbTextCompare) > 0 Then
Set IE = wnd
End If
Next

'Set WshShell = CreateObject("WScript.Shell")
'WshShell.AppActivate IEs


'wait(2)
WScript.Sleep 3000
PayloadContent = CopyXML_Contents(InputXml1)
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").select
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").innertext = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").ContentEditable = False
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").disabled = True
ScreenshotExcel_NonSAP("XML Copied")
'Msgbox "Copy to clipboard"
WScript.Sleep 3000
'Click Search button
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.Button-img").click

'wait(2)
WScript.Sleep 2000
'IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
'IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").value = PayloadContent


'Search for the interface
Set aHTML = IE.document
set iframes = aHTML.getElementById("URLSPW-0")
WScript.Sleep 2000
Set obabc=iframes.contentdocument
'obabc.getElementById("CEPJICNK.PrefillFieldsView.PreFillFeildsTable:2147483639").value="I101_PickPGI"
obabc.getElementById("CEPJICNK.PrefillFieldsView.PreFillFeildsTable:2147483639").value=SenderInterface
WScript.Sleep 3000
obabc.getElementById("CEPJICNK.PrefillFieldsView.PreFillFeildsTable:2147483639").focus
'wait(2)
WScript.Sleep 3000
Set WshShell = CreateObject("WScript.Shell")
'WshShell.AppActivate IEs
WshShell.SendKeys "{ENTER}"
obabc.getElementById("CEPJICNK.PrefillFieldsView.PreFillFeildsTable:2147483639").focus
'wait(2)
WScript.Sleep 1000
WshShell.SendKeys "{ENTER}"
'Msgbox "Check"

WScript.Sleep 3000
obabc.getElementById("CEPJICNK.PrefillFieldsView.SITextView.0").focus
WshShell.SendKeys "{ENTER}"

WshShell.SendKeys "{ENTER}"
'Msgbox "done"
WScript.Sleep 2000
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.QOSDropDownByKey").click
WshShell.SendKeys "{DOWN}"
WshShell.SendKeys "{DOWN}"
WshShell.SendKeys "{ENTER}"
WScript.Sleep 2000

IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").select
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").innertext = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").ContentEditable = False
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").disabled = True

ScreenshotExcel_NonSAP("Interface Details enetered")
IE.Document.getElementByID("CEPJ.IDPView.InputField").focus
WScript.Sleep 2000

'Msgbox "125"
On error resume next
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text urBtnCntTxt" Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'Msgbox "Post click"
'***********
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text " Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'***********
On error goto 0

Msgbox "1 done"
'XML 2
PayloadContent = CopyXML_Contents(InputXml2)

IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").select
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").innertext = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").ContentEditable = False
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").disabled = True
ScreenshotExcel_NonSAP("XML Copied")
On error resume next
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text urBtnCntTxt" Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'Msgbox "Post click"
'***********
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text " Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'***********
On error goto 0

Msgbox "2 done"
'XML 3
PayloadContent = CopyXML_Contents(InputXml3)
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").select
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").innertext = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").ContentEditable = False
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").disabled = True
ScreenshotExcel_NonSAP("XML Copied")
On error resume next
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text urBtnCntTxt" Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'Msgbox "Post click"
'***********
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text " Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'***********
On error goto 0

Msgbox "3 done"
'XML 4
PayloadContent = CopyXML_Contents(InputXml4)
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").select
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").innertext = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").ContentEditable = False
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").disabled = True
ScreenshotExcel_NonSAP("XML Copied")
On error resume next
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text urBtnCntTxt" Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'Msgbox "Post click"
'***********
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text " Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'***********
On error goto 0

Msgbox "4 done"
'XML 5
PayloadContent = CopyXML_Contents(InputXml5)
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").select
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").innertext = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").ContentEditable = False
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").disabled = True


ScreenshotExcel_NonSAP("XML Copied")
On error resume next
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text urBtnCntTxt" Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'Msgbox "Post click"
'***********
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text " Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'***********
On error goto 0

Msgbox "5 done"
'XML 6
PayloadContent = CopyXML_Contents(InputXml6)
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").select
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").innertext = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").ContentEditable = False
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").disabled = True
ScreenshotExcel_NonSAP("XML Copied")
On error resume next
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text urBtnCntTxt" Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'Msgbox "Post click"
'***********
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text " Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'***********
On error goto 0

Msgbox "6 done"


'Msgbox "Post click"
WScript.Sleep 2000
'Status = IE.Document.getElementByID("CEPJ.IDPView.MessageArea-txt").innertext
ScreenshotExcel_NonSAP("Message Posted")
'Msgbox Status
IE.quit
IE.quit
End Function


Function PO_Post_I269_Store(SenderInterface,InputXml1,InputXml2,InputXml3,InputXml4,InputXml5,InputXml6,InputXml7,InputXml8)

'Dim WshShell1, oExec
Set WshShell1 = CreateObject("WScript.Shell")




Set oExec = WshShell1.Exec("taskkill /fi ""imagename eq iexplore.exe""")
WScript.Sleep 5000
Set oExec = nothing
'Dim IE
Set IE = CreateObject("InternetExplorer.Application")


IE.Visible = 1

Set objWord22 = CreateObject("Word.Application")
Set colTasks = objWord22.Tasks
For Each objTask22 in colTasks
	If Instr(objTask22.Name, "Internet Explorer") Then
	XLSrunning = 3
	objTask22.Activate
	objTask22.WindowState =&H00000001' wdWindowStateMaximize
	Exit For 'Exit this loop after the first Excel Application found!
	End if
	Next
	objWord22.quit
	Set objWord22 = nothing	

ExecutionRegion = ObjXMLData.Item("Environment")
	Select Case Ucase(Trim(ExecutionRegion))
	
	Case "CATEA"
		IE.Navigate "http://hlxc0bf027.unix.marksandspencercate.com:54400/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true#" 'FQJ			
        Case "CATEL"
		IE.Navigate "http://hlxc0bf029.unix.marksandspencercate.com:54400/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true" 'FQL
	End Select
'IE.navigate "http://hlxc0bf033.unix.marksandspencercate.com:8147/webdynpro/dispatcher/sap.com/tc~lm~itsam~co~ui~xi~ovcustom~wd/MonitoringHomeApp" 'catec  
'IE.navigate "http://clxc8bfqr1.unix.marksandspencercate.com:8147/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true" 'INTB
'IE.Navigate "http://hlxd0bf032.unix.marksandspencerdev.com:54400/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true" 'FCQ
'IE.Navigate "http://hlxc0bf030.unix.marksandspencercate.com:8147/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true" 'FQL
'IE.Navigate "http://hlxc0bf029.unix.marksandspencercate.com:54400/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true" 'FQL
'IE.Navigate "http://hlxc0bf027.unix.marksandspencercate.com:54400/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true#" 'FQJ


'Click Configuration and Monitoring Home link
'wait(2)
'For Each l In IE.Document.getElementsByTagName("A")  
'If l.classname = "urLnk" Then 
'If l.innertext = "Configuration and Monitoring Home" Then 
'l.click
'end if           
'End If 
'Next 
'
''Point to second Tab - Login page

For Each wnd In CreateObject("Shell.Application").Windows
If InStr(1, wnd.FullName, "iexplore.exe", vbTextCompare) > 0 Then
Set IE = wnd
End If
Next
'wait(3)
WScript.Sleep 3000
'Enter Credentials
Msgbox "1"
ExecutionRegion = ObjXMLData.Item("Environment")
	Select Case Ucase(Trim(ExecutionRegion))
	
	Case "CATEA"
		IE.Document.getElementByID("logonuidfield").value = "P9134904"
		IE.Document.getElementByID("logonpassfield").value = "Sapfqj123"
        Case "CATEL"
		IE.Document.getElementByID("logonuidfield").value = "P9134904"
		IE.Document.getElementByID("logonpassfield").value = "Sapfql123"
	End Select
'IE.Document.getElementByID("logonuidfield").value = "P9134904"
'IE.Document.getElementByID("logonpassfield").value = "Sapfqj123"



IE.Document.Forms(0).Submit()



'wait(2)
WScript.Sleep 4000



For Each wnd In CreateObject("Shell.Application").Windows
If InStr(1, wnd.FullName, "iexplore.exe", vbTextCompare) > 0 Then
Set IE = wnd
End If
Next
'wait(3)
WScript.Sleep 4000



'Click SOA tab
'IE.FullScreen = True
On error resume next
IE.Document.getElementByID("CEPJ.IDPView.HorizontalContextualPanel1.2-title").click
IE.Document.getElementByID("CEPJ.IDPView.HorizontalContextualPanel1.2-2").click
'IE.Document.getElementByID("CEPJ.IDPView.HorizontalContextualPanel1.2-title").click
On error goto 0
'IE.Document.getElementsByTagName("A")
'Click Send Test message link
'wait(2)
On error resume next
WScript.Sleep 3000
For Each l In IE.Document.getElementsByTagName("A")  
If l.classname = "lsControl--valign lsLink urLnkFunction urTxtStd lsTextNoWrapping" Then
If l.innertext = "Send Test Message" Then 
l.click
IEnewpage1 = l.href
End if           
End If 
Next
'*********
For Each l In IE.Document.getElementsByTagName("A")  
If l.classname = "lsControl--valign lsLink urLnkFunction urTxtStd lsTextNoWrapping lsControl--noWrapping" Then
If l.innertext = "Send Test Message" Then 
l.click
IEnewpage1 = l.href
End if           
End If 
Next
'*********
On error goto 0


'Point to 3rd Tab - Send Test message
For Each wnd In CreateObject("Shell.Application").Windows
If InStr(1, wnd.FullName, "iexplore.exe", vbTextCompare) > 0 Then
Set IE = wnd
End If
Next

'Set WshShell = CreateObject("WScript.Shell")
'WshShell.AppActivate IEs


'wait(2)
WScript.Sleep 3000
PayloadContent = CopyXML_Contents(InputXml1)
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").select
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").innertext = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").ContentEditable = False
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").disabled = True
ScreenshotExcel_NonSAP("XML Copied")
'Msgbox "Copy to clipboard"
WScript.Sleep 3000
'Click Search button
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.Button-img").click

'wait(2)
WScript.Sleep 2000
'IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
'IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").value = PayloadContent


'Search for the interface
Set aHTML = IE.document
set iframes = aHTML.getElementById("URLSPW-0")
WScript.Sleep 2000
Set obabc=iframes.contentdocument
'obabc.getElementById("CEPJICNK.PrefillFieldsView.PreFillFeildsTable:2147483639").value="I101_PickPGI"
obabc.getElementById("CEPJICNK.PrefillFieldsView.PreFillFeildsTable:2147483639").value=SenderInterface
WScript.Sleep 3000
obabc.getElementById("CEPJICNK.PrefillFieldsView.PreFillFeildsTable:2147483639").focus
'wait(2)
WScript.Sleep 3000
Set WshShell = CreateObject("WScript.Shell")
'WshShell.AppActivate IEs
WshShell.SendKeys "{ENTER}"
obabc.getElementById("CEPJICNK.PrefillFieldsView.PreFillFeildsTable:2147483639").focus
'wait(2)
WScript.Sleep 1000
WshShell.SendKeys "{ENTER}"
'Msgbox "Check"

WScript.Sleep 3000
obabc.getElementById("CEPJICNK.PrefillFieldsView.SITextView.0").focus
WshShell.SendKeys "{ENTER}"

WshShell.SendKeys "{ENTER}"
'Msgbox "done"
WScript.Sleep 2000
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.QOSDropDownByKey").click
WshShell.SendKeys "{DOWN}"
WshShell.SendKeys "{DOWN}"
WshShell.SendKeys "{ENTER}"
WScript.Sleep 2000



'Point to 3rd Tab - Send Test message
For Each wnd In CreateObject("Shell.Application").Windows
If InStr(1, wnd.FullName, "iexplore.exe", vbTextCompare) > 0 Then
Set IE = wnd
End If
Next

'Set WshShell = CreateObject("WScript.Shell")
'WshShell.AppActivate IEs


'wait(2)
WScript.Sleep 3000
PayloadContent = CopyXML_Contents(InputXml1)
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").select
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").innertext = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").ContentEditable = False
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").disabled = True
'ScreenshotExcel_NonSAP("XML Copied")
'Msgbox "Copy to clipboard"
WScript.Sleep 3000
'Click Search button
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.Button-img").click

'wait(2)
WScript.Sleep 2000
'IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
'IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").value = PayloadContent


'Search for the interface
Set aHTML = IE.document
set iframes = aHTML.getElementById("URLSPW-0")
WScript.Sleep 2000
Set obabc=iframes.contentdocument
'obabc.getElementById("CEPJICNK.PrefillFieldsView.PreFillFeildsTable:2147483639").value="I101_PickPGI"
obabc.getElementById("CEPJICNK.PrefillFieldsView.PreFillFeildsTable:2147483639").value=SenderInterface
WScript.Sleep 3000
obabc.getElementById("CEPJICNK.PrefillFieldsView.PreFillFeildsTable:2147483639").focus
'wait(2)
WScript.Sleep 3000
Set WshShell = CreateObject("WScript.Shell")
'WshShell.AppActivate IEs
WshShell.SendKeys "{ENTER}"
obabc.getElementById("CEPJICNK.PrefillFieldsView.PreFillFeildsTable:2147483639").focus
'wait(2)
WScript.Sleep 1000
WshShell.SendKeys "{ENTER}"
'Msgbox "Check"

WScript.Sleep 3000
obabc.getElementById("CEPJICNK.PrefillFieldsView.SITextView.0").focus
WshShell.SendKeys "{ENTER}"

WshShell.SendKeys "{ENTER}"
'Msgbox "done"
WScript.Sleep 2000
'IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.QOSDropDownByKey").click
'WshShell.SendKeys "{DOWN}"
'WshShell.SendKeys "{DOWN}"
'WshShell.SendKeys "{ENTER}"
WScript.Sleep 2000

IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").select
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").innertext = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").ContentEditable = False
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").disabled = True

'ScreenshotExcel_NonSAP("Interface Details enetered")
IE.Document.getElementByID("CEPJ.IDPView.InputField").focus
WScript.Sleep 2000

On error resume next
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text urBtnCntTxt" Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'Msgbox "Post click"
'***********
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text " Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'***********
On error goto 0

'Msgbox "1 done"
'XML 2
WScript.Sleep 2000
PayloadContent = CopyXML_Contents(InputXml2)
WScript.Sleep 2000
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").select
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").innertext = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").ContentEditable = False
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").disabled = True
ScreenshotExcel_NonSAP("XML Copied")
On error resume next
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text urBtnCntTxt" Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'Msgbox "Post click"
'***********
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text " Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'***********
On error goto 0

'Msgbox "2 done"


'XML 3
WScript.Sleep 2000
PayloadContent = CopyXML_Contents(InputXml3)
WScript.Sleep 2000
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").select
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").innertext = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").ContentEditable = False
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").disabled = True
ScreenshotExcel_NonSAP("XML Copied")
On error resume next
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text urBtnCntTxt" Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'Msgbox "Post click"
'***********
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text " Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'***********
On error goto 0

'Msgbox "3 done"


'XML 4
WScript.Sleep 2000
PayloadContent = CopyXML_Contents(InputXml4)
WScript.Sleep 2000
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").select
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").innertext = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").ContentEditable = False
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").disabled = True
ScreenshotExcel_NonSAP("XML Copied")
On error resume next
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text urBtnCntTxt" Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'Msgbox "Post click"
'***********
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text " Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'***********
On error goto 0

'Msgbox "4 done"


'XML 5
WScript.Sleep 2000
PayloadContent = CopyXML_Contents(InputXml5)
WScript.Sleep 2000

IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").select
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").innertext = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").ContentEditable = False
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").disabled = True


ScreenshotExcel_NonSAP("XML Copied")
On error resume next
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text urBtnCntTxt" Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'Msgbox "Post click"
'***********
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text " Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'***********
On error goto 0

'Msgbox "5 done"


'XML 6
WScript.Sleep 2000
PayloadContent = CopyXML_Contents(InputXml6)
WScript.Sleep 2000
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").select
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").innertext = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").ContentEditable = False
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").disabled = True
ScreenshotExcel_NonSAP("XML Copied")
On error resume next
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text urBtnCntTxt" Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'Msgbox "Post click"
'***********
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text " Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'***********
On error goto 0

'Msgbox "6 done"


'XML 7
WScript.Sleep 2000
PayloadContent = CopyXML_Contents(InputXml7)
WScript.Sleep 2000
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").select
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").innertext = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").ContentEditable = False
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").disabled = True
ScreenshotExcel_NonSAP("XML Copied")
On error resume next
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text urBtnCntTxt" Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'Msgbox "Post click"
'***********
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text " Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'***********
On error goto 0

'Msgbox "7 done"


'XML 8
WScript.Sleep 2000
PayloadContent = CopyXML_Contents(InputXml8)
WScript.Sleep 2000
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").select
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").innertext = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").ContentEditable = False
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").disabled = True
ScreenshotExcel_NonSAP("XML Copied")
On error resume next
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text urBtnCntTxt" Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'Msgbox "Post click"
'***********
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text " Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'***********
On error goto 0

'Msgbox "8 done"

'Msgbox "Post click"
WScript.Sleep 2000
'Status = IE.Document.getElementByID("CEPJ.IDPView.MessageArea-txt").innertext
ScreenshotExcel_NonSAP("Message Posted")
'Msgbox Status



On Error Resume Next
For Each t in IE.Document.getElementsByTagName("A")

If t.classname = "lsControl--valign lsLink urLnkReportGl20 urTxtStd lsTextNoWrapping lsControl--noWrapping" Or t.classname = "lsControl--valign lsLink urLnkReportGl20 urTxtStd lsTextNoWrapping" Then
		
If t.innertext = "Log Off" Then
t.click
End If
End If

Next
On Error Goto 0
WScript.Sleep 3000
IE.quit
IE.quit

Set oExec = WshShell.Exec("taskkill /fi ""imagename eq iexplore.exe""")
WScript.Sleep 3000
Set oExec = nothing
End Function



Function PO_Post_I269_OrderInt(SenderInterface,InputXml1,InputXml2,InputXml3,InputXml4,InputXml5,InputXml6)

'Dim WshShell1, oExec
Set WshShell1 = CreateObject("WScript.Shell")




Set oExec = WshShell1.Exec("taskkill /fi ""imagename eq iexplore.exe""")
WScript.Sleep 5000
Set oExec = nothing
'Dim IE
Set IE = CreateObject("InternetExplorer.Application")


IE.Visible = 1

'Set objWord22 = CreateObject("Word.Application")
'Set colTasks = objWord22.Tasks
'For Each objTask22 in colTasks
	'If Instr(objTask22.Name, "Internet Explorer") Then
	'XLSrunning = 3
	'objTask22.Activate
	'objTask22.WindowState =&H00000001' wdWindowStateMaximize
	'Exit For 'Exit this loop after the first Excel Application found!
	'End if
	'Next
	'objWord22.quit
	'Set objWord22 = nothing	

ExecutionRegion = ObjXMLData.Item("Environment")
	Select Case Ucase(Trim(ExecutionRegion))
	
	Case "CATEA"
		IE.Navigate "http://hlxc0bf027.unix.marksandspencercate.com:54400/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true#" 'FQJ			
        Case "CATEL"
		IE.Navigate "http://hlxc0bf029.unix.marksandspencercate.com:54400/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true" 'FQL
	End Select
'IE.navigate "http://hlxc0bf033.unix.marksandspencercate.com:8147/webdynpro/dispatcher/sap.com/tc~lm~itsam~co~ui~xi~ovcustom~wd/MonitoringHomeApp" 'catec  
'IE.navigate "http://clxc8bfqr1.unix.marksandspencercate.com:8147/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true" 'INTB
'IE.Navigate "http://hlxd0bf032.unix.marksandspencerdev.com:54400/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true" 'FCQ
'IE.Navigate "http://hlxc0bf030.unix.marksandspencercate.com:8147/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true" 'FQL
'IE.Navigate "http://hlxc0bf029.unix.marksandspencercate.com:54400/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true" 'FQL
'IE.Navigate "http://hlxc0bf027.unix.marksandspencercate.com:54400/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true#" 'FQJ


'Click Configuration and Monitoring Home link
'wait(2)
'For Each l In IE.Document.getElementsByTagName("A")  
'If l.classname = "urLnk" Then 
'If l.innertext = "Configuration and Monitoring Home" Then 
'l.click
'end if           
'End If 
'Next 
'
''Point to second Tab - Login page

For Each wnd In CreateObject("Shell.Application").Windows
If InStr(1, wnd.FullName, "iexplore.exe", vbTextCompare) > 0 Then
Set IE = wnd
End If
Next
'wait(3)
WScript.Sleep 3000
'Enter Credentials
Msgbox "1"
ExecutionRegion = ObjXMLData.Item("Environment")
	Select Case Ucase(Trim(ExecutionRegion))
	
	Case "CATEA"
		IE.Document.getElementByID("logonuidfield").value = "P9134904"
		IE.Document.getElementByID("logonpassfield").value = "Sapfqj123"
        Case "CATEL"
		IE.Document.getElementByID("logonuidfield").value = "P9134904"
		IE.Document.getElementByID("logonpassfield").value = "Sapfql123"
	End Select
'IE.Document.getElementByID("logonuidfield").value = "P9134904"
'IE.Document.getElementByID("logonpassfield").value = "Sapfqj123"



IE.Document.Forms(0).Submit()



'wait(2)
WScript.Sleep 4000



For Each wnd In CreateObject("Shell.Application").Windows
If InStr(1, wnd.FullName, "iexplore.exe", vbTextCompare) > 0 Then
Set IE = wnd
End If
Next
'wait(3)
WScript.Sleep 4000



'Click SOA tab
'IE.FullScreen = True
On error resume next
IE.Document.getElementByID("CEPJ.IDPView.HorizontalContextualPanel1.2-title").click
IE.Document.getElementByID("CEPJ.IDPView.HorizontalContextualPanel1.2-2").click
'IE.Document.getElementByID("CEPJ.IDPView.HorizontalContextualPanel1.2-title").click
On error goto 0
'IE.Document.getElementsByTagName("A")
'Click Send Test message link
'wait(2)
On error resume next
WScript.Sleep 3000
For Each l In IE.Document.getElementsByTagName("A")  
If l.classname = "lsControl--valign lsLink urLnkFunction urTxtStd lsTextNoWrapping" Then
If l.innertext = "Send Test Message" Then 
l.click
IEnewpage1 = l.href
End if           
End If 
Next
'*********
For Each l In IE.Document.getElementsByTagName("A")  
If l.classname = "lsControl--valign lsLink urLnkFunction urTxtStd lsTextNoWrapping lsControl--noWrapping" Then
If l.innertext = "Send Test Message" Then 
l.click
IEnewpage1 = l.href
End if           
End If 
Next
'*********
On error goto 0


'Point to 3rd Tab - Send Test message
For Each wnd In CreateObject("Shell.Application").Windows
If InStr(1, wnd.FullName, "iexplore.exe", vbTextCompare) > 0 Then
Set IE = wnd
End If
Next

'Set WshShell = CreateObject("WScript.Shell")
'WshShell.AppActivate IEs




'wait(2)
WScript.Sleep 3000
PayloadContent = CopyXML_Contents(InputXml1)
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").select
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").innertext = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").ContentEditable = False
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").disabled = True
ScreenshotExcel_NonSAP("XML Copied")
'Msgbox "Copy to clipboard"
WScript.Sleep 3000
'Click Search button
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.Button-img").click

'wait(2)
WScript.Sleep 2000
'IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
'IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").value = PayloadContent


'Search for the interface
Set aHTML = IE.document
set iframes = aHTML.getElementById("URLSPW-0")
WScript.Sleep 2000
Set obabc=iframes.contentdocument
'obabc.getElementById("CEPJICNK.PrefillFieldsView.PreFillFeildsTable:2147483639").value="I101_PickPGI"
obabc.getElementById("CEPJICNK.PrefillFieldsView.PreFillFeildsTable:2147483639").value=SenderInterface
WScript.Sleep 3000
obabc.getElementById("CEPJICNK.PrefillFieldsView.PreFillFeildsTable:2147483639").focus
'wait(2)
WScript.Sleep 3000
Set WshShell = CreateObject("WScript.Shell")
'WshShell.AppActivate IEs
WshShell.SendKeys "{ENTER}"
obabc.getElementById("CEPJICNK.PrefillFieldsView.PreFillFeildsTable:2147483639").focus
'wait(2)
WScript.Sleep 1000
WshShell.SendKeys "{ENTER}"
'Msgbox "Check"

WScript.Sleep 3000
obabc.getElementById("CEPJICNK.PrefillFieldsView.SITextView.0").focus
WshShell.SendKeys "{ENTER}"

WshShell.SendKeys "{ENTER}"
'Msgbox "done"
WScript.Sleep 2000
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.QOSDropDownByKey").click
WshShell.SendKeys "{DOWN}"
WshShell.SendKeys "{DOWN}"
WshShell.SendKeys "{ENTER}"
WScript.Sleep 2000

IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").select
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").innertext = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").ContentEditable = False
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").disabled = True

'ScreenshotExcel_NonSAP("Interface Details enetered")
IE.Document.getElementByID("CEPJ.IDPView.InputField").focus
WScript.Sleep 2000

On error resume next
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text urBtnCntTxt" Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'Msgbox "Post click"
'***********
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text " Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'***********
On error goto 0

'Msgbox "1 done"
'XML 2
WScript.Sleep 2000
PayloadContent = CopyXML_Contents(InputXml2)
WScript.Sleep 2000
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").select
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").innertext = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").ContentEditable = False
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").disabled = True
ScreenshotExcel_NonSAP("XML Copied")
On error resume next
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text urBtnCntTxt" Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'Msgbox "Post click"
'***********
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text " Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'***********
On error goto 0

'Msgbox "2 done"


'XML 3
WScript.Sleep 2000
PayloadContent = CopyXML_Contents(InputXml3)
WScript.Sleep 2000
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").select
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").innertext = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").ContentEditable = False
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").disabled = True
ScreenshotExcel_NonSAP("XML Copied")
On error resume next
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text urBtnCntTxt" Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'Msgbox "Post click"
'***********
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text " Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'***********
On error goto 0

'Msgbox "3 done"


'XML 4
WScript.Sleep 2000
PayloadContent = CopyXML_Contents(InputXml4)
WScript.Sleep 2000
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").select
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").innertext = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").ContentEditable = False
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").disabled = True
ScreenshotExcel_NonSAP("XML Copied")
On error resume next
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text urBtnCntTxt" Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'Msgbox "Post click"
'***********
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text " Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'***********
On error goto 0

'Msgbox "4 done"


'XML 5
WScript.Sleep 2000
PayloadContent = CopyXML_Contents(InputXml5)
WScript.Sleep 2000

IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").select
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").innertext = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").ContentEditable = False
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").disabled = True


ScreenshotExcel_NonSAP("XML Copied")
On error resume next
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text urBtnCntTxt" Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'Msgbox "Post click"
'***********
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text " Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'***********
On error goto 0

'Msgbox "5 done"


'XML 6
WScript.Sleep 2000
PayloadContent = CopyXML_Contents(InputXml6)
WScript.Sleep 2000
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").select
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").innertext = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").ContentEditable = False
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").disabled = True
ScreenshotExcel_NonSAP("XML Copied")
On error resume next
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text urBtnCntTxt" Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'Msgbox "Post click"
'***********
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text " Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'***********
On error goto 0

'Msgbox "6 done"

'Msgbox "Post click"
WScript.Sleep 2000
'Status = IE.Document.getElementByID("CEPJ.IDPView.MessageArea-txt").innertext
ScreenshotExcel_NonSAP("Message Posted")
'Msgbox Status


On Error Resume Next
For Each t in IE.Document.getElementsByTagName("A")

If t.classname = "lsControl--valign lsLink urLnkReportGl20 urTxtStd lsTextNoWrapping lsControl--noWrapping" Or t.classname = "lsControl--valign lsLink urLnkReportGl20 urTxtStd lsTextNoWrapping" Then
		
If t.innertext = "Log Off" Then
t.click
End If
End If

Next
On Error Goto 0
WScript.Sleep 3000
IE.quit
IE.quit

Set oExec = WshShell.Exec("taskkill /fi ""imagename eq iexplore.exe""")
WScript.Sleep 3000
Set oExec = nothing
End Function




Function PO_Post_I269_OrderIntFranc(SenderInterface,InputXml1,InputXml2,InputXml3,InputXml4,InputXml5)

'Dim WshShell1, oExec
Set WshShell1 = CreateObject("WScript.Shell")




Set oExec = WshShell1.Exec("taskkill /fi ""imagename eq iexplore.exe""")
WScript.Sleep 5000
Set oExec = nothing
'Dim IE
Set IE = CreateObject("InternetExplorer.Application")


IE.Visible = 1

Set objWord22 = CreateObject("Word.Application")
Set colTasks = objWord22.Tasks
For Each objTask22 in colTasks
	If Instr(objTask22.Name, "Internet Explorer") Then
	XLSrunning = 3
	objTask22.Activate
	objTask22.WindowState =&H00000001' wdWindowStateMaximize
	Exit For 'Exit this loop after the first Excel Application found!
	End if
	Next
	objWord22.quit
	Set objWord22 = nothing	

ExecutionRegion = ObjXMLData.Item("Environment")
	Select Case Ucase(Trim(ExecutionRegion))
	
	Case "CATEA"
		IE.Navigate "http://hlxc0bf027.unix.marksandspencercate.com:54400/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true#" 'FQJ			
        Case "CATEL"
		IE.Navigate "http://hlxc0bf029.unix.marksandspencercate.com:54400/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true" 'FQL
	End Select
'IE.navigate "http://hlxc0bf033.unix.marksandspencercate.com:8147/webdynpro/dispatcher/sap.com/tc~lm~itsam~co~ui~xi~ovcustom~wd/MonitoringHomeApp" 'catec  
'IE.navigate "http://clxc8bfqr1.unix.marksandspencercate.com:8147/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true" 'INTB
'IE.Navigate "http://hlxd0bf032.unix.marksandspencerdev.com:54400/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true" 'FCQ
'IE.Navigate "http://hlxc0bf030.unix.marksandspencercate.com:8147/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true" 'FQL
'IE.Navigate "http://hlxc0bf029.unix.marksandspencercate.com:54400/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true" 'FQL
'IE.Navigate "http://hlxc0bf027.unix.marksandspencercate.com:54400/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true#" 'FQJ


'Click Configuration and Monitoring Home link
'wait(2)
'For Each l In IE.Document.getElementsByTagName("A")  
'If l.classname = "urLnk" Then 
'If l.innertext = "Configuration and Monitoring Home" Then 
'l.click
'end if           
'End If 
'Next 
'
''Point to second Tab - Login page

For Each wnd In CreateObject("Shell.Application").Windows
If InStr(1, wnd.FullName, "iexplore.exe", vbTextCompare) > 0 Then
Set IE = wnd
End If
Next
'wait(3)
WScript.Sleep 3000
'Enter Credentials
Msgbox "1"
ExecutionRegion = ObjXMLData.Item("Environment")
	Select Case Ucase(Trim(ExecutionRegion))
	
	Case "CATEA"
		IE.Document.getElementByID("logonuidfield").value = "P9134904"
		IE.Document.getElementByID("logonpassfield").value = "Sapfqj123"
        Case "CATEL"
		IE.Document.getElementByID("logonuidfield").value = "P9134904"
		IE.Document.getElementByID("logonpassfield").value = "Sapfql123"
	End Select
'IE.Document.getElementByID("logonuidfield").value = "P9134904"
'IE.Document.getElementByID("logonpassfield").value = "Sapfqj123"



IE.Document.Forms(0).Submit()



'wait(2)
WScript.Sleep 4000



For Each wnd In CreateObject("Shell.Application").Windows
If InStr(1, wnd.FullName, "iexplore.exe", vbTextCompare) > 0 Then
Set IE = wnd
End If
Next
'wait(3)
WScript.Sleep 4000



'Click SOA tab
'IE.FullScreen = True
On error resume next
IE.Document.getElementByID("CEPJ.IDPView.HorizontalContextualPanel1.2-title").click
IE.Document.getElementByID("CEPJ.IDPView.HorizontalContextualPanel1.2-2").click
'IE.Document.getElementByID("CEPJ.IDPView.HorizontalContextualPanel1.2-title").click
On error goto 0
'IE.Document.getElementsByTagName("A")
'Click Send Test message link
'wait(2)
On error resume next
WScript.Sleep 3000
For Each l In IE.Document.getElementsByTagName("A")  
If l.classname = "lsControl--valign lsLink urLnkFunction urTxtStd lsTextNoWrapping" Then
If l.innertext = "Send Test Message" Then 
l.click
IEnewpage1 = l.href
End if           
End If 
Next
'*********
For Each l In IE.Document.getElementsByTagName("A")  
If l.classname = "lsControl--valign lsLink urLnkFunction urTxtStd lsTextNoWrapping lsControl--noWrapping" Then
If l.innertext = "Send Test Message" Then 
l.click
IEnewpage1 = l.href
End if           
End If 
Next
'*********
On error goto 0


'Point to 3rd Tab - Send Test message
For Each wnd In CreateObject("Shell.Application").Windows
If InStr(1, wnd.FullName, "iexplore.exe", vbTextCompare) > 0 Then
Set IE = wnd
End If
Next

'Set WshShell = CreateObject("WScript.Shell")
'WshShell.AppActivate IEs


'wait(2)
WScript.Sleep 3000
PayloadContent = CopyXML_Contents(InputXml1)
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").select
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").innertext = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").ContentEditable = False
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").disabled = True
ScreenshotExcel_NonSAP("XML Copied")
'Msgbox "Copy to clipboard"
WScript.Sleep 3000
'Click Search button
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.Button-img").click

'wait(2)
WScript.Sleep 2000
'IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
'IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").value = PayloadContent


'Search for the interface
Set aHTML = IE.document
set iframes = aHTML.getElementById("URLSPW-0")
WScript.Sleep 2000
Set obabc=iframes.contentdocument
'obabc.getElementById("CEPJICNK.PrefillFieldsView.PreFillFeildsTable:2147483639").value="I101_PickPGI"
obabc.getElementById("CEPJICNK.PrefillFieldsView.PreFillFeildsTable:2147483639").value=SenderInterface
WScript.Sleep 3000
obabc.getElementById("CEPJICNK.PrefillFieldsView.PreFillFeildsTable:2147483639").focus
'wait(2)
WScript.Sleep 3000
Set WshShell = CreateObject("WScript.Shell")
'WshShell.AppActivate IEs
WshShell.SendKeys "{ENTER}"
obabc.getElementById("CEPJICNK.PrefillFieldsView.PreFillFeildsTable:2147483639").focus
'wait(2)
WScript.Sleep 1000
WshShell.SendKeys "{ENTER}"
'Msgbox "Check"

WScript.Sleep 3000
obabc.getElementById("CEPJICNK.PrefillFieldsView.SITextView.0").focus
WshShell.SendKeys "{ENTER}"

WshShell.SendKeys "{ENTER}"
'Msgbox "done"
WScript.Sleep 2000
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.QOSDropDownByKey").click
WshShell.SendKeys "{DOWN}"
WshShell.SendKeys "{DOWN}"
WshShell.SendKeys "{ENTER}"
WScript.Sleep 2000



'Point to 3rd Tab - Send Test message
For Each wnd In CreateObject("Shell.Application").Windows
If InStr(1, wnd.FullName, "iexplore.exe", vbTextCompare) > 0 Then
Set IE = wnd
End If
Next

'Set WshShell = CreateObject("WScript.Shell")
'WshShell.AppActivate IEs


'wait(2)
WScript.Sleep 3000
PayloadContent = CopyXML_Contents(InputXml1)
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").select
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").innertext = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").ContentEditable = False
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").disabled = True
'ScreenshotExcel_NonSAP("XML Copied")
'Msgbox "Copy to clipboard"
WScript.Sleep 3000
'Click Search button
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.Button-img").click

'wait(2)
WScript.Sleep 2000
'IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
'IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").value = PayloadContent


'Search for the interface
Set aHTML = IE.document
set iframes = aHTML.getElementById("URLSPW-0")
WScript.Sleep 2000
Set obabc=iframes.contentdocument
'obabc.getElementById("CEPJICNK.PrefillFieldsView.PreFillFeildsTable:2147483639").value="I101_PickPGI"
obabc.getElementById("CEPJICNK.PrefillFieldsView.PreFillFeildsTable:2147483639").value=SenderInterface
WScript.Sleep 3000
obabc.getElementById("CEPJICNK.PrefillFieldsView.PreFillFeildsTable:2147483639").focus
'wait(2)
WScript.Sleep 3000
Set WshShell = CreateObject("WScript.Shell")
'WshShell.AppActivate IEs
WshShell.SendKeys "{ENTER}"
obabc.getElementById("CEPJICNK.PrefillFieldsView.PreFillFeildsTable:2147483639").focus
'wait(2)
WScript.Sleep 1000
WshShell.SendKeys "{ENTER}"
'Msgbox "Check"

WScript.Sleep 3000
obabc.getElementById("CEPJICNK.PrefillFieldsView.SITextView.0").focus
WshShell.SendKeys "{ENTER}"

WshShell.SendKeys "{ENTER}"
'Msgbox "done"
WScript.Sleep 2000
'IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.QOSDropDownByKey").click
'WshShell.SendKeys "{DOWN}"
'WshShell.SendKeys "{DOWN}"
'WshShell.SendKeys "{ENTER}"
'WScript.Sleep 2000

IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").select
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").innertext = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").ContentEditable = False
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").disabled = True

'ScreenshotExcel_NonSAP("Interface Details enetered")
IE.Document.getElementByID("CEPJ.IDPView.InputField").focus
WScript.Sleep 2000

On error resume next
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text urBtnCntTxt" Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'Msgbox "Post click"
'***********
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text " Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'***********
On error goto 0

'Msgbox "1 done"
'XML 2
WScript.Sleep 2000
PayloadContent = CopyXML_Contents(InputXml2)
WScript.Sleep 2000
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").select
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").innertext = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").ContentEditable = False
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").disabled = True
ScreenshotExcel_NonSAP("XML Copied")
On error resume next
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text urBtnCntTxt" Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'Msgbox "Post click"
'***********
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text " Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'***********
On error goto 0

Msgbox "2 done"


'XML 3
WScript.Sleep 2000
PayloadContent = CopyXML_Contents(InputXml3)
WScript.Sleep 2000
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").select
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").innertext = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").ContentEditable = False
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").disabled = True
ScreenshotExcel_NonSAP("XML Copied")
On error resume next
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text urBtnCntTxt" Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'Msgbox "Post click"
'***********
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text " Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'***********
On error goto 0

'Msgbox "3 done"


'XML 4
WScript.Sleep 2000
PayloadContent = CopyXML_Contents(InputXml4)
WScript.Sleep 2000
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").select
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").innertext = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").ContentEditable = False
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").disabled = True
ScreenshotExcel_NonSAP("XML Copied")
On error resume next
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text urBtnCntTxt" Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'Msgbox "Post click"
'***********
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text " Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'***********
On error goto 0

'Msgbox "4 done"


'XML 5
WScript.Sleep 2000
PayloadContent = CopyXML_Contents(InputXml5)
WScript.Sleep 2000

IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").select
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").innertext = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").ContentEditable = False
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").disabled = True


ScreenshotExcel_NonSAP("XML Copied")
On error resume next
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text urBtnCntTxt" Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'Msgbox "Post click"
'***********
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text " Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'***********
On error goto 0

'Msgbox "5 done"


'Msgbox "Post click"
WScript.Sleep 2000
'Status = IE.Document.getElementByID("CEPJ.IDPView.MessageArea-txt").innertext
ScreenshotExcel_NonSAP("Message Posted")
'Msgbox Status


On Error Resume Next
For Each t in IE.Document.getElementsByTagName("A")

If t.classname = "lsControl--valign lsLink urLnkReportGl20 urTxtStd lsTextNoWrapping lsControl--noWrapping" Or t.classname = "lsControl--valign lsLink urLnkReportGl20 urTxtStd lsTextNoWrapping" Then
		
If t.innertext = "Log Off" Then
t.click
End If
End If

Next
On Error Goto 0
WScript.Sleep 3000
IE.quit
IE.quit

Set oExec = WshShell.Exec("taskkill /fi ""imagename eq iexplore.exe""")
WScript.Sleep 3000
Set oExec = nothing
End Function



Function PO_Post_I269_Franchise(SenderInterface,InputXml1,InputXml2,InputXml3,InputXml4,InputXml5,InputXml6)

'Dim WshShell1, oExec
Set WshShell1 = CreateObject("WScript.Shell")




Set oExec = WshShell1.Exec("taskkill /fi ""imagename eq iexplore.exe""")
WScript.Sleep 5000
Set oExec = nothing
'Dim IE
Set IE = CreateObject("InternetExplorer.Application")


IE.Visible = 1
	Set objWord22 = CreateObject("Word.Application")
Set colTasks = objWord22.Tasks
	For Each objTask22 in colTasks
	If Instr(objTask22.Name, "Internet Explorer") Then
	XLSrunning = 3
	objTask22.Activate
	objTask22.WindowState =&H00000001' wdWindowStateMaximize
	Exit For 'Exit this loop after the first Excel Application found!
	End if
	Next
	objWord22.quit
	Set objWord22 = nothing	


ExecutionRegion = ObjXMLData.Item("Environment")
	Select Case Ucase(Trim(ExecutionRegion))
	
	Case "CATEA"
		IE.Navigate "http://hlxc0bf027.unix.marksandspencercate.com:54400/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true#" 'FQJ			
        Case "CATEL"
		IE.Navigate "http://hlxc0bf029.unix.marksandspencercate.com:54400/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true" 'FQL
	End Select
'IE.navigate "http://hlxc0bf033.unix.marksandspencercate.com:8147/webdynpro/dispatcher/sap.com/tc~lm~itsam~co~ui~xi~ovcustom~wd/MonitoringHomeApp" 'catec  
'IE.navigate "http://clxc8bfqr1.unix.marksandspencercate.com:8147/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true" 'INTB
'IE.Navigate "http://hlxd0bf032.unix.marksandspencerdev.com:54400/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true" 'FCQ
'IE.Navigate "http://hlxc0bf030.unix.marksandspencercate.com:8147/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true" 'FQL
'IE.Navigate "http://hlxc0bf029.unix.marksandspencercate.com:54400/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true" 'FQL
'IE.Navigate "http://hlxc0bf027.unix.marksandspencercate.com:54400/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true#" 'FQJ


'Click Configuration and Monitoring Home link
'wait(2)
'For Each l In IE.Document.getElementsByTagName("A")  
'If l.classname = "urLnk" Then 
'If l.innertext = "Configuration and Monitoring Home" Then 
'l.click
'end if           
'End If 
'Next 
'
''Point to second Tab - Login page

For Each wnd In CreateObject("Shell.Application").Windows
If InStr(1, wnd.FullName, "iexplore.exe", vbTextCompare) > 0 Then
Set IE = wnd
End If
Next
'wait(3)
WScript.Sleep 3000
'Enter Credentials
Msgbox "1"
ExecutionRegion = ObjXMLData.Item("Environment")
	Select Case Ucase(Trim(ExecutionRegion))
	
	Case "CATEA"
		IE.Document.getElementByID("logonuidfield").value = "P9134904"
		IE.Document.getElementByID("logonpassfield").value = "Sapfqj123"
        Case "CATEL"
		IE.Document.getElementByID("logonuidfield").value = "P9134904"
		IE.Document.getElementByID("logonpassfield").value = "Sapfql123"
	End Select
'IE.Document.getElementByID("logonuidfield").value = "P9134904"
'IE.Document.getElementByID("logonpassfield").value = "Sapfqj123"



IE.Document.Forms(0).Submit()



'wait(2)
WScript.Sleep 4000



For Each wnd In CreateObject("Shell.Application").Windows
If InStr(1, wnd.FullName, "iexplore.exe", vbTextCompare) > 0 Then
Set IE = wnd
End If
Next
'wait(3)
WScript.Sleep 4000



'Click SOA tab
'IE.FullScreen = True
On error resume next
IE.Document.getElementByID("CEPJ.IDPView.HorizontalContextualPanel1.2-title").click
IE.Document.getElementByID("CEPJ.IDPView.HorizontalContextualPanel1.2-2").click
'IE.Document.getElementByID("CEPJ.IDPView.HorizontalContextualPanel1.2-title").click
On error goto 0
'IE.Document.getElementsByTagName("A")
'Click Send Test message link
'wait(2)
On error resume next
WScript.Sleep 3000
For Each l In IE.Document.getElementsByTagName("A")  
If l.classname = "lsControl--valign lsLink urLnkFunction urTxtStd lsTextNoWrapping" Then
If l.innertext = "Send Test Message" Then 
l.click
IEnewpage1 = l.href
End if           
End If 
Next
'*********
For Each l In IE.Document.getElementsByTagName("A")  
If l.classname = "lsControl--valign lsLink urLnkFunction urTxtStd lsTextNoWrapping lsControl--noWrapping" Then
If l.innertext = "Send Test Message" Then 
l.click
IEnewpage1 = l.href
End if           
End If 
Next
'*********
On error goto 0


'Point to 3rd Tab - Send Test message
For Each wnd In CreateObject("Shell.Application").Windows
If InStr(1, wnd.FullName, "iexplore.exe", vbTextCompare) > 0 Then
Set IE = wnd
End If
Next

'Set WshShell = CreateObject("WScript.Shell")
'WshShell.AppActivate IEs


'wait(2)
WScript.Sleep 3000
PayloadContent = CopyXML_Contents(InputXml1)
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").select
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").innertext = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").ContentEditable = False
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").disabled = True
ScreenshotExcel_NonSAP("XML Copied")
'Msgbox "Copy to clipboard"
WScript.Sleep 3000
'Click Search button
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.Button-img").click

'wait(2)
WScript.Sleep 2000
'IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
'IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").value = PayloadContent


'Search for the interface
Set aHTML = IE.document
set iframes = aHTML.getElementById("URLSPW-0")
WScript.Sleep 2000
Set obabc=iframes.contentdocument
'obabc.getElementById("CEPJICNK.PrefillFieldsView.PreFillFeildsTable:2147483639").value="I101_PickPGI"
obabc.getElementById("CEPJICNK.PrefillFieldsView.PreFillFeildsTable:2147483639").value=SenderInterface
WScript.Sleep 3000
obabc.getElementById("CEPJICNK.PrefillFieldsView.PreFillFeildsTable:2147483639").focus
'wait(2)
WScript.Sleep 3000
Set WshShell = CreateObject("WScript.Shell")
'WshShell.AppActivate IEs
WshShell.SendKeys "{ENTER}"
obabc.getElementById("CEPJICNK.PrefillFieldsView.PreFillFeildsTable:2147483639").focus
'wait(2)
WScript.Sleep 1000
WshShell.SendKeys "{ENTER}"
'Msgbox "Check"

WScript.Sleep 3000
obabc.getElementById("CEPJICNK.PrefillFieldsView.SITextView.0").focus
WshShell.SendKeys "{ENTER}"

WshShell.SendKeys "{ENTER}"
'Msgbox "done"
WScript.Sleep 2000
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.QOSDropDownByKey").click
WshShell.SendKeys "{DOWN}"
WshShell.SendKeys "{DOWN}"
WshShell.SendKeys "{ENTER}"
WScript.Sleep 2000



'Point to 3rd Tab - Send Test message
For Each wnd In CreateObject("Shell.Application").Windows
If InStr(1, wnd.FullName, "iexplore.exe", vbTextCompare) > 0 Then
Set IE = wnd
End If
Next

'Set WshShell = CreateObject("WScript.Shell")
'WshShell.AppActivate IEs


'wait(2)
WScript.Sleep 3000
PayloadContent = CopyXML_Contents(InputXml1)
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").select
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").innertext = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").ContentEditable = False
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").disabled = True
'ScreenshotExcel_NonSAP("XML Copied")
'Msgbox "Copy to clipboard"
WScript.Sleep 3000
'Click Search button
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.Button-img").click

'wait(2)
WScript.Sleep 2000
'IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
'IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").value = PayloadContent


'Search for the interface
Set aHTML = IE.document
set iframes = aHTML.getElementById("URLSPW-0")
WScript.Sleep 2000
Set obabc=iframes.contentdocument
'obabc.getElementById("CEPJICNK.PrefillFieldsView.PreFillFeildsTable:2147483639").value="I101_PickPGI"
obabc.getElementById("CEPJICNK.PrefillFieldsView.PreFillFeildsTable:2147483639").value=SenderInterface
WScript.Sleep 3000
obabc.getElementById("CEPJICNK.PrefillFieldsView.PreFillFeildsTable:2147483639").focus
'wait(2)
WScript.Sleep 3000
Set WshShell = CreateObject("WScript.Shell")
'WshShell.AppActivate IEs
WshShell.SendKeys "{ENTER}"
obabc.getElementById("CEPJICNK.PrefillFieldsView.PreFillFeildsTable:2147483639").focus
'wait(2)
WScript.Sleep 1000
WshShell.SendKeys "{ENTER}"
'Msgbox "Check"

WScript.Sleep 3000
obabc.getElementById("CEPJICNK.PrefillFieldsView.SITextView.0").focus
WshShell.SendKeys "{ENTER}"

WshShell.SendKeys "{ENTER}"
'Msgbox "done"
WScript.Sleep 2000
'IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.QOSDropDownByKey").click
'WshShell.SendKeys "{DOWN}"
'WshShell.SendKeys "{DOWN}"
'WshShell.SendKeys "{ENTER}"
'WScript.Sleep 2000

IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").select
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").innertext = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").ContentEditable = False
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").disabled = True

'ScreenshotExcel_NonSAP("Interface Details enetered")
IE.Document.getElementByID("CEPJ.IDPView.InputField").focus
WScript.Sleep 2000

On error resume next
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text urBtnCntTxt" Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'Msgbox "Post click"
'***********
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text " Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'***********
On error goto 0

'Msgbox "1 done"
'XML 2
WScript.Sleep 2000
PayloadContent = CopyXML_Contents(InputXml2)
WScript.Sleep 2000
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").select
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").innertext = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").ContentEditable = False
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").disabled = True
ScreenshotExcel_NonSAP("XML Copied")
On error resume next
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text urBtnCntTxt" Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'Msgbox "Post click"
'***********
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text " Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'***********
On error goto 0

'Msgbox "2 done"


'XML 3
WScript.Sleep 2000
PayloadContent = CopyXML_Contents(InputXml3)
WScript.Sleep 2000
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").select
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").innertext = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").ContentEditable = False
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").disabled = True
ScreenshotExcel_NonSAP("XML Copied")
On error resume next
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text urBtnCntTxt" Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'Msgbox "Post click"
'***********
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text " Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'***********
On error goto 0

'Msgbox "3 done"


'XML 4
WScript.Sleep 2000
PayloadContent = CopyXML_Contents(InputXml4)
WScript.Sleep 2000
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").select
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").innertext = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").ContentEditable = False
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").disabled = True
ScreenshotExcel_NonSAP("XML Copied")
On error resume next
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text urBtnCntTxt" Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'Msgbox "Post click"
'***********
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text " Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'***********
On error goto 0

'Msgbox "4 done"


'XML 5
WScript.Sleep 2000
PayloadContent = CopyXML_Contents(InputXml5)
WScript.Sleep 2000

IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").select
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").innertext = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").ContentEditable = False
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").disabled = True


ScreenshotExcel_NonSAP("XML Copied")
On error resume next
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text urBtnCntTxt" Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'Msgbox "Post click"
'***********
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text " Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'***********
On error goto 0

'Msgbox "5 done"


'XML 6
WScript.Sleep 2000
PayloadContent = CopyXML_Contents(InputXml6)
WScript.Sleep 2000
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").select
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").innertext = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").ContentEditable = False
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").disabled = True
ScreenshotExcel_NonSAP("XML Copied")
On error resume next
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text urBtnCntTxt" Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'Msgbox "Post click"
'***********
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text " Then
If m.innertext = "Send" Then
m.click
End If
End If

Next
'***********
On error goto 0

'Msgbox "6 done"

'Msgbox "Post click"
WScript.Sleep 2000
'Status = IE.Document.getElementByID("CEPJ.IDPView.MessageArea-txt").innertext
ScreenshotExcel_NonSAP("Message Posted")
'Msgbox Status


On Error Resume Next
For Each t in IE.Document.getElementsByTagName("A")

If t.classname = "lsControl--valign lsLink urLnkReportGl20 urTxtStd lsTextNoWrapping lsControl--noWrapping" Or t.classname = "lsControl--valign lsLink urLnkReportGl20 urTxtStd lsTextNoWrapping" Then
		
If t.innertext = "Log Off" Then
t.click
End If
End If

Next
On Error Goto 0
WScript.Sleep 3000
IE.quit
IE.quit

Set oExec = WshShell.Exec("taskkill /fi ""imagename eq iexplore.exe""")
WScript.Sleep 3000
Set oExec = nothing
End Function



Function PO_Post_I269_Franchise_VB(SenderInterface,InputXml1,InputXml2,InputXml3,InputXml4,InputXml5,InputXml6)

Dim WshShell1, oExec
Set WshShell1 = CreateObject("WScript.Shell")



Set oExec = WshShell1.Exec("taskkill /fi ""imagename eq iexplore.exe""")
WScript.Sleep 3000
Set oExec = nothing
'Dim IE
Set IE = CreateObject("InternetExplorer.Application")

IE.Visible = 1
Set objWord22 = CreateObject("Word.Application")
Set colTasks = objWord22.Tasks
For Each objTask22 in colTasks
	If Instr(objTask22.Name, "Internet Explorer") Then
	XLSrunning = 3
	objTask22.Activate
	objTask22.WindowState =&H00000001' wdWindowStateMaximize
	Exit For 'Exit this loop after the first Excel Application found!
	End if
	Next
	objWord22.quit
	Set objWord22 = nothing	


'IE.navigate "http://hlxc0bf033.unix.marksandspencercate.com:8147/webdynpro/dispatcher/sap.com/tc~lm~itsam~co~ui~xi~ovcustom~wd/MonitoringHomeApp" 'catec  
'IE.navigate "http://clxc8bfqr1.unix.marksandspencercate.com:8147/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true" 'INTB
'IE.Navigate "http://hlxd0bf032.unix.marksandspencerdev.com:54400/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true" 'FCQ
'IE.Navigate "http://hlxc0bf030.unix.marksandspencercate.com:8147/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true" 'FQL
IE.Navigate"http://hlxc0bf029.unix.marksandspencercate.com:54400/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true" 'FQL
'IE.Navigate "http://hlxc0bf027.unix.marksandspencercate.com:54400/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true#" 'FQJ


'Click Configuration and Monitoring Home link
'wait(2)
'For Each l In IE.Document.getElementsByTagName("A")  
'If l.classname = "urLnk" Then 
'If l.innertext = "Configuration and Monitoring Home" Then 
'l.click
'end if           
'End If 
'Next 
'
''Point to second Tab - Login page

For Each wnd In CreateObject("Shell.Application").Windows
If InStr(1, wnd.FullName, "iexplore.exe", vbTextCompare) > 0 Then
Set IE = wnd
End If
Next
'wait(3)
WScript.Sleep 3000
'Enter Credentials

IE.Document.getElementByID("logonuidfield").value = "P9124459"
IE.Document.getElementByID("logonpassfield").value = "fql12345"
IE.Document.Forms(0).Submit()

'wait(2)
WScript.Sleep 3000


For Each wnd In CreateObject("Shell.Application").Windows
If InStr(1, wnd.FullName, "iexplore.exe", vbTextCompare) > 0 Then
Set IE = wnd
End If
Next
'wait(3)
WScript.Sleep 3000
'Click SOA tab
IE.Document.getElementByID("CEPJ.IDPView.HorizontalContextualPanel1.2-2").click
'IE.Document.getElementsByTagName("A")
'Click Send Test message link
'wait(2)
WScript.Sleep 3000
For Each l In IE.Document.getElementsByTagName("A")  
If l.classname = "lsControl--valign lsLink urLnkFunction urTxtStd lsTextNoWrapping" Then 
If l.innertext = "Send Test Message" Then 
l.click
IEnewpage1 = l.href
End if           
End If 
Next

'Point to 3rd Tab - Send Test message
For Each wnd In CreateObject("Shell.Application").Windows
If InStr(1, wnd.FullName, "iexplore.exe", vbTextCompare) > 0 Then
Set IE = wnd
End If
Next

'Set WshShell = CreateObject("WScript.Shell")
'WshShell.AppActivate IEs


'wait(2)
WScript.Sleep 3000
PayloadContent = CopyXML_Contents(InputXml1)
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").select
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").innertext = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").ContentEditable = False
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").disabled = True
ScreenshotExcel_NonSAP("XML Copied")
'Msgbox "Copy to clipboard"
WScript.Sleep 3000
'Click Search button
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.Button-img").click

'wait(2)
WScript.Sleep 2000
'IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
'IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").value = PayloadContent


'Search for the interface
Set aHTML = IE.document
set iframes = aHTML.getElementById("URLSPW-0")
WScript.Sleep 2000
Set obabc=iframes.contentdocument
'obabc.getElementById("CEPJICNK.PrefillFieldsView.PreFillFeildsTable:2147483639").value="I101_PickPGI"
obabc.getElementById("CEPJICNK.PrefillFieldsView.PreFillFeildsTable:2147483639").value=SenderInterface
WScript.Sleep 3000
obabc.getElementById("CEPJICNK.PrefillFieldsView.PreFillFeildsTable:2147483639").focus
'wait(2)
WScript.Sleep 3000
Set WshShell = CreateObject("WScript.Shell")
'WshShell.AppActivate IEs
WshShell.SendKeys "{ENTER}"
obabc.getElementById("CEPJICNK.PrefillFieldsView.PreFillFeildsTable:2147483639").focus
'wait(2)
WScript.Sleep 1000
WshShell.SendKeys "{ENTER}"
'Msgbox "Check"

WScript.Sleep 3000
obabc.getElementById("CEPJICNK.PrefillFieldsView.SITextView.0").focus
WshShell.SendKeys "{ENTER}"

WshShell.SendKeys "{ENTER}"
'Msgbox "done"
WScript.Sleep 2000
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.QOSDropDownByKey").click
WshShell.SendKeys "{DOWN}"
WshShell.SendKeys "{DOWN}"
WshShell.SendKeys "{ENTER}"
WScript.Sleep 2000

IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").select
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").innertext = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").ContentEditable = False
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").disabled = True

ScreenshotExcel_NonSAP("Interface Details enetered")
IE.Document.getElementByID("CEPJ.IDPView.InputField").focus
WScript.Sleep 2000

'Msgbox "125"
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text urBtnCntTxt" Then

If m.innertext = "Send" Then

m.click

End If

End If

Next

'XML 2
PayloadContent = CopyXML_Contents(InputXml2)
WScript.Sleep 3000
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").select
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").innertext = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").ContentEditable = False
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").disabled = True
ScreenshotExcel_NonSAP("XML Copied")
For Each m in IE.Document.getElementsByTagName("span")
If m.classname = "lsButton__text urBtnCntTxt" Then
If m.innertext = "Send" Then
m.click
End If
End If
Next

'XML 3
PayloadContent = CopyXML_Contents(InputXml3)
WScript.Sleep 3000
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").select
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").innertext = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").ContentEditable = False
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").disabled = True
ScreenshotExcel_NonSAP("XML Copied")
For Each m in IE.Document.getElementsByTagName("span")
If m.classname = "lsButton__text urBtnCntTxt" Then
If m.innertext = "Send" Then
m.click
End If
End If
Next


'XML 4
PayloadContent = CopyXML_Contents(InputXml4)
WScript.Sleep 3000
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").select
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").innertext = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").ContentEditable = False
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").disabled = True
ScreenshotExcel_NonSAP("XML Copied")
For Each m in IE.Document.getElementsByTagName("span")
If m.classname = "lsButton__text urBtnCntTxt" Then
If m.innertext = "Send" Then
m.click
End If
End If
Next


'XML 5
PayloadContent = CopyXML_Contents(InputXml5)
WScript.Sleep 3000
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").select
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").innertext = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").ContentEditable = False
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").disabled = True


ScreenshotExcel_NonSAP("XML Copied")
For Each m in IE.Document.getElementsByTagName("span")
If m.classname = "lsButton__text urBtnCntTxt" Then
If m.innertext = "Send" Then
m.click
End If
End If
Next

'XML 6
PayloadContent = CopyXML_Contents(InputXml6)
WScript.Sleep 3000
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").select
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").innertext = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").ContentEditable = False
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").disabled = True
ScreenshotExcel_NonSAP("XML Copied")
For Each m in IE.Document.getElementsByTagName("span")
If m.classname = "lsButton__text urBtnCntTxt" Then
If m.innertext = "Send" Then
m.click
End If
End If
Next
msgbox "6"


'Msgbox "Post click"
WScript.Sleep 2000
'Status = IE.Document.getElementByID("CEPJ.IDPView.MessageArea-txt").innertext
ScreenshotExcel_NonSAP("Message Posted")
'Msgbox Status
IE.quit
IE.quit
End Function


Function PO_Post_I269_OrderInt_VB(SenderInterface,InputXml1,InputXml3,InputXml4,InputXml5,InputXml6,InputXml7)


Dim WshShell1, oExec
Set WshShell1 = CreateObject("WScript.Shell")



Set oExec = WshShell1.Exec("taskkill /fi ""imagename eq iexplore.exe""")
WScript.Sleep 3000
Set oExec = nothing
Dim IE
Set IE = CreateObject("InternetExplorer.Application")

IE.Visible = 1
'IE.navigate "http://hlxc0bf033.unix.marksandspencercate.com:8147/webdynpro/dispatcher/sap.com/tc~lm~itsam~co~ui~xi~ovcustom~wd/MonitoringHomeApp" 'catec  
'IE.navigate "http://clxc8bfqr1.unix.marksandspencercate.com:8147/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true" 'INTB
'IE.Navigate "http://hlxd0bf032.unix.marksandspencerdev.com:54400/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true" 'FCQ
'IE.Navigate "http://hlxc0bf030.unix.marksandspencercate.com:8147/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true" 'FQL
IE.Navigate"http://hlxc0bf029.unix.marksandspencercate.com:54400/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true" 'FQL
'IE.Navigate "http://hlxc0bf027.unix.marksandspencercate.com:54400/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true#" 'FQJ


'Click Configuration and Monitoring Home link
'wait(2)
'For Each l In IE.Document.getElementsByTagName("A")  
'If l.classname = "urLnk" Then 
'If l.innertext = "Configuration and Monitoring Home" Then 
'l.click
'end if           
'End If 
'Next 
'
''Point to second Tab - Login page

For Each wnd In CreateObject("Shell.Application").Windows
If InStr(1, wnd.FullName, "iexplore.exe", vbTextCompare) > 0 Then
Set IE = wnd
End If
Next
'wait(3)
WScript.Sleep 3000
'Enter Credentials

IE.Document.getElementByID("logonuidfield").value = "P9124459"
IE.Document.getElementByID("logonpassfield").value = "fql12345"
IE.Document.Forms(0).Submit()

'wait(2)
WScript.Sleep 3000


For Each wnd In CreateObject("Shell.Application").Windows
If InStr(1, wnd.FullName, "iexplore.exe", vbTextCompare) > 0 Then
Set IE = wnd
End If
Next
'wait(3)
WScript.Sleep 3000
'Click SOA tab
IE.Document.getElementByID("CEPJ.IDPView.HorizontalContextualPanel1.2-2").click
'IE.Document.getElementsByTagName("A")
'Click Send Test message link
'wait(2)
WScript.Sleep 3000
For Each l In IE.Document.getElementsByTagName("A")  
If l.classname = "lsControl--valign lsLink urLnkFunction urTxtStd lsTextNoWrapping" Then 
If l.innertext = "Send Test Message" Then 
l.click
IEnewpage1 = l.href
End if           
End If 
Next

'Point to 3rd Tab - Send Test message
For Each wnd In CreateObject("Shell.Application").Windows
If InStr(1, wnd.FullName, "iexplore.exe", vbTextCompare) > 0 Then
Set IE = wnd
End If
Next

'Set WshShell = CreateObject("WScript.Shell")
'WshShell.AppActivate IEs

msgbox "2"
'wait(2)
WScript.Sleep 3000
PayloadContent = CopyXML_Contents(InputXml1)
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").select
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").innertext = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").ContentEditable = False
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").disabled = True
ScreenshotExcel_NonSAP("XML Copied")
'Msgbox "Copy to clipboard"
WScript.Sleep 3000
'Click Search button
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.Button-img").click
msgbox "3"
'wait(2)
WScript.Sleep 2000
'IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
'IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").value = PayloadContent


'Search for the interface
Set aHTML = IE.document
set iframes = aHTML.getElementById("URLSPW-0")
WScript.Sleep 2000
Set obabc=iframes.contentdocument
'obabc.getElementById("CEPJICNK.PrefillFieldsView.PreFillFeildsTable:2147483639").value="I101_PickPGI"
obabc.getElementById("CEPJICNK.PrefillFieldsView.PreFillFeildsTable:2147483639").value=SenderInterface
WScript.Sleep 3000
obabc.getElementById("CEPJICNK.PrefillFieldsView.PreFillFeildsTable:2147483639").focus
'wait(2)
WScript.Sleep 3000
Set WshShell = CreateObject("WScript.Shell")
'WshShell.AppActivate IEs
WshShell.SendKeys "{ENTER}"
obabc.getElementById("CEPJICNK.PrefillFieldsView.PreFillFeildsTable:2147483639").focus
'wait(2)
WScript.Sleep 1000
WshShell.SendKeys "{ENTER}"
'Msgbox "Check"

WScript.Sleep 3000
obabc.getElementById("CEPJICNK.PrefillFieldsView.SITextView.0").focus
WshShell.SendKeys "{ENTER}"

WshShell.SendKeys "{ENTER}"
'Msgbox "done"
WScript.Sleep 2000
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.QOSDropDownByKey").click
WshShell.SendKeys "{DOWN}"
WshShell.SendKeys "{DOWN}"
WshShell.SendKeys "{ENTER}"
WScript.Sleep 2000

IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").select
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").innertext = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").ContentEditable = False
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").disabled = True

ScreenshotExcel_NonSAP("Interface Details enetered")
IE.Document.getElementByID("CEPJ.IDPView.InputField").focus
WScript.Sleep 2000

'Msgbox "125"
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text urBtnCntTxt" Then

If m.innertext = "Send" Then

m.click

End If

End If

Next


'XML 3
PayloadContent = CopyXML_Contents(InputXml3)
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").select
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").innertext = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").ContentEditable = False
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").disabled = True
ScreenshotExcel_NonSAP("XML Copied")
For Each m in IE.Document.getElementsByTagName("span")
If m.classname = "lsButton__text urBtnCntTxt" Then
If m.innertext = "Send" Then
m.click
End If
End If
Next


'XML 4
PayloadContent = CopyXML_Contents(InputXml4)
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").select
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").innertext = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").ContentEditable = False
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").disabled = True
ScreenshotExcel_NonSAP("XML Copied")
For Each m in IE.Document.getElementsByTagName("span")
If m.classname = "lsButton__text urBtnCntTxt" Then
If m.innertext = "Send" Then
m.click
End If
End If
Next


'XML 5
PayloadContent = CopyXML_Contents(InputXml5)
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").select
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").innertext = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").ContentEditable = False
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").disabled = True


ScreenshotExcel_NonSAP("XML Copied")
For Each m in IE.Document.getElementsByTagName("span")
If m.classname = "lsButton__text urBtnCntTxt" Then
If m.innertext = "Send" Then
m.click
End If
End If
Next


'XML 6
PayloadContent = CopyXML_Contents(InputXml6)
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").select
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").innertext = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").ContentEditable = False
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").disabled = True
ScreenshotExcel_NonSAP("XML Copied")
For Each m in IE.Document.getElementsByTagName("span")
If m.classname = "lsButton__text urBtnCntTxt" Then
If m.innertext = "Send" Then
m.click
End If
End If
Next


'XML 7
PayloadContent = CopyXML_Contents(InputXml7)
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").select
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").innertext = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").ContentEditable = False
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").disabled = True
ScreenshotExcel_NonSAP("XML Copied")
For Each m in IE.Document.getElementsByTagName("span")
If m.classname = "lsButton__text urBtnCntTxt" Then
If m.innertext = "Send" Then
m.click
End If
End If
Next


'Msgbox "Post click"
WScript.Sleep 2000
'Status = IE.Document.getElementByID("CEPJ.IDPView.MessageArea-txt").innertext
ScreenshotExcel_NonSAP("Message Posted")
'Msgbox Status
IE.quit
IE.quit

Set oExec = WshShell.Exec("taskkill /fi ""imagename eq iexplore.exe""")
WScript.Sleep 3000
Set oExec = nothing
End Function



Function PO_Post_I269_OrderIntFranc_VB(SenderInterface,InputXml1,InputXml3,InputXml5,InputXml6,InputXml7)


Dim WshShell1, oExec
Set WshShell1 = CreateObject("WScript.Shell")



Set oExec = WshShell1.Exec("taskkill /fi ""imagename eq iexplore.exe""")
WScript.Sleep 3000
Set oExec = nothing
Dim IE
Set IE = CreateObject("InternetExplorer.Application")

IE.Visible = 1
'IE.navigate "http://hlxc0bf033.unix.marksandspencercate.com:8147/webdynpro/dispatcher/sap.com/tc~lm~itsam~co~ui~xi~ovcustom~wd/MonitoringHomeApp" 'catec  
'IE.navigate "http://clxc8bfqr1.unix.marksandspencercate.com:8147/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true" 'INTB
'IE.Navigate "http://hlxd0bf032.unix.marksandspencerdev.com:54400/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true" 'FCQ
'IE.Navigate "http://hlxc0bf030.unix.marksandspencercate.com:8147/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true" 'FQL
IE.Navigate"http://hlxc0bf029.unix.marksandspencercate.com:54400/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true" 'FQL
'IE.Navigate "http://hlxc0bf027.unix.marksandspencercate.com:54400/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true#" 'FQJ


'Click Configuration and Monitoring Home link
'wait(2)
'For Each l In IE.Document.getElementsByTagName("A")  
'If l.classname = "urLnk" Then 
'If l.innertext = "Configuration and Monitoring Home" Then 
'l.click
'end if           
'End If 
'Next 
'
''Point to second Tab - Login page

For Each wnd In CreateObject("Shell.Application").Windows
If InStr(1, wnd.FullName, "iexplore.exe", vbTextCompare) > 0 Then
Set IE = wnd
End If
Next
'wait(3)
WScript.Sleep 3000
'Enter Credentials

IE.Document.getElementByID("logonuidfield").value = "P9124459"
IE.Document.getElementByID("logonpassfield").value = "fql12345"
IE.Document.Forms(0).Submit()

'wait(2)
WScript.Sleep 3000


For Each wnd In CreateObject("Shell.Application").Windows
If InStr(1, wnd.FullName, "iexplore.exe", vbTextCompare) > 0 Then
Set IE = wnd
End If
Next
'wait(3)
WScript.Sleep 3000
'Click SOA tab
IE.Document.getElementByID("CEPJ.IDPView.HorizontalContextualPanel1.2-2").click
'IE.Document.getElementsByTagName("A")
'Click Send Test message link
'wait(2)
WScript.Sleep 3000
For Each l In IE.Document.getElementsByTagName("A")  
If l.classname = "lsControl--valign lsLink urLnkFunction urTxtStd lsTextNoWrapping" Then 
If l.innertext = "Send Test Message" Then 
l.click
IEnewpage1 = l.href
End if           
End If 
Next

'Point to 3rd Tab - Send Test message
For Each wnd In CreateObject("Shell.Application").Windows
If InStr(1, wnd.FullName, "iexplore.exe", vbTextCompare) > 0 Then
Set IE = wnd
End If
Next

'Set WshShell = CreateObject("WScript.Shell")
'WshShell.AppActivate IEs

msgbox "2"
'wait(2)
WScript.Sleep 3000
PayloadContent = CopyXML_Contents(InputXml1)
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").select
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").innertext = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").ContentEditable = False
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").disabled = True
ScreenshotExcel_NonSAP("XML Copied")
'Msgbox "Copy to clipboard"
WScript.Sleep 3000
'Click Search button
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.Button-img").click
msgbox "3"
'wait(2)
WScript.Sleep 2000
'IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
'IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").value = PayloadContent


'Search for the interface
Set aHTML = IE.document
set iframes = aHTML.getElementById("URLSPW-0")
WScript.Sleep 2000
Set obabc=iframes.contentdocument
'obabc.getElementById("CEPJICNK.PrefillFieldsView.PreFillFeildsTable:2147483639").value="I101_PickPGI"
obabc.getElementById("CEPJICNK.PrefillFieldsView.PreFillFeildsTable:2147483639").value=SenderInterface
WScript.Sleep 3000
obabc.getElementById("CEPJICNK.PrefillFieldsView.PreFillFeildsTable:2147483639").focus
'wait(2)
WScript.Sleep 3000
Set WshShell = CreateObject("WScript.Shell")
'WshShell.AppActivate IEs
WshShell.SendKeys "{ENTER}"
obabc.getElementById("CEPJICNK.PrefillFieldsView.PreFillFeildsTable:2147483639").focus
'wait(2)
WScript.Sleep 1000
WshShell.SendKeys "{ENTER}"
'Msgbox "Check"

WScript.Sleep 3000
obabc.getElementById("CEPJICNK.PrefillFieldsView.SITextView.0").focus
WshShell.SendKeys "{ENTER}"

WshShell.SendKeys "{ENTER}"
'Msgbox "done"
WScript.Sleep 2000
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.QOSDropDownByKey").click
WshShell.SendKeys "{DOWN}"
WshShell.SendKeys "{DOWN}"
WshShell.SendKeys "{ENTER}"
WScript.Sleep 2000

IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").select
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").innertext = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").ContentEditable = False
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").disabled = True

ScreenshotExcel_NonSAP("Interface Details enetered")
IE.Document.getElementByID("CEPJ.IDPView.InputField").focus
WScript.Sleep 2000

'Msgbox "125"
For Each m in IE.Document.getElementsByTagName("span")

If m.classname = "lsButton__text urBtnCntTxt" Then

If m.innertext = "Send" Then

m.click

End If

End If

Next


'XML 3
PayloadContent = CopyXML_Contents(InputXml3)
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").select
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").innertext = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").ContentEditable = False
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").disabled = True
ScreenshotExcel_NonSAP("XML Copied")
For Each m in IE.Document.getElementsByTagName("span")
If m.classname = "lsButton__text urBtnCntTxt" Then
If m.innertext = "Send" Then
m.click
End If
End If
Next



'XML 5
PayloadContent = CopyXML_Contents(InputXml5)
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").select
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").innertext = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").ContentEditable = False
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").disabled = True


ScreenshotExcel_NonSAP("XML Copied")
For Each m in IE.Document.getElementsByTagName("span")
If m.classname = "lsButton__text urBtnCntTxt" Then
If m.innertext = "Send" Then
m.click
End If
End If
Next


'XML 6
PayloadContent = CopyXML_Contents(InputXml6)
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").select
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").innertext = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").ContentEditable = False
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").disabled = True
ScreenshotExcel_NonSAP("XML Copied")
For Each m in IE.Document.getElementsByTagName("span")
If m.classname = "lsButton__text urBtnCntTxt" Then
If m.innertext = "Send" Then
m.click
End If
End If
Next


'XML 7
PayloadContent = CopyXML_Contents(InputXml7)
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").select
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").click
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").innertext = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").defaultvalue = PayloadContent
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").ContentEditable = False
IE.Document.getElementByID("CEPJICNK.SendTestMsgCompView.PayloadTextEdit").disabled = True
ScreenshotExcel_NonSAP("XML Copied")
For Each m in IE.Document.getElementsByTagName("span")
If m.classname = "lsButton__text urBtnCntTxt" Then
If m.innertext = "Send" Then
m.click
End If
End If
Next


'Msgbox "Post click"
WScript.Sleep 2000
'Status = IE.Document.getElementByID("CEPJ.IDPView.MessageArea-txt").innertext
ScreenshotExcel_NonSAP("Message Posted")
'Msgbox Status
IE.quit
IE.quit

Set oExec = WshShell.Exec("taskkill /fi ""imagename eq iexplore.exe""")
WScript.Sleep 3000
Set oExec = nothing
End Function

Function PO_Post_FCJ()


'Set WshShell = CreateObject("WScript.Shell")
'Set oExec = WshShell.Exec("taskkill /fi ""imagename eq iexplore.exe""")
'WScript.Sleep 3000
'Set oExec = nothing

'Dim IE

'Set IE = CreateObject("InternetExplorer.Application")
'Wscript.Sleep 3000
'Set WSHShell = CreateObject("WScript.Shell")



'IE.Visible = 1

'IE.navigate "http://hlxc0bf033.unix.marksandspencercate.com:8147/webdynpro/dispatcher/sap.com/tc~lm~itsam~co~ui~xi~ovcustom~wd/MonitoringHomeApp" 'catec  
'IE.navigate "http://clxc8bfqr1.unix.marksandspencercate.com:8147/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true" 'INTB
'IE.Navigate "http://hlxd0bf032.unix.marksandspencerdev.com:54400/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true" 'FCQ
'IE.Navigate "http://hlxc0bf030.unix.marksandspencercate.com:8147/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true" 'FQL
'IE.Navigate"http://hlxc0bf029.unix.marksandspencercate.com:54400/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true" 'FQL
'IE.Navigate "http://hlxc0bf027.unix.marksandspencercate.com:54400/webdynpro/resources/sap.com/tc~lm~itsam~ui~mainframe~wd/FloorPlanApp?home=true#" 'FQJ


'Click Configuration and Monitoring Home link
'wait(2)
'For Each l In IE.Document.getElementsByTagName("A")  
'If l.classname = "urLnk" Then 
'If l.innertext = "Configuration and Monitoring Home" Then 
'l.click
'End if           
'End If 
'Next 
'
''Point to second Tab - Login page

'For Each wnd In CreateObject("Shell.Application").Windows
'If InStr(1, wnd.FullName, "iexplore.exe", vbTextCompare) > 0 Then
'Set IE = wnd
'End If
'Next
'WScript.Sleep 3000
'Enter Credentials

'IE.Document.getElementByID("logonuidfield").value = "P9134904"
'IE.Document.getElementByID("logonpassfield").value = "Sapfqj123"
'IE.Document.Forms(0).Submit()


End Function



Function UI5Logon(IE,WshShell1)

Dim oExec
Set WshShell1 = CreateObject("WScript.Shell")
Set oExec = WshShell1.Exec("taskkill /fi ""imagename eq iexplore.exe""")
WScript.Sleep 20000
Set oExec = nothing
'Dim IE

Set IE = CreateObject("InternetExplorer.Application")
IE.Visible = 1


ExecutionRegion = ObjXMLData.Item("Environment")
	Select Case Ucase(Trim(ExecutionRegion))
	
	Case "CATEA"
		IE.Navigate "https://hlxc0gs002.unix.marksandspencercate.com/" 
        Case "CATEL"
		IE.navigate "https://hlxc0gs003.unix.marksandspencercate.com:1404/sap/bc/ui5_ui5/ui2/ushell/shells/abap/Fiorilaunchpad.html" 'UI5
	End Select




Wscript.sleep 20000
For Each wnd In CreateObject("Shell.Application").Windows
If InStr(1, wnd.FullName, "iexplore.exe", vbTextCompare) > 0 Then
Set IE = wnd
End If
Next

ExecutionRegion = ObjXMLData.Item("Environment")
	Select Case Ucase(Trim(ExecutionRegion))
	
	Case "CATEA"
		IE.Document.getElementByID("USERNAME_FIELD-inner").value = "P9111207"
		IE.Document.getElementByID("PASSWORD_FIELD-inner").value = "ggj12345"
 		IE.Document.getElementByID("LOGIN_LINK").click
        Case "CATEL"
		IE.Document.getElementByID("USERNAME_FIELD-inner").value = "P3005873"
		IE.Document.getElementByID("PASSWORD_FIELD-inner").value = "access12345"
 		IE.Document.getElementByID("LOGIN_LINK").click
	End Select

		
WScript.Sleep 10000

End Function



Function Ui5_script_dummy()

Dim WshShell1, oExec
Set WshShell1 = CreateObject("WScript.Shell")
Set oExec = WshShell1.Exec("taskkill /fi ""imagename eq iexplore.exe""")
WScript.Sleep 20000
Set oExec = nothing
'Dim IE

Set IE = CreateObject("InternetExplorer.Application")
IE.Visible = 1
IE.navigate "https://hlxc0gs003.unix.marksandspencercate.com:1404/sap/bc/ui5_ui5/ui2/ushell/shells/abap/Fiorilaunchpad.html" 'UI5


Wscript.sleep 20000
For Each wnd In CreateObject("Shell.Application").Windows
If InStr(1, wnd.FullName, "iexplore.exe", vbTextCompare) > 0 Then
Set IE = wnd
End If
Next


		IE.Document.getElementByID("USERNAME_FIELD-inner").value = "P3005873"
		IE.Document.getElementByID("PASSWORD_FIELD-inner").value = "access12345"
 		IE.Document.getElementByID("LOGIN_LINK").click
WScript.Sleep 10000

IE.Document.getElementById("__tile2").focus
WshShell1.SendKeys "{ENTER}"
ScreenshotExcel_NonSAP("Scope creation Page")
IE.Document.getElementById("__button0").focus
WshShell1.SendKeys "{ENTER}"
Wscript.sleep 2000

IE.Document.getElementByID("__xmlview2--createScopeDespInput-inner").value = "Artilce-"&Article
IE.Document.getElementByID("__xmlview2--createBUClothes-Button").focus
WshShell1.SendKeys "{ENTER}"
IE.Document.getElementByID("__xmlview2--createSiteCatDC-Button").focus
WshShell1.SendKeys "{ENTER}"
IE.Document.getElementByID("__xmlview2--createIsArtl-Button").focus
WshShell1.SendKeys "{ENTER}"
'**********
IE.Document.getElementByID("__xmlview2--createSiteIncludeText-inner").defaultvalue = Site
IE.Document.getElementByID("__xmlview2--createSiteIncludeText-inner").select
IE.Document.getElementByID("__xmlview2--createSiteIncludeText-inner").click
IE.Document.getElementByID("__xmlview2--createSiteIncludeText-inner").innertext = Site
IE.Document.getElementByID("__xmlview2--createSiteIncludeText-inner").defaultvalue = Site
IE.Document.getElementByID("__xmlview2--createSiteIncludeText-inner").ContentEditable = False
IE.Document.getElementByID("__xmlview2--createSiteIncludeText-inner").disabled = True
'IE.Document.getElementByID("__xmlview2--createSiteIncludeText-inner").Value = Site
'**********
'******************
'IE.Document.getElementByID("CEPJICNK.MessageFilterView.IDocNumberInputField").Value = Article
IE.Document.getElementByID("CEPJICNK.MessageFilterView.IDocNumberInputField").defaultvalue =  Article
IE.Document.getElementByID("CEPJICNK.MessageFilterView.IDocNumberInputField").select
IE.Document.getElementByID("CEPJICNK.MessageFilterView.IDocNumberInputField").click
IE.Document.getElementByID("CEPJICNK.MessageFilterView.IDocNumberInputField").innertext = Article
IE.Document.getElementByID("CEPJICNK.MessageFilterView.IDocNumberInputField").defaultvalue =  Article
IE.Document.getElementByID("CEPJICNK.MessageFilterView.IDocNumberInputField").ContentEditable = False
IE.Document.getElementByID("CEPJICNK.MessageFilterView.IDocNumberInputField").disabled = True
'****************
IE.Document.getElementByID("__button2").focus
WshShell1.SendKeys "{ENTER}"
Wscript.sleep 2000
IE.Document.getElementByID("__button3").focus
WshShell1.SendKeys "{ENTER}"
Wscript.sleep 2000
IE.Document.getElementByID("__item3-__xmlview1--manageScopesTable-0-selectSingle-Button").focus
WshShell1.SendKeys "{ENTER}"
Wscript.sleep 2000
IE.Document.getElementByID("__xmlview1--approveScopeBtn").focus
WshShell1.SendKeys "{ENTER}"
Wscript.sleep 10000
IE.Document.getElementByID("__button4").focus
WshShell1.SendKeys "{ENTER}"
ScreenshotExcel_NonSAP("Idoc created")
IE.Document.getElementByID("actionsBtn").focus
WshShell1.SendKeys "{ENTER}"
Wscript.Sleep 1000
IE.Document.getElementByID("logoutBtn").focus
WshShell1.SendKeys "{ENTER}"
Wscript.Sleep 2000

IE.Document.getElementByID("__mbox-btn-0").focus
WshShell1.SendKeys "{ENTER}"

IE.quit
IE.quit

Set oExec = WshShell.Exec("taskkill /fi ""imagename eq iexplore.exe""")
WScript.Sleep 3000
Set oExec = nothing


End Function


Public Function Get_BOReport_Values()
Set objFSO = CreateObject("Scripting.FileSystemObject")

objStartFolder = "C:\Users\ilango.mariappan\AppData\Local\Microsoft\Windows\INetCache\IE"

Set objFolder = objFSO.GetFolder(objStartFolder)
LastDate = "28/03/2019 08:54:30"

'Set colFiles = objFolder.Files
Set colFolders = objFolder.SubFolders
For Each objFolder in colFolders
'Wscript.Echo objFolder.Name
 
For Each objfile in objFolder.Files
    
  'If objFolder.DateLastModified > LastDate Or IsEmpty(LastDate) Then
  'If objFolder.DateLastModified > LastDate Then
   If Instr(objfile.Name , "CH01a_Current_Sales_(GC)_Store_Stock") > 0 And Instr(objfile.Name , ".xls")  Then
        'LastFolder = objFolder.Name
        'LastDate = objFolder.DateLastModified
	
	 Set objExcel = CreateObject("Excel.Application")
	
	objExcel.Workbooks.Open objStartFolder & "\" & objFolder.Name & "\" & objfile.Name
	objExcel.Visible = True
	set mysheets = objExcel.ActiveWorkbook.Worksheets(1)
	
	
	rowcnt=mysheets.Usedrange.rows.count
	colcnt=mysheets.Usedrange.columns.count
	 Set ObjDataExl = CreateObject("Scripting.Dictionary")
	 Set objRegEx = CreateObject("VBScript.RegExp")

	objRegEx.Global = True

	objRegEx.Pattern = "[^A-Za-z]"
	For i=2 to colcnt

		If mysheets.Cells(13,i).Value <> "" Then
			colname = mysheets.Cells(13,i).Value
			
			strSearchString = mysheets.Cells(13,i).Value

			If Instr(strSearchString , "%") > 0 Then
				strSearchString = strSearchString & "_Percent"
			End If
			strSearchString = objRegEx.Replace(strSearchString, "")


			'Wscript.Echo strSearchString
			ObjDataExl.Add strSearchString , mysheets.Cells(14,i).Value
		End If

	Next
	objExcel.Workbooks.Close
	objExcel.Quit
	Set objExcel=Nothing
	Set mysheets=Nothing
Exit For
   End If

Next
Next

End Function