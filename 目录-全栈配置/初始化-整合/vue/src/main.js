import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui';

//样式
import 'element-ui/lib/theme-chalk/index.css';
import 'element-ui/lib/theme-chalk/display.css';
import Vuetify from 'vuetify/lib';

Vue.use(Vuetify);

export default new Vuetify({
});

import axios from 'axios'
import {
	post,
	get,
	patch,
	put
} from '@/assets/js/http.js'


/* 
非关键预设
	名字:Animate CSS 
		类型:CSS样式库
		描述:强大的动效库
		默认:不使用
		加载:
			import animated from 'animate.css'
			Vue.use(animated)
	名字:JWT
		类型:数据传输插件
		描述:用于前后端分离项目
		默认:不使用
		加载:
			import jwt from 'jsonwebtoken'
 */



//定义全局变量
Vue.prototype.$post = post;
Vue.prototype.$get = get;
Vue.prototype.$patch = patch;
Vue.prototype.$put = put;

Vue.use(ElementUI);

Vue.config.productionTip = false


const vuetify = new Vuetify({})
new Vue({
	router,
	render: h => h(App)
	vuetify,
}).$mount('#app')
