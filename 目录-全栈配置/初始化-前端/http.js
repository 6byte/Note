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
 * @param url
 * @param data
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
 * @param url
 * @param data
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
 * @param url
 * @param data
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
 * @param url
 * @param data
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
