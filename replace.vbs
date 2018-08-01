version = InputBox("Please input the mod version:", _
	"Building build.gradle")
group = InputBox("Please input the mod package:", _
	"Building build.gradle")
modid = InputBox("Please input the mod id:", _
	"Building build.gradle")

Set objFS = CreateObject("Scripting.FileSystemObject")
strFile = "build.gradle"
Set objFile = objFS.OpenTextFile(strFile)

do Until objFile.AtEndOfStream
    strLine = objFile.ReadLine
    If InStr(strLine,"version-1.0")> 0 Then
        strLine = Replace(strLine,"version-1.0",version)
    ElseIf InStr(strLine,"com.yourname.modid")> 0 Then
        strLine = Replace(strLine,"com.yourname.modid",group)
    ElseIf InStr(strLine,"MODID")> 0 Then
        strLine = Replace(strLine,"MODID",modid)
    End If
    WScript.Echo strLine
Loop    