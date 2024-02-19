package com.example.demo.mapper;

import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <code>@MybatisTest</code> 在默认情况下将会探测到带有 <code>@SpringBootApplication</code> 的类。 因此，由于 bean 定义的一些方法，可能会发生一些意想不到的错误，或者一些不必要的组件被装入 ApplicationContext <br>
 * 为了避免这种情况，我们可以在与测试类相同的包中创建带有 <code>@SpringBootApplication</code>的类。
 */
@SpringBootApplication
class MapperTestApplication {

}