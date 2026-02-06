# 🛡️ NIO Async Defender

A high-performance log analyzer developed in Java. This application leverages **Non-blocking I/O (NIO)** and **multi-threaded processing** to scan security records in real-time and communicate with the **DefenderBrain** microservice for threat intelligence.

## 🏗️ Project Structure

The project follows a modular package structure to ensure clean separation of concerns:

```text
nio-async-defender/
├── .github/                # GitHub Actions & workflows
├── .vscode/                # VS Code workspace settings
├── logs/                   # Log storage directory
├── src/
│   ├── main/
│   │   ├── java/io/github/davidmart7n/defender/
│   │   │   ├── analyzer/   # Security logic & HTTP Client communication
│   │   │   │   └── LogAnalyzer.java
│   │   │   ├── config/     # Multithreading & Executor configuration
│   │   │   │   └── AppConfig.java
│   │   │   ├── model/      # Data structures (Records)
│   │   │   │   └── LogEntry.java
│   │   │   ├── reader/     # NIO-based file scanning & task submission
│   │   │   │   └── LogScanner.java
│   │   │   ├── writer/     # Log generation & Report exporting
│   │   │   │   ├── LogGenerator.java
│   │   │   │   └── ReportWriter.java
│   │   │   └── Main.java   # Application entry point
│   │   └── resources/      # App configuration files
│   └── test/               # Not integrated tests yet
├── target/                 # Compiled bytecode and build artifacts
├── final_report.txt        # Output: Analysis summary
├── logEntries.log          # Input: Source log file
└── pom.xml                 # Maven dependencies
```

## ✨ Key Features

* **NIO Streaming:** Uses `java.nio.file.Files.lines()` to stream data, allowing the processing of massive log files without causing `OutOfMemory` errors.
* **Asynchronous Processing:** Powered by a `FixedThreadPool` that scales based on the system's available CPU cores.
* **Real-time Intelligence:** Integrates with a Spring Boot microservice to validate threats via REST API.
* **Graceful Shutdown:** Implements `awaitTermination` logic to ensure all threads complete their analysis before generating reports.

## ⚙️ How It Works

1.  **Generation:** `LogGenerator` populates `logEntries.log` with mock traffic data.
2.  **Scanning:** `LogScanner` reads the file line-by-line using a memory-efficient stream.
3.  **Analysis:** For every line, a task is submitted to the `ExecutorService`. The `LogAnalyzer` parses the CSV-like data into a `LogEntry` object.
4.  **Verification:** If an entry matches suspicious patterns (e.g., path traversal or known attack agents), an HTTP POST request is sent to `DefenderBrain`.
5.  **Reporting:** Once all tasks are finished, `ReportWriter` summarizes the total processed lines, threats found, and a breakdown of attacking IPs.



## 🛠️ Tech Stack

* **Java 17**
* **Jackson Databind:** For JSON serialization.
* **Java HttpClient:** Native asynchronous HTTP/2 communication.
* **Maven:** Project management and build automation.


## 🚀 Getting Started

1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/davidmart7n/nio-async-defender.git](https://github.com/davidmart7n/nio-async-defender.git)
    cd nio-async-defender
    ```

2.  **Ensure the microservice is active:** Make sure **DefenderBrain** is running on `http://localhost:8084`.

3.  **Build the project:**
    ```bash
    mvn clean install
    ```

4.  **Run the application:**
    * Open the project in your IDE (IntelliJ, VS Code, or Eclipse).
    * Navigate to `src/main/java/io/github/davidmart7n/defender/Main.java`.
    * **Run the `Main` class** directly from your editor.

5.  **Check results:**
    * Monitor the console for real-time threat detection.
    * Once the process is complete, review the generated `final_report.txt`.
