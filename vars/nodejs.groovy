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
                   // sh 'sonar-scanner -Dsonar.projectKey=${component} -Dsonar.host.url=http://172.31.91.92:9000 -Dsonar.login=admin -Dsonar.password=admin123'
                    sh 'echo COde Quality'
                }
            }

            stage('unit test cases') {
                steps {
                    sh 'echo unit test cases'
                    //sh 'npm test'
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
                when {
                    expression {
                        env.TAG_NAME ==~ ".*"
                    }
                }
                steps {
                    sh 'npm install'
                    sh 'echo $TAG_NAME >VERSION'
                    sh 'zip -r ${component}-${TAG_NAME}.zip node_module server.js VERSION'
                    sh 'curl -v -u admin:admin123 --upload-file ${component}-${TAG_NAME}.zip http://172.31.85.41:8081/repository/${component}/${component}-${TAG_NAME}.zip'
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

