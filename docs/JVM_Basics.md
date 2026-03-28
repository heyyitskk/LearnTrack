# JVM Basics

- JDK (Java Development Kit): tools to write, compile and run Java code (includes javac and java).
- JRE (Java Runtime Environment): runtime components to run Java applications (java). Historically separate from JDK, shipped inside modern JDKs.
- JVM (Java Virtual Machine): the runtime engine that executes Java bytecode (platform-specific implementation).
- Bytecode: compiled `.class` files produced by `javac` that the JVM executes; this enables "write once, run anywhere" because the same bytecode runs on any JVM implementation.
- “Write once, run anywhere” means that a program written in Java can run on any device or operating system without needing to be rewritten. This is possible because Java code is compiled into bytecode, which is not specific to any one platform. 
Instead of running directly on the system, the bytecode is executed by the Java Virtual Machine (JVM). Since different platforms (Windows, Linux, macOS, etc.) have their own JVM implementations, the same compiled program can run anywhere a JVM exists—without changes to the original code.