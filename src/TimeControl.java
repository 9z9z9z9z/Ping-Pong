public class TimeControl {
    public static double startTime = System.nanoTime();

    public static double getPeriod() { return (System.nanoTime() - startTime) / 1000000000; }

}
