## JDK-IO

概览

```
·	打开文本编辑器，都会查询编码表，把字节转换成字符
转换过程
·	0-127查询ASCII
·	其他值查询系统默认编码表
·	中文查询GBK
```



IO-分类

```
三种分类:
	1.按流的流向分，可以分为输入流和输出流
		输入流：数据从硬盘到内存，通常称为输入流
		输出流：数据从内存到硬盘，通常称为输出流
	2.按操作单元划分，可分为字节流和字符流
		字节流操作8位的字节：OutputStream，InputStream
		字符流操作16位的字符：Reader，Writer
	3.按照流的角色，分为节点流和处理流。
		 节点流是可以从IO设备读写的流
		 处理流则用于对一个已存在的流进行连接和封装，来实现数据的读/写功能
```



### 字节操作

#### FileOutputStream

```
构造函数有三个作用
1.创建一个FileOutputStream对象
2.创建一个空的文件
3.把FileOutputStream对象指向创建好的文件
```

#### write

概览

```
·介于0-127之间的数，在记事本中打开时，会查询ASCII码表，显示a
·如果写的第一个字节是负数，那第一个字节会和第二个字节组成一个中文显示，查询GBK编码
```



写入方式

1.单个字节写入	write(number);

```java
String path ="F:\\a.txt";
FileOutputStream fos = new FileOutputStream( path );
向  F:\\a.txt  写入单个数据
fos.write( 49 );
fos.close();

```

2.多个字节写入 

```  
write(byte数组)
byte[] b = {49,50,51,52,53,54};
 fos.write( b);
 fos.close();
```

3.分节写入 

```
write(byte[] b ,int off ,int len)   
    b:指定数组
    off:数组下标
	len:写入数组的长度
 	解释:从数组b中第off+1个位置读取len个字节的数据
实例:
byte[] b = {48,49,50,51,52};
fos.write(b,2,1);
从b数组中，下标2的位置，读取1个字节--50，在记事本中打开，会将50转换成ascii码中的2
```

4.字符串写入

```java
使用getBytes()可以把字符串转换成byte数组
String text = "你好";
byte [] b = text.getBytes();
write(b)//原理同2
```



代码

```JAVA

public class WRITE {
public static void main( String[] args ) throws IOException {
        
   FileOutputStream fi = new FileOutputStream( "F:\\b.txt" );
   fi.write(12);
    fi.close();
}
}
```





#### read

```java
注意
·read返回[0-255]的unsigned byte,java没有该类型，所以用int接收
·byte的范围是[-128,127]，如果read()返回值在128-255，则表示负数
·返回-1代表文件结束
·read自带指针，每读取一个字节，指针都会往后移动一位

单个读取
int read = fi.read()

循环读取
    首先要理解
    int read = fi.read()	//读取第一字节
    read = fi.read()		//读取第二个字节
    read = fi.read()		//读取第三个字节
    ....
    
int read = 0;
while ((read = fi.read())!=-1){
    System.out.println(read );
}
分析
    //从fi中读取数据到read中
   	· read = fi.read()
    //等介于read!=-1,并且将指针后移，相当于执行了多个fi.read()
    ·(read = fi.read())!=-1
    ·上面的条件为真，一直执行，直到read==-1


多个读取
```

代码

```JAVA
public class READ {
public static void main( String[] args ) throws IOException {
    
    FileInputStream fi = new FileInputStream( "F:\\b.txt" );
    int read = 0;
    while ((read = fi.read())!=-1){
        System.out.println(read );
    }
}
}
```

