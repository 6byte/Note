## 文件传输

https://www.cnblogs.com/heqiyoujing/p/9477490.htm

### 文件上传

```JAVA
@RequestMapping("/upload")
@ResponseBody
public String upload(MultipartFile MultipartFile) {
        if(StringUtils.isEmpty(MultipartFile)) {
                return "上传失败，请选择文件";
        }
        //指定文件另存为的名字
        String fileName = file.getOriginalFilename();
        //指定文件路径
        String filePath = "F:\\Temp";
        File dest = new File(filePath + fileName);
        try {
                MultipartFile.transferTo(dest);
                return filePath + fileName;
        }
        catch(IOException e) {
        }
        return "失败";
}
```

前端

```JAVA
HTML
//别想了,直接用form
<form action="" method="post" enctype="multipart/form-data">
    //与后端MultipartFile的名字对应
    <input type="file" name="multipartFile" class="custom-file-input" id="user-file">
    <label class="custom-file-label" for="user-file">选择文件</label>

    <div class="input-group-append">
        <button class="btn btn-outline-secondary " type="button" id="upload">上传</button>
    </div>
</form>

JS
$.ajax({
    method: 'POST',
    url: '/user/userFile',
    cache: false,
    data: new FormData($('#uploadForm')[0]),
    processData: false,
    contentType: false,
    success: function(data) {},
    error: function() {}
})
```

### 文件下载

```JAVA
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
    return null
}
```

