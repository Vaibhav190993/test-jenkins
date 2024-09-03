pipeline {
    agent any

    stages {
        stage('Create a File') {
            steps {
                sh 'echo "Hello, World!" > myfile.txt'
            }
        }

        stage('Read a File') {
            steps {
                script {
                    def content = readFile 'myfile.txt'
                    echo "File content: ${content}"
                }
            }
        }
    }
}
