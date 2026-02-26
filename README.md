# E-Commerce Product Management API

A REST API for managing products in an e-commerce system, built with Spring Boot.

## Prerequisites

- **Java 21** or later
- **Maven 3.9+** (or use the included Maven wrapper)

### Verifying Java Installation

```bash
java -version
```

Ensure the output shows Java 21 or higher.

## Project Structure

```
src/main/java/ecommerce/api/
├── controller/          # REST controllers
├── service/             # Business logic
├── domain/              # Entities and DTOs
├── repository/          # Data access layer
└── exception/           # Custom exceptions
```

## Installation

### Command Line

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd ecommerce-java
   ```

2. **Install dependencies**
   ```bash
   ./mvnw dependency:resolve
   ```
   On Windows:
   ```cmd
   mvnw.cmd dependency:resolve
   ```

### IntelliJ IDEA

1. **Open the project**
   - Go to **File > Open**
   - Navigate to the project folder and select the `pom.xml` file
   - Click **Open as Project**

2. **Configure JDK**
   - Go to **File > Project Structure** (Ctrl+Alt+Shift+S)
   - Under **Project Settings > Project**, set the SDK to Java 21
   - If Java 21 is not listed, click **Add SDK > Download JDK** and select version 21

3. **Enable Lombok**
   - Go to **File > Settings** (Ctrl+Alt+S)
   - Navigate to **Build, Execution, Deployment > Compiler > Annotation Processors**
   - Check **Enable annotation processing**
   - IntelliJ should prompt to install the Lombok plugin if not already installed

4. **Import Maven dependencies**
   - IntelliJ automatically imports dependencies when opening a Maven project
   - If needed, click the Maven icon in the sidebar and press the refresh button

## Running the Application

### Command Line (Maven Wrapper)

```bash
./mvnw spring-boot:run
```

On Windows:
```cmd
mvnw.cmd spring-boot:run
```

### Command Line (Java)

First, build the project:
```bash
./mvnw clean package
```

Then run the JAR:
```bash
java -jar target/api-0.0.1-SNAPSHOT.jar
```

### IntelliJ IDEA

**Option 1: Run from main class**
1. Navigate to `src/main/java/ecommerce/api/ApiApplication.java`
2. Click the green play button in the gutter next to the `main` method
3. Select **Run 'ApiApplication'**

**Option 2: Run from Maven tool window**
1. Open the Maven tool window (View > Tool Windows > Maven)
2. Expand **Plugins > spring-boot**
3. Double-click **spring-boot:run**

**Option 3: Create a Run Configuration**
1. Go to **Run > Edit Configurations**
2. Click **+** and select **Spring Boot**
3. Set the main class to `ecommerce.api.ApiApplication`
4. Click **OK** and run with the play button in the toolbar

The application starts on **http://localhost:8080**

## API Endpoints

| Method | Endpoint              | Description           |
|--------|-----------------------|-----------------------|
| GET    | /api/products         | List all products     |
| GET    | /api/products/{id}    | Get product by ID     |
| POST   | /api/products         | Create a new product  |
| PATCH  | /api/products/{id}    | Update a product      |
| DELETE | /api/products/{id}    | Delete a product      |

### Example Requests (curl)

**Create a product:**
```bash
curl -X POST http://localhost:8080/api/products \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Wireless Mouse",
    "sku": "WM-001",
    "price": 29.99,
    "stockQuantity": 100
  }'
```

**Get all products:**
```bash
curl http://localhost:8080/api/products
```

### IntelliJ HTTP Client

IntelliJ has a built-in HTTP client for testing APIs. Create a file with `.http` extension (e.g., `requests.http`):

```http
### Get all products
GET http://localhost:8080/api/products

### Get product by ID
GET http://localhost:8080/api/products/{{productId}}

### Create a product
POST http://localhost:8080/api/products
Content-Type: application/json

{
  "name": "Wireless Mouse",
  "sku": "WM-001",
  "price": 29.99,
  "stockQuantity": 100
}

### Update a product
PATCH http://localhost:8080/api/products/{{productId}}
Content-Type: application/json

{
  "price": 24.99
}

### Delete a product
DELETE http://localhost:8080/api/products/{{productId}}
```

Click the green play button next to any request to execute it. Results appear in the Run tool window.

## Debugging

### Enable Debug Logging

Add to `src/main/resources/application.properties`:
```properties
logging.level.ecommerce.api=DEBUG
logging.level.org.springframework.web=DEBUG
```

### IntelliJ IDEA Debugging

**Starting the debugger:**
1. Navigate to `ApiApplication.java`
2. Click the green play button in the gutter next to `main`
3. Select **Debug 'ApiApplication'** (or press Shift+F9)

**Setting breakpoints:**
- Click in the gutter (left margin) next to any line of code to set a breakpoint
- Right-click a breakpoint to add conditions or log messages

**Debugger controls:**
| Action | Shortcut |
|--------|----------|
| Step Over | F8 |
| Step Into | F7 |
| Step Out | Shift+F8 |
| Resume | F9 |
| Evaluate Expression | Alt+F8 |

**Useful debugger features:**
- **Watches**: Right-click a variable and select "Add to Watches" to monitor its value
- **Evaluate Expression**: Press Alt+F8 to evaluate any expression during debugging
- **Conditional Breakpoints**: Right-click a breakpoint to add a condition (e.g., `id.equals("123")`)

**Debug tool windows:**
- **Variables**: Shows all variables in the current scope
- **Frames**: Shows the call stack
- **Console**: Shows application output and allows input

### Remote Debugging

If running the application outside IntelliJ:

1. Start the application with debug options:
   ```bash
   ./mvnw spring-boot:run -Dspring-boot.run.jvmArguments="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005"
   ```

2. In IntelliJ, go to **Run > Edit Configurations**
3. Click **+** and select **Remote JVM Debug**
4. Set port to `5005` and click **OK**
5. Run the remote debug configuration to attach

### Debugging from IntelliJ on Windows with WSL2

When running the project inside WSL2, IntelliJ cannot launch the app directly for debugging. The `debug.sh` script included in the project root handles this.

**One-time setup:**

1. Ensure `debug.sh` is executable:
   ```bash
   chmod +x debug.sh
   ```

2. In IntelliJ, create a Remote JVM Debug configuration:
   - Go to **Run > Edit Configurations**
   - Click **+** and select **Remote JVM Debug**
   - Set **Host**: `localhost`, **Port**: `5005`, **Mode**: `Attach to remote JVM`
   - Click **OK**

**Every time you want to debug:**

1. In your WSL2 terminal, run:
   ```bash
   ./debug.sh
   ```
   The app will pause and wait for the debugger to attach (`suspend=y`).

2. In IntelliJ, immediately click **Debug** on the Remote JVM Debug configuration.

3. The app starts and breakpoints work normally.

### Common Debug Scenarios

**View incoming requests:**
```properties
logging.level.org.springframework.web.servlet.DispatcherServlet=TRACE
```

**View SQL queries (when using database):**
```properties
logging.level.org.springframework.jdbc=DEBUG
spring.h2.console.enabled=true
```

Access H2 console at: http://localhost:8080/h2-console

## Running Tests

### Command Line

```bash
./mvnw test
```

Run a specific test class:
```bash
./mvnw test -Dtest=ApiApplicationTests
```

### IntelliJ IDEA

**Run all tests:**
1. Right-click the `src/test/java` folder in the Project view
2. Select **Run 'All Tests'**

**Run a single test class:**
1. Open the test file (e.g., `ApiApplicationTests.java`)
2. Click the green play button next to the class name
3. Select **Run 'ApiApplicationTests'**

**Run a single test method:**
1. Click the green play button next to the test method
2. Select **Run 'testMethodName'**

**Debug tests:**
- Use the same steps but select **Debug** instead of **Run**
- Breakpoints work the same way as when debugging the application

**View test results:**
- The Run tool window shows test results with pass/fail status
- Click on a failed test to see the failure message and stack trace
- Double-click to navigate to the test code

## Building for Production

```bash
./mvnw clean package -DskipTests
```

The executable JAR will be in `target/api-0.0.1-SNAPSHOT.jar`

## Technology Stack

- Java 21
- Spring Boot 3.5.0
- Spring Web MVC
- Spring Validation
- H2 Database (development)
- Lombok
- Maven
