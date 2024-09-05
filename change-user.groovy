pipeline {
    agent any

    environment {
        SOURCE_FILE = ""  // Path to the source file
        TARGET_FILE = "" // Target path for the copied file
        DEPLOYMENT_HOST = '10.92.131.112'  // Remote server IP
    }

    stages {
        stage('Copy Configuration File') {
            steps {
                // Copy the file to the target directory
                sh """
                    ssh cloud-user@${DEPLOYMENT_HOST} cp /data/all-envs-and-hosts.yml /data/fo_installer/inventory/yaml/all-envs-and-hosts.yml
                """
            }
        }
    }
}
