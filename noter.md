# NOTER AVANCERET PROGRAMMERING

## WEEK 1

### SEARCH AND SORT



### DATA STRUCTURES



## WEEK 2

### DESIGN PATTERNS

  - [patterns, Martin Fowler](https://www.martinfowler.com/ieeeSoftware/patterns.pdf)


#### Structural

##### Adapter
##### Composite
##### Decorator

  - Wrapping classes/interfaces in new ones with added behavior, but still
    implementing original functionality in some way.
  - NOT ECS

##### Singleton
##### Proxy

#### Behavioral

##### Command

  - Store behavior of object in its constructor; `Runnable(() -> { ... }), onClick(() -> { ... })`

##### Strategy

  - Class with interchangeable algorithm; `navigator.setMethod(shortestPath); navigator.navigate();`
  - Switch behavior at runtime...

##### Observer

#### Creational

##### Builder

  ```java
  myClass.newBuilder()
    .name()
    .age()
    .build();
  ```

##### Factory
