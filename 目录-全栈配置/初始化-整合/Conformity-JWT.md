## JWT

#### Jwt-JAVA

概览

```
1.根据用户填写的账号密码生成token
2.往token中添加权限等信息
3.返回token
4.vue解析token获取权限信息
```

##### 生成

```JAVA
@Data
public class Jwt {
    /*
     *   功能:设置认证token
     *   参数:
     *       map.id:         登录用户ID
     *       map.subject:    登录用户名
     *       map.author      用户权限信息
     * */
    public String createJwt(HashMap map,Long ttl,String key) {
        try {
//        设置失效时间
            long now = System.currentTimeMillis();
            Long exp = now + ttl;

            JwtBuilder builder = Jwts.builder()
                    .setId(map.get("id").toString())            //设置ID
                    .setSubject(map.get("subject").toString())  //设置用户名
                    .signWith(SignatureAlgorithm.HS256 , key)   // //设置签名
                    .claim("author" , map.get("author"))
                    .setExpiration(new Date(exp));              //设置失效时间
            String token = builder.compact();
            return token;                                       //返回token
        }
        catch (java.lang.Exception e) {
            System.out.println(e);
        }
        return null;
    }
}


```

##### 解析

```JAVA
/*
     * 功能:解析token
     * 参数:客户端传来的token
     * 返回:claims或null	
* */

public Object parseJwt(String token,String key) {
    return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
}
```

#### Vue

获取Token

```vue
//全局导入
import  jwt  from  'jsonwebtoken'
//局部导入
let  jwt = require('jsonwebtoken');
let str =   jwt.decode(data.data) 
```

发送Token

```
配置全局axios请求头

```

存储Token

```

```

#### Axios封装

```JS
import axios from 'axios';
import {
	Message
} from 'element-ui';

axios.defaults.timeout = 5000;
// axios.defaults.baseURL = 'http://127.0.0.1:8090';

if (process.env.NODE_ENV == 'development') {
	axios.defaults.baseURL = 'http://127.0.0.1:8090';
}
//局域网
else if (process.env.NODE_ENV == 'debug') {
	axios.defaults.baseURL = 'http://192.168.0.54:8090';
}
// 外网
else if (process.env.NODE_ENV == 'production') {
	axios.defaults.baseURL = 'http://138.1.1.1:8080';
}
/* 
	功能:
		1.请求拦截器
		2.配置token响应头
 */
axios.interceptors.request.use(
	config => {
		/* 
			使用普通Session
			注意使用的时候需要引入cookie方法，推荐js-cookie
			const token = getCookie('名称');
			const token = JSON.stringify(localStorage['token']);
		 */
		/* 
			功能:配置Jwt请求头 
			
		 */
		// 配置请求头
		const token = localStorage['token']
		if (token) {
			config.headers = {
				'token': token
			}
		}
		return config;
	},
	error => {
		return Promise.reject(err);
	}
);


//响应拦截器
axios.interceptors.response.use(
	response => {
		console.log(response);
		if (response.data.errCode == 2) {
			router.push({
				path: "/login",
				querry: {
					redirect: router.currentRoute.fullPath
				} //从哪个页面跳转
			})
		}
		return response;
	},
	error => {
		return Promise.reject(error)
	}
)


/**
 * 封装get方法
 * @returns {Promise}
 */

export function get(url, params = {}) {
	return new Promise((resolve, reject) => {
		axios.get(url, {
				params: params
			})
			.then(response => {
				resolve(response.data);
			})
			.catch(err => {
				reject(err)
			})
	})
}


/**
 * 封装post请求
 * @returns {Promise}
 */

export function post(url, data = {}) {
	return new Promise((resolve, reject) => {
		axios.post(url, data)
			.then(response => {
				resolve(response.data);
			}, err => {
				reject(err)
			})
	})
}

/**
 * 封装patch请求
 * @returns {Promise}
 */

export function patch(url, data = {}) {
	return new Promise((resolve, reject) => {
		axios.patch(url, data)
			.then(response => {
				resolve(response.data);
			}, err => {
				reject(err)
			})
	})
}

/**
 * 封装put请求
 * @returns {Promise}
 */

export function put(url, data = {}) {
	return new Promise((resolve, reject) => {
		axios.put(url, data)
			.then(response => {
				resolve(response.data);
			}, err => {
				reject(err)
			})
	})
}
```

