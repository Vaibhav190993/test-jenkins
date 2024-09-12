pipeline {
    agent any

    environment {
        DEPLOYMENT_HOST = '10.92.131.112'  // Remote server IP
    }

    stages {
        stage('Run Deployment Script') {  // Renamed this stage
            steps {
                // Run the install-fo-infrastructure.sh script
                sh """
                    cd /data/fo_installer/ &&
                    chmod +x install-fo-components-catalog.sh &&
                    ./install-fo-components-catalog.sh
                """
            }
        }
    }
}
