## dalomao-spring-extend
spring的一些可以经常用到的扩展类使用

## 包结构
* **aware**：Aware 接口是一个标记接口，表示所有实现该接口的类是会被Spring容器选中，并得到某种通知
* **factorypostprocessor**：在所有bean注册为beanDefinition之后，spring会扫描所有实现了BeanFactoryPostProcessor的bean，并调用其postProcessBeanFactory方法，该扩展主要用来动态修改beanDefinnition的属性等，实用性不高
* **initbean**：Spring在实例化bean之后，会判断该bean是否实现了InitializingBean并调用afterPropertiesSet进行初始化，可以用来在bean实例化之后做一些初始化工作
* **listenerasync**：spring异步监听事件，发布/订阅模式
* **listenersync**：spring同步监听事件，发布/订阅模式
* **postprocessor**：在Spring实例化每个bean之后，会调用所有实现了BeanPostProcessor接口的bean，可以在bean初始化之后做一些其他初始化工作
* **registrypostprocessor**：在所有bean注册为beanDefinition之后，spring会扫描所有实现了BeanDefinitionRegistryPostProcessor的bean，并调用其postProcessBeanDefinitionRegistry方法，该扩展主要用来动态修改beanDefinnition的属性等，实用性不高

