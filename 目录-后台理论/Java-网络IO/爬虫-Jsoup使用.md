## Jsoup使用

基本使用

```java
 doc = Jsoup.connect("https://github.com/Yooven/Git").get();
//masthead相当于获取Dom||Jquery对象
Elements masthead = doc.select("li:nth-child(2) a.social-count");
if(!StringUtils.isEmpty(masthead) ){
    for(Element index : masthead){
        list.add(masthead.text());
    }
}
```

