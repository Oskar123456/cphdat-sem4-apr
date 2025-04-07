# NOTER AVANCERET PROGRAMMERING

## WEEK 1

### SEARCH AND SORT



### DATA STRUCTURES



## WEEK 2

### DESIGN PATTERNS

  - [patterns, Martin Fowler](https://www.martinfowler.com/ieeeSoftware/patterns.pdf)


#### Structural

##### Adapter

  - Wrapper.
  - Reuse existing, but superficially incompatible class.

##### Composite

  - Lets a group of objects treated as one instance of that object.
  - In a tree structure, like in some UI object, recursively call Draw() for each child.

##### Decorator

  - Wrapping classes/interfaces in new ones with added behavior, but still
    implementing original functionality in some way.
  - NOT ECS.

##### Proxy

  - Middleman between client and subject.

#### Behavioral

##### Command

  - Store behavior of object in its constructor; `Runnable(() -> { ... }), onClick(() -> { ... })`

##### Strategy

  - Class with interchangeable algorithm; `navigator.setMethod(shortestPath); navigator.navigate();`
  - Switch behavior at runtime...

##### Observer

  - Object keeps track of its dependents, and notifies them of any state change.
  - Removes the need for dependents, like UI elements, or damage calculations
    in a game, to hammer the cpu with checks for changes.

#### Creational

##### Singleton
##### Builder

  ```java
  myClass.newBuilder()
    .name()
    .age()
    .build();
  ```

##### Factory

  - Define interface for creating an object, but defer which and how to subclasses.
