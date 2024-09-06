pipeline {
    agent any

    environment {
        SOURCE_FILE = ""  // Path to the source file
        TARGET_FILE = "" // Target path for the copied file
        DEPLOYMENT_HOST = '10.91.45.198'  // Remote server IP
    }

    stages {
        stage('Initialize FlowOne Fulfillment deployment') {
            steps {
                // Copy the file to the target directory
                sh """
                ssh cloud-user@${DEPLOYMENT_HOST}
                """
            }
        }
        stage('Initialize FlowOne Fulfillment deployment') {
            steps {
                // Copy the file to the target directory
                sh """
                whoami
                """
            }
        }
    }
}
