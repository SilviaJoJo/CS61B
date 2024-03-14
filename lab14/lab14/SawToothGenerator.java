package lab14;

import lab14lib.Generator;

public class SawToothGenerator implements Generator {
    private int period;
    private int state;

    public int getPeriod() {
        return period;
    }

    public int getState() {
        return state;
    }

    protected void setPeriod(int p) {
        period = p;
    }

    protected void setState(int s) {
        state = s;
    }

    public SawToothGenerator(int p) {
        state = 0;
        period = p;
    }

    private double normalize(int integer) {
        int scale = getPeriod() - 1;
        return integer * 2.0 / scale - 1;
    }
    public double next() {
        int integer = getState() % getPeriod();
        double ans = normalize(integer);
        setState(state + 1);
        return ans;
    }
}
