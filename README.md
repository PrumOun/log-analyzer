# Log Analyzer Tool

## Skills Demonstrated

**Java • OOP • Collections • Regex • File I/O • Concurrency • JUnit • JSON • CSV • Maven**

---

## Overview

Modern applications generate large amounts of log data. Manually reviewing thousands of log entries is inefficient and time-consuming.

This project is a Java-based Log Analyzer that processes server log files, extracts structured information, and generates useful analytics reports.

The application parses log files, analyzes traffic patterns, detects errors, identifies the most active IP addresses, and exports results in multiple formats.

This project was built to strengthen core Java skills and simulate a real-world backend analytics system.

---

## Features

### Log Processing

- Read log files from disk
- Parse Apache-style log entries using Regular Expressions
- Convert raw log lines into structured Java objects

### Analytics

- Count total requests
- Count requests by IP address
- Analyze HTTP status codes
- Detect and summarize error logs
- Find the most requested endpoints
- Identify Top N IP addresses

### Performance

- Single-threaded log analysis
- Multi-threaded log analysis using ExecutorService
- Thread-safe counting using ConcurrentHashMap

### Reporting

- Console report generation
- JSON report export
- CSV report export

### Testing

- Unit testing using JUnit 5
- Parser validation tests
- Analyzer functionality tests

---

## Project Architecture

```text
Log File
    ↓
File Reader
    ↓
Log Parser (Regex)
    ↓
LogEntry Objects
    ↓
Analysis Engine
    ↓
Report Generator
    ↓
Console / JSON / CSV Reports
```

---

## Technologies Used

| Technology | Purpose |
|------------|----------|
| Java 21 | Core Programming Language |
| Maven | Dependency Management |
| JUnit 5 | Unit Testing |
| Jackson | JSON Serialization |
| Regex | Log Parsing |
| Collections Framework | Data Aggregation |
| ExecutorService | Multithreading |
| ConcurrentHashMap | Thread-Safe Processing |

---

## Project Structure

```text
src
├── main
│   └── java
│       └── com
│           └── odev
│               └── loganalyzer
│
│                   ├── model
│                   │   ├── LogEntry
│                   │   ├── AnalysisResult
│                   │   └── ErrorReport
│                   │
│                   ├── parser
│                   │   └── LogParser
│                   │
│                   ├── service
│                   │   ├── LogAnalyzer
│                   │   ├── MultiThreadedLogAnalyzer
│                   │
│                   ├── report
│                   │   ├── JsonReportGenerator
│                   │   └── CsvReportGenerator
│                   │   ├── ConsoleReportGenerator
│                   │
│                   ├── util
│                   │   └── FileReaderUtil
│                   │   └── FakeLogGenerator
│                   │
│                   └── app
│                       └── Main
│
└── test
    └── java
        └── com
            └── odev
                └── loganalyzer
                    ├── parser
                    │   └── LogParserTest
                    │
                    ├── service
                    │   └── LogAnalyzerTest
                    │   └── MultiThreadedLogAnalyzerTest
                    │
                    └── util
                         └── FileReaderUtilTest
```

---

## Sample Log Input

```text
192.168.1.10 - - [29/Apr/2026:10:15:32 +0000] "GET /api/products HTTP/1.1" 200 1024

172.16.0.1 - - [29/Apr/2026:10:15:32 +0000] "POST /api/login HTTP/1.1" 404 512

10.0.0.1 - - [29/Apr/2026:10:15:32 +0000] "GET /api/orders HTTP/1.1" 500 256
```

---

## Sample Console Analysis Report

```text
================================
      LOG ANALYSIS REPORT
================================

Total Requests: 100

Top IP Addresses:
192.168.1.10                   31
172.16.0.1                     30
10.0.0.1                       20
192.168.1.1                    19

Status Codes:
HTTP 404 -> 28
HTTP 400 -> 23
HTTP 200 -> 21
HTTP 201 -> 18
HTTP 500 -> 10

Endpoints:
/api/login                     27
/api/orders                    27
/api/products                  25
/api/users                     21

================================
```

---

## Sample Console Error Report

```text
=================================
        ERROR REPORT
=================================

Total Errors: 61

--- Errors by Status Code ---
HTTP 404 -> 28
HTTP 400 -> 23
HTTP 500 -> 10

--- Top Error Endpoints ---
/api/login                     21
/api/products                  15
/api/orders                    13
/api/users                     12

--- Top Error IPs ---
172.16.0.1                     19
192.168.1.10                   18
192.168.1.1                    14
10.0.0.1                       10

=================================
```

---

## Sample JSON Analysis Report

```json
{
  "totalRequests" : 100,
  "ipCounts" : {
    "192.168.1.1" : 19,
    "192.168.1.10" : 31,
    "10.0.0.1" : 20,
    "172.16.0.1" : 30
  },
  "statusCounts" : {
    "400" : 23,
    "500" : 10,
    "404" : 28,
    "200" : 21,
    "201" : 18
  },
  "endpointCounts" : {
    "/api/login" : 27,
    "/api/users" : 21,
    "/api/orders" : 27,
    "/api/products" : 25
  },
  "topIps" : [ {
    "192.168.1.10" : 31
  }, {
    "172.16.0.1" : 30
  }, {
    "10.0.0.1" : 20
  }, {
    "192.168.1.1" : 19
  } ]
}
```

---
## Sample JSON Error Report

```json
{
  "totalErrors" : 61,
  "errorByStatusCode" : {
    "400" : 23,
    "500" : 10,
    "404" : 28
  },
  "errorByEndpoint" : {
    "/api/login" : 21,
    "/api/users" : 12,
    "/api/orders" : 13,
    "/api/products" : 15
  },
  "errorByIp" : {
    "192.168.1.1" : 14,
    "192.168.1.10" : 18,
    "10.0.0.1" : 10,
    "172.16.0.1" : 19
  }
}
```

---

## Sample CSV Analysis Export

```csv
LOG ANALYSIS REPORT
SUMMARY
Metric,Value
Total Requests,100

TOP IP ADDRESSES
IP Address,Request Count
192.168.1.10,31
172.16.0.1,30
10.0.0.1,20
192.168.1.1,19

STATUS CODES
Status Code,Request Count
404,28
400,23
200,21
201,18
500,10

ENDPOINTS
Endpoint,Request Count
/api/login,27
/api/orders,27
/api/products,25
/api/users,21

```

---

## Sample CSV Error Export

```csv
ERROR REPORT
SUMMARY
Metric,Value
Total Errors,61

ERRORS BY STATUS CODE
Status Code,Error Count
404,28
400,23
500,10

TOP ERROR ENDPOINTS
Endpoint,Error Count
/api/login,21
/api/products,15
/api/orders,13
/api/users,12

TOP ERROR IPS
IP Address,Error Count
172.16.0.1,19
192.168.1.10,18
192.168.1.1,14
10.0.0.1,10

```

---

## How to Run

### Clone Repository

```bash
git clone https://github.com/PrumOun/log-analyzer
```

### Open Project

1. Open IntelliJ IDEA
2. Select **Open**
3. Choose the project folder

### Run Application

1. Open the `Main` class
2. Click the **Run ▶** button next to the `main()` method
3. View the generated analysis report in the console

### Run Tests

1. Open the test class inside the `test` directory
2. Right-click the test class
3. Select **Run 'TestClassName'**
4. Review test results in the IntelliJ test runner

## Requirements

- Java 21 or later
- IntelliJ IDEA (Community or Ultimate Edition)

---

## Design Decisions

### Why Regex?

Regular Expressions provide a flexible and efficient way to extract structured information from log files.

### Why LogEntry?

A dedicated model class improves readability, maintainability, and type safety.

### Why Separate Parser and Analyzer?

Following the Single Responsibility Principle keeps parsing logic independent from analytics logic.

### Why ConcurrentHashMap?

Multiple threads may update counters simultaneously. ConcurrentHashMap ensures thread-safe operations without manual synchronization.

### Why AnalysisResult?

It provides a single object containing all analytics data, making report generation cleaner and easier to maintain.

---

## Performance

The project includes both:

### Single-threaded Analysis

Suitable for smaller log files.

### Multi-threaded Analysis

Uses ExecutorService and ConcurrentHashMap to process large log files concurrently.

The multi-threaded implementation demonstrates Java concurrency concepts and thread-safe data processing.

---

## What I Learned

Through this project I gained practical experience with:

- Object-Oriented Programming
- Java Collections Framework
- File I/O Operations
- Regular Expressions
- Concurrent Programming
- Thread-Safe Collections
- Unit Testing with JUnit
- JSON Serialization
- CSV Export
- Application Architecture Design

---

## Future Improvements

- Support additional log formats
- Real-time log streaming
- Spring Boot REST API
- Database persistence
- Dashboard visualization
- Docker containerization
- Cloud deployment

---

## Author

**PRUM OUN**

Junior Java Developer

GitHub: https://github.com/PrumOun

---

## License

This project is intended for learning, portfolio demonstration, and educational purposes.