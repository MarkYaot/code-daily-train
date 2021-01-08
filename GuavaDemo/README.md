### Base

- Optional：避免使用null

- Objects：提供重写equals，hashCode，toString，compare/compareTo的实用方法

- PreConditions：减少if else，不满足条件直接抛指定的异常，并提供明确的错误信息

### I/O

概览：I/O实用工具，主要用于方便处理（读，写，复制）流数据；**注意Guava不负责流的关闭**。

- ByteStreams，CharStreams：字节流数据和字符流数据的处理工具类，主要包括读，写，复制流

- ByteSource，CharSource，ByteSink，CharSink：这些类用来包装某种可读写的资源，并提供更便于读写资源数据的方法，每次读写都基于新创建的流。按流的类型，Byte是字节流，Char是字符流；按数据流向来分：Source用来读取，Sink用来写入。

- Files：提供创建File类型Source和Sink的方法，以及操作文件更方便的方法。

### Collections

- Immutable Collections：不可变集合，节约空间和时间

- Multiset：MultiSet可以方便的记录每个元素在集合中出现的次数

- Multimap：Multimap满足单key对应value，摆脱Map<K, List<V>>或者Map<K, Set<V>>

- BiMap：双向map，同时维护key到val以及val到key的映射