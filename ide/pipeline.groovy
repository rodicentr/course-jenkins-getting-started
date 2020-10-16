pipeline {
    agent any
    triggers { pollSCM('* * * * *') }
    stages {

        stage('Checkout') {
            steps {
                                // Get some code from a GitHub repository
                git branch: 'main', url: 'https://github.com/rodicentr/jgsu-spring-petclinic.git'
            }
        }

        stage('Build') {

            steps {
                sh './mvnw clean package'
            }
            
            post {
                always {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }
    }
}

