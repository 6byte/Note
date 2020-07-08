<template>
	<div>
		<div class="hljs" v-html="Content"></div>
	</div>
</template>

<script>
	import marked from 'marked'
	import hljs from "highlight.js";
	import javascript from 'highlight.js/lib/languages/javascript';
	import 'highlight.js/styles/monokai-sublime.css';
	export default {
		mounted: function() {
			//从主机下载md文件
			
			//配置Marked，最好配置上
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
			});

		},
		//必须
		computed: {
			Content: function() {
				//处理markdown数据，data为markdown文件读出的字符串
				return marked(this.content || '', {
					sanitize: true
				})
			}
		},
	}
</script>

<style scoped>
	/* @import url(''); */
</style>
