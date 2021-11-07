Attribute VB_Name = "Module2"
Sub Button1_Click()
    Dim filePath    As String
    filePath = "C:\Users\Kristine\source\RTU\RisAlg\2MD\md2\src\test\resources\dip107\positive-tests.csv"
    ' Ieprieksh defineetie testi
    Dim testList    As Variant
    testList = Array("19.5", "19.999", "26", "25.6589", "70", "75", "65", "82", "95", "-5.4443", "-6.1254", "-17.9254")
    Dim results(12)
    For I = 0 To UBound(testList) - LBound(testList)
        Range("L1").Value = testList(I)
        results(I) = testList(I) + ", " + Range("B3").Value
    Next I
    
    'Random testi
    Dim a           As Double
    Dim Randresults(20)
    For I = 0 To 20
        a = Application.WorksheetFunction.RandBetween(-180, 180) + (Application.WorksheetFunction.RandBetween(0, 100001) / 100000)
        Range("L1").Value = a
        'Strange Randresults(I) = a + ", " + Range("B3").Value
        'Results into error 13 Type Mismatch...
        Randresults(I) = CStr(a) + ", " + Range("B3").Value
    Next I
    
    r = Join(results, vbCrLf) + Join(Randresults, vbCrLf)
    MsgBox r
    
    dAlrt = Application.DisplayAlerts
    Application.DisplayAlerts = False        'do not save tempBook
    pth = ThisWorkbook.Path
    Workbooks.Add
    Dim sA          As Variant
    ActiveSheet.Range("A1").Value = "a"
    ActiveSheet.Range("B1").Value = "TheLastOneIsExpectedOutputResult"
    For I = 0 To 12
        sA = Split(results(I), ",")
        ActiveSheet.Range("A" + CStr(I + 2)).Value = sA(0)
        ActiveSheet.Range("B" + CStr(I + 2)).Value = sA(1)
    Next I
    For J = 0 To 12
        sA = Split(Randresults(J), ",")
        ActiveSheet.Range("A" + CStr(J + 2 + 12)).Value = sA(0)
        ActiveSheet.Range("B" + CStr(J + 2 + 12)).Value = sA(1)
    Next J
    'fName = pth + Application.PathSeparator + "positive-tests.csv"
    fName = "C:\Users\oskar\dip107\dip107\src\test\resources\dip107\positive-tests.csv"
    ActiveWorkbook.WebOptions.Encoding = msoEncodingUTF8
    ActiveSheet.SaveAs _
                       Filename:=fName _
                       , FileFormat:=xlCSV _
                       , CreateBackup:=False        '_
    '  , AccessMode:=xlExclusive _
    '  , ConflictResolution:=Excel.XlSaveConflictResolution.xlLocalSessionChanges
    
    ActiveWorkbook.Close
    
    Application.DisplayAlerts = dAlrt
End Sub

