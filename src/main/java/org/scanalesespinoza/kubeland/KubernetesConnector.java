package org.scanalesespinoza.kubeland;

import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.AppsV1Api;
import io.kubernetes.client.openapi.apis.AppsV1Api.APIlistNamespacedDeploymentRequest;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.apis.CoreV1Api.APIlistNamespaceRequest;
import io.kubernetes.client.openapi.apis.CoreV1Api.APIlistNamespacedPodRequest;
import io.kubernetes.client.openapi.models.V1NamespaceList;
import io.kubernetes.client.openapi.models.V1DeploymentList;
import io.kubernetes.client.openapi.models.V1PodList;
import io.kubernetes.client.util.ClientBuilder;
import io.kubernetes.client.util.KubeConfig;

import java.io.File;
import java.io.FileReader;

public class KubernetesConnector {
    private ApiClient client;

    public boolean connectUsingKubeConfig(File kubeConfigFile) {
        try {
            if (kubeConfigFile != null && kubeConfigFile.exists()) {
                // Using ClientBuilder to build ApiClient from kubeconfig file
                client = ClientBuilder.kubeconfig(KubeConfig.loadKubeConfig(new FileReader(kubeConfigFile))).build();
                Configuration.setDefaultApiClient(client);
                System.out.println("Connected to Kubernetes using KubeConfig");
                testConnection(); // Optional: Perform a test API call to ensure connectivity
                return true;
            } else {
                System.out.println("KubeConfig file is not selected or does not exist.");
                return false;
            }
        } catch (Exception e) {
            System.err.println("Failed to connect using KubeConfig: " + e.getMessage());
            return false;
        }
    }

    private boolean testConnection() {
        try {
            CoreV1Api api = new CoreV1Api();
            APIlistNamespaceRequest namespacesRequest = api.listNamespace();
            V1NamespaceList namespaces = namespacesRequest.execute();
            if (!namespaces.getItems().isEmpty()) {
                String namespaceName = namespaces.getItems().get(0).getMetadata().getName();
                System.out.println("First Namespace: " + namespaceName);

                // Fetch deployments in the first namespace
                AppsV1Api appsApi = new AppsV1Api();
                APIlistNamespacedDeploymentRequest deploymentsRequest = appsApi.listNamespacedDeployment(namespaceName);
                V1DeploymentList deployments = deploymentsRequest.execute();
                System.out.println("Deployments in " + namespaceName + ": " + deployments.getItems().size());

                // Fetch pods in the first namespace
                APIlistNamespacedPodRequest podsRequest = api.listNamespacedPod(namespaceName);
                V1PodList pods = podsRequest.execute();
                System.out.println("Pods in " + namespaceName + ": " + pods.getItems().size());

                return true; // Connection test successful
            } else {
                System.out.println("No namespaces found.");
                return false; // Connection test failed
            }
        } catch (Exception e) {
            System.err.println("Failed to test connection: " + e.getMessage());
            return false;
        }
    }
}
