# 一、计网

## 1.网络编程入门

### 1.1软件结构

* C/S结构：Client/Server结构，客户端和服务器结构
* B/S结构：Browser/Server结构，浏览器和服务器结构

均需要网络的支持，称为网络编程

* 网络编程：在一定的协议下，实现两台计算机的通信的程序

### 1.2网络通信协议

* 网络通信协议：通过计算机网络可使多台计算机实现连接和通信，但他们都需要遵守一定的规则，这个规则就称为网络通信协议。在计算机网络中，这些连接和通信的规则称为网络通信协议，它对数据的传输格式、传输速率、传输步骤等做了统一规定，通信双方必须同时遵守才能完成数据的交换
* TCP/IP协议：传输控制协议/因特网互联协议（Transmission Control Protocol/Internet Protocol），是最基本的协议，它定义了计算机如何连入因特网，以及数据如何在它们之间传输的标准。采用4层分层模型，每一层都呼叫它的下一层所提供的协议来完成自己的需求。

![image-20210707002249742](C:\Users\wu197\AppData\Roaming\Typora\typora-user-images\image-20210707002249742.png)

物理层：物理上的概念如网线

链路层：网络链接设备的驱动协议

网络层：核心，将传输数据分组，将分组数据发送到目标计算机或网络

传输层：进行通信（TCP或UDP）

应用层：应用程序的协议（HTTP、FTP等）

### 1.3协议分类

数据报（Datagram）：网络传输的基本单位

* UDP：用户数据报协议（User Datagram Protocol），面向无连接的通信协议，即传输数据时，数据的发送端和接收端不建立逻辑连接，简而言之，在两台电脑传输数据时，发送端并不会确认接收端是否存在，而是直接发送数据，同样接收端也不会确认发送端是否存在，只是接收数据。

  * 优点：消耗资源小，通信效率高，常用于音频、视频和普通数据的传输如视频会议就使用UDP协议。尽管UDP协议可能会丢失一些数据，但也不会对接收结果产生很大的影响
  * 缺点：可能会丢失数据（丢包），在传输重要数据时，不建议使用UDP协议
  * 特点：数据大小限制在64KB内

* TCP：传输控制协议（Transmission Control Protocol）。面向连接的通信协议，即传输前，先在发送端和接收端建立逻辑连接，然后再传输数据，它提供了两台计算机间的完整数据传输

  * 注意：必须明确客户端和服务器端，客户端向服务器建立连接请求，都需要经过“三次握手”

    * 1.客户端向服务器端发出连接请求，等待服务器确认
    * 2.服务器端向客户端回送响应，通知客户端收到了连接请求
    * 3.客户端再次向服务器发送确认信息，确认连接

    客问服务器可以连接嘛？服务器回应，客再次问服务器确定嘛？后确认连接

  * 特性：“三次握手”，连接建立后，客户端和服务器就可以进行数据传输，这样可以使传输数据更安全

### 1.4网络编程三要素

* 协议：计算机网络通信必须遵守的规则

* IP地址：互联网协议地址（Internet Protocol Address），相对于电话号码，便于通信和连接

  * 分类：
    * IPv4：32位的二进制数，常被分为4个字节，表示为a.b.c.d形式，如192.168.65.100。其中abcd都是0~255间的十进制整数
    * IPv6：由于IPv4的地址快用完了，所以扩张为IPv6地址，采用128为地址长度，每16个字节一组，分成8组，表示为ABCD:EF04:2345:6789:ABCD:EF01:2345:6789
  * 查看本机IP

  ```cmd
  ipconfig
  ```

  * 检查网络是否连通

  ```cmd
  ping ip地址
  ```

  * 本机IP：*127.0.0.1*、*localhost*

* 端口号：传输数据到另一台电脑时，需要区分谁要使用这个数据，所以就有了端口号来区分

  * 逻辑端口，无法直接看到，可以使用一些软件查看
  * 当我们使用网络软件一打开，操作系统便会为网络软件分配一个随机端口号或网络软件在打开时和系统要指定的端口号
  * 由2个字节组成，取值范围为0~65535（1个字节=2个bit）
  * 注意：1024之前的端口号不能使用，因为已经分配给已知网络软件了，且网络软件的端口号不能重复
  * 常用端口：
    * 80端口（网络端口）eg：www.baidu.com:80（不加一般时默认为80端口）
    * 数据库端口号
      * mysql：3306
      * oracle：1521
    * Tomcat服务器：8080

使用IP地址加端口号就可以保证数据准确无误的发送到对方计算机的指定软件上

## 2.TCP通信程序

### 2.1概述

* TCP协议要严格区分客户端（Client）和服务器端（Server）
* 通信步骤：
  * 服务器端程序，需要事先启动，等待客户端的连接
  * 客户端主动连接服务器端，连接成功才能通信。服务器端不能主动连接客户端
  * 等待客户端请求，服务器端和客户端“三次握手”后，就会和客户端便会建立一个逻辑连接，这个连接中包含一个IO对象，客户端和服务器端就是使用IO对象进行通信，因为通信的对象不仅仅是字符，所以IO对象是字节流对象
* 注意（关于服务器端）：
  * 多个客户端同时和服务器端进行交互，服务器端必须明确对象，所以有一个accept方法可以获取到请求客户端对象
  * 多个客户端同时和服务器端进行交互，就需要使用多个IO流对象；但实际上是服务器端（无IO流）使用客户端的IO对象来传输数据，这样就可以提高效率

在java中实现

* 客户端：Socket类，创建Socket对象，向服务器发送连接请求，服务器端响应请求，两者建立连接开始通信
* 服务器端：ServerSocket类，创建ServerSocket对象，相对于开启一个服务，并等待客户端的连接

### 2.2Socket与ServerSocket类

补充：套接字：两台机器间通信的端点，包含了IP地址和端口号的网络单位

* 本地文件与客户端或服务器的交互需要使用字节流而不是Socket中的字节流

* read方法，如果没有输入可用，则会阻塞

### 3.实战

* CS：https://www.bilibili.com/video/BV1T7411m7Ta?p=406
* BS：https://www.bilibili.com/video/BV1T7411m7Ta?p=411



# 二、Mysql学习

https://www.bilibili.com/video/BV1H64y1U7GJ?p=4&t=496

## 1.基本命令

* 连接mysql

```cmd
mysql -h 主机名 -P 端口号 -u 用户名 -p密码
mysql -h localhost -P 3306 -u root -p
mysql -uroot -p
mysql --host=主机名 --user=用户名 --password=密码
```

* 退出->exit

mysql密码：wuruxin

打开服务窗口servies.msc（手动）

![image-20210704020825455](C:\Users\wu197\AppData\Roaming\Typora\typora-user-images\image-20210704020825455.png)

* 停止mysql服务

![image-20210704021409294](C:\Users\wu197\AppData\Roaming\Typora\typora-user-images\image-20210704021409294.png)

```cmd
net stop mysql
```

* 启动mysql服务

```cmd
net start mysql
```

![image-20210704021530139](C:\Users\wu197\AppData\Roaming\Typora\typora-user-images\image-20210704021530139.png)

## 2.mysql目录结构

* 安装目录：D:\mysql\mysql-5.7.19-winx64

my.ini------配置文件，通过此文件修改配置

* 数据目录：D:\mysql\mysql-5.7.19-winx64\data

mysql服务器是指mysql软件所在的那台计算机

数据库：文件夹

表：文件

一个数据库可以存放多个表，表就是文件，一个表可以存放多个数据记录

## 3.SQL（Structured Query Language）

### 3.1概念

Structured Query Language：结构化查询语言

SQL其实就是定义了操作所有关系型数据库的规则，每一种数据库的操作方式存在不一样的地方，称为”方言“

### 3.2通用语法

* 以分号结尾，可以单行或多行书写

  * 查看数据库：show databases;

* 不区分大小写，关键字建议使用大写

* 注释

  * 单行注释      
    * -- 注释内容
    *  \# 注释内容

  * 多行注释
    * /* 注释 */

### 3.3分类

* DDL（Data Definition Language）数据定义语言：操作数据库、表
* DML（Data Manipulation Language）数据操作语言：操作表中记录（数据），增删改数据
* DQL（Data Query Language）数据查询语言：操作表中记录，查询数据
* DCL（Data Control Language）数据控制语言：授权，是否有权限操作数据库和表

#### 3.3.1DDL：操作数据库和表

* 操作数据库：CRUD

  * C（Create）：创建
    * 创建数据库：CREATE DATABASE 数据库名称;
      * 判断是否存在这个数据库：CREATE DATABASE IF NOT EXISTS 数据库名称;
      * 指定默认字符集：CREATE DATABASE 数据库名称 CHARACTER SET 字符集;
      * 即判断是否存在又指定字符集：CREATE DATABASE IF NOT EXSITS 数据库名称 CHARACTER SET GBK;
  * R（Retrieve）：查询
    * 查询所有数据库的名称：SHOW DATABASES;
    * 查询对应数据库的字符集（查询数据库的创建语句）：SHOW CREATE DATABASE 数据库名称;
  * U（Update）：修改
    * 修改数据库的字符集：ALTER DATABASE 数据库名称 CHARACTER SET 字符集;
    * 判断存在再删：DROP DATABASE IF EXISTS 数据库名称;
  * D（Delete）：删除
    * 删除数据库：DROP DATABASE 数据库名称;
  * 使用数据库（进入数据库或退出数据库）
    * 查询当前正在使用的数据库名称：SELECT DATABASE();
    * 进入（使用）数据库：USE 数据库名称;

* 操作表CRUD

  * C（Create）：创建

    * CREATE TABLE 表名(

      ​			列名1 数据类型1,

      ​			列名2 数据类型2，

      ​			.....

      ​			列名n 数据类型n

      );

    * 数据类型：

      * int：整数类型：age int,
      * double：小数类型，score double(5, 2);
      * date：日期，只包含年月日，yyyy-MM-dd
      * datetime：日期，包含年月日时分秒，yyyy-MM-dd HH:mm:ss
      * timestamp：时间戳类型 包含年月日时分秒，yyyy-MM-dd HH:mm:ss（注意，如果不给这个字段赋值，或赋值为null，则默认使用当前的系统时间，来自动赋值）
      * varchar：字符串类型，name varchar(20);#姓名最大20个字符

    * CREATE TABLE STUDENT (

      ​	ID INT,

      ​	NAME VARCHAR(32),

      ​	AGE INT,

      ​	SCORE DOUBLE(4,1),

      ​	BIRTHDAY DATE,

      ​	INSERT_TIME TIMESTAMP

      );

  * R（Retrieve）：查询

    * 查询某个数据库中所有表名称：SHOW TABLES;
    * 查询表结构：DESC 表名;

  * U（Update）：修改

    * 复制表：CREATE TABLE 复制目标表表名 LIKE 待复制的表表名;
    * 修改表名：ALTER TABLE 表名 RENAME TO 新表名;
    * 修改表的字符集：ALTER TABLE 表名 CHARACTER SET 字符集名称;
      * 查看表的字符集：SHOW CREATE TABLE 表名;
    * 添加一列：ALTER TABLE 表名 ADD 列名 数据类型;
    * 修改列名称、类型
      * 都改：ALTER TABLE 表名 CHANGE 旧列名 新列名 新数据类型;
      * 只改类型：ALTER TABLE 表名 MODIFY 列名 新数据类型;
    * 删除列
      * ALTER TABLE 表名 DROP 列名;

  * D（Delete）：删除

    * DROP TABLE 表名;

    * DROP TABLE IF EXISTS 表名;