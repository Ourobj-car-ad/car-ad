# car-ad-app

## API

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

