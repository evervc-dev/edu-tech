package com.evervc.api.edutech.audit;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class AuditoriaAspect {
    // Se indica a Spring: "Intercepta cualquier método que tenga esta anotación"
    @Around("@annotation(AuditarTiempoRespuesta)")
    public Object medirTiempoEjecucion(ProceedingJoinPoint joinPoint) throws Throwable {

        // ANTES DE EJECUTAR: Toma el tiempo de inicio
        long tiempoInicio = System.currentTimeMillis();

        // Extrae el nombre del método interceptado para que el log sea más claro
        String nombreMetodo = joinPoint.getSignature().getName();

        Object resultado;
        try {
            // EJECUTA EL MÉTODO REAL:
            resultado = joinPoint.proceed();
        } finally {
            // DESPUÉS DE EJECUTAR: Calcula la diferencia de tiempo
            long tiempoFin = System.currentTimeMillis();
            long tiempoTotal = tiempoFin - tiempoInicio;

            // Imprimimos el resultado en la consola
            log.info("***> [Auditoría] El método '{}' tardó {} ms en ejecutarse.", nombreMetodo, tiempoTotal);
        }

        // Retorna el resultado intacto para no romper el flujo de la aplicación
        return resultado;
    }
}
