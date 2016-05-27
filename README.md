# car-ad-app

## API

### 更新

>http://139.129.132.60/api/getbytype?id=19&types=道路附属设施,道路附属设施

字段:
```
id  //用户的id
types  //类别的字符串, 以逗号为分割.以下为所有类别
    :   
    汽车服务
    汽车维修
    汽车销售
    摩托车服务
    餐饮服务
    购物服务
    生活服务
    体育休闲服务
    医疗保健服务
    住宿服务
    风景名胜
    商务住宅
    政府机构及社会团体
    科教文化服务
    交通设施服务
    金融保险服务
    公司企业
    地名地址信息
    公共设施
```

### 提供给平板app的根据用户id获得当前广告的api

>http://139.129.132.60/api/getbyuserid

字段:
```
id  //用户的id
```

### app退出时发送一个退出消息,用以更新用户状态

>http://139.129.132.60/api/exitwithuserid

字段:
```
id  //用户的id
```

### 添加一个订单

>http://139.129.132.60//api/addorder

字段:
```
price=5000
adId=3
advertiserId=5
userId=7
regionInfo=test
```

### 查询一个用户

>http://139.129.132.60/api/login?

字段:
```
email
pwd
```

### 新建一个用户

>http://139.129.132.60/api/sign?

字段:
```
name
email
pwd
realName
phone
carTravelCode
alipay
carCode
city
```

### 获得一条广告

>http://139.129.132.60/api/getad?

字段:
```
id
```

### 获得一条订单

>http://139.129.132.60/api/getorder?

字段:
```
id
```

### 新建一个订单

>http://139.129.132.60/api/addorder?

字段:
```
price
startTime
endTime
adverserId
userId
regionInfo
```




## To Do List

- [ ] 注册功能
- [ ] 主界面显示广告

