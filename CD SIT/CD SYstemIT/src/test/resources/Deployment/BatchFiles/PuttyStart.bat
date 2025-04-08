@setlocal EnableDelayedExpansion
@echo off

set envname= "huxc0562.unix.marksandspencercate.com"
set username= "p9147280"
set usrpw= "Secret39"
set "user=rpwms"

echo echo "df -gt . ;sleep 25; /bin/bash" ^| sudo su - %user% > space_check.txt

SET _var3=%username%@%envname%
exit | putty.exe -ssh "%_var3%"  22 -pw %usrpw% -m "space_check.txt" -t