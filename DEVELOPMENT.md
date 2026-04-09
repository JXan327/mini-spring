# Mini-Spring 开发指南

## 快速开始

### 1. 环境要求
- JDK 17+
- Maven 3.8+

### 2. 构建项目
```bash
# 编译项目
mvn clean compile

# 运行测试
mvn test

# 打包项目
mvn package

# 查看代码覆盖率报告
mvn test
# 报告位于: target/site/jacoco/index.html

# 运行代码质量检查
mvn checkstyle:check
mvn pmd:check
```

### 3. 项目结构
```
src/main/java/com/minispring/
├── aop/                    # AOP相关实现
│   ├── framework/         # AOP代理框架
│   ├── aspectj/           # AspectJ集成
│   └── support/           # AOP支持类
├── beans/                 # IoC容器核心
│   ├── factory/          # Bean工厂相关
│   │   ├── config/       # Bean定义配置
│   │   ├── support/      # 工厂实现类
│   │   └── xml/          # XML配置解析
│   └── BeansException.java
├── context/               # 应用上下文
│   ├── event/            # 事件机制
│   └── support/          # 上下文实现
├── core/                  # 核心工具类
│   ├── convert/          # 类型转换
│   ├── env/              # 环境抽象
│   └── io/               # 资源加载
└── web/                   # Web相关支持
    └── context/          # Web上下文
```

## 核心概念

### IoC容器
IoC（控制反转）是Mini-Spring的核心，它通过依赖注入实现对象的创建和管理。

```java
// 创建容器
DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
reader.loadBeanDefinitions("classpath:applicationContext.xml");

// 获取Bean
MyService service = beanFactory.getBean("myService", MyService.class);
```

### Bean生命周期
1. **实例化** - 创建Bean实例
2. **属性填充** - 注入依赖
3. **Aware接口回调** - BeanNameAware、BeanFactoryAware等
4. **BeanPostProcessor前置处理**
5. **初始化** - InitializingBean接口或自定义init-method
6. **BeanPostProcessor后置处理**
7. **Bean就绪** - 可以使用
8. **销毁** - DisposableBean接口或自定义destroy-method

### AOP
AOP（面向切面编程）允许你将横切关注点与业务逻辑分离。

```java
// 创建代理
ProxyFactory proxyFactory = new ProxyFactory(target);
proxyFactory.addAdvice(new MethodBeforeAdvice() {
    @Override
    public void before(Method method, Object[] args, Object target) {
        System.out.println("方法执行前: " + method.getName());
    }
});

Object proxy = proxyFactory.getProxy();
```

## 开发规范

### 命名规范
- **类名**: 大驼峰（PascalCase）- `BeanFactory`
- **方法名**: 小驼峰（camelCase）- `getBean`
- **常量名**: 全大写下划线分隔 - `DEFAULT_BEAN_NAME`
- **包名**: 全小写 - `com.minispring.beans.factory`

### 注释规范
```java
/**
 * 简洁描述类的功能
 * 详细说明（可选）
 *
 * @author 作者名
 * @since 1.0
 */
public class MyClass {
    /**
     * 方法功能描述
     *
     * @param param1 参数1的说明
     * @return 返回值说明
     * @throws Exception 异常说明
     */
    public Object myMethod(String param1) throws Exception {
        // 实现
    }
}
```

### 测试规范
```java
@Test
void testBeanCreation() {
    // Arrange - 准备测试数据
    DefaultListableBeanFactory factory = new DefaultListableBeanFactory();

    // Act - 执行测试操作
    TestBean bean = factory.getBean("testBean", TestBean.class);

    // Assert - 验证结果
    assertNotNull(bean);
    assertEquals("expected", bean.getName());
}
```

## 常见问题

### Bean找不到异常
**问题**: `BeansException: 找不到名为 'xxx' 的BeanDefinition`
**解决**:
- 检查XML配置文件中的bean名称拼写
- 确认配置文件路径正确
- 验证BeanDefinition是否正确加载

### 循环依赖
**问题**: Bean之间相互依赖导致创建失败
**解决**:
- 使用setter注入替代构造器注入
- 使用@Lazy注解延迟初始化
- 重构代码避免循环依赖

### AOP代理不生效
**问题**: 切面逻辑没有执行
**解决**:
- 确保目标方法不是private/final
- 检查切点表达式是否正确
- 验证是否通过容器获取代理对象

## 扩展开发

### 自定义BeanPostProcessor
```java
public class CustomBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        // 前置处理逻辑
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        // 后置处理逻辑
        return bean;
    }
}
```

### 自定义作用域
```java
public class CustomScope implements Scope {
    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        // 获取Bean逻辑
        return objectFactory.getObject();
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {
        // 注册销毁回调
    }
}
```

## 性能优化

1. **懒加载** - 使用lazy-init属性延迟Bean初始化
2. **原型模式** - 对于不需要单例的Bean使用原型作用域
3. **缓存优化** - 合理使用三级缓存解决循环依赖
4. **AOP优化** - 调整代理策略减少性能损耗

## 调试技巧

1. **启用Debug日志** - 修改logback.xml中的日志级别
2. **断点调试** - 在关键类中设置断点：
   - `AbstractBeanFactory.getBean()`
   - `AbstractAutowireCapableBeanFactory.createBean()`
   - `AbstractApplicationContext.refresh()`

## 贡献指南

1. Fork项目到你的GitHub账号
2. 创建功能分支: `git checkout -b feature/AmazingFeature`
3. 提交更改: `git commit -m 'Add some AmazingFeature'`
4. 推送到分支: `git push origin feature/AmazingFeature`
5. 创建Pull Request

### 提交信息规范
```
<type>(<scope>): <subject>

<body>

<footer>
```

**类型 (type)**:
- feat: 新功能
- fix: 修复bug
- docs: 文档更新
- style: 代码格式调整
- refactor: 重构代码
- test: 测试相关
- chore: 构建/工具链相关

**示例**:
```
feat(aop): 添加 Around Advice 支持

实现了Around Advice的完整功能，支持：
- 方法执行前后自定义逻辑
- 返回值修改
- 异常处理

Closes #123
```

## 许可证
MIT License
