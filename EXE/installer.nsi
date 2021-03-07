!include "FileAssociation.nsh"

Name "Wortuzzel Installer"
OutFile "Wortuzzel_Setup.exe"

InstallDir $PROGRAMFILES\Wortuzzel

Page directory
Page instfiles

Section "Install"
	SetOutPath $INSTDIR
	File src\wortuzzel.jar
	File src\Wortuzzel.exe
	
	Delete $APPDATA\Wortuzzel\lang\DE
	Delete $APPDATA\Wortuzzel\lang\ENG
	Delete $APPDATA\Wortuzzel\config.ini
	Delete $APPDATA\Wortuzzel\video.mp4
	
	${registerExtension} "$INSTDIR\Wortuzzel.exe" ".wuzz" "Wortuzzel File"
	
	WriteUninstaller $INSTDIR\uninstall.exe
	
	WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\Wortuzzel" \
                 "DisplayName" "Wortuzzel"
	WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\Wortuzzel" \
                 "UninstallString" "$\"$INSTDIR\uninstall.exe$\""
	
	CreateDirectory $SMPROGRAMS\Wortuzzel
	CreateShortCut $SMPROGRAMS\Wortuzzel\Wortuzzel.lnk $INSTDIR\Wortuzzel.exe
SectionEnd

Section "Uninstall"
	Delete $SMPROGRAMS\Wortuzzel\Wortuzzel.lnk
	RMDir $SMPROGRAMS\Wortuzzel

	Delete $INSTDIR\wortuzzel.jar
	Delete $INSTDIR\Wortuzzel.exe
	
	Delete $INSTDIR\uninstall.exe
	
	RMDir $INSTDIR
	
	${unregisterExtension} ".wuzz" "Wortuzzel File"

	DeleteRegKey HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\Wortuzzel"
SectionEnd