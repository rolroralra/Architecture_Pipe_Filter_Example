package components.source;

import framework.AbstractCommonFilter;

import java.io.*;

public class SourceFilter extends AbstractCommonFilter {
    private String sourceFile;
    
    public SourceFilter(String inputFile){
        this.sourceFile = inputFile;
    }    
    @Override
    public boolean specificComputationForFilter(PipedInputStream in, PipedOutputStream out) throws IOException {
        int byte_read;    
        BufferedInputStream br = new BufferedInputStream(new FileInputStream(sourceFile));
        while(true) {
            byte_read = br.read();
            if (byte_read == -1) {
                System.out.printf("[%s] %s::Filtering is finished; Read [%s]%n", Thread.currentThread(), this.getClass().getSimpleName(), sourceFile);
                return true;
            }
            out.write(byte_read);
        }
    }
}
