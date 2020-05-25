#### JavaSpringboot邮件发送

参照网页	<https://www.cnblogs.com/heqiyoujing/p/9477490.html>

**Controller代码**

```java
@Value("${spring.mail.username}")
private String username;

@RequestMapping("/email")
public String sendSimpleMail() throws Exception {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(username);
        message.setTo("444891953@qq.com");
        message.setSubject("主题：发送邮件");
        message.setText("测试邮件发送成功");
        mailSender.send(message);
        return "success";
}
```

**application.xml配置**

```
#####163邮箱########
spring.mail.host=smtp.163.com
#邮箱账号
spring.mail.username=xxx@163.com
#163邮箱授权码
spring.mail.password=xxx
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
spring.mail.properties.mail.smtp.starttls.required=true
```

