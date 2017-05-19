# ParallelWordCount
Excercise in Parallel Programming

 * Exercise: There is a specified number of words in a file as well as a specified number of threads.
 The number of threads is smaller than the number of words.
 The task is to make the subsequent threads read subsequent words in the file and print them out to the console.
 For example if the text in the file is "Lorem ipsum dolor sit amet, consectetur adipiscing" (7 words),
 and there are 3 threads, the program should work as follows:
 Lorem (word no. 1, read by thread no. 1.)
 ipsum (word no. 2, read by thread no. 2.)
 dolor (word no. 3, read by thread no. 3.)
 sit (word no. 4, read by thread no. 1.)
 amet (word no. 5, read by thread no. 2.)
 consectetur (word no. 6, read by thread no. 3.)
 adipiscing (word no. 7, read by thread no. 1.).
