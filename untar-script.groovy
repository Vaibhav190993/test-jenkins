pipeline {
    agent any

    environment {
        PYTHON_VERSION = '/usr/bin/python3'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/Vaibhav190993/test-jenkins.git'
            }
        }
        stage('Verify Python Installation') {
            steps {
                script {
                    sh '''
                    which python3
                    python3 --version
                    '''
                }
            }
        }
        stage('Setup') {
            steps {
                script {
                    sh '''
                    ${PYTHON_VERSION} -m venv venv
                    . venv/bin/activate
                    pip install -r requirements.txt
                    '''
                }
            }
        }
        stage('Untar File using Python') {
            steps {
                script {
                    sh '''
                    . venv/bin/activate
                    python untar_script.py
                    '''
                }
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
