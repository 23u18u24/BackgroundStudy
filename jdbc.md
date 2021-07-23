# JDBC

java database connectivity：java语言连接数据库

* jdbc：是一个接口

为什么要面向接口编程：降耦合性（多态）

![image-20210722200333394](C:\Users\wu197\AppData\Roaming\Typora\typora-user-images\image-20210722200333394.png)

该文件称为驱动（由厂家提供（mysql提供的mysql的java.class文件....）），接口的实现类的.class文件

## 1.配置环境变量

文本编辑器下：.;D:\java\intellj\mysql-connector-java-8.0.25\mysql-connector-java-8.0.25.jar

idea有自己的配置方式

## 2.JDBC编程六步

* 1.注册驱动（告诉java程序，即将要连接的是什么数据库）
* 2.获取连接（表示JVM的进程和数据库进程之间的通道打开了，属于进程间的通信，使用完后要关闭通道）
* 3.获取数据库操作对象（专门执行sql语句的对象）
* 4.执行sql语句（DQL、DML等）
* 5.处理查询结果集（只有当第四步执行的是select语句时，才有第五步）
* 6.释放资源（使用完资源之后一定要关闭资源，java和数据库属于进程间的通信，开启之后一定要关闭）

## 3.sql注入

当你输入不正确的密码时也能够成功登录，这种现象称为sql注入

用户：fdsa

密码：fdsa' or '1'='1

* 最根本原因：输入的sql语句中含有关键字，导致判断失败

* 解决方法：使用户提供的信息不参与sql语句的编译过程
  * 使用PreparedStatement（预编译）（是预编译的数据库操作对象）
    * 原理：预先对sql语句的框架进行编译，然后再给sql语句传“值”
  * 将用户输入的信息使用?占位符值且使用connection中的prepareStatement方法
    * ?占位符不能使用单引号括起来

* prepareStatement和Statement区别
  * Statement存在sql注入，prepareStatement解决了sql注入
  * prepareStatement执行效率更高，Statement是编译一次执行一次，而prepareStatement是编译一次，执行N次
  * prepareStatement会编译阶段执行类型的安全检查

## 4.JDBC与事务

jdbc中的事务是自动提交的，只要执行任意一条DML（delete、update、insert）语句，则自动提交一次，这是JDBC默认的事务行为

* 将自动提交事务改成手动提交
  * 在获取到连接后connection.setAutoCommit(false)
  * 在所有语句执行后connection.commit()
  * 在出现异常后connection.rollback()

## 5.行级锁（悲观锁）

```sql
#该sql语句执行出来的记录被锁住，在当前事务没有结果前，其他事务不能对这些记录进行修改
sql语句 for update;
```

```sql
select ename,job,sal from emp where job='MANAGER' for update;
+-------+---------+---------+
| ename | job     | sal     |
+-------+---------+---------+
| JONES | MANAGER | 2975.00 |
| BLAKE | MANAGER | 2850.00 |
| CLARK | MANAGER | 2450.00 |
+-------+---------+---------+
3 rows in set (0.00 sec)
```

* 乐观锁：多线程并发，多事务均可对记录进行修改，但需要一个版本号
* 悲观锁：事务必须排队执行，数据被锁住了，不允许并发

## 6.数据库连接池

提高效率，不再是执行一段sql代码重启一次连接，不再是向系统底层获取连接

* 定义：使用一个容器，里面装入很多连接对象，方便调用，提高调用

* 实现：
  * 标准接口：DataSource
    * 方法
      * 获取连接：getConnection()
      * 归还连接：Connection.close()，不会再关闭而是归还
  * 由数据库厂商实现
* 常见数据库连接池实现
  * C3P0（Tomcat）：数据库连接池技术
  * Druid（阿里巴巴提供）：数据库连接池技术

* C3P0（一直报错，放弃！）

  * 实现步骤：

    * 导入jar包（两个）![](C:\Users\wu197\AppData\Roaming\Typora\typora-user-images\image-20210723201847425.png)![image-20210723201927766](C:\Users\wu197\AppData\Roaming\Typora\typora-user-images\image-20210723201927766.png)

    * 定义配置文件：
      * 名称：c3p0-config.xml
      * 路径：直接将文件放入src目录下

    * 创建核心对象 数据库连接池对象 ComboPoolDataSource
    * 获取连接：getConnection()

* druid

  * 步骤：
    * 导入jar包
    * 配置文件：(尽量放在src文件下)
      * 是properties格式
      * 可以叫任意名称，可以放在任意目录下
    * 获取数据库连接池对象：通过工厂类获取 DruidDataSourceFactory
    * 获取连接：getConnection()

  * 定义工具类
    * 定义一个类JDBCUtils
    * 提供静态代码块加载配置文件，初始化连接池对象
    * 提供方法
      * 获取连接方法：通过数据库连接池获取连接
      * 释放资源
      * 获取连接池的方法