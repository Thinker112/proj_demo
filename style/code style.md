# 一、编程规约

## (一) 命名风格

1. **<span style="color: red;">【强制】</span>** 所有编程相关的命名均不能以下划线或美元符号开始，也不能以下划线或美元符号结束。
     - 反例：`_name `/ `__name` / `$Object` / `name_` / `name$` / `Object$`

2. **<span style="color: red;">【强制】</span>** 所有编程相关的命名严禁使用拼音与英文混合的方式，更不允许直接使用中文的方式。

     - <span style="color: #F5B800;">说明</span>：正确的英文拼写和语法可以让阅读者易于理解，避免歧义。注意，即使纯拼音命名方式也要避免采用。

     - <span style="color: #00F5B8;">正例</span>：ali / alibaba / taobao / kaikeba / aliyun / youku / hangzhou 等国际通用的名称，可视同英文。

3. **<span style="color: red;">【强制】</span>** 代码和注释中都要避免使用任何人类语言中的种族歧视性或侮辱性词语。

     - <span style="color: #00F5B8;">正例</span>：blockList / allowList / secondary

     - 反例：blackList / whiteList / slave / SB / WTF

4. **<span style="color: red;">【强制】</span>** 类名使用 UpperCamelCase 风格，以下情形例外：DO / PO / DTO / BO / VO / UID 等。

     - <span style="color: #00F5B8;">正例</span>：ForceCode / UserDO / HtmlDTO / XmlService / TcpUdpDeal / TaPromotion

     - 反例：forcecode / UserDo / HTMLDto / XMLService / TCPUDPDeal / TAPromotion

5. **<span style="color: red;">【强制】</span>** 方法名、参数名、成员变量、局部变量都统一使用 lowerCamelCase 风格。
     - <span style="color: #00F5B8;">正例</span>：localValue / getHttpMessage() / inputUserId

6. **<span style="color: red;">【强制】</span>** 常量命名应该全部大写，单词间用下划线隔开，力求语义表达完整清楚，不要嫌名字长。

     - <span style="color: #00F5B8;">正例</span>：MAX_STOCK_COUNT / CACHE_EXPIRED_TIME

     - 反例：MAX_COUNT / EXPIRED_TIME

7. **<span style="color: red;">【强制】</span>** 抽象类命名使用 `Abstract` 或 `Base` 开头；异常类命名使用 `Exception` 结尾，测试类命名以它要测试的类的名称开始，以 `Test` 结尾。
     - <span style="color: #00F5B8;">正例</span>：`AbstractClass` / `BaseClass` / `MyException` / `MyServiceTest`

8. **<span style="color: red;">【强制】</span>** 类型与中括号紧挨相连来定义数组。

     - <span style="color: #00F5B8;">正例</span>：定义整形数组 `int[] arrayDemo`。

     - 反例：在 main 参数中，使用 String args[] 来定义。

9. **<span style="color: red;">【强制】</span>** POJO 类中的任何布尔类型的变量，都不要加 is 前缀，否则部分框架解析会引起序列化错误。
     - <span style="color: #F5B800;">说明</span>：本文 MySQL 规约中的建表约定第 1 条，表达是与否的变量采用 is_xxx 的命名方式，所以需要在 `<resultMap>` 设置从 is_xxx 到 xxx 的映射关系。
     - 反例：定义为基本数据类型 `Boolean isDeleted` 的属性，它的方法也是 `isDeleted()`，框架在反向解析时，“误以为”对 应的属性名称是 `deleted`，导致属性获取不到，进而抛出异常。

10. **<span style="color: red;">【强制】</span>** 包名统一使用小写，点分隔符之间有且仅有一个自然语义的英语单词。包名统一使用单数形式，但是类名如果有复数含义，类名可以使用复数形式。
       - <span style="color: #00F5B8;">正例</span>：应用工具类包名为 com.alibaba.ei.kunlun.aap.util；类名为 MessageUtils（此规则参考 spring 的框架结构）。

11. **<span style="color: red;">【强制】</span>** 避免在子父类的成员变量之间、或者不同代码块的局部变量之间采用完全相同的命名，使可理解性降低。

       - <span style="color: #F5B800;">说明</span>：子类、父类成员变量名相同，即使是 public 也是能够通过编译，而局部变量在同一方法内的不同代码块中同名也是合法的，但是要避免使用。

       - 反例：

```java
public class ConfusingName {
	protected int stock;
	protected String alibaba;
	// 非 setter/getter 的参数名称，不允许与本类成员变量同名
	public void access(String alibaba) {
		if (condition) {
			final int money = 666;
			// ...
		}
		for (int i = 0; i < 10; i++) {
    		// 在同一方法体中，不允许与其它代码块中的 money 命名相同
			final int money = 15978;
			// ...
		}
	}
}
class Son extends ConfusingName {
	// 不允许与父类的成员变量名称相同
	private int stock;
}
```

12. **<span style="color: red;">【强制】</span>** 杜绝完全不规范的英文缩写，避免望文不知义。
       - 反例：AbstractClass“缩写”成 AbsClass；condition“缩写”成 condi；Function“缩写”成 Fu，此类随意缩写严重降低了代码的可阅读性。

13. **【推荐】** 为了达到代码自解释的目标，任何自定义编程元素在命名时，使用完整的单词组合来表达。
       - <span style="color: #00F5B8;">正例</span>：在 JDK 中，对某个对象引用的 `volatile` 字段进行原子更新的类名为 `AtomicReferenceFieldUpdater`。
       - 反例：常见的方法内变量为 `int a;` 的定义方式。

14. **【推荐】** 在常量与变量命名时，表示类型的名词放在词尾，以提升辨识度。
   - <span style="color: #00F5B8;">正例</span>：startTime / workQueue / nameList / TERMINATED_THREAD_COUNT
   - 反例：startedAt / QueueOfWork / listName / COUNT_TERMINATED_THREAD

15. **【推荐】** 如果模块、接口、类、方法使用了设计模式，在命名时要体现出具体模式。
   - <span style="color: #F5B800;">说明</span>：将设计模式体现在名字中，有利于阅读者快速理解架构设计思想。
   - <span style="color: #00F5B8;">正例</span>：`public class OrderFactory`; `public class LoginProxy`; `public class ResourceObserver`;

16. **【推荐】** 接口类中的方法和属性不要加任何修饰符号（public 也不要加），保持代码的简洁性，并加上有效的 Javadoc 注释。尽量不要在接口里定义常量，如果一定要定义，最好确定该常量与接口的方法相关，并且是整个应用的基础常量。
- <span style="color: #00F5B8;">正例</span>：接口方法签名 `void commit();`
  - 接口基础常量 `String COMPANY = "alibaba";`

- 反例：接口方法定义 `public abstract void commit();`
- 说明：JDK8 中接口允许有默认实现，那么这个 default 方法，是对所有实现类都有价值的默认实现

   17. 接口和实现类的命名有两套规则：

        - **<span style="color: red;">【强制】</span>** 对于 Service 和 DAO 类，基于 SOA 的理念，暴露出 来的服务一定是接口，内部的实现类用 Impl 的后缀与接口区别。
          - <span style="color: #00F5B8;">正例</span>：`CacheServiceImpl` 实现 `CacheService` 接口。

        - **【推荐】** 如果是形容能力的接口名称，取对应的形容词为接口名（通常是 –able 结尾的形容词）。
          - <span style="color: #00F5B8;">正例</span>：`AbstractTranslator` 实现 `Translatable`。

18. **【参考】** 枚举类名带上 Enum 后缀，枚举成员名称需要全大写，单词间用下划线隔开。
   - <span style="color: #F5B800;">说明</span>：枚举其实就是特殊的常量类，且构造方法被默认强制是私有。
   - <span style="color: #00F5B8;">正例</span>：枚举名字为 `ProcessStatusEnum` 的成员名称：``SUCCESS` / `UNKNOWN_REASON`

19. **【参考】** 各层命名规约：
   - Service / DAO 层方法命名规约：
       1. 获取单个对象的方法用 get 做前缀。
       2. 获取多个对象的方法用 list 做前缀，复数结尾，如：`listObjects`。
       3. 获取统计值的方法用 count 做前缀。
       4. 插入的方法用 save / insert 做前缀。
       5. 删除的方法用 remove / delete 做前缀。
       6. 修改的方法用 update 做前缀。
   - 领域模型命名规约：
       1. 数据对象：xxxDO，xxx 即为数据表名。
       2. 数据传输对象：xxxDTO，xxx 为业务领域相关的名称。
       3. 展示对象：xxxVO，xxx 一般为网页名称。
       4. POJO 是 DO / DTO / BO / VO 的统称，禁止命名成 xxxPOJO。

## (二) 常量定义

1. **<span style="color: red;">【强制】</span>** 不允许任何魔法值（即未经预先定义的常量）直接出现在代码中。
  - **反例：**
    ```java
    // 开发者 A 定义了缓存的 key。
    String key = "Id#taobao_" + tradeId;
    cache.put(key, value);
    // 开发者 B 使用缓存时直接复制少了下划线，即 key 是"Id#taobao" + tradeId，导致出现故障。
    String key = "Id#taobao" + tradeId;
    cache.get(key);
    ```

2. **<span style="color: red;">【强制】</span>** long 或 Long 赋值时，数值后使用大写 L，不能是小写 l，小写容易跟数字混淆，造成误解。
  - **<span style="color: #F5B800;">说明</span>：** public static final Long NUM = 2l; 写的是数字的 21，还是 Long 型的 2？

3. **<span style="color: red;">【强制】</span>** 浮点数类型的数值后缀统一为大写的 D 或 F。
  - **<span style="color: #00F5B8;">正例</span>：**
    
    ```java
    public static final double HEIGHT = 175.5D;
    public static final float WEIGHT = 150.3F;
    ```

4. **【推荐】** 不要使用一个常量类维护所有常量，要按常量功能进行归类，分开维护。
  - **<span style="color: #F5B800;">说明</span>：** 大而全的常量类，杂乱无章，使用查找功能才能定位到要修改的常量，不利于理解，也不利于维护。
  - **<span style="color: #00F5B8;">正例</span>：** 缓存相关常量放在类 CacheConsts 下；系统配置相关常量放在类 SystemConfigConsts 下。

5. **【推荐】** 常量的复用层次有五层：跨应用共享常量、应用内共享常量、子工程内共享常量、包内共享常量、类内共享常量。
  - **1）** 跨应用共享常量：放置在二方库中，通常是 client.jar 中的 constant 目录下。
  - **2）** 应用内共享常量：放置在一方库中，通常是子模块中的 constant 目录下。
  - **反例：** 易懂常量也要统一定义成应用内共享常量，两个程序员在两个类中分别定义了表示“是”的常量：
    ```java
    类 A 中：public static final String YES = "yes";
    类 B 中：public static final String YES = "y";
    A.YES.equals(B.YES)，预期是 true，但实际返回为 false，导致线上问题。
    ```

6. **【推荐】** 如果变量值仅在一个固定范围内变化用 enum 类型来定义。
  - **<span style="color: #F5B800;">说明</span>：** 如果存在名称之外的延伸属性应使用 enum 类型，下面<span style="color: #00F5B8;">正例</span>中的数字就是延伸信息，表示一年中的第几个季节。
  - **<span style="color: #00F5B8;">正例</span>：**
    ```java
    public enum SeasonEnum {
        SPRING(1), SUMMER(2), AUTUMN(3), WINTER(4);
        private int seq;
        SeasonEnum(int seq) {
            this.seq = seq;
        }
        public int getSeq() {
            return seq;
        }
    }
    ```

## (三) 代码格式

1. **<span style="color: red;">【强制】</span>** 如果大括号内为空，简洁地写成 `{}` 即可，大括号中间无需换行和空格；如果是非空代码块，则：
  - 左大括号前不换行。
  - 左大括号后换行。
  - 右大括号前换行。
  - 右大括号后还有 `else` 等代码则不换行；表示终止的右大括号后必须换行。

2. **<span style="color: red;">【强制】</span>** 左小括号和右边相邻字符之间不需要空格；右小括号和左边相邻字符之间也不需要空格；而左大括号前需要加空格。详见第 5 条下方<span style="color: #00F5B8;">正例</span>提示。

3. **<span style="color: red;">【强制】</span>** `if` / `for` / `while` / `switch` / `do` 等保留字与左右括号之间都必须加空格。

4. **<span style="color: red;">【强制】</span>** 任何二目、三目运算符的左右两边都需要加一个空格。
  - **<span style="color: #F5B800;">说明</span>：** 包括赋值运算符 `=`、逻辑运算符 `&&`、加减乘除符号等。

5. **<span style="color: red;">【强制】</span>** 采用 4 个空格缩进，禁止使用 Tab 字符。
  - **<span style="color: #F5B800;">说明</span>：** 如使用 Tab 缩进，必须设置 1 个 Tab 为 4 个空格。IDEA 设置 Tab 为 4 个空格时，请勿勾选 Use tab character；而在 Eclipse 中，必须勾选 insert spaces for tabs。

6. **<span style="color: red;">【强制】</span>** 注释的双斜线与注释内容之间有且仅有一个空格。

7. **<span style="color: red;">【强制】</span>** 在进行类型强制转换时，右括号与强制转换值之间不需要任何空格隔开。

8. **<span style="color: red;">【强制】</span>** 单行字符数限制不超过 120 个，超出需要换行，换行时遵循如下原则：
  - 第二行相对第一行缩进 4 个空格，从第三行开始，不再继续缩进，参考示例。
  - 运算符与下文一起换行。
  - 方法调用的点符号与下文一起换行。
  - 方法调用中的多个参数需要换行时，在逗号后进行。
  - 在括号前不要换行，见反例。

9. **<span style="color: red;">【强制】</span>** 方法参数在定义和传入时，多个参数逗号后面必须加空格。

10. **<span style="color: red;">【强制】</span>** IDE 的 text file encoding 设置为 UTF-8；IDE 中文件的换行符使用 Unix 格式，不要使用 Windows 格式。

11. **【推荐】** 单个方法的总行数不超过 80 行。
   - **<span style="color: #F5B800;">说明</span>：** 除注释之外的方法签名、左右大括号、方法内代码、空行、回车及任何不可见字符的总行数不超过 80 行。

12. **【推荐】** 没有必要增加若干空格来使变量的赋值等号与上一行对应位置的等号对齐。

13. **【推荐】** 不同逻辑、不同语义、不同业务的代码之间插入一个空行，分隔开来以提升可读性。
   - **<span style="color: #F5B800;">说明</span>：** 任何情形，没有必要插入多个空行进行隔开。

## (四) OOP 规约

1. **<span style="color: red;">【强制】</span>** 避免通过一个类的对象引用访问此类的静态变量或静态方法，无谓增加编译器解析成本，直接用类名来访问即可。

2. **<span style="color: red;">【强制】</span>** 所有的覆写方法，必须加 `@Override` 注解。
  - **<span style="color: #F5B800;">说明</span>：** `getObject()` 与 `get0bject()` 的问题。一个是字母的 O，一个是数字的 0，加 `@Override` 可以准确判断是否覆盖成功。另外，如果在抽象类中对方法签名进行修改，其实现类会马上编译报错。

3. **<span style="color: red;">【强制】</span>** 相同参数类型，相同业务含义，才可以使用的可变参数，参数类型避免定义为 Object。
  - **<span style="color: #F5B800;">说明</span>：** 可变参数必须放置在参数列表的最后。（建议开发者尽量不用可变参数编程）

4. **<span style="color: red;">【强制】</span>** 外部正在调用的接口或者二方库依赖的接口，不允许修改方法签名，避免对接口调用方产生影响。接口过时必须加 `@Deprecated` 注解，并清晰地<span style="color: #F5B800;">说明</span>采用的新接口或者新服务是什么。

5. **<span style="color: red;">【强制】</span>** 不能使用过时的类或方法。
  - **<span style="color: #F5B800;">说明</span>：** `java.net.URLDecoder` 中的方法 `decode(String encodeStr)` 这个方法已经过时，应该使用双参数 `decode(String source, String encode)`。接口提供方既然明确是过时接口，那么有义务同时提供新的接口；作为调用方来说，有义务去考证过时方法的新实现是什么。

6. **<span style="color: red;">【强制】</span>** `Object` 的 `equals` 方法容易抛空指针异常，应使用常量或确定有值的对象来调用 `equals`。
  - **<span style="color: #00F5B8;">正例</span>：** `"test".equals(param);`
  - **反例：** `param.equals("test");`
  - **<span style="color: #F5B800;">说明</span>：** 推荐使用 JDK7 引入的工具类 `java.util.Objects#equals(Object a, Object b)`

7. **<span style="color: red;">【强制】</span>** 所有整型包装类对象之间值的比较，全部使用 `equals` 方法比较。

8. **<span style="color: red;">【强制】</span>** 任何货币金额，均以最小货币单位且为整型类型进行存储。

9. **<span style="color: red;">【强制】</span>** 浮点数之间的等值判断，基本数据类型不能使用 `==` 进行比较，包装数据类型不能使用 `equals` 进行判断。

10. **<span style="color: red;">【强制】</span>** `BigDecimal` 的等值比较应使用 `compareTo()` 方法，而不是 `equals()` 方法。
   - **<span style="color: #F5B800;">说明</span>：** `equals()` 方法会比较值和精度（1.0 与 1.00 返回结果为 false），而 `compareTo()` 则会忽略精度。

11. **<span style="color: red;">【强制】</span>** 定义数据对象 `DO` 类时，属性类型要与数据库字段类型相匹配。

12. **<span style="color: red;">【强制】</span>** 禁止使用构造方法 `BigDecimal(double)` 的方式把 `double` 值转化为 `BigDecimal` 对象。
   - **<span style="color: #F5B800;">说明</span>：** `BigDecimal(double)` 存在精度损失风险，在精确计算或值比较的场景中可能会导致业务逻辑异常。

13. **【关于基本数据类型与包装数据类型的使用标准】**：
   - **<span style="color: red;">【强制】</span>** 所有的 `POJO` 类属性必须使用包装数据类型。
   - **<span style="color: red;">【强制】</span>** RPC 方法的返回值和参数必须使用包装数据类型。
   - **【推荐】** 所有的局部变量使用基本数据类型。

14. **<span style="color: red;">【强制】</span>** 定义 `DO` / `PO` / `DTO` / `VO` 等 `POJO` 类时，不要设定任何属性默认值。

15. **<span style="color: red;">【强制】</span>** 序列化类新增属性时，请不要修改 `serialVersionUID` 字段，避免反序列失败；如果完全不兼容升级，避免反序列化混乱，那么请修改 `serialVersionUID` 值。

16. **<span style="color: red;">【强制】</span>** 构造方法里面禁止加入任何业务逻辑，如果有初始化逻辑，请放在 `init` 方法中。

17. **<span style="color: red;">【强制】</span>** `POJO` 类必须写 `toString` 方法。使用 IDE 中的工具 source > generate toString 时，如果继承了另一个 `POJO` 类，注意在前面加一下 `super.toString()`。

18. **<span style="color: red;">【强制】</span>** 禁止在 `POJO` 类中，同时存在对应属性 `xxx` 的 `isXxx()` 和 `getXxx()` 方法。

19. **【推荐】** 使用索引访问用 `String` 的 `split` 方法得到的数组时，需做最后一个分隔符后有无内容的检查，否则会有抛 `IndexOutOfBoundsException` 的风险。

20. **【推荐】** 当一个类有多个构造方法，或者多个同名方法，这些方法应该按顺序放置在一起，便于阅读，此条规则优先于下一条。

21. **【推荐】** 类内方法定义的顺序依次是：公有方法或保护方法 > 私有方法 > getter / setter 方法。

22. **【推荐】** `setter` 方法中，参数名称与类成员变量名称一致，`this.成员名=参数名`。在 `getter / setter` 方法中，不要增加业务逻辑，增加排查问题的难度。

23. **【推荐】** 循环体内，字符串的连接方式，使用 `StringBuilder` 的 `append` 方法进行扩展。

24. **【推荐】** `final` 可以声明类、成员变量、方法、以及本地变量，下列情况使用 `final` 关键字：
   - 不允许被继承的类，如：`String` 类。
   - 不允许修改引用的域对象，如：`POJO` 类的域变量。
   - 不允许被覆写的方法，如：`POJO` 类的 `setter` 方法。
   - 不允许运行过程中重新赋值的局部变量。
   - 避免上下文重复使用一个变量，使用 `final` 关键字可以强制重新定义一个变量，方便更好地进行重构。

25. **【推荐】** 慎用 `Object` 的 `clone` 方法来拷贝对象。
   - **<span style="color: #F5B800;">说明</span>：** 对象 `clone` 方法默认是浅拷贝，若想实现深拷贝需覆写 `clone` 方法实现域对象的深度遍历式拷贝。

26. **【推荐】** 类成员与方法访问控制从严：
   - 如果不允许外部直接通过 `new` 来创建对象，那么构造方法必须是 `private`。
   - 工具类不允许有 `public` 或 `default` 构造方法。
   - 类非 `static` 成员变量并且与子类共享，必须是 `protected`。
   - 类非 `static` 成员变量并且仅在本类使用，必须是 `private`。
   - 类 `static` 成员变量如果仅在本类使用，必须是 `private`。
   - 若是 `static` 成员变量，考虑是否为 `final`。
   - 类成员方法只供类内部调用，必须是 `private`。
   - 类成员方法只对继承类公开，那么限制为 `protected`。
   - <span style="color: #F5B800;">说明</span>：任何类、方法、参数、变量，严控访问范围。过于宽泛的访问范围，不利于模块解耦。

## (五) 日期时间

1. **<span style="color: red;">【强制】</span>** 日期格式化时，传入 pattern 中表示年份统一使用小写的 y。
  - **<span style="color: #F5B800;">说明</span>：** 日期格式化时，yyyy 表示当天所在的年，而大写的 YYYY 代表是 week in which year（JDK7 之后引入的概念），意思是当天所在的周属于的年份，一周从周日开始，周六结束，只要本周跨年，返回的 YYYY 就是下一年。

2. **<span style="color: red;">【强制】</span>** 在日期格式中分清楚大写的 M 和小写的 m，大写的 H 和小写的 h 分别指代的意义。
  - **<span style="color: #F5B800;">说明</span>：** 日期格式中的这两对字母表意如下：
    - 表示月份是大写的 M
    - 表示分钟则是小写的 m
    - 24 小时制的是大写的 H
    - 12 小时制的则是小写的 h

3. **<span style="color: red;">【强制】</span>** 获取当前毫秒数：`System.currentTimeMillis()`；而不是 `new Date().getTime()`.
  - **<span style="color: #F5B800;">说明</span>：** 获取纳秒级时间，则使用 `System.nanoTime()` 的方式。在 JDK8 中，针对统计时间等场景，推荐使用 `Instant` 类。

4. **<span style="color: red;">【强制】</span>** 不允许在程序任何地方中使用：1）`java.sql.Date` 2）`java.sql.Time` 3）`java.sql.Timestamp`.
  - **<span style="color: #F5B800;">说明</span>：** 第 1 个不记录时间，`getHours()` 抛出异常；第 2 个不记录日期，`getYear()` 抛出异常；第 3 个在构造方法 `super((time / 1000) * 1000)`，在 `Timestamp` 属性 `fastTime` 和 `nanos` 分别存储秒和纳秒信息。

5. **<span style="color: red;">【强制】</span>** 禁止在程序中写死一年为 365 天，避免在公历闰年时出现日期转换错误或程序逻辑错误。
  - **<span style="color: #00F5B8;">正例</span>：**
    
    ```java
    // 获取今年的天数
    int daysOfThisYear = LocalDate.now().lengthOfYear();
    // 获取指定某年的天数
    LocalDate.of(2011, 1, 1).lengthOfYear();
    ```

6. **【推荐】** 避免公历闰年 2 月问题。闰年的 2 月份有 29 天，一年后的那一天不可能是 2 月 29 日。

7. **【推荐】** 使用枚举值来指代月份。如果使用数字，注意 `Date`，`Calendar` 等日期相关类的月份 month 取值范围从 0 到 11 之间。
  - **<span style="color: #F5B800;">说明</span>：** 参考 JDK 原生注释，`Month` value is 0-based. e.g., 0 for January.

8. **【推荐】** 在无泛型限制定义的集合赋值给泛型限制的集合时，在使用集合元素时，需要进行 `instanceof` 判断，避免抛出 `ClassCastException` 异常。

## (六) 集合处理

1. **<span style="color: red;">【强制】</span>** 关于 `hashCode` 和 `equals` 的处理，遵循如下规则：
  - 只要覆写 `equals`，就必须覆写 `hashCode`。
  - 因为 `Set` 存储的是不重复的对象，依据 `hashCode` 和 `equals` 进行判断，所以 `Set` 存储的对象必须覆写这两种方法。
  - 如果自定义对象作为 `Map` 的键，那么必须覆写 `hashCode` 和 `equals`。

2. **<span style="color: red;">【强制】</span>** 判断所有集合内部的元素是否为空，使用 `isEmpty()` 方法，而不是 `size() == 0` 的方式。
  - **<span style="color: #F5B800;">说明</span>：** 在某些集合中，前者的时间复杂度为 O(1)，而且可读性更好。

3. **<span style="color: red;">【强制】</span>** 在使用 `java.util.stream.Collectors` 类的 `toMap()` 方法转为 `Map` 集合时，一定要使用参数类型为 `BinaryOperator`，参数名为 `mergeFunction` 的方法，否则当出现相同 key 时会抛出 `IllegalStateException` 异常。
  - **<span style="color: #F5B800;">说明</span>：** 参数 `mergeFunction` 的作用是当出现 key 重复时，自定义对 value 的处理策略。

4. **<span style="color: red;">【强制】</span>** 在使用 `java.util.stream.Collectors` 类的 `toMap()` 方法转为 `Map` 集合时，一定要注意当 value 为 null 时会抛 `NullPointerException` 异常。

5. **<span style="color: red;">【强制】</span>** `ArrayList` 的 `subList` 结果不可强转成 `ArrayList`，否则会抛出 `ClassCastException` 异常：`java.util.RandomAccessSubList cannot be cast to java.util.ArrayList`。

6. **<span style="color: red;">【强制】</span>** 使用 `Map` 的方法 `keySet()` / `values()` / `entrySet()` 返回集合对象时，不可以对其进行添加元素操作，否则会抛出 `UnsupportedOperationException` 异常。

7. **<span style="color: red;">【强制】</span>** `Collections` 类返回的对象，如：`emptyList()` / `singletonList()` 等都是 immutable list，不可对其进行添加或者删除元素的操作。

8. **<span style="color: red;">【强制】</span>** 在 `subList` 场景中，高度注意对父集合元素的增加或删除，均会导致子列表的遍历、增加、删除产生 `ConcurrentModificationException` 异常。

9. **<span style="color: red;">【强制】</span>** 使用集合转数组的方法，必须使用集合的 `toArray(T[] array)`，传入的是类型完全一致、长度为 0 的空数组。

10. **<span style="color: red;">【强制】</span>** 使用 `Collection` 接口任何实现类的 `addAll()` 方法时，要对输入的集合参数进行 `NPE` 判断。

11. **<span style="color: red;">【强制】</span>** 使用工具类 `Arrays.asList()` 把数组转换成集合时，不能使用其修改集合相关的方法，它的 `add` / `remove` / `clear` 方法会抛出 `UnsupportedOperationException` 异常。

12. **<span style="color: red;">【强制】</span>** 泛型通配符 `<? extends T>` 来接收返回的数据，此写法的泛型集合不能使用 `add` 方法，而 `<? super T>` 不能使用 `get` 方法，两者在接口调用赋值的场景中容易出错。

13. **<span style="color: red;">【强制】</span>** 在无泛型限制定义的集合赋值给泛型限制的集合时，在使用集合元素时，需要进行 `instanceof` 判断，避免抛出 `ClassCastException` 异常。

14. **<span style="color: red;">【强制】</span>** 不要在 `foreach` 循环里进行元素的 `remove` / `add` 操作。`remove` 元素请使用 `iterator` 方式，如果并发操作，需要对 `iterator` 对象加锁。

15. **<span style="color: red;">【强制】</span>** 在 JDK7 版本及以上，`Comparator` 实现类要满足如下三个条件，不然 `Arrays.sort`, `Collections.sort` 会抛 `IllegalArgumentException` 异常。

16. **【推荐】** 泛型集合使用时，在 JDK7 及以上，使用 diamond 语法或全省略。

17. **【推荐】** 集合初始化时，指定集合初始值大小。

18. **【推荐】** 使用 `entrySet` 遍历 `Map` 类集合 KV，而不是 `keySet` 方式进行遍历。

19. **【推荐】** 高度注意 `Map` 类集合 K / V 能不能存储 null 值的情况。

20. **【参考】** 合理利用好集合的有序性（sort）和稳定性（order），避免集合的无序性（unsort）和不稳定性（unorder）带来的负面影响。

21. **【参考】** 利用 `Set` 元素唯一的特性，可以快速对一个集合进行去重操作，避免使用 `List` 的 `contains()` 进行遍历去重或者判断包含操作。

## (七) 并发处理

1. **<span style="color: red;">【强制】</span>** 获取单例对象需要保证线程安全，其中的方法也要保证线程安全。资源驱动类、工具类、单例工厂类都需要注意。

2. **<span style="color: red;">【强制】</span>** 创建线程或线程池时请指定有意义的线程名称，方便出错时回溯。

3. **<span style="color: red;">【强制】</span>** 线程资源必须通过线程池提供，不允许在应用中自行显式创建线程。

4. **<span style="color: red;">【强制】</span>** 线程池不允许使用 Executors 去创建，而是通过 ThreadPoolExecutor 的方式，这样的处理方式让写的同学更加明确线程池的运行规则，规避资源耗尽的风险。

5. **<span style="color: red;">【强制】</span>** `SimpleDateFormat` 是线程不安全的类，一般不要定义为 static 变量，如果定义为 static，必须加锁，或者使用 `DateUtils` 工具类。

6. **<span style="color: red;">【强制】</span>** 必须回收自定义的 `ThreadLocal` 变量，尤其在线程池场景下，线程经常会被复用，如果不清理自定义的 `ThreadLocal` 变量，可能会影响后续业务逻辑和造成内存泄露等问题。尽量在代理中使用 try-finally 块进行回收。

7. **<span style="color: red;">【强制】</span>** 高并发时，同步调用应该去考量锁的性能损耗。能用无锁数据结构，就不要用锁；能锁区块，就不要锁整个方法体；能用对象锁，就不要用类锁。

8. **<span style="color: red;">【强制】</span>** 对多个资源、数据库表、对象同时加锁时，需要保持一致的加锁顺序，否则可能会造成死锁。

9. **<span style="color: red;">【强制】</span>** 在使用阻塞等待获取锁的方式中，必须在 try 代码块之外，并且在加锁方法与 try 代码块之间没有任何可能抛出异常的方法调用，避免加锁成功后，在 finally 中无法解锁。

10. **<span style="color: red;">【强制】</span>** 在使用尝试机制来获取锁的方式中，进入业务代码块之前，必须先判断当前线程是否持有锁。锁的释放规则与锁的阻塞等待方式相同。

11. **<span style="color: red;">【强制】</span>** 并发修改同一记录时，避免更新丢失，需要加锁。要么在应用层加锁，要么在缓存加锁，要么在数据库层使用乐观锁，使用 version 作为更新依据。

12. **<span style="color: red;">【强制】</span>** 多线程并行处理定时任务时，`Timer` 运行多个 `TimeTask` 时，只要其中之一没有捕获抛出的异常，其它任务便会自动终止运行，使用 `ScheduledExecutorService` 则没有这个问题。

13. **【推荐】** 资金相关的金融敏感信息，使用悲观锁策略。

14. **【推荐】** 使用 `CountDownLatch` 进行异步转同步操作，每个线程退出前必须调用 `countDown` 方法，线程执行代码注意 catch 异常，确保 `countDown` 方法被执行到，避免主线程无法执行至 `await` 方法，直到超时才返回结果。

15. **【推荐】** 避免 `Random` 实例被多线程使用，虽然共享该实例是线程安全的，但会因竞争同一 seed 导致的性能下降。

16. **【推荐】** 通过双重检查锁（double-checked locking），实现延迟初始化需要将目标属性声明为 volatile 型，（比如修改 helper 的属性声明为 private volatile Helper helper = null;）。

17. **【参考】** `volatile` 解决多线程内存不可见问题对于一写多读，是可以解决变量同步问题，但是如果多写，同样无法解决线程安全问题。

18. **【参考】** `HashMap` 在容量不够进行 resize 时由于高并发可能出现死链，导致 CPU 飙升，在开发过程中注意规避此风险。

19. **【参考】** `ThreadLocal` 对象使用 static 修饰，`ThreadLocal` 无法解决共享对象的更新问题。

## (八) 控制语句

1. **<span style="color: red;">【强制】</span>** 在一个 `switch` 块内，每个 `case` 要么通过 `continue` / `break` / `return` 等来终止，要么注释<span style="color: #F5B800;">说明</span>程序将继续执行到哪一个 `case` 为止；在一个 `switch` 块内，都必须包含一个 `default` 语句并且放在最后，即使它什么代码也没有。

2. **<span style="color: red;">【强制】</span>** 当 `switch` 括号内的变量类型为 `String` 并且此变量为外部参数时，必须先进行 `null` 判断。

3. **<span style="color: red;">【强制】</span>** 在 `if` / `else` / `for` / `while` / `do` 语句中必须使用大括号。

4. **<span style="color: red;">【强制】</span>** 三目运算符 `condition ? 表达式 1 : 表达式 2` 中，高度注意表达式 1 和 2 在类型对齐时，可能抛出因自动拆箱导致的 `NPE` 异常。

5. **<span style="color: red;">【强制】</span>** 在高并发场景中，避免使用“等于”判断作为中断或退出的条件。

6. **【推荐】** 当方法的代码总行数超过 10 行时，`return` / `throw` 等中断逻辑的右大括号后需要加一个空行。

7. **【推荐】** 表达异常的分支时，少用 `if-else` 方式，这种方式可以改写成：

  ```java
  if (condition) {
      // ...
      return obj;
  }
  // 接着写 else 的业务逻辑代码;
  ```

8. **【推荐】** 除常用方法（如 `getXxx` / `isXxx`）等外不要在条件判断中执行其它复杂的语句，将复杂逻辑判断的结果赋值给一个有意义的布尔变量名，以提高可读性。

9. **【推荐】** 不要在其它表达式（尤其是条件表达式）中，插入赋值语句。

10. **【推荐】** 循环体中的语句要考量性能，以下操作尽量移至循环体外处理，如定义对象、变量、获取数据库连接，进行不必要的 `try-catch` 操作（这个 `try-catch` 是否可以移至循环体外）。
11. **【推荐】** 避免采用取反逻辑运算符。
12. **【推荐】** 公开接口需要进行入参保护，尤其是批量操作的接口。
13. **【参考】** 下列情形，需要进行参数校验：

- 调用频次低的方法。
- 执行时间开销很大的方法。
- 需要极高稳定性和可用性的方法。
- 对外提供的开放接口，不管是 RPC / API / HTTP 接口。
- 敏感权限入口。

14. **【参考】** 下列情形，不需要进行参数校验：

- 极有可能被循环调用的方法。
- 底层调用频度比较高的方法。
- 被声明成 `private` 只会被自己代码所调用的方法

## (九) 注释规约

1. **<span style="color: red;">【强制】</span>** 类、类属性、类方法的注释必须使用 Javadoc 规范，使用 `/** 内容 */` 格式，不得使用 `// xxx` 方式。
2. **<span style="color: red;">【强制】</span>** 所有的抽象方法（包括接口中的方法）必须要用 Javadoc 注释，除了返回值、参数异常<span style="color: #F5B800;">说明</span>外，还必须指出该方法做什么事情，实现什么功能。
3. **<span style="color: red;">【强制】</span>** 所有的类都必须添加创建者和创建日期。
4. **<span style="color: red;">【强制】</span>** 方法内部单行注释，在被注释语句上方另起一行，使用 `//` 注释。方法内部多行注释使用 `/* */` 注释，注意与代码对齐。
5. **<span style="color: red;">【强制】</span>** 所有的枚举类型字段必须要有注释，<span style="color: #F5B800;">说明</span>每个数据项的用途。
6. **【推荐】** 与其用半吊子英文来注释，不如用中文注释说清楚。专有名词与关键字保持英文原文即可。
7. **【推荐】** 代码修改的同时，注释也要进行相应的修改，尤其是参数、返回值、异常、核心逻辑等。
8. **【推荐】** 在类中删除未使用的任何字段和方法、内部类；在方法中删除未使用的参数声明与内部变量。
9. **【参考】** 谨慎注释掉代码。在上方详细<span style="color: #F5B800;">说明</span>，而不是简单地注释掉。如果无用，则删除。
10. **【参考】** 对于注释的要求：第一、能够准确反映设计思想和代码逻辑；第二、能够描述业务含义，使别的程序员能够迅速了解到代码背后的信息。
11. **【参考】** 好的命名、代码结构是自解释的，注释力求精简准确、表达到位。避免出现注释的另一个极端：过多过滥的注释，代码的逻辑一旦修改，修改注释又是相当大的负担。

