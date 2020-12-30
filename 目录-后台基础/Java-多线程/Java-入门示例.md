### Java-多线程示例

#### 触发多线程

###### 普通执行

```java
public class MultiThreading implements Runnable {

	static int ticket = 100;//线程争夺的资源

	public static void main(String[] args) {
		MultiThreading controller = new MultiThreading();
        
        //只有同一个对象中的资源才会产生冲突
        //多个对象中有多个 ticket ，并不存在竞争关系
		Thread thread1 = new Thread(controller);
		Thread thread = new Thread(controller);
		thread.start();
		thread1.start();
	}

    //该方法未加锁
	public void run() {
		for (int i = 0; i < ticket--;) {
			System.out.println(Thread.currentThread().getName() + "==>" + ticket);
		}
	}
}
```

>输出结果
>	Thread-1==>56
>        Thread-0==>55
>        Thread-1==>54
>        Thread-0==>53
>	Thread-1==>52
>        ...

###### 加锁执行

```java
public class MultiThreading implements Runnable {

	static int ticket = 100;

	public static void main(String[] args) {
		MultiThreading controller = new MultiThreading();
		Thread thread1 = new Thread(controller);
		Thread thread = new Thread(controller);
		thread.start();
		thread1.start();
	}
//此处用 synchronized 修饰 ,每次仅能一个进程进去
	public synchronized void run() {
		for (int i = 0; i < ticket--;) {
			System.out.println(Thread.currentThread().getName() + "==>" + ticket);
		}
	}
}

```

