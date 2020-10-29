# docker-spring-cloud
Use Docker To Run And Debug Spring Cloud Application

# JVM优化
```
-XX:MetaspaceSize=128m（元空间默认大小）
-XX:MaxMetaspaceSize=128m（元空间最大大小）
-Xms1024m（堆最大大小）
-Xmx1024m（堆默认大小）
-Xmn256m（新生代大小）
-Xss256k（棧最大深度大小）
-XX:SurvivorRatio=8（新生代分区比例 8:2）
-XX:+UseConcMarkSweepGC（指定使用的垃圾收集器，这里使用CMS收集器）
-XX:+PrintGCDetails（打印详细的GC日志）
```

# Ribbon负载均衡算法
```
#
#  com.netflix.loadbalancer.RandomRule：从提供服务的实例中以随机的方式；
#  com.netflix.loadbalancer.RoundRobinRule：以线性轮询的方式，就是维护一个计数器，从提供服务的实例中按顺序选取，第一次选第一个，第二次选第二个，以此类推，到最后一个以后再从头来过；
#  com.netflix.loadbalancer.RetryRule：在RoundRobinRule的基础上添加重试机制，即在指定的重试时间内，反复使用线性轮询策略来选择可用实例；
#  com.netflix.loadbalancer.WeightedResponseTimeRule：对RoundRobinRule的扩展，响应速度越快的实例选择权重越大，越容易被选择；
#  com.netflix.loadbalancer.BestAvailableRule：选择并发较小的实例；
#  com.netflix.loadbalancer.AvailabilityFilteringRule：先过滤掉故障实例，再选择并发较小的实例；
#  com.netflix.loadbalancer.ZoneAwareLoadBalancer：采用双重过滤，同时过滤不是同一区域的实例和故障实例，选择并发较小的实例。
```

# Hystrix挖坑
```
java.lang.IllegalStateException: Request caching is not available. Maybe you need to initialize the HystrixRequestContext?
需要使用Filter初始化HystrixRequestContext

另外一个坑就是，Hystrix里的Cache，是针对单请求内单。
当前请求内，同一方法请求结果一样，下一次请求过来重新刷新。
生命周期较短。
@CacheResult(cacheKeyMethod = "getCacheKey")

```