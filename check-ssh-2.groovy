pipeline {
    agent any

    environment {
        LOCAL_USER = 'robinmishra413'
        REMOTE_SERVER = '34.44.167.143' // Replace with your remote server address
        REMOTE_USER = 'robinmishra413'
        SSH_KEY_PATH = "/home/robinmishra413/.ssh/id_rsa.pub"
        AUTHORIZED_KEYS_PATH = "/home/robinmishra413/.ssh/authorized_keys"
    }

    stages {
        stage('Generate SSH Key') {
            steps {
                script {
                    def sshKeyFile = new File(SSH_KEY_PATH)
                    if (!sshKeyFile.exists()) {
                        echo 'Generating SSH key...'
                        sh "ssh-keygen -t rsa -b 2048 -f ${SSH_KEY_PATH} -N ''"
                    } else {
                        echo 'SSH key already exists.'
                    }
                }
            }
        }

        stage('Copy SSH Key to Remote Server') {
            steps {
                script {
                    def sshKey = readFile(SSH_KEY_PATH).trim()
                    echo 'Copying SSH key to remote server...'
                    sh """
                        echo '${sshKey}' | ssh ${REMOTE_USER}@${REMOTE_SERVER} 'cat >> ${AUTHORIZED_KEYS_PATH}'
                    """
                }
            }
        }

        stage('Configure Remote Server') {
            steps {
                script {
                    echo 'Adding user and configuring sudoers on remote server...'
                    sh """
                        ssh ${REMOTE_USER}@${REMOTE_SERVER} 'sudo useradd ${LOCAL_USER}'
                        echo "${LOCAL_USER}        ALL=(ALL)       NOPASSWD: ALL" | ssh ${REMOTE_USER}@${REMOTE_SERVER} 'sudo tee -a /etc/sudoers'
                    """
                }
            }
        }
    }

    post {
        always {
            echo 'Pipeline completed.'
        }
    }
}
