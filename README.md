# notas_Estudiantes

Ejercicio de arquitectura - Gestión de Notas de Estudiantes

---

## ¿Qué hace este programa?

Este sistema permite gestionar usuarios (administradores, profesores y estudiantes), componentes (materias/cursos), y el registro y consulta de notas por estudiante y componente. Incluye autenticación, control de roles y una interfaz de usuario por consola con menús y tablas legibles.

---

## Estructura de Carpetas
📦 Proyecto                                   
├── 📄 Main.java                           
└── 📁 src                                 
    └── 📁 app                         
        ├── 📁 modelo                    
        │   ├── Usuario.java                    
        │   ├── Profesor.java                   
        │   ├── Estudiante.java                 
        │   ├── Administrador.java              
        │   ├── Rol.java                  
        │   └── Nota.java                 
        │                    
        ├── 📁 logica                        
        │   ├── Componente.java               
        │   ├── GestorComponentes.java            
        │   └── SistemaAutenticacion.java            
        │                    
        └── 📁 interfaz                                
            └── InterfazUsuario.java           

---

## Funcionalidades principales

- **Gestión de usuarios y autenticación:**  
  - Registro y autenticación de administradores, profesores y estudiantes.
  - Control de acceso por roles:  
    - Administrador: CRUD completo de usuarios, componentes y notas.
    - Profesor: CRUD de estudiantes, componentes y notas de sus componentes.
    - Estudiante: Consulta de sus propias notas.

- **Gestión de componentes:**  
  - Registro de componentes (materias/cursos) y asignación de profesor responsable.
  - Asociación de estudiantes a componentes.

- **Gestión de notas:**  
  - Registro de varias notas por estudiante y componente, con porcentaje individual.
  - Edición y eliminación de notas (solo por administradores y profesores).
  - Cálculo automático de nota final y cantidad de notas.

- **Consultas y reportes:**  
  - Consulta de usuarios por ID o listado general.
  - Consulta de componentes y estudiantes asociados.
  - Visualización de notas por estudiante y componente en tablas claras.
  - Resumen de notas finales y cantidad de notas por estudiante.

- **Interfaz de usuario por consola:**  
  - Menús claros y navegación por opciones.
  - Tablas ASCII para mostrar datos de usuarios, componentes y notas.
  - Mensajes de error y confirmación enmarcados para fácil lectura.

---

## Clases principales

- **/modelo**
  - `Usuario`: Clase base para todos los usuarios.
  - `Administrador`: Usuario con permisos totales.
  - `Profesor`: Usuario que gestiona componentes y notas de sus estudiantes.
  - `Estudiante`: Usuario que puede consultar sus notas.
  - `Rol`: Enum para los tipos de usuario.
  - `Nota`: Representa una nota con valor y porcentaje.

- **/logica**
  - `SistemaAutenticacion`: Maneja el registro, autenticación y gestión de usuarios.
  - `GestorComponentes`: Gestiona componentes y la asociación de estudiantes.
  - `Componente`: Representa una materia/curso.

- **/interfaz**
  - `InterfazUsuario`: Controla la interacción por consola, menús y flujos de usuario.

- **Main.java**
  - Punto de entrada del programa. Inicia la interfaz de usuario.

---

## ¿Cómo funciona?

1. **Inicio de sesión y registro:**  
   El usuario puede iniciar sesión o registrarse según su rol.

2. **Menús por rol:**  
   Cada usuario ve solo las opciones permitidas según su tipo (admin, profesor, estudiante).

3. **Gestión y consulta:**  
   Los administradores y profesores pueden crear, editar y eliminar usuarios, componentes y notas. Los estudiantes solo pueden consultar sus notas.

4. **Visualización clara:**  
   Todos los listados y consultas se muestran en tablas ASCII para facilitar la lectura en consola.

5. **Validación y control:**  
   El sistema valida entradas, controla duplicados y muestra mensajes claros de error o éxito.

---

**Premisa original:**  
> El sistema permite registrar estudiantes, componentes y notas, asociar estudiantes a componentes, y gestionar las notas de cada estudiante por componente, todo con control de roles y visualización clara en consola.