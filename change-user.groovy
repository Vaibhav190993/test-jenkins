pipeline {
    agent any

    environment {
        DEPLOYMENT_HOST = '10.91.45.198'  // Remote server IP
    }

    stages {
        stage('Verify User on Remote Server') {
            steps {
                // Verify the user running commands on the remote server
                sh """
                whoami
                """
            }
        }
    }
}
