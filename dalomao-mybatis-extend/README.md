## dalomao-mybatis-extend
mybatis相关的扩展使用

## 包路径
* **extend**：自定义mybatis插件的使用，包括Executor、StatementHandler、ResultSetHandler三种插件的自定义
    > * Excutor：可以加入缓存功能，在查询初期判断redis是否有缓存数据，若有则直接返回，若无则继续查询
    > * StatementHandler：可以加入分页功能
    > * ResultSetHandler：可以加入缓存功能，当查询完毕时，将查询的结果存到redis
