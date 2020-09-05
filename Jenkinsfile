#!/usr/bin/env groovy
pipeline {
    agent any
    triggers {
        pollSCM('* * * * *')
    }
    options { disableConcurrentBuilds() }
    stages {
        stage('Permissions') {
            steps {
                sh 'chmod 775 *'
            }
        }
        stage('Cleanup') {
            steps {
                sh './mvnw clean'
            }
        }

        stage('Test') {
            steps {
                sh './mvnw test'
            }
        }

        stage('Build') {
            steps {
                sh './mvnw clean package'
            }
        }

        stage('Create the Docker Image') {
            steps {
                sh '''
                    docker build --no-cache -t football-api .
                '''
            }
        }
        stage('Run the Docker Container') {
            steps {
                sh '''
                    docker stop football-api
                    docker rm football-api
                    docker run -p 8080:8080 --name football-api -t football-api
                    docker rmi -f $(docker images -q --filter dangling=true)
                '''
            }
        }
    }
}