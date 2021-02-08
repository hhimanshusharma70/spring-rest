pipeline { 

    environment { 
        registry = "himanshu1170/spring_demo" 
        registryCredential = 'docker_hub_id' 
        dockerImage = '' 
        fileName=":$BUILD_NUMBER" +"-${new Date().format("yyMMdd.HHmm", TimeZone.getTimeZone('UTC'))}"
        latestDockerTag="himanshu1170_spring_demo-latest" 
    }
    agent any 
    stages { 
           stage('Building our image') { 

            steps { 
                script { 
                    sh " echo $USER"
                    dockerImage = docker.build registry + fileName

                }
            } 

        }
        stage('Deploy our image') { 
            steps { 
                script { 
                   docker.withRegistry( '', registryCredential ) { 
                        dockerImage.push()
                        dockerImage.push('latest') 
                    }
                } 

            }
        } 

        stage('Cleaning up') { 
            steps { 
                sh "docker rmi $registry"+fileName 
            }
        } 
        
        stage('Deploying to EC2') {
            steps {
              sshagent(credentials: ['ems']){
		echo "Starting to EC2 deploy "
		        sh 'ssh -t -t ubuntu@ec2-13-232-192-86.ap-south-1.compute.amazonaws.com -o StrictHostKeyChecking=no " docker pull $registry:latest" '
                sh 'ssh -t -t ubuntu@ec2-13-232-192-86.ap-south-1.compute.amazonaws.com -o StrictHostKeyChecking=no " docker ps -q --filter  ancestor=$registry | xargs -r docker stop "'
                //sh 'ssh -t -t ubuntu@ec2-13-232-192-86.ap-south-1.compute.amazonaws.com -o StrictHostKeyChecking=no " docker stop $registry:latest"'
                //sh 'ssh -t -t ubuntu@ec2-13-232-192-86.ap-south-1.compute.amazonaws.com -o StrictHostKeyChecking=no " docker rm $registry:latest"'
                sh 'ssh -t -t ubuntu@ec2-13-232-192-86.ap-south-1.compute.amazonaws.com -o StrictHostKeyChecking=no " docker rm -f $latestDockerTag"'
                sh 'ssh -t -t ubuntu@ec2-13-232-192-86.ap-south-1.compute.amazonaws.com -o StrictHostKeyChecking=no "docker run -d -p 9100:80 --name=$latestDockerTag  $registry:latest" '
                }
             }
         }
    }

}


