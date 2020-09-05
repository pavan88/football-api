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
            post {
                always {
                    junit 'build/test-results/test/*.xml'
                }
            }
        }
        stage('Build') {
            steps {
                sh './mvnw clean package'
            }
        }
        stage('Create the Docker Image') {
            when { branch "master" }
            steps {
                sh '''
                    docker build --no-cache -t football-api .
                    docker rmi person:latest
                '''
            }
        }
        stage('Run the Docker Container') {
            when { branch "master" }
            steps {
                sh '''
                    docker pull football-api
                    docker stop football-api
                    docker rm football-api
                    docker run -p 9090:9090 --name person -t -d football-api
                    docker rmi -f $(docker images -q --filter dangling=true)
                '''
            }
        }
    }
}