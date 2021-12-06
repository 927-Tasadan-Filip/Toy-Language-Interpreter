# Toy-Language-Interpreter
This projects simulates an interpreter for a high-level programming language. It's purpose is to help first year students to understand what is happening behind the scenes of a real interpreter. The implementation is done in Java programming language and it respects the Model-View-Controller-Repository architecture and the OOP principles.
The interpreter has the following functionalities:
- variable declaration by specifying their type, which can be: bool, integer, reference or string
- assignment statement
- arithmetic, logic and relational expressions
- "if" and "while" statements
- file statements, that allows user to open, read and close a read-only text file
- dynamic variables handling using the heap
- fork statement which allows concurrent program execution
- type checking for all statements and expressions before run time
- garbage collector that deallocates all the heap blocks that are no more referenced
