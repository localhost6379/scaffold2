# SpringCloud+SpringDataJPA脚手架 优化版
1. 统一返回值使用了泛型
2. controller调用、feign调用、zuul调用 全部测试通过 
3. zuul熔断、zuul过滤 未测试
4. 配置中心未使用
5. 自动添加创建时间、最后更新时间、创建人、更新人 
6. 默认分页条件：name模糊查询、status精确查询、创建时间范围查询、更新时间范围查询
7. 默认分页排序：按照排序值降序，排序值相同按照更新时间升序
