/*

Set up Jenkins container
docker run -t --rm -u root -p 8080:8080 -v jenkins-data:/var/jenkins_home -v /var/run/docker.sock:/var/run/docker.sock -v "$HOME":/home --network mynet --name jenkins jenkinsci/blueocean

Set up DV8 container
docker run -it --rm -p 8000:8080 -v jenkins-data:/var/jenkins_home --network mynet --name dv8-console dv8-console java -jar gs-rest-service-0.1.0.jar

See the containers ip address
docker network inspect mynet

ServiceComponentRuntime Jenkinsfile

*/
pipeline {
    agent any
    options {
        skipStagesAfterUnstable()
    }
    environment {
        DV8_CONSOLE_IP='172.18.0.2:8080'
        WORKING_DIR='/var/jenkins_home/workspace/ServiceComponentRuntime@2'
    }
    stages {
    
        stage('Build') {
            agent {
                docker {
                    image 'maven:3-alpine'
                    args '-v /root/.m2:/root/.m2'
                }
            }
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        } 
        
        /*
        stage('Sonarqube analysis') {
            environment {
                SONAR_SCANNER_OPTS = "-Xmx2g -Dsonar.projectKey=ServiceComponentRuntime -Dsonar.login=534e3ba62bc724b92c4e3ba2b771ffe3d8dfa08a -Dsonar.java.binaries=${WORKING_DIR}/target/classes"
            } 
            steps {
                withSonarQubeEnv('SonarQube') {
                    sh "/var/jenkins_home/sonar-scanner/sonar-scanner-3.3.0.1492-linux/bin/sonar-scanner -Dproject.settings=sonar-project.properties"
                }
            }
        }
        */
        

        stage('DV8 analysis') {
            steps {
                /* sh 'curl http://${DV8_CONSOLE_IP}:8080/test-connection 2>/dev/null|jq -r .result' 

                echo "preprocessing files:"
                sh 'curl http://${DV8_CONSOLE_IP}/preprocessor?directory=${WORKING_DIR}'
                

                echo "generating arch-report:"
                sh 'curl http://${DV8_CONSOLE_IP}/arch-report?directory=${WORKING_DIR}'

                echo "Propagation cost ="
                echo sh(returnStdout: true, script: 'curl -X POST http://${DV8_CONSOLE_IP}/metrics -d "directory=${WORKING_DIR}&metric=pc" 2>/dev/null')
                
                echo "Decoupling level ="
                echo sh(returnStdout: true, script: 'curl -X POST http://${DV8_CONSOLE_IP}/metrics -d "directory=${WORKING_DIR}&metric=dl" 2>/dev/null')

            }
        }


    }
}
