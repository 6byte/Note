## SpringBoot文件操作

#### Sboot文件下载

```java
@RequestMapping("/guest/download")
public String downloadFile(HttpServletRequest request,
                           HttpServletResponse response) throws UnsupportedEncodingException {
    
    // 获取指定目录下的第一个文件
    File scFileDir = new File("F:\\temp\\download");
    File TrxFiles[] = scFileDir.listFiles();
    String fileName = TrxFiles[0].getName(); //下载的文件名
    // 如果文件名不为空，则进行下载
    if (fileName != null) {
        //设置文件路径
        String realPath = "F:\\temp\\download\\";
        File file = new File(realPath, fileName);
        
        // 如果文件名存在，则进行下载
        if (file.exists()) {
            
            // 配置文件下载
            response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/octet-stream");
            // 下载文件能正常显示中文
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            
            // 实现文件下载
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                System.out.println("Download the song successfully!");
            }
            catch (Exception e) {
                System.out.println("Download the song failed!");
            }
            finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    return null;
}
```

#### Sboot文件上传

```
@RequestMapping("/upload")
@ResponseBody
public String upload(MultipartFile file) {
        if(StringUtils.isEmpty(file)) {
                return "上传失败，请选择文件";
        }
        //指定文件另存为的名字
        String fileName = file.getOriginalFilename();
        //指定文件路径
        String filePath = "F:\\Temp";
        System.out.println(fileName);
        File dest = new File(filePath + fileName);
        try {
                file.transferTo(dest);
                return filePath + fileName;
        }
        catch(IOException e) {
        }
        return "失败";
}
```

设置上传文件大小

```java
@Component
public class MyExceptionHandler  {
@Bean
public MultipartConfigElement multipartConfigElement() {
MultipartConfigFactory factory = new MultipartConfigFactory();
//单个文件最大
	factory.setMaxFileSize(DataSize.parse("10240MB")); //KB,MB
// 设置总上传数据总大小
	factory.setMaxRequestSize(DataSize.parse("102400MB"));
	return factory.createMultipartConfig();
	}
}

```

对应前端VUE代码

```JS
<template>
  <div>
    <div>
      <div>
        <b-form-file
          v-model="file"
          :state="Boolean(file)"
          placeholder="文件名"
          drop-placeholder="Drop file here..."
          browse-text="选择文件"
        ></b-form-file>
      </div>
      <div class="pt-2">
        <b-button variant="outline-primary" @click="save" size="sm" block>上传文件</b-button>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      file: null,
      host: "http://127.0.0.1:8080/reciveFile"
    };
  },
  methods: {
    save() {
      let formData = new FormData();
      formData.append("file", this.file);
      axios.post(this.host, formData).then(res => {
        console.log(res);
      });
    }
  }
};
</script>

```

