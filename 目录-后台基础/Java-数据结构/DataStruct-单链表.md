### 线性表-单链表

概览

```java
实现步骤
	初始化:创建并初始化头结点
	添加元素：
		newNode.next = head;//将头结点赋值给新节点的next
		head = node ;//头结点的地址一直被新节点覆盖
```

#### 添加

```java
public class LinkedList{
	private	Node head;//创建头结点
    
    LinkedList(){
        head = new Node(null.null);//初始化头结点
    }
    class Node{
        //使用内部类来存储节点数据
        Node next;
        Object el;
        
        Node(Object el,Node head){
            //使用构造函数往链表添加对象
            this.el = el ;
            this.next = head;
        }
    }
    public void add(Object el){
        //往链表添加元素
        head = new Node(el,this.head);
    }
}
```

#### 输出

```java
public void print(Node head){
    Node tmp = head;
    while(tmp.next != null){
        tmp = tmp.next;
    }
}
```



