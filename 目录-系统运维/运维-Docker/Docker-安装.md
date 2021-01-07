## Docker安装

网页安装		<https://www.zhihu.com/topic/20167613/hot>

```js
--  查看是否已经安装
yum list installed | grep docker

--	安装
yum -y install docker

--	新建daemon.json文件
mkdir /etc/docker

--	编辑docker
cd /etc/docker
vim daemon.json

在文件中添加以下内容
{ "registry-mirrors": ["https://c86u3plt.mirror.aliyuncs.com"]}

--	重启daemon
systemctl daemon-reload

--	重启docker
systemctl restart docker

--  不知道干什么的，装就行
sudo yum install -y yum-utils device-mapper-persistent-data lvm2

--	添加软件源信息
sudo yum-config-manager --add-repo http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo

--	更新并安装 Docker-CE
sudo yum makecache fast
sudo yum -y install docker-ce

--	开启Docker服务
sudo service docker start

--	开机启动
chkconfig docker on 
```



