# 🌸 OTEC Primavera - Sistema Integral de Gestión Académica

[![Java](https://img.shields.io/badge/Java-17+-orange?logo=java&logoColor=white)](https://www.java.com/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.13-brightgreen?logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
[![Spring Security](https://img.shields.io/badge/Spring%20Security-6.0+-blue?logo=spring&logoColor=white)](https://spring.io/projects/spring-security)
[![MySQL](https://img.shields.io/badge/MySQL-8.0+-blue?logo=mysql&logoColor=white)](https://www.mysql.com/)
[![Thymeleaf](https://img.shields.io/badge/Thymeleaf-3.1+-green?logo=thymeleaf&logoColor=white)](https://www.thymeleaf.org/)
[![Bootstrap](https://img.shields.io/badge/Bootstrap-5.3-purple?logo=bootstrap&logoColor=white)](https://getbootstrap.com/)
[![Maven](https://img.shields.io/badge/Maven-3.9+-C71A36?logo=apachemaven&logoColor=white)](https://maven.apache.org/)
[![Status](https://img.shields.io/badge/Status-En%20Desarrollo-yellow)](https://github.com)
[![License](https://img.shields.io/badge/License-MIT-green)](LICENSE)

---

## 📋 Descripción del Proyecto

**OTEC Primavera** es una aplicación web desarrollada con **Spring Framework** para la gestión integral de procesos académicos en instituciones de formación técnica. Implementa un sistema completo de administración de cursos, alumnos y matrículas con autenticación segura y dashboards analíticos en tiempo real.

### 🎯 Objetivos Académicos

Este proyecto es una **entrega de portafolio (ABP 6)** del módulo 6: *"Desarrollo de Aplicaciones JEE con Spring Framework"* del **Bootcamp Fullstack Java** de **Talento Digital Chile**. Demuestra competencias en:

- ✅ Arquitectura MVC con Spring Boot
- ✅ Seguridad con Spring Security
- ✅ Persistencia con JPA/Hibernate
- ✅ Desarrollo de interfaces responsivas
- ✅ Buenas prácticas de desarrollo backend

---

## ✨ Características Principales

### 👨‍💼 Panel de Administración
- **Dashboard con métricas en tiempo real**: cursos activos, alumnos inscritos, tasa de éxito, progreso promedio
- **Gestión de cursos**: crear, editar y desactivar programas académicos (borrado lógico)
- **Administración de alumnos**: registrar, editar y asignar cursos a estudiantes
- **Sistema de filtros**: visualizar alumnos sin asignación de curso
- **Alertas visuales**: retroalimentación inmediata en operaciones CRUD

### 👨‍🎓 Portal de Estudiantes
- **Mi Programa Actual**: visualización del curso en curso con barra de progreso
- **Navegación intuitiva**: acceso rápido a información académica personal

### 🔒 Seguridad
- **Autenticación con Spring Security**: login seguro con roles (ADMIN, ALUMNO)
- **Contraseñas encriptadas**: usando BCrypt
- **Protección CSRF**: tokens en formularios sensibles
- **Control de acceso por rol**: endpoints protegidos según permisos

### 📊 Métricas y Análisis
- Cursos activos y su distribución
- Alumnos inscritos vs. no asignados
- Tasa de éxito (aprobados / finalizados)
- Progreso promedio del alumnado
- Distribución de matrículas por estado (EN_CURSO, APROBADO, REPROBADO)

### 🎨 Interfaz Moderna
- **Diseño Dark Mode**: tema moderno EduTech con acentos cyan neón
- **Responsive Design**: optimizado para desktop, tablet y móvil
- **Modales Bootstrap**: confirmaciones elegantes para operaciones críticas
- **Tablas interactivas**: navegación fluida de datos académicos

---

## 🛠️ Tecnologías Utilizadas

### Backend
| Tecnología | Versión | Descripción |
|-----------|---------|-------------|
| **Java** | 17+ | Lenguaje de programación |
| **Spring Boot** | 3.5.13 | Framework principal |
| **Spring MVC** | 6.0+ | Patrón arquitectónico |
| **Spring Security** | 6.0+ | Autenticación y autorización |
| **Spring Data JPA** | 3.0+ | Acceso a datos |
| **Hibernate** | 6.6.45 | ORM (Object-Relational Mapping) |
| **MySQL** | 8.0+ | Base de datos relacional |
| **Lombok** | 1.18.30 | Generación de código boilerplate |
| **Maven** | 3.9+ | Gestor de dependencias |

### Frontend
| Tecnología | Versión | Descripción |
|-----------|---------|-------------|
| **Thymeleaf** | 3.1+ | Motor de templates Java |
| **Bootstrap** | 5.3 | Framework CSS responsive |
| **Bootstrap Icons** | 1.11.1 | Iconografía |
| **HTML5** | - | Estructura semántica |
| **CSS3** | - | Estilos personalizados |
| **JavaScript (Vanilla)** | ES6+ | Interactividad cliente |

### Herramientas de Desarrollo
- **IDE**: IntelliJ IDEA / Eclipse
- **Git**: Control de versiones
- **PostMan**: Testing de APIs (opcional)
- **MySQL Workbench**: Administración de BD

---

## 📦 Requisitos Previos

- **Java JDK 17** o superior
- **Maven 3.9** o superior
- **MySQL 8.0** o superior
- **Git** para control de versiones

---

## 🚀 Instalación y Configuración

### 1. Clonar el Repositorio
```bash
git clone https://github.com/tu-usuario/primavera.git
cd primavera
```

### 2. Configurar Base de Datos MySQL
```bash
# Crear base de datos
CREATE DATABASE otec_primavera;
```

### 3. Variables de Entorno
Crear archivo `.env` en la raíz del proyecto:
```properties
DB_MYSQL_PASSWORD=tu_contraseña_mysql
```

O setear como variable de entorno del sistema:
```bash
# Windows (PowerShell)
$env:DB_MYSQL_PASSWORD="tu_contraseña"

# Linux/Mac
export DB_MYSQL_PASSWORD="tu_contraseña"
```

### 4. Compilar e Instalar
```bash
mvn clean install
```

### 5. Ejecutar la Aplicación
```bash
mvn spring-boot:run
```

Acceder en: `http://localhost:8081`

---

## 👤 Credenciales de Prueba

### Administrador
- **Email**: `admin@otec.cl`
- **Contraseña**: `admin123`

### Alumno
- **Email**: `alumno@otec.cl`
- **Contraseña**: `alumno123`

#### Alumnos Adicionales (Data Seeder)
| Email | Contraseña | Estado |
|-------|-----------|--------|
| `ana@otec.cl` | `ana123` | Aprobado |
| `luis@otec.cl` | `luis123` | Reprobado |
| `camila@otec.cl` | `camila123` | En Curso |

---

## 📁 Estructura del Proyecto

```
primavera/
├── src/
│   ├── main/
│   │   ├── java/com/otec/primavera/
│   │   │   ├── controllers/          # Controladores MVC
│   │   │   │   └── AdminController.java
│   │   │   │   └── AlumnoController.java
│   │   │   │   └── LoginController.java
│   │   │   │   └── HomeController.java
│   │   │   ├── models/               # Entidades JPA
│   │   │   │   ├── Usuario.java
│   │   │   │   ├── Curso.java
│   │   │   │   ├── Matricula.java
│   │   │   │   └── Rol.java
│   │   │   ├── repositories/         # Interfaces JPA
│   │   │   │   ├── UsuarioRepository.java
│   │   │   │   ├── CursoRepository.java
│   │   │   │   ├── MatriculaRepository.java
│   │   │   │   └── RolRepository.java
│   │   │   ├── services/             # Lógica de negocio
│   │   │   │   ├── UsuarioService.java
│   │   │   │   ├── CursoService.java
│   │   │   │   └── MatriculaService.java
│   │   │   ├── security/             # Configuración de seguridad
│   │   │   │   ├── SecurityConfig.java
│   │   │   │   ├── CustomUserDetailsService.java
│   │   │   │   └── DataSeeder.java
│   │   │   ├── dto/                  # Data Transfer Objects
│   │   │   │   └── AdminAlumnoMatriculaForm.java
│   │   │   └── PrimaveraApplication.java
│   │   ├── resources/
│   │   │   ├── application.properties
│   │   │   ├── templates/            # Vistas Thymeleaf
│   │   │   │   ├── login.html
│   │   │   │   ├── error.html
│   │   │   │   ├── admin/
│   │   │   │   │   ├── dashboard.html
│   │   │   │   │   ├── alumnos.html
│   │   │   │   │   ├── editar-alumno.html
│   │   │   │   │   ├── editar-curso.html
│   │   │   │   │   └── ...
│   │   │   │   └── alumno/
│   │   │   │       └── dashboard.html
│   │   │   └── static/               # Recursos estáticos
│   │   │       └── css/
│   │   │           └── styles.css
│   └── test/
│       └── java/com/otec/primavera/
│           └── PrimaveraApplicationTests.java
├── pom.xml                           # Configuración Maven
├── mvnw / mvnw.cmd                   # Maven Wrapper
└── README.md                         # Este archivo
```

---

## 🔄 Flujo de Uso

### Admin
1. Acceder con credenciales de administrador
2. Ver dashboard con métricas académicas
3. Gestionar cursos (crear, editar, eliminar)
4. Administrar alumnos (registrar, editar, asignar curso)
5. Filtrar alumnos sin asignación y asignarles cursos
6. Cerrar sesión desde el menú de perfil

### Alumno
1. Acceder con credenciales de estudiante
2. Ver su programa actual y progreso
3. Visualizar estado de matrícula
4. Cerrar sesión desde el menú de perfil

---

## 🧪 Testing

```bash
# Ejecutar todos los tests
mvn test

# Ejecutar un test específico
mvn test -Dtest=PrimaveraApplicationTests

# Generar reporte de cobertura
mvn test jacoco:report
```

---

## 📊 Diagrama de Componentes

```
┌─────────────────────────────────────────────────────┐
│             Capa de Presentación                    │
│  (Thymeleaf Templates + Bootstrap + CSS/JS)         │
└────────────────┬────────────────────────────────────┘
                 │
┌─────────────────────────────────────────────────────┐
│      Capa de Controladores (Spring MVC)              │
│  AdminController, AlumnoController, LoginController │
└────────────────┬────────────────────────────────────┘
                 │
┌─────────────────────────────────────────────────────┐
│      Capa de Servicios (Lógica de Negocio)          │
│  UsuarioService, CursoService, MatriculaService    │
└────────────────┬────────────────────────────────────┘
                 │
┌─────────────────────────────────────────────────────┐
│   Capa de Acceso a Datos (Spring Data JPA)          │
│  Repositories (JpaRepository Interfaces)            │
└────────────────┬────────────────────────────────────┘
                 │
┌─────────────────────────────────────────────────────┐
│      Capa de Persistencia (Hibernate + MySQL)       │
│  Entidades JPA y Base de Datos Relacional           │
└─────────────────────────────────────────────────────┘
```

---

## 🔐 Seguridad Implementada

### Autenticación
- ✅ Login con usuario y contraseña
- ✅ Contraseñas encriptadas con BCrypt
- ✅ Sesiones gestionadas por Spring Security

### Autorización
- ✅ Rol ADMIN: acceso completo a gestión
- ✅ Rol ALUMNO: acceso limitado a su información
- ✅ Protección de endpoints por rol

### Protección CSRF
- ✅ Tokens CSRF en formularios POST
- ✅ Validación servidor-side

### Validación de Datos
- ✅ Email único en el sistema
- ✅ Campos requeridos en formularios
- ✅ Normalización de valores (ej: progreso 0-100%)

---

## 📈 Mejoras Futuras

- [ ] Implementar API REST completa para consumo externo
- [ ] Agregar autenticación OAuth2 (Google, GitHub)
- [ ] Dashboard con gráficos dinámicos (Charts.js)
- [ ] Exportación de reportes (PDF, Excel)
- [ ] Notificaciones por email
- [ ] Historial completo de matrículas por alumno
- [ ] Sistema de evaluaciones y calificaciones
- [ ] Plataforma de contenidos LMS integrada
- [ ] Análisis avanzado con BI

---

## 👨‍💼 Información de la Entrega

| Aspecto | Detalle |
|--------|--------|
| **Proyecto** | ABP 6 - Proyecto de Capstone |
| **Módulo** | 6: Desarrollo de Aplicaciones JEE con Spring Framework |
| **Bootcamp** | Fullstack Java - Talento Digital Chile |
| **Duración** | 4 semanas de desarrollo |
| **Autor** | Nicole Fernandez |
| **Fecha** | Marzo 2026 |
| **Estado** | ✅ Completado y funcional |

---

## 🤝 Contribuciones

Este es un proyecto académico. Para propuestas de mejora, contactar con el autor.

---

## 📝 Licencia y Fines

Este proyecto se distribuye bajo licencia **MIT** para fines educativos exclusivamente.

⚠️ **Nota Importante**: OTEC Primavera ha sido desarrollado como proyecto académico (ABP 6) del Bootcamp Fullstack Java de Talento Digital Chile. Está diseñado únicamente con propósitos educativos y de demostración.

Ver el archivo `LICENSE` para más detalles.

---

## 📞 Contacto y Soporte

Para dudas o reportar issues:
- 📧 Email: ni.ferng@gmail.com
- 🐙 GitHub: [Tu GitHub]
- 💼 LinkedIn: [Tu LinkedIn]

---

## 🎓 Competencias Demostradas

### Backend
- [x] Arquitectura MVC con Spring Boot
- [x] Configuración de seguridad con Spring Security
- [x] ORM con Hibernate y JPA
- [x] Patrón Repository para acceso a datos
- [x] Servicios con lógica de negocio
- [x] Manejo de transacciones
- [x] Inyección de dependencias
- [x] Validación de datos

### Frontend
- [x] Plantillas dinámicas con Thymeleaf
- [x] Responsive Design con Bootstrap
- [x] CSS personalizado (Dark Mode)
- [x] JavaScript vanilla para interactividad
- [x] Modales y validación cliente

### DevOps
- [x] Gestión de dependencias con Maven
- [x] Control de versiones con Git
- [x] Configuración de entornos
- [x] Data Seeder para datos de prueba

---

<div align="center">

### 🌸 **OTEC Primavera - Donde florecen los talentos académicos** 🌸

**⚠️ Proyecto desarrollado con fines educativos exclusivamente para el Bootcamp Fullstack Java de Talento Digital Chile**

Desarrollado con ❤️ por Nicole Fernandez

**2026 © Talento Digital Chile - ABP 6**

</div>






