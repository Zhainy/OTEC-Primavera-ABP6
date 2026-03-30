# Changelog - OTEC Primavera

Todos los cambios notables en este proyecto serán documentados en este archivo.

El formato se basa en [Keep a Changelog](https://keepachangelog.com/es-ES/1.0.0/),
y este proyecto cumple con [Semantic Versioning](https://semver.org/lang/es/).

---

## [1.0.0] - 2026-03-30

### ✨ Agregado

#### Interfaz y UX
- 🎨 Dashboard admin completo con métricas en tiempo real
- 🎨 Pantalla de administración de alumnos
- 🎨 Pantalla de edición de alumno con asignación de curso/estado
- 🎨 Portal de estudiante con vista de programa actual
- 🎨 Diseño Dark Mode con tema EduTech cyan neón
- 🎨 Responsive design optimizado para móvil, tablet y desktop
- 🎨 Modales Bootstrap para confirmaciones elegantes
- 🎨 Alertas visuales de éxito/error para operaciones CRUD
- 🎨 Navbar interactivo con menú de perfil y logout

#### Funcionalidades Backend
- 👨‍💼 **Autenticación y Seguridad**
  - Login con Spring Security
  - Roles ADMIN y ALUMNO con control de acceso
  - Contraseñas encriptadas con BCrypt
  - Protección CSRF en formularios

- 📚 **Gestión de Cursos**
  - Crear programas académicos
  - Editar cursos existentes
  - Desactivar cursos (borrado lógico)
  - Listado de cursos activos

- 👥 **Gestión de Alumnos**
  - Registrar nuevos alumnos
  - Validación de email único
  - Edición de información de alumno
  - Asignación de cursos y estados
  - Histórico de matrículas

- 📊 **Matrículas y Académico**
  - Crear y actualizar matrículas
  - Estados: EN_CURSO, APROBADO, REPROBADO
  - Porcentaje de progreso por alumno
  - Cálculo de tasa de éxito
  - Promedio de progreso general

- 📈 **Métricas y Análisis**
  - Cursos activos
  - Alumnos inscritos
  - Alumnos no asignados (clickable, filtra automáticamente)
  - Tasa de éxito académico
  - Progreso promedio
  - Distribución de matrículas por estado

#### Datos de Prueba
- 🌱 DataSeeder automático con:
  - 5 alumnos demo (incluyendo aprobado y reprobado)
  - 3 cursos demo
  - 4 matrículas demo en diferentes estados

#### Documentación
- 📖 README.md completo con badges y guía de instalación
- 📋 CHANGELOG.md para historial de cambios
- 🗂️ .gitignore optimizado para proyecto Java/Maven

### 🔧 Cambios Técnicos

#### Backend
- ✅ Arquitectura MVC con Spring Boot 3.5.13
- ✅ Spring Security para autenticación y autorización
- ✅ Spring Data JPA con Hibernate ORM
- ✅ MySQL 8.0 como base de datos
- ✅ DTOs (Data Transfer Objects) para formularios complejos
- ✅ Servicios con lógica de negocio transaccional
- ✅ Repositorios JPA con consultas personalizadas
- ✅ Manejo de excepciones y validaciones

#### Frontend
- ✅ Thymeleaf como motor de templates
- ✅ Bootstrap 5.3 para estilos responsivos
- ✅ Bootstrap Icons para iconografía
- ✅ CSS personalizado (EduTech Dark Mode)
- ✅ JavaScript vanilla para interactividad
- ✅ Modales y validaciones cliente-side

#### Infrastructure
- ✅ Maven para gestión de dependencias
- ✅ Git para control de versiones
- ✅ Propiedades externalizadas en application.properties

### 🔐 Seguridad

- ✅ Autenticación segura con Spring Security
- ✅ Contraseñas hasheadas con BCrypt
- ✅ Tokens CSRF en formularios POST
- ✅ Control de acceso por rol
- ✅ Validación de datos entrada
- ✅ Protección de endpoints sensibles

### 📊 Base de Datos

Entidades principales:
- **Usuario**: Información de usuarios (admin y alumnos)
- **Rol**: Definición de roles (ADMIN, ALUMNO)
- **Curso**: Programas académicos disponibles
- **Matricula**: Registro de inscripciones de alumnos

Relaciones:
- Usuario ↔ Rol (1:1)
- Usuario ↔ Matricula (1:N)
- Curso ↔ Matricula (1:N)

### 🚀 Características de Producción

- ✅ Data Seeder automático para datos iniciales
- ✅ Borrado lógico de registros (soft delete)
- ✅ Transacciones ACID
- ✅ Caché y lazy loading optimizado
- ✅ Logs estructurados
- ✅ Manejo de errores centralizado

---

## [Futuro] - Por Implementar

### 🔄 Pendiente
- [ ] API REST completa (JSON endpoints)
- [ ] Autenticación OAuth2 (Google, GitHub)
- [ ] Dashboard con gráficos dinámicos (Charts.js)
- [ ] Exportación de reportes (PDF, Excel)
- [ ] Sistema de notificaciones por email
- [ ] Historial visual de cambios
- [ ] Evaluaciones y sistema de calificaciones
- [ ] Plataforma LMS integrada
- [ ] Análisis avanzado con BI
- [ ] Tests unitarios completos (JUnit5, Mockito)
- [ ] Tests de integración
- [ ] Docker para containerización
- [ ] CI/CD con GitHub Actions

---

## 🏆 Estado del Proyecto

- **Versión Actual**: 1.0.0
- **Estado**: ✅ **COMPLETADO Y FUNCIONAL**
- **Última Actualización**: 2026-03-30
- **Módulo**: ABP 6 - Capstone Fullstack Java
- **Bootcamp**: Talento Digital Chile

---

## 📝 Notas de Versión

### v1.0.0 - Release Inicial

Esta es la versión 1.0.0 de OTEC Primavera. El proyecto implementa exitosamente:

✅ Sistema completo de gestión académica
✅ Autenticación y autorización segura
✅ Dashboard con métricas en tiempo real
✅ Gestión de cursos y alumnos
✅ Interfaz moderna y responsiva
✅ Data persistente en MySQL
✅ Código limpio y bien documentado

**Criterios de Aceptación Cumplidos:**
- [x] Autenticación con roles
- [x] CRUD completo de cursos
- [x] Gestión de alumnos
- [x] Asignación de cursos a alumnos
- [x] Dashboard con métricas
- [x] Interfaz responsiva
- [x] Datos de prueba automáticos
- [x] Seguridad CSRF
- [x] Validaciones de datos
- [x] Documentación del proyecto

---

**Desarrollado con ❤️ por Nicole Fernandez**
**Marzo 2026 - Talento Digital Chile - ABP 6**


