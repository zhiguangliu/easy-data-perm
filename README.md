# 前言
这是一个有点复杂但是很强大的数据权限管理系统。她可以在跟业务逻辑完全解耦的情况下，通过配置实现数据权限控制。想要理解她的美妙，您只需要稍微有点耐心和一点java开发的基础就行了。当然，您需要准备个java环境，不用太高，1.8就行了。idea、eclipse随意，maven也得配好。依赖都在中央仓库了。哦，还得有个mysql，root密码您得知道，因为要建schema和表，还得导入数据。您说您都准备齐了，那咱就开始吧。

# 初体验

为了不让这个文档又臭又长，我们分成几个部分来说。首先，请移步[“快速开始”](QuickStart.md)。完成您的第一次体验。

# DEMO简要代码分析

如果您已经成功搭建了环境，体验到了权限的功能，那我们就离成功又近了一步了。下面简要介绍一下demo项目的代码。demo项目是一个典型的 springboot + mybatis + mybatis-plus + mysql 的项目结构。所有的业务逻辑都在cn.zhgliu.ezdpdemo.customer包下面，都是自动生成的。controller中是我们要测试的“查”。

```java
    @GetMapping("/listAll")
    public List<Customer> listAll(String userId) {
        List<Customer> list = iCustomerService.list(new QueryWrapper<Customer>().like("customer_name","公司").like("customer_name","公司"));        return list;
    }

    @GetMapping("/listAllWithPerm")
    public List<Customer> listAllWithPerm(String userId) {
        DataPermHelper.applyPermission(userId);
        List<Customer> list = iCustomerService.list(new QueryWrapper<Customer>().like("customer_name","公司").like("customer_name","公司"));
        return list;
    }
```

这两个方法，唯一的不同就是带权限的方法多了一行

```
DataPermHelper.applyPermission(userId);
```

两个业务逻辑完全相同。但是下面的方法就能够实现增加数据权限，这就是我们一直强调的“对业务逻辑零侵入”。

## 稍微深层一点分析

真正实现数据权限功能的地方在 cn.zhgliu.ezdpdemo.config.EasyDpConfig 中配置着。里面这么多Bean中，最终起作用的是那个叫DataPermClient的家伙，其他的都是给他服务的。这家伙有个

```
DataPermClient.addPermissionCondition(sql, userId, statementId);
```

方法，将原sql、用户id、statement id（就是mybatis接口里面方法的完全限定名）三个参数传进去，返回的就是加好权限的sql。再用这个新sql去发起查询就行了。

## 再进一步

为了实现刚才说的那个动作，我们给mybatis-plus写了个插件，就是MybatisPlusDataPermInterceptor。在这个拦截器里先：

```
String userId = DataPermHelper.getUserId();
```

获取到用户id，然后这样：

```
String newSql = dataPermClient.addPermissionCondition(sql, userId, statementId);
```

就得到了新的sql，然后用这个sql去查询就行了。

后面我还会给大家提供更多的方便使用的插件，敬请期待哦。

## 收一下尾

有的同学一定想到刚才在QuickStart中体会的那个分页了，那个分页也是个插件，在sql上加东西的，" limit x , y " 么。这个是怎么实现的呢，是在这里。

```
@Bean
public MybatisPlusInterceptor mybatisPlusInterceptor(@Autowired DataPermClient dataPermClient) {
    MybatisPlusInterceptor i = new MybatisPlusInterceptor();
    i.addInnerInterceptor(new MybatisPlusDataPermInterceptor(dataPermClient));
    i.addInnerInterceptor(new PaginationInnerInterceptor());
    return i;
}
```

这里定义了两个插件，一个是分页插件，一个是数据权限插件。都放到mybatis-plus的插件集里就行了。







## 概述



数据权限与功能权限相比，复杂性更高。

按控制对象划分，可以分为行权限和列权限两类，行权限是只用户可以看到某一行的数据，列权限是指这些行中，用户可以看到哪些列的数据。

按控制的操作类型划分，可以分为查询操作权限，插入操作权限、修改操作权限。

目前我们仅实现了对查询操作的行权限控制，其他控制暂未实现。

我们可以实现与业务系统松耦合的情况下，对业务代码无侵入的权限控制。

## 场景描述

嬴总开了一家公司，产品行销全国，需要分区域管理。公司的销售部由嬴总直接担任总监，下面设置了三个销售经理刘大（负责东北）、关二（负责华北）、张三（负责西北）。每个人手下都有若干销售，每个省至少有一名销售负责，对于一些大省由多名销售共同负责。

之前为了便于客户管理，公司开发了crm系统，但是当时业务没有这么大，crm没有做数据权限方面的设计。所有人都可以看到所有的客户数据。现在需要增加数据权限控制，让嬴总可以看到所有客户数据，销售经理可以看到自己负责区域的客户数据，负责全省的销售可以看全省数据，多个销售负责一个省的，每个销售只能看到自己负责的那部分数据。

## 需求分析

1. 客户信息有所属省份和维系人这两个字段可以用来做权限控制
2. 对于嬴总，数据权限是“不控制”
3. 对于销售经理，数据权限是 所属省份 in (A省,B省,C省)
4. 对于负责全省的销售，数据权限是 所属省份=A省
5. 对于负责一个省的部分客户的销售，数据权限是 所属省份=A省 and 维系人=销售员id

## 测试数据

用户数据：

| 用户id | 用户名 | 职务     | 负责区域                 |
| ------ | ------ | -------- | ------------------------ |
| 1      | 嬴总   | 销售总监 | 所有                     |
| 2      | 刘大   | 销售经理 | 负责东北（黑吉辽）       |
| 3      | 关二   | 销售经理 | 负责华北（京津冀蒙晋鲁） |
| 4      | 张三   | 销售经理 | 负责西北（陕甘青宁新）   |
| 5      | 李四   | 销售员   | 北京城区                 |
| 6      | 朱五   | 销售员   | 北京郊区                 |
| 7      | 杨六   | 销售员   | 天津                     |
| 8      | 牛七   | 销售员   | 河北                     |
| 9      | 马八   | 销售员   | 内蒙                     |
| 10     | 侯九   | 销售员   | 山西                     |

客户数据：

| 客户id | 客户名称           | 所属省份 | 创建人id | 维系人id |
| ------ | ------------------ | -------- | -------- | -------- |
| 1      | 北京雾灵山有限公司 | 京       | 1        | 6        |
| 2      | 北京慕田峪有限公司 | 京       | 1        | 6        |
| 3      | 北京景山有限公司   | 京       | 1        | 5        |
| 4      | 天津盘山有限公司   | 津       | 1        | 7        |
| 5      | 河北燕山有限公司   | 冀       | 1        | 8        |
| 6      | 河北狼牙山有限公司 | 冀       | 1        | 8        |
| 7      | 内蒙贺兰山有限公司 | 蒙       | 1        | 9        |
| 8      | 内蒙阴山有限公司   | 蒙       | 1        | 9        |
| 9      | 山西五台山有限公司 | 晋       | 1        | 10       |
| 10     | 山西恒山有限公司   | 晋       | 1        | 10       |

### 以华北地区为例，描述一下数据权限的具体需求

嬴总：没有附加条件

关二：所属省份 in ('京','津','冀','蒙','晋','鲁')    其实是  省份=‘京’ or 省份=‘津’ or 省份=‘冀’ or 省份=‘蒙’ or 省份=‘晋’ or 省份=‘鲁’

李四：所属省份 = ‘京’ and 维系人id=5          景山在城里

朱五：所属省份 = ‘京’ and 维系人id=6

杨六：所属省份 = ‘津’

牛七、马八、侯九与杨六同理
