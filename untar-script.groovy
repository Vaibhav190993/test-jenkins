pipeline {
    agent any

    environment {
        DEPLOYMENT_HOST = '10.92.131.112'  // Remote server IP
    }

    stages {
        stage('Run infrastructure Script') {  // Renamed this stage
            steps {
                // Run the init_deployment.sh script
                sh """
                    cd /data/fo_installer/ &&
                    chmod +x install-fo-components-om.sh &&
                    ./install-fo-components-om.sh
                """
            }
        }
    }
}
