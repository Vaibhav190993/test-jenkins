pipeline {
    agent any

    environment {
        DEPLOYMENT_HOST = '10.92.131.112'  // Remote server IP
        TAR_FILE = "/data/fo_installer-20.11.0_453110.tar"  // Path to the tar file on the remote server
        UNTAR_DIR = "/data"  // Directory where the tar file will be extracted
        TARGET_DIR = "${UNTAR_DIR}/fo_installer"  // Target directory for moving the tar file
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

        stage('Create Target Directory') {
            steps {
                // Create the target directory if it doesn't exist
                sh """
                    ssh cloud-user@${DEPLOYMENT_HOST} 'mkdir -p ${TARGET_DIR}'
                """
            }
        }

        stage('Move Tar Files') {
            steps {
                // Move the tar file to the target directory
                sh """
                    ssh cloud-user@${DEPLOYMENT_HOST} 'mv /data/*.tar ${TARGET_DIR}'
                """
            }
        }

        stage('Change Directory and Check') {
            steps {
                // Change to the target directory and list its contents
                sh """
                    ssh cloud-user@${DEPLOYMENT_HOST} '
                    cd ${TARGET_DIR} &&
                    ls -l
                    '
                """
            }
        }
    }
}
