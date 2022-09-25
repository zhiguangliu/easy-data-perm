# 前言
这是一个有点复杂但是很强大的数据权限管理系统。她可以在跟业务逻辑完全解耦的情况下，通过配置实现数据权限控制。想要理解她的美妙，您只需要稍微有点耐心和一点java开发的基础就行了。当然，您需要准备个java环境，不用太高，1.8就行了。idea、eclipse随意，maven也得配好。依赖都在中央仓库了。哦，还得有个mysql，root密码您得知道，因为要建schema和表，还得导入数据。您说您都准备齐了，那咱就开始吧。

# 初体验

为了不让这个文档又臭又长，我们分成几个部分来说。首先，请移步[“快速开始”](QuickStart.md)。完成您的第一次体验。

# 客户端DEMO简要代码分析

如果您已经成功搭建了环境，体验到了权限的功能，那我们就离成功又近了一步了。下面简要介绍一下客户端demo项目的代码。demo项目是一个典型的 springboot + mybatis + mybatis-plus + mysql 的项目结构。所有的业务逻辑都在cn.zhgliu.ezdpdemo.customer包下面，都是自动生成的。controller中是我们要测试的“查”。

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

后面我还会给大家提供更多的插件，敬请期待哦。

## 收一下尾

有的同学一定想到刚才在QuickStart中体会的那个分页了，那个分页也是个插件，mybatis-plus自己提供的，在sql上加东西的，" limit x , y " 么。这个是怎么实现的呢，是在这里。

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

# 服务器端

体验完了客户端，我们再来看服务器端。服务器端的界面，嗯，怎么说呢，真的非常的服务器端啊。大家先凑合着用。后面再说哈。

首先访问：

http://localhost:8899/ezdp/view/userRole/userRole.html

这里是定义用户的数据权限角色的地方。我们看到上面有个“子系统”的下拉列表，选择“销售系统”，下面的用户就加载出来了。

那么问题来了，服务器端是如何知道别的系统里的用户的呢？这里服务器端提供了一个接口：

```
public interface UserInfoProvider {

    Boolean support(String subsystemCode);

    UserInfo getUserInfoById(String userId);

    Pagination<UserInfo> listUserInfoByPage(UserInfo userInfo, Integer pageNum, Integer pageSize,Boolean isAsc, String... column);

    Pagination<UserInfo> searchUserByKeywordByPage(String keyword, Integer pageNum, Integer pageSize,Boolean isAsc, String... column);

}
```

查询用户时会遍历所有这个接口的实现，调用support方法，传入子系统编码，找到支持当前子系统的Provider，并使用这个Provider查询用户。

回到刚才的话题，我们以刘大为例，修改一下用户权限，看下效果。

先访问：

```
http://localhost:8765/ezdpdemo/customer/customer/listAllWithPerm?userId=2
```

返回的结果是：

```
[
    {
        "id": 11,
        "customerName": "哈尔滨二龙山有限公司",
        "areaCode": "黑",
        "maintainerId": 2,
        "createrId": 1
    }
]
```

可以看到后台执行的sql是

```
select id, customer_name, area_code, maintainer_id, creater_id
from customer customer
where customer.area_code = '辽'
	or customer.area_code = '吉'
	or customer.area_code = '黑'
	and (customer_name like '%公司%'
		and customer_name like '%公司%')
```

在刚才的页面上，给刘大增加一个“华北地区销售经理”的权限，还是访问刚才的接口，会发现返回结果变成了：

```
[
    {
        "id": 1,
        "customerName": "北京雾灵山有限公司",
        "areaCode": "京",
        "maintainerId": 6,
        "createrId": 1
    },
    {
        "id": 2,
        "customerName": "北京慕田峪有限公司",
        "areaCode": "京",
        "maintainerId": 6,
        "createrId": 1
    },
    {
        "id": 3,
        "customerName": "北京景山有限公司",
        "areaCode": "京",
        "maintainerId": 5,
        "createrId": 1
    },
    {
        "id": 4,
        "customerName": "天津盘山有限公司",
        "areaCode": "津",
        "maintainerId": 7,
        "createrId": 1
    },
    {
        "id": 5,
        "customerName": "河北燕山有限公司",
        "areaCode": "冀",
        "maintainerId": 8,
        "createrId": 1
    },
    {
        "id": 6,
        "customerName": "河北狼牙山有限公司",
        "areaCode": "冀",
        "maintainerId": 8,
        "createrId": 1
    },
    {
        "id": 7,
        "customerName": "内蒙贺兰山有限公司",
        "areaCode": "蒙",
        "maintainerId": 9,
        "createrId": 1
    },
    {
        "id": 8,
        "customerName": "内蒙阴山有限公司",
        "areaCode": "蒙",
        "maintainerId": 9,
        "createrId": 1
    },
    {
        "id": 9,
        "customerName": "山西五台山有限公司",
        "areaCode": "晋",
        "maintainerId": 10,
        "createrId": 1
    },
    {
        "id": 10,
        "customerName": "山西恒山有限公司",
        "areaCode": "晋",
        "maintainerId": 10,
        "createrId": 1
    },
    {
        "id": 11,
        "customerName": "哈尔滨二龙山有限公司",
        "areaCode": "黑",
        "maintainerId": 2,
        "createrId": 1
    }
]
```

可以看到执行的sql是

```
 select id, customer_name, area_code, maintainer_id, creater_id
from customer customer
where customer.area_code = '辽'
	or customer.area_code = '吉'
	or customer.area_code = '黑'
	or (customer.area_code = '鲁'
		or customer.area_code = '晋'
		or customer.area_code = '蒙'
		or customer.area_code = '冀'
		or customer.area_code = '津'
		or customer.area_code = '京')
	and (customer_name like '%公司%'
		and customer_name like '%公司%')
```

数据权限不同，返回结果也就不同了。

我们再取消刘大的所有角色，再访问这个接口，就会看到接口报了500，后台也打印了错误信息

```
{
    "timestamp": "2022-09-25T15:05:03.517+00:00",
    "status": 500,
    "error": "Internal Server Error",
    "path": "/ezdpdemo/customer/customer/listAllWithPerm"
}
```

```
2022-09-25 23:05:03.514 ERROR 4359 --- [nio-8765-exec-3] o.a.c.c.C.[.[.[.[dispatcherServlet]      : Servlet.service() for servlet [dispatcherServlet] in context with path [/ezdpdemo] threw exception [Request processing failed; nested exception is org.mybatis.spring.MyBatisSystemException: nested exception is org.apache.ibatis.exceptions.PersistenceException: 
### Error querying database.  Cause: cn.zhgliu.ezdp.exception.DataPermRuleFetchException: THE SYSTEM CONFIG THE MATCHINGMODE TO "STRICT", BUT THERE IS NO RULE FOR CURRENT USER. THE USER ID IS:2 AND THE OPERATION IDENTIFIER IS:cn.zhgliu.ezdpdemo.customer.mapper.CustomerMapper.selectList
```

我们看到报错信息：

“cn.zhgliu.ezdp.exception.DataPermRuleFetchException: THE SYSTEM CONFIG THE **MATCHINGMODE** TO **"STRICT"**, BUT THERE IS NO RULE FOR CURRENT USER. THE USER ID IS:2 AND THE **OPERATION IDENTIFIER** IS:cn.zhgliu.ezdpdemo.customer.mapper.CustomerMapper.selectList”

不禁会生出这样的疑问：

- MATCHINGMODE是什么呢？
- 除了“STRICT”，还有什么值可以设置，这些值又有什么不同呢？
- OPERATION IDENTIFIER又是个什么东西呢？

下面我们就来讲一下这个数据权限系统的设计思路。

# 设计思路

## 
