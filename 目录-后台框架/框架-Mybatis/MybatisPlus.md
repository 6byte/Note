## MybatisPlus

#### 自动填充时间

>数据库:	createTime：类型是datetime

##### java

```JAVA
import java.util.Date;//一定是java.util.Data，否则报错
public class Bean{

	@TableField(fill = FieldFill.INSERT)//开启注解填充条件
	private Date createTime;

}

```

##### MybaitsPlus 配置

```JAVA
import java.util.Date;//一定是这个
@Configuration
public class MybatisConfig implements MetaObjectHandler {

	@Override
	public void insertFill(MetaObject metaObject) {
        //一定是new Date()
		this.setFieldValByName("createTime",new Date(),metaObject);
        this.setFieldValByName("updateTime",new Date(),metaObject);
	}

	@Override
	public void updateFill(MetaObject metaObject) {
		this.setFieldValByName("updateTime", LocalDateTime.now().toString(), metaObject);
	}

}

```





