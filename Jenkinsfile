pipeline {



agent any
stages{


       stage('Checkout GIT'){
       steps{
             echo 'check';
            checkout([$class: 'GitSCM', branches: [[name: '*/oussama-branch']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/MedSalah98/DevOps5SE2G1.git']]])
         }


         
         stage("Test,Build"){
          steps{
              bat""" mvn -version"""
          bat """mvn clean package -Dmaven.test.skip=true"""
          }
          }
          
         
          
          stage("Nexus"){
          steps{
          bat """mvn deploy"""
          }
          }

          }
       
     
      
      
       }