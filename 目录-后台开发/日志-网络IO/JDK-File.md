## JDK-FILE

### JDK-File

#### 获取文件名

```mysql
方法定义:public String getName()
获取Path路径下的文件名：前缀名+后缀名
String fileName = file.getName();
#结果		img.png
```

#### 其他操作

```
#检测文件是否存在
exists();

#文件最后被修改的时间
lastModified()

#删除该文件
delete()

#重命名
renameTo(File dest)
```

