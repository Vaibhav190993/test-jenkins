pipeline {
    agent any // or specify a particular agent if needed

    environment {
        SSH_KEY_PATH = '/home/robinmishra413/.ssh/id_rsa.pub'
        CLIENT_SERVER = 'robinmishra413@34.44.167.143' // Destination server with user
    }

    stages {
        stage('Check and Generate SSH Key') {
            steps {
                script {
                    // Check if SSH key exists
                    if (fileExists(env.SSH_KEY_PATH)) {
                        echo "SSH key already exists at ${env.SSH_KEY_PATH}."
                    } else {
                        // Generate SSH key if it doesn't exist
                        echo "SSH key not found. Generating new SSH key..."
                        sh "sudo ssh-keygen -t rsa -b 4096 -f ${env.SSH_KEY_PATH} -y ''"
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
