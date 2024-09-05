pipeline {
    agent any

    environment {
        CONFIG_FILE = "/data/configurations.json"  // Path to the configuration file
        TARGET_DIR = "/data/fo_installer"  // Target directory for moving the file
    }

    stages {
        stage('Copy Configuration File') {
            steps {
                // Copy the configuration file to the target directory locally
                sh """
                    cp ${CONFIG_FILE} ${TARGET_DIR}
                """
            }
        }
    }
}
