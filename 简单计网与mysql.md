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

* 导入指定路径的数据

```
source 路径
```

## 2.mysql目录结构

* 安装目录：D:\mysql\mysql-5.7.19-winx64

my.ini------配置文件，通过此文件修改配置

* 数据目录：D:\mysql\mysql-5.7.19-winx64\data

mysql服务器是指mysql软件所在的那台计算机

数据库：文件夹

表：文件

一个数据库可以存放多个表，表就是文件，一个表可以存放多个数据记录

## 3.SQL（Structured Query Language）

https://www.bilibili.com/video/BV1fx411X7BD?p=31&spm_id_from=pageDriver

### 3.1概念

Structured Query Language：结构化查询语言

SQL其实就是定义了操作所有关系型数据库的规则，每一种数据库的操作方式存在不一样的地方，称为”方言“

关系型数据库mysql，表与表之间存在关系

* 表：一种结构化文件（excel表）
* 行：记录
* 列：字段

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
* 结束一条语句：\c
* 退出mysql：exit

* 查看表的结构：desc
* 查看建表语句：show create table 表名

### 3.3分类

* DDL（Data Definition Language）数据定义语言：操作数据库、表
* DML（Data Manipulation Language）数据操作语言：操作表中记录（数据），增删改数据
* DQL（Data Query Language）数据查询语言：操作表中记录，查询数据
* DCL（Data Control Language）数据控制语言：授权，是否有权限操作数据库和表

#### 3.3.1DDL：操作数据库和表（create、drop、alter）

* 操作数据库：CRUD（create、drop、show、update）

  * C（Create）：创建
    * 创建数据库：CREATE DATABASE 数据库名称;
      * 判断是否存在这个数据库：CREATE DATABASE IF NOT EXISTS 数据库名称;
      * 指定默认字符集：CREATE DATABASE 数据库名称 CHARACTER SET 字符集;
      * 即判断是否存在又指定字符集：CREATE DATABASE IF NOT EXSITS 数据库名称 CHARACTER SET GBK;
  * R（Retrieve）：查询结构
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

* 操作表CRUD（create、drop、show、alter）

  * C（Create）：创建

    * CREATE TABLE 表名(

      ​			列名1 数据类型1,

      ​			列名2 数据类型2，

      ​			.....

      ​			列名n 数据类型n

      );

    * 数据类型：

      * int：整数类型：age int,
      * bigint：长整数类型
      * double：小数类型，score double(5, 2);
      * date：日期，只包含年月日，yyyy-MM-dd
      * datetime：日期，包含年月日时分秒，yyyy-MM-dd HH:mm:ss
      * timestamp：时间戳类型 包含年月日时分秒，yyyy-MM-dd HH:mm:ss（注意，如果不给这个字段赋值，或赋值为null，则默认使用当前的系统时间，来自动赋值）
    * varchar：可变字符串类型（动态分配空间），name varchar(20);#姓名最大20个字符
      
      * char：定长字符串
    * BLOB：二进制大对象（存储图片、视频等流媒体信息）
  
    * CLOB：字符大对象（存储较大文本，eg：可以存储4G的字符串）
  
    * CREATE TABLE STUDENT (

      ​	ID INT,

      ​	NAME VARCHAR(32),

      ​	AGE INT DEFAULT 18,#默认为18

      ​	SCORE DOUBLE(4,1),

      ​	BIRTHDAY DATE,

      ​	INSERT_TIME TIMESTAMP
    
      );
    
  * 表的复制
  
    ```sql
      #将emp查询出的结果创建为一个新表emp1
      CREATE TABLE emp1 AS SELECT * FROM emp;
    ```
  
    * 将查询结果插入一张表中（列要求相同）
  
      ```sql
      #将dept中的数据插入dept1中
    INSERT INTO dept1 SELECT * FROM dept
      ```

  * R（Retrieve）
  
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

#### 3.3.2DML增删改表中的数据（insert、delete、update）

* 添加数据：insert into 表名(列名1, 列名2, ...列名n) values(值1, 值2, ....., 值n);
  * 列名和值要一一对应
  * 给所有列添加值：insert into 表名 values(值1, 值2, ....., 值n)
  * 插入多行数据：insert into 表名 values(),()....();
* 删除数据：delete from 表名 [where 条件];
  * 如果不加条件，则删除表中所有记录
  * 如果要删除整表且（创建一个一模一样的表），建议使用TRUNCATE TABLE stu; 因为delete方法是将表中的数据一条一条删除的，效率低下
* 修改数据：update 表名 set 列名1 = 值1, 列名2 = 值2, 列名3 = 值3,....., 列名n = 值n [where 条件];
  * 如果不加任何条件则会将表中所有数据进行修改

#### 3.3.3DQL查询表中记录

* 查询表中所有数据：select * from 表名;

* 语法：

  ```sql
  select 		5
  	字段列表
  from		1
  	表名列表
  where		2
  	条件列表
  group by	3
  	分组字段
  having		4
  	分组之后的条件
  order by	6
  	排序
  limit
  	分页限定
  ```

* 基础查询
  * 多个字段查询
    * 查询指定的列：select 列名1,列名2..... from 表名;
  * **去除重复**
    * 如果需要去除重复的数据则：SELECT DISTINCT 列名 FROM 表名;（如果有多列则需要每一列的数据完全一样才能去重）
    * 多个条件联合去重时，重复的条件是两个字段下完全相同
  * 计算列
    * SELECT 列名1,列名2,....,需要计算的列的操作 FROM 表名;
    * 如果有值为null时，则可以使用ifnull(列名,为null时替代的值)函数来将null替换你需要的值
  * 起别名
  * as 别名;或（空格） 别名;
  
* 条件查询
  * where子句后跟条件
  * 运算符
    * between... and
    * in(集合)：SELECT * FROM student WHERE age IN (22,19,25);--查询student表中age在集合（22，19，25）中的值
    * like（模糊查询）：
      * _：单个任意字符
      * %：零或多个任意字符
      * 转义字符\
    * is null：判断null不能使用=而是使用is
      * 不为null则：is not null
    * and 或 &&
    * or 或 ||
    * and的优先级高于or
    * not 或 ！
  
* 排序查询：
  
  * order by 排序字段1 排序方式1 , 排序字段2 排序方式2....（字段1的优先级高于字段2）
  
    SELECT * FROM student ORDER BY math ASC;
  
    SELECT * FROM student ORDER BY math ASC, english DESC;//math降序，english升序
    
    SELECT * FROM student ORDER BY 1;//按照第一列进行排序
    
  
  SELECT * FROM student WHERE sex='男' ORDER BY math DESC;//选出sex为男的同学再将math按降序排列
  
    * 若无排序方式则默认为升序排列
    * 排序方式：
      * ASC：升序
      * DESC：降序
  
* 聚合函数（分组函数，多行处理函数）：将一列数据作为一个整体，进行纵向的计算（计算排除null值，解决方案：选择不为null的列进行计算；ifnull函数将null替换为指定的值）（**where中不能使用count、max、min、sum、avg，原因是：这些函数必须在分组函数（group by）执行之后再执行，而where函数的执行优先级高于分组函数，那为什么前面没有使用group by函数却能够使用分组函数呢？因为默认调用了group by函数只不过是自成一张表**）
  * count：计算个数
    * SELECT COUNT(计算的列名) FROM 表名;
    * **count(*)与count(具体字段)区别**
      * **count(*)统计的是总的条数**
      * **count(具体字段)统计的是忽略null的条数**
  * max：计算最大值
  * min：计算最小值
  * sum：求和
  * avg：计算平均值

* group by和having
  * group by：按照某个字段或者某些字段进行分组，**当使用group by时select后面只能跟分组函数和分组**
    * SELECT sex,MAX(math+IFNULL(english,0)) FROM student GROUP BY sex;//找到以sex为分组的math+english的最大值
    * 联合分组：group by 分组字段1，分组字段2.....（1的优先级高于2...）
  * having：对分组后的数据进行再次过滤，使用where之后数据如果仍未达到要求则使用having进行过滤

优先级：先要从（from）表中调出数据，才能在第一层条件（where）中筛选，筛选后才能把数据进行分组（group by），如果此时数据还没有达到要求则继续过滤（having）选择出来（select），最后按照升序或降序排序（order by）输出

```
select		5
	..
from		1
	..
where		2
	..
group by	3
	..
having		4
	..
order by	6
	..
```

* SELECT name, math, english, math+IFNULL(english,0) as sum FROM student WHERE (math+IFNULL(english,0))>(SELECT AVG(math+IFNULL(english,0)) FROM student);//子查询，实现找到高于平均总分的人

* 当需要使用分组函数时便需要使用having过滤

* 连接查询：实际开发中，大部分情况都是多表查询，连接查询实现的就是多表联合查询

  * 笛卡尔积现象（sql底层实现原理）：当两张表连接查询时没有条件限制，则表中记录总数为两表的记录条数乘积
    * A={a1,a2,a3}，B={b1,b2,b3}--------A×B={a1b1,a1b2,a1b3,a2b1,a2b2,a2b3,a3b1.....a3b3}（共9条）
    * 避免笛卡尔积现象：加条件进行过滤

  * 表的连接方式

    * 内连接：无主副表之分，两张表是平等的

      * 等值连接：最大特点，条件时等量关系

        * ```sql
          SELECT * FROM dept;
          #查询每个员工的部门名称，要求显示员工名和部门名
          SELECT e.ename, d.dname FROM emp e, dept d WHERE e.DEPTNO=d.DEPTNO;#92，已过时
          SELECT 
          	e.ename, d.dname 
          FROM 
          	emp e
          (inner) JOIN
          	dept d 
          ON 
          	e.DEPTNO=d.DEPTNO;#99，常用，99语法表连接更清晰，将表连接和where分开了
          ```

      * 非等值连接：最大特点，连接条件中为非等量关系

        * ```sql
          #找出每个员工的工资等级，要求显示员工名、工资、工资等级
          SELECT 
          	e.ename, e.sal, s.grade 
          FROM 
          	emp e 
          JOIN 
          	salgrade s 
          ON 
          	e.sal BETWEEN s.losal AND s.hisal;
          ```

      * 自连接：最大特点，一张表看作两张表，自己连自己

        * ```sql
          #找出每个员工的上级领导，要求显示员工名和对应的领导名
          SELECT 
          	e1.ename, e2.ename leader
          FROM 
          	emp e1
          JOIN
          	emp e2
          ON
          	e1.mgr = e2.empno;
          ```

    * 外连接：有主副表之分，主要查询主表上的数据，顺带才查询副表的数据，当副表中的数据没有和主表中数据匹配上，副表自动模拟出NULL与之匹配（也就是说一定要把主表的数据都显示出来，就算副表没有也要使用NULL填充）

      * 左（外）连接（左边的表为主表）

        * ```sql
          #找出每个员工的上级领导，要求显示员工名和对应的领导名
          SELECT 
          	e1.ename, e2.ename leader
          FROM 
          	emp e1
          LEFT (OUTER) JOIN
          	emp e2
          ON
          	e1.mgr = e2.empno;
          ```

        * ```sql
          #找出哪个部门没有员工
          SELECT 
          	d.*
          FROM
          	dept d
          LEFT JOIN
          	emp e
          ON
          	e.deptno=d.deptno
          WHERE
          	e.ename IS NULL;
          ```

      * 右连接（右边的表为主表）

        * ```sql
          #找出每个员工的上级领导，要求显示员工名和对应的领导名
          SELECT 
          	e2.ename, e1.ename leader
          FROM 
          	emp e1
          RIGHT JOIN
          	emp e2
          ON
          	e2.mgr = e1.empno;
          ```

      * 左右连接都有互相的写法：左连接有右连接的写法，右连接有左连接的写法

      * 多表连查

        ```sql
        #找出每一个员工的部门名称和工资等级
        SELECT
        	e.ename, d.dname, s.grade
        FROM
        	emp e
        JOIN
        	dept d
        ON
        	e.deptno=d.deptno
        JOIN
        	salgrade s
        ON
        	e.sal BETWEEN s.losal AND s.hisal;
        ```

        ```sql
        ...
        	A
        JOIN
        	B
        JOIN
        	C
        ....
        ON
        	...;
        ```

        ```sql
        #找出每一个员工的部门名称和工资等级及上级领导
        SELECT
        	e.ename, d.dname, s.grade, e1.ename leader
        FROM
        	emp e
        JOIN
        	dept d
        ON
        	e.deptno=d.deptno
        JOIN
        	salgrade s
        ON
        	e.sal BETWEEN s.losal AND s.hisal
        LEFT JOIN
        	emp e1
        ON
        	e.mgr=e1.empno;
        ```

    * 全连接（极少用）(AB均为主表，全部查询出来)

* 子查询：select中嵌套select语句，被嵌套的select语句是子查询

  * ```sql
    #找出高于平均薪资的员工信息（where嵌套查询）
    SELECT ename,sal FROM emp WHERE sal>(SELECT AVG(sal) FROM emp);
    ```

  * ```sql
    #找出每个部门平均薪水的薪资等级（from嵌套查询）
    SELECT 
    	e.*,s.grade 
    FROM 
    	(SELECT deptno, AVG(sal) avg FROM emp GROUP BY deptno) e
    JOIN 
    	salgrade s
    ON
    	e.avg BETWEEN s.LOSAL AND s.HISAL;
    ```

  * ```sql
    #找出每个部门薪资等级的平均值（1.找到每个员工的薪资等级，并使用部门、等级列为新表a 2.将a中的部门进行分组，并求出薪资等级的平均值）
    #自己写的
    SELECT
    	a.deptno,AVG(a.grade) avg
    FROM
    	(SELECT 
    		e.deptno,s.grade
    	FROM 
    		emp e
    	JOIN	
    		salgrade s
    	ON
    		e.sal BETWEEN s.losal AND s.hisal
    	) a
    GROUP BY
    	a.deptno;
    #老师优化的
    SELECT 
    	e.deptno,AVG(s.grade)
    FROM 
    	emp e
    JOIN	
    	salgrade s
    ON
    	e.sal BETWEEN s.losal AND s.hisal
    GROUP BY
    	e.deptno;
    ```

    ```sql
    #找出每个员工所在的部门名称
    #第一种写法
    SELECT 
    	e.ename, d.dname
    FROM
    	emp e
    JOIN
    	dept d
    ON
    	e.deptno=d.deptno;
    #第二种写法（select嵌套写法）
    SELECT e.ename, (SELECT d.dname FROM dept d WHERE e.deptno=d.deptno) dname FROM emp e;
    ```

* union：将查询结果集相加（求并集）
  
  * 注意：union连接的表，select的列要相同

```sql
#找出工作岗位是SALESMAN和MANAGER的员工
#方法1
SELECT ename,job FROM emp WHERE job IN('SALESMAN','MANAGER');
#方法2
SELECT ename,job FROM emp WHERE job = 'SALESMAN'
UNION
SELECT ename,job FROM emp WHERE job = 'MANAGER';
```

* **limit：**mysql特有的语法，可实现取结果集中的部分数据（**执行优先级最低**）

  * ```
    limit startIndex, length
    startIndex：取得开始索引
    length：取得长度
    ```

    ```sql
    #取出工资前5名的员工
    SELECT ename,sal FROM emp ORDER BY sal DESC LIMIT 0,5;
    #0可忽略，在默认情况下从头开始取，索引从0开始
    ```

#### 3.3.4约束

在创建表的时候，可以给表的字段添加相应的约束，添加约束的目的是为了保证表中数据的合法性、完整性、有效性

* 常见约束：
  * 非空约束（not null）
  
  * 唯一约束（unique）（可以为null，因为null不是一个数值）
  
    * ```sql
      #单独具有唯一性
      CREATE TABLE t_user(
      	id int,
      	username VARCHAR(255) UNIQUE#列级约束
      );
      ```
  
    * ```sql
      #(usercode+username)具有唯一性
      CREATE TABLE t_user(
      	id int,
      	usercode VARCHAR(255),
      	username VARCHAR(255),
      	UNIQUE(usercode, username)#表级约束
      );
      ```
  
  * 主键约束（primary key）PK：约束的字段既不能为null也不能重复（非空and唯一）
  
    * ```sql
      CREATE TABLE t_user(
      	id int PRIMARY KEY,#列级约束
      	username VARCHAR(255),
      	email VARCHAR(255)
      );
      CREATE TABLE t_user(
      	id int,
      	usercode VARCHAR(255),
      	username VARCHAR(255),
      	PRIMARY KEY(usercode, username)#表级约束
      );
      ```
  
    * 基础概念
  
      * 主键字段：使用主键约束的列
      * 主键约束：primary key关键字
      * 主键值：插入的对应列的值
  
    * 作用：主键是一张表中的唯一标识，只要主键不同则两行数据所表示的数据也不同（如同身份证号）
  
      * 表的设计三范式：第一范式就要求任何一张表都应该有主键
  
    * 主键分类
  
      * 按照数量划分
        * 单一主键
        * 复合主键（多个字段联合起来添加一个主键约束）（不建议使用）
      * 按照主键性质划分
        * 自然主键
        * 业务主键：主键值和系统的业务挂钩，eg：银行卡的卡号做主键，身份证号做主键（不推荐使用）
  
    * 注意：一张表的主键约束只能有一个
  
    * 主键值自增：在主键约束后加auto_increment
  
    ```sql
    CREATE TABLE t_user(
    	id int PRIMARY KEY auto_increment,
    	username VARCHAR(255),
    	email VARCHAR(255)
    );
    ```
  
    
  
  * 外键约束（foreign key）FK（可以为NULL）
  
    * 基础概念
  
      * 外键约束：foreign key
      * 外键字段：使用外键约束的列的列名
      * 外键值：外键字段插入的值
  
    * 作用：可以使两张表关联起来，而不会发生在fk的pk中找不到fk的值
  
    * 注意：
  
      * 创建时应先创建父表再创建子表，删除时应先删除子表再删除副表，添加数据时应先添加副表再添加子表
      * 外键字段引用其他表的某字段时，该字段不一定为主键，只要该字段具有唯一性即可
  
      ```sql
      DROP TABLE IF EXISTS t_student;
      DROP TABLE IF EXISTS t_class;
      CREATE TABLE t_class(
      	cno INT,
      	cname VARCHAR(255),
      	PRIMARY KEY(cno)
      );
      CREATE TABLE t_student(
      	sno INT,
      	sname VARCHAR(255),
      	classno INT,
          PRIMARY KEY(sno),
      	FOREIGN KEY(classno) REFERENCES t_class(cno)
      );
      INSERT INTO t_class VALUES(101,'高三1班'),(102,'高三2班');
      INSERT INTO t_student VALUES(1,'zs1',101);
      INSERT INTO t_student VALUES(2,'zs2',101);
      INSERT INTO t_student VALUES(3,'zs3',101);
      INSERT INTO t_student VALUES(4,'zs4',102);
      INSERT INTO t_student VALUES(5,'zs5',102);
      INSERT INTO t_student VALUES(6,'zs6',102);
      ```
  
  * 检查约束（check）：目前mysql不支持检查约束，Oracle支持

#### 3.3.5存储引擎（mysql特有）

表的存储方式

* 常见存储引擎show engines \G
  * MyISAM：不支持事务，可被压缩，可为转换和压缩节省空间
  * InnoDB：最安全的存储引擎，支持事务、行级锁、外键等，mysql的缺省引擎，在mysql服务器崩溃后提供自动恢复
  * MEMORY（HEAP）：不支持事务，数据易丢失，因为所有数据都是存储在内存中，但查询速度最快

#### 3.3.6事务（transaction）

**只有“DML语句操作表中数据（insert、delete、update）“才和事务相关，事务的存在是为了保证数据的完整性、安全性**

* 定义：一个事务是一个完整的业务逻辑单元，不可再分（eg：完成转账需要实现一个逻辑过程，事务就是保证整个过程同时成功或失败）

* 事务机制执行过程
  * 开启事务机制
  * 执行DML语句，成功后将执行记录保存到数据库的操作历史中，并不会向文件中保存数据，也不是真正的修改硬盘上的数据
  * 提交事务（向硬盘中同步数据）或回滚事务（清空操作历史）（结束）
    * 提交事务：commit
    * 回滚事务：rollback a1（回滚到上一次提交或a1保存点）
    * 保存点：savepoint a1

* 四大特性ACID
  * A：原子性：事务是最小的工作单元，不可再分
  * C：一致性：事务必须保证多条DML语句同时成功或失败
  * I：隔离性：事务A与事务B之间具有隔离（隔离级别）
  * D：持久性：持久性说的是最终数据必须持久化到硬盘文件中，事务才算成功结束

* 事务的隔离级别（理论上4级，但所有数据默认在2级及以上）
  * 第一级别：读未提交（read uncommitted）（对方事务未提交，但我们当前事务可以读取到对方未提交的数据）
    * 读未提交存在脏读（dirty read）现象：表示读取到脏的（不稳定）数据
  * 第二级别：读已提交（read committed）（只有对方提交后的数据我方才可以提取到）
    * 读已提交存在**不可重复读**的问题（也就是我读到的数据随commit而改变）
    * 解决了脏读现象
  * 第三级别：可重复读（repeatable read）（每次读到的数据都是一样的）
    * 解决了不可重复问题
    * 存在读取到的数据是幻想（可能不是最新的数据）的问题
  * 第四级别：序列化读/串行化读（serializable）
    * 解决了所有问题
    * 效率低，事务需要排队执行
* mysql隔离级别默认为可重复读（第三级别），且默认为自动提交（执行一条DML就提交一次）
  * 关闭自动提交：strat transaction;
* oracle个理解级别默认为读已提交（第二级别）
* 设置隔离级别：（每次设置完之后都需要重新启动mysql）

```sql
set global transaction isolation level 级别(read uncommitted,read committed,repeatable read...);
```

* 查看全局隔离级别

```sql
select @@global.tx_isolation;
```

#### 3.3.7索引

