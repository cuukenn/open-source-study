#### caffeine缓存监控demo
- 启动项目
- 访问http://127.0.0.1:8080/api/messages
- 运行jdk自带监控工具jconsole(位于jdk安装目录/bin,有环境变量可cmd直接运行jconsole)
- jconsole切换到mbean,展开javax.cache即可看到注册的caffeine缓存监控信息