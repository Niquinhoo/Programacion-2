# Checklist TP1 - Fundamentos del Lenguaje y POO Avanzada

## Seccion 1: Creacion de clases, encapsulacion y documentacion

- [x] **1.1 Creacion de clases y comentarios**
  - Se creo `SistemaGestor` con comentario de una linea, bloque de comentario multilínea y Javadoc.
  - Se resolvio con una clase simple enfocada en demostrar la sintaxis pedida sin mezclar logica innecesaria.

- [x] **1.2 Uso de `static` y `final`**
  - Se declaro `public static final int MAX_CONEXIONES = 10;`.
  - Se explico en el metodo `imprimirLimiteConexiones()` por que el valor pertenece a la clase y no puede reasignarse.

- [x] **1.3 Encapsulacion y proteccion de datos**
  - Se implemento `CuentaBancaria` con campos `private`.
  - Se expusieron metodos publicos `depositar(double monto)`, `getSaldo()` y `getNumeroCuenta()`.
  - Se agrego validacion para impedir depositos negativos o cero, protegiendo la integridad del saldo.

## Seccion 2: Diseno de metodos y constructores

- [x] **2.1 Metodos con y sin retorno**
  - Se creo `Reporte.generarEncabezado()` como metodo `void`.
  - Se creo `Reporte.obtenerCuerpo()` como metodo con retorno `String`.
  - Se separo la responsabilidad entre imprimir y devolver datos.

- [x] **2.2 Sobrecarga de constructores y metodos**
  - Se implementaron tres constructores en `Usuario`: sin parametros, con nombre y con nombre mas edad.
  - Se agrego sobrecarga del metodo `actualizarPerfil(String correo)` y `actualizarPerfil(String correo, int telefono)`.
  - Se uso la sobrecarga para mostrar distintos modos de inicializacion y actualizacion del objeto.

- [x] **2.3 Metodos con argumentos variables**
  - Se agrego `Reporte.mostrarSecciones(String... secciones)`.
  - Se demostro su flexibilidad llamandolo con tres cadenas y tambien sin argumentos.
  - Se recorrieron las secciones con `for-each`, como pide la consigna.

## Seccion 3: Herencia, polimorfismo y clases abstractas

- [x] **3.1 Herencia, clase abstracta y metodo abstracto**
  - Se creo la clase abstracta `Documento`.
  - Se definio el metodo abstracto `procesar()`.
  - Se heredaron las clases `Factura` y `Recibo` desde `Documento`.

- [x] **3.2 Sobrescritura y polimorfismo**
  - `Factura` y `Recibo` sobrescriben `procesar()` con `@Override`.
  - En `App` se usaron referencias del tipo `Documento` apuntando a objetos concretos.
  - Se demostro despacho dinamico al ejecutar la version correcta segun el objeto real.

- [x] **3.3 Sobrecarga en clase hija**
  - Se agrego `Factura.procesar(boolean esUrgente)`.
  - Se resolvio como sobrecarga porque cambia la lista de parametros y no reemplaza el metodo padre.

## Seccion 4: Interfaces, abstraccion avanzada y polimorfismo de interfaz

- [x] **4.1 Implementacion de interfaces**
  - Se definieron `Exportable` y `Auditable`.
  - `Factura` implementa ambas interfaces separadas por coma.
  - Se completaron ambos contratos con sus cuerpos de metodo.

- [x] **4.2 Polimorfismo a traves de interfaces**
  - Se creo `ServicioImpresion.enviarAImpresion(Exportable documentoExportable)`.
  - Se paso una instancia de `Factura` al metodo.
  - Se demostro que el metodo trabaja contra la interfaz y no contra una clase concreta.

## Verificacion

- [x] El proyecto compila correctamente con `javac *.java`.
- [x] La ejecucion principal funciona con `java App`.
- [x] La salida en consola coincide con las demostraciones de cada ejercicio.
