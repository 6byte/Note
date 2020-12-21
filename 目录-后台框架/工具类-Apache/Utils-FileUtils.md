### FileUtils

https://www.jianshu.com/p/8eb773f8e552

#### 读取文件内容

>方法	**readFileToString**
>定义	FileUtils.readFileToString(new File(文件路径), "编码");
>
>ex 	  String text= FileUtils.readFileToString(new File("c://text.txt"), "utf-8");

#### 写入文件内容

>方法	readFileToString
>
>定义	FileUtils.write(new File(文件路径), "追加的字符串", "编码", 是否追加);
>
>FileUtils.write(new File(path), "Hello", "UTF-8", true);//false是覆盖追加



