OnlineEducation在线教育后端开发
============================

## 项目简介
OnlineEducation是一个B2C模式的在线教育系统，此部分为系统后端部分，前端部分见[OnlineEducation前端部分](https://github.com/flyerwge/OnlineEducation)。
项目主要实现讲师管理、课程管理等功能，并使用Postman对功能进行基本测试，最终与前端结合使用，前后端传输数据统一封装成json格式。

## 项目技术栈介绍
>* 开发框架使用Spring Boot + Spring Cloud + Mybatis;使用Nginx反向代理配置对应微服务的服务器地址；
>* 存储层使用MySQL数据库对讲师及课程等信息进行管理，使用阿里云对象存储OSS及视频点播管理图片、视频等信息;
>* 使用Nacos对服务进行注册，使用OpenFeign实现服务调用，使用Sentinel实现熔断机制；
>* 使用Maven管理工具对项目进行统一管理，Tomcat作为嵌入式Servlet Web容器；


## 项目模块内容介绍
- service_acl：用户权限管理api接口服务；
- service_cms：前台首页数据banner显示接口服务；
- service_edu：讲师、课程等教学相关api接口服务；
- service_oss：阿里云oss api接口服务；
- service_vod：视频点播api接口服务；