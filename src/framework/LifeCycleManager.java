/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University.
 */
package framework;

import components.middle.MiddleFilter2;
import components.sink.SinkFilter;
import components.source.SourceFilter;

public class LifeCycleManager {
    public static void main(String[] args) {
        try {
            CommonFilter filter1 = new SourceFilter("Students.txt");
            CommonFilter filter2 = new SinkFilter("Output.txt");

            CommonFilter filter4 = new SourceFilter("Students.txt");
            CommonFilter filter5 = new SinkFilter("Output2.txt");
//            CommonFilter filter3 = new MiddleFilter();
            AbstractMiddleFilter filter3 = new MiddleFilter2();


            filter3.connect(filter1, filter2);
            filter3.connect(filter4, filter5);
//            filter1.connectOutputTo(filter3);
//            filter3.connectOutputTo(filter2);
            
            Thread thread1 = new Thread(filter1);
            Thread thread2 = new Thread(filter2);
            Thread thread3 = new Thread(filter3);
            Thread thread4 = new Thread(filter4);
            Thread thread5 = new Thread(filter5);
            
            thread1.start();
            thread2.start();
            thread3.start();
            thread4.start();
            thread5.start();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
