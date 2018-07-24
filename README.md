# 项目风险计划系统

## 介绍：
项目通过协调项目群中的项目，运用遗传算法规划最优的应对风险策略集合方案，实现项目的风险应对计划。

## 项目技术
采用JavaEE软件架构，采用SPRING MVC技术和B/S开发模式来实现，数据库选用关系数据库PostgreSQL完成数据存储。

## 主要实现以下功能：
1. 项目群初始化参数设置——约束条件（子项目名称、WBS工作包）
2. 风险预先规划——风险清单、风险预算、储备资源种类与数量、风险类型、排优条件
3. 风险应对规划——风险规划应对策略优化
## 具体包含模块可分类如下：
1）系统管理：角色信息、用户信息
2）风险事件管理：风险事件信息、快速新建实例
3）网络节点管理：风险清单项、风险应对策略；
4）可调度资源与风险预算管理：可调度资源管理、风险预算管理；
5）WBS工作包管理：WBS工作包信息；
6）风险影响因子管理；
7）调度方案管理：调度方案信息。

## 登录过程
本软件为在Tomcat服务器中运行的Web应用程序，推荐使用CHROME或FIREFOX浏览器打开以期获得最优体验。
在浏览器地址栏输：http://localhost:8080/risk/login，进入登录界面。

## 软件开发环境如下： 
（1）操作系统：windows 7 64bit intel i5 3.3GHz RAM:4GB
（2）数据库：PostgreSQL 9.1
（3）WEB系统开发的IDE:My Eclipse 9.0
（4）服务器软件：Apache Tomcat 7.0
（5）后台算法开发的IDE: My Eclipse 9.0
（6）主要使用到的框架技术或API服务：SPRING MVC 4.0，JSP 3.0

