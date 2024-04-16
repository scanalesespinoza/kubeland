
# Kubeland Desktop Client

## Overview
Kubeland Desktop Client is an open-source tool designed to make it easier to understand and manage the status of Kubernetes workloads directly from your desktop. This application provides a user-friendly interface for visualizing the state of various Kubernetes resources including namespaces, deployments, and pods.

## Features
- Visual representation of Kubernetes workloads.
- Manage and view namespaces, deployments, and pods.
- Easy-to-use interface for navigating and understanding cluster statuses.

## Prerequisites
- Java 11 or higher
- Maven
- Access to a Kubernetes cluster

## Installation
To install the Kubeland Desktop Client, follow these steps:

1. Clone the repository:
   ```bash
   git clone https://github.com/scanalesespinoza/kubeland.git
   ```
2. Navigate to the project directory:
   ```bash
   cd kubeland
   ```
3. Build the project using Maven:
   ```bash
   mvn clean install
   ```

## Usage
To run the application, execute:
```bash
java -jar target/kubeland-1.0-SNAPSHOT.jar
```
The GUI will prompt you to load your kubeconfig file and will automatically connect to your Kubernetes cluster.

## Contributing
Contributions are welcome! Please fork the repository and submit pull requests with your proposed changes.

## Feature Requests
Please submit any requests for new features or enhancements via GitHub issues in the repository.

## License
This project is licensed under the Apache License 2.0. See the LICENSE file for more details.

## About
Kubeland Desktop Client is an open-source tool that simplifies the management of Kubernetes clusters. It provides a clear and intuitive way to visualize and understand your Kubernetes workloads.
