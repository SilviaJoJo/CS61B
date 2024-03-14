package lab14;
public class AcceleratingSawToothGenerator extends SawToothGenerator {
    private double changeRate;
    private int start;
    public AcceleratingSawToothGenerator(int p, double c) {
        super(p);
        changeRate = c;
        start = 0;
    }

    private double normalize(int integer) {
        int scale = getPeriod() - 1;
        return integer * 2.0 / scale - 1;
    }
    @Override
    public double next() {
        int integer = getState() - start;
        double ans = normalize(integer);
        setState(getState() + 1);
        if (ans == 1) {
            start += getPeriod();
            setPeriod((int) Math.floor(getPeriod() * changeRate));
        }
        return ans;
    }
}
