package lab14;

import lab14lib.Generator;

public class StrangeBitwiseGenerator implements Generator {
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

    public StrangeBitwiseGenerator(int p) {
        state = 0;
        period = p;
    }

    private double normalize(int integer) {
        int scale = getPeriod() - 1;
        return integer * 2.0 / scale - 1;
    }
    public double next() {
        //int weirdState = state & (state >>> 3) % period;
        int weirdState = state & (state >> 3) & (state >> 8) % period;
        double ans = normalize(weirdState);
        setState(state + 1);
        return ans;
    }
}
