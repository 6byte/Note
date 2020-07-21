### Nginx-备用配置

```JS
pid        logs/nginx.pid;
#必须
events {}

http {
#日志配置
log_format main '$remote_addr---$request_time---$http_user_agent';
# access_log /root/log/access.log main;

limit_req_zone $binary_remote_addr zone=one:10m rate=1r/s;

         upstream mserve {   
              server 127.0.0.1:9098;
              server 127.0.0.1:10713;
              #server 127.0.0.1:3333 backup;  #热备
            }

            server {
            listen       9090;
            server_name  134.175.79.33;

            location / {
            #limit_req zone=one burst=5  nodelay;
            proxy_pass http://mserve;
        }
    }
}

```

