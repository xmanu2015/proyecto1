package pe.com.tdp.ventafija.microservices.common.context;

import org.springframework.util.Assert;

public class ThreadLocalVentaFijaContextHolderStrategy implements VentaFijaContextHolderStrategy {
    private static final ThreadLocal<pe.com.tdp.ventafija.microservices.common.context.VentaFijaContext> contextHolder = new ThreadLocal<pe.com.tdp.ventafija.microservices.common.context.VentaFijaContext>();

    @Override
    public void clearContext() {
        contextHolder.remove();
    }

    @Override
    public pe.com.tdp.ventafija.microservices.common.context.VentaFijaContext getContext() {
        pe.com.tdp.ventafija.microservices.common.context.VentaFijaContext ctx = contextHolder.get();

        if (ctx == null) {
            ctx = createEmptyContext();
            contextHolder.set(ctx);
        }

        return ctx;
    }

    @Override
    public void setContext(pe.com.tdp.ventafija.microservices.common.context.VentaFijaContext context) {
        Assert.notNull(context, "Only non-null SecurityContext instances are permitted");
        contextHolder.set(context);
    }

    @Override
    public VentaFijaContext createEmptyContext() {
        return new VentaFijaContextImpl();
    }

}
