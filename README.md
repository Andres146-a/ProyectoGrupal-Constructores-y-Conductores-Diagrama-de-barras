# ProyectoGrupal-Constructores-y-Conductores-Diagrama-de-barras
DEBERES-TALLER-PROYECTOS


Descripción
Este proyecto es una aplicación JavaFX que muestra resultados y estadísticas de conductores y constructores de Fórmula 1. Utiliza una base de datos PostgreSQL para almacenar los datos y ofrece diversas visualizaciones, incluidas tablas y gráficos de barras.

Funcionalidades
Pantalla principal:

Botones para ver resultados de conductores.
Botones para ver resultados de constructores.
Botones para ver gráficos de barras de conductores.
Botones para ver gráficos de barras de constructores.
Resultados de Conductores:

Mostrar una tabla con los resultados de conductores por año.
Búsqueda dinámica de conductores.
Ordenar los resultados de mayor a menor y viceversa.
Botón para regresar a la pantalla principal.
Resultados de Constructores:

Mostrar una tabla con los resultados de constructores por año.
Búsqueda dinámica de constructores.
Ordenar los resultados de mayor a menor y viceversa.
Botón para regresar a la pantalla principal.
Gráficos de Barras de Conductores:

Mostrar un gráfico de barras con los puntos totales por conductor para un año específico.
Botón para regresar a la pantalla principal.
Gráficos de Barras de Constructores:

Mostrar un gráfico de barras con los puntos totales por constructor para un año específico.
Botón para regresar a la pantalla principal.

Estructura del Proyecto
proyectogrupalf1dbpostgres/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           ├── App.java
│   │   │           ├── Controllers/
│   │   │           │   ├── ConstructorResultsController.java
│   │   │           │   └── DriverResultsController.java
│   │   │           ├── Models/
│   │   │           │   ├── ConstructorResult.java
│   │   │           │   ├── DriverResult.java
│   │   │           │   └── Season.java
│   │   │           ├── Repositories/
│   │   │           │   ├── ConstructorResultRepository.java
│   │   │           │   ├── DriverResultRepository.java
│   │   │           │   └── SeasonRepository.java
│   │   │           └── view/
│   │   │               ├── ConstructorBarChartWindow.java
│   │   │               ├── DriverBarChartWindow.java
│   │   │               ├── ConstructorResultsWindow.java
│   │   │               └── DriverResultsWindow.java
│   │   ├── resources/
│   │   │   ├── css/
│   │   │   │   └── style.css
│   │   │   └── imagen/
│   │   │       ├── F1.jpg
│   │   │       └── icon.png
└── pom.xml



![image](https://github.com/user-attachments/assets/adcf9d9f-330a-4699-a02d-50fa0e72bca7)




RESULTADOS DE CONDUCTORES


![image](https://github.com/user-attachments/assets/15852b29-60a9-4afd-b696-b44a3232bba6)



![image](https://github.com/user-attachments/assets/5b0b1db5-c2b7-4033-98f5-29927978639b)



BARRA DE CONDUCTORES


![image](https://github.com/user-attachments/assets/e1d38f03-0eaf-4c46-a1c6-f796c682feea)





