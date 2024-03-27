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
                    sh 'echo code compile'
                }
            }

            stage('code Quality') {
                steps {
                    sh 'echo code quality'
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

