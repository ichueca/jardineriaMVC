## **README - Sistema de Gestión de Jardinería (MVC & DAO)**

### **Descripción del Proyecto**
Este proyecto implementa un sistema de gestión para una empresa de jardinería. Permite gestionar jardineros, trabajos y tipos de trabajo, aplicando los patrones de diseño **MVC (Model-View-Controller)** y **DAO (Data Access Object)**.

**Objetivo educativo:** Comprender cómo separar responsabilidades en una aplicación Java mediante patrones de diseño profesionales.

---

### **Estructura del Proyecto**

El proyecto se organiza en las siguientes capas:

#### **1. Modelo (Model)**
Contiene las clases que representan los datos del negocio:
- **Jardinero**: Representa un jardinero con atributos como nombre, apellidos, etc.
- **Trabajo**: Representa un trabajo con información como descripción, fecha de inicio, fecha de fin, etc. Tiene referencias al Jardinero y al TipoTrabajo.
- **TipoTrabajo**: Representa los tipos de trabajo disponibles (poda, riego, plantación, etc.). Se gestiona mediante una **matriz** para demostrar el uso de estructuras diferentes.

#### **2. DAO (Data Access Object)**
Separa la lógica de acceso a datos de la lógica de negocio:
- **JardineroDAO** (interfaz e implementación): Operaciones CRUD para jardineros.
- **TrabajoDAO** (interfaz e implementación): Operaciones CRUD para trabajos.
- **TipoTrabajoDAO** (interfaz e implementación): Gestión de tipos de trabajo mediante matriz.

**Ventaja:** Si cambias de base de datos (ficheros, SQL, etc.), solo modificas el DAO, no el resto de la aplicación.

#### **3. Servicio (Service)**
Integra los DAOs y proporciona la lógica de negocio:
- **GardenService**: Coordina las operaciones de los DAOs y aplica reglas de negocio.

#### **4. Vista (View)**
Interactúa con el usuario mediante JOptionPane:
- **GardenView**: Muestra menús, solicita datos al usuario y presenta información.
- **IMPORTANTE:** La vista **SOLO** interactúa con el controlador, nunca directamente con el DAO o el servicio.
- Métodos como `mostrarMenu()`, `pedirString()`, `pedirInt()`, `mostrarMensaje()`.

#### **5. Controlador (Controller)**
Coordina la vista y el servicio:
- **GardenController**: Recibe solicitudes de la vista, procesa con el servicio y devuelve resultados a la vista.
- Método `iniciar()`: Inicia la aplicación.

---

### **Flujo de Comunicación**

**Vista → Controlador → Servicio → DAO → Datos**

Ejemplo: El usuario quiere buscar un jardinero por nombre:
1. **Vista:** Muestra un menú y pide el nombre del jardinero.
2. **Controlador:** Recibe el nombre de la vista y lo pasa al servicio.
3. **Servicio:** Llama al DAO para buscar el jardinero.
4. **DAO:** Realiza la búsqueda en la lista de jardineros (en memoria).
5. **Resultado:** Se devuelve al controlador, que lo pasa a la vista para mostrar.

**Importante:** La vista **NUNCA** accede directamente al DAO o al servicio. Todo pasa por el controlador.

---

### **Patrones de Diseño Explicados**

#### **Patrón DAO (Data Access Object)**
**¿Qué es?** Un patrón que separa la lógica de acceso a datos de la lógica de negocio.

**¿Por qué lo usamos?**
- Si cambias de base de datos, solo modificas el DAO.
- El resto de la aplicación no se ve afectada.
- Facilita las pruebas unitarias.

#### **Patrón MVC (Model-View-Controller)**
**¿Qué es?** Un patrón que separa la aplicación en tres componentes:
- **Model:** Datos y lógica de negocio (clases de modelo + DAOs + servicio).
- **View:** Interfaz de usuario (menús, diálogos).
- **Controller:** Coordina modelo y vista.

**¿Por qué lo usamos?**
- Separación de responsabilidades: cada componente tiene un propósito claro.
- Facilita el mantenimiento: cambios en la vista no afectan la lógica de negocio.
- Reutilización: el modelo puede usarse con diferentes vistas.

---

### **Búsqueda Secuencial (Programación Estructurada)**

En este proyecto, usamos búsqueda secuencial sin saltos incondicionales (sin `break` ni `return` dentro del bucle):

```java
int i;
for(i=0; i<jardineros.size() && !jardineros.get(i).getNombre().equals(nombre); i++);
if (i<jardineros.size()){
  return jardineros.get(i);
} else {
  return null;
}
```

**¿Por qué?** Esto demuestra programación estructurada pura, sin saltos incondicionales. Es más fácil de entender y mantener.

---

### **Cómo Ejecutar**

Desde la clase principal (Main):
```java
GardenController controller = new GardenController();
controller.iniciar();
```

---

### **Conceptos Clave**

| Concepto | Explicación |
|----------|-------------|
| **Encapsulación** | Cada capa tiene responsabilidades claras y no interfiere con otras. |
| **Separación de responsabilidades** | La vista no conoce cómo se accede a los datos, solo muestra información. |
| **Reutilización** | El modelo y el servicio pueden usarse con diferentes vistas. |
| **Mantenibilidad** | Cambios en una capa no afectan a las otras. |

---
