# 通用数据权限组件
## 概述

数据权限按控制对象划分通常可以分为行权限和列权限两类，按控制的操作类型，可以分为查询限制和插入、更新限制。目前系统仅实现了对查询操作的行权限控制，其他控制暂未实现。目前基本可以实现与业务系统松耦合的情况下，对业务代码无侵入的行权限控制。

## 场景描述

**声明：以下场景仅为了说明数据权限的功能，与实际情况无关。如有雷同，纯属巧合。**

### 背景知识简介及演示数据：

- 中国民用航空局空中交通管理局(简称民航局空管局)是民航局管理全国空中交通服务各项业务的职能机构。
- 民航局空管局下设华北、东北、华东、中南、西南、西北、新疆七个地区空管局，分别管理各自地区内的航班。
- 航空公司（简称航司）是负责具体商业航班运营的公司。他们都有查询航班安排的需求。

- 航司的航线遍布全国，不会按地区划分，不过航司需要管理自己公司的航班。

我们假设有个数据表，保存了全国的所有机场的航班信息。为了简化为题我们做如下假设：

1. 只有北京（机场代码PEK）、天津（机场代码TSN，这两个机场归属华北空管局，以N代表）和哈尔滨机场（机场代码HRB）、沈阳（机场代码SHE，归属东北空管局，以NE代表）三个机场

2. 只有东航（航班号MU开头）和国航（航班号CA开头）两个航司的航班。

3. 每个航司在每两个机场之间都有且只有一个航班。

   根据以上假设，我们可以的到数据表和数据如下：

   ```
   +----+------+-----------+-----+---------+---------+---------------+
   | id | from | from_area | to  | to_area | company | flight_number |
   +----+------+-----------+-----+---------+---------+---------------+
   |  1 | PEK  | NC        | TSN | N       | CA      | CA0001        |
   |  2 | PEK  | NC        | SHE | NE      | CA      | CA0002        |
   |  3 | PEK  | NC        | HRB | NE      | CA      | CA0007        |
   |  4 | PEK  | NC        | TSN | N       | MU      | MU0001        |
   |  5 | PEK  | NC        | SHE | NE      | MU      | MU0002        |
   |  6 | PEK  | NC        | HRB | NE      | MU      | MU0007        |
   |  7 | TSN  | NC        | PEK | N       | CA      | CA0003        |
   |  8 | TSN  | NC        | SHE | NE      | CA      | CA0004        |
   |  9 | TSN  | NC        | HRB | NE      | CA      | CA0008        |
   | 10 | TSN  | NC        | PEK | N       | MU      | MU0003        |
   | 11 | TSN  | NC        | SHE | NE      | MU      | MU0004        |
   | 12 | TSN  | NC        | HRB | NE      | MU      | MU0008        |
   | 13 | SHE  | NE        | PEK | N       | CA      | CA0005        |
   | 14 | SHE  | N         | TSN | N       | CA      | CA0006        |
   | 15 | SHE  | N         | HRB | NE      | CA      | CA0009        |
   | 16 | SHE  | NE        | PEK | N       | MU      | MU0005        |
   | 17 | SHE  | N         | TSN | N       | MU      | MU0006        |
   | 18 | SHE  | N         | HRB | NE      | MU      | MU0009        |
   | 19 | HRB  | NE        | PEK | N       | CA      | CA0010        |
   | 20 | HRB  | NE        | TSN | N       | CA      | CA0011        |
   | 21 | HRB  | NE        | SHE | NE      | CA      | CA0012        |
   | 22 | HRB  | NE        | PEK | N       | MU      | MU0010        |
   | 23 | HRB  | NE        | TSN | N       | MU      | MU0011        |
   | 24 | HRB  | NE        | SHE | NE      | MU      | MU0012        |
   +----+------+-----------+-----+---------+---------+---------------+
   ```
   
   

### 应用场景描述：

现假设有如下人员需要查看这些数据：

华北空管局员工：所有从华北局属下机场起降的航班。

```
SELECT * FROM `flight_info` t where t.from_area='N' or t.to_area='N';
```

东北空管局员工：所有从东北局属下机场起降的航班。

```
SELECT * FROM `flight_info` t where t.from_area='NE' or t.to_area='NE';
```

国航总部运营管理：所有国航的航班。

```
SELECT * FROM `flight_info` t where t.company='CA';
```

国航首都机场运营管理：国航在首都机场起降的航班。

```
SELECT * FROM `flight_info` t where t.company='CA' and (t.`from`='PEK' or t.`to`='PEK');
```

或者进一步拆分：

```
SELECT * FROM `flight_info` t where (t.company='CA' and t.`from`='PEK') or(t.company='CA' and t.`to`='PEK');
```

东航运营管理：所有东航的航班。

```
SELECT * FROM `flight_info` t where t.company='MU';
```

东航华北区运营管理：东航在首都机场和天津机场起降的航班。（这个sql写法很多，这里只是一个例子）

```
SELECT * FROM `flight_info` t where t.company='MU' and (t.`from`='PEK' or t.`to`='PEK' or t.from='TSN' or t.`to`='TSN');
```

也可以按最小粒度拆分

```
SELECT * FROM `flight_info` t 
where (t.company='MU' and t.`from`='PEK')
or (t.company='MU' and t.`to`='PEK' )
or (t.company='MU' and t.from='TSN' )
or (t.company='MU' and t.`to`='TSN');
```

东航首都机场运营管理：东航在首都机场起降的航班。

```
SELECT * FROM `flight_info` t where t.company='MU' and (t.`from`='PEK' or t.`to`='PEK');
```

一般，我们可以这么做：

- 归纳上面这些sql，我们可以发现，根据等号左边的字段名分析，主要有三类条件：

1. from_area或to_area 

1. company 

1. company+from或company+to 

- 给空管和、航司总部运营、航司分部运营分开发不同接口，按刚才总结的规律，拼接不同的条件。但这样操作会带来一些问题：
  - 代码冗余（空管一份、航司一份），维护不便。
  - 如果有其他查询条件，还需要考虑如何跟其他的条件进行拼装。
  - 数据权限的逻辑侵入到查询航班的逻辑里面了。

  - 最重要的是，如果后期数据权限的需求发生变化，增加预计起飞时间，预计降落时间，并且人员按照这两个时间分工，则需要修改程序中的控制权限部分的逻辑并重新上线，这将带来很大的工作量和上线风险。

### 数据权限组件的功能

数据权限组件就是让上面的这些权限条件可以在界面上配置，然后利用插件将权限条件拼装进待执行的sql中。实现“查询航班”的逻辑与“控制权限”的逻辑分离，在不影响业务查询条件的基础上，实现了对数据权限的控制。

要实现独立的数据权限控制有这么几个难点：              

1. 条件如何管理
   1. 不同的条件如何保存
   2. 不同的关系如何组合
2. 条件如何拼装进基础查询sql

为了解决第一个问题，我们将整个过程抽象成如下模型：

1. 最底层是“数据权限元数据层”。这层定义了需要控制权限的表中那些字段需要控制条件，即sql条件的左值，一般会有多个字段；
2. 第二层是“数据权限层”。这层指定了条件的剩余部分，即sql条件的运算符和右值。按需设置，不是元数据层的所有字段都要设置，多个字段的条件之间是“且”的关系；
3. 第三层是“数据权限角色层”。这一层根据业务需求，对数据权限进行了组合，形成了一个条件组，同组中的数据权限是“或”的关系。
4. 第四层是“用户与角色关系”层，这里将用户关联到角色，多个角色之间的条件是“或”的关系。

为了解决第二个问题，我们引入了阿里的druid，利用他的sql解析功能，将权限条件拼装进原sql中。

## 功能和架构设计

- 数据权限条件要在查询执行之前生效

  数据权限针对的是查询数据的过程，而不是处理数据的过程。所以，要在sql查询之前，对sql进行处理，将权限加进去，而不是在查询到数据之后，再按照权限进行过滤。

- 数据权限的取值要可以动态控制

- 

  

  但是要做出一个可以通用的权限控制系统，有下面几个难点：
  
  1. 

- 列查询权限



