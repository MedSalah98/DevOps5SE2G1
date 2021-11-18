pipeline {



agent any

        tools {
        terraform 'terraform'
    }

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
