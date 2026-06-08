# Taller 3 - Programación Orientada a Objetos (2026-I)

Sistema de gestión de magos y hechizos desarrollado en Java. El programa permite
administrar un repertorio de magos y sus hechizos (cargados desde archivos de texto),
calcular puntuaciones según el elemento de cada hechizo y generar reportes y rankings.

## Integrantes

| Nombre Apellido | RUT | Usuario GitHub |
|-----------------|-----|----------------|
| Dylan Ordóñez | 21387801-8 | DylanZaur | |

**Carrera:** ICI 

## Descripción del proyecto

En este mundo la magia domina sobre todas las capacidades. Cada mago tiene un
repertorio de uno o muchos hechizos, y cada hechizo pertenece a un elemento
(Fuego, Tierra, Planta o Agua) que define cómo se calcula su puntuación.

El sistema cuenta con dos paneles:

- **Administrador:** permite agregar, modificar y eliminar magos y hechizos. Todos
  los cambios se guardan automáticamente en los archivos `.txt`.
- **Analista:** muestra reportes y rankings (Top 10 hechizos, Top 3 magos y listados
  completos con o sin puntuación).

## Estructura del proyecto

El proyecto sigue una arquitectura por capas, separando el punto de entrada (`Main`)
de la lógica del sistema y del modelo de datos.

```
Taller3/
├── src/
│   ├── main/
│   │   └── Main.java                # Punto de entrada: menús y entrada/salida por consola
│   ├── modelo/
│   │   ├── Puntuable.java           # Interfaz con el método calcularPuntaje()
│   │   ├── Hechizo.java             # Clase abstracta base de los hechizos
│   │   ├── Fuego.java               # Subclase de Hechizo (elemento Fuego)
│   │   ├── Tierra.java              # Subclase de Hechizo (elemento Tierra)
│   │   ├── Planta.java              # Subclase de Hechizo (elemento Planta)
│   │   ├── Agua.java                # Subclase de Hechizo (elemento Agua)
│   │   └── Mago.java                # Mago con su lista de hechizos
│   └── sistema/
│       ├── Sistema.java             # Lógica central: CRUD, rankings y colecciones
│       └── ManejadorArchivos.java   # Lectura y escritura de los archivos .txt
├── Magos.txt                        # Datos de los magos y sus hechizos
├── Hechizos.txt                     # Datos de los hechizos y sus propiedades
├── Modelo_Dominio.pdf               # Modelo de dominio (conceptual)
├── Diagrama_Clases.pdf              # Diagrama de clases
└── README.md
```

### Clases principales

- **Puntuable (interfaz):** define el comportamiento `calcularPuntaje()`, implementado
  tanto por los hechizos como por los magos.
- **Hechizo (abstracta):** guarda los atributos comunes (nombre, tipo, daño) y obliga a
  cada subclase a definir su propia fórmula de puntaje.
- **Fuego, Tierra, Planta, Agua:** subclases con los atributos propios de cada elemento.
- **Mago:** mantiene un `ArrayList<Hechizo>` y calcula su puntaje sumando el de sus hechizos.
- **Sistema:** centraliza la lógica (cargar datos, CRUD, ordenamientos) y maneja las colecciones.
- **ManejadorArchivos:** lee los `.txt` con `Scanner` y los sobrescribe con `BufferedWriter`.
- **Main:** muestra los menús y se comunica únicamente con la clase `Sistema`.

## Archivos de datos

**Magos.txt** — un mago por línea:

```
NombreMago;Hechizo1|Hechizo2|HechizoN
```

**Hechizos.txt** — un hechizo por línea, con parámetros según su elemento:

```
NombreHechizo;Fuego;Daño;DuracionQuemadura
NombreHechizo;Tierra;Daño;MejoraDefensa
NombreHechizo;Planta;Daño;DuracionStun,CantPlantas
NombreHechizo;Agua;Daño;CantidadHeal,PresionDelAgua
```

## Fórmulas de puntuación

| Elemento | Fórmula |
|----------|---------|
| Fuego | Daño × DuracionQuemadura |
| Tierra | (Daño × MejoraDefensa) / 2 |
| Planta | Daño + (DuracionStun × CantPlantas) |
| Agua | (Daño + CantidadHeal + PresionDelAgua) × 2 |

El puntaje de un mago es la suma de las puntuaciones de todos los hechizos que domina.

## Funcionalidades

### Panel Administrador
1. Agregar Mago
2. Modificar Mago
3. Eliminar Mago
4. Agregar Hechizo
5. Modificar Hechizo
6. Eliminar Hechizo

Al modificar o eliminar un hechizo, el cambio se refleja automáticamente en todos los
magos que lo dominan.

### Panel Analista
1. Top 10 Mejores Hechizos
2. Top 3 Mejores Magos
3. Mostrar todos los Hechizos
4. Mostrar todos los Magos
5. Mostrar todos los Hechizos junto a su puntuación
6. Mostrar todos los Magos junto a su puntuación

## Instrucciones de ejecución

### Desde Eclipse
1. Importar el proyecto: `File > Import > Existing Projects into Workspace`.
2. Verificar que los archivos `Magos.txt` y `Hechizos.txt` estén en la **raíz del
   proyecto** (al mismo nivel que la carpeta `src`).
3. Ejecutar la clase `Main` (`Run As > Java Application`).

### Desde la terminal
```bash
# Compilar (desde la raíz del proyecto)
javac -d bin src/main/Main.java src/modelo/*.java src/sistema/*.java

# Ejecutar
java -cp bin main.Main
```

> **Nota:** los archivos `.txt` deben estar en la carpeta desde la que se ejecuta el
> programa, ya que se leen con ruta relativa. Los proyectos están codificados en UTF-8
> para conservar correctamente las tildes y caracteres especiales en los nombres.

## Diagramas

- **Modelo_Dominio.pdf:** abstracción conceptual del problema.
- **Diagrama_Clases.pdf:** diagrama UML técnico con todas las clases, atributos, métodos
  y relaciones (herencia, interfaces y asociaciones).

## Librerías utilizadas

- `Scanner` — lectura de archivos y entrada por consola.
- `BufferedWriter` / `FileWriter` — escritura de los archivos.
- `ArrayList` — manejo de colecciones dinámicas.
