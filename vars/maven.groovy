def call () {
    pipeline {

        agent {
            node {
                label 'workstation'
            }
        }

        options {
            ansiColor('xterm')
        }


        stages {

            stage('code Compile') {
                steps {
                    sh 'mvn compile'
                    sh 'echo code compile'
                }
            }

            stage('code Quality') {
                steps {
                    //sh 'sonar-scanner -Dsonar.projectKey=${component} -Dsonar.host.url=http://172.31.91.92:9000 -Dsonar.login=admin -Dsonar.password=admin123 -Dsonar.qualitygate.wait=true -Dsonar.java.binaries=./target'
                    sh 'echo code quality'
                }
            }

            stage('unit test cases') {
                steps {
                    sh 'echo unit test cases'
                    //sh 'mvn test'
                }
            }

            stage('checkmark SAST Scan') {
                steps {
                    sh 'echo Checkmark SAST '
                }
            }

            stage('checkmark SCA Scan') {
                steps {
                    sh 'echo Checkmark SCA '
                }
            }

            stage('Application Release') {
                steps {
                    sh 'env'
                    sh 'release'
                }
            }

        }

        post {
            always {
                cleanWs()
            }
        }

    }
}


