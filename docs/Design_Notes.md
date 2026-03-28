# Design Notes

- Data storage: in-memory `ArrayList` is used because we don't have to define the size of the array in advance and we can add as many elements or Objects(in this case - Students) in the list as we want.
- IDs: `util/IdGenerator.java` provides static counters such as `studentCounter`, `courseCounter` and `enrollmentCounter` to simulate database-generated IDs in the Service classes.
- Inheritance: `Person` is a base class used by `Student` and `Trainer` to demonstrate `super` and reuse of common fields.
