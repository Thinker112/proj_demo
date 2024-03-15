# RESTful API



大多数现代Web应用程序都通过暴露API的方式与客户端进行交互的。精心设计的Web API应旨在支持：

- 平台独立性：任何客户端都应该能够调用API，无论API是如何在内部实现的。这需要使用标准协议，并有一种机制，客户端和Web服务能够对交换的数据格式达成一致。

- 服务演进： web API 应该能够独立于客户端应用程序发展和添加功能。随着API的发展，现有的客户端应用程序应该继续运行而无需修改。所有功能都应该是可发现的，以便客户端应用程序可以充分使用它。

## 什么是REST

2000年，Roy Fielding提出了**R**epresentational **S**tat**e** **T**ransfer (表现层状态转换 )（REST）作为设计Web服务的架构方法。REST是一种用于构建基于超媒体的分布式系统的架构风格。REST独立于任何底层协议，不一定绑定到HTTP。但是，大多数常见的REST API实现使用HTTP作为应用程序协议，本指南重点介绍为HTTP设计REST API。

REST 相对于 HTTP 的主要优点是它使用开放标准，并且不会将 API 的实现或客户端应用程序绑定到任何特定的实现。例如，REST Web服务可以用ASP.NET编写，客户端应用程序可以使用任何可以生成HTTP请求和解析HTTP响应的语言或工具集。

以下是使用HTTP的RESTful API的一些主要设计原则：

- REST API是围绕资源设计的，资源是客户端可以访问的任何类型的对象、数据或服务。

- 资源有一个标识符，它是唯一标识该资源的URI。例如，特定客户订单的URI可能是：

  ```
  https://adventure-works.com/orders/1
  ```

- 客户端通过交换资源表示与服务交互。许多Web API使用JSON作为交换格式。例如，对上面列出的URI的GET请求可能会返回以下响应正文：

  ```json
  {"orderId":1,"orderValue":99.90,"productId":1,"quantity":1}
  ```

- REST API 使用统一的接口，这有助于解耦客户端和服务实现。对于基于HTTP构建的REST API，统一接口包括使用标准HTTP动词对资源执行操作。最常见的操作是GET、POST、PUT、PATCH和DELETE。

- REST API使用无状态请求模型。HTTP请求应该是独立的，并且可能以任何顺序发生，因此在请求之间保留瞬态状态信息是不可行的。存储信息的唯一位置是资源本身，每个请求都应该是一个原子操作。此约束使 Web 服务具有高度可扩展性，因为无需在客户端和特定服务器之间保留任何关联性。任何服务器都可以处理来自任何客户端的任何请求。也就是说，其他因素可能会限制可扩展性。例如，许多Web服务写入后端数据存储，这可能很难横向扩展。有关横向扩展数据存储的策略的详细信息，请参阅[Horizontal, vertical, and functional data partitioning](https://learn.microsoft.com/en-us/azure/architecture/best-practices/data-partitioning)。

- REST API由表示中包含的超媒体链接驱动。例如，下面显示了订单的JSON表示。它包含获取或更新与订单关联的客户的链接。

  ```json
  {
    "orderID":3,
    "productID":2,
    "quantity":4,
    "orderValue":16.60,
    "links": [
      {"rel":"product","href":"https://adventure-works.com/customers/3", "action":"GET" },
      {"rel":"product","href":"https://adventure-works.com/customers/3", "action":"PUT" }
    ]
  }
  ```

2008 年，Leonard Richardson 提出了以下 Web API  [maturity model](https://martinfowler.com/articles/richardsonMaturityModel.html)：

- Level 0: 定义一个URI，所有操作都是对该URI的POST请求。
- Level 1: 为单个资源创建单独的URI。
- Level 2: 使用HTTP方法定义对资源的操作。
- Level 3: 使用超媒体（HATEOAS，如下所述）。

根据 Roy Fielding的定义，Level 3对应于真正的 RESTful API。 实际上，许多已发布的 Web API 都处于 Level 2左右。

## 围绕资源进行 API 设计

































## 参考文档

- [Azure best practices api design](https://learn.microsoft.com/en-us/azure/architecture/best-practices/api-design)

- [codecademy- What-is-REST](https://www.codecademy.com/article/what-is-rest)