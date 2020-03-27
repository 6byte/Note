# Command-常用

## 端口

```
netstat -aon|findstr 8080
tasklist | findstr  5080

查找端口
netstat -aon|findstr 端口号
结束端口
tasklist | findstr  对应的PID

```

## 配置后台运行

```
注意要使用UNICODE编码,UTF-8会报错

简单使用	createobject("wscript.shell").run "要运行的程序路径",0 

Demo	createobject("wscript.shell").run "back.bat",0 
```



## schtasks

#### 1.创建任务

　在每天的22.44定时执行一次。

```
schtasks /create /tn "test3" /tr "F:\test.bat" /sc daily /st 22:44
```

　在特定时间运行一次。

```
schtasks /create /tn "test3" /tr "F:\test.bat" /sc once /st 23:03 /sd 2018/03/05
```

#### 2.运行一次任务

　创建任务后，立即运行一次。

```
schtasks /run /tn test3
```

#### 3.删除任务

　任务不准备用时，删除任务。

```
schtasks /delete /tn test3
```