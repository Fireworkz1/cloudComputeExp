# 基于SpringBoot+Mybatis+MySQL5.7的轻语音乐网-升级版（springboot-music-plus）

## 主要用到的技术：

 * 使用maven进行项目构建
 * 使用Springboot+Mybatis搭建整个系统
 * 使用ajax连接前后端
 * 使用框架Bootstrap、JQuery开发前端界面 
 * 使用MySQL存储歌曲链接和图片链接
 * 使用cookies存储用户id等
 * 使用阿里云OSS存储歌曲的大部分mp3文件
 
 ## 主要分为六个模块
 
 * **用户管理模块**：用户登录、用户注册、修改密码
 * **榜单列表模块**：动态地从数据库中获取歌曲的链接和数据在榜单中显示
 * **歌曲搜索模块**：在首页的搜索框中可以输入关键字进行歌曲的模糊搜索
 * **歌曲收藏模块**：在榜单列表中或搜索页面中可以点击歌曲进行收藏
 * **我的音乐模块**：收藏的歌曲会被添加到我的音乐列表中，在我的音乐列表中也可以对歌曲进行删除操作
 * **音乐播放器模块**：在榜单、搜索页面和我的音乐列表等地方点击歌曲可以跳转到播放页面进行播放。播放页面显示播放进度条，删除歌曲，暂停等操作。播放页面背景为模糊背景，根据歌手的专辑图片自动变化。 
 
 ## 升级版功能
 
  * 对原来的springboot-music项目使用springboot重新进行重构，代码更加规范和易懂
  * 加入一线互联网公司各种主流技术，力争和企业接轨
  * 兼容原springboot-music项目所有功能
  * 修复了原项目榜单分页的bug以及不能根据旧密码修改用户密码的bug
  * 使用mybatis-plus、lombok、fastJson、swagger
  * 使用mybatis-generator自动生成xml、entity、mapper层代码
  * mybatis和mybatis-plus混合使用，简单sql用mybatis-plus，复杂sql也可以写自定义sql
  * 使用PageHelper分页工具、通用工具类、response类、枚举、事务等等
  * 更多新功能和技术加入... 敬请期待  
 
 ## 问题：如何启动本系统？ 
 
 1. 将sql文件在MySQL运行生成表和数据
 2. 最后直接启动SpringbootMusicPlusApplication.java主类后访问http://localhost:8088/index.html 就可以进入本系统！ 
 3. 详细步骤可以看项目里的"运行说明.txt"文件
 
    项目演示地址：www.linliquan.top  
    github地址：https://github.com/Linliquan/springboot-music-plus  
    如下载速度比较慢，请移至码云下载，gitee地址：https://gitee.com/linliquan/springboot-music-plus  
    QQ交流群：1029064815  
 
 ## 功能展示
 
 * 主页
 
 ![image](https://liquan-springboot-music.oss-cn-shanghai.aliyuncs.com/images/github_img/%E4%B8%BB%E9%A1%B5.jpg?raw=true)
 
 * 登录注册
 
 ![image](https://liquan-springboot-music.oss-cn-shanghai.aliyuncs.com/images/github_img/%E7%99%BB%E5%BD%95%E6%B3%A8%E5%86%8C.png?raw=true)
 
 * 播放页面
 
 ![image](https://liquan-springboot-music.oss-cn-shanghai.aliyuncs.com/images/github_img/%E6%92%AD%E6%94%BE%E9%A1%B5%E9%9D%A2.png?raw=true)
 
 * 收藏
 
 ![image](https://liquan-springboot-music.oss-cn-shanghai.aliyuncs.com/images/github_img/%E6%94%B6%E8%97%8F.png?raw=true)
 
 * 模糊搜索
 
 ![image](https://liquan-springboot-music.oss-cn-shanghai.aliyuncs.com/images/github_img/%E6%90%9C%E7%B4%A2.png?raw=true)
 
 * 删除
 
 ![image](https://liquan-springboot-music.oss-cn-shanghai.aliyuncs.com/images/github_img/%E5%88%A0%E9%99%A4.png?raw=true)
 
 * 炫酷动画
 
 ![image](https://liquan-springboot-music.oss-cn-shanghai.aliyuncs.com/images/github_img/%E7%82%AB%E9%85%B7%E5%8A%A8%E7%94%BB.png?raw=true)
