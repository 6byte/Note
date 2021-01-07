## Java-线程

##### 线程-通信

​	<https://www.jianshu.com/p/8a58d8335270>

​	<https://blog.csdn.net/dingchang3060/article/details/82384748>

````
关键字:wait(),notify(),notifyAll(),synchronized
前提理解：
	1.一个类可以有多个对象，一个对象可以产生多个线程
	2.线程独享java栈，程序计数器PC,本地方法栈
	3.线程共享，是指同一个对象中的线程共享方法栈和堆
	4.CPU速度远超内存读写，为了避免CPU等待，加入高速缓存
	5.缓存一致性:
		1.每个CPU都有自己的缓存器
		2.所有的缓存器共享主内存
		3.当多个缓存器对主内存进行读写时，需要缓存一致
	6.JMM规定静态变量，常量等线程共享的变量均存放于主内存中
	7.每个线程有各自的工作内存，工作内存中的变量是主内存的副本
	8.线程通信只能在主内存中进行

````

##### 线程-交互

```
lock（锁定）	：	作用于主内存变量，把一个变量标识为一条线程独占状态。
unlock（解锁）  ：	作用于主内存变量，把一个处于锁定状态的变量释放出来，释放后的变量才可以被其他线程锁定。
read（读取）	：   作用于主内存变量，把一个变量从主内存传输到线程的工作内存中，以便随后的 load 动作使用。
load（载入）	：   作用于工作内存变量，把 read 操作从主内存中得到的变量值放入工作内存的变量副本中。
use（使用）		：   作用于工作内存变量，把工作内存中的一个变量值传递给执行引擎，每当虚拟机遇到一个需要使用变量值的字节码指令时执行此操作。
assign（赋值）  ：   作用于工作内存变量，把一个从执行引擎接收的值赋值给工作内存的变量，每当虚拟机遇到一个需要给变量进行赋值的字节码指令时执行此操作。
store（存储）   ：   作用于工作内存变量，把工作内存中一个变量的值传递到主内存中，以便后续 write 操作。
write（写入）   ：   作用于主内存变量，把 store 操作从工作内存中得到的值放入主内存变量中。
```

##### 线程-Happen-Before

````
先行发生原则:A操作比B先执行，发生B操作的时候，操作A产生的影响能被B观察到，“影响”包括修改了内存中的共享变量的值、发送了消息、调用了方法等。

　　Happen-Before的规则有以下几条

- - 程序次序规则（Program Order Rule）：在一个线程内，程序的执行规则跟程序的书写规则是一致的，从上往下执行
  - 管程锁定规则（Monitor Lock Rule）：一个Unlock的操作肯定先于下一次Lock的操作。这里必须是同一个锁。同理我们可以认为在synchronized同步同一个锁的时候，锁内先行执行的代码，对后续同步该锁的线程来说是完全可见的。
  - volatile变量规则（volatile Variable Rule）：对同一个volatile的变量，先行发生的写操作，肯定早于后续发生的读操作
  - 线程启动规则（Thread Start Rule）：Thread对象的start()方法先行发生于此线程的没一个动作
  - 线程中止规则（Thread Termination Rule）：Thread对象的中止检测（如：Thread.join()，Thread.isAlive()等）操作，必行晚于线程中所有操作
  - 线程中断规则（Thread Interruption Rule）：对线程的interruption（）调用，先于被调用的线程检测中断事件(Thread.interrupted())的发生
  - 对象中止规则（Finalizer Rule）：一个对象的初始化方法先于一个方法执行Finalizer()方法
  - 传递性（Transitivity）：如果操作A先于操作B、操作B先于操作C,则操作A先于操作C

````

##### 线程-创建

方式一:继承Runnable

```JAVA
public static void main(String[] args) {
    //1.新建对象,将目标类传给Thread类
    Thread thread = new Thread(new Main());
    //调用thread.start();
    thread.start();
}
//覆盖方法
@Override
public void run(){
    for(int i = 0 ; i < 10000 ; i ++ ){
        System.out.println(i+"A");
    }
}

```

方式二:继承Thread

```JAVA
public class A extends Thread{
    public static void main(String[] args) {
        Main main = new Main();//1.新建对象
        main.start();
    }
public void run(){
      for(int i = 0 ; i < 10000 ; i ++ ){
       System.out.println(i+"A");
     }
}
}

```

方式三:匿名内部类

```
new Thread(new Runnable() {
			@Override
	public void run() {
				for (int i = 0; i < 10000; i++) {
			System.out.println(i);
		}
	}
}).start();
```

##### 线程-方法

```
sleep(): 					强迫一个线程睡眠Ｎ毫秒。
yield():					线程让步
isAlive(): 					判断一个线程是否存活。
join(): 					调用Join的方法先被执行。
activeCount(): 				程序中活跃的线程数。
enumerate(): 				枚举程序中的线程。
currentThread(): 			得到当前线程。
isDaemon(): 				一个线程是否为守护线程。
setDaemon(): 				设置一个线程为守护线程。
setName(): 					为线程设置一个名称。
wait(): 					强迫一个线程等待。
notify(): 					通知一个线程继续运行。
setPriority(): 				设置一个线程的优先级。
getPriority():				获取优先级
```



## 线程-线程池

线程池-入门理解

<https://www.cnblogs.com/zhujiabin/p/5404771.html>

```
Java通过Executors提供四种线程池，分别为：
newCachedThreadPool，newScheduledThreadPool，newSingleThreadExecutor
newCachedThreadPool 		创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
newScheduledThreadPool 		创建一个定长线程池，支持定时及周期性任务执行。
newSingleThreadExecutor 	创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
```

线程池-使用场景

```
单个任务时间比较短
需要处理的任务数量很大
```

线程池-模型

```
ULT:用户程序实现,不依赖操作系统,线程阻塞进程阻塞
KTL:系统内容管理线程，线程阻塞不会导致进程阻塞，多处理器并行运行
JDK是KLT内存模型，用户空间线程和内核线程1:1关系
```

线程池-参数分析

```
corePoolSize:		核心线程数，超过转就绪
maximumPoolsize:	最大线程数
keepAliveTime:		线程池维护线程所允许的空闲时间
workQueue:			用来保存等待被执行任务的阻塞队列
threadFactory		创建新的线程
handler:			线程池执行策略
```



