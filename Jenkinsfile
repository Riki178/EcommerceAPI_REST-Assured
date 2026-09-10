pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                git branch: 'master',
                    url: 'https://github.com/Riki178/EcommerceAPI_REST-Assured.git'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Run Tests') {
            steps {
                bat 'mvn test'
            }
        }
    }

    post {
        always {
            echo 'Test execution completed'
        }

        success {
            echo 'Tests passed successfully'
        }

        failure {
            echo 'Tests failed'
        }
    }
}