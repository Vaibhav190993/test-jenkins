pipeline {
    agent any

    environment {
        SOURCE_FILE = "/data/all-envs-and-hosts.yml"  // Path to the source file
        TARGET_FILE = "/data/fo_installer/inventory/yaml/all-envs-and-hosts.yml"  // Target path for the copied file
    }

    stages {
        stage('Copy Configuration File') {
            steps {
                // Copy the file to the target directory
                sh """
                    cp ${SOURCE_FILE} ${TARGET_FILE}
                """
            }
        }
    }
}
