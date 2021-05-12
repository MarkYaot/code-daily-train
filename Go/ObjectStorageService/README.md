### 目标
支持REST API访问的简易对象存储服务

### 代码结构
server.go：服务进程，服务生命周期管理
api.go：外部API接口，支持REST API访问方式

### API
- GET /BucketName 查询桶中对象列表
- GET /BucketName/ObjectName 查询对象
- PUT /BucketName/ObjectName 上传对象