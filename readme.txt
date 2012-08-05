How to run the code:
--------------------

The project is a simple maven project. Hence to see the output and execute tests one can say, 

$ mvn test

Dependencies:
-------------

The only dependencies are JUnit and google guava.

Abstract:
-----------
The code is the naive approach to solving the problem. I haven't concentrated on the algorithm part, as that may not say anything about the OOP design skills.
There are better known algorithms to the problem like "Hashlife" and such, which I haven't ventured into.
The only optimization I have done is employing a cache using google guava to improve performance for repetitive patterns.

Possible Improvements:
----------------------
This algorithm can be improved by enabling caching for cells. ie.,  the current Cell class can have another sub class which is neighbor aware. Means if a cell and its neighbors have the same properties then the future state of the cell is going to the same as the first calculation. 

Hence we may not have to do a second time calculation if the cell exists in cache. The only change that may be required is to make the Cell neighbor aware.
