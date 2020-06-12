## Codes-框架工具

### Codes-Shiro

###### Shiro-登录处理

```java
@RestController
public class Login {
    //进行密码检查后才校验用户状态，否则可能泄露信息
    @RequestMapping("/login")
    public void index(User user) {
        //获取登录主体
        Subject subject = SecurityUtils.getSubject();
        //        开启验证
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername() , user.getPassword());
        /** 无异常    =      成功
             有异常    =      失败
         **/
        try {
            subject.login(token);
            //如果不为空，用户存在，进行逻辑处理
            if (!StringUtils.isEmpty(user)) {}
        }
        // 账号被锁定
        catch (LockedAccountException e) {}
        //未知账号异常
        catch (UnknownAccountException e) {}
        //凭证过期
        catch (ExpiredCredentialsException e) {}
        //多个用户同时登录
        catch (ConcurrentAccessException e) {}
        //认证次数超过限制
        catch (ExcessiveAttemptsException e) {}
        //黑名单账号登录
        catch (DisabledAccountException e) {}
        //不允许资源访问
        catch (UnauthorizedException e) {}
        //未认证就授权
        catch (UnauthenticatedException e) {}
        // 密码错误
        catch (IncorrectCredentialsException e) {}
    }
}

```

###### Shiro-账号注册

```java
(1):请求
@RequestMapping("/add")
 public void insert(User user) {
    setPassword(user);
}

(2):密码加密
public void setPassword(User user) {
    //生成盐值
    String salt = new SecureRandomNumberGenerator().nextBytes().toHex();
    //从浏览器获取密码
    String password = user.getPassword();
    //对密码进行加密
    String ciphertext = new Md5Hash(password, salt, 3).toString();
    //类型转换
    ByteSource byteSalt = ByteSource.Util.bytes(ciphertext);
    //将加密后的盐值和密码插入数据库
    user.setSalt(String.valueOf(salt));
    user.setPassword(ciphertext);
    //在Dao中有对应的SQL语句
    Dao层.insertOne(user);
}
}
```

###### Shiro-身份校验

```JAVA
AuthenticationInfo 类

@Override
protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
    String username = (String) token.getPrincipal();
    User user = userDao.findByName(username);
    /*判断用户名是否存在*/
    if(StringUtils.isEmpty(user)) {
        return null;
    }
    String password = user.getPassword();
    
//    盐值是ByteSource类型，
    ByteSource salt = ByteSource.Util.bytes(user.getSalt());
    /*
    第一个参数：返回给shiro的参数，
    第二个参数：密码，
    第三个参数: 盐值
    第四个参数：reaml的名字，在配置多个reaml时有用
    */
    return new SimpleAuthenticationInfo(username, password, salt,"");
}
```

###### Shiro-全局Config

```JAVA
//需要单独导入这个包，否则报错

import org.apache.shiro.mgt.SecurityManager;
@Component  //必须配置该注解
public class ShiroConfig {

    //创建ShiroFilterFactoryBean
    @Bean(name = "shiroFilterFactoryBean")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager) {

        ShiroFilterFactoryBean
                shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        Map<String, String> prevent = new LinkedHashMap<>();
        prevent.put("/" , "anon");
        prevent.put("/public/**" , "anon");
        prevent.put("/login/logout" , "logout");//退出
        prevent.put("/**" , "authc");//一定要写在最下面，避免出现不可预知的问题

        shiroFilterFactoryBean.setLoginUrl("/");//登录拦截返回页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/");//授权拦截返回页面
        shiroFilterFactoryBean.setSuccessUrl("/");  //登录成功需要访问的页面
        shiroFilterFactoryBean.setFilterChainDefinitionMap(prevent);
        return shiroFilterFactoryBean;
    }

    @Bean(name = "defaultWebSecurityManager")
//创建DefaultWebSecurityManager
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("realmConfig") Realm realm) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(realm);
        return defaultWebSecurityManager;
    }

    //创建Realm==此处需要修改，改成自定义的Realm
    @Bean(name = "realmConfig")
    public Realm getUserRealm() {
        return new Realm();
    }

    /**
     * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类
     * 需要配置以下两个Bean
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    /**
     * 开启aop注解支持
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}

```

###### Shiro-Realm

```JAVA
需要继承AuthorizingRealm
public class Realm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        return null;
    }
}
```



### Codes-MybatisPlus

###### 代码生成

```JAVA
public class MpGenerator {
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入 包名：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + someThing + "！");
    }

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor(创建者ID);
        gc.setOpen(false);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        //配置数据库，和application.properties保持一样即可
        //一定要该，不然建空文件夹
        dsc.setUrl(jdbc:mysql://localhost:3306/test?characterEncoding=utf8&serverTimezone=UTC);
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername(数据库账号);
        dsc.setPassword(数据库密码);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        //配置包名，创建的所有文件都会放在这个包下
        //如果该包不存在，就创建
        pc.setModuleName(scanner(包名));
        //将要创建的文件夹名字
        pc.setModuleName(Folder);
        //配置根目录包，生成文件会放在com.m.alpha.next.Folder下
        pc.setParent(根目录包);
        mpg.setPackageInfo(pc);


        // 如果模板引擎是 freemarker
	    //String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
         String templatePath = "/templates/mapper.xml.vm";
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapper/" + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        // 写于父类中的公共字段
        strategy.setSuperEntityColumns("id");
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.setStrategy(strategy);
        mpg.execute();
    }
}
```



### Codes-ALiYun

###### ALi-基础信息

```java
//注意检查此处信息
    static String endpoint = "http://oss-cn-**.***.com";
    static String accessKeyId = "***";
    static String accessKeySecret = "***";
    static String bucketName = "sixbyte";
    static OSS ossClient = getOssClient();

    public static OSS getOssClient() {
        return new OSSClientBuilder().build(endpoint , accessKeyId , accessKeySecret);
    }
```



##### 文件上传

###### ALi-本地文件

```java
/*
   上传文本文件
   filePath：仅需要本地文件路径
* */
public static void uploadFile(String filePath) {
    PutObjectRequest putObjectRequest = null;
    try {

        File file = new File(filePath);
        if (!StringUtils.isEmpty(null) ) {
            String name = file.getName();
            //            new PutObjectRequest("bucket名字 , 在云端文件的名字 , 文件对象);
            putObjectRequest = new PutObjectRequest(bucketName , name , file);
            ossClient.putObject(putObjectRequest);
        }
    }
    catch (Exception e) {
        e.printStackTrace();
    }

}
```

###### ALi-网络路径

```JAVA
    /*上传网络文件到云端
     	url:一个网络路径
     	fileName:OSS存储文件名
     * */
    public static void uploadNetFile(String url,String fileName) {
// 上传网络流。
        InputStream inputStream = null;
        try {
            if (!StringUtils.isEmpty(url)) {
                inputStream = new URL(url).openStream();
                ossClient.putObject(bucketName , fileName , inputStream);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
```

###### ALi-断点续传

```java
    public static void uploadPartFile(String fileName , String path,String backupFile) {

        ObjectMetadata meta = new ObjectMetadata();
// 指定上传的内容类型。
        meta.setContentType("text/plain");

// 通过UploadFileRequest设置多个参数。
        UploadFileRequest uploadFileRequest = new UploadFileRequest(bucketName , fileName);

        uploadFileRequest.setUploadFile(path + fileName);
// 指定上传并发线程数，默认为1。
        uploadFileRequest.setTaskNum(5);
// 指定上传的分片大小，范围为100KB~5GB，默认为文件大小/10000。
        uploadFileRequest.setPartSize(1 * 1024 * 1024);
// 开启断点续传，默认关闭。
        uploadFileRequest.setEnableCheckpoint(false);
// 上传结果文件。如果上传失败，再上传根据文件中记录的点继续上传。
// 上传完成后，该文件会被删除。默认与待上传的本地文件同目录，为uploadFile.ucp。
        uploadFileRequest.setCheckpointFile(backupFile);
// 文件的元数据。
        uploadFileRequest.setObjectMetadata(meta);
// 设置上传成功回调，参数为Callback类型。
        uploadFileRequest.setCallback(null);
// 断点续传上传。
        try {
            ossClient.uploadFile(uploadFileRequest);
        }
        catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

```

##### 文件下载

###### ALi-文件下载

```java
/*
     * 下载文件
     * path:需要保存到的路径
     * fileName:保存到云端的文件名
* */
public static void downloadFile(String path,String fielName) {
//    判断文件是否存在
    if (isExist(fielName)) {
//   new GetObjectRequest(bucket名字, 云端文件名), 本地文件对象);
        ossClient.getObject(new GetObjectRequest(bucketName , fielName) , new File(path));
    }
}
```

##### 文件管理

###### ALi-检查文件

```JAVA
/*
* 判断文件是否存在
* */
public static boolean isExist(String objectName) {
	return ossClient.doesObjectExist(bucketName , objectName);
}
```

###### ALi-权限

```java
/*
  * 设置权限
  * CannedAccessControlList的值
            Private：私有
            PublicRead：公共读
            PublicReadWrite：公共读写
  * 简单调用ControlList.Private
* */

public static Boolean setAuthority(String fileName , CannedAccessControlList ControlList) {
    try {
        ossClient.setObjectAcl(bucketName , fileName , ControlList);
    }
    catch (Exception e) {
        return false;
    }
    return true;
}

调用
setAuthority("filname.x" , CannedAccessControlList.Private);
```

###### ALi-普通列举

```java
/*
     * 列举文件
     * */
public static LinkedList findFileList(int maxKeys ) {
    // 列举文件。 如果不设置KeyPrefix，则列举存储空间下所有的文件。KeyPrefix，则列举包含指定前缀的文件。
    if(!StringUtils.isEmpty(maxKeys)){
        //            默认100条
        maxKeys = 100;
    }
    LinkedList fileList = new LinkedList();
    // 列举文件。
    ObjectListing objectListing = ossClient.listObjects(new ListObjectsRequest(bucketName).withMaxKeys(maxKeys));
    List<OSSObjectSummary> sums = objectListing.getObjectSummaries();
    for (OSSObjectSummary s : sums) {
        fileList.add(s.getKey());
    }
    return fileList;
}

```

###### ALi-例举前缀

```JAVA
keyPrefix:文件前缀名
RETURN :一个OSSObjectSummary List列表

public static LinkedList prefixList(String keyPrefix) {
    // 列举包含指定前缀的文件。默认列举100个文件。
    LinkedList<OSSObjectSummary> linkedList = new LinkedList();
    ObjectListing objectListing = ossClient.listObjects(new ListObjectsRequest(bucketName).withPrefix(keyPrefix));
    List<OSSObjectSummary> sums = objectListing.getObjectSummaries();
    for (OSSObjectSummary s : sums) {
        linkedList.add(s);
    }
    return linkedList;
}
```

###### ALi-分页列举

```java
//    nextMarker:以文件名作为分页标记，需要前端返回一个文件名
    public static void pageFile(String nextMarker) {
        final int maxKeys = 50;
        ObjectListing objectListing;
        do {
            objectListing = ossClient.listObjects(new ListObjectsRequest(bucketName).withMarker(nextMarker).withMaxKeys(maxKeys));
            List<OSSObjectSummary> sums = objectListing.getObjectSummaries();
            for (OSSObjectSummary s : sums) {
                System.out.println("\t" + s.getKey());
            }
            nextMarker = objectListing.getNextMarker();
        }
        while (objectListing.isTruncated());
    }
```

###### ALi-指定文件夹

```java
//第一值时文件夹的名字，根据需要移除
public static LinkedList fileFolder(String fileFolder) {
    LinkedList<String> result = new LinkedList();
    // 构造ListObjectsRequest请求。
    ListObjectsRequest listObjectsRequest = new ListObjectsRequest(bucketName);
    // 设置prefix参数来获取fun目录下的所有文件。
    listObjectsRequest.setPrefix(fileFolder + "/");

    // 递归列出fun目录下的所有文件。
    ObjectListing listing = ossClient.listObjects(listObjectsRequest);

    // 遍历所有文件。
    System.out.println("Objects:");
    for (OSSObjectSummary objectSummary : listing.getObjectSummaries()) {
        result.add(objectSummary.getKey());
    }
    return result;
}
```

###### ALi-单文件大小

```JAVA
// 获取bucket下某个文件夹大小。
private static long calcFolderLength(String fileName) {
    long size = 0L;
    ObjectListing objectListing = null;
    do {
        // MaxKey默认值为100，最大值为1000。
        ListObjectsRequest request = new ListObjectsRequest(bucketName).withPrefix(fileName).withMaxKeys(1000);
        if (objectListing != null) {
            request.setMarker(objectListing.getNextMarker());
        }
        objectListing = ossClient.listObjects(request);
        List<OSSObjectSummary> sums = objectListing.getObjectSummaries();
        for (OSSObjectSummary s : sums) {
            size += s.getSize();
        }
    } while (objectListing.isTruncated());
    return size;
}
```

###### ALi-多文件大小

```JAVA
 mfolder:指定文件夹
 keyPrefix：指定文件夹前缀
public static Map Calculate(String mfolder, String keyPrefix) {
            Map info = new HashMap();

        // 指定前缀，若希望遍历主目录文件夹，则将该值置空。
        ObjectListing objectListing = null;
        do {
            // 默认情况下，每次列举100个文件或目录。
            ListObjectsRequest request = new ListObjectsRequest(bucketName).withDelimiter("/").withPrefix(keyPrefix);
            if (objectListing != null) {
                request.setMarker(objectListing.getNextMarker());
            }
            objectListing = ossClient.listObjects(request);
            List<String> folders = objectListing.getCommonPrefixes();
            for (String folder : folders) {
                info.put("folderSize",calcFolderLength(mfolder) / 1024+"KB");
            }
            List<OSSObjectSummary> sums = objectListing.getObjectSummaries();
            for (OSSObjectSummary s : sums) {
                info.put(s.getKey(),(s.getSize() / 1024) + "KB");
            }
        } while (objectListing.isTruncated());
        return info;
    }
```

##### 删除文件

###### ALi-删单文件

```
    /*
     * 
     * */
    public static void deleteFile(String objectName) {
        ossClient.deleteObject(bucketName , objectName);
    }

```

