pipeline {
    agent any

    environment {
        DEPLOYMENT_HOST = '10.92.131.112'  // Remote server IP
        TAR_FILE = "/data/fo_installer-20.11.0_453110.tar"  // Path to the tar file on the remote server
        UNTAR_DIR = "/data"  // Directory where the tar file will be extracted
    }

    stages {
        stage('Untar File on Remote') {
            steps {
                // Use SSH to untar the file on the remote server
                sh """
                    ssh cloud-user@${DEPLOYMENT_HOST} 'tar -xvf ${TAR_FILE} -C ${UNTAR_DIR}'
                """
            }
        }

        stage('Move and Change Directory') {
            steps {
                // Move the tar file and change to the 'fo_installer' directory
                sh """
                    ssh cloud-user@${DEPLOYMENT_HOST} '
                    mv /data/*.tar fo_installer/ &&
                    cd ${UNTAR_DIR}/fo_installer
                    '
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
