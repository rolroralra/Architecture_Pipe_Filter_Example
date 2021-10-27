package framework;

import components.middle.AddFilter;
import components.middle.MiddleFilter;
import components.middle.MiddleFilter2;
import components.sink.SinkFilter;
import components.source.SourceFilter;

public class LifeCycleManager {
    public static void main(String[] args) {
        try {
            CommonFilter sourceFilter1 = new SourceFilter("Students.txt");
            CommonFilter sinkFilter1 = new SinkFilter("Output.txt");

            CommonFilter sourceFilter2 = new SourceFilter("Students.txt");
            CommonFilter sinkFilter2 = new SinkFilter("Output2.txt");

            CommonFilter sourceFilter3 = new SourceFilter("Students.txt");
            CommonFilter sinkFilter3 = new SinkFilter("Output3.txt");

            CommonFilter middleFilter1 = new MiddleFilter();
            AbstractMiddleFilter middleFilter2 = new MiddleFilter2();
            AbstractMiddleFilter addFilter1 = new AddFilter();
            AbstractMiddleFilter addFilter2 = new AddFilter();


//            sourceFilter3.connectOutputTo(middleFilter2);
//            middleFilter2.connectOutputTo(middleFilter3);
//            middleFilter3.connectOutputTo(sinkFilter3);


            middleFilter2.connect(sourceFilter1, addFilter2).connectOutputTo(sinkFilter1);
            middleFilter2.connect(sourceFilter3, addFilter1).connectOutputTo(sinkFilter3);

//            middleFilter2.connect(sourceFilter1, middleFilter3);
//            middleFilter3.connectOutputTo(sinkFilter3);

//            middleFilter2.connect(sourceFilter1, sinkFilter1);
//            middleFilter2.connect(sourceFilter2, sinkFilter2);

            Thread thread1 = new Thread(sourceFilter1);
//            Thread thread2 = new Thread(middleFilter1);
            Thread thread3 = new Thread(sinkFilter1);
//            Thread thread4 = new Thread(sourceFilter2);
            Thread thread5 = new Thread(middleFilter2);
//            Thread thread6 = new Thread(sinkFilter2);
            Thread thread7 = new Thread(sourceFilter3);
            Thread thread8 = new Thread(addFilter1);
            Thread thread9 = new Thread(sinkFilter3);
            Thread thread10 = new Thread(addFilter2);
            
            thread1.start();
//            thread2.start();
            thread3.start();
//            thread4.start();
            thread5.start();
//            thread6.start();
            thread7.start();
            thread8.start();
            thread9.start();
            thread10.start();


        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
