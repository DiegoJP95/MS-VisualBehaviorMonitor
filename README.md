# 👁️ Sistema de Monitoreo Visual IoT para Prevención del Síndrome Visual Informático (SVI)

Este proyecto desarrolla una solución de software y hardware integrada para el monitoreo continuo de comportamiento visual y condiciones ambientales, orientada a la prevención del **Síndrome Visual Informático (SVI)** en adolescentes. Utiliza sensores IoT y un microservicio en Java para capturar, almacenar y analizar datos visuales con enfoque en salud digital.

---

## 📆 Estructura general del proyecto

```
/src
 └── main/java/
       └── ... (microservicio Java - WebFlux + JDBC)
/frontend
 └── react/
      └── notificaciones-visuales (interfaz de alertas aun por implementar)
```

---

## 🧠 Funcionalidad principal

* 📸 **Captura de parpadeos y comportamiento ocular** con un modelo y algoritmo preparado para detectar parpadeos mediante una cámara y uso de NPU.
* 🧳️ **Persistencia de datos** en base de datos SQL vía microservicio Java reactivo.
* 🔔 **Generación de notificaciones** si se detectan condiciones de riesgo (fatiga visual, bajo parpadeo, mala postura, etc.).
* 🌐 **Frontend** básico para visualizar notificaciones del usuario.

---

## 💡 Tecnologías utilizadas

| Componente           | Tecnología                                            |
| -------------------- | ----------------------------------------------------- |
| Backend              | Java 17, Spring WebFlux, JDBC Template, H2/SQL Server |
| IoT/NPU              | Cámara de video integrada (Laptop)                    |
| Comunicación         | HTTP POST / JSON                                      |
| Frontend             | React + TailwindCSS                                   |

---

## 📡 Flujo de arquitectura

```
[Cámara integrada] --> [Modelo entrenado para detección] --> (JSON via HTTP)
                            ↓
                    [Microservicio Java]
                            ↓
                      [Base de datos SQL]
                            ↓
                      [Frontend React]
```

---

## 🚀 Cómo iniciar

### 1. Microservicio Java

```bash
./gradlew bootRun
```

> Asegúrate de configurar el archivo `application.yml` o `application.properties` con la conexión a la base de datos.

### 2. Dispositivo ESP32-CAM

* Usa el código del archivo `main.ino` en la carpeta `/iot/esp32-cam`.
* Sube vía FTDI y conecta al Wi-Fi configurado.
* Envía imágenes o datos vía HTTP POST al endpoint `/visual/upload`.

### 3. Frontend React (opcional)

```bash
cd frontend/notificaciones-visuales
npm install
npm run dev
```

---

## 🛠️ Endpoints principales

* `POST /visual/upload` → Recibe datos visuales en JSON.
* `GET /visual/{userId}` → Devuelve registros visuales por usuario.
* `GET /notificaciones/{userId}` → Devuelve alertas generadas.

---

## 💪 Ejemplo de payload recibido

```json
{
  "userId": "abc123",
  "deviceId": "esp32cam-01",
  "timestamp": "2025-06-10T15:30:00Z",
  "blink_frecuency": 12,
  "blink_duration_ms": 140.5,
  "screen_exposure_minutes": 180,
  "fatigue_level": "media",
  "ambient_light_lux": 300,
  "screen_brightness": 80,
  "posture": "inclinada"
}
```

---

## ⚠️ Notificaciones

El microservicio evalúa los datos históricos de parpadeo. Si detecta:

* Frecuencia baja sostenida
* Exposición prolongada sin descanso
* Iluminación deficiente

... genera alertas y las expone en el frontend.

---

## ✍️ Autor

Desarrollado por Diego Peña Arce y Denisse Rosales Canales como parte de una solución de investigación en salud digital para adolescentes en Lima, Perú.
Incluye aportes de tecnologías RAG, IoT y visión computacional.
