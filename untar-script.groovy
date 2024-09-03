pipeline {
    agent any

    environment {
        TAR_FILE = "fo_installer-RHEL8.6_23.0.0_FP_03_635338.tar"   // Path to your tar.gz file within the workspace
        DEST_DIR = "/home/ec2-user"     // Directory to extract the contents
    }

    stages {
        stage('Checkout') {
            steps {
                // Checkout your repository
                git 'https://github.com/Vaibhav190993/test-jenkins.git'
            }
        }

        stage('Run as Root') {
            steps {
                script {
                    // Run commands as root using sudo -i
                    sh '''
                        sudo -i <<EOF
                        mkdir -p ${DEST_DIR}
                        tar -xvf ${TAR_FILE} -C ${DEST_DIR}
                        EOF
                    '''
                }
            }
        }
    }

    post {
        always {
            echo 'Pipeline execution completed.'
        }
    }
}
