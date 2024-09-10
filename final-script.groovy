pipeline {
    agent any

    environment {
        DEPLOYMENT_HOST = '10.91.45.198'  // Remote server IP
        TAR_FILE = "/data/fo_installer-ROCKY8.6_23.0.0_FP_03_638238.tar"  // Path to the tar file on the remote server
        UNTAR_DIR = "/data"  // Directory where the tar file will be extracted
        TARGET_DIR = "${UNTAR_DIR}/fo_installer"  // Target directory for moving the tar file
    }

    stages {
        stage('Untar File on Remote') {
            steps {
                // Use SSH to untar the file on the remote server
                sh """
                    'tar -xvf ${TAR_FILE} -C ${UNTAR_DIR}'
                """
            }
        }

        stage('Create Target Directory') {
            steps {
                // Create the target directory if it doesn't exist
                sh """
                    'mkdir -p ${TARGET_DIR}'
                """
            }
        }

        stage('Move Tar Files') {
            steps {
                // Move the tar file to the target directory
                sh """
                    'mv /data/*.tar ${TARGET_DIR}'
                """
            }
        }

        stage('Copy Configuration File') {
            steps {
                // Copy the configuration file to the target directory locally
                sh """
                    cp /data/configurations.json /data/fo_installer
                """
            }
        }
        stage('Copy Yaml File') {
            steps {
                // Copy the file to the target directory
                sh """
                    cp /data/all-envs-and-hosts.yml /data/fo_installer/inventory/yaml/all-envs-and-hosts.yml
                """
            }
        }
        stage('Initialize FlowOne Fulfillment deployment') {
            steps {
                // Copy the file to the target directory
                sh """
                    '/data/fo_installer/init_deployment.sh'
                """
            }
        }

        stage('Change Directory and Check') {
            steps {
                // Change to the target directory and list its contents
                sh """
                    '
                    cd ${TARGET_DIR} &&
                    ls -l
                    '
                """
            }
        }
        stage('Initialize FlowOne Fulfillment deployment') {
            steps {
                // Copy the file to the target directory
                sh """
                cd /data/fo_installer/ &&
                    ./init_deployment.sh
                """
            }
        }
        stage('Run infrastructure Script') {
            steps {
                // Run the fo-infrastructure.sh script
                sh """
                    cd /data/fo_installer/ &&
                    chmod +x install-fo-infrastructure.sh &&
                    ./install-fo-infrastructure.sh
                """
            }
        }
        stage('Run Fo Components Script') {
            steps {
                // Run the fo-components-om script
                sh """
                    cd /data/fo_installer/ &&
                    chmod +x install-fo-components-om.sh &&
                    ./install-fo-components-om.sh
                """
            }
        }
        stage('Run Fo Components Catalog Script') { 
            steps {
                // Run the fo-components-catalog script
                sh """
                    cd /data/fo_installer/ &&
                    chmod +x install-fo-components-catalog.sh &&
                    ./install-fo-components-catalog.sh
                """
            }
        }
        stage('Run Fo Components flowoneapi Script') {
            steps {
                // Run the fo-components-flowoneapi script
                sh """
                    cd /data/fo_installer/ &&
                    chmod +x install-fo-components-flowoneapi.sh &&
                    ./install-fo-components-flowoneapi.sh
                """
            }
        }
        stage('Run Fo Components wso2 Script') {
            steps {
                // Run the fo-components-wso2 script
                sh """
                    cd /data/fo_installer/ &&
                    chmod +x install-fo-components-wso2.sh &&
                    ./install-fo-components-wso2.sh
                """
            }
        }
    }
}
