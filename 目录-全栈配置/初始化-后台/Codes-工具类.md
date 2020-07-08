### Codes-公用工具

#### Codes-随机数

概览

```
三种创建方式：
第一种：new Random()
第二种：Math.random()
第三种：currentTimeMillis()
```

根据区间返回

```
/*
    * 功能:生成(min,max)之间的随机数
* */
public static long getRandom( int max, int min ) {
    return Math.abs( ( long ) ( Math.random( ) * ( max - min ) + min ) );
}
```

###### 返回不重复数

```
#返回一个不会重复的数
    
public static int getRandomNumber(){
        Random random = new Random( System.currentTimeMillis( ));
        return  Math.abs(random.nextInt());
}
```

#### Codes-ID生成

###### 生成UUID

```JAVA
public static String getId() {
    UUID uuid = UUID.randomUUID();
    return uuid.toString().replace("-" , "");
}
```

生成数字ID

```JAVA
public class getId{
    private static long tmpID = 0;
    private static boolean tmpIDlocked = false;
    public static long getId(){
        long ltime = 0;
        while (true){
            if(tmpIDlocked == false){
                tmpIDlocked = true;
                //当前：（年、月、日、时、分、秒、毫秒）*10000
                ltime = Long.valueOf(new SimpleDateFormat("yyMMddhhmmssSSS").format(new Date()).toString()) * 10000;
                if(tmpID < ltime){
                    tmpID = ltime;
                }
                else{
                    tmpID = tmpID + 1;
                    ltime = tmpID;
                }
                tmpIDlocked = false;
                return ltime;
            }
        }
    }
}
```

#### Codes-远程调用

###### 普通请求

```JAVA
RestTemplate template = new RestTemplate();
String url = "http://localhost:8080/test";
String result = template.postForObject(url , null , String.class);
```

###### 带参请求

```JAVA
RestTemplate template = new RestTemplate();
String url = "http://localhost/";
// 封装参数，千万不要替换为Map与HashMap，否则参数无法传递
MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<String, Object>();
paramMap.add("text" , "test");
String result = template.postForObject(url , paramMap , String.class);
```

#### Codes-格式转换

###### String转Json

```
String addr = JSON.toJSONString(address);
```

###### 时间转化

```JAVA
 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
 String currentTime = sdf.format(new Date());
//结果是:2020-05-29
```

