pipeline {
    agent any

    environment {
        DEPLOYMENT_HOST = '10.92.131.112'  // Remote server IP
    }

    stages {
        stage('Run Deployment Script') {  // Renamed this stage
            steps {
                // Run the init_deployment.sh script
                sh """
                    cd /data/fo_installer/ &&
                    chmod +x init_deployment.sh &&
                    ./install-fo-infrastructure.sh
                """
            }
        }
    }
}
