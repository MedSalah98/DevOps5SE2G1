pipeline {



agent any

tool name: 'terraform', type: 'terraform'

stages{


       stage('Checkout GIT'){
       steps{
             echo 'check';
            checkout([$class: 'GitSCM', branches: [[name: '*/oussama-branch']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/MedSalah98/DevOps5SE2G1.git']]])
         }
}

         
         stage("Clean Package"){
          steps{
              bat""" mvn -version"""
          bat """mvn clean package -Dmaven.test.skip=true"""
          }
          }
       
       stage("Test,Sonar"){
          steps{
             
          bat """mvn clean verify sonar:sonar -Dsonar.projectKey=Timesheet_devops -Dsonar.host.url=http://localhost:9000 -Dsonar.login=d58be3c78edd2135c0dc3a7e273688e4fb56cc02"""
          }
          }
          
         
          
          stage("Nexus"){
          steps{
          bat """mvn deploy"""
          }
          }
       
        stage("Build Docker Image") {
                steps {
                script {
                    
                       
                       bat """docker build -t oussamaissaoui/timesheet:1.0 ."""
                    
                       }
                      }
              }
       
       
       stage("Push Image in DockerHub") {
                steps {
                script {
                    
                       
                                   withCredentials([string(credentialsId: 'oussamaissaoui', variable: 'dockerHubCred')]) {
                                       
                                          bat """docker login -u oussamaissaoui -p ${dockerHubCred}"""
                                          bat """docker push oussamaissaoui/timesheet:1.0"""
                                                                             }
                    
                       }
                      }
              }

       
         stage("Cleaning docker's local repos") {
                steps {
                script {
                    
                       
                                   
                                          bat """docker rmi oussamaissaoui/timesheet:1.0"""
                                                                             
                    
                       }
                      }
              }

       
       stage("Terraform Init ") {
                steps {
                
                    
                       
                                   
                                          bat """terraform init"""
                       
                      }
              }

       stage("Terraform Apply ") {
                steps {
                
                    
                       
                                   
                                          bat """terraform apply --auto-approve"""
                       
                      }
              }
          
       
       
       






              }
           
      
      
       }
