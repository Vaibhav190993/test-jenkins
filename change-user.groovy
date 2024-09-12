pipeline {
    agent any

    environment {
        DEPLOYMENT_HOST = '10.92.131.112'  // Remote server IP
    }

    stages {
        stage('Run Deployment Script') {  // Renamed this stage
            steps {
                // Run the install-fo-components-om.sh script
                sh """
                whoami
                """
            }
        }
    }
}
