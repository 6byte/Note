## MQ-ACTIVEMQ

概览

```
API发送和接受，高可用性，MQ的集群和容错配置，持久化，延时/定时处理，签收机制，整合
作用:解耦，异步
```

JMS

```
ConnectionFactory
Connection
Session
MessageProducer
MessageConsumer
Destination
Msg
```

Destination

```
队列：点对点的消息传递域中，目的地成为队列
主题：在发布订阅消息传递域中，目的地被称为主题
```

### 生产者

```JAVA
//主机IP，使用的是TCP协议
private final static String ACTIVEMQ_URL = "tcp://134.175.79.33:61616";
//发送的消息的队列名字
    private final static String ACTIVEMQ_NAME = "NAME1";
    public static void main(String[] args) throws JMSException {
//        创建连接
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        Connection connection = factory.createConnection();
        connection.start();
        //        第一个参数:事务
        //        第二个参数:签收
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        //		  选择主题或队列，并指定名字
        Queue queue = session.createQueue(ACTIVEMQ_NAME);
        //            创建消息
        MessageProducer producer = session.createProducer(queue);
        for (int i = 0; i < 3; i++) {
            TextMessage textMessage = session.createTextMessage("msg" + i);
            producer.send(textMessage);

        }
        session.close();
        connection.close();
```

### 消费者

```

```

