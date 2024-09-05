pipeline {
    agent any

    environment {
        DEPLOYMENT_HOST = '10.92.131.112'  // Remote server IP
        CONFIG_FILE = "/data/configurations.json"  // Path to the configuration file
        TARGET_DIR = "/data/fo_installer"  // Target directory for moving the file
    }

    stages {
        stage('Copy Configuration File') {
            steps {
                // Copy the configuration file to the target directory
                sh """
                    cp cloud-user@${DEPLOYMENT_HOST}:${CONFIG_FILE} cloud-user@${DEPLOYMENT_HOST}:${TARGET_DIR}
                """
            }
        }
    }
}
