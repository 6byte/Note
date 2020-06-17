### 中间件-Active-MQ

参考博客

```
http://www.uml.org.cn/zjjs/201802111.asp
```

#### JMS

- JMS即Java消息服务
- 消息的发送是异步的,非阻塞的,无需等待服务端响应
- 自身不是消息服务系统，仅定义了消息传送的接口，没有具体实现。

#### ActiveMQ

- ActiveMQ就是JMS规范的具体实现

![img](http://www.uml.org.cn/zjjs/images/201802111.png)

#### AcMQ起步

- 需要Java的支持，配置Java环境变量
- 8161：前端控制台，61616消息接收发送

#### API总览

##### 公共 API

- 公共API：可用于向一个队列或主题发送消息或从其中接收消息
- 点对点API：专门用于使用队列Queue传送消息
- 发布/订阅API：专门用于使用主题Topic传送消息

公共API:

|        API        |                     作用                      |
| :---------------: | :-------------------------------------------: |
| ConnectionFactory |                创建Connection                 |
|    Connection     |                  创建Session                  |
|      Session      | 创建Message，MessageProducer，MessageConsumer |
|      Message      |                   定义消息                    |
|    Destination    |                                               |
|  MessageProducer  |                   发送消息                    |
|  MessageConsumer  |                   接收消息                    |
|                   |                                               |

##### 点对点API

```
点对点传输消息，使用这套API
QueueConnectionFactory QueueConnection QueueSession Message Queue QueueSender QueueReceiver 
```

##### 发布/订阅API

```
TopicConnectionFactory TopicConnection TopicSession Message Topic 
TopicPublisher TopicSubscriber 
```

图示

![img](http://www.uml.org.cn/zjjs/images/201802114.png)

#### 比较

![img](http://www.uml.org.cn/zjjs/images/201802115.png)

|  比较项  |                 队列                 |                主题                |
| :------: | :----------------------------------: | :--------------------------------: |
|   概要   |                点对点                |              发布订阅              |
|  完整性  |          保证每条都能被接收          |               不保证               |
| 消息丢失 |                 不会                 |     只有正在监听的订阅者能接收     |
| 传送策略 | 一对一发送接收，完成后服务器删除消息 | 一对多发布接收，监听者都能收到消息 |
|          |                                      |                                    |

#### 推拉模式

##### 拉模式

- 点对点消息，如果没有消费者在监听队列，消息将一直保留在队列中
- 属于轮询模型。
- 消息不主动推送给消息消费者，称拉模式

##### 推模式

- 发布订阅是推模型
- 消息自动广播，消息消费者无须通过主动请求或轮询主题的方法来获得新的消息。

#### 消息类型

|   消息类型    |                   作用                   |
| :-----------: | :--------------------------------------: |
|  TextMessage  |             传递String的信息             |
| ObjectMessage |          传递可序列化的Java对象          |
|  MapMessage   | 必须是Java八大数据类型，或包装类及String |
| BytesMessage  |                传递字节流                |
| StreamMessage |   传递原始数据类型流，读写类型必须相同   |
|               |                                          |

##### 事务消息

概念

- 事务消息必须在收发消息显式地调用session.commit();
- 事务消息，任何情况会自动被确认

编码

```
事务消息
connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
非事务消息
connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
```

#### 消息确认机制

概览

- 消息只有在被确认之后，才认为已经被成功消费，然后从队列或主题中删除
- 消息的成功消费通常包含三个阶段：客户接收消息；(2)、客户处理消息;(3)、消息被确认；



![img](http://www.uml.org.cn/zjjs/images/201802116.png)

##### 四种机制

AUTO_ACKNOWLEDGE

- 客户成功从receive方法返回时，会话自动确认消息,然后自动删除消息.

CLIENT_ACKNOWLEDGE

- 接收端调用message.acknowledge()才会被删除，消息是不会被删除的

DUPS_OK_ACKNOWLEDGE 

- 非必须确认,消息可能会重复发送
- 在第二次重新传送消息时，消息头的JMSRedelivered会被置为true，表示当前消息已经传送过一次，客户端需要进行消息的重复处理控制

SESSION_TRANSACTED

- 事务提交并确认

#### 持久化消息

**概览**:默认持久化的

**编码**

```
//持久化的，当然activemq发送消息默认都是持久化的
messageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);

//不持久化
messageProducer.setDeliveryMode(DeliveryMode. NON_PERSISTENT);
```

#### 消息过滤



