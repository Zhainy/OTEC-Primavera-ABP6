# 🚀 Quick Start - Guía Rápida de Inicio

Tener **OTEC Primavera** corriendo en menos de 5 minutos.

---

## ⚡ Requisitos (30 segundos)

```bash
# Verificar requisitos instalados
java -version              # Debe ser Java 17+
mvn -version              # Debe ser Maven 3.9+
mysql --version           # Debe ser MySQL 8.0+
```

---

## 🔧 Configuración Rápida (2 minutos)

### 1️⃣ Clonar repositorio
```bash
git clone https://github.com/tu-usuario/primavera.git
cd primavera
```

### 2️⃣ Base de datos
```bash
# Abrir MySQL
mysql -u root -p

# Dentro de MySQL
CREATE DATABASE otec_primavera;
EXIT;
```

### 3️⃣ Configurar credenciales
```bash
# PowerShell (Windows)
$env:DB_MYSQL_PASSWORD="tu_contraseña_mysql"

# O crear archivo .env
echo "DB_MYSQL_PASSWORD=tu_contraseña" > .env
```

### 4️⃣ Compilar e instalar
```bash
mvn clean install
```

---

## ▶️ Ejecutar la Aplicación (30 segundos)

```bash
# Opción 1: Maven
mvn spring-boot:run

# Opción 2: IDE (IntelliJ IDEA / Eclipse)
# Click derecho en PrimaveraApplication.java > Run

# Opción 3: Jar compilado
java -jar target/primavera-0.0.1-SNAPSHOT.jar
```

**Acceder en**: http://localhost:8081

---

## 🔐 Credenciales de Prueba

### Administrador
```
Email: admin@otec.cl
Password: admin123
```

### Estudiante
```
Email: alumno@otec.cl
Password: alumno123
```

### Otros Estudiantes
```
ana@otec.cl / ana123
luis@otec.cl / luis123
camila@otec.cl / camila123
```

---

## 🎯 Primeros Pasos en la Aplicación

### Si eres Admin 👨‍💼
1. Login con `admin@otec.cl`
2. Ve al Dashboard - verás todas las métricas
3. Click en **"Alumnos"** para gestionar
4. Crea un nuevo alumno o edita uno existente
5. Clic en el botón **"Editar"** para asignar curso

### Si eres Alumno 👨‍🎓
1. Login con `alumno@otec.cl`
2. Verás tu programa actual
3. Visualiza tu progreso
4. Click en perfil para cerrar sesión

---

## 🐛 Solucionar Problemas

### ❌ Error: "Cannot connect to database"
```bash
# Solución: Verificar MySQL está corriendo
# Windows
services.msc          # Buscar MySQL80

# Mac
brew services list

# Linux
sudo systemctl status mysql
```

### ❌ Error: "Port 8081 already in use"
```bash
# Solución: Cambiar puerto en application.properties
# Edita: src/main/resources/application.properties
server.port=8082
```

### ❌ Error: Maven build fails
```bash
# Solución: Limpiar y reconstruir
mvn clean install -DskipTests
```

### ❌ Error: "CREATE DATABASE denied"
```bash
# Solución: Usar root de MySQL con permisos
mysql -u root -p        # Usar password correcto
```

---

## 📊 Ver Datos Demo

Los datos de prueba se crean **automáticamente** al iniciar:

| Recurso | Cantidad | Detalles |
|---------|----------|----------|
| **Alumnos** | 5 | Ana, Luis, Camila, + 2 demo |
| **Cursos** | 3 | Java, React, Datos |
| **Matrículas** | 4 | Diversos estados |

---

## 🔍 Explorar la Aplicación

### Dashboard Admin
- **URL**: http://localhost:8081/admin/dashboard
- Ver todas las métricas en tiempo real
- Click en "Alumnos No Asignados" para filtrar

### Administrar Alumnos
- **URL**: http://localhost:8081/admin/alumnos
- Registrar nuevos alumnos
- Editar y asignar cursos

### Editar Alumno
- **URL**: http://localhost:8081/admin/alumnos/editar/{id}
- Cambiar curso y estado
- Actualizar progreso

### Portal Alumno
- **URL**: http://localhost:8081/alumno/dashboard
- Ver programa actual
- Ver barra de progreso

---

## 💻 Comandos Útiles

```bash
# Ejecutar tests
mvn test

# Solo compilar (sin tests)
mvn clean compile

# Ver versiones de dependencias
mvn dependency:tree

# Actualizar dependencias
mvn versions:display-dependency-updates

# Formatear código
mvn spotless:apply

# Generar reporte
mvn clean install site

# Ver logs detallados
mvn spring-boot:run -X
```

---

## 📁 Archivos Importantes

```
primavera/
├── pom.xml                    # Dependencias Maven
├── .env                       # Variables de entorno (crear)
├── README.md                  # Documentación principal
├── CHANGELOG.md               # Historial de cambios
├── CONTRIBUTING.md            # Guía de contribución
└── src/main/resources/
    └── application.properties # Configuración de app
```

---

## 🧭 Estructura del Código

```
controllers/
├── AdminController.java       # Gestión admin
├── AlumnoController.java      # Portal alumno
└── LoginController.java       # Autenticación

services/
├── CursoService.java          # Lógica cursos
├── UsuarioService.java        # Lógica usuarios
└── MatriculaService.java      # Lógica matrículas

models/
├── Usuario.java               # Tabla usuarios
├── Curso.java                 # Tabla cursos
└── Matricula.java             # Tabla matrículas
```

---

## 🎓 Próximos Pasos

### Aprender el Código
1. Lee `AdminController.java` para entender las rutas
2. Revisa `CursoService.java` para ver lógica de negocio
3. Explora `admin/dashboard.html` para ver Thymeleaf

### Hacer Cambios
1. Crea una rama: `git checkout -b feature/mi-cambio`
2. Realiza cambios
3. Commit: `git commit -m "feat: descripción"`
4. Push: `git push origin feature/mi-cambio`

### Agregar Nueva Funcionalidad
1. Define nueva entidad en `models/`
2. Crea repositorio en `repositories/`
3. Implementa servicio en `services/`
4. Agrega controlador en `controllers/`
5. Crea templates en `templates/`

---

## 🆘 ¿Necesitas Ayuda?

| Recurso | Ubicación |
|---------|-----------|
| **Documentación Completa** | [README.md](README.md) |
| **Historial de Cambios** | [CHANGELOG.md](CHANGELOG.md) |
| **Guía de Contribución** | [CONTRIBUTING.md](CONTRIBUTING.md) |
| **Email de Soporte** | ni.ferng@gmail.com |

---

## ✅ Checklist de Inicio

- [ ] Java 17+ instalado
- [ ] Maven 3.9+ instalado
- [ ] MySQL 8.0+ instalado y corriendo
- [ ] Base de datos creada
- [ ] Variables de entorno configuradas
- [ ] Repositorio clonado
- [ ] `mvn clean install` completado exitosamente
- [ ] Aplicación ejecutándose en puerto 8081
- [ ] Acceso a http://localhost:8081 exitoso
- [ ] Login como admin funciona
- [ ] Login como alumno funciona

---

<div align="center">

### 🌸 ¡Listo para empezar!

**Bienvenido a OTEC Primavera** 🚀

</div>




