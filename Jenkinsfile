pipeline {
  agent any
  stages {
    
   stage ('test') { // la phase build
steps {
bat 'gradlew test'
 junit 'build/test-results/test/TEST-Matrix.xml'
  
   cucumber buildStatus: 'UNSTABLE',
                reportTitle: 'My report',
                fileIncludePattern: 'target/report.json',
     
                trendsLimit: 10

}
}
   
    
        stage('Code Analysis') {
          steps {
            withSonarQubeEnv('sonar') {
              bat 'gradle sonar'
            }


          }
        }
    
    
}

}
