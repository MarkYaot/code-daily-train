### 目标
支持华为云OBS SDK的简易对象存储服务

### 代码结构
server.go：服务进程，服务生命周期管理
store.go：底层存储接口，使用本地文件系统
api.go：外部API接口，支持REST API访问方式

### API
- GET /BucketName 获取桶列表
- GET /BucketName/ObjectName 查询桶中对象列表
- DELETE /BucketName 删除桶
- POST /BucketName/ObjectName 上传对象
- GET /BucketName/ObjectName 查询对象内容
- DELETE /BucketName/ObjectName 删除对象