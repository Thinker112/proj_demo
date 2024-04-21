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

专注于 Web API 公开的业务实体。例如，在电子商务系统中，主要实体可能是客户和订单。可以通过发送包含订单信息的HTTP POST请求来创建订单。HTTP响应指示是否下单成功。如果可能，资源URI应基于**名词**（资源）而不是动词（对资源的操作）。

```
https://adventure-works.com/orders // Good

https://adventure-works.com/create-order // Avoid
```

资源不必基于单个物理数据项。 例如，订单资源可能在内部实现为关系数据库中的多个表，但作为单个实体呈现给客户端。避免创建简单地反映数据库内部结构的API。REST的目的是对实体和应用程序可以对这些实体执行的操作进行建模。客户端不应该暴露于内部实现。

实体通常被分组为集合（订单、客户）。 集合是与集合中的项目分开的资源，并且应该有自己的 URI。 例如，以下 URI 可能表示订单的集合：

```
https://adventure-works.com/orders
```

向集合URI发送HTTP GET请求会检索集合中的项目列表。集合中的每个项目也有自己唯一的URI。对项目URI的HTTP GET请求返回该项目的详细信息。

在URI中采用一致的命名约定。通常，对引用集合的URI使用复数名词会有所帮助。将集合和项目的URI组织到层次结构中是一种很好的做法。例如，` /customers`是客户集合的路径， `/customers/5`是ID等于5的客户的路径。这种方法有助于保持Web API的直观性。此外，许多Web API框架可以基于参数化的URI路径路由请求，因此您可以为路径`/customers/{id}`定义路由。

还要考虑不同类型资源之间的关系以及如何公开这些关联。例如， `/customers/5/orders`可能代表客户5的所有订单。换个角度，使用 URI（例如` /orders/99/customer`）表示从订单到客户的关联。但是，将此模型扩展得太远可能会变得难以实施。更好的解决方案是在HTTP响应消息的正文中提供指向相关资源的可导航链接。此机制在使用HATEOAS启用相关资源的导航部分中有更详细的描述。[Use HATEOAS to enable navigation to related resources](https://learn.microsoft.com/en-us/azure/architecture/best-practices/api-design#use-hateoas-to-enable-navigation-to-related-resources).

在更复杂的系统中，很容易提供 URI，使客户端能够浏览多个级别的关系，例如` /customers/1/orders/99/products`。然而，如果将来资源之间的关系发生变化，这种复杂程度可能难以维持，并且是不灵活的。相反，尽量保持URI相对简单。一旦应用程序具有对资源的引用，就应该可以使用此引用来查找与该资源相关的项目。前面的查询可以替换为URI `/customers/1/orders`查找客户1的所有订单，然后` /orders/99/products`查找该订单中的产品。

> [!TIP]
>
> 避免需要比`collection/item/collection`更复杂的资源URI。

另一个因素是所有Web请求都会对Web服务器施加负载。请求越多，负载就越大。因此，尽量避免暴露大量小资源的Web API。这样的API可能要求客户端应用程序发送多个请求来查找它需要的所有数据。相反，您可能希望对数据进行非规范化，并将相关信息组合成可以通过单个请求检索的更大资源。但是，您需要平衡这种方法与获取客户端不需要的数据的开销。检索大型对象会增加请求的延迟并产生额外的带宽成本。有关这些对性能造成影响的API的更多信息，参阅[Chatty I/O](https://learn.microsoft.com/en-us/azure/architecture/antipatterns/chatty-io/) 和 [Extraneous Fetching](https://learn.microsoft.com/en-us/azure/architecture/antipatterns/extraneous-fetching/)

避免在Web API和底层数据源之间引入依赖关系。例如，如果您的数据存储在关系数据库中，则Web API不需要将每个表公开为资源集合。事实上，这可能是一个糟糕的设计。相反，将Web API视为数据库的抽象。如有必要，在数据库和Web API之间引入映射层。

这样，客户端应用程序就与底层数据库方案的更改隔离开来。

最后，可能无法将Web API实现的每个操作映射到特定资源。您可能通过调用函数并将结果作为HTTP响应消息返回的HTTP请求来处理此类非资源场景。例如，实现简单计算器操作（例如加减）的Web API可以提供将这些操作公开为伪资源并使用查询字符串指定所需参数的URI。例如，对URI` /add?operand1=99&operand2=1`的GET请求将返回一个响应消息，其正文包含值100。但是，请谨慎使用这些形式的URI。

## 根据HTTP方法定义API操作

HTTP协议定义了许多为请求分配语义含义的方法。大多数RESTful Web API使用的常见HTTP方法是：

- **GET** 检索指定 URI 处资源的表示形式。 响应消息的正文包含所请求资源的详细信息。

- **POST** 在指定的URI处创建新资源。请求消息的正文提供新资源的详细信息。请注意，POST还可用于触发实际上并未创建资源的操作。

- **PUT**  在指定的URI处创建或替换资源。请求消息的正文指定要创建或更新的资源。

- **PATCH**  执行资源的部分更新。请求正文指定要应用于资源的更改集。
- **DELETE** 删除指定URI处的资源。

特定请求的效果应取决于资源是集合还是单个项目。下表总结了使用电子商务示例的大多数RESTful实现采用的常见约定。并非所有这些请求都可以实现-这取决于具体场景。

| **Resource**          | **POST**          | **GET**             | **PUT**                         | **DELETE**          |
| :-------------------- | :---------------- | :------------------ | :------------------------------ | :------------------ |
| `/customers`          | 创建新客户        | 检索所有客户        | 批量更新客户                    | 删除所有客户        |
| `/customers/1`        | Error             | 检索客户1的详细信息 | 更新客户1的详细信息（如果存在） | 删除客户1           |
| `/customers/1/orders` | 为客户1创建新订单 | 检索客户1的所有订单 | 批量更新客户1的订单             | 删除客户1的所有订单 |

POST、PUT和PATCH之间的区别可能会令人困惑。

- POST请求创建一个资源。服务器为新资源分配一个URI，并将该URI返回给客户端。在REST模型中，您经常将POST请求应用于集合。新资源被添加到集合中。POST请求还可用于将数据提交给现有资源进行处理，而无需创建任何新资源。

- PUT 请求创建资源或更新现有资源。 客户端指定资源的 URI。 请求正文包含资源的完整表示。 如果具有此 URI 的资源已存在，则会将其替换。 否则，如果服务器支持，则会创建新资源。 PUT 请求最常应用于单个项目的资源，例如特定客户，而不是集合。 服务器可能支持更新，但不支持通过 PUT 创建。 是否支持通过 PUT 创建取决于客户端是否可以在资源存在之前将 URI 有意义地分配给该资源。 如果没有，则使用 POST 创建资源并使用 PUT 或 PATCH 进行更新。

- PATCH 请求对现有资源执行部分更新。 客户端指定资源的 URI。 请求正文指定一组要应用于资源的更改。 这比使用 PUT 更有效，因为客户端仅发送更改，而不是资源的整个表示。 从技术上讲，如果服务器支持的话，PATCH 还可以创建新资源（通过指定对“空”资源的一组更新）。

**PUT请求必须是幂等的**。如果客户端多次提交相同的PUT请求，结果应该始终相同（相同的资源将被修改为相同的值）。POST和PATCH请求不能保证是幂等的。

## 符合HTTP语义

本节介绍设计符合HTTP规范的API的一些典型注意事项。但是，它并没有涵盖所有可能的细节或场景。如有疑问，请查阅HTTP规范。

### 媒体类型

如前所述，客户端和服务器交换资源的表示。 例如，在 POST 请求中，请求正文包含要创建的资源的表示。 在 GET 请求中，响应正文包含所获取资源的表示。

在 HTTP 协议中，通过使用媒体类型（也称为 MIME 类型）来指定格式。对于非二进制数据，大多数Web API支持JSON（media type = `application/json`），可能还支持XML（media type = `application/xml`）。

请求或响应中的Content-Type标头指定表示的格式。这是包含JSON数据的POST请求示例：

```json
POST https://adventure-works.com/orders HTTP/1.1
Content-Type: application/json; charset=utf-8
Content-Length: 57

{"Id":1,"Name":"Gizmo","Category":"Widgets","Price":1.99}
```

如果服务器不支持媒体类型，它应该返回HTTP状态码415（不支持的媒体类型）。

### GET methods

成功的GET方法通常返回HTTP状态码200（OK）。如果找不到资源，该方法应返回404（未找到）。

如果请求已完成，但HTTP响应中没有包含响应正文，则它应该返回HTTP状态码204（无内容）；例如，可以使用此行为实现没有匹配的搜索操作。

### POST methods

如果POST方法创建了一个新资源，它会返回HTTP状态代码201（Created）。新资源的URI包含在响应的位置标头中。响应正文包含资源的表示。

如果该方法做了一些处理但没有创建新资源，则该方法可以返回HTTP状态码200并在响应正文中包括该操作的结果。或者，如果没有要返回的结果，则该方法可以返回没有响应正文的HTTP状态码204（No Content）。

如果客户端将无效数据放入请求中，服务器应返回HTTP状态代码400（错误请求）。响应正文可以包含有关错误的附加信息或提供更多详细信息的URI链接。

### PUT methods

如果PUT方法创建了一个新资源，它会返回HTTP状态代码201（Created），就像POST方法一样。如果该方法更新了现有资源，它会返回200（OK）或204（No Content）。在某些情况下，可能无法更新现有资源。在这种情况下，请考虑返回HTTP状态代码409（冲突）。

考虑实施批量 HTTP PUT 操作，该操作可以批量更新集合中的多个资源。 PUT 请求应指定集合的 URI，请求正文应指定要修改的资源的详细信息。 这种方法可以帮助减少与服务器交互次数并提高性能。

### PATCH methods

通过 PATCH 请求，客户端以补丁文档的形式向现有资源发送一组更新。 服务器处理补丁文档以执行更新。 补丁文档不描述整个资源，仅描述一组要应用的更改。PATCH方法规范（[RFC 5789](https://datatracker.ietf.org/doc/html/rfc5789)）没有为补丁文档定义特定格式。格式必须从请求中的媒体类型推断出来。

JSON可能是Web API最常见的数据格式。有两种主要的基于JSON的补丁格式，称为JSON补丁和JSON合并补丁。

JSON合并补丁稍微简单一些。补丁文档与原始JSON资源具有相同的结构，但只包含应该更改或添加的字段子集。此外，可以通过为补丁文档中的字段值指定null来删除字段。（这意味着如果原始资源可以有明确的null值，则合并补丁不合适。）

例如，假设原始资源具有以下JSON表示：

```json
{
    "name":"gizmo",
    "category":"widgets",
    "color":"blue",
    "price":10
}
```

这是此资源的可能JSON合并补丁：

```json
{
    "price":12,
    "color":null,
    "size":"small"
}
```

这告诉服务器更新price、删除color和添加size，而不修改name和category。有关JSON合并补丁的确切详细信息，请参阅[RFC 7396](https://datatracker.ietf.org/doc/html/rfc7396#/)。JSON合并补丁的媒体类型是`application/merge-patch+json`.

如果原始资源可以包含显式空值，则合并补丁不适合，因为补丁文档中null的特殊含义（删除）。此外，补丁文档没有指定服务器应用更新的顺序。这可能重要也可能无关紧要，具体取决于数据和领域。[RFC 6902](https://datatracker.ietf.org/doc/html/rfc6902#/)中定义的JSON补丁更加灵活。它将更改指定为要应用的操作序列。操作包括添加、删除、替换、复制和测试（以验证值）。JSON补丁的媒体类型是`application/json-patch+json`.

以下是处理PATCH请求时可能遇到的一些典型错误情况，以及适当的HTTP状态代码。

| Error condition                                | HTTP status code             |
| :--------------------------------------------- | :--------------------------- |
| 不支持补丁文档格式。                           | 415 (Unsupported Media Type) |
| 格式错误的补丁文档。                           | 400 (Bad Request)            |
| 补丁文档有效，但更改无法应用于当前状态的资源。 | 409 (Conflict)               |

### DELETE methods

如果删除操作成功，Web 服务器应使用 HTTP 状态代码 204（无内容）进行响应，表明该过程已成功处理，但响应正文不包含更多信息。 如果资源不存在，Web 服务器会返回 HTTP 404（未找到）。

### 异步操作

有时POST、PUT、PATCH或DELETE操作可能需要一段时间才能完成。如果在向客户端发送响应之前等待完成，可能会导致不可接受的延迟。如果是这样，请考虑使操作异步。返回HTTP状态代码202（接受）以指示请求已被接受以进行处理但未完成。

您应该公开一个返回异步请求状态的端点，以便客户端可以通过轮询状态端点来监控状态。在202响应的位置标头中包含状态端点的URI。例如：

```json
HTTP/1.1 202 Accepted
Location: /api/status/12345
```

如果客户端向该端点发送GET请求，则响应应包含请求的当前状态。或者，它还可以包括预计完成时间或取消操作的链接。

```json
HTTP/1.1 200 OK
Content-Type: application/json

{
    "status":"In progress",
    "link": { "rel":"cancel", "method":"delete", "href":"/api/status/12345" }
}
```

如果异步操作创建新资源，状态端点应在操作完成后返回状态码303（See Other）。在303响应中，包含一个给出新资源URI的位置标头：

```json
HTTP/1.1 303 See Other
Location: /api/orders/12345
```

有关如何实现此方法的更多信息，请参阅 [Providing asynchronous support for long-running requests](https://learn.microsoft.com/en-us/azure/architecture/best-practices/api-implementation#provide-asynchronous-support-for-long-running-requests) and the [Asynchronous Request-Reply pattern](https://learn.microsoft.com/en-us/azure/architecture/patterns/async-request-reply).

### 消息体中的空集

每当成功响应的正文为空时，状态代码都应该是204（无内容）。对于空集，例如对没有item的过滤请求的响应，状态代码仍然应该是204（无内容），而不是200（确定）。

## 过滤和分页数据

通过单个URI公开资源集合可能会导致应用程序在只需要信息的子集时获取大量数据。例如，假设客户端应用程序需要查找成本超过特定值的所有订单。它可能从 `/orders`URI检索所有订单，然后在客户端过滤这些订单。显然这个过程效率很低。它浪费了托管Web API的服务器上的网络带宽和处理能力。

取而代之的是，API可以允许在URI的查询字符串中传递一个过滤器，例如 `/orders?minCost=n`。然后，Web API负责解析和处理查询字符串中的`minCost`参数，并在服务器端返回过滤后的结果。

对集合资源的GET请求可能会返回大量item。您应该设计一个Web API来限制任何单个请求返回的数据量。考虑支持指定要检索的最大项目数和集合的起始偏移量的查询字符串。例如：

```json
/orders?limit=25&offset=50
```

还可以考虑对返回的项目数量施加上限，以帮助防止拒绝服务攻击。为了帮助客户端应用程序，返回分页数据的GET请求还应包括某种形式的元数据，这些元数据指示集合中可用资源的总数。

您可以使用类似的策略在获取数据时对其进行排序，方法是提供一个以字段名为值的排序参数，例如`/orders?sort=ProductID`

但是，这种方法可能会对缓存产生负面影响，因为查询字符串参数构成了许多缓存实现用作缓存数据键的资源标识符的一部分。

如果每个项目包含大量数据，您可以扩展此方法以限制为每个项目返回的字段。例如，您可以使用接受逗号分隔字段列表的查询字符串参数，例如 `/orders?fields=ProductID,Quantity` 

为查询字符串中的所有可选参数提供有意义的默认值。例如，如果实现分页，则将`limit`参数设置为10，将`offset`参数设置为0，如果实现排序，则将排序参数设置为资源的键，如果支持投影（投影允许客户端指定要包含在响应中的字段子集，从而减少传输的数据量并提高性能），则将字段参数设置为资源中的所有`fields`。

## 支持对大型二进制资源的部分响应

资源可能包含大型二进制字段，例如文件或图像。为了克服由不可靠和间歇性连接引起的问题并缩短响应时间，可以考虑允许以块的形式检索此类资源。为此，Web API应支持用于大型资源的GET请求的Accept-Ranges标头。该标头表明 GET 操作支持部分请求。 客户端应用程序可以提交返回资源子集的 GET 请求，指定为字节范围。

此外，考虑为这些资源实现HTTP HEAD请求。HEAD请求类似于GET请求，只是它只返回描述资源的HTTP标头，并带有一个空的消息正文。客户端应用程序可以发出HEAD请求以确定是否使用部分GET请求获取资源。例如：

```json
HEAD https://adventure-works.com/products/10?fields=productImage HTTP/1.1
```

响应消息：

```json
HTTP/1.1 200 OK

Accept-Ranges: bytes
Content-Type: image/jpeg
Content-Length: 4580
```

Content-Llong标头给出资源的总大小，Accept-Ranges标头表示相应的GET操作支持分片结果。客户端应用程序可以使用此信息以更小的块检索图像。第一个请求通过使用Range标头获取前2500个字节：

```json
GET https://adventure-works.com/products/10?fields=productImage HTTP/1.1
Range: bytes=0-2499
```

响应消息通过返回HTTP状态代码206来表明这是分片响应。Content-Length标头指定消息正文中返回的实际字节数（不是资源的大小），Content-Range标头指示这是资源的哪一部分（4580中的字节0-2499）：

```json
HTTP/1.1 206 Partial Content

Accept-Ranges: bytes
Content-Type: image/jpeg
Content-Length: 2500
Content-Range: bytes 0-2499/4580

[...]
```

## 使用HATEOAS启用对相关资源的导航



## 版本控制RESTful Web API



## Open API Initiative









## 参考文档

- [Azure best practices api design](https://learn.microsoft.com/en-us/azure/architecture/best-practices/api-design)

- [codecademy- What-is-REST](https://www.codecademy.com/article/what-is-rest)