### Win 优化

1.移除无用的侧边文件夹

网页:<https://jingyan.baidu.com/article/adc81513aa02cff722bf7367.html>

```
1.打开注册表regedit
2.找键
HKEY_LOCAL_MACHINE＼SOFTWARE＼Microsoft＼Windows＼CurrentVersion＼Explorer＼FolderDescriptions  
3.将 图片 文件夹隐藏
	-查找0ddd015d-b06c-45d5-8c4c-f59713854639键
	-将该键下的	PropertyBag/ThisPCPolicy的Show改为Hide
```

2.移除3D对象文件夹

```
1.创建xx.bat

@echo off
REG DELETE "HKEY_LOCAL_MACHINE\SOFTWARE\Microsoft\Windows\CurrentVersion\Explorer\MyComputer\NameSpace\{0DB7E03F-FC29-4DC6-9020-FF41B59E513A}" /f
REG DELETE "HKEY_LOCAL_MACHINE\SOFTWARE\Wow6432Node\Microsoft\Windows\CurrentVersion\Explorer\MyComputer\NameSpace\{0DB7E03F-FC29-4DC6-9020-FF41B59E513A}" /f

exit



“图片”：{0ddd015d-b06c-45d5-8c4c-f59713854639}＼PropertyBag

“视频”：{35286a68-3c57-41a1-bbb1-0eae73d76c95}＼PropertyBag

“下载”：{7d83ee9b-2244-4e70-b1f5-5393042af1e4}＼PropertyBag

“音乐”：{a0c69a99-21c8-4671-8703-7934162fcf1d}＼PropertyBag

“桌面”：{B4BFCC3A-DB2C-424C-B029-7FE99A87C641}＼PropertyBag

“文档”：{f42ee2d3-909f-4907-8871-4c22fc0bf756}＼PropertyBag
2.以管理员身份运行
```

