pipeline {
    agent any

    environment {
        // Define the SSH credentials ID that Jenkins should use
        DEPLOYMENT_HOST = '10.92.131.112'
    }

    stages {
        stage('check user') {
            steps {
                script {
                    // Use the 'ssh' command to connect and execute commands remotely
                    sh """
                        whoami
                    """
                }
            }
        }
        stage('SSH and Navigate') {
            steps {
                script {
                    // Use the 'ssh' command to connect and execute commands remotely
                    sh """
                        ssh cloud-user@${DEPLOYMENT_HOST} 'cd /data && ls -l'
                    """
                }
            }
        }
    }
}
