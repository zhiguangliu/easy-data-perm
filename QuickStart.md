# 快速开始

## clone代码

从 https://github.com/zhiguangliu/easy-data-perm 获取数据权限项目代码（你应该已经搞定了）

从 https://github.com/zhiguangliu/easydp-demo 获取demo项目代码

## 导入数据

在mysql中，执行easy-data-perm项目（也就是本项目）中的 database/ezdp.sql和database/ezdp-demo.sql两个脚本。这两个脚本会建立两个schema，分别为easy_dp和seller_system。并导入测试数据。

## 修改配置

### 端口配置

确认8899端口（easy-data-perm项目）和8765端口（demo项目）可用。如果修改easy-data-perm的端口，需要同步修改demo项目的application.properties中的ezdp.server项的地址。

### 数据库配置

修改两个项目中的数据库用户名和密码

easy-data-perm项目的配置在 easy-data-perm-server/src/main/resources/application.properties

easydp-demo项目的配置在 easydp-demo/src/main/resources/application.properties

## 运行程序

分别运行

easy-data-perm项目中的 cn.zhgliu.ezdp.EasyDataPermServerApplication

和

demo项目中的 cn.zhgliu.ezdpdemo.EzdpDemoApplication

## 测试结果

分别访问：

```
localhost:8765/ezdpdemo/customer/customer/listAllWithPerm?userId=1
localhost:8765/ezdpdemo/customer/customer/listAllWithPerm?userId=2
```

测试数据权限的效果。可以将userId分别设置为1到11，观察返回结果。其中11会报错，因为没有这个用户的数据权限

分别访问：

```
localhost:8765/ezdpdemo/customer/customer/pageWithPerm?userId=1&current=1&size=2
localhost:8765/ezdpdemo/customer/customer/pageWithPerm?userId=2&current=1&size=2
localhost:8765/ezdpdemo/customer/customer/pageWithPerm?userId=3&current=1&size=2
```

测试数据权限插件与mybatis-plus的分页插件配合的效果

