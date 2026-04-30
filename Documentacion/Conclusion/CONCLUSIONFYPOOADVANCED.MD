# Conclusión - Fundamentos del Lenguaje y POO Avanzada

La guía se resolvió construyendo una base de clases pequeña pero completa, pensada para demostrar cada concepto pedido sin mezclar responsabilidades de más.

En la primera sección se trabajó la estructura básica de Java, la documentación con comentarios y la encapsulación. `SistemaGestor` se usó para mostrar `static final` y `CuentaBancaria` para proteger el estado interno con atributos `private` y validación de negocio en el depósito.

En la segunda sección se reforzó el diseño de métodos y constructores. `Reporte` mostró métodos con y sin retorno, además de `String...` para argumentos variables. `Usuario` permitió practicar sobrecarga de constructores y sobrecarga de métodos con distintas firmas.

La tercera sección permitió aplicar herencia, abstracción y polimorfismo. La clase abstracta `Documento` obligó a definir un contrato común, mientras que `Factura` y `Recibo` resolvieron su propia versión de `procesar()`. También se agregó una sobrecarga en `Factura` para diferenciar comportamiento según parámetros.

En la cuarta sección se incorporaron interfaces y polimorfismo por contrato. `Factura` implementó `Exportable` y `Auditable`, y el servicio `ServicioImpresion` demostró que el código puede trabajar con el tipo de interfaz en lugar de depender de una clase concreta.

Finalmente, la solución se validó compilando y ejecutando el proyecto, y la documentación quedó acompañada por la checklist y los logs de consola para dejar trazabilidad completa de cómo se resolvió la guía.
