# 邮件服务支持组件; 验证springboot 自动配置功能

# springboot 自动配置原理
 springboot启动类 -> @SpringBootApplication
 
 @SpringBootApplication -> @EnableAutoConfiguration
 @Enable***开启某一项功能,@Enable***会聚合@Import(XXX.class);向spring工厂加入对应功能的配置;
 
 @EnableAutoConfiguration -> @Import({AutoConfigurationImportSelector.class})
 @Import{ 作用::::
    1: 普通bean; 直接将bean加入spring 容器
    2: 实现ImportSelector接口的bean; 会执行ImportSelector.selectImports(AnnotationMetadata var1)方法;
       selectImports方法调用下面方法,扫描引入的包中是否包含 resources/META-INF/spring.factories;
       如果存在则按照spring.factories 的配置开始初始化配置的bean
    protected List<String> getCandidateConfigurations(AnnotationMetadata metadata, AnnotationAttributes attributes) {
        List<String> configurations = SpringFactoriesLoader.loadFactoryNames(this.getSpringFactoriesLoaderFactoryClass(), this.getBeanClassLoader());
        Assert.notEmpty(configurations, "No auto configuration classes found in META-INF/spring.factories. If you are using a custom packaging, make sure that file is correct.");
        return configurations;
    }
    3: 忘记了
 }
 
 注意: 该项目做为springboot项目在本地运行时,加载的是本地的application.yml配置;
       作为jar包组件时, 由其他项目加载自动配置时, 使用的是其他项目的application.yml配置
       
       
       
 注意:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 异常:::java.lang.IllegalStateException: Unable to read meta-data for XXXXXXXXXXXXXX.class 
        可能是使用springboot 的打包插件 , 在pom.xml中删除这个插件就好了.
 注意:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 
  
