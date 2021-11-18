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
           
        
        post{
		success{
			emailext body: 'Build success', subject: 'Jenkins', to:'jenkins.oussama.issaoui@gmail.com
'
		}
		failure{
			emailext body: 'Build failure', subject: 'Jenkins', to:'jenkins.oussama.issaoui@gmail.com'
		}
	}
      
      
       }
