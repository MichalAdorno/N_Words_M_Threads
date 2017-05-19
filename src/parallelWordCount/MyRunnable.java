package parallelWordCount;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class MyRunnable implements Runnable {
    MyRunnable[] pool;
    BlockingQueue<String> words;
    int nr;
    Thread t;
    int NUMBER_OF_THREADS;
    Object syn;


    public MyRunnable(MyRunnable[] pool, BlockingQueue<String> words, int i, int NUMBER_OF_THREADS, Object syn) {
        this.pool = pool;
        this.words = words;
        this.nr = i;
        this.syn=syn;
        this.NUMBER_OF_THREADS = NUMBER_OF_THREADS;
        t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        //
        while (true) {
            if (words.isEmpty()) {
                break;
            } else {
                if (nr == words.size() % NUMBER_OF_THREADS) {
                    try {
                        synchronized(words){
                            if(words.peek()!=null){
                                System.out.println("<" + words.poll(500, TimeUnit.MILLISECONDS) + "    " + nr + ">");
//								System.out.print(words.poll(500, TimeUnit.MILLISECONDS) + " ");
                            }
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}