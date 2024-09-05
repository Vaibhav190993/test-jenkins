pipeline {
    agent any

    environment {
        // Define the SSH credentials ID that Jenkins should use
        DEPLOYMENT_HOST = '10.92.131.112'
        TAR_FILE = "/data/fo_installer-20.11.0_453110.tar"   // Path to the tar.gz file within the workspace
        UNTAR_DIR = "/data" 
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
        stage('check login user') {
            steps {
                script {
                    // Use the 'ssh' command to connect and execute commands remotely
                    sh """
                        whoami
                    """
                }
            }
        }
        stage('Untar File') {
            steps {
                // Run the tar command directly
                sh "tar -xvf ${TAR_FILE}"
            }
        }
        stage('After Untar File check') {
            steps {
                // Run the tar command directly
                sh "ls -ld /data/fo_installer"
            }
        }
    }
}
