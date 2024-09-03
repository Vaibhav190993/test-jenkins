pipeline {
    agent any // or specify a particular agent if needed

    environment {
        SSH_KEY_PATH = '/home/ec2-user/.ssh/id_rsa' // Path to the private key
        CLIENT_SERVER = 'ec2-user@54.66.47.15' // Destination server with user
    }

    stages {
        stage('Check and Generate SSH Key') {
            steps {
                script {
                    // Use shell command to check if the SSH key file exists
                    def keyExists = sh(script: "test -f ${env.SSH_KEY_PATH}", returnStatus: true) == 0

                    if (keyExists) {
                        echo "SSH key already exists at ${env.SSH_KEY_PATH}."
                    } else {
                        // Generate SSH key if it doesn't exist
                        echo "SSH key not found. Generating new SSH key..."
                        sh "ssh-keygen -t rsa -b 4096 -f ${env.SSH_KEY_PATH} -N ''"
                        echo "SSH key generated successfully."
                    }
                }
            }
        }

        stage('Copy SSH Key to Destination Server') {
            steps {
                script {
                    // Copy SSH key to the destination server
                    echo "Copying SSH key to the destination server..."
                    sh "ssh-copy-id -i ${env.SSH_KEY_PATH}.pub ${env.CLIENT_SERVER}"
                    echo "SSH key copied successfully to ${env.CLIENT_SERVER}."
                }
            }
        }
    }

    post {
        failure {
            echo 'Pipeline failed.'
        }
        success {
            echo 'Pipeline succeeded.'
        }
    }
}
