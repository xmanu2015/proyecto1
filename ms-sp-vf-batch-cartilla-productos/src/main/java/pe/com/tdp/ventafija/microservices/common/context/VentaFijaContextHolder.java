package pe.com.tdp.ventafija.microservices.common.context;


public class VentaFijaContextHolder {
    private static VentaFijaContextHolderStrategy strategy;
    private static int initializeCount = 0;

    public VentaFijaContextHolder() {
    }

    private static void initialize() {
        strategy = new ThreadLocalVentaFijaContextHolderStrategy();
        ++initializeCount;
    }

    public static void clearContext() {
        strategy.clearContext();
    }

    public static VentaFijaContext getContext() {
        return strategy.getContext();
    }

    public static int getInitializeCount() {
        return initializeCount;
    }

    public static void setContext(VentaFijaContext context) {
        strategy.setContext(context);
    }

    static {
        initialize();
    }
}
