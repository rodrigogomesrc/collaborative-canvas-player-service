package br.ufrn.dimap.collaborativecanvas.playerservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class UseVirtualThreadCondition implements Condition {


    //@Value("${app.use-virtual-threads}")
    private boolean useVirtualThreads = true;

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        System.out.println("=============== USING VIRTUAL THREADS: " + useVirtualThreads);
        return useVirtualThreads;
    }
}
