<p align="center">
	<img alt="logo" src="https://oscimg.oschina.net/oscnet/up-d3d0a9303e11d522a06cd263f3079027715.png">
</p>
<h1 align="center" style="margin: 30px 0 30px; font-weight: bold;">RuoYi v3.9.2</h1>
<h4 align="center">企鹅盲盒智能售货机全链路管理系统</h4>

## 项目简介
本项目是**企鹅盲盒智能售货机全链路管理系统的后端服务**，基于 SpringBoot 2.x 构建，采用企业级分层架构设计，为前端提供标准化 RESTful API，实现了用户权限管理、商品全生命周期管理、订单交易处理、库存智能预警、AI 智能客服等核心业务能力。

> 本项目为个人全栈开发项目，面向 Java 后端开发求职场景打造，完整复现了企业级项目的开发规范、架构设计与工程化实践，适配暑期实习校招的能力展示需求。
>
> 配套前端仓库：[帝可得 - 前端项目](https://github.com/what232-star/qie-front)

## 项目核心亮点（求职加分项）
1.  **规范的企业级分层架构**：采用 Controller → Service → Mapper 经典三层架构，职责边界清晰，代码可维护性、可扩展性强
2.  **完整的工程化配套**：统一响应结果封装、全局异常处理、JWT 无状态身份认证、接口权限控制，符合企业开发标准
3.  **业务场景完整**：覆盖从用户登录、商品管理、订单流转、库存监控到智能客服的完整业务闭环，贴合真实商用场景
4.  **性能优化实践**：基于 Redis 实现热点数据缓存、接口限流，针对高频查询场景添加数据库索引，优化接口响应速度
5.  **开发规范严谨**：遵循 Alibaba Java 开发手册，Git 提交采用 Conventional Commits 规范，敏感配置脱敏处理，无冗余代码与无效提交

## 技术选型
| 技术/框架 | 版本 | 核心用途 |
|-----------|------|----------|
| SpringBoot | 2.7.18 | 后端核心框架，自动配置与项目快速构建 |
| MyBatis-Plus | 3.5.3.1 | ORM 框架，简化数据库操作，实现高效 CRUD |
| MySQL | 8.0 | 关系型数据库，存储核心业务数据 |
| Redis | 7.0 | 内存数据库，热点数据缓存、接口限流、分布式锁 |
| JWT | 0.11.5 | 无状态身份认证，实现用户登录与权限控制 |
| Spring Task | 内置 | 定时任务，实现库存自动预警、订单超时自动关闭 |
| Hutool | 5.8.16 | Java 工具库，简化日期、加密、校验等通用操作 |
| Knife4j | 3.0.3 | 基于 Swagger 的接口文档框架，自动生成可视化 API 文档 |
| SLF4J + Logback | 内置 | 日志框架，实现分级日志与日志归档 |

## 核心功能模块
| 模块 | 功能说明 |
|------|----------|
| 用户权限模块 | 实现用户注册、登录、密码加密存储、JWT 令牌签发与刷新；基于角色的权限控制（RBAC），区分管理员/普通用户权限 |
| 商品管理模块 | 商品的新增、编辑、删除、上下架；商品分类管理、库存数量实时更新、商品图片存储管理 |
| 订单管理模块 | 订单创建、支付状态流转、订单详情查询、订单历史记录；订单超时自动取消、交易数据统计 |
| 库存预警模块 | 基于 Spring Task 实现定时任务，实时监控商品库存，低于预设阈值自动触发预警通知 |
| AI 智能客服模块 | 对接通义千问大模型 API，实现智能问答、常见问题自动回复、用户问题分类处理 |
| 系统通用模块 | 统一响应结果封装、全局异常拦截、跨域配置、参数校验、操作日志记录 |

## 项目架构
├── Controller 控制层：接收 HTTP 请求，参数校验，返回统一响应结果
├── Service 服务层：核心业务逻辑实现，事务控制，调用 Mapper 层
│ └── impl 服务实现类：业务逻辑的具体实现
├── Mapper 数据访问层：基于 MyBatis-Plus 实现数据库交互，自定义 SQL 处理
├── Entity 实体层：与数据库表一一对应的实体类
├── DTO 数据传输层：前后端交互的入参 / 出参封装，隔离数据库实体
├── VO 视图层：前端页面展示的视图对象封装
├── Config 配置层：SpringBoot 配置类，Redis、Knife4j、跨域、拦截器等配置
├── Common 通用模块
│ ├── constant 常量定义
│ ├── enums 枚举类（订单状态、响应码等）
│ ├── exception 自定义异常与全局异常处理
│ ├── result 统一响应结果封装
│ └── utils 通用工具类
└── 项目启动类


## 快速开始
### 1. 环境要求
| 环境 | 版本要求 |
|------|----------|
| JDK | 1.8 及以上 |
| Maven | 3.6.0 及以上 |
| MySQL | 8.0 及以上 |
| Redis | 6.0 及以上 |

### 2. 克隆项目
```bash
git clone git@github.com:what232-star/qie-behind.git
cd qie-behind

### 3. 数据库配置
创建数据库
/1.sql
CREATE DATABASE ry-vue CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

/2. 导入项目中的 sql/quartz.sql 及 ry_20260320.sql数据库脚本，完成表结构与基础数据初始化

4. 项目配置
/1复制配置示例文件，生成本地配置
cp src/main/resources/application-example.yml src/main/resources/application.yml
/2.修改 application.yml，填入你的本地环境配置

5. 启动项目

/1 Maven 安装依赖    mvn clean install -Dmaven.test.skip=true
/2 启动项目 mvn spring-boot:run
/3 验证启动成功
项目启动后，访问接口文档地址：http://localhost:8081/api/doc.html
可在线查看所有接口定义、调试接口

项目结构
qie-behind
├── src/main/java/com/mind
│   ├── controller          # 控制层
│   │   ├── ManyController.java
│   │   ├── OrderController.java
│   │   └── AiController.java
│   ├── service             # 服务层接口
│   │   └── impl            # 服务层实现
│   ├── mapper              # 数据访问层
│   ├── entity              # 数据库实体
│   ├── dto                 # 数据传输对象
│   ├── vo                  # 视图对象
│   ├── config              # 配置类
│   ├── common              # 通用模块
│   └── PenguinApplication.java # 项目启动类
├── src/main/resources
│   ├── mapper              # MyBatis XML映射文件
│   └── application.yml     # 本地配置文件（不提交到Git）
├── sql                     # 数据库脚本
├── .gitignore              # Git忽略文件
├── pom.xml                 # Maven依赖配置
└── README.md

配套仓库
前端项目仓库：https://github.com/what232-star/qie-front
后端项目仓库：https://github.com/what232-star/qie-behind

关于作者
GitHub：@what232-star
邮箱：13560250826@163.com
求职意向：Java 后端开发 / 全栈开发 暑期实习






