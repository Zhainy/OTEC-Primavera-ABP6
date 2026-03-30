# CONTRIBUTING.md - Guía de Contribución

## 👋 Bienvenido

Gracias por tu interés en contribuir a **OTEC Primavera**. Este documento proporciona las pautas y procedimientos para contribuir al proyecto.

---

## 📋 Código de Conducta

Este proyecto se rige por un Código de Conducta. Al participar, se espera que mantengas un entorno profesional, respetuoso y acogedor.

### Esperamos:
- ✅ Comunicación respetuosa y constructiva
- ✅ Apertura a diferentes perspectivas
- ✅ Enfoque en el mejoramiento del código
- ✅ Reconocimiento del trabajo de otros

---

## 🐛 Reportar Problemas (Issues)

Si encuentras un bug o problema:

1. **Verifica que no exista** un issue similar ya reportado
2. **Incluye información clara**:
   - Descripción del problema
   - Pasos para reproducirlo
   - Comportamiento esperado vs actual
   - Entorno (Java version, OS, etc.)
3. **Adjunta screenshots o logs** si es relevante

### Ejemplo de Issue
```
Título: Login no valida email duplicado

Descripción:
Al intentar registrar un usuario con un email ya existente,
el sistema no muestra error y crea duplicados.

Pasos para reproducir:
1. Ir a login
2. Registrar con email@example.com
3. Intentar registrar con el mismo email
4. El sistema lo permite

Esperado: Mostrar alerta de email duplicado
Actual: Se crea un segundo usuario
```

---

## ✨ Sugerir Mejoras (Feature Requests)

Para sugerir nuevas funcionalidades:

1. **Usa descriptivo y claro**
2. **Explica el caso de uso**
3. **Sugiere una posible implementación**
4. **Considera el alcance del proyecto**

---

## 🔧 Cómo Contribuir Código

### 1. Fork del Repositorio
```bash
git clone https://github.com/tu-usuario/primavera.git
cd primavera
```

### 2. Crear una Rama de Trabajo
```bash
# Usa convención de nombres clara
git checkout -b feature/nueva-funcionalidad
# o
git checkout -b bugfix/corregir-problema
```

### 3. Seguir el Estilo de Código

#### Java
```java
// Usa camelCase para variables y métodos
private String nombreUsuario;

public String obtenerNombreUsuario() {
    return nombreUsuario;
}

// Javadoc para métodos públicos
/**
 * Calcula la tasa de éxito académico.
 *
 * @return porcentaje de éxito (0-100)
 */
public double calcularTasaExito() {
    // ...
}
```

#### HTML/Thymeleaf
```html
<!-- Usa indentación de 4 espacios -->
<div class="container">
    <h1 th:text="${titulo}">Título</h1>
</div>
```

#### CSS
```css
/* Organiza propiedades lógicamente */
.stat-card {
    display: flex;
    justify-content: space-between;
    
    background: rgba(0, 0, 0, 0.3);
    border: 1px solid var(--glass-border);
    border-radius: 12px;
}
```

### 4. Commits con Mensajes Claros
```bash
# Buen mensaje
git commit -m "feat: agregar filtro de alumnos sin asignación"

# Evitar
git commit -m "cambios varios"
```

#### Convención de Commits
- `feat:` Nueva funcionalidad
- `fix:` Corrección de bug
- `docs:` Cambios en documentación
- `style:` Cambios de formato (sin lógica)
- `refactor:` Refactorización de código
- `test:` Agregar o modificar tests
- `chore:` Cambios en build, dependencies, etc.

### 5. Push y Pull Request
```bash
git push origin feature/nueva-funcionalidad
```

Luego en GitHub:
1. Haz clic en "Compare & Pull Request"
2. Describe los cambios
3. Referencia cualquier issue relacionado (#123)
4. Espera revisión

---

## 📋 Checklist para Pull Requests

Antes de enviar un PR, asegúrate:

- [ ] El código sigue las convenciones del proyecto
- [ ] Los cambios están documentados
- [ ] Se agregaron tests si es necesario
- [ ] Los tests locales pasan: `mvn test`
- [ ] Sin errores de compilación: `mvn clean compile`
- [ ] Sin warnings nuevos
- [ ] La rama está actualizada con main
- [ ] El PR tiene descripción clara
- [ ] No hay commits accidentales

---

## 🧪 Escribir Tests

### Tests Unitarios
```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CursoServiceTest {
    
    @Test
    void testContarCursosActivos() {
        // Arrange
        CursoService service = new CursoService(...);
        
        // Act
        long resultado = service.contarCursosActivos();
        
        // Assert
        assertEquals(3, resultado);
    }
}
```

### Ejecutar Tests
```bash
# Todos los tests
mvn test

# Test específico
mvn test -Dtest=CursoServiceTest

# Con cobertura
mvn test jacoco:report
```

---

## 📚 Documentación

Ayuda a mejorar los docs:

### JavaDoc
```java
/**
 * Obtiene un alumno por ID.
 *
 * @param id el ID único del alumno
 * @return el usuario encontrado
 * @throws IllegalArgumentException si el alumno no existe
 */
public Usuario obtenerPorId(Long id) {
    // ...
}
```

### Archivos README
- Mantén actualizado el README.md
- Documenta nuevas features
- Incluye ejemplos de uso

---

## 🔍 Proceso de Revisión

1. **Revisión Automática**
   - Build compila correctamente
   - Tests pasan
   - Sin warnings nuevos

2. **Revisión Manual**
   - Alguien del equipo revisa el código
   - Se solicitan cambios si es necesario
   - Aprobación del mantenedor

3. **Merge**
   - PR aprobado se fusiona a main
   - Se cierra el issue asociado
   - Se agrega entrada al CHANGELOG.md

---

## 📁 Estructura de Carpetas

Cuando agregues archivos nuevos:

```
src/main/java/com/otec/primavera/
├── controllers/      # Nuevos controladores aquí
├── models/          # Nuevas entidades aquí
├── repositories/    # Nuevos repos aquí
├── services/        # Nueva lógica de negocio aquí
├── security/        # Configuración de seguridad
└── dto/             # Data Transfer Objects
```

```
src/main/resources/templates/
├── admin/           # Templates admin
├── alumno/          # Templates alumno
└── ...
```

---

## 🎯 Prioridades de Contribución

### Alta Prioridad 🔴
- Fixes de seguridad críticos
- Bugs que rompen funcionalidad
- Fixes de rendimiento

### Media Prioridad 🟡
- Mejoras de UI/UX
- Nuevas features solicitadas
- Refactorización de código

### Baja Prioridad 🟢
- Mejoras en documentación
- Optimizaciones menores
- Code cleanup

---

## 📞 Comunicación

- **Issues**: Para bugs y features
- **Email**: ni.ferng@gmail.com
- **GitHub Discussions**: Para preguntas generales

---

## 📜 Licencia

Al contribuir, aceptas que tu código se licencie bajo MIT.

---

## 🙏 Agradecimientos

Apreciamos todas las contribuciones, desde código hasta documentación y feedback. ¡Eres parte del equipo!

---

**Gracias por ayudar a mejorar OTEC Primavera** 🌸




