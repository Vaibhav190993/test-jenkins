pipeline {
    agent any

    environment {
        // Define the SSH credentials ID that Jenkins should use
        DEPLOYMENT_HOST = '10.92.131.112'
        TAR_FILE = "/data/fo_installer-20.11.0_453110.tar"   // Path to the tar.gz file within the workspace
        UNTAR_DIR = "/data" 
    }

    stages {
        stage('check user locally') {
            steps {
                script {
                    // Check the current user locally (on Jenkins)
                    sh "whoami"
                }
            }
        }

        stage('SSH and Navigate') {
            steps {
                script {
                    // Use SSH to connect as cloud-user to the remote server
                    sh """
                        ssh cloud-user@${DEPLOYMENT_HOST} 'cd /data && ls -l'
                    """
                }
            }
        }

        stage('Check user remotely') {
            steps {
                script {
                    // Use SSH to connect as cloud-user and check the user on the remote server
                    sh """
                        ssh cloud-user@${DEPLOYMENT_HOST} 'whoami'
                    """
                }
            }
        }

        stage('Untar File on Remote') {
            steps {
                // Use SSH to untar the file on the remote server
                sh """
                    ssh cloud-user@${DEPLOYMENT_HOST} 'tar -xvf ${TAR_FILE} -C ${UNTAR_DIR}'
                """
            }
        }

        stage('After Untar File Check on Remote') {
            steps {
                // Check the result of the untar operation on the remote server
                sh """
                    ssh cloud-user@${DEPLOYMENT_HOST} 'ls -ld ${UNTAR_DIR}/fo_installer'
                """
            }
        }
    }
}
