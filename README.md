# escapeSolution

Java Maze Solver: Intelligent Navigation and Backtracking

![image](https://github.com/ayocloudi/escapeSolution/assets/126922387/52f6e9e7-3380-4205-bb2b-d6c0566b48b1)


This repository contains escapeSolution, a Java program designed to intelligently navigate through a text-based maze. The core of this program is the Brain class, an innovative algorithm that makes strategic decisions at each step of the maze.

Key Features:

Dynamic Maze Parsing: The program reads a maze layout from a text file, allowing for easy customization and testing of different maze structures.
Intelligent Navigation: Utilizing the Brain class, the program decides the best move at each junction based on available paths, ensuring efficient exploration of the maze.
Backtracking Capability: When dead ends are encountered, the Brain uses a stack-based approach to backtrack, retracing steps and exploring new paths without getting stuck.
Visualization and Debugging: The maze is printed to the console with the current position marked, providing a clear visual representation of the maze and the solver's progress.
Customizable Parameters: Users can modify key variables such as starting position and number of lives, offering flexibility and adaptability to different scenarios.
Robust Error Handling: The program includes error handling for file reading, ensuring stability and user-friendly feedback in case of issues with the maze file.
Technical Details:

Written in Java, this program exemplifies object-oriented programming principles.
The maze is represented as a 2D boolean array, making it efficient for the solver to navigate and check for walls.
The Brain class's logic is based on evaluating available directions and marking visited positions to avoid loops.
