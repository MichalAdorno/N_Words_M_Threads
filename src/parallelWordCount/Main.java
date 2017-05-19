package parallelWordCount;

/*
 * Exercise: There is a specified number of words in a file as well as a specified number of threads.
 * The number of threads is smaller than the number of words.
 * The task is to make the subsequent threads read subsequent words in the file and print them out to the console.
 * For example if the text in the file is "Lorem ipsum dolor sit amet, consectetur adipiscing" (7 words),
 * and there are 3 threads, the program should work as follows:
 *  Lorem (word no. 1, read by thread no. 1.)
 *  ipsum (word no. 2, read by thread no. 2.)
 *  dolor (word no. 3, read by thread no. 3.)
 *  sit (word no. 4, read by thread no. 1.)
 *  amet (word no. 5, read by thread no. 2.)
 *  consectetur (word no. 6, read by thread no. 3.)
 *  adipiscing (word no. 7, read by thread no. 1.).
 */

import java.io.BufferedReader;
import java.io.File;

import java.io.FileReader;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {

    static final int NUMBER_OF_WORDS = 41;
    static final int NUMBER_OF_THREADS = 17;
    static final Object syn = new Object();
    private static void initQueue(BlockingQueue<String> bq) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(
                    new FileReader(
                            new File("/home/lxuserb/loremipsum.txt")));
            String line;
            line = reader.readLine();
            String[] words = line.split(" ");
            for(int i=0;i<NUMBER_OF_WORDS;i++){
//				bq.offer(words[i]);
                bq.offer(words[i]+"("+i+")");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void initThreads(MyRunnable[] pool,BlockingQueue<String> words) {

        for (int i = 0; i < NUMBER_OF_THREADS; i++) {
            pool[i] = new MyRunnable(pool,words,i,NUMBER_OF_THREADS, syn);
        }
    }

    public static void main(String... args) {
        BlockingQueue<String> words = new LinkedBlockingQueue<>();
        MyRunnable[] pool = new MyRunnable[NUMBER_OF_THREADS];
        initQueue(words);
        initThreads(pool,words);
    }

}