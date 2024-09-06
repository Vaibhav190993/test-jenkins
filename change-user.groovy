pipeline {
    agent any

    environment {
        DEPLOYMENT_HOST = '10.91.45.198'  // Remote server IP
    }

    stages {
        stage('SSH into Remote Server') {
            steps {
                // SSH into the remote server
                sh """
                ssh cloud-user@${DEPLOYMENT_HOST}
                """
            }
        }

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
