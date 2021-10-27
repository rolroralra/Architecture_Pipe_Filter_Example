package components.middle;

import framework.AbstractCommonFilter;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class MiddleFilter extends AbstractCommonFilter {
    @Override
    public boolean specificComputationForFilter(PipedInputStream in, PipedOutputStream out) throws IOException {
    	int checkBlank = 4; 
        int numOfBlank = 0;
        int idx = 0;
        byte[] buffer = new byte[64];
        boolean isCS = false;    
        int byte_read = 0;
        
        while(true) {          
        	// check "CS" on byte_read from student information
            while(byte_read != '\n' && byte_read != -1) {
            	byte_read = in.read();
                if(byte_read == ' ') numOfBlank++;
                if(byte_read != -1) buffer[idx++] = (byte)byte_read;
                if(numOfBlank == checkBlank && buffer[idx-3] == 'C' && buffer[idx-2] == 'S')
                    isCS = true;
            }      
            if(isCS) {
                for(int i = 0; i<idx; i++) 
                    out.write((char)buffer[i]);
                isCS = false;
            }
            if (byte_read == -1) {
                System.out.printf("[%s] %s::Filtering is finished;%n", Thread.currentThread(), this.getClass().getSimpleName());
                return true;
            }
            idx = 0;
            numOfBlank = 0;
            byte_read = '\0';
        }
    }
}
