Om Namah Shivaya
# Docker build & run (production)

This project uses a multi-stage Dockerfile located at the repository root to produce a small, production-ready image.

Build (from repo root):

```bash
# Build the image (uses the multi-stage Dockerfile)
docker build -t expense:latest .
```

Run (simple):

```bash
# Run with default settings
docker run --rm -p 8080:8080 --name expense expense:latest
```

Run with JVM overrides and environment variables:

```bash
docker run --rm -p 8080:8080 \
  -e JAVA_OPTS='-XX:MaxRAMPercentage=60.0' \
  -e SPRING_PROFILES_ACTIVE=prod \
  --name expense expense:latest
```

Notes and tips:
- The image exposes port 8080; change if your application uses a different port.
- The runtime image expects a healthy `/actuator/health` endpoint. If you don't include Spring Boot Actuator, either add it or change the Dockerfile HEALTHCHECK to a lightweight endpoint.
- For Kubernetes / production, prefer configuring memory/cpu limits and use a readinessProbe + livenessProbe that call your app's health endpoints.
- To create smaller artifacts, consider enabling Maven layer caching (CI optimization) or building a native image with GraalVM (advanced).

