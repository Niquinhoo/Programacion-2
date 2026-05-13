# Guía: Desarrollo de Interfaces Gráficas con Java Swing

## Ejercicio 1: Formulario interactivo básico
Este ejercicio se centra en organizar componentes de entrada simples usando paneles y capturar sus datos.

- **Componentes a utilizar**: `JFrame`, `JPanel`, `JLabel`, `JTextField`, `JButton`, `ActionListener`.
- **Instrucciones**:
  1. Crea una ventana principal instanciando un `JFrame` y asígnale un tamaño usando `setSize()`.
  2. Crea un contenedor intermedio `JPanel` y añádelo a la ventana principal.
  3. Dentro del panel, añade una `JLabel` con el texto "Introduce tu nombre:" y un `JTextField` de una sola línea configurado con un ancho de, por ejemplo, 20 columnas.
  4. Añade un `JButton` con el texto "Saludar". Añade otra `JLabel` vacía en la parte inferior del panel que servirá para mostrar el resultado.
  5. **Interacción**: Implementa la interfaz `ActionListener` y asóciala al botón mediante el método `addActionListener()`. Dentro del método `actionPerformed()`, debes recoger el texto escrito por el usuario usando el método `getText()` del `JTextField` y modificar la etiqueta inferior vacía para que muestre el mensaje: "¡Hola, [Nombre introducido]!".

---

## Ejercicio 2: Mini Editor de Texto con Barra de Menús
Este ejercicio introduce la creación de menús superiores y el manejo de áreas de texto multilínea.

- **Componentes a utilizar**: `JFrame`, `JMenuBar`, `JMenu`, `JMenuItem`, `JTextArea`, `ActionListener`.
- **Instrucciones**:
  1. Crea un `JFrame` principal.
  2. Instancia un `JMenuBar` (la barra superior) y fíjala a la ventana usando el método `setJMenuBar()` del `JFrame`.
  3. Crea un `JMenu` llamado "Archivo" y añádelo al `JMenuBar`.
  4. Dentro del menú "Archivo", añade dos opciones instanciando `JMenuItem`: uno llamado "Limpiar texto" y otro llamado "Salir".
  5. En el centro de la ventana, añade un `JTextArea` para permitir la edición de múltiples líneas de texto. (*Nota: envuelve este JTextArea dentro de un JScrollPane para que aparezcan barras de desplazamiento si el texto es muy largo*).
  6. **Interacción**: Asocia un `ActionListener` a cada `JMenuItem`.
     - Si el evento proviene del ítem "Limpiar texto", invoca el método `setText("")` sobre el `JTextArea` para borrar todo su contenido.
     - Si el evento proviene del ítem "Salir", ejecuta la instrucción para cerrar el programa.

---

## Ejercicio 3: Panel de Preferencias con Ventana Secundaria
Este ejercicio combina todos los componentes restantes, enfocándose en abrir ventanas de diálogo secundarias y usar casillas de verificación.

- **Componentes a utilizar**: `JFrame`, `JDialog`, `JButton`, `JCheckBox`, `JLabel`, `JPanel`, `ActionListener`.
- **Instrucciones**:
  1. **Ventana Principal**: Crea un `JFrame` con un `JPanel`. Dentro, coloca una `JLabel` que diga "Opciones seleccionadas: Ninguna" y un `JButton` llamado "Configurar Preferencias".
  2. **Ventana Secundaria**: Crea una clase que herede de `JDialog`. Esta será tu ventana de diálogo. Asegúrate de configurarla como modal para que bloquee la interacción con la ventana principal hasta que se cierre.
  3. Dentro del `JDialog`, añade tres `JCheckBox` representando diferentes opciones (por ejemplo: "Modo Oscuro", "Notificaciones", "Autoguardado"). Añade también un `JButton` llamado "Aceptar".
  4. **Interacción 1 (Abrir Diálogo)**: Añade un `ActionListener` al botón "Configurar Preferencias" del `JFrame`. En el método `actionPerformed()`, haz que el `JDialog` se haga visible (`setVisible(true)`).
  5. **Interacción 2 (Procesar Opciones)**: Añade un `ActionListener` al botón "Aceptar" del `JDialog`. Al pulsarlo, el código debe comprobar el estado de cada `JCheckBox` utilizando su método `isSelected()`.
  6. Construye una cadena de texto (`String`) con los nombres de las opciones que estén marcadas. Finalmente, actualiza el texto de la `JLabel` de la ventana principal con esta información y cierra el diálogo usando el método `dispose()`.

---

## Ejercicio 4: Pantalla de Autenticación (Login) con Verificación
Este ejercicio consiste en crear una ventana de acceso segura. `JPasswordField` es una especialización de `JTextField` que oculta los caracteres que el usuario va tecleando.

- **Componentes a utilizar**: `JFrame`, `JPanel`, `JLabel`, `JTextField`, `JPasswordField`, `JButton`, `ActionListener`, y un cuadro de diálogo (`JDialog` o `JOptionPane`).
- **Instrucciones**:
  1. **Configuración del contenedor**: Crea una clase que herede de `JFrame` o instancia uno directamente. Añádele un `JPanel` que actuará como contenedor principal para organizar los elementos.
  2. **Campo de Usuario**: Dentro del panel, añade una `JLabel` que diga "Usuario:" y un `JTextField` para que la persona escriba su nombre.
  3. **Campo de Contraseña**: Añade una segunda `JLabel` con el texto "Contraseña:" y a su lado instancia un `JPasswordField`. Puedes definir su anchura inicial utilizando el constructor, por ejemplo: `new JPasswordField("", 20)`.
  4. **Personalización visual**: Utiliza el método `setEchoChar(char oculta)` del `JPasswordField` para definir qué símbolo quieres que se muestre en pantalla (por ejemplo, un asterisco '*').
  5. **Botón de envío**: Añade un `JButton` con el texto "OK" o "Acceder".
  6. **Lógica del programa**: En la clase de tu ventana, crea un atributo privado que guarde una contraseña secreta predefinida. Asocia un `ActionListener` a tu botón de "OK".
  7. **Captura y validación**: Dentro del método `actionPerformed()`, recoge la contraseña introducida utilizando el método `getPassword()`. (*Nota: este método devuelve un arreglo de caracteres (char[])*).
  8. **Respuesta final**: Compara la contraseña capturada con la almacenada. Si coincide, despliega un cuadro de diálogo informando que el acceso es correcto. Si no coincide, muestra un error.

---

## Ejercicio 6: Entorno de Desarrollo y NetBeans GUI Designer
- **Objetivo**: Familiarizarse con el entorno de desarrollo y utilizar el constructor visual para evitar programar la disposición gráfica a mano.
- **Instrucciones**:
  1. **Instalación**: Descarga e instala NetBeans IDE junto con el JDK.
  2. **Creación del Formulario**: Abre NetBeans, crea un nuevo proyecto Java y añade un nuevo `JFrame Form`.
  3. **Uso del Diseñador Visual**: Abre la vista de diseño (Design). Utiliza el NetBeans GUI Designer (editor Matisse) para construir tu interfaz.
  4. **Arrastrar y Soltar**: Localiza la "Paleta" (Palette). Selecciona componentes (JLabel, JTextField, JButton) y arrástralos hacia la ventana.
  5. **Análisis del Código Generado**: Ve a la vista de código (Source). Observa cómo NetBeans ha generado el código necesario para inicializar los componentes usando `GroupLayout`.
  6. **Regla de Oro del Diseñador**: El bloque de código grisáceo que genera NetBeans **NO** se puede modificar directamente. Cualquier cambio visual debe hacerse desde el menú de propiedades del diseñador visual.