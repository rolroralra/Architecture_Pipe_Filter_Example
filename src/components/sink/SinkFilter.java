package components.sink;

import framework.AbstractCommonFilter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class SinkFilter extends AbstractCommonFilter {
    private String sinkFile;
    
    public SinkFilter(String outputFile) {
        this.sinkFile = outputFile;
    }
    @Override
    public boolean specificComputationForFilter(PipedInputStream in, PipedOutputStream out) throws IOException {
        int byte_read;
        FileWriter fw = new FileWriter(this.sinkFile);
        while(true) {
            byte_read = in.read(); 
            if (byte_read == -1) {
            	 fw.close();
                System.out.printf("[%s] %s::Filtering is finished; Output file is created. [%s]%n", Thread.currentThread(), this.getClass().getSimpleName(), sinkFile);
                 return true;
            }
            fw.write((char)byte_read);
        }   
    }
}
