pipeline {
    agent any

    environment {
        TAR_FILE = "fo_installer-RHEL8.6_23.0.0_FP_03_635338.tar"   // Path to the tar.gz file within the workspace
        DEST_DIR = "/home/ec2-user"     // Directory to extract the contents
    }

    stages {
        stage('Checkout') {
            steps {
                // Checkout your repository
                git 'https://github.com/Vaibhav190993/test-jenkins.git'
            }
        }

        stage('Untar File') {
            steps {
                script {
                    untarFile(env.TAR_FILE, env.DEST_DIR)
                }
            }
        }
    }

    post {
        always {
            // Any cleanup or notification steps can go here
        }
    }
}

def untarFile(String tarFilePath, String destDirPath) {
    File tarFile = new File(tarFilePath)
    File destDir = new File(destDirPath)
    
    if (!destDir.exists()) {
        destDir.mkdirs()
    }

    def command = "tar -xvf ${tarFile.absolutePath} -C ${destDir.absolutePath}"
    def process = command.execute()

    process.waitForProcessOutput(System.out, System.err)
    
    if (process.exitValue() == 0) {
        println "File successfully extracted to ${destDirPath}"
    } else {
        println "Error extracting file. Exit code: ${process.exitValue()}"
    }
}
