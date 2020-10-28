### Vue渲染Markdown

https://blog.csdn.net/catgray/article/details/95728375

安装

``` js
yarn add marked -S
yarn add highlight.js -S

npm install marked --save
npm install highlight.js --save
```



代码

```VUE
<template>
  <div>
    <div class="hljs" ref="hlDiv" v-html="code"></div>
  </div>
</template>

<script>
  import marked from 'marked'
  import hljs from "highlight.js";
  import 'highlight.js/styles/monokai-sublime.css';

  export default {
    name: "height",
    data(){
      return {
        code:'```javascript\nfunction(){\n\tconsole.log(123)\n}\n```'
      }
    },
    mounted(){
      marked.setOptions({
          renderer: new marked.Renderer(),
          highlight: function(code) {
            return hljs.highlightAuto(code).value;
          },
          pedantic: false,
          gfm: true,
          tables: true,
          breaks: false,
          sanitize: false,
          smartLists: true,
          smartypants: false,
          xhtml: false
        }
      );
      this.code = marked(this.code)
    },
  }
</script>
```

### Vuetify经典布局

```JS
<template>
	<div>
	<v-app>
		<v-container class="pa-0" fluid>
			<v-row no-gutters>
				<v-col>
					<v-app-bar color="deep-purple" dark>
						<v-app-bar-nav-icon @click="drawer = true"></v-app-bar-nav-icon>
		
						<v-toolbar-title>Title</v-toolbar-title>
					</v-app-bar>
		
					<v-navigation-drawer v-model="drawer" absolute temporary>
						<v-list nav dense>
							<v-list-item-group active-class="deep-purple--text text--accent-4">
								<v-list-item>
									<v-list-item-icon>
										<v-icon>mdi-home</v-icon>
									</v-list-item-icon>
									<v-list-item-title>项目一</v-list-item-title>
								</v-list-item>
		
								<v-list-item>
									<v-list-item-icon>
										<v-icon>mdi-account</v-icon>
									</v-list-item-icon>
									<v-list-item-title>项目二</v-list-item-title>
								</v-list-item>
		
							</v-list-item-group>
						</v-list>
						
					</v-navigation-drawer>
					<div class="pt-16">
						<router-view></router-view>
					</div>
				</v-col>
			</v-row>
		
		</v-container>
	</v-app>
	</div>
</template>

<script>
	export default {
		data: () => ({
			drawer: false,
		}),
	}
</script>
			
```

### Editor使用

```VUE
<template>
	<div class="home">
		<div id="demo1"></div>
		<button type="button" @click="sendContent">提交内容</button>
		</div>
	</div>
</template>

<script>
	// 引入 wangEditor
	import hljs from 'highlight.js'
	import wangEditor from 'wangeditor'
	import 'highlight.js/styles/arta.css'
	export default {
		data() {
			return {
				editor: null,//需要将edito实例挂到data中，全局调用
				editorData: '',
				content: []
			}
		},
		mounted() {
			const editor = new wangEditor(`#demo1`)
			// 配置 onchange 回调函数，将数据同步到 vue 中
			editor.config.onchange = (newHtml) => {
				this.editorData = newHtml
			}
			// 创建编辑器
			editor.highlight = hljs
			editor.create()
			this.editor = editor
			
			
		},
		beforeDestroy() {
			// 调用销毁 API 对当前编辑器实例进行销毁
			this.editor.destroy()
			this.editor = null
		}
	}
</script>

```

