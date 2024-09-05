pipeline {
    agent any
    stages {
        stage('Run as Cloud User') {
            steps {
                script {
                    // Ensure sudo permissions are configured for `jenkins` to `clouduser`
                    sh 'sudo -u clouduser whoami'
                }
            }
        }
    }
}
