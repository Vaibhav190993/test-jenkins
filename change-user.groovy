pipeline {
    agent any

    environment {
        SOURCE_FILE = ""  // Path to the source file
        TARGET_FILE = "" // Target path for the copied file
        DEPLOYMENT_HOST = '10.92.131.112'  // Remote server IP
    }

    stages {
        stage('Initialize FlowOne Fulfillment deployment') {
            steps {
                // Copy the file to the target directory
                sh """
                cd /data/fo_installer/ &&
                    ./init_deployment.sh
                """
            }
        }
    }
}
