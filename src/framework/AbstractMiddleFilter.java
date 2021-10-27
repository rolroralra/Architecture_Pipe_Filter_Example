package framework;


import lombok.Getter;

import java.io.EOFException;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public abstract class AbstractMiddleFilter extends AbstractCommonFilter {
    private List<PipedInputStream> inList = new ArrayList<>();
    private List<PipedOutputStream> outList = new ArrayList<>();
    private Map<PipedInputStream, PipedOutputStream> map = new HashMap<>();

    public void connect(CommonFilter prevFilter, CommonFilter nextFilter) throws IOException {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream();

        in.connect(prevFilter.getPipedOutputStream());
        out.connect(nextFilter.getPipedInputStream());

        map.put(in, out);
    }

    @Override
    public void run() {
        for (final PipedInputStream in : map.keySet()) {
            final PipedOutputStream out = map.get(in);
            new Runnable() {
                @Override
                public void run() {
                    try {
                        specificComputationForFilter(in, out);
                    } catch (IOException e) {
                        if (e instanceof EOFException) return;
                        else e.printStackTrace();
                    } finally {
                        closePorts(in, out);
                    }
                }
            }.run();
        }
    }

    private void closePorts(PipedInputStream in, PipedOutputStream out) {
        try {
            out.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
