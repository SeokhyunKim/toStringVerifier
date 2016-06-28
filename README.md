# toStringVerifier
Verifies (unit tests) the toString method generated by Lombok.

```java
    @Test
    public void shouldImplementToString(){
        SomeObject sut = SomeObject.builder().build();
        ToStringVerifier.forClass(SomeObject.class).containsAllPrivateFields(sut);
    }
```

Can be integrated via maven
===========================

```xml
<dependency>
    <groupId>be.joengenduvel.java.verifiers</groupId>
    <artifactId>to-string</artifactId>
    <version>1.0.2</version>
    <scope>test</scope>
</dependency>
```
