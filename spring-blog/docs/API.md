# 博客系统接口文档

## 目录
1. [基础信息](#基础信息)
2. [通用响应格式](#通用响应格式)
3. [用户模块](#用户模块)
4. [博客模块](#博客模块)
5. [建议增加的功能接口](#建议增加的功能接口)

---

## 基础信息

- **Base URL**: `http://127.0.0.1:8080`
- **Content-Type**: `application/x-www-form-urlencoded` (表单提交) / `application/json` (JSON提交)
- **认证方式**: JWT Token，通过请求头 `user_header_token` 传递

---

## 通用响应格式

### 成功响应
```json
{
  "code": "SUCCESS",
  "errMsg": "",
  "data": {}
}
```

### 失败响应
```json
{
  "code": "FAIL",
  "errMsg": "错误信息",
  "data": null
}
```

### 未登录响应
- HTTP Status: 401
- 前端应跳转到登录页面

---

## 用户模块

### 1. 用户登录

**接口说明**: 用户登录，成功后返回JWT token

- **URL**: `/user/login`
- **Method**: POST
- **Auth Required**: 否

**请求参数**:

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| username | string | 是 | 用户名 |
| password | string | 是 | 密码 |

**响应示例**:
```json
{
  "code": "SUCCESS",
  "errMsg": "",
  "data": "eyJhbGciOiJIUzI1NiJ9..."
}
```

**错误情况**:
- 账号或密码不能为空
- 用户不存在
- 密码错误

---

### 2. 获取当前登录用户信息

**接口说明**: 获取当前登录用户的详细信息

- **URL**: `/user/getUserInfo`
- **Method**: GET
- **Auth Required**: 是

**请求头**:
```
user_header_token: {token}
```

**响应示例**:
```json
{
  "code": "SUCCESS",
  "errMsg": "",
  "data": {
    "id": 1,
    "userName": "zhangsan",
    "password": "123456",
    "githubUrl": "https://gitee.com/xxx",
    "deleteFlag": 0,
    "createTime": "2026-03-06 22:15:23",
    "updateTime": "2026-03-06 22:15:23"
  }
}
```

---

## 博客模块

### 1. 获取博客列表

**接口说明**: 获取所有未删除的博客列表

- **URL**: `/blog/getList`
- **Method**: GET
- **Auth Required**: 是

**请求头**:
```
user_header_token: {token}
```

**响应示例**:
```json
{
  "code": "SUCCESS",
  "errMsg": "",
  "data": [
    {
      "id": 1,
      "title": "第一篇博客",
      "content": "博客正文内容...",
      "userId": 1,
      "isLoginUser": true,
      "deleteFlag": 0,
      "createTime": "2026-03-06 22:15",
      "updateTime": "2026-03-06 22:15"
    }
  ]
}
```

---

### 2. 获取博客详情

**接口说明**: 根据博客ID获取博客详情

- **URL**: `/blog/getBlogDetail`
- **Method**: GET
- **Auth Required**: 是

**请求参数**:

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| blogId | int | 是 | 博客ID |

**请求头**:
```
user_header_token: {token}
```

**响应示例**:
```json
{
  "id": 1,
  "title": "第一篇博客",
  "content": "博客正文内容...",
  "userId": 1,
  "isLoginUser": true,
  "deleteFlag": 0,
  "createTime": "2026-03-06 22:15",
  "updateTime": "2026-03-06 22:15"
}
```

---

### 3. 发布博客

**接口说明**: 发布新博客

- **URL**: `/blog/add`
- **Method**: POST
- **Auth Required**: 是
- **Content-Type**: `application/json`

**请求头**:
```
user_header_token: {token}
Content-Type: application/json
```

**请求体**:
```json
{
  "title": "博客标题",
  "content": "博客正文内容（支持Markdown）"
}
```

**响应示例**:
```json
{
  "code": "SUCCESS",
  "errMsg": "",
  "data": true
}
```

**错误情况**:
- 标题或内容不能为空
- 用户未登录

---

### 4. 更新博客

**接口说明**: 更新博客信息

- **URL**: `/blog/update`
- **Method**: POST
- **Auth Required**: 是

**请求参数**:

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| id | int | 是 | 博客ID |
| title | string | 是 | 博客标题 |
| content | string | 是 | 博客内容 |

**请求头**:
```
user_header_token: {token}
```

**响应示例**:
```json
{
  "code": "SUCCESS",
  "errMsg": "",
  "data": true
}
```

---

### 5. 删除博客

**接口说明**: 删除博客（逻辑删除）

- **URL**: `/blog/delete`
- **Method**: POST
- **Auth Required**: 是

**请求参数**:

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| blogId | int | 是 | 博客ID |

**请求头**:
```
user_header_token: {token}
```

**响应示例**:
```json
{
  "code": "SUCCESS",
  "errMsg": "",
  "data": true
}
```

---

## 建议增加的功能接口

### 一、用户模块扩展

#### 1. 用户注册

**接口说明**: 新用户注册

- **URL**: `/user/register`
- **Method**: POST
- **Auth Required**: 否

**请求参数**:

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| username | string | 是 | 用户名（唯一） |
| password | string | 是 | 密码 |
| githubUrl | string | 否 | GitHub地址 |

**响应示例**:
```json
{
  "code": "SUCCESS",
  "errMsg": "",
  "data": {
    "id": 4,
    "userName": "newuser",
    "githubUrl": "https://github.com/newuser"
  }
}
```

---

#### 2. 修改密码

**接口说明**: 修改当前用户密码

- **URL**: `/user/changePassword`
- **Method**: POST
- **Auth Required**: 是

**请求参数**:

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| oldPassword | string | 是 | 旧密码 |
| newPassword | string | 是 | 新密码 |

**响应示例**:
```json
{
  "code": "SUCCESS",
  "errMsg": "",
  "data": true
}
```

---

#### 3. 更新用户信息

**接口说明**: 更新当前用户信息

- **URL**: `/user/updateInfo`
- **Method**: POST
- **Auth Required**: 是

**请求参数**:

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| githubUrl | string | 否 | GitHub地址 |
| avatar | string | 否 | 头像URL |

**响应示例**:
```json
{
  "code": "SUCCESS",
  "errMsg": "",
  "data": true
}
```

---

### 二、博客模块扩展

#### 1. 博客分页查询

**接口说明**: 分页获取博客列表

- **URL**: `/blog/getListByPage`
- **Method**: GET
- **Auth Required**: 是

**请求参数**:

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| pageNum | int | 否 | 页码（默认1） |
| pageSize | int | 否 | 每页数量（默认10） |
| keyword | string | 否 | 搜索关键词（标题/内容） |

**响应示例**:
```json
{
  "code": "SUCCESS",
  "errMsg": "",
  "data": {
    "total": 100,
    "pageNum": 1,
    "pageSize": 10,
    "list": [
      {
        "id": 1,
        "title": "第一篇博客",
        "content": "博客正文...",
        "userId": 1,
        "isLoginUser": true,
        "deleteFlag": 0,
        "createTime": "2026-03-06 22:15",
        "updateTime": "2026-03-06 22:15"
      }
    ]
  }
}
```

---

#### 2. 搜索博客

**接口说明**: 根据关键词搜索博客

- **URL**: `/blog/search`
- **Method**: GET
- **Auth Required**: 是

**请求参数**:

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| keyword | string | 是 | 搜索关键词 |

**响应示例**:
```json
{
  "code": "SUCCESS",
  "errMsg": "",
  "data": [
    {
      "id": 1,
      "title": "第一篇博客",
      "content": "博客正文...",
      "userId": 1,
      "isLoginUser": true,
      "deleteFlag": 0,
      "createTime": "2026-03-06 22:15",
      "updateTime": "2026-03-06 22:15"
    }
  ]
}
```

---

#### 3. 获取我的博客列表

**接口说明**: 获取当前登录用户的所有博客

- **URL**: `/blog/getMyBlogList`
- **Method**: GET
- **Auth Required**: 是

**请求头**:
```
user_header_token: {token}
```

**响应示例**:
```json
{
  "code": "SUCCESS",
  "errMsg": "",
  "data": [
    {
      "id": 1,
      "title": "我的第一篇博客",
      "content": "博客正文...",
      "userId": 1,
      "isLoginUser": true,
      "deleteFlag": 0,
      "createTime": "2026-03-06 22:15",
      "updateTime": "2026-03-06 22:15"
    }
  ]
}
```

---

### 三、评论模块（新增）

#### 1. 获取博客评论列表

**接口说明**: 获取指定博客的所有评论

- **URL**: `/comment/getList`
- **Method**: GET
- **Auth Required**: 否

**请求参数**:

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| blogId | int | 是 | 博客ID |

**响应示例**:
```json
{
  "code": "SUCCESS",
  "errMsg": "",
  "data": [
    {
      "id": 1,
      "blogId": 1,
      "userId": 2,
      "userName": "lisi",
      "content": "评论内容",
      "createTime": "2026-03-06 22:15"
    }
  ]
}
```

---

#### 2. 发表评论

**接口说明**: 对博客发表评论

- **URL**: `/comment/add`
- **Method**: POST
- **Auth Required**: 是

**请求参数**:

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| blogId | int | 是 | 博客ID |
| content | string | 是 | 评论内容 |

**响应示例**:
```json
{
  "code": "SUCCESS",
  "errMsg": "",
  "data": {
    "id": 1,
    "blogId": 1,
    "userId": 1,
    "userName": "zhangsan",
    "content": "评论内容",
    "createTime": "2026-03-06 22:15"
  }
}
```

---

#### 3. 删除评论

**接口说明**: 删除自己的评论

- **URL**: `/comment/delete`
- **Method**: POST
- **Auth Required**: 是

**请求参数**:

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| commentId | int | 是 | 评论ID |

**响应示例**:
```json
{
  "code": "SUCCESS",
  "errMsg": "",
  "data": true
}
```

---

### 四、点赞模块（新增）

#### 1. 点赞/取消点赞

**接口说明**: 对博客进行点赞或取消点赞

- **URL**: `/like/toggle`
- **Method**: POST
- **Auth Required**: 是

**请求参数**:

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| blogId | int | 是 | 博客ID |

**响应示例**:
```json
{
  "code": "SUCCESS",
  "errMsg": "",
  "data": {
    "liked": true,
    "likeCount": 10
  }
}
```

---

#### 2. 获取博客点赞数

**接口说明**: 获取指定博客的点赞数

- **URL**: `/like/getCount`
- **Method**: GET
- **Auth Required**: 否

**请求参数**:

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| blogId | int | 是 | 博客ID |

**响应示例**:
```json
{
  "code": "SUCCESS",
  "errMsg": "",
  "data": 10
}
```

---

#### 3. 检查是否已点赞

**接口说明**: 检查当前用户是否已点赞

- **URL**: `/like/check`
- **Method**: GET
- **Auth Required**: 是

**请求参数**:

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| blogId | int | 是 | 博客ID |

**响应示例**:
```json
{
  "code": "SUCCESS",
  "errMsg": "",
  "data": true
}
```

---

### 五、文件上传模块（新增）

#### 1. 上传图片

**接口说明**: 上传图片（用于博客中的图片）

- **URL**: `/file/uploadImage`
- **Method**: POST
- **Auth Required**: 是
- **Content-Type**: `multipart/form-data`

**请求参数**:

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| file | File | 是 | 图片文件 |

**响应示例**:
```json
{
  "code": "SUCCESS",
  "errMsg": "",
  "data": {
    "url": "/upload/2026/03/08/xxx.jpg"
  }
}
```

---

#### 2. 上传头像

**接口说明**: 上传用户头像

- **URL**: `/file/uploadAvatar`
- **Method**: POST
- **Auth Required**: 是
- **Content-Type**: `multipart/form-data`

**请求参数**:

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| file | File | 是 | 头像图片 |

**响应示例**:
```json
{
  "code": "SUCCESS",
  "errMsg": "",
  "data": {
    "url": "/upload/avatar/xxx.jpg"
  }
}
```

---

## 数据库表结构建议

### 现有表

#### user表
```sql
CREATE TABLE user (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_name VARCHAR(128) NOT NULL UNIQUE,
    password VARCHAR(128) NOT NULL,
    github_url VARCHAR(128),
    delete_flag TINYINT DEFAULT 0,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
```

#### blog表
```sql
CREATE TABLE blog (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    user_id INT NOT NULL,
    delete_flag TINYINT DEFAULT 0,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES user(id)
);
```

### 建议新增表

#### comment表（评论）
```sql
CREATE TABLE comment (
    id INT PRIMARY KEY AUTO_INCREMENT,
    blog_id INT NOT NULL,
    user_id INT NOT NULL,
    content TEXT NOT NULL,
    delete_flag TINYINT DEFAULT 0,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (blog_id) REFERENCES blog(id),
    FOREIGN KEY (user_id) REFERENCES user(id)
);
```

#### like表（点赞）
```sql
CREATE TABLE blog_like (
    id INT PRIMARY KEY AUTO_INCREMENT,
    blog_id INT NOT NULL,
    user_id INT NOT NULL,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    UNIQUE KEY uk_blog_user (blog_id, user_id),
    FOREIGN KEY (blog_id) REFERENCES blog(id),
    FOREIGN KEY (user_id) REFERENCES user(id)
);
```

---

## 开发优先级建议

### 第一阶段（核心功能完善）
1. ✅ 用户登录/注销
2. ✅ 博客CRUD
3. 🔄 博客摘要显示（只显示前200字）
4. 🔄 按时间倒序排列
5. 🔄 内容区域背景优化（提高可读性）

### 第二阶段（用户体验）
1. 用户注册
2. 个人资料编辑
3. 博客搜索
4. 分页功能
5. 加载动画

### 第三阶段（社区功能）
1. 评论系统
2. 点赞功能
3. 阅读量统计
4. 图片上传

### 第四阶段（高级功能）
1. 博客分类/标签
2. 热门博客推荐
3. 用户关注
4. 消息通知

---

*文档生成时间: 2026-03-08*
*版本: v1.0*
