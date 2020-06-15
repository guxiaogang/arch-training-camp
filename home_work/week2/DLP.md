依赖倒置原则：
上层模块不应该依赖底层模块,它们都应该依赖于抽象；抽象不应该依赖于细节,细节应该依赖于抽象。
好莱坞原则：don't call us, we'll call you。在使用框架的时候不应该主动去调用框架，应该按框架规范实现扩展，让框架来调用你的扩展。

依赖倒转原则在框架或工具中的应用：
SUM公司在J2EE标准中对这个原则的应用较多，
如JPA作为抽象标准，对应的实现hibernate,Toplink,OpenJPA等。
JMS作为抽象层标准，对应的实现有ActiveMQ,Websphere MQ,TIBCO EMS等。

常用的工具如日志工具，抽象层是sl4j. 对应两种具体实现：log4j和logback。