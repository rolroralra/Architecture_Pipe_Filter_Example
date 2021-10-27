package components.middle;

import framework.AbstractMiddleFilter;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.Arrays;

public class AddFilter extends AbstractMiddleFilter {
    @Override
    public boolean specificComputationForFilter(PipedInputStream in, PipedOutputStream out) throws IOException {
        int checkBlank = 4;
        int numOfBlank = 0;
        int idx = 0;
        byte[] buffer = new byte[64];
        boolean isAlreadyToLesson = false;
        int byte_read = 0;

        byte[][] conditions = new byte[][]{"12345".getBytes(), "23456".getBytes()};
        int satisfiedCount = 0;

        while(true) {
            // check "CS" on byte_read from student information
            while(byte_read != '\n' && byte_read != -1) {
                byte_read = in.read();
                if(byte_read == ' ') numOfBlank++;
                if(byte_read != -1) buffer[idx++] = (byte)byte_read;


                if(numOfBlank > checkBlank) {
                    byte[] subBytes = Arrays.copyOfRange(buffer, idx-6, idx-1);

                    for (byte[] condition : conditions) {
                        if (Arrays.equals(subBytes, condition)) {
                            satisfiedCount++;
                        }
                    }

                    if (satisfiedCount >= 2) {
                        isAlreadyToLesson = true;
                    }
                }
            }

            if(isAlreadyToLesson) {
                for(int i = 0; i<idx; i++)
                    out.write((char)buffer[i]);
                isAlreadyToLesson = false;
                satisfiedCount = 0;
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
