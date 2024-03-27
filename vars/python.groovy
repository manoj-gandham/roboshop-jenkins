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

            stage('code Quality') {
                steps {
                    sh 'sonar-scanner -Dsonar.projectKey=${component} -Dsonar.host.url=http://172.31.91.92:9000 -Dsonar-login=admin Dsonar-password=admin123'
                }
            }

            stage('unit test cases') {
                steps {
                    sh 'echo unit test cases'
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

        }

        post {
            always {
                cleanWs()
            }
        }

    }
}

