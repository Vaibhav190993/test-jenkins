pipeline {
    agent any

    environment {
        TAR_FILE = "fo_installer-RHEL8.6_23.0.0_FP_03_635338.tar" // Path to the tar.gz file within the workspace
        DEST_DIR = "/home/ec2-user" // Directory to extract the contents
    }

    stages {
        stage('Ansible Script: Add User to Sudoers') {
            steps {
                // Run the Ansible command to add cloud-user to sudoers with sudo privileges
                sh '''
                ansible demo -m lineinfile -ab "path=/etc/sudoers line='cloud-user ALL=(ALL) ALL' insertafter='^root'"
                '''
            }
        }

        stage('Ansible Script: Check Security Settings') {
            steps {
                // Run the Ansible command to set SELinux to disabled with sudo privileges
                sh '''
                ansible demo -m lineinfile -a "path=/etc/selinux/config regexp='^SELINUX=' line='SELINUX=disabled'"
                '''
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
