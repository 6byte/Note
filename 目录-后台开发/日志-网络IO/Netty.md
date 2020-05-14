### Netty

#### 概览

```
1.三大核心：Channel(通道)，Buffer(缓冲区)，Selector（选择器）
2.NIO是面向缓冲区编程
3.Channel
	1.通道(Channel)是双向的
	2.FileChannel用于文件的数据读写
	3.DatagramChannel用于UDP的数据读写
	4.ServerSocketChannel和SocketChannel用于TCP的数据读写
	5.flip()可以实现读写切换
	6.可以反应操作系统
4.关系:
	1.每个Channel对应一个Buffer
	2.一个Selector对应多个Channel
	3.一个线程对应一个Selector
	4.Selector会根据不同的事件，在各个通道中切换
5.Buffer:
	1.是一个内存块
	2.可以读，可以写
```

### Buffer

<https://www.cnblogs.com/xy80hou/p/11106005.html>

```
理解:数组，和一堆方法
四个重要属性:对buffer的限制
	position:下个要读写元素的下标
	limit:不能读或写元素下标
	capacity:缓冲区元素数量
	mark:不能大于position
```

### Channel

```
理解:提供从文件网络读取数据的渠道
```

