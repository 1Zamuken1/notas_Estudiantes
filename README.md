# notas_Estudiantes
Ejercicio de arquitectura - Gestión de Notas de Estudiantes

## ¿Qué hace este programa?

Permite registrar uno o varios estudiantes, asociarlos a un componente (materia/curso), ingresar varias notas para ese componente y calcular automáticamente el porcentaje, la nota final y la cantidad de notas de cada estudiante.

## Funcionalidades principales

- **Registrar estudiantes:**  
  Ingresa los datos personales del estudiante y selecciona el componente (materia) al que pertenece.

- **Registrar componentes:**  
  Los componentes incluyen código, nombre y profesor responsable.

- **Agregar notas:**  
  Permite ingresar varias notas para cada estudiante en su componente. El porcentaje de cada nota se ingresa manualmente y el sistema valida que la suma sea 100%.

- **Consultar notas de estudiante:**  
  Busca un estudiante por su identificación y muestra sus notas, porcentajes, nota final, cantidad de notas y estado académico (aprobado/reprobado).

- **Resumen general:**  
  Muestra una tabla con todos los estudiantes, su componente, nota final y estado.

- **Estadísticas generales:**  
  Calcula y muestra el promedio general, nota más alta, nota más baja, cantidad de aprobados y reprobados, y porcentaje de aprobación.

## ¿Cómo funciona?

1. **Menú principal:**  
   El usuario puede registrar estudiantes, consultar notas, ver resúmenes y estadísticas, o salir del sistema.

2. **Registro guiado:**  
   El sistema guía paso a paso para ingresar los datos del estudiante, seleccionar el componente y registrar las notas con sus porcentajes.

3. **Visualización clara:**  
   Las notas y resúmenes se muestran en tablas con caracteres especiales para facilitar la lectura.

4. **Validación de datos:**  
   El sistema valida que los porcentajes de las notas sumen 100% y que los valores ingresados sean correctos.

5. **Gestión en memoria:**  
   Toda la información se mantiene en memoria durante la ejecución del programa (no se guarda en disco).

---

**Premisa original:**  
> Se desea realizar un programa que le permita a uno o varios estudiantes registrar sus datos, los datos de un componente y las notas de ese componente.  
> El programa debe permitir registrar varias notas del estudiante para ese componente y calcular el porcentaje de la nota ingresada, la nota final y la cantidad de notas para ese estudiante.
