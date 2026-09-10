pipeline {

    agent any

    tools {
        jdk 'JDK-21'
        maven 'Maven-3'
    }

    stages {

        stage('Checkout') {
            steps {
                git 'https://github.com/YOUR_USERNAME/EcommerceAPI.git'
            }
        }

        stage('Run API Tests') {
            steps {
                bat 'mvn clean test'
            }
        }
    }

    post {
        always {
            echo 'API test execution completed'
        }

        success {
            echo 'API Tests Passed'
        }

        failure {
            echo 'API Tests Failed'
        }
    }
}