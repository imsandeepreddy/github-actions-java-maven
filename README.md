# Multi-Module Build Systems – DevOps-Oriented README

This repository demonstrates practical usage of:

* Maven (multi-module Java)
* Gradle (multi-module Java)
* Node.js (npm/yarn-based project)

It is designed for **CI/CD implementation practice** and DevOps-focused understanding.

---

# 1️⃣ Project Structure – Maven / Gradle / Node.js

---

## A. Maven Multi-Module Structure

```
maven-multi-module-demo/
│
├── pom.xml                ← Parent POM (packaging: pom)
│
├── common/
│   ├── pom.xml            ← Library module
│   └── src/main/java/
│
└── service/
    ├── pom.xml            ← Executable Spring Boot app
    ├── src/main/java/
    └── src/test/java/
```

### Structure Rules (Maven Standard Layout)

| Folder             | Purpose         |
| ------------------ | --------------- |
| src/main/java      | Production code |
| src/test/java      | Unit tests      |
| src/main/resources | App configs     |
| target/            | Build output    |

---

## B. Gradle Multi-Module Structure

```
multi-module-gradle/
│
├── settings.gradle        ← Module declaration
├── build.gradle           ← Root config
│
├── common/
│   └── build.gradle
│
└── service/
    └── build.gradle
```

Gradle also follows:

```
src/main/java
src/test/java
build/
```

---

## C. Node.js Project Structure

```
node-app/
│
├── package.json
├── package-lock.json
├── node_modules/
├── src/
└── dist/ (after build)
```

Monorepo example:

```
packages/
  api/
  web/
```

---

# 2️⃣ Significance of Core Files

---

## Maven – `pom.xml`

Defines:

* Project metadata
* Dependencies
* Plugin configuration
* Modules (if parent)
* Build lifecycle behavior

Key sections:

```xml
<dependencies>
<build>
<modules>
<dependencyManagement>
<pluginManagement>
```

Root POM (`packaging=pom`) acts as:

* Aggregator
* Version manager
* Build orchestrator

---

## Gradle – `build.gradle`

Defines:

* Plugins
* Dependencies
* Tasks
* Custom logic

Example:

```groovy
plugins {
    id 'java'
}

dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-web'
}
```

---

## Gradle – `settings.gradle`

Declares modules:

```groovy
include 'common', 'service'
```

Without this file, submodules are not recognized.

---

## Node.js – `package.json`

Defines:

* Dependencies
* Dev dependencies
* Scripts
* Version
* Entry point

Example:

```json
{
  "scripts": {
    "start": "node index.js",
    "build": "webpack"
  }
}
```

---

## Node.js – `package-lock.json`

Critical for:

* Deterministic builds
* Exact dependency tree
* CI reproducibility

Must always be committed.

Equivalent to:

* Maven dependency tree resolution
* Gradle resolved dependency graph

---

# 3️⃣ Lifecycle and Commands

---

## Maven Lifecycle

### Main Lifecycle

| Phase    | Purpose               |
| -------- | --------------------- |
| validate | Validate project      |
| compile  | Compile source        |
| test     | Run tests             |
| package  | Create jar/war        |
| verify   | Run checks            |
| install  | Install to local repo |
| deploy   | Push to remote repo   |

Common Commands:

```
mvn clean install
mvn package
mvn test
mvn dependency:tree
```

---

## Gradle Lifecycle

Phases:

1. Initialization
2. Configuration
3. Execution

Common Commands:

```
./gradlew build
./gradlew test
./gradlew dependencies
./gradlew clean
```

Build output goes to:

```
build/
```

---

## Node.js Lifecycle

Install dependencies:

```
npm install
```

Clean CI install:

```
npm ci
```

Run build script:

```
npm run build
```

Run app:

```
npm start
```

---

# 4️⃣ Build Output – What Gets Created

---

## Maven

After:

```
mvn package
```

Output:

```
service/target/service-1.0.0.jar
common/target/common-1.0.0.jar
```

If Spring Boot:

Fat jar created including dependencies.

---

## Gradle

After:

```
./gradlew build
```

Output:

```
build/libs/service-1.0.0.jar
```

---

## Node.js

After:

```
npm run build
```

Output typically:

```
dist/
```

Or bundled JS file.

---

# What Should Be Packaged in Docker?

---

## Maven / Gradle

Only the executable artifact:

```
service-1.0.0.jar
```

NOT:

* target/
* .m2/
* source code

---

## Node.js

Two approaches:

### Option A – Copy source + node_modules

```
COPY package.json package-lock.json
RUN npm ci
COPY . .
```

### Option B – Build outside, copy only dist

```
COPY dist/
```

Preferred:

* Multi-stage builds
* Minimal runtime image

---

# 5️⃣ DevOps Topics You Must Learn

---

## Dependency Management

* Transitive dependencies
* Version conflicts
* Exclusions
* Lock files importance

---

## CI/CD Concepts

* Build reproducibility
* Caching dependencies
* Artifact storage (Nexus/Artifactory)
* Semantic versioning
* Git tagging

---

## Docker Best Practices

* Multi-stage builds
* Small base images
* Non-root containers
* Layer caching optimization

---

## Security

* Dependency vulnerability scanning
* Snyk / Trivy
* SBOM generation
* Supply chain security

---

## Performance Optimization

* Parallel builds
* Gradle build cache
* npm ci vs install
* Maven wrapper usage

---

## Repository Management

* Snapshot vs release builds
* Artifact promotion
* Immutable artifacts

---

# 6️⃣ DevOps Interview Questions

---

## Maven

1. What is the difference between `install` and `deploy`?
2. What is transitive dependency?
3. How does Maven resolve version conflicts?
4. What is dependencyManagement?
5. Why use Maven Wrapper?
6. What happens if packaging is `pom`?
7. Difference between jar and war?

---

## Gradle

1. Why is Gradle faster than Maven?
2. What are configuration and execution phases?
3. What is the Gradle daemon?
4. How do you force a dependency version?
5. What is a multi-project build?

---

## Node.js

1. Difference between `npm install` and `npm ci`?
2. Why commit package-lock.json?
3. What are devDependencies?
4. How do you handle dependency vulnerabilities?
5. What is a monorepo?

---

## Docker + CI/CD

1. Why use multi-stage builds?
2. How do you cache dependencies in CI?
3. How do you version Docker images?
4. What is immutable infrastructure?
5. How do you reduce Docker image size?

---

# Practical Exercises To Master

1. Break dependency version → analyze resolution
2. Convert Maven project to Gradle
3. Add vulnerability scanner to pipeline
4. Implement multi-stage Dockerfile
5. Push artifacts to Nexus
6. Implement release tagging workflow
7. Enable parallel builds in CI

---

# Final DevOps Mindset

As a DevOps engineer, you must understand:

* Build systems deeply
* Artifact lifecycle
* Dependency reproducibility
* Container optimization
* CI/CD orchestration
* Security and compliance integration

Build tools are not just developer tools — they are critical components of production delivery pipelines.

---
