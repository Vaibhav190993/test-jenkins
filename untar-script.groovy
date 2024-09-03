pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {

            git branch: 'main', url: 'https://github.com/Vaibhav190993/test-jenkins.git'
            
            }
        }

        stage('Untar File using Python') {
            steps {
                // Run the Python script
                sh 'python3 /path/to/untar_script.py'
            }
        }
    }

    post {
        always {
            echo 'Pipeline execution completed.'
        }
    }
}
