## Email

https://www.cnblogs.com/heqiyoujing/p/9477490.html

### 配置

```
spring.mail.default-encoding=UTF-8
spring.mail.host=smtp.qq.com
#发送者的邮箱密码
spring.mail.password=XXXXXX
#端口
spring.mail.port=25
#协议
spring.mail.protocol=smtp
#发送者的邮箱账号
spring.mail.username=XXXXXX@qq.com
```

### 应用

```JAVA
@Autowired
JavaMailSender jsm;
@Autowired
SimpleMailMessage message;

@Value("${spring.mail.username}")
private String username;
/*
    *   功能:给目标发送邮件
    *   参数:
    *       targetEmail:目标邮箱
    *       content：内容
    *       title:标题
    *   返回值:
    *       是否成功
    * */
@RequestMapping("/sendEmail")
public boolean sendEmail(String targetEmail,String content,String title) {
    //建立邮箱消息
    //发送者
    try {
        message.setFrom(username);
        //接收者
        message.setTo(targetEmail);
        //发送标题
        message.setSubject(title);
        //发送内容
        message.setText(content);
        jsm.send(message);
    }
    catch (MailException e) {
        return false;
    }
    return true;
}
```

