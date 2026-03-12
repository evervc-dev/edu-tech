package com.evervc.api.edutech.audit;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) // Esta anotación solo se puede poner encima de Métodos
@Retention(RetentionPolicy.RUNTIME) // La anotación debe sobrevivir en tiempo de ejecución para que el Aspecto la lea
public @interface AuditarTiempoRespuesta {
}
