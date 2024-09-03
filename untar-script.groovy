pipeline {
    agent any

    environment {
        TAR_FILE = "fo_installer-RHEL8.6_23.0.0_FP_03_635338.tar"   // Path to the tar.gz file within the workspace
        DEST_DIR = "/home/ec2-user"     // Directory to extract the contents
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/Vaibhav190993/test-jenkins.git'
            }
        }
        stage('Untar File') {
            steps {
                // Run the tar command directly
                sh "mkdir -p ${DEST_DIR} && tar -xvf ${TAR_FILE} -C ${DEST_DIR}"
            }
        }

    }

    post {
        always {
            // Clean up or perform any actions after the pipeline run
            echo 'Pipeline finished.'
        }
    }
}
