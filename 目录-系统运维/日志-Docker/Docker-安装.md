## Docker安装

网页安装		https://www.jianshu.com/p/66575c930cf1

网页加速		https://blog.csdn.net/shijiujiu33/article/details/90540031

##### CentOS 7 (使用yum进行安装)



###### step 1:安装阿里镜像源

```js
#1、新建daemon.json文件
mkdir /etc/docker

#2、编辑docker
cd /etc/docker
vim daemon.json
在文件中添加以下内容
{
     "registry-mirrors": ["https://c86u3plt.mirror.aliyuncs.com"]
}
重启daemon
systemctl daemon-reload
重启docker
systemctl restart docker
```

阿里云镜像地址https://cr.console.aliyun.com/cn-hangzhou/instances/mirrors



###### step 2: 安装必要的一些系统工具

```kotlin
sudo yum install -y yum-utils device-mapper-persistent-data lvm2
```



###### Step 3: 添加软件源信息

```csharp
sudo yum-config-manager --add-repo http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
```



###### Step :four: 更新并安装 Docker-CE

```undefined
sudo yum makecache fast
sudo yum -y install docker-ce
```



###### Step 5: 开启Docker服务

```undefined
sudo service docker start
```



###### Step 6:开机启动

```
chkconfig docker on 
```

