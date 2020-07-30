#### Vue WebSockt

```js
<template>
    <div>
		<input type="text" v-model="msg">
        <button @click="send">发消息</button>
		<div v-for="(i,e) in msgList">
			<li v-text="i"></li>
		</div>
    </div>
</template>
<script>
export default {
    data () {
        return {
            path:'ws://172.16.22.1:10022/talk/456',
            socket:"",
			msgList:new Array(),
			msg:''
        }
    },
    mounted () {
        // 初始化
        this.init()
    },
    methods: {
        init: function () {
            if(typeof(WebSocket) === "undefined"){
                alert("您的浏览器不支持socket")
            }else{
                // 实例化socket
                this.socket = new WebSocket(this.path)
                // 监听socket连接
                this.socket.onopen = this.open
                // 监听socket错误信息
                this.socket.onerror = this.error
                // 监听socket消息
                this.socket.onmessage = this.getMessage
            }
        },
        open: function () {
            console.log("socket连接成功")
        },
        error: function () {
            console.log("连接错误")
        },
        getMessage: function (msg) {
			console.log();
			this.msgList.push(msg.data)
			console.log(this.msgList);
        },
        send: function () {
            this.socket.send(this.msg)
        },
        close: function () {
            console.log("socket已经关闭")
        }
    },
    destroyed () {
        // 销毁监听
        this.socket.onclose = this.close
    }
}
</script>
<style>
</style>
```

